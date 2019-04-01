package net.sunwukong.www.chat.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 说明:审核好友/群对象
 *
 * @author Mick
 * @CreateDate 2018/7/11 17:16
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "AuditVo",description = "审核对象")
public class AuditVo {
    @ApiModelProperty(dataType = "String", name = "inviteNo", value = "申请编码")
    @NotNull(message = "申请编码不能为空")
    private String inviteNo;
    @ApiModelProperty(dataType = "String",name = "auditState",value = "审核状态")
    @NotNull(message = "审核状态不能为空")
    private String auditState;

    public String getInviteNo() {
        return inviteNo;
    }

    public void setInviteNo(String inviteNo) {
        this.inviteNo = inviteNo;
    }

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }
}
