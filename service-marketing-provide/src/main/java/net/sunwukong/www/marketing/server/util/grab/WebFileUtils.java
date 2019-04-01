package net.sunwukong.www.marketing.server.util.grab;

import com.sdkinfo.www.log.StaticLog;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 说明:
 *
 * @author Mick
 * CreateDate 2018/7/23/023 22:41
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class WebFileUtils {

    public static final String hostpath = "http://localhost:8080/static_img/";
    public static final String saveDir = "d://sunwukongfile/static_img/";

    public static MultipartFile createImg(String url, String suffix){
        /*try {
            // File转换成MutipartFile
            File file = WebFileUtils.createFileByUrl(url, suffix);
            FileInputStream inputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
            //注意这里面填啥，MultipartFile里面对应的参数就有啥，比如我只填了name，则
            //MultipartFile.getName()只能拿到name参数，但是originalFilename是空。
            return multipartFile;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }*/
        return null;
    }

    /**
     * 根据url拿取file
     *
     * @param url
     * @param suffix
     *            文件后缀名
     * */
    public static File createFileByUrl(String url, String suffix) {
        byte[] byteFile = getImageFromNetByUrl(url);
        if (byteFile != null) {
            File file = getFileFromBytes(byteFile, suffix);
            return file;
        } else {
            StaticLog.info("生成文件失败！");
            return null;
        }
    }

    /**
     * 根据地址获得数据的字节流
     *
     * @param strUrl
     *            网络连接地址
     * @return
     */
    public static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(60 * 1000);
            InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从输入流中获取数据
     *
     * @param inStream
     *            输入流
     * @return
     * @throws Exception
     */
    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    // 创建临时文件
    private static File getFileFromBytes(byte[] b, String suffix) {
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = File.createTempFile("pattern", "." + suffix);
            System.out.println("临时文件位置："+file.getCanonicalPath());
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    /*public static void main(String[] args) throws IOException {
        File file = WebFileUtils.createFileByUrl("https://mmbiz.qpic.cn/mmbiz_png/Np6H9BrgwVp2y1Odf6EiaPkHCy0WNxOoY85ek0qaJY5I8NOSYI6Diby27tvOedDbuzhSVqicOZNxQhibWfwXckNNbA/640?wx_fmt=png", "png");
        System.out.println(file.getName());
        FileInputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
        //注意这里面填啥，MultipartFile里面对应的参数就有啥，比如我只填了name，则
        //MultipartFile.getName()只能拿到name参数，但是originalFilename是空。
        String filePath = FtpClientTools.uploadFile(multipartFile, "4");
        System.out.println(filePath);
    }*/

    /*public static void main(String[] args) {

        *//*String pathname="d://test.gif";//
        File file=new File(pathname);
        //此处为指定指定图片的url,以百度logo为例
        String urlstr="https://mmbiz.qpic.cn/mmbiz_jpg/Np6H9BrgwVp2y1Odf6EiaPkHCy0WNxOoY2r36GSIpjo4qKBhUWDSiaK7Cc8WUSPxDjn4sxbor3ZXaPrZic9U12aAw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1";
        boolean success=false;
        success=downloadCompressedPicture(file, urlstr);
        System.out.println(success?"success":"false");*//*

    }*/
    /**
     * url下载压缩图
     * jdk1.8可以执行
     * jdk1.4无法执行
     * @param imgName
     * @param urlstr
     * @return
     */
    public static String downloadCompressedPicture(String imgName,String urlstr){
        System.out.println("imgName:"+imgName+"urlstr:"+urlstr);
        File file = new File(saveDir+imgName);
        URL url=null;
        try{
            url=new URL(urlstr);
            //1.获取url的输入流 dataInputStream
            DataInputStream dataInputStream=new DataInputStream(url.openStream());
            //2.加一层BufferedInputStream
            BufferedInputStream bufferedInputStream=new BufferedInputStream(dataInputStream);
            //3.构造原始图片流 preImage
            BufferedImage preImage= ImageIO.read(bufferedInputStream);
            //4.获得原始图片的长宽 width/height
            int width=preImage.getWidth();
            int height=preImage.getHeight();
            //5.构造压缩后的图片流 image 长宽各为原来的1/2
            BufferedImage image=new BufferedImage(width/2, height/2, BufferedImage.TYPE_INT_RGB);
            //6.给image创建Graphic ,在Graphic上绘制压缩后的图片
            Graphics graphic=image.createGraphics();
            graphic.drawImage(preImage, 0, 0, width/2, height/2, null);
            //7.为file生成对应的文件输出流
            //将image传给输出流
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(fileOutputStream);
            //8.将image写入到file中
            ImageIO.write(image, "bmp", bufferedOutputStream);
            //9.关闭输入输出流
            bufferedInputStream.close();
            bufferedOutputStream.close();

            return hostpath+imgName;
        }catch(IOException e){
            System.out.println(e);
        }
        return "";
    }
}
