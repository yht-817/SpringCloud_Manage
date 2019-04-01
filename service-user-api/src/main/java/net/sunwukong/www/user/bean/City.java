package net.sunwukong.www.user.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "City",description = "城市实体")
public class City {
    @ApiModelProperty(dataType = "String",name = "id",value = "城市ID")
    private String id;
    @ApiModelProperty(dataType = "String",name = "cityNo",value = "城市编码")
    private String cityNo;
    @ApiModelProperty(dataType = "String",name = "cityName",value = "城市名称")
    private String cityName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo == null ? null : cityNo.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }
}