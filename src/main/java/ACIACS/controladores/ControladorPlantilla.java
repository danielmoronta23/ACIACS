package ACIACS.controladores;

import ACIACS.encapsulaciones.Sucursal;
import ACIACS.encapsulaciones.Usuario;
import ACIACS.logica.Controladora;
import ACIACS.util.ControladorBase;
import ACIACS.util.RolUsuario;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;

import java.util.*;

import static io.javalin.apibuilder.ApiBuilder.*;

public class ControladorPlantilla extends ControladorBase {
    public ControladorPlantilla(Javalin app) {
        super(app);
        JavalinRenderer.register(JavalinThymeleaf.INSTANCE, ".html");
    }

    @Override
    public void aplicarRutas() {
        app.routes(() -> {
            // VISTA ESTATICA: Bienvenida.
            /**
            get("/", ctx -> {
                ctx.render("/Visual/index.html");
            });
             //VISTA DEL LOGIN
             **/
            get("/login", ctx -> {
                ctx.render("/Visual/login.html");
            });
            //CERRAR SESION
            app.get("/loginOUT", ctx -> {
                ctx.clearCookieStore();
                String id = ctx.req.getSession().getId();
                // INVALIDANDO LA SESION.
                ctx.req.getSession().invalidate();
                ctx.result(String.format("La Sesion: %s hacido invalidada", id));
                ctx.redirect("/login");
            });
            // AUTENTICACIÓN EN EL LOGIN
            post("/ingresar", ctx -> {
                Map<String, Object> modelo = new HashMap<>();
                String user = ctx.formParam("usuario");
                String pass = ctx.formParam("password");
                String boton = ctx.formParam("checkbox");
                Usuario aux = Controladora.getInstance().autenticarUsuario(user, pass);
                RolUsuario rolUsuario;
                if (aux != null) {
                    rolUsuario = aux.getRolUsuario();
                    // Creando un atributo de sesion
                    ctx.sessionAttribute("usuario", aux);
                    ctx.cookie("usuario", aux.getCorreo());
                    //PAGINA PRINCIPAL
                    if (rolUsuario==RolUsuario.Admintrador_Comercial) {
                        System.out.println("administrador-comercial\n");
                        ctx.redirect("/administrador-comercial");
                    } else if (rolUsuario==RolUsuario.Super_Admintrador) {
                        System.out.println("Super_Admintrador\n");
                        ctx.redirect("/root");
                    }
                } else {
                    modelo.put("Error", " Por favor, verifique su usuario y constraseña!");
                    //RETORNO A LOGIN
                    ctx.render("/Visual/login.html", modelo);
                }

            });

            path("/root", () -> {
                before("/", ctx -> {
                    // VERIFICAR SI EXISTE COOKIE PARA ENTRAR A LA PAGINA PRINCIPAL O LLEVAR AL LOGIN
                    if (ctx.sessionAttribute("usuario") != null) {
                        //
                    } else {
                        ctx.redirect("/login");
                    }
                });
                get("/", ctx -> {
                    System.out.println("Entrando a root");
                    ctx.render("/Visual/root.html");
                });
            });
            path("/administrador-comercial", () -> {

                before("/", ctx -> {
                    // VERIFICAR SI EXISTE COOKIE PARA ENTRAR A LA PAGINA PRINCIPAL O LLEVAR AL LOGIN
                    if (ctx.sessionAttribute("usuario") != null) {
                        //
                    } else {
                        ctx.redirect("/login");
                    }
                });
                get("/", ctx -> {
                    Usuario usuario = Controladora.getControladora().buscarUsuario(ctx.cookie("usuario"));
                    Map<String, Object> modelo = new HashMap<>();
                    List<Sucursal> sucursalList;
                    if(usuario!=null){
                        sucursalList = usuario.getEmpresa().getListaSucursal();
                        System.out.println("Entrando a dashboard");
                        modelo.put("rol",usuario.getRolUsuario().toString());
                        modelo.put("listaSucursales",sucursalList);
                        ctx.render("/Visual/dashboard.html", modelo);
                    }
                });
            });
            post("/registro-de-empresa", ctx -> {
                Map<String, Object> modelo = new HashMap<>();
                String correo = ctx.formParam("correo");
                String pass = ctx.formParam("password");
                String nombreEmpresa = ctx.formParam("nombreEmpresa");
                String RNCEmpresa = ctx.formParam("nombreEmpresa");
                modelo.put("Error", "No se pudo crear la Empresa, intento mas tarde.");
                if(correo!=null && pass!=null && nombreEmpresa!=null){
                    if(Controladora.getControladora().agregarEmpresa(nombreEmpresa, correo, pass)){
                        ctx.redirect("/login");
                    }else{
                        ctx.render("/Visual/register.html");
                    }
                }

            });
            get("/registro-de-empresa", ctx -> {
                ctx.render("/Visual/register.html");
            });

        });

    }
}
