package com.smzdm.service;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Changdy on 2017/6/11.
 */
//http://blog.csdn.net/xietansheng/article/details/51673073
public class EmailService {

    private static final String EMAILACCOUNT = "117969632@qq.com";
    private static final String PASSWORD = "";
    private static final String SMTPHOST = "smtp.qq.com";
    private static final String RECEIVEMAILACCOUNT = "changdy@163.com";
    private static final String smtpPort = "465";

    private Properties getProperties() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", SMTPHOST);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        return props;
    }

    private MimeMessage createMimeMessage(Session session, String title, String content) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(EMAILACCOUNT, "什么值得买", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(RECEIVEMAILACCOUNT, "什么值得买信息", "UTF-8"));
        message.setSubject(title, "UTF-8");
        message.setContent(content, "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

    public void sendMsg(String title, String content) throws Exception {
        Properties properties = getProperties();
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);
        MimeMessage message = createMimeMessage(session, title, content);
        Transport transport = session.getTransport();
        transport.connect(EMAILACCOUNT, PASSWORD);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
