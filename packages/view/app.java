package packages.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import packages.models.Conection;
import java.sql.Connection;

public class app extends JFrame {
    public app() {
        setTitle("Seleccione el tipo de conexión");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Por favor, seleccione el tipo de conexión:");
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        JButton localButton = new JButton("Conexión Local");
        JButton remoteButton = new JButton("Conexión Remota");
        
        JLabel resultLabel = new JLabel("");
        resultLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));

        // opcion local
        localButton.addActionListener(_ -> {
            String secretKey = javax.swing.JOptionPane.showInputDialog(this, "Ingrese la clave secreta:");
            if (secretKey == null || secretKey.isEmpty()) {
                resultLabel.setText("Clave no ingresada");
                return;
            }
            try {
                // Verificar desencriptación del usuario
                org.jasypt.util.text.BasicTextEncryptor textEncryptor = new org.jasypt.util.text.BasicTextEncryptor();
                textEncryptor.setPassword(secretKey);
                String userDecrypted = null;
                String userProp = packages.models.Conection.class.getResource("/../application.properties") != null ? "local.user" : "root";
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
            String secretKey = javax.swing.JOptionPane.showInputDialog(this, "Ingrese la clave secreta:");
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
        panel.add(label);
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
