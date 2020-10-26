package ACIACS.encapsulaciones;

import ACIACS.util.EstatusModulo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
@Table(name="Modulo_Normal")
public class ModuloNormal extends Modulo implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "id_Modulo_Normal")
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
