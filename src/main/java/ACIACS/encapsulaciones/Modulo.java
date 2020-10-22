package ACIACS.encapsulaciones;

import ACIACS.util.EstatusModulo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Modulo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private EstatusModulo estatus;

    public Modulo() {
    }

    public Modulo(EstatusModulo estatus) {
        this.estatus = estatus;
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
}
