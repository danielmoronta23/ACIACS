package ACIACS.encapsulaciones;

import ACIACS.util.EstatusModulo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
@Table(name="Modulo_Prioridad")
public class ModuloPrioridad extends Modulo implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "id_Modulo_Prioridad")
    @OneToMany
    private Set<TestingPriority> listaTestingPriority;

    public ModuloPrioridad() {
        super();
    }

    public ModuloPrioridad(EstatusModulo estatus, Set<TestingPriority> listaTestingPriority) {
        super(estatus);
        this.listaTestingPriority = listaTestingPriority;
    }

    public Set<TestingPriority> getListaTestingPriority() {
        return listaTestingPriority;
    }

    public void setListaTestingPriority(Set<TestingPriority> listaTestingPriority) {
        this.listaTestingPriority = listaTestingPriority;
    }
}
