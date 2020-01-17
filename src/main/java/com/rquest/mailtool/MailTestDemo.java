package com.rquest.mailtool;

import org.apache.commons.lang3.StringUtils;


public class MailTestDemo {
	
	public static void main(String[] args) {
		sendEmail();
	}
	
	private static void sendEmail() {
		String userEmail = "用户email"；
		if (StringUtils.isBlank(userEmail)) {
			Notification.show("邮箱不能为空", Notification.Type.WARNING_MESSAGE);
			return;
		}
		if (!StringUtils.isBlank(userNametf.getValue())) {
			if (!ValidateUtil.isUsername(userNametf.getValue())) {
				Notification.show("用户名格式不正确", Notification.Type.WARNING_MESSAGE);
				return;
			}
		}
		if (ValidateUtil.isEmail(email.getValue().trim())) {
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
		} else {
			Notification.show("邮箱格式错误请重新填写", Notification.Type.WARNING_MESSAGE);
			return;
		}
	}
}
