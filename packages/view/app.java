package packages.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;


public class app extends JFrame {
    public app() {
        setTitle("Seleccione el tipo de conexión");
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Por favor, seleccione el tipo de conexión:");
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        JButton localButton = new JButton("Conexión Local");
        JButton remoteButton = new JButton("Conexión Remota");

        panel.add(label);
        panel.add(localButton);
        panel.add(remoteButton);
        add(panel);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            app ventana = new app();
            ventana.setVisible(true);
        });
    }
}
