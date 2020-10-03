package ACIACS.services;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private String URL = "jdbc:h2:tcp://localhost/~/ACIACS-BD";
    private static ConexionDB conexionBD;
    private static Server tcp;
    private static Server webServer;


    public ConexionDB() throws SQLException {
        registroDriver();
        InciarBD();
    }

    public static ConexionDB getInstance() throws SQLException {
        if(conexionBD==null){
            conexionBD = new ConexionDB();
        }
        return conexionBD;
    }
    public static void InciarBD() throws SQLException {
        //subiendo en modo servidor H2
        tcp = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-tcpDaemon", "-ifNotExists").start();
        String status = Server.createWebServer( "-webPort", "9091", "-webAllowOthers", "-webDaemon").start().getStatus();
        System.out.println("Status Web: "+status);

    }
    public static void detenerBD() throws SQLException {
        tcp.stop();
    }

    private void registroDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, "sa", "");
        } catch (SQLException ex) {
        }
        return con;
    }


}
