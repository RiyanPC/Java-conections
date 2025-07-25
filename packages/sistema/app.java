package packages.sistema; 

public class app{
    public static void main(String[] args) {
            System.out.println("Aplicación de Sistema Iniciada");
            javax.swing.SwingUtilities.invokeLater(() -> {
                // Crear la ventana principal
                packages.view.app ventana = new packages.view.app();
                ventana.setVisible(true);
            });
    }

}
