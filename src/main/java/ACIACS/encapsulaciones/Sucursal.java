package ACIACS.encapsulaciones;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Sucursal implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private String id;
   private Ubicacion ubicacion;
   @OneToMany
   private Set<Modulo> listaModulo;

   public Sucursal() {

   }
   public Sucursal(Ubicacion ubicacion, Set<Modulo> listaModulo) {
      this.ubicacion = ubicacion;
      this.listaModulo = listaModulo;
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
}
