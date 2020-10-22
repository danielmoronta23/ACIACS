package ACIACS.encapsulaciones;

import ACIACS.util.EstatusModulo;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="Modulo_Prioridad")
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class ModuloPrioridad extends Modulo implements Serializable {
    private static final long serialVersionUID = 1L;

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
