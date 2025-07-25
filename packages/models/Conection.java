package packages.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import org.jasypt.util.text.BasicTextEncryptor;


public class Conection {

    Connection conexion;
    Properties props;

    private String secretKey;

    public Conection(String secretKey) {
        this.secretKey = secretKey;
        props = new Properties();
        try {
            props.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo de propiedades: " + e);
        }
    }

    // Desencripta si el valor está en formato ENC(...)
    private String decryptIfNeeded(String value) {
        if (value != null && value.startsWith("ENC(") && value.endsWith(")")) {
            String enc = value.substring(4, value.length() - 1);
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword(secretKey);
            return textEncryptor.decrypt(enc);
        }
        return value;
    }

    // Conexion Local
    public Connection getLocalConnection() {
        try {
            String url = props.getProperty("local.url");
            String user = decryptIfNeeded(props.getProperty("local.user"));
            String password = decryptIfNeeded(props.getProperty("local.password"));
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
            String user = decryptIfNeeded(props.getProperty("remote.user"));
            String password = decryptIfNeeded(props.getProperty("remote.password"));
            conexion = DriverManager.getConnection(url, user, password);
            return conexion;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }



    // Probar las conexiones
    // Permite seleccionar el tipo de conexión Local o Remota
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

    // Revisa la conexión
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la clave secreta para desencriptar: ");
        String secretKey = scanner.nextLine();
        System.out.print("Selecciona el tipo de conexión (local/remota): ");
        String tipo = scanner.nextLine();
        Conection con = new Conection(secretKey);
        Connection conn = con.getConnectionByType(tipo);
        if (conn != null) {
            System.out.println("Conexión " + tipo + " exitosa");
        } else {
            System.out.println("Error en la conexión " + tipo);
        }
        scanner.close();
    }
}
