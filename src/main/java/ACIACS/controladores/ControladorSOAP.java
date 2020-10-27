package ACIACS.controladores;
import ACIACS.Soap.SurcursalWebServices;
import ACIACS.util.ControladorBase;
import com.sun.net.httpserver.HttpContext;
import io.javalin.Javalin;
import org.eclipse.jetty.http.spi.HttpSpiContextHandler;
import org.eclipse.jetty.http.spi.JettyHttpContext;
import org.eclipse.jetty.http.spi.JettyHttpServer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

import javax.xml.ws.Endpoint;
import java.lang.reflect.Method;

public class ControladorSOAP extends ControladorBase {
    public ControladorSOAP(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas() {
        Server server = app.server().server();
        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        server.setHandler(contextHandlerCollection);

        try {
            HttpContext context = build(server, "/ws");

            SurcursalWebServices wsa = new SurcursalWebServices();
            Endpoint endpoint = Endpoint.create(wsa);
            endpoint.publish(context);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private HttpContext build(Server server, String contextString) throws Exception {
        JettyHttpServer jettyHttpServer = new JettyHttpServer(server, true);
        JettyHttpContext ctx = (JettyHttpContext) jettyHttpServer.createContext(contextString);
        Method method = JettyHttpContext.class.getDeclaredMethod("getJettyContextHandler");
        method.setAccessible(true);
        HttpSpiContextHandler contextHandler = (HttpSpiContextHandler) method.invoke(ctx);
        contextHandler.start();
        return ctx;
    }
}
