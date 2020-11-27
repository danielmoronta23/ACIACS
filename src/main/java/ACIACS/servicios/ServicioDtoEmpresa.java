package ACIACS.servicios;

import ACIACS.DTO.DtoEmpresa;
import ACIACS.DTO.DtoSucursal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class ServicioDtoEmpresa extends ManejadorBD<DtoEmpresa> {
    public ServicioDtoEmpresa() {
        super(DtoEmpresa.class);
    }

    public List<DtoEmpresa> buscarTodos() throws PersistenceException {
        EntityManager entityManager = getEntityManager();
        String sql = "";
        sql += " select new ACIACS.DTO.DtoEmpresa(E.id,E.nombre)";
        sql += " FROM Empresa E";
        Query query = entityManager.createQuery(sql, DtoEmpresa.class);
        return query.getResultList();
    }
}
