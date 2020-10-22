package ACIACS.encapsulaciones;

import ACIACS.util.EstatusModulo;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="Modulo_Normal")
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class ModuloNormal extends Modulo implements Serializable {
    private static final long serialVersionUID = 1L;

    @OneToMany
    private Set<Testing> listaTesting;

    public ModuloNormal() {
        super();
    }
    public ModuloNormal(EstatusModulo estatus, Set<Testing> listaTesting) {
        super(estatus);
        this.listaTesting = listaTesting;
    }

    public Set<Testing> getListaTesting() {
        return listaTesting;
    }

    public void setListaTesting(Set<Testing> listaTesting) {
        this.listaTesting = listaTesting;
    }
}
