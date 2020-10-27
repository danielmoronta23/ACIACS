package ACIACS.services;

import ACIACS.encapsulaciones.ListaDeAccesso;
import ACIACS.util.EstatusAcceso;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;

public class ServicioListaDeAccesso extends ManejadorBD<ListaDeAccesso> {
    public ServicioListaDeAccesso() {
        super(ListaDeAccesso.class);
    }

    public boolean consultarPrioridad(String idPersona,  String idEmpresa) {
        EntityManager entityManager = getEntityManager();
        String sql = "";
        Date fechaActual = new Date();
        sql+= " SELECT l from ListaDeAccesso l ";
        sql+=" WHERE l.persona.cedula =:cedula";
        sql+=" and l.estatus =:estatus";
        sql+=" and  l.fechaInicio<=:fecha and l.fechaFin>=:fecha";
        sql+=" and l.empresa.id= :empresa";
        Query query = entityManager.createQuery(sql, ListaDeAccesso.class);
        query.setParameter("fecha", fechaActual);
        query.setParameter("estatus", EstatusAcceso.Activo);
        query.setParameter("cedula", idPersona);
        query.setParameter("empresa", idEmpresa);

        if(query.getResultList().size()>0){
            return true;
        }

        return false;
    }
}
