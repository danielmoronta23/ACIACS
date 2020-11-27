package ACIACS.encapsulaciones;

import ACIACS.util.EstatusModulo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Modulo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private EstatusModulo estatus;
    @JsonIgnore
    @ManyToOne
    private Sucursal sucursal;
    private boolean tipo;

    public Modulo() {
    }

    public Modulo(EstatusModulo estatus, Sucursal sucursal, boolean tipo) {
        this.estatus = estatus;
        this.sucursal = sucursal;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EstatusModulo getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusModulo estatus) {
        this.estatus = estatus;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }
}
