package net.sunwukong.www.marketing.server.util.imgtools;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sdkinfo.www.core.util.ImageUtil;
import sun.misc.BASE64Encoder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;

/**
 * 对图片添加二维码
 */
public class CodeImgTools {
    /**
     * 二维码图片的生成
     *
     * @param content       链接
     * @param qrcode_width  二维码宽
     * @param qrcode_height 二维码高
     * @return
     * @throws Exception
     */
    public static BufferedImage createImage(String content, int qrcode_width, int qrcode_height) throws WriterException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrcode_width, qrcode_height, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

    // 把图片和二维码合并
    public static String backBase64(String contUrl, String imgUrl) throws Exception {
        // 替换字符
        String backimgUrl = imgUrl.replace("https", "http");
        String filec = "/data/code/";
        //String filec = "C:\\Users\\swk\\Desktop\\";
        File fc = new File(filec);
        if (!fc.exists()) {
            fc.mkdir();
        }
        String path = filec + System.currentTimeMillis() + ".png";
        int qrcode_width = 160;
        int qrcode_height = 160;
        int width = -ImageUtil.read(new URL(backimgUrl)).getWidth() / 2 + qrcode_width / 2;
        int height = ImageUtil.read(new URL(backimgUrl)).getHeight() / 2 - qrcode_height / 2;
        BufferedImage image = createImage(contUrl, qrcode_width, qrcode_height);
        ImageUtil.pressImage(ImageUtil.read(new URL(backimgUrl)), new File(path), image, width, height, 1.0f);
        // 把本地图片转换成Base64
        String str = ImageToBase64ByLocal(path);
        // 删除图片
        File file = new File(path);
        file.delete();
        return str;
    }

    // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
    public static String ImageToBase64ByLocal(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }

    public static void main(String[] args) throws Exception {
        String url = "www.baidu.com";
        String bu = "https://static.gokong.cn/img/back/20180903115605aakp0srl";
        String sre = backBase64(url, bu);
        System.out.println(sre);
    }


}
