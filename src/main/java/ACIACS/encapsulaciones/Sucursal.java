package ACIACS.encapsulaciones;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Sucursal implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private String id;
   private Ubicacion ubicacion;
   @Column(name="personas_Dentro")
   private int personasDentro;
   @Column(name = "capacidad")
   private int capacidad;
   @OneToMany(mappedBy = "sucursal", fetch = FetchType.EAGER)
   private Set<Modulo> listaModulo = new HashSet<>();
   @ManyToOne
   private Empresa empresa;
   private Date fecha;

   public Sucursal() {

   }
   public Sucursal(Ubicacion ubicacion, int personasDentro, int capacidad, Empresa empresa) {
      this.ubicacion = ubicacion;
      this.personasDentro = personasDentro;
      this.capacidad = capacidad;
      this.empresa = empresa;
      this.fecha = new Date();
   }

   public Sucursal(String id, Ubicacion ubicacion, int capacidad) {
      this.id = id;
      this.ubicacion = ubicacion;
      this.capacidad = capacidad;
      this.personasDentro = 0;
      this.fecha = new Date();
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public Ubicacion getUbicacion() {
      return ubicacion;
   }

   public void setUbicacion(Ubicacion ubicacion) {
      this.ubicacion = ubicacion;
   }

   public Set<Modulo> getListaModulo() {
      return listaModulo;
   }

   public void setListaModulo(Set<Modulo> listaModulo) {
      this.listaModulo = listaModulo;
   }

   public int getPersonasDentro() {
      return personasDentro;
   }

   public void setPersonasDentro(int personasDentro) {
      this.personasDentro = personasDentro;
   }

   public int getCapacidad() {
      return capacidad;
   }

   public void setCapacidad(int capacidad) {
      this.capacidad = capacidad;
   }

   public Empresa getEmpresa() {
      return empresa;
   }

   public void setEmpresa(Empresa empresa) {
      this.empresa = empresa;
   }

   public Date getFecha() {
      return fecha;
   }

   public void setFecha(Date fecha) {
      this.fecha = fecha;
   }
}
