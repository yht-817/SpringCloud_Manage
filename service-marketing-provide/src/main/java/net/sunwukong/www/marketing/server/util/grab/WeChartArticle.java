package net.sunwukong.www.marketing.server.util.grab;

import com.sdkinfo.www.core.util.ImageUtil;
import com.sdkinfo.www.core.util.StrUtil;
import com.sdkinfo.www.http.HttpUtil;
import com.sdkinfo.www.log.StaticLog;
import net.sunwukong.www.api.enums.DefualtEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.base.ftp.DirectoryEnums;
import net.sunwukong.www.base.ftp.FTPUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明:微信文章
 *
 * @author Mick
 * CreateDate 2018/7/19/019 22:48
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class WeChartArticle {

    /**
     * 获取微信文章
     *
     * @param url
     *            请求地址
     * @return 微信文章
     * @throws IOException
     */
    public static ArticleEntity getWechartArticle(String url) throws IOException {
        if (!url.contains("https://mp.weixin.qq.com")){
            return null;
        }
        String htmlStr = HttpUtil.get(url);
        Document htmlDoc = Jsoup.parse(htmlStr);
        Elements tittle = htmlDoc.select("#activity-name");
        Elements author = htmlDoc.select(".rich_media_meta.rich_media_meta_text");
        Elements time = htmlDoc.select(".rich_media_meta.rich_media_meta_text#publish_time");
        Elements content = htmlDoc.select(".rich_media_content#js_content");

        //获取所有的图片
        Elements imgs = content.select("img");
        List<InputStream> inputStreams = new ArrayList<>(16);
        for (int i = 0; i < imgs.size(); i++) {
            //2.将所有的图片data-src 改为 src
            imgs.get(i).attr("src",imgs.get(i).attr("data-src"));
            imgs.get(i).removeAttr("data-src");
            inputStreams.add(FTPUtil.getImageStreamByUrl(imgs.get(i).attr("src")));
        }

        StaticLog.error("-------上传图片");
        List<String> list = FTPUtil.uploadStreamFile(DirectoryEnums.CODE_100010002,inputStreams);

        StaticLog.error("-------赋值新的图片地址给img");
        for (int i = 0; i < imgs.size(); i++) {
            imgs.get(i).attr("src",list.get(i));
        }
        //由于背景图片无法获取,那么将背景图片key置为空
        ArticleEntity articleEntity = getArticleEntity(tittle,imgs, content.html());

        //将内容放入文件
        //FileUtil.writeUtf8String(articleEntity.getContent(),"d:\\spider\\"+ DateUtil.today()+"\\index.html");
        return articleEntity;
    }

    private static ArticleEntity getArticleEntity(Elements tittle,Elements imgs, String content) throws MalformedURLException {
        //content = HtmlUtil.htmlStr(content,tittle.text());
        //去掉微信默认背景图
        content = content.replace("image:","");
        ArticleEntity articleEntity = new ArticleEntity();

        if (imgs.size()>0){
            //获取第一张图片
            String src = imgs.get(0).attr("src");
            if (StrUtil.isNotEmpty(src)){
                //获取URL图片信息
                BufferedImage bufferedImage = ImageUtil.read(new URL(src.replace("https","http")));
                //BufferedImage bufferedImage = ImageUtil.toImage(HttpUtil.get(src).getBytes());
                //获取图片长度
                int width = bufferedImage.getWidth();
                //获取图片宽度
                int height = bufferedImage.getHeight();
                if (width==height||width<height){
                    //方图
                    articleEntity.setImgLayout(SysCode.CODE_10400002.getCode());
                }else if (width>height){
                    //长图
                    articleEntity.setImgLayout(SysCode.CODE_10400001.getCode());
                }
                articleEntity.setTitleImg(src);
                StaticLog.error("图片长度:{}    图片宽度:{}",width,height);
                StaticLog.error("文章标题:{}", articleEntity.getTitle());
                StaticLog.error("文章图片:{}", articleEntity.getTitleImg());
                StaticLog.error("文章布局:{}","1".equals(articleEntity.getImgLayout())?"长图":"方图");
                StaticLog.error("抓取成功");
            }else {
                StaticLog.error("抓取微信文章,未找到合适的图片!");
                //插入默认图片
                articleEntity.setTitleImg(DefualtEnum.CODE_10010002.getUrl());
                articleEntity.setImgLayout(SysCode.CODE_10400001.getCode());
            }
        }else {
            StaticLog.error("抓取微信文章,未找到合适的图片!");
            //插入默认图片
            articleEntity.setTitleImg(DefualtEnum.CODE_10010002.getUrl());
            articleEntity.setImgLayout(SysCode.CODE_10400001.getCode());
        }
        articleEntity.setContent(content);
        articleEntity.setTitle(tittle.text());
        return articleEntity;
    }

    /**
     * 获取本地文章内容
     * @param url
     * @return
     * @throws IOException
     */
    public static ArticleEntity getLocalhostArticle(String url) throws IOException {
        String articleHtml = HttpUtil.get(url);
        Document articlDoc = Jsoup.parse(articleHtml);
        Elements tittle = articlDoc.select("title");
        Elements content = articlDoc.select("body");
        //获取所有的图片
        Elements imgs = articlDoc.select("img");

        ArticleEntity articleEntity = getArticleEntity(tittle, imgs, content.html());
        return articleEntity;
    }

    public static void main(String[] args) throws MalformedURLException {
        BufferedImage read = ImageUtil.read(new URL("http://static.gokong.cn/img/back/20180828165031unas7jam"));
        System.out.println(read.getWidth());
        System.out.println(read.getWidth());

    }
}
