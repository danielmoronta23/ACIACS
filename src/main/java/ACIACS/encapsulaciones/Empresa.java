package ACIACS.encapsulaciones;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nombre;
    @OneToMany(mappedBy = "empresa",fetch = FetchType.EAGER)
    private Set<Sucursal> listaSucursal = new HashSet<Sucursal>();
    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
    private List<Usuario> listaUsuario;
    private String descripcion;
    @Column(name = "fecha_Ingreso")
    private Date fechaIngreso;
    @OneToMany(mappedBy = "empresa",fetch = FetchType.EAGER)
    private Set<ListaDeAccesso> listaDeAccessos  = new HashSet<ListaDeAccesso>();

    public Empresa() {
    }
    public Empresa(String nombre, Date fechaIngreso) {
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
    }



    public Empresa(String nombre, Set<Sucursal> listaSucursal, String descripcion) {
        this.nombre = nombre;
        this.listaSucursal = listaSucursal;
        this.descripcion = descripcion;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Sucursal> getListaSucursal() {
        return listaSucursal;
    }

    public void setListaSucursal(Set<Sucursal> listaSucursal) {
        this.listaSucursal = listaSucursal;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Set<ListaDeAccesso> getListaDeAccessos() {
        return listaDeAccessos;
    }

    public void setListaDeAccessos(Set<ListaDeAccesso> listaDeAccessos) {
        this.listaDeAccessos = listaDeAccessos;
    }
}
