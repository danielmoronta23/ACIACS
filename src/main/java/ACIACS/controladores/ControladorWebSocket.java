package ACIACS.controladores;

import ACIACS.util.ControladorBase;
import ACIACS.util.ModulosConectados;
import io.javalin.Javalin;
import org.eclipse.jetty.websocket.api.Session;

import java.util.ArrayList;
import java.util.List;

public class ControladorWebSocket extends ControladorBase {
    // Creando el repositorio de las sesiones recibidas.
    public static List<ModulosConectados> modulosConectados = new ArrayList<>();

    public ControladorWebSocket(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas() {
        app.wsBefore("/moduloServidor", wsHandler -> {
            System.out.println("Filtro para WS antes de la llamada ws");
            //ejecutar cualquier evento antes...
        });
        /**
         * Definición del websocket  en Javalin.
         * Documentación: https://javalin.io/documentation#websockets
         */
        app.ws("/moduloServidor", ws -> {
            ws.onConnect(ctx -> {
                System.out.println(ctx.headerMap());
                System.out.println("Contenido Header Cookie: " + ctx.header("Cookie"));
                System.out.println("Conexión Iniciada - " + ctx.getSessionId());
                modulosConectados.add(new ModulosConectados("", "", "", ctx.session));
            });
            ws.onMessage(ctx -> {
                System.out.println("Mensaje Recibido de: " + ctx.getSessionId());
                System.out.println("Mensaje: " + ctx.message());
                System.out.println("--------------------------------------------------------------\n");
                Session session = ctx.session;
                session.getRemote().sendString("ERROR! ");

            });
            ws.onClose(ctx -> {
                System.out.println("Conexión Cerrada - " + ctx.getSessionId());
                //usuariosConectados.remove(ctx.session);
            });

            ws.onError(ctx -> {
                System.out.println("Ocurrió un error en el WebSocket :)\n");
            });
        });

    }
}
