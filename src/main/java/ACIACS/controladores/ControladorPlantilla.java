package ACIACS.controladores;

import ACIACS.encapsulaciones.*;
import ACIACS.logica.Controladora;
import ACIACS.servicios.ServicioEmpresa;
import ACIACS.servicios.ServicioModulo;
import ACIACS.servicios.ServicioSucursal;
import ACIACS.util.ControladorBase;
import ACIACS.util.EstatusAcceso;
import ACIACS.util.EstatusModulo;
import ACIACS.util.RolUsuario;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;
import javassist.expr.NewArray;

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
                    modelo.put("totalModulosNormalesActivos", new ServicioModulo().cantModulos(true,EstatusModulo.Activo));
                    modelo.put("totalModulosPrioritarioActivos", new ServicioModulo().cantModulos(false,EstatusModulo.Activo));
                    modelo.put("listaSucursal",new ServicioEmpresa().explorarTodo());
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
                System.out.println("\n Entrado a agregar Persona ...");
                Usuario usuario = null;
                Map<String, Object> modelo = new HashMap<>();
                String cedula = ctx.formParam("cedula");
                String nombre = ctx.formParam("nombre");
                String apellido = ctx.formParam("apellido");
                String correo = ctx.formParam("correo");
                if (ctx.cookie("usuario") != null) {
                    usuario = Controladora.getControladora().buscarUsuario(ctx.cookie("usuario"));
                }
                if (usuario != null && cedula != null) {
                    ListaDeAccesso listaDeAccesso;
                    Persona persona = Controladora.getControladora().buscarPersona(cedula);
                    if (persona != null) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(new Date());
                        calendar.add(Calendar.YEAR, 2);
                        listaDeAccesso = new ListaDeAccesso(persona, usuario.getEmpresa(), EstatusAcceso.Activo, new Date(), calendar.getTime());
                        System.out.println("Se pudo agregar a la personas ? " + Controladora.getControladora().agregarListaDeAcceso(listaDeAccesso));
                    } else {
                        Controladora.getControladora().agregarPersona(new Persona(cedula, nombre, "", apellido, "", correo));
                        Persona persona1 = Controladora.getControladora().buscarPersona(cedula);
                        if (persona1 != null) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(new Date());
                            calendar.add(Calendar.YEAR, 2);
                            listaDeAccesso = new ListaDeAccesso(persona1, usuario.getEmpresa(), EstatusAcceso.Activo, new Date(), calendar.getTime());
                            System.out.println("Se pudo agregar a la personas ? " + Controladora.getControladora().agregarListaDeAcceso(listaDeAccesso));
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
        });

    }
}
