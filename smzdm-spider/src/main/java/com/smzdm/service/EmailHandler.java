package com.smzdm.service;

import com.smzdm.model.Commodity;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by Changdy on 2017/6/11.
 */
//http://blog.csdn.net/xietansheng/article/details/51673073
@Service
public class EmailHandler {

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

    public void sendMsg(List<Commodity> matchList) {
        if (matchList.size() > 0) {
            StringBuilder content = new StringBuilder("<div style='max-width:700px;'>");
            try {
                for (Commodity commodity : matchList) {
                    content.append("<a style='color:#696969;text-decoration:none;text-align:center;' href='http://www.smzdm.com/p/").append(commodity.getArticleId()).append("'>");
                    content.append(commodity.getTitle()).append("</a>");
                    content.append("<div style='color:#aaa'>").append(commodity.getPriceString()).append("</div>");
                    content.append("<div style='text-align:center'><img src='").append(commodity.getPicUrl()).append("'></div>");
                    content.append("<div style='color:#F04848;text-indent:10px'>").append(commodity.getContent()).append("</div>");
                    content.append("<hr/>");
                }
                content.append("</div>");
                System.out.println(content);
                if (matchList.size() == 1) {
                    sendMsg(matchList.get(0).getTitle(), content.toString());
                } else {
                    sendMsg("捕获到" + matchList.size() + "条优惠信息", content.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
