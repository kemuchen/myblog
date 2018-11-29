package cn.muchen.framework.util.email;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sun.mail.util.MailSSLSocketFactory;
import cn.muchen.framework.util.Environment;
import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName:：SendMailUtil @Description： 邮件发送工具类
 * @author ：柯雷
 * @date ：2018年11月19日 上午10:29:10
 *
 */
public class SendMailUtil {

	/** 日志打印对象 */
	private static Logger logger = LoggerFactory.getLogger(SendMailUtil.class);

	/** 邮件发送服务器 */
	private static final String MAIL_HOST = Util.nvl(Environment.getValue("mail.host"), "smtp.qq.com");
	/** 邮件传输协议 */
	private static final String MAIL_TRANSPORT_PROTOCOL = Util.nvl(Environment.getValue("mail.transport.protocol"), "smtp");
	/** 是否验证用户 */
	private static final String MAIL_SMTP_AUTH = Util.nvl(Environment.getValue("mail.smtp.auth"), "true");
	/** 发送服务器需要身份验证 */
	private static final String MAIL_DEBUG = Util.nvl(Environment.getValue("mail.debug"), "false");
	/** 验证用户名 */
	private static final String MAIL_USER = Util.nvl(Environment.getValue("mail.user"), "2584073978@qq.com");
	/** 密码 */
	private static final String MAIL_PASSWORD = Util.nvl(Environment.getValue("mail.password"), "oafhodfsowtbdibf");

	/**
	 * @throws Exception 
	 * @Title：sendMessage 
	 * @Description：发送邮件
	 * @param ：@param receiveMail
	 * @param ：@param title
	 * @param ：@param content 
	 * @return ：void 
	 * @throws
	 */
	public static void sendMessage(String receiveMail, String title, String content) throws Exception {
		sendMessage(receiveMail, title, content, null, null);
	}
	
	/**
	 * @Title：sendMessage 
	 * @Description：发送邮件
	 * @param ：@param receiveMail
	 * @param ：@param title
	 * @param ：@param content
	 * @param ：@param images
	 * @param ：@param files
	 * @param ：@throws Exception 
	 * @return ：void 
	 * @throws
	 */
	public static void sendMessage(String receiveMail, String title, String content, List<Map<String, Object>> images,
			List<Map<String, Object>> files) throws Exception {
		logger.info("【SendMailUtil.sendMessage】开始发送邮件====接收人：" + receiveMail + ";标题：" + title);

		Properties prop = new Properties();
		prop.setProperty("mail.debug", MAIL_DEBUG); 
		prop.setProperty("mail.host", MAIL_HOST);
		prop.setProperty("mail.transport.protocol", MAIL_TRANSPORT_PROTOCOL);
		prop.setProperty("mail.smtp.auth", MAIL_SMTP_AUTH);
		
		// 开启SSL加密，否则会失败
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.socketFactory", sf);
		
		// 使用JavaMail发送邮件的5个步骤
		// 1、创建session
		Session session = Session.getInstance(prop);
		// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(true);
		// 2、通过session得到transport对象
		Transport transport = null;
		try {
			transport = session.getTransport();
			// 3、连上邮件服务器，需要发件人提供邮箱的用户名和密码进行验证
			transport.connect(MAIL_HOST, MAIL_USER, MAIL_PASSWORD);
			Message message = null;
			// 4、创建邮件
			if (!Util.isEmpty(images) && !Util.isEmpty(files)) {
				// 发送既有图片又有附件的邮件
				message = createMixedMail(session, receiveMail, title, content, images, files);
			} else if (!Util.isEmpty(images)) {
				// 发送待图片的邮件
				message = createImageMail(session, receiveMail, title, content, images);
			} else if (!Util.isEmpty(files)) {
				// 发送待附件的文件
				message = createAttachMail(session, receiveMail, title, content, files);
			} else {
				// 发送简单文件
				message = createSimpleMail(session, receiveMail, title, content);
			}

			if (message != null) {
				// 5、发送邮件
				transport.sendMessage(message, message.getAllRecipients());
			}
		} catch (Exception e) {
			logger.error("【SendMailUtil.sendMessage】发送邮件失败：" + e);
			throw e;
		} finally {
			if (transport != null) {
				transport.close();
			}
		}
	}

	/**
	 * @Title：createSimpleMail 
	 * @Description：创建一封只包含文本的邮件
	 * @param ：@param session
	 * @param ：@param receiveMail
	 * @param ：@param title
	 * @param ：@param content
	 * @param ：@return
	 * @param ：@throws Exception 
	 * @return ：MimeMessage 
	 * @throws
	 */
	private static MimeMessage createSimpleMail(Session session, String receiveMail, String title, String content) throws Exception {
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress(MAIL_USER));
		// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail));
		// 邮件的标题
		message.setSubject(title);
		// 邮件的文本内容
		message.setContent(content, "text/html;charset=UTF-8");
		// 返回创建好的邮件对象
		return message;
	}

	/**
	 * @Title：createImageMail 
	 * @Description：生成一封邮件正文带图片的邮件
	 * @param ：@param session
	 * @param ：@param receiveMail
	 * @param ：@param title
	 * @param ：@param content
	 * @param ：@param images
	 * @param ：@return
	 * @param ：@throws Exception 
	 * @return ：MimeMessage 
	 * @throws
	 */
	private static MimeMessage createImageMail(Session session, String receiveMail, String title, String content,
			List<Map<String, Object>> images) throws Exception {
		// 创建邮件
		MimeMessage message = new MimeMessage(session);
		// 设置邮件的基本信息
		// 发件人
		message.setFrom(new InternetAddress(MAIL_USER));
		// 收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail));
		// 邮件标题
		message.setSubject(title);

		// 准备邮件数据
		// 准备邮件正文数据
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(content, "text/html;charset=UTF-8");
		// 准备图片数据
		// 描述关系:正文和图片
		MimeMultipart imageMultipart = new MimeMultipart();
		imageMultipart.addBodyPart(mimeBodyPart);
		imageMultipart.setSubType("related");
		// 图片
		for (Map<String, Object> imageMap : images) {
			MimeBodyPart image = new MimeBodyPart();
			image.setDataHandler(new DataHandler(new FileDataSource((String) imageMap.get("IMAGESRC"))));
			image.setContentID((String) imageMap.get("IMAGENAME"));
			imageMultipart.addBodyPart(image);
		}

		message.setContent(imageMultipart);
		message.saveChanges();
		// 将创建好的邮件写入到E盘以文件的形式进行保存
		message.writeTo(new FileOutputStream("E:\\ImageMail.eml"));
		// 返回创建好的邮件
		return message;
	}

	/**
	 * @Title：createAttachMail 
	 * @Description：创建一封带附件的邮件
	 * @param ：@param session
	 * @param ：@param receiveMail
	 * @param ：@param title
	 * @param ：@param content
	 * @param ：@param files
	 * @param ：@return
	 * @param ：@throws Exception 
	 * @return ：MimeMessage 
	 * @throws
	 */
	private static MimeMessage createAttachMail(Session session, String receiveMail, String title, String content,
			List<Map<String, Object>> files) throws Exception {
		MimeMessage message = new MimeMessage(session);

		// 设置邮件的基本信息
		// 发件人
		message.setFrom(new InternetAddress(MAIL_USER));
		// 收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail));
		// 邮件标题
		message.setSubject(title);

		// 创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
		MimeBodyPart text = new MimeBodyPart();
		text.setContent(content, "text/html;charset=UTF-8");

		// 描述关系：正文和附件
		MimeMultipart fileMultipart = new MimeMultipart();
		for (Map<String, Object> fileMap : files) {
			// 附件1
			MimeBodyPart attach = new MimeBodyPart();
			DataHandler dataHandler = new DataHandler(new FileDataSource((String) fileMap.get("FILESRC")));
			attach.setDataHandler(dataHandler);
			attach.setFileName(dataHandler.getName());

			fileMultipart.addBodyPart(attach);
		}
		fileMultipart.addBodyPart(text);
		fileMultipart.setSubType("mixed");

		message.setContent(fileMultipart);
		message.saveChanges();
		// 将创建的Email写入到E盘存储
		message.writeTo(new FileOutputStream("E:\\attachMail.eml"));
		// 返回生成的邮件
		return message;
	}

	/**
	 * @Title：createMixedMail 
	 * @Description：生成一封带附件和带图片的邮件
	 * @param ：@param session
	 * @param ：@param receiveMail
	 * @param ：@param title
	 * @param ：@param content
	 * @param ：@param images
	 * @param ：@param files
	 * @param ：@return
	 * @param ：@throws Exception 
	 * @return ：MimeMessage 
	 * @throws
	 */
	private static MimeMessage createMixedMail(Session session, String receiveMail, String title, String content,
			List<Map<String, Object>> images, List<Map<String, Object>> files) throws Exception {
		// 创建邮件
		MimeMessage message = new MimeMessage(session);

		// 设置邮件的基本信息
		message.setFrom(new InternetAddress(MAIL_USER));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail));
		message.setSubject(title);

		// 正文
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(content, "text/html;charset=UTF-8");

		// 描述关系:正文和图片
		MimeMultipart imageMultipart = new MimeMultipart();
		imageMultipart.addBodyPart(mimeBodyPart);
		imageMultipart.setSubType("related");
		// 图片
		for (Map<String, Object> imageMap : images) {
			MimeBodyPart image = new MimeBodyPart();
			image.setDataHandler(new DataHandler(new FileDataSource((String) imageMap.get("IMAGESRC"))));
			image.setContentID((String) imageMap.get("IMAGENAME"));
			imageMultipart.addBodyPart(image);
		}

		// 描述关系：正文和附件
		MimeMultipart fileMultipart = new MimeMultipart();
		for (Map<String, Object> fileMap : files) {
			// 附件1
			MimeBodyPart attach = new MimeBodyPart();
			DataHandler dataHandler = new DataHandler(new FileDataSource((String) fileMap.get("FILESRC")));
			attach.setDataHandler(dataHandler);
			attach.setFileName(dataHandler.getName());

			fileMultipart.addBodyPart(attach);
		}

		// 代表正文的bodypart
		MimeBodyPart bodyPart = new MimeBodyPart();
		bodyPart.setContent(imageMultipart);
		fileMultipart.addBodyPart(bodyPart);
		fileMultipart.setSubType("mixed");

		message.setContent(fileMultipart);
		message.saveChanges();

		message.writeTo(new FileOutputStream("E:\\MixedMail.eml"));
		// 返回创建好的的邮件
		return message;
	}
}
