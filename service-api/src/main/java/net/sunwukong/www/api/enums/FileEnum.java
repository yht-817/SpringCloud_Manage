package net.sunwukong.www.api.enums;

/**
 * 说明:文件属性
 *
 * @author Mick
 * @CreateDate 2018/6/23 12:48
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public enum FileEnum {

    CERTIFICATE_DIR("certificate/","证件目录"),
    HEAD_DIR("head/","头像目录"),
    BACK_IMG_DIR("backimg/","背景图目录"),
    DEFAULT_DIR("default/","默认图库");

    private String dir;
    private String desc;

    FileEnum(String dir, String desc) {
        this.dir = dir;
        this.desc = desc;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
