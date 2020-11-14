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

    @OneToMany(mappedBy = "moduloNormal", fetch = FetchType.LAZY)
    private Set<TestingNormal> listaTesting;

    public ModuloNormal() {
        super();
    }
    public ModuloNormal(EstatusModulo estatus, Sucursal sucursal) {
        super(estatus, sucursal);
    }

    public ModuloNormal(EstatusModulo estatus, Sucursal sucursal, Set<TestingNormal> listaTesting) {
        super(estatus, sucursal);
        this.listaTesting = listaTesting;
    }

    public Set<TestingNormal> getListaTesting() {
        return listaTesting;
    }

    public void setListaTesting(Set<TestingNormal> listaTesting) {
        this.listaTesting = listaTesting;
    }
}
