package ACIACS.encapsulaciones;

import ACIACS.util.EstatusAcceso;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class ListaDeAccesso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @ManyToOne
    private Persona persona;
    @ManyToOne
    private Empresa empresa;
    private EstatusAcceso estatus;
    @Column(name = "fecha_Inicio")
    private Date fechaInicio;
    @Column(name = "fecha_Fin")
    private Date fechaFin;

    public ListaDeAccesso() {
    }

    public ListaDeAccesso(Persona persona, Empresa empresa, EstatusAcceso estatus, Date fechaInicio, Date fechaFin) {
        this.persona = persona;
        this.empresa = empresa;
        this.estatus = estatus;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public EstatusAcceso getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusAcceso estatus) {
        this.estatus = estatus;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
