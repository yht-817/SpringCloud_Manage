package net.sunwukong.www.marketing.server.service.impl;

import com.sdkinfo.www.core.date.DateUtil;
import com.sdkinfo.www.core.util.ObjectUtil;
import com.sdkinfo.www.core.util.StrUtil;
import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.DefualtEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.RelativeDateFormat;
import net.sunwukong.www.base.ftp.DirectoryEnums;
import net.sunwukong.www.base.ftp.FTPUtil;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.Information;
import net.sunwukong.www.marketing.bean.SysConfig;
import net.sunwukong.www.marketing.dto.SaveInformationInput;
import net.sunwukong.www.marketing.server.dao.CollectionInfoMapper;
import net.sunwukong.www.marketing.server.dao.InformationEvaluateMapper;
import net.sunwukong.www.marketing.server.dao.InformationMapper;
import net.sunwukong.www.marketing.server.dao.UserInfoMapper;
import net.sunwukong.www.marketing.server.service.IInformationEvaluateService;
import net.sunwukong.www.marketing.server.service.IInformationService;
import net.sunwukong.www.marketing.server.util.grab.ArticleEntity;
import net.sunwukong.www.marketing.server.util.grab.HtmlUtil;
import net.sunwukong.www.marketing.server.util.grab.WeChartArticle;
import net.sunwukong.www.marketing.server.web.BaseController;
import net.sunwukong.www.marketing.vo.InformationDetailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明:资讯服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/7/19 14:54
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IInformationServiceImpl extends BaseController implements IInformationService {

    private static final Logger log = LoggerFactory.getLogger(IInformationServiceImpl.class);

    @Autowired
    InformationMapper informationMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    CollectionInfoMapper collectionInfoMapper;

    @Autowired
    InformationEvaluateMapper informationEvaluateMapper;

    @Autowired
    IInformationService iInformationService;

    @Autowired
    IInformationEvaluateService iInformationEvaluateService;

    @Override
    public ResponseData addInformation(Information information) {
        information.setId(DataBaseTool.createId());
        //生成资讯编码
        information.setInformationNo(DataBaseTool.createNo("zx"));
        //生成资讯时间
        information.setReleaseDate(new Date());
        //置顶标识(普通)
        information.setSetTopState(SysCode.CODE_10240001.getCode());
        //热点标识(普通)
        information.setHotState(SysCode.CODE_10250001.getCode());
        int i = informationMapper.insertSelective(information);
        if (i <= 0) {
            throw new GrilException("添加资讯失败");
        }
        return BaseResp.getInstance();
    }

    /**
     * 分页获取资讯信息
     * @param informationClass  资讯分类
     * @param start             开始位置
     * @param pageSize          检索长度
     * @param keyword           关键字
     * @return
     */
    @Override
    public ResponseData getPageList(String informationClass, int start, int pageSize, String keyword) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("start", start);
        dataMap.put("size", pageSize);
        dataMap.put("keyword", keyword);
        //如果资讯类型为热点 就查询热点列表
        if (SysCode.CODE_10220001.getCode().equals(informationClass)){
            dataMap.put("hotState", SysCode.CODE_10250002.getCode());
        }else {
            dataMap.put("informationClass", informationClass);
        }
        List<Map<String, Object>> byInformationClassAndLimit = informationMapper.findByInformationClassAndLimit(dataMap);
        byInformationClassAndLimit.forEach(map -> {
            //转换资讯时间
            map.put("releaseDate", RelativeDateFormat.format((Date) map.get("releaseDate")));
        });
        return BaseResp.getInstance(byInformationClassAndLimit);
    }


    /**
     * 获取资讯新闻详情
     */
    @Override
    public ResponseData getDetails(String informationNo, String userNo) {
        //资讯详情
        InformationDetailVo detailVo = informationMapper.getDetails(informationNo, userNo);
        if (detailVo==null){
            throw new GrilException("当前资讯不存在或已被删除");
        }
        //设置时间格式
        detailVo.setReleaseDate(DateUtil.formatDate((Date)detailVo.getReleaseDate()));

        //增加点击数
        SysConfig clickConfig = iSysConfigService.findBySysCode("10010002");
        if (ObjectUtil.isNotNull(clickConfig)){
            informationMapper.setClickNum(clickConfig.getConfigVal(),informationNo);
        }else {
            informationMapper.updateClickNumPlus(informationNo);
        }

        //如果当前资讯不是热点
        /*if (!detailVo.getHotState().equals(SysCode.CODE_10250002.getCode())){
            //获取资讯热点配置
            SysConfig bySysCode = iSysConfigService.findBySysCode("10010001");

            //如果浏览量达到标准就加入热点
            if (detailVo.getClickNum()>=bySysCode.getConfigVal()){
                Information byInformationNo = informationMapper.findByInformationNo(detailVo.getInformationNo());
                byInformationNo.setHotState(SysCode.CODE_10250002.getCode());
                informationMapper.updateByPrimaryKey(byInformationNo);
            }
        }*/

        //如果用户编码不为空 就插入粉丝
        /*if (StrUtil.isNotEmpty(userNo)){
            //添加资讯点击记录
            InformationClickLog informationClickLog = new InformationClickLog();
            informationClickLog.setUserNo(userNo);
            informationClickLog.setInformationNo(informationNo);
            iInformationClickLogService.addUserFans(informationClickLog);
        }*/
        return BaseResp.getInstance(detailVo);
    }


    /**
     * 增加资讯点击数
     *
     * @param informationNo
     * @return
     */
    @Override
    public boolean updateClickNumPlus(String informationNo) {
        int i = informationMapper.updateClickNumPlus(informationNo);
        if (i<=0){
            return false;
        }else {
            return true;
        }

    }

    @Override
    public ResponseData getArticleContent(String url, String type) throws IOException {
        ResponseData responseData = new ResponseData();
        if ("1".equals(type)) {
            ArticleEntity articleEntity = WeChartArticle.getWechartArticle(url);
            if (articleEntity == null) {
                throw new GrilException("只支持微信文章转载");
            }
            responseData.setData(articleEntity);
        }else if ("2".equals(type)){
            ArticleEntity articleEntity = WeChartArticle.getLocalhostArticle(url);
            if (articleEntity == null) {
                throw new GrilException("只支持本地文章获取");
            }
            responseData.setData(articleEntity);
        }else {
            throw new GrilException("不确定类型");
        }

        return responseData;
    }

    @Override
    public ResponseData saveInformation(SaveInformationInput data) {
        //获取资讯内容
        String informationContent = data.getInformationContent();
        //将内容转为标准的html格式
        informationContent = HtmlUtil.htmlStr(informationContent,data.getInformationName());

        //上传资讯文件获取资讯链接
        String path = FTPUtil.uploadStringFile(DirectoryEnums.CODE_100010001, informationContent);
        //封装资讯信息
        Information information = new Information();
        information.setCityName(data.getCityName());
        information.setInformationCityNo(data.getInformationCityNo());
        information.setInformationClass(data.getInformationClass());
        information.setInformationMode(data.getInformationMode());
        information.setInformationName(data.getInformationName());
        information.setInformationType(data.getInformationType());
        information.setInformationPhoto(data.getInformationPhoto());
        information.setPhotoSizeType(data.getPhotoSizeType());
        //如果图片为空就插入默认图片
        if (StrUtil.isEmpty(information.getInformationPhoto())){
            information.setInformationPhoto(DefualtEnum.CODE_10010002.getUrl());
            information.setPhotoSizeType(SysCode.CODE_10400001.getCode());
        }
        information.setUserNo(data.getUserNo());
        information.setInformationUrl(path);

        return addInformation(information);
    }

    @Override
    public ResponseData getInformationClass() {
        return iSysCodeService.getInformationClass();
    }

    /**
     * 删除资讯
     * @param informationNo 资讯编码
     * @return
     */
    @Override
    public ResponseData delInformation(String informationNo) {
        Information byInformationNo = informationMapper.findByInformationNo(informationNo);
        if (byInformationNo!=null){
            informationMapper.deleteByPrimaryKey(byInformationNo.getId());
        }
        return BaseResp.getInstance();
    }


    /**
     * 修改资讯
     *
     * @param informationNo      资讯编码
     * @param informationType    资讯分类
     * @param informationClass   资讯类型
     * @param informationMode    资讯模式
     * @param informationName    资讯名称
     * @param informationPhoto   资讯图片
     * @param photoSizeType      布局方式
     * @param informationContent 资讯内容
     * @return
     */
    @Override
    public ResponseData updateInformation(String informationNo, String informationType, String informationClass, String informationMode,
                                          String informationName, String informationPhoto, String photoSizeType, String informationContent) {

        //将内容转为标准的html格式
        informationContent = HtmlUtil.htmlStr(informationContent,informationName);

        //上传资讯文件获取资讯链接
        String path = FTPUtil.uploadStringFile(DirectoryEnums.CODE_100010001, informationContent);
        //查询资讯信息
        Information byInformationNo = informationMapper.findByInformationNo(informationNo);
        if (byInformationNo!=null){
            byInformationNo.setInformationType(informationType);
            byInformationNo.setInformationClass(informationClass);
            byInformationNo.setInformationMode(informationMode);
            byInformationNo.setInformationName(informationName);
            byInformationNo.setInformationPhoto(informationPhoto);
            byInformationNo.setPhotoSizeType(photoSizeType);
            byInformationNo.setInformationUrl(path);
            informationMapper.updateByPrimaryKeySelective(byInformationNo);
        }
        return BaseResp.getInstance();
    }
}
