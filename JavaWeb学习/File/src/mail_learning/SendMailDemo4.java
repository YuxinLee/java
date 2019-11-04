package mail_learning;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.MimeMessage.RecipientType;

public class SendMailDemo4 {

    // 邮件发送参数信息
    static Properties prop = new Properties();

    static String user = "itcast";
    static String pwd = "888";

    // 初始化参数
    static {
        // 协议
        prop.put("mail.transport.protocol", "smtp");
        // 端口
        prop.put("mail.smtp.port", "25");
        // 主机
        prop.put("mail.smtp.host", "localhost");
        // 认证
        prop.put("mail.smtp.auth", "true");
        // 调试
        prop.put("mail.debug", "true");
    }

    //1. 发送邮件
    public static void sendEmail() throws Exception {
        //创建会话对象
        Session session = Session.getDefaultInstance(prop, new MyAuthenticator(user,pwd));
        //邮件对象
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("itcast@it.com"));
        message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("yuanjie@it.com"));
        message.setSentDate(new Date());
        message.setSubject("踢足球..............");
        message.setText("那些人去？？？");
        message.saveChanges();

        // 发送
        Transport.send(message);
    }
    //2. 发送html邮件
    public static void sendHtmlEmail() throws Exception {
        //创建会话对象
        Session session = Session.getDefaultInstance(prop, new MyAuthenticator(user,pwd));
        //邮件对象
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("itcast@it.com"));
        message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("yuanjie@it.com"));
        message.setSentDate(new Date());
        message.setSubject("踢足球..............");
        message.setContent("<a href='http://www.baidu.com'>度娘</a>", "text/html;charset=GBK");
        message.saveChanges();
        // 发送
        Transport.send(message);
    }
    //3. 发送html + img 邮件
    public static void sendHtmlAndImgEmail() throws Exception {
        // 会话对象
        Session session = Session.getDefaultInstance(prop, new MyAuthenticator(user, pwd));
        // 邮件对象
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("itcast@it.com"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("yuanjie@it.com"));
        message.setSubject("图片");
        message.setSentDate(new Date());

        // 设置多功能邮件
        MimeMultipart multipart = new MimeMultipart("related");
        message.setContent(multipart);

        // 邮件内容： html + 文本
        MimeBodyPart body = new MimeBodyPart();
        // 邮件“内嵌资源”
        MimeBodyPart source = new MimeBodyPart();

        // 添加邮件内容，到多功能用途邮件
        multipart.addBodyPart(body);
        multipart.addBodyPart(source);

        // 设置资源
        DataSource ds = new FileDataSource(Mail2.class.getResource("1.jpg").getPath());
        DataHandler handler = new DataHandler(ds);
        source.setDataHandler(handler);
        // 设置资源id，供邮件内容引用
        source.setContentID("1.jpg");

        // 设置内容
        body.setContent("<img src='cid:1.jpg' />好好好", "text/html;charset=UTF-8");

        // 保存邮件、发送
        message.saveChanges();
        Transport.send(message);
    }

    //4. 发送html + img + 附件邮件
    public static void sendHtmlWithImgAndAttacheEmail() throws Exception {
        // 会话对象
        Session session = Session.getDefaultInstance(prop, new MyAuthenticator(user, pwd));
        // 邮件对象
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("itcast@it.com"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("yuanjie@it.com"));
        message.setSubject("图片");
        message.setSentDate(new Date());

        /*********设置总邮件块***********/
        MimeMultipart mix = new MimeMultipart("mixed");
        message.setContent(mix);
        // 左右快
        MimeBodyPart left = new MimeBodyPart();
        MimeBodyPart right = new MimeBodyPart();
        // 添加
        mix.addBodyPart(left);
        mix.addBodyPart(right);

        // 设置右边： 附件(中文：1. 路径解码； 2. 通过setFileName设置文件编码为gbk)
        DataSource fileDs = new FileDataSource(URLDecoder.decode(Mail2.class.getResource("用户须知.doc").getPath(), "UTF-8"));
        DataHandler fileHandler = new DataHandler(fileDs);
        right.setDataHandler(fileHandler);
        // 设置文件
        right.setFileName(MimeUtility.encodeText("用户须知.doc"));


        // 设置多功能邮件
        MimeMultipart multipart = new MimeMultipart("related");
        // 设置左边块： html + 文本 + 资源
        left.setContent(multipart);

        // 邮件内容： html + 文本
        MimeBodyPart body = new MimeBodyPart();
        // 邮件“内嵌资源”
        MimeBodyPart source = new MimeBodyPart();

        // 添加邮件内容，到多功能用途邮件
        multipart.addBodyPart(body);
        multipart.addBodyPart(source);

        // 设置资源
        DataSource ds = new FileDataSource(Mail2.class.getResource("1.jpg").getPath());
        DataHandler handler = new DataHandler(ds);
        source.setDataHandler(handler);
        // 设置资源id，供邮件内容引用
        source.setContentID("1.jpg");

        // 设置内容
        body.setContent("<img src='cid:1.jpg' />好好好", "text/html;charset=UTF-8");

        // 保存邮件、发送
        message.saveChanges();
        Transport.send(message);
    }


    // 认证
    static class MyAuthenticator extends Authenticator {
        private String user;
        private String pwd;
        public MyAuthenticator(String user, String pwd) {
            super();
            this.user = user;
            this.pwd = pwd;
        }
        public MyAuthenticator() {}

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, pwd);
        }
    }


    public static void main(String[] args) throws Exception {
//		sendEmail();
//		sendHtmlEmail();
//		sendHtmlAndImgEmail();
        sendHtmlWithImgAndAttacheEmail();
    }
}
