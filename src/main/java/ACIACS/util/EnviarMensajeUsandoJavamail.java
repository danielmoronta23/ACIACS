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
    public void enviarMensaje(String correo, String asunto, String qr) throws IOException {
        /**
         * Fuente: https://github.com/vacax/mail_transaccionales
         */
        //Configurando el servidor.
        String mensaje = mensajeHTML();
        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "aciacs.project@gmail.com", "aciacsDPM-AR@")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withSessionTimeout(10 * 1000)
                .clearEmailAddressCriteria() // turns off email validation
                .withDebugLogging(true)
                .buildMailer();
        byte[] imagen = new GenerateQRCode().GenerarQR(qr);

        //Configurando el Correo para ser enviado.
        //https://generator.email/qkamal@alpicley.gq
        Email email = EmailBuilder.startingBlank()
                .from("administrador@aciacs.com.do")
                .to("Para", "20161226@ce.pucmm.edu.do")
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
    }
    public static String mensajeHTML() {
        // no cambiar el src=\cid:qr\ pues es el que permite colocar el QR
        String mensaje = "";
        mensaje = "<H1>A continuación QR de acceso de prioridad: </H1>";
        mensaje += "<img alt='' width='240' height='240' src=\"cid:qr\">   </div>";
        return mensaje;
    }
}
