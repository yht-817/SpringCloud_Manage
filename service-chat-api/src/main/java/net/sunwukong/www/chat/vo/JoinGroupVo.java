package net.sunwukong.www.chat.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * 说明:用户加入群对象
 *
 * @author Mick
 * @CreateDate 2018/7/12 10:54
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "JoinGroupVo",description = "用户加入群对象")
public class JoinGroupVo {

    @ApiModelProperty(dataType = "String",name = "userNo",value = "邀请方的用户编码")
    private String userNo;
    @ApiModelProperty(dataType = "String",name = "groupNo",value = "群聊编码")
    private String groupNo;
    @ApiModelProperty(dataType = "List<String>",name = "userNos",value = "用户编码列表")
    private List<Map<String,String>> userNos;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public List<Map<String, String>> getUserNos() {
        return userNos;
    }

    public void setUserNos(List<Map<String, String>> userNos) {
        this.userNos = userNos;
    }
}
