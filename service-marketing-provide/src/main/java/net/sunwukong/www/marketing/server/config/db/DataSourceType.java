package net.sunwukong.www.marketing.server.config.db;

/**
 * 说明:数据库配置
 *
 * @author Mick
 * CreateDate 2018/6/10/010 9:18
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public enum  DataSourceType {
    read("read", "从库"),
    write("write", "主库");

    private String type;
    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
