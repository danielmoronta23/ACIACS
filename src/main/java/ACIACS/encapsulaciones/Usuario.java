package ACIACS.encapsulaciones;

import ACIACS.util.RolUsuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String correo;
    private String password;
    @Column(name = "rol_Usuario")
    private RolUsuario rolUsuario;
    @ManyToOne
    private Empresa empresa;

    public Usuario() {
    }

    public Usuario(String correo, String password, RolUsuario rolUsuario, Empresa empresa) {
        this.correo = correo;
        this.password = password;
        this.rolUsuario = rolUsuario;
        this.empresa = empresa;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
