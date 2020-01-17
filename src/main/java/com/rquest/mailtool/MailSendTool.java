package com.rquest.mailtool;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;


public class MailSendTool {

	/**
	 * 收件人邮箱，多个邮箱以“;”分隔
	 */
	private String toEmails;
	/**
	 * 邮件主题
	 */
	private String subject;
	/**
	 * 邮件内容
	 */
	private String content;
	/**
	 * 邮件中的图片，为空时无图片。map中的key为图片ID，value为图片地址
	 */
	private Map<String, String> pictures;
	/**
	 * 邮件中的附件，为空时无附件。map中的key为附件ID，value为附件地址
	 */
	private Map<String, String> attachments;

	/**
	 * 发件人邮箱服务器
	 */
	public static final String EMAILHOST = "smtp.sina.com";
	/**
	 * 发件人用户名
	 */
	public static final String EMAILUSERNAME = "@sina.com";
	/**
	 * 发件人邮箱
	 */
	public static final String EMAILFROM = "@sina.com";
	/**
	 * 发件人密码
	 */
	public static final String EMAILPASSWORD = "";
	/**
	 * 发送邮件
	 * @throws Exception
	 */
	public void sendEmail() throws Exception {

		if (StringUtils.isBlank(EMAILHOST)
				|| StringUtils.isBlank(EMAILFROM)
				|| StringUtils.isBlank(EMAILUSERNAME)
				|| StringUtils.isBlank(EMAILPASSWORD)) {
			throw new RuntimeException("发件人信息不完全，请确认发件人信息！");
		}

		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

		// 设定mail server
		senderImpl.setHost(EMAILHOST);

		// 建立邮件消息
		MimeMessage mailMessage = senderImpl.createMimeMessage();

		MimeMessageHelper messageHelper = null;
		messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
		// 设置发件人邮箱
		System.out.print("设置发件人邮箱:" + EMAILFROM);
		messageHelper.setFrom(EMAILFROM);

		// 设置收件人邮箱
		List<String> toEmailList = new ArrayList<String>();
					toEmailList.add(toEmails);
			String[] toEmailArray;
			if (null == toEmailList || toEmailList.size() <= 0) {
				throw new RuntimeException("收件人邮箱不得为空！");
			} else {
				toEmailArray = new String[toEmailList.size()];
				for (int i = 0; i < toEmailList.size(); i++) {
					toEmailArray[i] = toEmailList.get(i);
			}
		}
		messageHelper.setTo(toEmailArray);

		// 邮件主题
		messageHelper.setSubject(subject);

		// true 表示启动HTML格式的邮件
		messageHelper.setText(content, true);

		// 添加图片
		if (null != pictures) {
			for (Iterator<Map.Entry<String, String>> it = pictures.entrySet()
					.iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = it.next();
				String cid = entry.getKey();
				String filePath = entry.getValue();
				if (null == cid || null == filePath) {
					throw new RuntimeException("请确认每张图片的ID和图片地址是否齐全！");
				}

				File file = new File(filePath);
				if (!file.exists()) {
					throw new RuntimeException("图片" + filePath + "不存在！");
				}

				FileSystemResource img = new FileSystemResource(file);
				messageHelper.addInline(cid, img);
			}
		}

		// 添加附件
		if (null != attachments) {
			for (Iterator<Map.Entry<String, String>> it = attachments
					.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = it.next();
				String cid = entry.getKey();
				String filePath = entry.getValue();
				if (null == cid || null == filePath) {
					throw new RuntimeException("请确认每个附件的ID和地址是否齐全！");
				}

				File file = new File(filePath);
				if (!file.exists()) {
					throw new RuntimeException("附件" + filePath + "不存在！");
				}

				FileSystemResource fileResource = new FileSystemResource(file);
				messageHelper.addAttachment(cid, fileResource);
			}
		}

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.timeout", "25000");
		prop.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");

		prop.setProperty("mail.smtp.socketFactory.fallback", "false");

		prop.setProperty("mail.smtp.port", "465");

		prop.setProperty("mail.smtp.socketFactory.port", "465");
		// 添加验证
		EmailAutherticator auth = new EmailAutherticator(EMAILUSERNAME,
				EMAILPASSWORD);

//		Session session = Session.getDefaultInstance(prop, auth);
		Session session = Session.getInstance(prop, auth);
		
		senderImpl.setSession(session);

		// 发送邮件
		senderImpl.send(mailMessage);
	}

	/**
	 * @return the toEmails
	 */
	public String getToEmails() {
		return toEmails;
	}

	/**
	 * @param toEmails
	 *            the toEmails to set
	 */
	public void setToEmails(String toEmails) {
		this.toEmails = toEmails;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		if (StringUtils.isBlank(subject)) {
			subject = "无主题";
		}
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the pictures
	 */
	public Map<String, String> getPictures() {
		return pictures;
	}

	/**
	 * @param pictures
	 *            the pictures to set
	 */
	public void setPictures(Map<String, String> pictures) {
		this.pictures = pictures;
	}

	/**
	 * @return the attachments
	 */
	public Map<String, String> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments
	 *            the attachments to set
	 */
	public void setAttachments(Map<String, String> attachments) {
		this.attachments = attachments;
	}
	
/*	public void sendEmailDemo(){
		this.emailVerifycode = VerifyCodeUtils.generateVerifyCode(6);
		// 发邮件
		MailSendTool mu = new MailSendTool();
		String toEmails = userEmail;
		String subject = "点石-信用风险平台";
		StringBuilder builder = new StringBuilder();
		builder.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /></head><body><div style='padding:20px;'>亲爱的<font color='#C40000'>"
				+ userNametf.getValue().toString()
				+ "</font>,您好！<br />"
				+ "感谢您注册点石金融服务集团-信用风险平台，请确认您的邮箱帐号为<font color='#1E5494'>"
				+ userEmail
				+ "</font><br />"
				+"您的验证码为："+"<font  font-size='15px'><strong>"+emailVerifycode+"</strong></font>"
				+ " <br />"
				+ "<p style='text-align:right'>点石金融服务 敬启</p><br />"
				+ "此为自动发送邮件，请勿直接回复！如您有任何疑问，请点击联系我们 <a>http://www.rquest.com.cn/<a/></div></body></html>");// 联系我们的还需要加链接
		String content = builder.toString();
		mu.setToEmails(toEmails);
		mu.setSubject(subject);
		mu.setContent(content);
		try {
			mu.sendEmail();
		} catch (Exception e) {
			logger.error("邮件发送失败" + userEmail + userNametf.getValue().toString());
			e.printStackTrace();
		}
	}*/

}
