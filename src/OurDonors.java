import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OurDonors extends JFrame {
    private JPanel panel1;
    private JTable table;

    public OurDonors(Connection con) {
        // Fetch data from the database
        String[] columnNames = {"Name", "Blood_Group", "Contact", "Address", "Disease"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM donor");

            while (resultSet.next()) {
                Object[] rowData = {resultSet.getString("donor_name"), resultSet.getString("blood_group"), resultSet.getString("donor_contact_no"), resultSet.getString("donor_address"), resultSet.getString("disease")};
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
                    new Mainpage();
                }
            });
        });
    }
}
