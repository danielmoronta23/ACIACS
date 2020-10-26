package ACIACS.encapsulaciones;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Persona")
public class Persona {
    @Id
    @Column(name = "Cedula", length = 13)
    private String cedula;
    @Column(name = "primer_Nombre")
    private String primerNombre;
    @Column(name = "segundo_Nombre")
    private String segundoNombre;
    @Column(name = "primer_Apellido")
    private String primerApellido;
    @Column(name = "Segundo_Apellido")
    private String SegundoApellido;
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    private Set<ListaDeAccesso> listaListaDeAccesso;

    public Persona() {
    }

    public Persona(String cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        SegundoApellido = segundoApellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return SegundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        SegundoApellido = segundoApellido;
    }

    public Set<ListaDeAccesso> getListaQrAccesso() {
        return listaListaDeAccesso;
    }

    public void setListaQrAccesso(Set<ListaDeAccesso> listaListaDeAccesso) {
        this.listaListaDeAccesso = listaListaDeAccesso;
    }
}
