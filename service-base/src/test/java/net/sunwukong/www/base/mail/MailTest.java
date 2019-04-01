/*
package net.sunwukong.www.base.mail;


import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.Properties;


*/
/**
 * 邮件发送测试
 * @author looly
 *
 *//*

@Ignore
public class MailTest {
	
	@Test
	public void sendTest() {
		MailUtil.send("185476856@qq.com","孙悟空华人网-获取验证码","尊敬的用户：<br/>您的验证码：5683,5分钟内有效，请勿泄漏。", true);
	}
	
	@Test
	public void sendHtmlTest() {
		MailUtil.send("hutool@foxmail.com", "测试", "<h1>邮件来自Hutool测试</h1>", true);
	}
	
	@Test
	public void sendByAccountTest() {
		MailAccount account = new MailAccount();
//		account.setHost("smtp.yeah.net");
//		account.setPort(25);
		account.setFrom("hutool@yeah.net");
//		account.setUser("hutool");
		account.setPass("q1w2e3");
//		MailUtil.send(account, "914104645@qq.com, loolly@aliyun.com", "测试", "邮件来自Hutool测试", true);
	}
	
	@Test
	public void mailAccountTest() {
		MailAccount account = new MailAccount();
		account.setFrom("hutool@yeah.net");
		account.setDebug(true);
		account.defaultIfEmpty();
		Properties props = account.getSmtpProps();
		Assert.assertEquals("true", props.getProperty("mail.debug"));
	}
}
*/
