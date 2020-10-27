package ACIACS.logica;

import ACIACS.encapsulaciones.Modulo;
import ACIACS.encapsulaciones.Sucursal;
import ACIACS.services.*;

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
    private final ServicioTestingPriority servicioTestingPriority = new ServicioTestingPriority();
    private final ServicioUsuario servicioUsuario = new ServicioUsuario();

    private Controladora() {
        consultarPersonaPrioridad("", "");

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

    public Modulo buscarModulo(String idCodigo){
        return servicioModulo.buscar(idCodigo);
    }

    public Sucursal buscarSucursal(String idSucursal) {
        return servicioSucursal.buscar(idSucursal);
    }

    public boolean consultarPersonaPrioridad(String idPersona, String idEmpresa) {
        return servicioListaDeAccesso.consultarPrioridad(idPersona, idEmpresa);

    }
}
