package ACIACS.DTO;

import ACIACS.encapsulaciones.Ubicacion;

public class DtoSucursal {
    private String id;
    private Ubicacion ubicacion;

    public DtoSucursal() {
    }

    public DtoSucursal(String id, Ubicacion ubicacion) {
        this.id = id;
        this.ubicacion = ubicacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
}
