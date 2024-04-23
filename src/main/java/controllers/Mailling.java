package controllers;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import Entities.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import javax.mail.PasswordAuthentication;

public class Mailling {
    public static void send(User user, Map<String, String> data) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protokls", "TLSv1.2");
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.port", "2525");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("2335f5d5205db9", "********7be3");
            }
        });

        String htmlFilePath = "./css/emailTemplate.html";
        try {
            String htmlContent = new String(Files.readAllBytes(Paths.get(htmlFilePath)));

            htmlContent = htmlContent.replace("[titlePlaceholder]", data.get("titlePlaceholder"));
            htmlContent = htmlContent.replace("[msgPlaceholder]", data.get("msgPlaceholder"));
            htmlContent = htmlContent.replace("[codePlaceholder]", Integer.toString(user.getIs_verified()));

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("E-FlexBank@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject(data.get("emailSubject"));
            // message.setText("Hello, this is a test email from JavaFX.");
            message.setContent(htmlContent, "text/html");

            Transport.send(message);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
