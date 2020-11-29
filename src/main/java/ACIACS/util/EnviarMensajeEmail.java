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

        String username  = "aciacs.project@gmail.com";
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
    public String mensajeHTML(){
        String mensaje="";
        mensaje = "<!DOCTYPE html>\n" +
                "<html lang='en'>\n" +
                "<head>\n" +
                "    <meta charset='UTF-8'>\n" +
                "    <style>\n" +
                "        :root {\n" +
                "            --white: #fff;\n" +
                "            --warning: #ffc107;\n" +
                "            --font-family-sans-serif: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, 'Noto Sans', sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol', 'Noto Color Emoji';\n" +
                "            --font-family-monospace: SFMono-Regular, Menlo, Monaco, Consolas, 'Liberation Mono', 'Courier New', monospace;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, 'Noto Sans', sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol', 'Noto Color Emoji';\n" +
                "        }\n" +
                "        *, ::before, ::after {\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        .bg-custom {\n" +
                "            background-color: #eceff1;\n" +
                "        }\n" +
                "\n" +
                "        .bg-dark {\n" +
                "            background-color: #343a40 !important;\n" +
                "        }\n" +
                "\n" +
                "        .container-fluid {\n" +
                "            width: 100%;\n" +
                "            padding-right: 0.75rem;\n" +
                "            padding-left: 0.75rem;\n" +
                "            margin-right: auto;\n" +
                "            margin-left: auto;\n" +
                "        }\n" +
                "\n" +
                "        .card {\n" +
                "            position: relative;\n" +
                "            display: flex;\n" +
                "            flex-direction: column;\n" +
                "            min-width: 0;\n" +
                "            word-wrap: break-word;\n" +
                "            background-color: #fff;\n" +
                "            background-clip: border-box;\n" +
                "            border: 1px solid rgba(0, 0, 0, 0.125);\n" +
                "            border-radius: 0.25rem;\n" +
                "        }\n" +
                "\n" +
                "        .card-body {\n" +
                "            flex: 1 1 auto;\n" +
                "            min-height: 1px;\n" +
                "            padding: 1.25rem;\n" +
                "        }\n" +
                "\n" +
                "        .card-header {\n" +
                "            padding: 0.75rem 1.25rem;\n" +
                "            margin-bottom: 0;\n" +
                "            background-color: rgba(0, 0, 0, 0.03);\n" +
                "            border-bottom: 1px solid rgba(0, 0, 0, 0.125);\n" +
                "        }\n" +
                "\n" +
                "        .card-footer {\n" +
                "            padding: 0.75rem 1.25rem;\n" +
                "            background-color: rgba(0, 0, 0, 0.03);\n" +
                "            border-top: 1px solid rgba(0, 0, 0, 0.125);\n" +
                "        }\n" +
                "\n" +
                "        .text-center {\n" +
                "            text-align: center !important;\n" +
                "        }\n" +
                "\n" +
                "        .text-white {\n" +
                "            color: #fff !important;\n" +
                "        }\n" +
                "\n" +
                "        .text-warning {\n" +
                "            color: #ffc107 !important;\n" +
                "        }\n" +
                "\n" +
                "        .mt-4 {\n" +
                "            margin-top: 1.5rem !important;\n" +
                "        }\n" +
                "\n" +
                "        .col-lg-8 {\n" +
                "            flex: 0 0 66.6666666667%;\n" +
                "            max-width: 66.6666666667%;\n" +
                "            position: relative;\n" +
                "        }\n" +
                "\n" +
                "        h1, h2, h3, h4, h5, h6 {\n" +
                "            margin-top: 0;\n" +
                "            margin-bottom: 0.5rem;\n" +
                "            font-weight: 500;\n" +
                "            line-height: 1.2;\n" +
                "        }\n" +
                "        a {\n" +
                "            text-decoration: none;\n" +
                "            background-color: transparent;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            margin-top: 0;\n" +
                "            margin-bottom: 1rem;\n" +
                "        }\n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body class='bg-custom'>\n" +
                "<div class='container-fluid col-lg-8'>\n" +
                "    <div class='card mt-1 bg-dark'>\n" +
                "        <div class='card-header mt-4 text-center bg-dark'>\n" +
                "            <img alt='' width='240' height='240' src='c:/temp/qrcode.png'>   </div>\n" +
                "        <div class='card-body text-center  text-white bg-dark'>\n" +
                "            <p>Mediante este C&oacute;digo QR podr&aacute;s aceder a nuestro local a traves del paso de prioridad.<br/>\n" +
                "                Solo debes presentarlo antes el escaner del sistema y este te notificar&aacute; que podr&aacute;s\n" +
                "                continuar para evaluar si posee mascarilla y una optima temperatura corporal.</p>\n" +
                "            &nbsp;\n" +
                "            <h2 class='text-center bg-dark'>\n" +
                "                <a class='text-warning' href='https://aciacs.azrael.studio/'>\n" +
                "                    <b>ACIACS</b>\n" +
                "                </a>\n" +
                "            </h2>\n" +
                "            &nbsp;\n" +
                "        </div>\n" +
                "        <div class='card-footer text-center text-white bg-dark'>\n" +
                "            <h2><b>&iexcl;Gracias por Preferirnos!</b></h2>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>\n";
        return mensaje;
    }
}
