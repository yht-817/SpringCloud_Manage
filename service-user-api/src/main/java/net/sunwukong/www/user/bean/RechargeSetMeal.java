package net.sunwukong.www.user.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value = "RechargeSetMeal",description = "充值套餐实体")
public class RechargeSetMeal {

    @ApiModelProperty(dataType = "String",name = "id",value = "充值套餐ID")
    private String id;

    @ApiModelProperty(dataType = "String",name = "setMealNo",value = "套餐编码")
    private String setMealNo;

    @ApiModelProperty(dataType = "BigDecimal",name = "payNum",value = "购买数量")
    private BigDecimal payNum;

    @ApiModelProperty(dataType = "BigDecimal",name = "sendNum",value = "赠送数量")
    private BigDecimal sendNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSetMealNo() {
        return setMealNo;
    }

    public void setSetMealNo(String setMealNo) {
        this.setMealNo = setMealNo == null ? null : setMealNo.trim();
    }

    public BigDecimal getPayNum() {
        return payNum;
    }

    public void setPayNum(BigDecimal payNum) {
        this.payNum = payNum;
    }

    public BigDecimal getSendNum() {
        return sendNum;
    }

    public void setSendNum(BigDecimal sendNum) {
        this.sendNum = sendNum;
    }
}