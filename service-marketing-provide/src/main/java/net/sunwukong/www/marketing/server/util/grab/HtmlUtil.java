package net.sunwukong.www.marketing.server.util.grab;

/**
 * 说明:HTML工具类
 *
 * @author Mick
 * CreateDate 2018/7/19/019 22:51
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class HtmlUtil {

    /**
     * 拼接成标准的HTML格式文件
     * @param content
     * @return
     */
    public static String htmlStr(String content,String title){
        if (content.contains("<html>")){
            return content;
        }
        StringBuffer text = new StringBuffer();
        text.append("\n<html>\n");
        text.append("\n<head>\n");
        text.append("<title>"+ title +"</title>");
        text.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n");
        text.append("<meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no\">\n");
        text.append("<meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n");
        text.append("<meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">\n");
        text.append("<meta name=\"format-detection\" content=\"telephone=no\">\n");

        text.append("<style>\n");
        text.append("html {overflow-y:scroll;}\n");
        text.append("body {overflow-x:hidden;}\n");
        text.append("body * {\n" +
                "width: auto!important;\n" +
                /*"height: auto!important;\n" +
                "max-height: 100%!important;\n" +*/
                "max-width:100%!important;\n" +
                "}\n");
        text.append("</style>\n");
        text.append("</head>\n");

        text.append("<body>\n");
        text.append(content);
        text.append("</body>");
        text.append("</html>");
        return text.toString();
    }
}
