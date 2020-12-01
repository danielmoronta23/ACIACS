package ACIACS.controladores;

import ACIACS.encapsulaciones.*;
import ACIACS.logica.Controladora;
import ACIACS.servicios.ServicioEmpresa;
import ACIACS.servicios.ServicioModulo;
import ACIACS.servicios.ServicioSucursal;
import ACIACS.util.*;
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
                    if (rolUsuario == RolUsuario.Admintrador_Comercial) {
                        System.out.println("administrador-comercial\n");
                        ctx.redirect("/administrador-comercial");
                    } else if (rolUsuario == RolUsuario.Super_Admintrador) {
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
                    Map<String, Object> modelo = new HashMap<>();
                    System.out.println("Entrando a root");
                    modelo.put("totalModulos", new ServicioModulo().cantModulos());
                    modelo.put("totalModulosNormalesActivos", new ServicioModulo().cantModulos(true, EstatusModulo.Activo));
                    modelo.put("totalModulosPrioritarioActivos", new ServicioModulo().cantModulos(false, EstatusModulo.Activo));
                    modelo.put("listaSucursal", new ServicioEmpresa().explorarTodo());
                    ctx.render("/Visual/root.html", modelo);
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
                    Set<Sucursal> sucursalList;
                    if (usuario != null) {
                        sucursalList = usuario.getEmpresa().getListaSucursal();
                        System.out.println("Entrando a dashboard");
                        modelo.put("rol", usuario.getRolUsuario().toString());
                        modelo.put("listaSucursales", sucursalList);
                        ctx.render("/Visual/dashboard.html", modelo);
                    }
                });
                post("/modificarCantSucursal", ctx -> {
                    String idSucursal = ctx.formParam("selectOption");
                    boolean estatus = false;
                    int capacidad = Integer.parseInt(ctx.formParam("maxCap"));
                    if (capacidad > 0) {
                        Sucursal sucursal = Controladora.getControladora().buscarSucursal(idSucursal);
                        if (sucursal != null) {
                            sucursal.setCapacidad(capacidad);
                            estatus = Controladora.getControladora().actualizarSucursal(sucursal);
                        }
                        ctx.redirect("/administracion-sucursal");
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
                if (correo != null && pass != null && nombreEmpresa != null) {
                    if (Controladora.getControladora().agregarEmpresa(nombreEmpresa, correo, pass)) {
                        ctx.redirect("/login");
                    } else {
                        ctx.render("/Visual/register.html");
                    }
                }

            });
            get("/registro-de-empresa", ctx -> {
                ctx.render("/Visual/register.html");
            });
            get("/administracion-sucursal", ctx -> {
                Usuario usuario = null;
                if (ctx.cookie("usuario") != null) {
                    usuario = Controladora.getControladora().buscarUsuario(ctx.cookie("usuario"));

                }
                Map<String, Object> modelo = new HashMap<>();
                Set<Sucursal> sucursalList;
                if (usuario != null) {
                    sucursalList = usuario.getEmpresa().getListaSucursal();
                    System.out.println("Entrando a sucursales desde administrador comercial... " + usuario.getEmpresa().getListaSucursal().size());
                    modelo.put("rol", usuario.getRolUsuario().toString());
                    modelo.put("listaSucursales", sucursalList);
                    ctx.render("/Visual/sucursales.html", modelo);
                } else {
                    ctx.redirect("/login");
                }
            });
            get("/administar-PersonasPrioritarias", ctx -> {
                Usuario usuario = null;
                Map<String, Object> modelo = new HashMap<>();
                Set<ListaDeAccesso> listaDeAccessos = new HashSet<>();
                if (ctx.cookie("usuario") != null) {
                    usuario = Controladora.getControladora().buscarUsuario(ctx.cookie("usuario"));
                }
                if (usuario != null) {
                    listaDeAccessos = usuario.getEmpresa().getListaDeAccessos();
                    modelo.put("listaPersona", listaDeAccessos);
                    ctx.render("/Visual/registerPriority.html", modelo);
                } else {
                    ctx.redirect("/login");
                }

            });
            post("/agregarPersona", ctx -> {
                boolean estado = false;
                System.out.println("\n Entrado a agregar Persona ...");
                Usuario usuario = null;
                Map<String, Object> modelo = new HashMap<>();
                String cedula = ctx.formParam("cedula");
                String nombre = ctx.formParam("nombre");
                String apellido = ctx.formParam("apellido");
                String correo = ctx.formParam("correo");
                String coreoEmail = null;
                if (ctx.cookie("usuario") != null) {
                    usuario = Controladora.getControladora().buscarUsuario(ctx.cookie("usuario"));
                }
                if (usuario != null && cedula != null) {
                    ListaDeAccesso listaDeAccesso;
                    Persona persona = Controladora.getControladora().buscarPersona(cedula);
                    if (persona != null) {
                        // Ya la persona existe
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(new Date());
                        calendar.add(Calendar.YEAR, 2);
                        listaDeAccesso = new ListaDeAccesso(persona, usuario.getEmpresa(), EstatusAcceso.Activo, new Date(), calendar.getTime());
                        estado = Controladora.getControladora().agregarListaDeAcceso(listaDeAccesso);
                        coreoEmail = persona.getCorreo();
                    } else {
                        Controladora.getControladora().agregarPersona(new Persona(cedula, nombre, "", apellido, "", correo));
                        Persona persona1 = Controladora.getControladora().buscarPersona(cedula);
                        if (persona1 != null) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(new Date());
                            calendar.add(Calendar.YEAR, 2);
                            listaDeAccesso = new ListaDeAccesso(persona1, usuario.getEmpresa(), EstatusAcceso.Activo, new Date(), calendar.getTime());
                            estado = Controladora.getControladora().agregarListaDeAcceso(listaDeAccesso);
                            if(estado){
                                coreoEmail = persona1.getCorreo();
                            }
                        }
                    }
                    if (estado) {
                        //Enviando QR...
                        String asunto = "Ya eres un cliente prioritario de: "+usuario.getEmpresa().getNombre()+".";
                        try {
                            new EnviarMensajeUsandoJavamail().enviarMensaje(coreoEmail, asunto, mensajeHTML(),cedula);
                            System.out.println("Se ha enviado el mensaje al correo electronico.");
                        } catch (Exception  e) {
                            System.out.println("NO SE PUDO ENVIAR EL MENSAJE...");
                            e.printStackTrace();
                        }
                    }
                    ctx.redirect("/administar-PersonasPrioritarias");
                } else {
                    ctx.redirect("/login");
                }
            });
            post("/eliminarPersona", ctx -> {
                Usuario usuario = null;
                String id = ctx.formParam("cedula");
                if (ctx.cookie("usuario") != null) {
                    usuario = Controladora.getControladora().buscarUsuario(ctx.cookie("usuario"));
                }
                if (usuario != null && id != null) {
                    System.out.println("entrado para eliminar a personas de la lista de acceso: " + id);
                    Controladora.getControladora().eliminarPersonaListaDeAcceso(id);
                }
                ctx.redirect("/administar-PersonasPrioritarias");
            });
            post("/bloquearPersona", ctx -> {
                Usuario usuario = null;
                String id = ctx.formParam("idAcceso");
                if (ctx.cookie("usuario") != null) {
                    usuario = Controladora.getControladora().buscarUsuario(ctx.cookie("usuario"));
                }
                if (usuario != null && id != null) {
                    System.out.println("entrado para eliminar a personas de la lista de acceso: " + id);
                    Controladora.getControladora().bloquearAcceso(id);
                }
                ctx.redirect("/administar-PersonasPrioritarias");
            });
            get("/root-ConfModulos", ctx -> {
                if (ctx.sessionAttribute("usuario") != null) {
                    //
                    Usuario usuario = null;
                    Map<String, Object> modelo = new HashMap<>();
                    List<Modulo> listaModulo = new ArrayList<>();
                    listaModulo = new ServicioModulo().explorarTodo();
                    modelo.put("listaModulo", listaModulo);
                    System.out.println("Entrando a root-confModulos");
                    ctx.render("/Visual/ConfModulos.html", modelo);
                } else {
                    ctx.redirect("/login");
                }
            });
            post("/root-ConfModulos", ctx -> {
                if (ctx.sessionAttribute("usuario") != null) {
                    //
                    String sucursal = ctx.formParam("sucursal");
                    String modo = ctx.formParam("modo");
                    Usuario usuario = null;
                    Sucursal sucursal1 = null;
                    Boolean estatus = false;
                    if (sucursal != null) {
                        sucursal1 = Controladora.getControladora().buscarSucursal(sucursal);
                    }
                    if (sucursal1 != null) {
                        if (modo != null) {
                            if (modo.equalsIgnoreCase("1")) {
                                /// normal
                                ModuloNormal moduloNormal = new ModuloNormal(EstatusModulo.Activo, sucursal1);
                                estatus = Controladora.getControladora().agregarModulo(moduloNormal);
                                System.out.println("Intentado agregar modulo normal..");
                            } else if (modo.equalsIgnoreCase("2")) {
                                //prioritario
                                ModuloPrioridad moduloPrioridad = new ModuloPrioridad(EstatusModulo.Activo, sucursal1);
                                estatus = Controladora.getControladora().agregarModulo(moduloPrioridad);
                                System.out.println("Intentado agregar modulo Prioritario...");

                            }
                        }
                    }
                    Map<String, Object> modelo = new HashMap<>();
                    List<Modulo> listaModulo = new ArrayList<>();
                    listaModulo = new ServicioModulo().explorarTodo();
                    String info = "";
                    if (estatus) {
                        info = "Se ha creado el modulo de forma sactifactoria!";
                    }
                    modelo.put("Info", info);
                    modelo.put("listaModulo", listaModulo);
                    System.out.println("Entrando a root-confModulos");
                    ctx.render("/Visual/ConfModulos.html", modelo);
                } else {
                    ctx.redirect("/login");
                }
            });

            get("/root-ConfSucursales", ctx -> {
                if (ctx.sessionAttribute("usuario") != null) {
                    //
                    Usuario usuario = null;
                    Map<String, Object> modelo = new HashMap<>();
                    List<Sucursal> listaSucursal = new ArrayList<>();
                    listaSucursal = new ServicioSucursal().explorarTodo();
                    modelo.put("listaSucursal", listaSucursal);
                    System.out.println("Entrando a root-ConfSucursales");
                    ctx.render("/Visual/ConfSucursales.html", modelo);
                } else {
                    ctx.redirect("/login");
                }
            });
            post("/root-ConfSucursales", ctx -> {
                if (ctx.sessionAttribute("usuario") == null) {
                    ctx.redirect("/login");
                } else {
                    Map<String, Object> modelo = new HashMap<>();
                    String empresa = ctx.formParam("empresa");
                    String dirrecion = ctx.formParam("dirrecion");
                    String capacidad = ctx.formParam("capacidad");
                    Empresa auxEmpresa = null;
                    Boolean estatus = false;
                    if (empresa != null) {
                        auxEmpresa = Controladora.getControladora().buscarEmpresa(empresa);
                    }
                    if (auxEmpresa != null) {
                        if (dirrecion != null && capacidad != null) {
                            Sucursal sucursal = new Sucursal(new Ubicacion("", "", dirrecion), 0, Integer.parseInt(capacidad), auxEmpresa);
                            Controladora.getControladora().agregarSucursal(sucursal);

                        }
                    }
                    List<Sucursal> listaSucursal = new ArrayList<>();
                    listaSucursal = new ServicioSucursal().explorarTodo();
                    modelo.put("listaSucursal", listaSucursal);
                    System.out.println("Entrando a agregarSucursal root-ConfSucursales");
                    ctx.render("/Visual/ConfSucursales.html", modelo);
                }
            });

            //  Error 404.
            app.error(404, ctx -> {
                //ctx.result("Generic 404 message");
                ctx.render("/Visual/404.html");
            });
        });

    }
    /**
     *  EL SRC se debe dejar igual como esta (src="cid:qr\). Pues el que se usa para colocar el QR
     */
    public static String mensajeHTML() {
        String mensaje = " <!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "<meta name=\"viewport\" content=\"initial-scale=1.0\">\n" +
                "<meta name=\"format-detection\" content=\"telephone=no\">\n" +
                "<meta content=\"IE=edge,chrome=1\" http-equiv=\"X-UA-Compatible\">\n" +
                "<link href=\"http://fonts.googleapis.com/css?family=Oswald|Open+Sans&subset=latin,latin-ext\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300&subset=latin,latin-ext' rel='stylesheet' type='text/css'>\n" +
                "<style type=\"text/css\">\n" +
                "body {\n" +
                "\twidth:100% !important;\n" +
                "\t-webkit-text-size-adjust:100%;\n" +
                "\t-ms-text-size-adjust:100%;\n" +
                "\tmargin:0;\n" +
                "\tpadding:0;\n" +
                "\tline-height:100%;\n" +
                "}\n" +
                "#outlook a {\n" +
                "\tpadding:0;\n" +
                "}\n" +
                "\n" +
                ".ExternalClass,\n" +
                ".ExternalClass p,\n" +
                ".ExternalClass span,\n" +
                ".ExternalClass font,\n" +
                ".ExternalClass td,\n" +
                ".ExternalClass div {\n" +
                "\tline-height:100%;\n" +
                "    border-collapse: collapse;\n" +
                "    \n" +
                "}\n" +
                ".tablereset {\n" +
                "\tmargin:0 auto;\n" +
                "\twidth:680px !important;\n" +
                "\tline-height:100% !important\n" +
                "}\n" +
                "img {\n" +
                "\toutline:none;\n" +
                "\ttext-decoration:none;\n" +
                "\tborder:none;\n" +
                "\t-ms-interpolation-mode:bicubic;\n" +
                "    max-width: 100%!important;\n" +
                "    margin: 0;\n" +
                "    padding: 0;\n" +
                "    display: block;\n" +
                "}\n" +
                "table td {\n" +
                "\tborder-collapse:collapse;\n" +
                "}\n" +
                "table {\n" +
                "\tborder-collapse:collapse; \n" +
                "    mso-table-lspace:0pt; \n" +
                "    mso-table-rspace:0pt;\n" +
                "}\n" +
                "   \n" +
                "    td[class*=font-resize] {font-size: 140%!important}\n" +
                "    td[style*=\"Oswald\"] {font-family: 'Oswald', Arial, Helvetica, sans-serif!important}\n" +
                "    td[style*=\"Open Sans\"] {font-family: 'Open Sans', arial, sans-serif !important}    \n" +
                "\n" +
                "@media only screen and (max-width:680px) {\n" +
                "\ta[href=\"tel\"],\n" +
                "\ta[href=\"sms\"] {\n" +
                "\t\ttext-decoration:none;\n" +
                "\t\tcolor:#ffffff;\n" +
                "\t\tpointer-events:none;\n" +
                "\t\tcursor:default;\n" +
                "\t}\n" +
                "\ttr.removemobile {\n" +
                "\t\tdisplay:none;\n" +
                "\t}\n" +
                "\ttable.removemobile {\n" +
                "\t\tdisplay:none;\n" +
                "\t}\n" +
                "\ttable.tablereset {\n" +
                "\t\tmargin:0 auto;\n" +
                "\t\twidth:440px !important;\n" +
                "\t\tline-height:100% !important\n" +
                "\t}\n" +
                "\ttable[class*=devicewidth] {\n" +
                "\t\twidth:440px!important;\n" +
                "\t\ttext-align:center!important;\n" +
                "        float: none!important;\n" +
                "        display: table!important;\n" +
                "\t}\n" +
                "\ttable[class*=devicewidth] .banner img {\n" +
                "\t\twidth: 440px!important;\n" +
                "\t}\n" +
                "\ttable[class*=devicewidthinner] {\n" +
                "\t\twidth:400px!important;\n" +
                "\t\ttext-align:center!important;\n" +
                "        float: none!important;\n" +
                "\t}\n" +
                "\ttable[class*=devicewidthinner] .banner img {\n" +
                "\t\twidth: 400px!important;\n" +
                "\t}\n" +
                "     table[class*=devicewidthinner3] {\n" +
                "\t\twidth:173px!important;\n" +
                "        float: none!important;\n" +
                "\t}\n" +
                "\timg[class*=logoImg] {\n" +
                "\t\twidth:110px!important;\n" +
                "\t\theight:auto!important;\n" +
                "\t}\n" +
                "}\n" +
                "@media only screen and (max-width:519px) {\n" +
                "    table.removemobile {\n" +
                "\t\tdisplay:none;\n" +
                "\t}\n" +
                "\ttable[class*=tablereset] {\n" +
                "\t\tmargin:0 auto;\n" +
                "\t\twidth:280px !important;\n" +
                "\t\tline-height:100% !important\n" +
                "\t}\n" +
                "    img[class*=logoImg] {\n" +
                "\t\twidth:100px!important;\n" +
                "\t\theight:auto!important;\n" +
                "\t}\n" +
                "    td[class*=threecolumnphoto] {\n" +
                "\t\tpadding-bottom: 20px;\n" +
                "\t}\n" +
                "\ttable[class*=devicewidthright] {\n" +
                "\t\twidth:160px!important;\n" +
                "\t}\n" +
                "\ttable[class*=devicewidth] {\n" +
                "\t\twidth:280px!important;\n" +
                "        float: none!important;\n" +
                "        display: table!important;        \n" +
                "\t}\n" +
                "\ttable[class*=devicewidth] .banner img {\n" +
                "\t\twidth: 280px!important;\n" +
                "\t}\n" +
                "\ttable[class*=devicewidthinner] {\n" +
                "\t\twidth:240px!important;\n" +
                "        float: none!important;\n" +
                "\t}\n" +
                "\ttable[class*=devicewidthinner] .banner img {\n" +
                "\t\twidth: 240px!important;\n" +
                "\t}\n" +
                "    table[class*=devicewidthinner3] {\n" +
                "\t\twidth:173px!important;\n" +
                "        float: none!important;\n" +
                "\t}\n" +
                "    table[class*=button] {\n" +
                "\t\tfloat: none!important;\n" +
                "\t}\n" +
                "\ttable[class*=button] td,\n" +
                "\ttable[class*=button] td a {\n" +
                "\t\tfont-size:12px!important;\n" +
                "\t}\n" +
                "    td[class*=oswaldfont] {\n" +
                "\t\tfont-size: large!important;\n" +
                "\t}\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body style=\"background-color:#ededed; margin:0; padding:0\">\n" +
                "<div style=\"background-color: #e7e6e6 \">\n" +
                "\t<br>\n" +
                "\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"table-layout:fixed\" width=\"100%\">\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td>\n" +
                "\n" +
                "\t\t\t\t<!-- ciemny banner -->\n" +
                "\n" +
                "\t\t\t\t<table class=\"tablereset\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"-moz-border-top-left-radius: 5px; -webkit-border-radius: 5px;\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=\"680\">\n" +
                "\t\t\t\t\t\t\t<table class=\"devicewidth\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"680\" bgcolor=\"#393939\">\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td width=\"680\" align=\"center\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<table class=\"devicewidthinner\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"580\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"580\" align=\"center\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<table class=\"devicewidthinner\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"290\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"520\" align=\"center\" style=\"font-size:1px;line-height:1px;\" height=\"26\" >&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"520\" align=\"center\" style=\"font-family:Arial,Helvetica,sans-serif, 'Open Sans';font-size:28px;line-height:1.3em;color:#ffffff;font-weight:300;text-align:left;\">ACIACS</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"520\" height=\"15\" align=\"center\" style=\"font-size:1px;line-height:1px;\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"520\" align=\"center\" style=\"font-family:Arial,Helvetica,sans-serif, 'Open Sans';font-size:17px;line-height:1.6em;color:#8b8b8b;font-weight:300;text-align:left;\">Mediante este C&oacute;digo QR podr&aacute;s aceder a nuestro local a traves del paso de prioridad.<br/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<br/>Solo debes presentarlo frente al esc&aacute;ner del sistema y posteriormente se le notificar&aacute; para que siga con el proceso de evaluaci&oacute;n.\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tcontinuar para evaluar si poses mascarilla y una optima temperatura corporal.</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"520\" height=\"30\" align=\"center\" style=\"font-size:1px;line-height:1px;\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<table class=\"devicewidthinner\" align=\"right\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"290\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"290\" align=\"center\" style=\"font-size:1px;line-height:1px;\" height=\"26\" width=\"290\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"240\" align=\"center\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<img  HEIGHT=\"290\"  src=\"cid:qr\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t alt=\"QR CODE\" border=\"0\" style=\"color:#000000; border:none;  display:block; font-size:22px;margin:0 auto;text-align:center;\" width=\"290\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<br>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<br>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\n" +
                "\n" +
                "\t\t\t\t<!-- koniec -->\n" +
                "\n" +
                "\n" +
                "                <table class=\"tablereset\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=\"680\">\n" +
                "\t\t\t\t\t\t\t<table  class=\"devicewidth\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"680\">\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td align=\"center\" style=\"font-size:1px; line-height:1px\" height=\"32\" width=\"680\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t\t<table class=\"tablereset\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=\"680\">\n" +
                "\t\t\t\t\t\t\t<table class=\"devicewidth\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"680\">\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td align=\"center\" style=\"font-size:1px; line-height:1px\" height=\"24\" width=\"680\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t\t<table class=\"tablereset\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=\"680\">\n" +
                "\t\t\t\t\t\t\t<table class=\"devicewidth\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"680\">\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td width=\"680\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<table class=\"devicewidthinner\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"580\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" width=\"580\" style=\"color:#8f8f8f; font-family:Arial, Helvetica, sans-serif; font-size:12px; line-height:1.4em\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\tMessage sent by ACIACS: RD Navarrete, Santiago de los Caballeros.\n" +
                "Phone Number. +44 12 3456 7890, email: support@aciacs.com, aciacs.azrael.studio.\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t\t<table class=\"tablereset\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=\"680\">\n" +
                "\t\t\t\t\t\t\t<table class=\"devicewidth\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"680\">\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td align=\"center\" style=\"font-size:1px; line-height:1px\" height=\"32\" width=\"680\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t\t<table class=\"tablereset\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=\"680\">\n" +
                "\t\t\t\t\t\t\t<table align=\"center\" class=\"devicewidth\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"134\">\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"middle\" style=\"font-family:Arial, Helvetica, sans-serif; padding:10px 0 10px 0px\" width=\"180\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.pucmm.edu.do\" style=\"color:#f1f1f1; font-size:10px; text-decoration:none\" target=\"_blank\" title=\"FreshMail.com\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<img class=\"logoImg\" src=\"https://www.pucmm.edu.do/recursos/PublishingImages/logosimbolo/Marca%20PUCMM%20%28Color%29.png\" alt=\"FreshMail\" border=\"0\" width=\"139\" style=\"color:#000000; border:none; font-size:22px; display:block; height:auto; max-height:139px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t</a>\n" +
                "\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t\t<table class=\"tablereset\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=\"680\">\n" +
                "\t\t\t\t\t\t\t<table class=\"devicewidth\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"680\">\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td align=\"center\" style=\"font-size:1px; line-height:1px\" height=\"54\" width=\"680\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t</table>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html> ";


        return mensaje;
    }
}
