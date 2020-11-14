package ACIACS.services;

import ACIACS.encapsulaciones.Sucursal;
import ACIACS.encapsulaciones.Testing;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ServicioSucursal extends ManejadorBD<Sucursal> {
    public ServicioSucursal() {
        super(Sucursal.class);
    }

    public List<Testing> pruebasRealizadasPorFecha(Date fecha, String sucursal) throws ParseException {
        DateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaF = fechaFormato.parse(fechaFormato.format(fecha));
        EntityManager entityManager = getEntityManager();
        String sql = "";
        sql += " SELECT distinct * FROM TESTING T INNER JOIN MODULO M ON T.MODULONORMAL_ID = M.ID OR T.MODULOPRIORIDAD_ID = M.ID";
        sql += " WHERE convert(T.fecha_Resgistro, date)=:fecha";
               sql+=" and M.SUCURSAL_ID =:sucursal ";
        Query query = entityManager.createNativeQuery(sql, Testing.class);
        query.setParameter("fecha", fechaF);
        query.setParameter("sucursal", sucursal);

        return query.getResultList();
    }

    public List<Testing> pruebasRealizadasPorMensuales(Date fecha, String sucursal) throws ParseException {
        DateFormat annoFormato = new SimpleDateFormat("yyyy");
        Date annoF = annoFormato.parse(annoFormato.format(fecha));
        String fechaInicio = "01/01/"+annoFormato.format(annoF);
        String fechaFin = "31/12/"+annoFormato.format(annoF);
        DateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaF = fechaFormato.parse(fechaFin);
        Date fechaI = fechaFormato.parse(fechaInicio);

        EntityManager entityManager = getEntityManager();
        String sql = "";
        sql += " SELECT distinct * FROM TESTING T INNER JOIN MODULO M ON T.MODULONORMAL_ID = M.ID OR T.MODULOPRIORIDAD_ID = M.ID";
        sql += " WHERE convert(T.fecha_Resgistro, date) BETWEEN :fechaInicio AND :fechaFin ";
        sql+=" and M.SUCURSAL_ID =:sucursal ";
        Query query = entityManager.createNativeQuery(sql, Testing.class);
        query.setParameter("fechaInicio", fechaI);
        query.setParameter("fechaFin", fechaF);

        query.setParameter("sucursal", sucursal);

        return query.getResultList();
    }

}
