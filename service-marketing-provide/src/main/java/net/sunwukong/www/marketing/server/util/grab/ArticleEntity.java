package net.sunwukong.www.marketing.server.util.grab;

/**
 * 说明:封装微信文章对象
 *
 * @author Mick
 * CreateDate 2018/7/19/019 22:54
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class ArticleEntity {
    /**
     * 文章标题
     */
    private String title;
    /**
     * 发布者信息
     */
    private String author;
    /**
     * 发布时间
     */
    private String time;
    /**
     * 文章HTML内容
     */
    private String content;
    /**
     * 文章文本内容
     */
    private String contentText;
    /**
     * 标题图片
     */
    private String titleImg;

    /**
     * 图片布局
     */
    private String imgLayout;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getImgLayout() {
        return imgLayout;
    }

    public void setImgLayout(String imgLayout) {
        this.imgLayout = imgLayout;
    }

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", contentText='" + contentText + '\'' +
                ", titleImg='" + titleImg + '\'' +
                ", imgLayout='" + imgLayout + '\'' +
                '}';
    }
}
