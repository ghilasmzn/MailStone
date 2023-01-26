package com.analyzer.analyzer.controller.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReceiveMail {

    private final String pop3Host = "pop.gmail.com";
    private final String storeType = "pop3s";
    private final String username = "mailstone2022@gmail.com";
    private final String password = "dtdxbgmdobizcyqf";

    /**
     * Process the content of the mail.
     * 
     * @param emailMessages
     * @throws MessagingException
     * @throws IOException
     */
    private void processMails(Message[] emailMessages) throws MessagingException, IOException {

        // Iterate the messages
        for (int i = 0; i < emailMessages.length; i++) {

            Message message = emailMessages[i];

            System.out.println("\nEmail " + (i + 1) + "-");
            System.out.println("Subject - " + message.getSubject());
            System.out.println("From - " + message.getFrom()[0]);
            System.out.println("Message : ");

            Object content = emailMessages[i].getContent();

            if (content instanceof String) {
                String mailString = (String) content;
                new FetchObject().analyzeContent(message.getSentDate(), message.getFrom()[0].toString(), mailString);
                System.out.println(mailString);

            } else if (content instanceof Multipart) {
                Multipart multipart = (Multipart) emailMessages[i].getContent();

                for (int x = 0; x < multipart.getCount() - 1; x++) {
                    BodyPart bodyPart = multipart.getBodyPart(x);
                    String mailString = bodyPart.getContent().toString();

                    new FetchObject().analyzeContent(message.getSentDate(), message.getFrom()[0].toString(),
                            mailString);
                    System.out.println("\n=>" + bodyPart.getContent());
                }
            }
        }

    }

    /**
     * Receive the mail and get the data
     * 
     * @throws MessagingException
     * @throws IOException
     */
    public void receiveEmail() throws MessagingException, IOException {
        Properties props = new Properties();

        props.put("mail.pop3.host", pop3Host);
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.starttls.enable", "true");
        props.put("mail.store.protocol", "pop3");

        Session session = Session.getInstance(props);

        Store mailStore = session.getStore(storeType);
        mailStore.connect(pop3Host, username, password);

        Folder folder = mailStore.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);

        Message[] emailMessages = folder.getMessages();
        System.out.println("Total Message - " + emailMessages.length);

        processMails(emailMessages);

        folder.close(false);
        mailStore.close();

    }
}
