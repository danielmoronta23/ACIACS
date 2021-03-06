import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        //Configurando el servidor.
        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 465, "aciacs.project@gmail.com", "aciacsDPM-AR@")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withSessionTimeout(10 * 1000)
                .clearEmailAddressCriteria() // turns off email validation
                .withDebugLogging(true)
                .buildMailer();

        //Cargando la información
      //  byte[] imagen = Main.class.getResourceAsStream("/logoPucmm.png").readAllBytes();
      //  byte[] anexo = Main.class.getResourceAsStream("/isc1b.pdf").readAllBytes();

        /**
         System.out.println("Hola mundo: Enviar mensaje Email");
         System.out.println("Estatus: " + new EnviarMensajeEmail().enviarMensaje("20170570", "Desde Pueblo para el Mundo.", "Mi primer mensaje"));
         **/
        //Configurando el Correo para ser enviado.
        //https://generator.email/qkamal@alpicley.gq
        Email email = EmailBuilder.startingBlank()
                .from("noreply@micorre.com")
                .to("Otra Dirección", "danielmoronta23@hotmail.com")
                //.to("Otra Dirección Random", "dissuadaient@voicememe.com")
                .withReplyTo("Soporte", "soporte@micorre.com")
                .withSubject("Mensaje de Correo Transaccional - Usando Simple Java Mail - "+ new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()))
              //  .withHTMLText(html)
                .withPlainText("No visualiza la información en formato html")
                //.withEmbeddedImage("logo", imagen, "image/png")
                //.withAttachment("Programa ISC", anexo, "application/pdf")
                .withReturnReceiptTo()
                .withBounceTo("aciacs.project@gmail.com")
                .buildEmail();

        //Enviando el mensaje:
        mailer.sendMail(email);
    }
}
