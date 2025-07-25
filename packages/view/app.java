package packages.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import packages.models.Conection;
import java.sql.Connection;
import packages.view.ComponentFactory;
import javax.swing.JTextField;

public class app extends JFrame {
    public app() {
        setTitle("Seleccione el tipo de conexión");
        setSize(500, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel titleLabel = ComponentFactory.createLabel("Acceder", 24, java.awt.Font.BOLD);
        JLabel secretLabel = ComponentFactory.createLabel("Ingrese la clave secreta: ", 14, java.awt.Font.BOLD);
        JLabel tipoConexionLabel = ComponentFactory.createLabel("Elija el tipo de conexión", 18, java.awt.Font.BOLD);
        JTextField secretKeyField = ComponentFactory.createTextField(20, 14);
        JButton localButton = ComponentFactory.createButton("Conexión Local", 16, java.awt.Font.PLAIN);
        JButton remoteButton = ComponentFactory.createButton("Conexión Remota", 16, java.awt.Font.PLAIN);

        
        JLabel resultLabel = new JLabel("");
        resultLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));

        // opcion local
        localButton.addActionListener(_ -> {
            String secretKey = secretKeyField.getText();
            if (secretKey == null || secretKey.isEmpty()) {
                resultLabel.setText("Clave no ingresada");
                return;
            }
            try {
                // Verificar desencriptación del usuario
                org.jasypt.util.text.BasicTextEncryptor textEncryptor = new org.jasypt.util.text.BasicTextEncryptor();
                textEncryptor.setPassword(secretKey);
                String userDecrypted = null;
                try {
                    userDecrypted = textEncryptor.decrypt("GIzoaqwF2m4n3e+tLBcWCA==");
                } catch (Exception ex) {
                    resultLabel.setText("Clave secreta incorrecta");
                    return;
                }
                Conection con = new Conection(secretKey);
                Connection conn = con.getLocalConnection();
                if (conn != null) {
                    resultLabel.setText("Conexión local exitosa");
                } else {
                    resultLabel.setText("Error en la conexión local");
                }
            } catch (Exception ex) {
                resultLabel.setText("Clave secreta incorrecta o error de desencriptación");
            }
        });

        // opcion remota
        remoteButton.addActionListener(_ -> {
            String secretKey = secretKeyField.getText();
            if (secretKey == null || secretKey.isEmpty()) {
                resultLabel.setText("Clave no ingresada");
                return;
            }
            try {
                // Verificar desencriptación del usuario remoto
                org.jasypt.util.text.BasicTextEncryptor textEncryptor = new org.jasypt.util.text.BasicTextEncryptor();
                textEncryptor.setPassword(secretKey);
                try {
                    textEncryptor.decrypt("82jU1aRl6ZXmdZerWtKEyai6T/4EE3aT");
                } catch (Exception ex) {
                    resultLabel.setText("Clave secreta incorrecta");
                    return;
                }
                Conection con = new Conection(secretKey);
                Connection conn = con.getRemoteConnection();
                if (conn != null) {
                    resultLabel.setText("Conexión remota exitosa");
                } else {
                    resultLabel.setText("Error en la conexión remota");
                }
            } catch (Exception ex) {
                resultLabel.setText("Clave secreta incorrecta o error de desencriptación");
            }

            


        });

        // Mostrar los componentes del panel
        panel.add(titleLabel);
        panel.add(secretLabel);
        panel.add(secretKeyField);
        panel.add(localButton);
        panel.add(remoteButton);
        panel.add(resultLabel);
        add(panel);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Correr la aplicación
            app ventana = new app();
            ventana.setVisible(true);

        });
    }
}
