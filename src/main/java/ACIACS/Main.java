package ACIACS;

import ACIACS.API.ApiRest;
import ACIACS.controladores.ControladorPlantilla;
import ACIACS.controladores.ControladorSOAP;
import ACIACS.logica.Controladora;
import ACIACS.servicios.ConexionDB;
import ACIACS.util.EnviarMensajeUsandoJavamail;
import io.javalin.Javalin;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {

        System.out.println("Haciendo TEST de envio de mensaje: ");
        try {
            new EnviarMensajeUsandoJavamail().enviarMensaje("danielmoronta23@hotmail.com","TEST",mensajeHTML(),"TEST");
            System.out.println("Se ha enviado el mensaje al correo electronico.");
        } catch (IOException e) {
            System.out.println("NO SE PUDO ENVIAR EL MENSAJE...");
            e.printStackTrace();
        }

        System.out.println("Test completado\n");
        Javalin app = Javalin.create(config ->{
            config.addStaticFiles("/Visual"); //Desde la carpeta de resources.
            config.enableCorsForAllOrigins();
        });
        try {
            ConexionDB.getInstance();
            Controladora.getInstance().datosPruebas(); //.crearDatosPorDefecto();
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        new ControladorSOAP(app).aplicarRutas();
        new ApiRest(app).aplicarRutas();
        new ControladorPlantilla(app).aplicarRutas();

        app.start(7000);
    }
    public static String mensajeHTML() {
        String mensaje = "";
        mensaje = "<H1>Este es un test de envio de mensaje con QR: </H1>";
        mensaje += "<img alt='' width='240' height='240' src=\"cid:qr\">   </div>";
        return mensaje;
    }
}
