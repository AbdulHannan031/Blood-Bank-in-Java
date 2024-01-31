import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class asks extends JFrame {
    JPanel panel1;
    JTable table;

    asks(Connection con) {
        String[] columnNames = {"ID","Name", "Contact", "Blood_Type", "Blood_Amount", "Location"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ask");

            while (resultSet.next()) {
                Object[] rowData = {resultSet.getInt("id"),resultSet.getString("Name"), resultSet.getString("Contact"), resultSet.getString("Bloodtype"), resultSet.getString("Bloodamount"), resultSet.getString("location")};
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

            // Add MouseListener to the JTable
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        int row = table.rowAtPoint(e.getPoint());
                        table.setRowSelectionInterval(row, row);

                        JPopupMenu popupMenu = new JPopupMenu();
                        JMenuItem approveItem = new JMenuItem("Approve");



                        approveItem.addActionListener(actionEvent -> {
                            int selectedRow = table.getSelectedRow();
                            if (selectedRow != -1) {
                                int nameToDelete =(Integer) table.getValueAt(selectedRow, 0);

                                try {
                                    // Create a new Statement and execute the DELETE query
                                    Statement deleteStatement = con.createStatement();
                                    String deleteQuery = "DELETE FROM ask WHERE id='" + nameToDelete + "'";
                                    int rowsDeleted = deleteStatement.executeUpdate(deleteQuery);

                                    if (rowsDeleted > 0) {
                                        // Remove the selected row from the JTable's model
                                        ((DefaultTableModel) table.getModel()).removeRow(selectedRow);
                                       JOptionPane.showMessageDialog(null,"Approved");
                                    } else {
                                        System.out.println("Error deleting from database.");
                                    }

                                    deleteStatement.close();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });




                        popupMenu.add(approveItem);



                        popupMenu.show(table, e.getX(), e.getY());
                    }
                }
            });

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
}
