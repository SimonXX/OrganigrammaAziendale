package ObserverGUI.view.style;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    public CustomTableCellRenderer() {
        setHorizontalAlignment(SwingConstants.CENTER); // Imposta l'allineamento al centro
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Imposta un bordo inferiore
        Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY);
        label.setBorder(border);

        return label;
    }
}