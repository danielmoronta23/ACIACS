package ACIACS.API;

import ACIACS.logica.Controladora;
import ACIACS.util.ControladorBase;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class ApiRest extends ControladorBase {
    public ApiRest(Javalin app) {
        super(app);
    }

    private Controladora controladora = Controladora.getInstance();

    @Override
    public void aplicarRutas() {
        app.routes(() -> {
            path("api-Rest", () -> {
                after(ctx -> {
                    ctx.header("Content-Type", "application/json");
                });
                get("/visitasPorHora/:id", ctx -> {
                    ctx.json(controladora.pruebasRealizadasPorHora(ctx.pathParam("id", String.class).get()));
                });
                get("/visitasPorMeses/:id", ctx -> {
                    ctx.json(controladora.estadisticaVisitasPorMeses(ctx.pathParam("id", String.class).get()));
                });
                get("/estadisticas/:id", ctx -> {
                    Object[] estadisticas = new Object[2];
                    estadisticas[0] = controladora.pruebasRealizadasPorHora(ctx.pathParam("id", String.class).get());
                    estadisticas[1] = controladora.estadisticaVisitasPorMeses(ctx.pathParam("id", String.class).get());
                    ctx.json(estadisticas);
                });
            });
        });
    }
}
