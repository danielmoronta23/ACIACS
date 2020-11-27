package ACIACS.API;

import ACIACS.DTO.DtoPersona;
import ACIACS.encapsulaciones.Sucursal;
import ACIACS.logica.Controladora;
import ACIACS.servicios.ServicioDtoEmpresa;
import ACIACS.servicios.ServicioDtoSucursal;
import ACIACS.util.ControladorBase;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

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
                    Object[] estadisticas = new Object[4];
                    estadisticas[0] = controladora.pruebasRealizadasPorHora(ctx.pathParam("id", String.class).get());
                    estadisticas[1] = controladora.estadisticaVisitasPorMeses(ctx.pathParam("id", String.class).get());
                    estadisticas[2] = controladora.cantidadDePersonaEnSucursal(ctx.pathParam("id", String.class).get());
                    Sucursal sucursal = controladora.buscarSucursal(ctx.pathParam("id", String.class).get());
                    if (sucursal != null) {
                        estadisticas[3] = sucursal.getCapacidad();
                    } else {
                        estadisticas[3] = -1;
                    }
                    ctx.json(estadisticas);
                });
                get("/buscarPersona/:id", ctx -> {
                    Object a[] = new Object[2];
                    DtoPersona b = controladora.buscarPersonaDTO(ctx.pathParam("id", String.class).get());
                    if (b != null) {
                        a[1] = b;
                        a[0] = true;
                    } else {
                        a[0] = false;
                    }
                    ctx.json(a);
                });
                get("/capacidadSucursal/:id", ctx -> {
                    Map<String, Object> modelo = new HashMap<>();
                    Sucursal sucursal = controladora.buscarSucursal(ctx.pathParam("id", String.class).get());
                    if (sucursal != null) {
                        modelo.put("capacidad", sucursal.getCapacidad());
                    } else {
                        modelo.put("capacidad", -1);
                    }
                    ctx.json(modelo);
                });
                get("/sucursalPorEmpresa/:id", ctx -> {
                    ctx.json(new ServicioDtoSucursal().buscarSucursalesPorEmpresa(ctx.pathParam("id", String.class).get()));
                });
                get("/listaEmpresa", ctx -> {
                    ctx.json(new ServicioDtoEmpresa().buscarTodos());
                });
            });
        });
    }
}
