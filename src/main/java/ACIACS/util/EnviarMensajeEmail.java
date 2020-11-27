package ACIACS.util;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * GUIA https://www.hostinger.es/tutoriales/como-usar-el-servidor-smtp-gmail-gratuito/
 * https://mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/
 * https://pepipost.com/tutorials/send-email-in-java-using-gmail-smtp/
 */
public class EnviarMensajeEmail {

    public boolean enviarMensaje(String correo, String mensaje, String asunto){
        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";
        String port ="465";

        // Get system properties
        Properties properties = new Properties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        String username  = "goniometria.project@gmail.com";
        String password  = "aciacsDPM-AR@";
        String receptor = correo;

        Session sesion = Session.getDefaultInstance(properties);

        MimeMessage mail = new MimeMessage(sesion);
        try {
            mail.setFrom(new InternetAddress(username));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress (receptor));
            mail.setSubject(asunto);
            mail.setContent(mensaje,"text/html");
            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(username,password);
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transportar.close();
            return true;
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return false;

    }
}
