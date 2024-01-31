import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class managedonors extends JFrame {
    JPanel panel1;
    JTable table;

    managedonors(Connection con) {
        String[] columnNames = {"ID", "Name", "Blood_Group", "Contact", "Address", "Disease"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM donor");

            while (resultSet.next()) {
                Object[] rowData = {resultSet.getInt("donor_id"), resultSet.getString("donor_name"), resultSet.getString("blood_group"), resultSet.getString("donor_contact_no"), resultSet.getString("donor_address"), resultSet.getString("disease")};
                model.addRow(rowData);
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Color color = new Color(66, 4, 2);
        SwingUtilities.invokeLater(() -> {
            // Create JTable and set the model
            table = new JTable(model);
            table.setBackground(color);
            table.setForeground(Color.WHITE);

            // Create popup menu
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem deleteMenuItem = new JMenuItem("Delete");
            deleteMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int donorId = (int) table.getValueAt(selectedRow, 0);
                        deleteDonorFromDatabase(con, donorId);
                        model.removeRow(selectedRow);
                    }
                }
            });
            popupMenu.add(deleteMenuItem);

            // Attach the popup menu to the table
            table.setComponentPopupMenu(popupMenu);

            panel1 = new JPanel(new BorderLayout());
            panel1.setBackground(color);
            panel1.add(new JScrollPane(table), BorderLayout.CENTER);

            add(panel1);

            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    dispose();
                }
            });
        });
    }

    private void deleteDonorFromDatabase(Connection con, int donorId) {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("DELETE FROM donor WHERE donor_id = " + donorId);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
