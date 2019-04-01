package net.sunwukong.www.api.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.sunwukong.www.api.enums.StatusEnum;

/**
 * 类说明:数据响应实体
 *
 * @author ideacoding
 * @create 2018-01-18 11:33
 * @Email ：ideacoding@163.com
 **/
@ApiModel(value = "ResponseData",description = "数据响应实体")
public class ResponseData<T> {

    @ApiModelProperty(dataType = "String",name = "message",value = "响应信息")
    private String message;

    @ApiModelProperty(dataType = "int",name = "code",value = "响应状态")
    private int code;

    @ApiModelProperty(dataType = "T",name = "data",value = "响应数据")
    private T data;

    @ApiModelProperty(dataType = "int",name = "currentPage",value = "当前页")
    private int currentPage;

    @ApiModelProperty(dataType = "int",name = "totalPage",value = "总页数")
    private int totalPage;

    @ApiModelProperty(dataType = "int",name = "totalCount",value = "总条数")
    private int totalCount;

    public void setStatus(StatusEnum status){
        this.code = status.getCode();
        this.message = status.getMsg();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public ResponseData() {
        setStatus(StatusEnum.SUCCESS);
    }

    public ResponseData(String message, int code, T data, int currentPage, int totalPage, int totalCount) {
        this.message = message;
        this.code = code;
        this.data = data;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                ", currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                '}';
    }
}
