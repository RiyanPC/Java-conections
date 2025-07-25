package packages.view;

import javax.swing.*;
import java.awt.*;

public class ComponentFactory {
    public static JButton createButton(String text, int fontSize, int style) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", style, fontSize));
        return button;
    }

    public static JLabel createLabel(String text, int fontSize, int style) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", style, fontSize));
        return label;
    }
    public static JTextField createTextField(int columns, int fontSize) {
        JTextField field = new JTextField(columns);
        field.setFont(new Font("Arial", Font.PLAIN, fontSize));
        return field;
    }
}
