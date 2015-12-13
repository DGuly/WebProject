package ua.com.jee.service;


import org.springframework.stereotype.Service;
import ua.com.jee.entity.UserEntity;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.text.html.HTMLDocument;
import java.util.Properties;

@Service
public class EmailService {

    private Session mailSession;
    private Transport mailTransport;

    public void sendAccessCode(UserEntity userEntity) {
        sendText(userEntity.getEmail(), "Access Code", "Your access code is: " + userEntity.getCode());
    }

    public void sendText(String email, String subject, String textMessage) {
        setProperties();
        MimeMessage message = new MimeMessage(mailSession);

        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(subject);
            message.setText(textMessage);

            mailTransport.sendMessage(message, message.getAllRecipients());
            mailTransport.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void setProperties() {
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", "587");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");

        mailSession = Session.getDefaultInstance(emailProperties, null);
        /**
         * Sender's credentials
         * */
        String user = "kpishare.noreply@gmail.com";
        String password = "kpi-share";

        String host = "smtp.gmail.com";
        try {
            mailTransport = mailSession.getTransport("smtp");
            mailTransport.connect(host, user, password);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
