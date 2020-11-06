package ACIACS.encapsulaciones;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Testing implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private boolean mascarilla;
    private double temperatura;
    @Column(name = "fecha_Resgistro")
    private Date fechaResgistro;

    public Testing() {
    }

    public Testing(boolean mascarilla, double temperatura, Date fechaResgistro) {
        this.mascarilla = mascarilla;
        this.temperatura = temperatura;
        this.fechaResgistro = fechaResgistro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isMascarilla() {
        return mascarilla;
    }

    public void setMascarilla(boolean mascarilla) {
        this.mascarilla = mascarilla;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public Date getFechaResgistro() {
        return fechaResgistro;
    }

    public void setFechaResgistro(Date fechaResgistro) {
        this.fechaResgistro = fechaResgistro;
    }
}
