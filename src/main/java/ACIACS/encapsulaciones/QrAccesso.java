package ACIACS.encapsulaciones;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class QrAccesso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idAcceso;
    private String codigoQR;
    @ManyToOne
    @JoinColumn(name="id_Persona_Prioritaria")
    private PersonaPrioritaria personaPrioritaria;

    public QrAccesso() {
    }

    public QrAccesso(String codigoQR, PersonaPrioritaria personaPrioritaria) {
        this.codigoQR = codigoQR;
        this.personaPrioritaria = personaPrioritaria;
    }

    public String getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(String idAcceso) {
        this.idAcceso = idAcceso;
    }

    public String getCodigoQR() {
        return codigoQR;
    }

    public void setCodigoQR(String codigoQR) {
        this.codigoQR = codigoQR;
    }

    public PersonaPrioritaria getPersonaPrioritaria() {
        return personaPrioritaria;
    }

    public void setPersonaPrioritaria(PersonaPrioritaria personaPrioritaria) {
        this.personaPrioritaria = personaPrioritaria;
    }
}
