package net.sunwukong.www.api.util;

import com.vdurmont.emoji.EmojiParser;
import net.sunwukong.www.api.enums.SysCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 说明:工具类
 *
 * @author Mick
 * CreateDate 2018/6/19/019 17:20
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class ApiTool {

    public static void main(String[] args) throws Exception {
        String str = "Here is a boy: 👦🏿!";
        System.out.println("原始字符为：\n" + str);

        System.out.println("to aliases 之后：");
        System.out.println(EmojiParser.parseToAliases(str));
        System.out.println(EmojiParser.parseToAliases(str, EmojiParser.FitzpatrickAction.PARSE));
        System.out.println(EmojiParser.parseToAliases(str, EmojiParser.FitzpatrickAction.REMOVE));
        System.out.println(EmojiParser.parseToAliases(str, EmojiParser.FitzpatrickAction.IGNORE));
    }

    /**
     * 过滤表情
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (source == null) {
            return source;
        }
        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher emojiMatcher = emoji.matcher(source);
        if (emojiMatcher.find()) {
            source = emojiMatcher.replaceAll("*");
            return source;
        }
        return source;
    }

    /**
     * 比较版本大小
     *
     * 说明：支n位基础版本号+1位子版本号
     * 示例：1.0.2>1.0.1 , 1.0.1.1>1.0.1
     *
     * @param version1 版本1
     * @param version2 版本2
     * @return 0:相同 1:version1大于version2 -1:version1小于version2
     */
    public static int compareVersion(String version1, String version2) throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用.；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    /**
     * 根据当前页和页面长度获取开始值
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static int getStartPage(int pageNo, int pageSize) {
        int i = (pageNo - 1) * pageSize;
        return i < 0 ? 0 :i;
    }

    /**
     * 根据服务状态查询需求状态
     * @param serverState
     * @return
     */
    public static String getDemandState(String serverState) {
        if (serverState.equals(SysCode.CODE_10290001.getCode())){
            return SysCode.CODE_10080006.getCode();
        }else if (serverState.equals(SysCode.CODE_10290006.getCode())){
            return SysCode.CODE_10080006.getCode();
        }else if (serverState.equals(SysCode.CODE_10290002.getCode())){
            return SysCode.CODE_10080002.getCode();
        }else if (serverState.equals(SysCode.CODE_10290003.getCode())){
            return SysCode.CODE_10080003.getCode();
        }else if (serverState.equals(SysCode.CODE_10290004.getCode())){
            return SysCode.CODE_10080004.getCode();
        }
        return null;
    }

    /**
     * 根据第三方名称查询对应第三方代码
     * @param otherName 第三方名称
     * @return
     */
    public static String getOtherLoginCode(String otherName){
        if (otherName.equals(SysCode.CODE_10320001.getMsg())){
            return SysCode.CODE_10320001.getCode();
        }else if (otherName.equalsIgnoreCase(SysCode.CODE_10320002.getMsg())){
            return SysCode.CODE_10320002.getCode();
        }else if (otherName.equals(SysCode.CODE_10320003.getMsg())){
            return SysCode.CODE_10320003.getCode();
        }
        return null;
    }

    public static String StringToHtml(String color,String str){
        StringBuffer buffer = new StringBuffer("<font ");
        buffer.append("color=\"");
        buffer.append(color);
        buffer.append("\">");
        buffer.append(str);
        buffer.append("</font>");
        return buffer.toString();
    }
}
