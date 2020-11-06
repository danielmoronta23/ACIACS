package ACIACS.services;

import ACIACS.encapsulaciones.Empresa;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;

public class ServicioEmpresa extends ManejadorBD<Empresa> {
    public ServicioEmpresa() {
        super(Empresa.class);
    }

    public Empresa buscarEmpresPorSucursal(String idSucursal) {
        EntityManager entityManager = getEntityManager();
        String sql = "";
        Date fechaActual = new Date();
        sql += " SELECT E from Empresa E INNER JOIN Sucursal S on E.id = S.id";
        Query query = entityManager.createQuery(sql, Empresa.class);
        query.setParameter("sucursal", idSucursal);
        int resultado = -1;
        resultado = query.getFirstResult();
        if (resultado > -1) {
            return (Empresa) query.getResultList().get(resultado);
        }
        return null;
    }
}
