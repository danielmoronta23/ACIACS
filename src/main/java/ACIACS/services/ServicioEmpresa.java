package ACIACS.services;

import ACIACS.encapsulaciones.Empresa;

public class ServicioEmpresa extends ManejadorBD<Empresa> {
    public ServicioEmpresa() {
        super(Empresa.class);
    }
}
