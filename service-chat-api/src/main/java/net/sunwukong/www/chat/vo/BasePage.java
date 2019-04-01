package net.sunwukong.www.chat.vo;

/**
 * 说明:公用分页实体
 *
 * @author Mick
 * @CreateDate 2018/7/12 14:36
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public class BasePage {


    private int pageNo;

    private int pageSize;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
