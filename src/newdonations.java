import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class newdonations extends JFrame {
    JPanel panel1 = new JPanel();
    JTable table;

    newdonations(Connection con) {
        String[] columnNames = {"Date", "Blood_Bank", "Blood_Type", "Contact"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM donate WHERE flag IS NULL");


            while (resultSet.next()) {
                Object[] rowData = {resultSet.getString("date_of_donation"), resultSet.getString("blood_bank_name"), resultSet.getString("bloodgroup"), resultSet.getString("contact")};
                model.addRow(rowData);

                // Update flag to 1 for the current row in the database
                int id = resultSet.getInt("donate_id");

                updateFlagTo1(con, id);
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

    // Update flag to 1 for the specified row in the database
    private void updateFlagTo1(Connection con, int id) {
        try {

            Statement updateStatement = con.createStatement();
            String updateQuery = "UPDATE donate SET flag = 1 WHERE donate_id = " + id;
            updateStatement.executeUpdate(updateQuery);
            updateStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
