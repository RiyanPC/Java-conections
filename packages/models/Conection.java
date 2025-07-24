package packages.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {

    Connection conexion;

    // Conexion Local
    public Connection getLocalConnection() {
        try {
            String myBD = "jdbc:mysql://localhost:3306/db_test?serverTimezone=UTC";
            conexion = DriverManager.getConnection(myBD, "root", "");
            return conexion;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }
    
    // Conexion remota
    public Connection getRemoteConnection() {
        try {
            String myBD = "jdbc:mysql://sql10.freesqldatabase.com:3306/sql10790842?serverTimezone=UTC";
            conexion = DriverManager.getConnection(myBD, "sql10790842", "whXigt84kn");
            return conexion;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }


    // Metodo principal para probar las conexiones
    public static void main(String[] args) {
        Conection con = new Conection();
        Connection local = con.getLocalConnection();
        if (local != null) {
            System.out.println("Conexión local exitosa");
        } else {
            System.out.println("Error en la conexión local");
        }

        Connection remote = con.getRemoteConnection();
        if (remote != null) {
            System.out.println("Conexión remota exitosa");
        } else {
            System.out.println("Error en la conexión remota");
        }
    }
}
