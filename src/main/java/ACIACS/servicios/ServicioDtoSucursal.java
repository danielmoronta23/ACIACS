package ACIACS.servicios;

import ACIACS.DTO.DtoPersona;
import ACIACS.DTO.DtoSucursal;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class ServicioDtoSucursal extends ManejadorBD<DtoSucursal> {
    public ServicioDtoSucursal() {
        super(DtoSucursal.class);
    }

    @Override
    public boolean crear(DtoSucursal entidad) throws IllegalArgumentException, EntityExistsException, PersistenceException {
        return false;
    }

    @Override
    public boolean eliminar(Object entidadId) throws PersistenceException {
        return false;
    }

    @Override
    public boolean editar(DtoSucursal entidad) throws PersistenceException {
        return false;
    }

    @Override
    public DtoSucursal buscar(Object id) throws PersistenceException {
        return null;
    }

    @Override
    public List<DtoSucursal> explorarTodo() throws PersistenceException {
        return null;
    }
    public List<DtoSucursal> buscarSucursalesPorEmpresa(String idEmpresa) throws PersistenceException {
        EntityManager entityManager = getEntityManager();
        String sql = "";
        sql += " select new ACIACS.DTO.DtoSucursal(S.id,S.ubicacion)";
        sql += " FROM Sucursal S";
        sql += " WHERE S.id = :codigo";
        Query query = entityManager.createQuery(sql, DtoSucursal.class);
        query.setParameter("codigo", idEmpresa);
        return query.getResultList();
    }
}
