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
    private PersonaPrioritaria personaPrioritaria;

    public TestingPriority() {
    }

    public TestingPriority(boolean mascarilla, double temperatura, PersonaPrioritaria personaPrioritaria) {
        super(mascarilla, temperatura);
        this.personaPrioritaria = personaPrioritaria;
    }

    public PersonaPrioritaria getPersonaPrioritaria() {
        return personaPrioritaria;
    }

    public void setPersonaPrioritaria(PersonaPrioritaria personaPrioritaria) {
        this.personaPrioritaria = personaPrioritaria;
    }
}
