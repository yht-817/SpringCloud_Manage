package net.sunwukong.www.marketing.server.domain;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: DemandParamPo
 * @Author: kangdong
 * @Date: 2018/6/20 下午1:30
 * @Description: 需求请求参数po类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
public class DemandParamPo {

    private String userNo;
    //状态
    private String state;
    //跳过的条数
    private Integer start;
    //跳过的条数，返回后面的条数
    private Integer pageSize;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
