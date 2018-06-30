package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class SendEmailTest {

	@Autowired
	private MailSender mailSender;

	@Test
	public void test() {
		// 简单邮件对象
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		// 发送人:和配置一致
		mailMessage.setFrom("wyl513901@163.com");
		// 收件人
		mailMessage.setTo("842389033@qq.com");
		// 主题
		mailMessage.setSubject("密码找回通知");
		// 内容
		mailMessage.setText("您的密码是：" + "000000" + "请您注意保管好您的密码！");
		// 发送
		mailSender.send(mailMessage);
	}

}
