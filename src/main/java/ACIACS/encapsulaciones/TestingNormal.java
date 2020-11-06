package ACIACS.encapsulaciones;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Entity
public class TestingNormal  extends Testing implements Serializable {
    private static final long serialVersionUID = 1L;
    @ManyToOne
    private ModuloNormal moduloNormal;

    public TestingNormal() {
        super();
    }

    public TestingNormal(boolean mascarilla, double temperatura, Date fechaResgistro, ModuloNormal moduloNormal) {
        super(mascarilla, temperatura, fechaResgistro);
        this.moduloNormal = moduloNormal;
    }

    public ModuloNormal getModuloNormal() {
        return moduloNormal;
    }

    public void setModuloNormal(ModuloNormal moduloNormal) {
        this.moduloNormal = moduloNormal;
    }
}
