/**
 * Copyright 2018 bejson.com
 */
package net.sunwukong.www.user.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Auto-generated: 2018-08-09 15:48:17
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@ApiModel(value = "Visit",description = "访问量数据")
public class Visit {

    @ApiModelProperty(dataType = "String",name = "total",value = "总访问量")
    private int total;
    @ApiModelProperty(dataType = "String",name = "today",value = "今日访问量")
    private int today;
    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return total;
    }

    public void setToday(int today) {
        this.today = today;
    }
    public int getToday() {
        return today;
    }

}