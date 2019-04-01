package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.marketing.bean.UserApplyCategory;
import net.sunwukong.www.marketing.bean.UserApplyCity;
import net.sunwukong.www.marketing.bean.UserApplyPhoto;
import net.sunwukong.www.marketing.bean.UserApplyScope;
import net.sunwukong.www.marketing.server.web.BaseController;
import net.sunwukong.www.marketing.vo.SubmitApplyVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserApplyServiceImplTest extends BaseController {

    /**
     * 服务申请
     */
    @Test
    public void saveSubmitApply() {
        SubmitApplyVo submitApplyVo = new SubmitApplyVo();

        submitApplyVo.setUserNo("yh201808061607196828");
        submitApplyVo.setPhoneNo("13952032658");
        submitApplyVo.setSexNo("10010001");
        //个体服务首次申请
        //submitApplyVo.setApplyType(SysCode.CODE_10300001.getCode());
        //个体服务补充申请
        //submitApplyVo.setApplyType(SysCode.CODE_10300002.getCode());
        //服务机构首次申请
        submitApplyVo.setApplyType(SysCode.CODE_10300003.getCode());
        //服务机构补充申请
        //submitApplyVo.setApplyType(SysCode.CODE_10300004.getCode());
        submitApplyVo.setAddress("");
        submitApplyVo.setUserNoManger("yh201808061111189873");
        submitApplyVo.setContractNumber("");
        //封装用户所在城市列表
        List<UserApplyCity> userApplyCities = new ArrayList<>();
        //封装用户所在城市对象
        UserApplyCity userApplyCity = new UserApplyCity();
        userApplyCity.setCityNo("10001000");
        userApplyCity.setCityName("纽约");
        userApplyCities.add(userApplyCity);
        //封装用户服务类目列表
        List<UserApplyCategory> userApplyCategories = new ArrayList<>();
        //封装用户服务类目
        UserApplyCategory userApplyCategory = new UserApplyCategory();
        userApplyCategory.setCategoryOneNo("10001");
        userApplyCategory.setCategoryOneName("出国咨询");
        userApplyCategory.setCategoryTwoNo("1000100002");
        userApplyCategory.setCategoryTwoName("移民服务");
        userApplyCategories.add(userApplyCategory);
        //封装用户服务范围列表
        List<UserApplyScope> userApplyScopes = new ArrayList<>();
        //封装用户服务范围对象
        UserApplyScope userApplyScope = new UserApplyScope();
        userApplyScope.setScopeOneNo("201");
        userApplyScope.setScopeOneName("中国");
        userApplyScope.setScopeTwoNo("20101");
        userApplyScope.setScopeTwoName("四川");
        userApplyScopes.add(userApplyScope);
        //封装用户证件列表
        List<UserApplyPhoto> userApplyPhotos = new ArrayList<>();
        //封装用户证件对象
        UserApplyPhoto userApplyPhoto = new UserApplyPhoto();
        userApplyPhoto.setDataPhotoType("10310001");
        userApplyPhoto.setDataPhoto("http://zhenmian.jpg");
        userApplyPhotos.add(userApplyPhoto);

        userApplyPhoto = new UserApplyPhoto();
        userApplyPhoto.setDataPhotoType("10310002");
        userApplyPhoto.setDataPhoto("http://fanmian.jpg");
        userApplyPhotos.add(userApplyPhoto);

        submitApplyVo.setUserApplyCitys(userApplyCities);
        submitApplyVo.setUserApplyCategories(userApplyCategories);
        submitApplyVo.setUserApplyPhotos(userApplyPhotos);
        submitApplyVo.setUserApplyScopes(userApplyScopes);
        iUserApplyService.saveSubmitApply(submitApplyVo);
    }

    /*@Test
    public void updateAuditUserApply() {
        //已审核
        String auditType = SysCode.CODE_10070002.getCode();
        //已驳回
        //String auditType = SysCode.CODE_10070003.getCode();
        iUserApplyService.updateAuditUserApply("apply201807080154167331",auditType);
    }*/
}