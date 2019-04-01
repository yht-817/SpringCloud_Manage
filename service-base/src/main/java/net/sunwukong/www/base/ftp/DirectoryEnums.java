package net.sunwukong.www.base.ftp;

/**
 * 说明:
 *
 * @author Mick
 * @CreateDate 2018/7/26 11:43
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public enum DirectoryEnums {
    CODE_100010001("html/article/","资讯文件存放地址"),
    CODE_100010002("img/article/","资讯图片存放地址"),
    CODE_100010003("img/userhead/","头像存放地址"),
    CODE_100010004("img/userback/","背景图存放地址"),
    CODE_100010005("img/useriden/","证件存放地址"),
    ;
    private String directory;
    private String msg;


    DirectoryEnums(String directory, String msg) {
        this.directory = directory;
        this.msg = msg;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
