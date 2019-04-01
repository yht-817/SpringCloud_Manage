package net.sunwukong.www.marketing.server.util.grab;

import org.junit.Test;

import java.io.IOException;

public class WeChartArticleTest {

    @Test
    public void getWechartArticle() throws IOException {
        WeChartArticle.getWechartArticle("https://mp.weixin.qq.com/s/jjRW_sHKa2SJf3mW2AfCZA");
        //WeChartArticle.getWechartArticle("https://mp.weixin.qq.com/s/XhFAuK2YYitS1dxinaTAMw");
        //WeChartArticle.getWechartArticle("https://mp.weixin.qq.com/s/UZFetHTypr9u0PzYQkEqsg");
        //WeChartArticle.getWechartArticle("https://mp.weixin.qq.com/s/UZFetHTypr9u0PzYQkEqsg");
        //WeChartArticle.getWechartArticle("https://mp.weixin.qq.com/s/TG_bOoIYHKJP4bv53L4FAQ");
    }

    @Test
    public void getLocalhostArticle() throws IOException {
        ArticleEntity localhostArticle = WeChartArticle.getLocalhostArticle("http://192.168.50.84:8080/static_img/article/file/2018081015465320616279");
        System.out.println(localhostArticle);
    }
}