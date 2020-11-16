package ACIACS.encapsulaciones;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Ubicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "longitud")
    private String longitud;
    @Column(name = "latitud")
    private String latitud;
    private String dirrecion;

    public Ubicacion() {
    }

    public Ubicacion(String longitud, String latitud, String dirrecion) {
        this.longitud = longitud;
        this.latitud = latitud;
        this.dirrecion = dirrecion;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getDirrecion() {
        return dirrecion;
    }

    public void setDirrecion(String dirrecion) {
        this.dirrecion = dirrecion;
    }
}
