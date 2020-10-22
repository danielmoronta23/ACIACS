package ACIACS.encapsulaciones;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nombre;
    @OneToMany
    @JoinColumn(name = "id_Sucursal")
    private Set<Sucursal> listaSucursal;
    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
    private Set<Usuario> listaUsuario;

    public Empresa() {
    }
    public Empresa(String nombre, Set<Sucursal> listaSucursal) {
        this.nombre = nombre;
        this.listaSucursal = listaSucursal;
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

    public Set<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(Set<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
}
