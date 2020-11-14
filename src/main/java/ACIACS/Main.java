package ACIACS;

import ACIACS.API.ApiRest;
import ACIACS.controladores.ControladorSOAP;
import ACIACS.logica.Controladora;
import ACIACS.services.ConexionDB;
import io.javalin.Javalin;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config ->{
            config.addStaticFiles("/publico"); //Desde la carpeta de resources.
            config.enableCorsForAllOrigins();
        });
        try {
            ConexionDB.getInstance();
            Controladora.getInstance(); //.crearDatosPorDefecto();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
      //  new ControladorSOAP(app).aplicarRutas();
        new ApiRest(app).aplicarRutas();
        app.start(7000);
    }
}
