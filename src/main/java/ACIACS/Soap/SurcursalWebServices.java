package ACIACS.Soap;

import ACIACS.DTO.DtoPersona;
import ACIACS.DTO.DtoTesting;
import ACIACS.encapsulaciones.*;
import ACIACS.logica.Controladora;

import javax.jws.WebMethod;
import javax.jws.WebService;


// Para acceder al wsdl -> http://localhost:7000/ws/ws?wsdl
@WebService
public class SurcursalWebServices {
    private Controladora controladora = Controladora.getInstance();

    @WebMethod
    public String holaMundo(String hola) {
        System.out.println("Ejecuntado en el servidor.");
        return "Hello World: " + hola;
    }

    @WebMethod
    public Object[] capacidadSucursal(String idSucursal) {
        Object[] resultado = new Object[3];
        Sucursal sucursal = controladora.buscarSucursal(idSucursal);
        if (sucursal != null) {
            resultado[0] = sucursal.getCapacidad();
            resultado[1] = sucursal.getPersonasDentro();
            return resultado;
        }
        resultado[0] = -1;
        resultado[1] = -1;
        return resultado;
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
    public Object[] agregarTest(DtoTesting dtoTesting) {
        Object[] resultado = new Object[3];
        Boolean estatus = false;
        String error = "";
        int cantPersonadentro = -1;
        Modulo aux = controladora.buscarModulo(dtoTesting.getIdModulo());
        if (dtoTesting.isTipoModulo()) {
            /**
             *  TESTING NORMAL.
             */

            if (aux != null) {
                if (aux instanceof ModuloNormal) {
                    estatus = controladora.agregarTesting(new TestingNormal(dtoTesting.isMascarilla(), dtoTesting.getTemperatura(), dtoTesting.getFechaResgistro(), (ModuloNormal) aux));
                } else {
                    error = "[Error] El tipo de modulo y el tipo de test no coinciden.";
                    error += "\n El Modulo y el Test deben ser Normales";
                }
            } else {
                error = "[Info] No se encontro el modulo con el ID: " + dtoTesting.getIdModulo();
            }
        } else {
            /**
             *  testing con prioridad.
              */
            DtoPersona dtoPersona = Controladora.getInstance().buscarPersonaDTO(dtoTesting.getCedulaPersona());
            if (dtoPersona != null) {
                if (aux != null) {
                    if (aux instanceof ModuloPrioridad) {
                        Persona persona = new Persona(dtoPersona.getCedula(), dtoPersona.getPrimerNombre(), dtoPersona.getSegundoNombre(), dtoPersona.getPrimerApellido(), dtoPersona.getSegundoApellido(), dtoPersona.getCorreo());
                        estatus = controladora.agregarTesting(new TestingPriority(dtoTesting.isMascarilla(), dtoTesting.getTemperatura(), dtoTesting.getFechaResgistro(), persona, (ModuloPrioridad) aux));
                    } else {
                        error = "[Error] El tipo de modulo y el tipo de test no coinciden.";
                        error += "\n El Modulo y el Test deben ser Prioritario.";
                    }
                } else {
                    error = "[Info] No se encontro el modulo con el ID: " + dtoTesting.getIdModulo();
                }
            }
        }
        if (estatus) {
            //cantidad de persona ++
            Sucursal sucursal = controladora.buscarSucursal(aux.getSucursal().getId());
            if (sucursal != null) {
                cantPersonadentro = sucursal.getPersonasDentro();
                if (dtoTesting.isEstatus()) {
                    //actulizando sucursal (solo cantidad de persona dentro)
                    cantPersonadentro = sucursal.getPersonasDentro() + 1;
                    sucursal.setPersonasDentro(cantPersonadentro);
                    controladora.actualizarSucursal(sucursal);
                }
            }
        }
        resultado[0] = estatus;
        resultado[1] = cantPersonadentro;
        resultado[2] = error;
        return resultado;
    }

    @WebMethod
    public boolean salidadDePersona(String idSucursal) {
        //descontarPersona
        Sucursal sucursal = controladora.buscarSucursal(idSucursal);
        if (sucursal != null) {
            sucursal.setPersonasDentro(sucursal.getPersonasDentro() - 1);
            controladora.actualizarSucursal(sucursal);
            return true;
        }
        return false;
    }


}
