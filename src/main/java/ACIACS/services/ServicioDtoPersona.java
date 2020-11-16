package ACIACS.services;

import ACIACS.DTO.DtoPersona;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ServicioDtoPersona extends ManejadorBD<DtoPersona> {
    public ServicioDtoPersona() {
        super(DtoPersona.class);
    }

    @Override
    public boolean crear(DtoPersona entidad) throws IllegalArgumentException, EntityExistsException, PersistenceException {
        return false;
    }

    @Override
    public boolean eliminar(Object entidadId) throws PersistenceException {
        return false;
    }

    @Override
    public boolean editar(DtoPersona entidad) throws PersistenceException {
        return false;
    }

    @Override
    public DtoPersona buscar(Object id) throws PersistenceException {
        EntityManager entityManager = getEntityManager();
        String sql = "";
        sql += " select new ACIACS.DTO.DtoPersona(P.cedula, P.primerNombre, P.segundoNombre, P.primerApellido, P.SegundoApellido,P.correo)";
        sql += " FROM Persona P";
        sql += " WHERE P.cedula = :c";
        Query query = entityManager.createQuery(sql, DtoPersona.class);
        query.setParameter("c", id);
        List<DtoPersona> aux = new ArrayList<>();
            aux = query.getResultList();
        if(aux.size()>0){
            return aux.get(0);
        }
        return null;
    }

    @Override
    public List<DtoPersona> explorarTodo() throws PersistenceException {
        return null;
    }
}
