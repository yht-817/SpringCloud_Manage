package net.sunwukong.www.chat.vo;

import java.util.List;
import java.util.Map;

/**
 * 说明:退出群聊对象
 *
 * @author Mick
 * @CreateDate 2018/7/12 19:29
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public class DelGroupUserVo {
    private String userNo;
    private String groupNo;
    private List<Map<String,String>> userNos;

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

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
}
