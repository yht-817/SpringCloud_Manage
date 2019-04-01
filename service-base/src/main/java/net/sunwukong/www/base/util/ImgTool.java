package net.sunwukong.www.base.util;

import com.sdkinfo.www.core.date.DateUtil;
import com.sdkinfo.www.core.util.StrUtil;
import com.sdkinfo.www.core.util.ToolUtil;

/**
 * 说明:图片工具
 *
 * @author Mick
 * CreateDate 2018/6/15/015 0:06
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class ImgTool {
    public static final String IMG_SUFFIX_JPG = ".jpg";

    /**
     * 创建图片名称
     * @return
     */
    public static String createImgName(){
        return DateUtil.getMillis()+ToolUtil.buildRandom(4)+IMG_SUFFIX_JPG;
    }

    /**
     * 创建图片名称
     * @return
     */
    public static String createImgName(String suffix){
        return DateUtil.getMillis()+ToolUtil.buildRandom(4)+"."+ StrUtil.trim(suffix);
    }
}
