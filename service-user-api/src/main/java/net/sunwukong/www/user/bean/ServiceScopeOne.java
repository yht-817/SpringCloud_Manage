package net.sunwukong.www.user.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 1、服务机构入驻申请
 *    城市服务范围：根据一级服务范围关联二级服务范围生成当前一对多的实体类
 */
@ApiModel(value = "ServiceScopeOne",description = "城市服务范围一级目录")
public class ServiceScopeOne {
    @ApiModelProperty(dataType = "String",name = "id",value = "插入数据时的ID")
    private String id;
    @ApiModelProperty(dataType = "String",name = "scopeOneNo",value = "一级服务范围编码")
    private String scopeOneNo;
    @ApiModelProperty(dataType = "String",name = "scopeOneName",value = "一级服务范围的城市名")
    private String scopeOneName;
    @ApiModelProperty(dataType = "list",name = "listChilds",value = "一级服务城市名下面的所有二级服务")
    private List<ServiceScopeTwo> listChilds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getScopeOneNo() {
        return scopeOneNo;
    }

    public void setScopeOneNo(String scopeOneNo) {
        this.scopeOneNo = scopeOneNo == null ? null : scopeOneNo.trim();
    }

    public String getScopeOneName() {
        return scopeOneName;
    }

    public void setScopeOneName(String scopeOneName) {
        this.scopeOneName = scopeOneName == null ? null : scopeOneName.trim();
    }

    public List<ServiceScopeTwo> getListChilds() {
        return listChilds;
    }

    public void setListChilds(List<ServiceScopeTwo> listChilds) {
        this.listChilds = listChilds;
    }
}