package ACIACS.services;

import ACIACS.encapsulaciones.Usuario;

public class ServicioUsuario extends ManejadorBD<Usuario>{
    public ServicioUsuario() {
        super(Usuario.class);
    }
}
