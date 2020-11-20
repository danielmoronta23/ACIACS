package ACIACS.servicios;

import ACIACS.encapsulaciones.Empresa;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class ServicioEmpresa extends ManejadorBD<Empresa> {
    public ServicioEmpresa() {
        super(Empresa.class);
    }

    public Empresa buscarEmpresPorSucursal(String idSucursal) {
        EntityManager entityManager = getEntityManager();
        String sql = "";
        Date fechaActual = new Date();
        sql += " SELECT E from Empresa E INNER JOIN Sucursal S on E.id = S.id";
        sql += " WHERE S.id=:sucursal";
        Query query = entityManager.createQuery(sql, Empresa.class);
        query.setParameter("sucursal", idSucursal);
        List<Empresa> empresaList = query.getResultList();
        if (empresaList.size() > 0) {
            return empresaList.get(0);
        }
        return null;
    }
}
