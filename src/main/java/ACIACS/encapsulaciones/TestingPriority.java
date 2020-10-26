package ACIACS.encapsulaciones;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
@Table(name = "Testing_Priority")
public class TestingPriority extends Testing implements Serializable {
    private static final long serialVersionUID = 1L;
    @OneToOne
    private Persona persona;

    public TestingPriority() {
    }

    public TestingPriority(boolean mascarilla, double temperatura, Persona persona) {
        super(mascarilla, temperatura);
        this.persona = persona;
    }

    public Persona getPersonaPrioritaria() {
        return persona;
    }

    public void setPersonaPrioritaria(Persona persona) {
        this.persona = persona;
    }
}
