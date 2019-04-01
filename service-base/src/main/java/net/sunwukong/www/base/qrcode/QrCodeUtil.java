package net.sunwukong.www.base.qrcode;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sdkinfo.www.core.util.CharsetUtil;
import com.sdkinfo.www.core.util.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;

/**
 * 基于Zxing的二维码工具类
 * 
 * @author looly
 * @since 4.0.2
 *
 */
public class QrCodeUtil {

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	private static final String CHARSET = "utf-8";
	private static final String FORMAT = "JPG";
	// 二维码尺寸
	private static final int QRCODE_SIZE = 300;
	// LOGO宽度
	private static final int LOGO_WIDTH = 60;
	// LOGO高度
	private static final int LOGO_HEIGHT = 60;

	public static String IMG_SUFFIX = ".jpg";

	/**
	 * 生成二维码到文件，二维码图片格式取决于文件的扩展名
	 * 
	 * @param content 文本内容
	 * @param width 宽度
	 * @param height 高度
	 * @param targetFile 目标文件，扩展名决定输出格式
	 * @return 目标文件
	 */
	public static File generate(String content, int width, int height, File targetFile) {
		final BufferedImage image = generate(content, width, height);
		ImageUtil.write(image, targetFile);
		return targetFile;
	}

	/**
	 * 生成二维码到输出流
	 * 
	 * @param content 文本内容
	 * @param width 宽度
	 * @param height 高度
	 * @param imageType 图片类型（图片扩展名），见{@link ImageUtil}
	 * @param out 目标流
	 */
	public static void generate(String content, int width, int height, String imageType, OutputStream out) {
		final BufferedImage image = generate(content, width, height);
		ImageUtil.write(image, imageType, out);
	}

	/**
	 * 生成二维码图片
	 * 
	 * @param content 文本内容
	 * @param width 宽度
	 * @param height 高度
	 * @return 二维码图片（黑白）
	 */
	public static BufferedImage generate(String content, int width, int height) {
		final BitMatrix bitMatrix = encode(content, width, height);
		return toImage(bitMatrix);
	}

	/**
	 * 将文本内容编码为二维码
	 * 
	 * @param content 文本内容
	 * @param width 宽度
	 * @param height 高度
	 * @return {@link BitMatrix}
	 */
	public static BitMatrix encode(String content, int width, int height) {
		return encode(content, BarcodeFormat.QR_CODE, width, height);
	}

	/**
	 * 将文本内容编码为条形码或二维码
	 * 
	 * @param content 文本内容
	 * @param format 格式枚举
	 * @param width 宽度
	 * @param height 高度
	 * @return {@link BitMatrix}
	 */
	public static BitMatrix encode(String content, BarcodeFormat format, int width, int height) {
		final MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		final HashMap<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, CharsetUtil.UTF_8);

		BitMatrix bitMatrix;
		try {
			bitMatrix = multiFormatWriter.encode(content, format, width, height, hints);
		} catch (WriterException e) {
			throw new QrCodeException(e);
		}

		return bitMatrix;
	}

	// ------------------------------------------------------------------------------------------------------------------- 解析二维码
	/**
	 * 解码二维码图片为文本
	 * 
	 * @param qrCodeInputstream 二维码输入流
	 * @return 解码文本
	 */
	public static String decode(InputStream qrCodeInputstream) {
		return decode(ImageUtil.read(qrCodeInputstream));
	}

	/**
	 * 解码二维码图片为文本
	 * 
	 * @param qrCodeFile 二维码文件
	 * @return 解码文本
	 */
	public static String decode(File qrCodeFile) {
		return decode(ImageUtil.read(qrCodeFile));
	}

	/**
	 * 将二维码图片解码为文本
	 * 
	 * @param image {@link Image} 二维码图片
	 * @return 解码后的文本
	 */
	public static String decode(Image image) {
		final MultiFormatReader formatReader = new MultiFormatReader();

		final LuminanceSource source = new BufferedImageLuminanceSource(ImageUtil.toBufferedImage(image));
		final Binarizer binarizer = new HybridBinarizer(source);
		final BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

		final HashMap<DecodeHintType, Object> hints = new HashMap<>();
		hints.put(DecodeHintType.CHARACTER_SET, CharsetUtil.UTF_8);
		Result result;
		try {
			result = formatReader.decode(binaryBitmap, hints);
		} catch (NotFoundException e) {
			throw new QrCodeException(e);
		}

		return result.getText();
	}

	/**
	 * BitMatrix转BufferedImage
	 * 
	 * @param matrix BitMatrix
	 * @return BufferedImage
	 */
	public static BufferedImage toImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	private static BufferedImage createImage(String content, String logoPath, boolean needCompress) throws Exception {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
				hints);
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}
		if (logoPath == null || "".equals(logoPath)) {
			return image;
		}
		// 插入图片
		insertImage(image, logoPath, needCompress);
		return image;
	}

	/**
	 * 插入LOGO
	 *
	 * @param source
	 *            二维码图片
	 * @param logoPath
	 *            LOGO图片地址
	 * @param needCompress
	 *            是否压缩
	 * @throws Exception
	 */
	private static void insertImage(BufferedImage source, String logoPath, boolean needCompress) throws Exception {
		File file = new File(logoPath);
		if (!file.exists()) {
			throw new Exception("logo file not found.");
		}
		Image src = ImageIO.read(new File(logoPath));
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (needCompress) { // 压缩LOGO
			if (width > LOGO_WIDTH) {
				width = LOGO_WIDTH;
			}
			if (height > LOGO_HEIGHT) {
				height = LOGO_HEIGHT;
			}
			Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (QRCODE_SIZE - width) / 2;
		int y = (QRCODE_SIZE - height) / 2;
		graph.drawImage(src, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 * 二维码文件名随机，文件名可能会有重复
	 *
	 * @param content
	 *            内容
	 * @param logoPath
	 *            LOGO地址
	 * @param destPath
	 *            存放目录
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static String encode(String content, String logoPath, String destPath, boolean needCompress) throws Exception {
		BufferedImage image = createImage(content, logoPath, needCompress);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		boolean flag = ImageIO.write(image, FORMAT, out);
		byte[] b = out.toByteArray();
		mkdirs(destPath);
		String fileName = new Random().nextInt(99999999) + "." + FORMAT.toLowerCase();
		ImageIO.write(image, FORMAT, new File(destPath + "/" + fileName));
		return fileName;
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 *
	 * @param content
	 *  内容
	 * @param logoPath
	 *  logo地址
	 * @param needCompress
	 *  是否压缩
	 * @return
	 * @throws Exception
	 */
	public static byte[] encodeByte(String content,String logoPath,boolean needCompress) throws Exception {
		BufferedImage image = createImage(content,logoPath,needCompress);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, FORMAT, out);
		return out.toByteArray();
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 * 调用者指定二维码文件名
	 *
	 * @param content
	 *            内容
	 * @param logoPath
	 *            LOGO地址
	 * @param destPath
	 *            存放目录
	 * @param fileName
	 *            二维码文件名
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static String encode(String content, String logoPath, String destPath, String fileName, boolean needCompress) throws Exception {
		BufferedImage image = createImage(content, logoPath, needCompress);
		mkdirs(destPath);
		fileName = fileName.substring(0, fileName.indexOf(".")>0?fileName.indexOf("."):fileName.length())
				+ "." + FORMAT.toLowerCase();
		ImageIO.write(image, FORMAT, new File(destPath + "/" + fileName));
		return fileName;
	}

	/**
	 * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．
	 * (mkdir如果父目录不存在则会抛出异常)
	 * @param destPath
	 *            存放目录
	 */
	public static void mkdirs(String destPath) {
		File file = new File(destPath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 *
	 * @param content
	 *            内容
	 * @param logoPath
	 *            LOGO地址
	 * @param destPath
	 *            存储地址
	 * @throws Exception
	 */
	public static String encode(String content, String logoPath, String destPath) throws Exception {
		return encode(content, logoPath, destPath, false);
	}

	/**
	 * 生成二维码
	 *
	 * @param content
	 *            内容
	 * @param destPath
	 *            存储地址
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static String encode(String content, String destPath, boolean needCompress) throws Exception {
		return encode(content, null, destPath, needCompress);
	}

	/**
	 * 生成二维码
	 *
	 * @param content
	 *            内容
	 * @param destPath
	 *            存储地址
	 * @throws Exception
	 */
	public static String encode(String content, String destPath) throws Exception {
		return encode(content, null, destPath, false);
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 *
	 * @param content
	 *            内容
	 * @param logoPath
	 *            LOGO地址
	 * @param output
	 *            输出流
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static void encode(String content, String logoPath, OutputStream output, boolean needCompress)
			throws Exception {
		BufferedImage image = createImage(content, logoPath, needCompress);
		ImageIO.write(image, FORMAT, output);
	}

	/**
	 * 生成二维码
	 *
	 * @param content
	 *            内容
	 * @param output
	 *            输出流
	 * @throws Exception
	 */
	public static void encode(String content, OutputStream output) throws Exception {
		encode(content, null, output, false);
	}

	/**
	 * 解析二维码
	 *
	 * @param path
	 *            二维码图片地址
	 * @return
	 * @throws Exception
	 */
	public static String decode(String path) throws Exception {
		return decode(new File(path));
	}


}
