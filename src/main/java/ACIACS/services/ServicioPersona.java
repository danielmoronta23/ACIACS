package ACIACS.services;

import ACIACS.encapsulaciones.Persona;

public class ServicioPersona extends ManejadorBD<Persona> {
    public ServicioPersona() {
        super(Persona.class);
    }
}
