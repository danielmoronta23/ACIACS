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
                    controladora.datosPruebas();
                    ctx.json(controladora.pruebasRealizadasPorHora(ctx.pathParam("id", String.class).get()));
                });
                get("/visitasPorMeses/:id", ctx -> {
                    controladora.datosPruebas();
                    ctx.json(controladora.estadisticaVisitasPorMeses(ctx.pathParam("id", String.class).get()));
                });
            });
        });
    }
}