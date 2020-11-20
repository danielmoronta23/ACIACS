package ACIACS.servicios;

import ACIACS.encapsulaciones.Usuario;

public class ServicioUsuario extends ManejadorBD<Usuario>{
    public ServicioUsuario() {
        super(Usuario.class);
    }
}
