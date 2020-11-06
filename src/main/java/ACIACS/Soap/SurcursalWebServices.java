package ACIACS.Soap;

import ACIACS.encapsulaciones.*;
import ACIACS.logica.Controladora;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class SurcursalWebServices {
    private Controladora controladora = Controladora.getInstance();

    @WebMethod
    public String holaMundo(String hola) {
        System.out.println("Ejecuntado en el servidor.");
        return "Hello World: " + hola;
    }

    @WebMethod
    public Sucursal iniciarSistema(String idSucursal) {
        return controladora.buscarSucursal(idSucursal);
    }

    @WebMethod
    public boolean consultarPrioridad(String idPersona, String idSucural) {
        Empresa aux = null;
        aux = controladora.buscarEmpresaBySucursal(idSucural);
        if (aux != null) {
            return controladora.consultarPersonaPrioridad(idPersona, aux.getId());
        }
        return false;
    }
    @WebMethod
    public boolean agregarTest(int tipoTest, TestingNormal testingNormal, TestingPriority testingPriority){
        if(tipoTest==0){
            // testing normal
            controladora.agregarTesting(testingNormal);
        }else if(tipoTest==1){
            // testing con prioridad
            controladora.agregarTesting(testingPriority);
        }
        return false;
    }
    @WebMethod
    public boolean actulizarSucursal(Sucursal sucursal){
       return controladora.actualizarSucursal(sucursal);
    }
}
