package ACIACS.DTO;

import java.util.Date;

public class DtoTesting {
    private boolean mascarilla;
    private double temperatura;
    private Date fechaResgistro;
    private String idModulo;
    private boolean tipoModulo;
    private String cedulaPersona;
    private boolean estatus;

    public DtoTesting() {
    }

    public DtoTesting(boolean mascarilla, double temperatura, Date fechaResgistro, String idModulo, boolean tipoModulo, String cedulaPersona) {
        this.mascarilla = mascarilla;
        this.temperatura = temperatura;
        this.fechaResgistro = fechaResgistro;
        this.idModulo = idModulo;
        this.tipoModulo = tipoModulo;
        this.cedulaPersona = cedulaPersona;
    }

    public boolean isMascarilla() {
        return mascarilla;
    }

    public void setMascarilla(boolean mascarilla) {
        this.mascarilla = mascarilla;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public Date getFechaResgistro() {
        return fechaResgistro;
    }

    public void setFechaResgistro(Date fechaResgistro) {
        this.fechaResgistro = fechaResgistro;
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        this.idModulo = idModulo;
    }

    public boolean isTipoModulo() {
        return tipoModulo;
    }

    public void setTipoModulo(boolean tipoModulo) {
        this.tipoModulo = tipoModulo;
    }

    public String getCedulaPersona() {
        return cedulaPersona;
    }

    public void setCedulaPersona(String cedulaPersona) {
        this.cedulaPersona = cedulaPersona;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }
}
