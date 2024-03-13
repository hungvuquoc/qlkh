package com.example.qlkh.utils;

import com.example.qlkh.error.status.CommonStatus;
import com.example.qlkh.exception.DataRuntimeException;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class EbsEmailUtils {
    @Value("${mailServer.email}")
    String myEmail;
    @Value("${mailServer.password}")
    String myPassword;
    @Value("${mailServer.host}")
    String host;
    @Value("${mailServer.port}")
    String port;
    @Value("${mailServer.auth}")
    String auth;
    @Value("${mailServer.Starttls}")
    String starttls;

    public void send(String toEmail, String subject, String body) {
        ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
        emailExecutor.execute(() -> {
            try {
                Properties prop = System.getProperties();
                prop.put("mail.smtp.host", host);
                prop.put("mail.smtp.auth", auth);
                prop.put("mail.smtp.port", port);
                prop.put("mail.smtp.starttls.enable", starttls);

                Session session = Session.getInstance(prop, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(myEmail, myPassword);
                    }
                });

                MimeMessage message = new MimeMessage(session);

                message.setFrom(new InternetAddress(myEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
                message.setSubject(subject);
                message.setText(body);

                Transport.send(message);
            } catch (MessagingException e) {
                log.error("EmailUtils error: {}", e.getMessage());
                throw new DataRuntimeException(CommonStatus.SERVER_SEND_MAIL_ERROR);
            }
        });
    }
}
