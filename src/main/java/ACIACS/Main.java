package ACIACS;

import ACIACS.API.ApiRest;
import ACIACS.controladores.ControladorPlantilla;
import ACIACS.controladores.ControladorSOAP;
import ACIACS.controladores.ControladorWebSocket;
import ACIACS.logica.Controladora;
import ACIACS.servicios.ConexionDB;
import io.javalin.Javalin;

import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
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
}
