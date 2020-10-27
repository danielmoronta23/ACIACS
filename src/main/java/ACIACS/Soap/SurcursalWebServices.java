package ACIACS.Soap;

import ACIACS.encapsulaciones.Sucursal;
import ACIACS.logica.Controladora;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class SurcursalWebServices {
    private Controladora controladora = Controladora.getInstance();
    @WebMethod
    public String holaMundo(String hola){
        System.out.println("Ejecuntado en el servidor.");
        return "Hello World: "+hola;
    }
    @WebMethod
    public Sucursal iniciarSistema(String idSucursal){
        return controladora.buscarSucursal(idSucursal);
    }
    public boolean consultarPrioridad(String idPersona, String idEmpresa){
        return controladora.consultarPersonaPrioridad(idPersona, idEmpresa);
    }

}
