package packages.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

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


    // Permite seleccionar el tipo de conexión
    public Connection getConnectionByType(String tipo) {
        if ("local".equalsIgnoreCase(tipo)) {
            return getLocalConnection();
        } else if ("remota".equalsIgnoreCase(tipo) || "remote".equalsIgnoreCase(tipo)) {
            return getRemoteConnection();
        } else {
            System.out.println("Tipo de conexión no válido. Usa 'local' o 'remota'.");
            return null;
        }
    }

    // Metodo principal para probar la selección de conexión
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Selecciona el tipo de conexión (local/remota): ");
        String tipo = scanner.nextLine();
        Conection con = new Conection();
        Connection conn = con.getConnectionByType(tipo);
        if (conn != null) {
            System.out.println("Conexión " + tipo + " exitosa");
        } else {
            System.out.println("Error en la conexión " + tipo);
        }
        scanner.close();
    }
}
