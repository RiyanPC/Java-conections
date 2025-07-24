package packages.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class Conection {

    Connection conexion;
    Properties props;

    public Conection() {
        props = new Properties();
        try {

            props.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo de propiedades: " + e);
        }
    }

    // Conexion Local
    public Connection getLocalConnection() {
        try {
            String url = props.getProperty("local.url");
            String user = props.getProperty("local.user");
            String password = props.getProperty("local.password");
            conexion = DriverManager.getConnection(url, user, password);
            return conexion;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }
    
    // Conexion remota
    public Connection getRemoteConnection() {
        try {
            String url = props.getProperty("remote.url");
            String user = props.getProperty("remote.user");
            String password = props.getProperty("remote.password");
            conexion = DriverManager.getConnection(url, user, password);
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
