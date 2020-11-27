package ACIACS.servicios;

import ACIACS.encapsulaciones.Modulo;
import ACIACS.util.EstatusModulo;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ServicioModulo extends ManejadorBD<Modulo> {
    public ServicioModulo() {
        super(Modulo.class);
    }

    public int cantModulos(boolean tipo, EstatusModulo estatusModulo) {
        EntityManager entityManager = getEntityManager();
        String sql = "";
        sql += " SELECT count(M.id) from Modulo M";
        sql += " WHERE M.tipo=:tipo and M.estatus =:estatus";
        Query query = entityManager.createQuery(sql);
        query.setParameter("tipo", tipo);
        query.setParameter("estatus", estatusModulo);
        int posicionPrimerElemento = query.getFirstResult();
        int tamano = query.getResultList().size();
        if (tamano > 0) {
            return Integer.parseInt(query.getResultList().get(0).toString());
        }
        return 0;
    }

    public int cantModulos() {
        EntityManager entityManager = getEntityManager();
        String sql = "";
        sql += " SELECT count(M.id) from Modulo M";
        Query query = entityManager.createQuery(sql);
        int posicionPrimerElemento = query.getFirstResult();
        int tamano = query.getResultList().size();
        if (tamano > 0) {
            return Integer.parseInt(query.getResultList().get(0).toString());
        }
        return 0;
    }
}
