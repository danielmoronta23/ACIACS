package ACIACS.encapsulaciones;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Persona_Prioritaria")
public class PersonaPrioritaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String cedula;
    @Column(name = "primer_Nombre")
    private String primerNombre;
    @Column(name = "segundo_Nombre")
    private String segundoNombre;
    @Column(name = "primer_Apellido")
    private String primerApellido;
    @Column(name = "Segundo_Aprellido")
    private String SegundoAprellido;
    @OneToMany(mappedBy = "personaPrioritaria", fetch = FetchType.LAZY)
    private Set<QrAccesso> listaQrAccesso;

    public PersonaPrioritaria() {
    }

    public PersonaPrioritaria(String cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoAprellido) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        SegundoAprellido = segundoAprellido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSegundoAprellido() {
        return SegundoAprellido;
    }

    public void setSegundoAprellido(String segundoAprellido) {
        SegundoAprellido = segundoAprellido;
    }

    public Set<QrAccesso> getListaQrAccesso() {
        return listaQrAccesso;
    }

    public void setListaQrAccesso(Set<QrAccesso> listaQrAccesso) {
        this.listaQrAccesso = listaQrAccesso;
    }
}
