package com.mail.mail.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mail.mail.model.Mail;

public class MailService {

    private static final String SEPARATOR = "##";

    public void send(Mail m) {

        // SENDER
        final String username = "idrissbenguezzou@gmail.com";
        final String password = "vtclhroghazqboko";

        Properties prop = new Properties();

        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Idriss Benguezzou <idrissbenguezzou@gmail.com>"));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mailstone2022@gmail.com"));

            message.setSubject("Plateforme en ligne");
            message.setText(m.getProduct() + SEPARATOR + m.getIssue());

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
