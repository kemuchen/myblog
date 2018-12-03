package cn.muchen.framework.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * @ClassName:：Util
 * @Description：工具类
 * @author ：柯雷
 * @date ：2018年9月5日 下午4:44:47
 *
 */
public class Util {

	/** 数据库字符集 */
	private static final String DB_CHARSET = "GBK";

	/** 日志打印对象 */
	static Logger logger = LoggerFactory.getLogger(Util.class);

	// 验证码字符集
	private static final char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z' };
	// 字符数量
	private static final int SIZE = 4;
	// 干扰线数量
	private static final int LINES = 5;
	// 宽度
	private static final int WIDTH = 80;
	// 高度
	private static final int HEIGHT = 40;
	// 字体大小
	private static final int FONT_SIZE = 30;

	/**
	 * 生成随机验证码及图片 Object[0]：验证码字符串； Object[1]：验证码图片。
	 */
	public static Object[] createImage() {
		StringBuffer sb = new StringBuffer();
		// 1.创建空白图片
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		// 2.获取图片画笔
		Graphics graphic = image.getGraphics();
		// 3.设置画笔颜色
		graphic.setColor(Color.LIGHT_GRAY);
		// 4.绘制矩形背景
		graphic.fillRect(0, 0, WIDTH, HEIGHT);
		// 5.画随机字符
		Random ran = new Random();
		for (int i = 0; i < SIZE; i++) {
			// 取随机字符索引
			int n = ran.nextInt(chars.length);
			// 设置随机颜色
			graphic.setColor(getRandomColor());
			// 设置字体大小
			graphic.setFont(new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE));
			// 画字符
			graphic.drawString(chars[n] + "", i * WIDTH / SIZE, HEIGHT * 2 / 3);
			// 记录字符
			sb.append(chars[n]);
		}
		// 6.画干扰线
		for (int i = 0; i < LINES; i++) {
			// 设置随机颜色
			graphic.setColor(getRandomColor());
			// 随机画线
			graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT), ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
		}
		// 7.返回验证码和图片
		return new Object[] { sb.toString(), image };
	}

	/**
	 * 随机取色
	 */
	private static Color getRandomColor() {
		Random ran = new Random();
		Color color = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
		return color;
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object) {
		// 对象为空，返回false
		if (object == null) {
			return true;
		}

		// 如果对象是字符串，则判断对象是否为空串
		if (object instanceof String && "".equals(object)) {
			return true;
		}
		
		// 对象是map，则判断map是否为空
		if (object instanceof Map && ((Map<?, ?>)object).isEmpty()) {
			return true;
		}
		
		// 对象是list，则判断list.size是否为0
		if (object instanceof List && ((List<?>)object).size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title：getGapCount 
	 * @Description：获取日之间相差天数
	 * @param ：@param startDate
	 * @param ：@param endDate
	 * @param ：@return 
	 * @return ：int 
	 * @throws
	 */
	public static int getGapCount(Date startDate, Date endDate) {
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(startDate);
		fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		fromCalendar.set(Calendar.MINUTE, 0);
		fromCalendar.set(Calendar.SECOND, 0);
		fromCalendar.set(Calendar.MILLISECOND, 0);
		
		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(endDate);
		toCalendar.set(Calendar.HOUR_OF_DAY, 0);
		toCalendar.set(Calendar.MINUTE, 0);
		toCalendar.set(Calendar.SECOND, 0);
		toCalendar.set(Calendar.MILLISECOND, 0);
		
		return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
	}
	
	/**
	 * @Title：formatDate 
	 * @Description：格式化日期
	 * @param ：@param date
	 * @param ：@param format
	 * @param ：@return 
	 * @return ：Date 
	 * @throws
	 */
	public static String formatDate(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	
	/**
	 * @Title：parseDate 
	 * @Description： string转date类型
	 * @param ：@param date
	 * @param ：@param format
	 * @param ：@return
	 * @param ：@throws ParseException 
	 * @return ：Date 
	 * @throws
	 */
	public static Date parseDate(String date, String format) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.parse(date);
	}
	
	/**
	 * @Title：getDqrq @Description：获取当前日期 @param ：@param format @param
	 *                ：@return @return ：String @throws
	 */
	public static String getDqrq(String format) {
		return formatDate(new Date(), format);
	}

	/**
	 * 当前日期增加天数
	 * 
	 * @param day        增加天数
	 * @param isWeekdays 是否为工作日
	 * @return
	 */
	public static String addDate(int day, boolean isWeekdays) {
		return addDate(new Date(), day, isWeekdays);
	}

	/**
	 * 日期增加天数
	 * 
	 * @param date       日期
	 * @param day        增加天数
	 * @param isWeekdays 是否为工作日
	 * @return
	 */
	public static String addDate(Date date, int day, boolean isWeekdays) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
		long time = date.getTime(); // 得到指定日期的毫秒数

		int addDay = 0;
		for (int i = 0; i < day; i++) {
			addDay++;
			if (isWeekdays && isWeekend(new Date(time + addDay * 24 * 60 * 60 * 1000))) {
				i--;
			}
		}
		addDay = addDay * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
		time += addDay; // 相加得到新的毫秒数
		return dateFormat.format(new Date(time)); // 将毫秒数转换成日期
	}

	/**
	 * 判断日期是否为周末
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 增加月份
	 * 
	 * @param month
	 * @return
	 */
	public static String addMonth(int month) {
		return addMonth(new Date(), month);
	}

	/**
	 * 增加月份
	 * 
	 * @param date
	 * @param Month
	 * @return
	 */
	public static String addMonth(Date date, int Month) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM"); // 日期格式
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, Month);// 把日期往后增加一年.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		return dateFormat.format(date);
	}

	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 * 
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100
	 * 
	 * 用户真实IP为： 192.168.1.110
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取字符串的数据库长度
	 * 
	 * @param str
	 * @return
	 */
	public static int getDbLength(String str) {
		if (str == null) {
			return 0;
		}
		try {
			return str.getBytes(DB_CHARSET).length;
		} catch (UnsupportedEncodingException e) {
			return str.getBytes().length;
		}
	}

	/**
	 * 本地图片转换成base64字符串
	 * 
	 * @param imgFile 图片本地路径
	 * @return
	 */
	public static String imageToBase64(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理

		InputStream in = null;
		byte[] data = null;

		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);

			data = new byte[in.available()];
			in.read(data);
		} catch (IOException e) {
			logger.error("【图片Base64转换】错误：" + e.getMessage());
			return "";
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("【图片Base64转换】错误：" + e.getMessage());
				}
			}
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	/**
	 * base64字符串转换成图片
	 * 
	 * @param imgStr      base64字符串
	 * @param imgFilePath 图片存放路径
	 * @return
	 */
	public static boolean base64ToImage(String imgStr, String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片

		if (Util.isEmpty(imgStr)) { // 图像数据为空
			return false;
		}

		OutputStream out = null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}

			out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();

			return true;
		} catch (Exception e) {
			logger.error("【Base64图片转换】错误：" + e.getMessage());
			return false;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error("【Base64图片转换】错误：" + e.getMessage());
				}
			}
		}
	}
	
	/**
	 * @Title：nvl 
	 * @Description：相当于oracle的nvl函数
	 * @param ：@param string
	 * @param ：@param defaultString
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	public static String nvl(String string, String defaultString) {
		if (isEmpty(string)) {
			return defaultString;
		} else {
			return string;
		}
	}
	
	/**
	 * 删除文件夹里的数据（默认里面没有文件夹）
	 * @param dir
	 */
	public static void deleteDir(File dir) {
		File[] files = dir.listFiles();
		int len = files.length;
		for(int i=0;i<len;i++) {
			files[i].delete();
		}
	}

	/**
	 * 获取字符串的编码格式
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				return encode;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				return encode;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				return encode;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				return encode;
			}
		} catch (Exception exception3) {
		}
		return "";
	}
}
