package net.sunwukong.www.user.server.service.impl;

import com.sdkinfo.www.core.bean.BeanUtil;
import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.crypto.SecureUtil;
import net.sunwukong.www.api.crypto.digest.DigestAlgorithm;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.util.ApiTool;
import net.sunwukong.www.user.output.MobileVersionOutput;
import net.sunwukong.www.user.server.dao.usercenter.read.MobileVersionMapperRead;
import net.sunwukong.www.user.server.model.MobileVersion;
import net.sunwukong.www.user.server.service.IMobileVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 说明:APP版本升级接口实现
 *
 * @author swk
 * @CreateDate 2018/8/20 18:27
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IMobileVersionServiceImpl implements IMobileVersionService {
    @Autowired
    MobileVersionMapperRead mobileVersionMapperRead;

    /**
     * 获取APP版本信息
     * @param appName   app名称（ios android）
     * @param appType   app类型（测试:0 正式:1）
     * @param version   当前版本
     * @return
     */
    @Override
    public ResponseData getAppVersion(String appName, String appType,String version) {
        MobileVersionOutput output = new MobileVersionOutput();
        try {
            //根据当前给定的数据获取数据库的版本信息
            MobileVersion mobileVersion = mobileVersionMapperRead.findByAppNameAndAppTypeOrderByAppVersion(appName, appType);
            //如果版本信息为NULL者不更新
            if (mobileVersion==null){
                output.setUpdate(false);
            }else {
                int compareVersion = ApiTool.compareVersion(version, mobileVersion.getAppVersion());
                //如果版本号相同则不更新
                if (compareVersion==0) {//版本号相等不需要更新版本
                    output.setUpdate(false);
                } else if (compareVersion>0){//当前版本大于手机中的版本号，那么就不升级
                    output.setUpdate(false);
                }else if (compareVersion<0){//当前版本低于数据库中的版本 ，需要升级提示
                    output.setUpdate(true);
                    output.setUpdateLog(mobileVersion.getAppLog());
                    output.setApkFileUrl(mobileVersion.getAppFilePath());
                    output.setConstraints(mobileVersion.getConstraints().equalsIgnoreCase("1")?true:false);
                    output.setNewVersion(mobileVersion.getAppVersion());
                    output.setTargetSize(mobileVersion.getTargetSize());
                    Map<String, Object> beanToMap = BeanUtil.beanToMap(output, true, true);
                    output.setSign(SecureUtil.signParams(DigestAlgorithm.MD5, beanToMap, "&", "=", true));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseResp.getInstance(output);
    }
}
