package ACIACS.logica;

import ACIACS.encapsulaciones.*;
import ACIACS.services.*;
import ACIACS.util.EstadisticaVisitas;
import ACIACS.util.EstatusModulo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controladora {
    private static Controladora controladora;
    private final ServicioEmpresa servicioEmpresa = new ServicioEmpresa();
    private final ServicioModulo servicioModulo = new ServicioModulo();
    private final ServicioModuloNormal servicioModuloNormal = new ServicioModuloNormal();
    private final ServicioModuloPrioritario servicioModuloPrioritario = new ServicioModuloPrioritario();
    private final ServicioPersona servicioPersona = new ServicioPersona();
    private final ServicioListaDeAccesso servicioListaDeAccesso = new ServicioListaDeAccesso();
    private final ServicioSucursal servicioSucursal = new ServicioSucursal();
    private final ServicioTesting servicioTesting = new ServicioTesting();
    private final ServicioTestingNormal servicioTestingNormal = new ServicioTestingNormal();
    private final ServicioTestingPriority servicioTestingPriority = new ServicioTestingPriority();
    private final ServicioUsuario servicioUsuario = new ServicioUsuario();

    private Controladora() {
    }

    public static Controladora getInstance() {
        if (controladora == null) {
            controladora = new Controladora();
        }
        return controladora;
    }

    public static Controladora getControladora() {
        return controladora;
    }

    public Modulo buscarModulo(String idCodigo) {
        return servicioModulo.buscar(idCodigo);
    }

    public Sucursal buscarSucursal(String idSucursal) {
        return servicioSucursal.buscar(idSucursal);
    }

    public boolean consultarPersonaPrioridad(String idPersona, String idEmpresa) {
        return servicioListaDeAccesso.consultarPrioridad(idPersona, idEmpresa);
    }

    public Empresa buscarEmpresaBySucursal(String sucursal) {
        return servicioEmpresa.buscarEmpresPorSucursal(sucursal);
    }

    public boolean agregarTesting(Testing testing) {
        return servicioTesting.crear(testing);
    }

    public boolean actualizarSucursal(Sucursal sucursal) {
        return servicioSucursal.editar(sucursal);
    }

    public boolean agregarModulo(Modulo modulo) {
        return servicioModulo.crear(modulo);
    }

    public boolean agregarEmpresa(Empresa empresa) {
        return servicioEmpresa.crear(empresa);
    }

    public boolean agregarSucursal(Sucursal sucursal) {
        return servicioSucursal.crear(sucursal);
    }

    public Empresa buscarEmpresa(String id) {
        return servicioEmpresa.buscar(id);
    }

    private List<Testing> pruebasRealizadasHoy(String sucursal) throws ParseException {
        return servicioSucursal.pruebasRealizadasPorFecha(new Date(), sucursal);
    }

    public boolean agregarPersona(Persona persona){
        return servicioPersona.crear(persona);
    }
    public boolean editarPersona(Persona persona){
        return servicioPersona.editar(persona);
    }
    public boolean autenticarUsuario(String correo, String passowerd){
        Usuario aux = servicioUsuario.buscar(correo);
        if(aux!=null){
            if(aux.getPassword().equals(passowerd)){
                return true;
            }
        }
        return false;
    }
    public void datosPruebas() throws ParseException {
        agregarEmpresa(new Empresa("PEPE", "PEPE"));
        agregarSucursal(new Sucursal(new Ubicacion(), 0, 100, buscarEmpresa("1")));
        agregarModulo(new ModuloNormal(EstatusModulo.Activo, buscarSucursal("1")));
        agregarModulo(new ModuloPrioridad(EstatusModulo.Activo, buscarSucursal("1")));

        TestingNormal aux = new TestingNormal(false, 100, new Date(), servicioModuloNormal.buscar("1"));
        agregarTesting(aux);
        TestingNormal aux1 = new TestingNormal(false, 100, new Date(), servicioModuloNormal.buscar("1"));
        agregarTesting(aux1);

        Persona persona = new Persona("402-1409395-3","Daniel ", "","P","Moronta");
        agregarPersona(persona);
        TestingPriority aux2 = new TestingPriority(false, 39, new Date(), servicioPersona.buscar("402-1409395-3"), servicioModuloPrioritario.buscar("2"));
        agregarTesting(aux2);
        Sucursal sucursal = buscarSucursal("1");
        sucursal.setPersonasDentro(20);
        actualizarSucursal(sucursal);
    }

    public EstadisticaVisitas pruebasRealizadasPorHora(String idSucursal) throws ParseException {
        int[] visitaNormalesAceptadas = new int[24];
        int visitaNormalesAceptadasTotal = 0;
        int[] visitaNormalesDenegadas = new int[24];
        int visitaNormalesDenegadasTotal = 0;
        int[] visitaPrioritariaAceptada = new int[24];
        int visitaPrioritariaAceptadaTotal = 0;
        int[] visitaPrioritariaDenegada = new int[24];
        int visitaPrioritariaDenegadaTotal = 0;
        DateFormat hourFormat = new SimpleDateFormat("HH");
        int i;
        for (Testing t : pruebasRealizadasHoy(idSucursal)) {
            i = Integer.parseInt(hourFormat.format(t.getFechaResgistro()));
            if (t instanceof TestingNormal) {
                if (verificarTesting(t)) {
                    visitaNormalesAceptadas[i] += 1;
                    visitaNormalesAceptadasTotal += 1;
                } else {
                    visitaNormalesDenegadas[i] += 1;
                    visitaNormalesDenegadasTotal += 1;
                }
            } else if (t instanceof TestingPriority) {
                if (verificarTesting(t)) {
                    visitaPrioritariaAceptada[i] += 1;
                    visitaPrioritariaAceptadaTotal += 1;

                } else {
                    visitaPrioritariaDenegada[i] += 1;
                    visitaPrioritariaDenegadaTotal += 1;
                }
            }
        }

        Map<String, Object> visitaNormalesAceptadas1 = new HashMap();
        visitaNormalesAceptadas1.put("Total", visitaNormalesAceptadasTotal);
        visitaNormalesAceptadas1.put("PorHora", visitaNormalesAceptadas);

        Map<String, Object> visitaNormalesDenegadas1 = new HashMap();
        visitaNormalesDenegadas1.put("Total", visitaNormalesDenegadasTotal);
        visitaNormalesDenegadas1.put("PorHora", visitaNormalesDenegadas);

        Map<String, Object> visitaPrioritariaAceptada1 = new HashMap();
        visitaPrioritariaAceptada1.put("Total", visitaPrioritariaAceptadaTotal);
        visitaPrioritariaAceptada1.put("PorHora", visitaPrioritariaAceptada);

        Map<String, Object> visitaPrioritariaDenegada1 = new HashMap();
        visitaPrioritariaDenegada1.put("Total", visitaPrioritariaDenegadaTotal);
        visitaPrioritariaDenegada1.put("PorHora", visitaPrioritariaDenegada);
        return new EstadisticaVisitas(visitaNormalesAceptadas1, visitaNormalesDenegadas1, visitaPrioritariaAceptada1, visitaPrioritariaDenegada1);

    }

    public boolean verificarTesting(Testing testing) {
        if (testing.getTemperatura() > 38 || testing.getMascarilla() == false) {
            return false;
        }
        return true;

    }

    public EstadisticaVisitas estadisticaVisitasPorMeses(String idSucursal) throws ParseException {
        int[] visitaNormalesAceptadas = new int[12];
        int visitaNormalesAceptadasTotal = 0;
        int[] visitaNormalesDenegadas = new int[12];
        int visitaNormalesDenegadasTotal = 0;
        int[] visitaPrioritariaAceptada = new int[12];
        int visitaPrioritariaAceptadaTotal = 0;
        int[] visitaPrioritariaDenegada = new int[12];
        int visitaPrioritariaDenegadaTotal = 0;
        DateFormat hourFormat = new SimpleDateFormat("MM");
        int i;
        for (Testing t : servicioSucursal.pruebasRealizadasPorMensuales(new Date(), idSucursal)) {
            i = Integer.parseInt(hourFormat.format(t.getFechaResgistro()));
            if (t instanceof TestingNormal) {
                if (verificarTesting(t)) {
                    visitaNormalesAceptadas[i - 1] += 1;
                    visitaNormalesAceptadasTotal += 1;
                } else {
                    visitaNormalesDenegadas[i - 1] += 1;
                    visitaNormalesDenegadasTotal += 1;
                }
            } else if (t instanceof TestingPriority) {
                if (verificarTesting(t)) {
                    visitaPrioritariaAceptada[i - 1] += 1;
                    visitaPrioritariaAceptadaTotal += 1;

                } else {
                    visitaPrioritariaDenegada[i - 1] += 1;
                    visitaPrioritariaDenegadaTotal += 1;
                }
            }
        }

        Map<String, Object> visitaNormalesAceptadas1 = new HashMap();
        visitaNormalesAceptadas1.put("Total", visitaNormalesAceptadasTotal);
        visitaNormalesAceptadas1.put("PorMes", visitaNormalesAceptadas);

        Map<String, Object> visitaNormalesDenegadas1 = new HashMap();
        visitaNormalesDenegadas1.put("Total", visitaNormalesDenegadasTotal);
        visitaNormalesDenegadas1.put("PorMes", visitaNormalesDenegadas);

        Map<String, Object> visitaPrioritariaAceptada1 = new HashMap();
        visitaPrioritariaAceptada1.put("Total", visitaPrioritariaAceptadaTotal);
        visitaPrioritariaAceptada1.put("PorMes", visitaPrioritariaAceptada);

        Map<String, Object> visitaPrioritariaDenegada1 = new HashMap();
        visitaPrioritariaDenegada1.put("Total", visitaPrioritariaDenegadaTotal);
        visitaPrioritariaDenegada1.put("PorMes", visitaPrioritariaDenegada);
        return new EstadisticaVisitas(visitaNormalesAceptadas1, visitaNormalesDenegadas1, visitaPrioritariaAceptada1, visitaPrioritariaDenegada1);

    }

    public List<Sucursal> listaSucursalesPorEmpresa(String empresa){
        Empresa aux = servicioEmpresa.buscar(empresa);
        if(aux!=null){
            return (List<Sucursal>) aux.getListaSucursal();
        }
        return null;
    }
    public List<Empresa> listaEmpresas(){
        return servicioEmpresa.explorarTodo();
    }
    public Object cantidadDePersonaEnSucursal(String idSucursal){
        int[] cant = new int[2];

        Sucursal sucursal = servicioSucursal.buscar(idSucursal);
        if(sucursal!=null) {
            cant[0] = sucursal.getCapacidad();
            cant[1] = sucursal.getPersonasDentro();

        }
        return cant;
    }
}

