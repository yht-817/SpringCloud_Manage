package net.sunwukong.www.marketing.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.sunwukong.www.marketing.bean.UserApplyCategory;
import net.sunwukong.www.marketing.bean.UserApplyCity;
import net.sunwukong.www.marketing.bean.UserApplyPhoto;
import net.sunwukong.www.marketing.bean.UserApplyScope;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 说明:提交申请接收对象
 *
 * @author Mick
 * @CreateDate 2018/6/28 17:19
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "SubmitApplyVo",description = "提交申请参数")
public class SubmitApplyVo {

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    @NotNull(message = "用户编码不能为空")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "phoneNo",value = "手机号")
    @NotNull(message = "手机号不能为空")
    private String phoneNo;

    @ApiModelProperty(dataType = "String",name = "sexNo",value = "性别(男：10010001 女：10010002)")
    @NotNull(message = "性别不能为空")
    private String sexNo;

    @ApiModelProperty(dataType = "String",name = "applyType",value = "申请类型(个体首次申请:10300001 个体补充申请:10300002 机构首次申请:10300003 机构补充申请:10300004)")
    @NotNull(message = "申请类型不能为空")
    private String applyType;

    @ApiModelProperty(dataType = "String",name = "address",value = "地址")
    @NotNull(message = "地址不能为空")
    private String address;

    @ApiModelProperty(dataType = "String",name = "userNoManger",value = "上级编码")
    private String userNoManger;

    @ApiModelProperty(dataType = "String",name = "contractNumber",value = "机构电话")
    private String contractNumber;

    @ApiModelProperty(dataType = "String",name = "userApplyCategories",value = "服务类目列表")
    @NotNull(message = "服务类目列表不能为空")
    private List<UserApplyCategory> userApplyCategories;

    @ApiModelProperty(dataType = "String",name = "userApplyCitys",value = "所在城市列表")
    @NotNull(message = "所在城市列表不能为空")
    private List<UserApplyCity> userApplyCitys;

    @ApiModelProperty(dataType = "String",name = "userApplyScopes",value = "服务范围列表")
    @NotNull(message = "服务范围列表不能为空")
    private List<UserApplyScope> userApplyScopes;

    @ApiModelProperty(dataType = "String",name = "userApplyPhotos",value = "用户证件列表")
    @NotNull(message = "用户证件列表不能为空")
    private List<UserApplyPhoto> userApplyPhotos;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getSexNo() {
        return sexNo;
    }

    public void setSexNo(String sexNo) {
        this.sexNo = sexNo;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserNoManger() {
        return userNoManger;
    }

    public void setUserNoManger(String userNoManger) {
        this.userNoManger = userNoManger;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public List<UserApplyCategory> getUserApplyCategories() {
        return userApplyCategories;
    }

    public void setUserApplyCategories(List<UserApplyCategory> userApplyCategories) {
        this.userApplyCategories = userApplyCategories;
    }

    public List<UserApplyCity> getUserApplyCitys() {
        return userApplyCitys;
    }

    public void setUserApplyCitys(List<UserApplyCity> userApplyCitys) {
        this.userApplyCitys = userApplyCitys;
    }

    public List<UserApplyPhoto> getUserApplyPhotos() {
        return userApplyPhotos;
    }

    public void setUserApplyPhotos(List<UserApplyPhoto> userApplyPhotos) {

        this.userApplyPhotos = userApplyPhotos;
    }

    public List<UserApplyScope> getUserApplyScopes() {
        return userApplyScopes;
    }

    public void setUserApplyScopes(List<UserApplyScope> userApplyScopes) {
        this.userApplyScopes = userApplyScopes;
    }
}
