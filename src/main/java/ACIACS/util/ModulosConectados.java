package ACIACS.util;

import org.eclipse.jetty.websocket.api.Session;

public class ModulosConectados {
    private String idModulo;
    private String idSucursal;
    private String idEmpresa;
    private Session sesion;

    public ModulosConectados() {
    }

    public ModulosConectados(String idModulo, String idSucursal, String idEmpresa, Session sesion) {
        this.idModulo = idModulo;
        this.idSucursal = idSucursal;
        this.idEmpresa = idEmpresa;
        this.sesion = sesion;
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        this.idModulo = idModulo;
    }

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Session getSesion() {
        return sesion;
    }

    public void setSesion(Session sesion) {
        this.sesion = sesion;
    }
}
