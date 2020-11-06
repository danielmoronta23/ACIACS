package ACIACS.encapsulaciones;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
@Table(name = "Testing_Priority")
public class TestingPriority extends Testing implements Serializable {
    private static final long serialVersionUID = 1L;
    @OneToOne
    private Persona persona;
    @ManyToOne
    private ModuloPrioridad moduloPrioridad;


    public TestingPriority() {
        super();
    }

    public TestingPriority(boolean mascarilla, double temperatura, Date fechaResgistro, Persona persona, ModuloPrioridad moduloPrioridad) {
        super(mascarilla, temperatura, fechaResgistro);
        this.persona = persona;
        this.moduloPrioridad = moduloPrioridad;
    }
    public Persona getPersonaPrioritaria() {
        return persona;
    }

    public void setPersonaPrioritaria(Persona persona) {
        this.persona = persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public ModuloPrioridad getModuloPrioridad() {
        return moduloPrioridad;
    }

    public void setModuloPrioridad(ModuloPrioridad moduloPrioridad) {
        this.moduloPrioridad = moduloPrioridad;
    }
}
