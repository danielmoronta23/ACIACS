package ACIACS.util;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EnviarMensajeUsandoJavamail {
    public void enviarMensaje(String correo, String asunto, String mensaje, String qr) throws IOException {
        /**
         * Fuente: https://github.com/vacax/mail_transaccionales
         */
        //Configurando el servidor.
        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "aciacs.project@gmail.com", "aciacsDPM-AR@")
                .withTransportStrategy(TransportStrategy.SMTP_TLS) // estaba SMTP_TLS
                .withSessionTimeout(5 * 1000)
                .clearEmailAddressCriteria() // turns off email validation
                .withDebugLogging(true)
                .buildMailer();
        if (qr != null) {
            byte[] imagen = new GenerateQRCode().GenerarQR(qr);

            //Configurando el Correo para ser enviado.
            //https://generator.email/qkamal@alpicley.gq
            Email email = EmailBuilder.startingBlank()
                    .from("administrador@aciacs.com.do")
                    .to("Para", correo)
                    //.to("Otra Dirección Random", "dissuadaient@voicememe.com")
                    .withReplyTo("Soporte", "soporte@aciacs.com.do")
                    .withSubject(asunto + " " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()))
                    .withHTMLText(mensaje)
                    .withPlainText("No visualiza la información en formato html")
                    .withEmbeddedImage("qr", imagen, "image/png")
                    //.withAttachment("Manual de Usuario", anexo, "application/pdf")
                    .withReturnReceiptTo()
                    .withBounceTo("aciacs.project@gmail.com")
                    .buildEmail();

            //Enviando el mensaje:
            mailer.sendMail(email);
        } else {
            //Configurando el Correo para ser enviado.
            //https://generator.email/qkamal@alpicley.gq
            Email email = EmailBuilder.startingBlank()
                    .from("administrador@aciacs.com.do")
                    .to("Para", correo)
                    //.to("Otra Dirección Random", "dissuadaient@voicememe.com")
                    .withReplyTo("Soporte", "soporte@aciacs.com.do")
                    .withSubject(asunto + " " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()))
                    .withHTMLText(mensaje)
                    .withPlainText("No visualiza la información en formato html")
                    //.withEmbeddedImage("qr", imagen, "image/png")
                    //.withAttachment("Manual de Usuario", anexo, "application/pdf")
                    .withReturnReceiptTo()
                    .withBounceTo("aciacs.project@gmail.com")
                    .buildEmail();

            //Enviando el mensaje:
            mailer.sendMail(email);
        }
    }
}
