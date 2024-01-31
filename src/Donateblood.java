import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Donateblood extends JFrame {
    private JPanel panel1;
    private JTextField NAmeTextField;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton DONATEButton;
    private JButton CANCELButton;
    private JTextField textField1;

    Donateblood(Connection con)
    {
        add(panel1);
        setUndecorated(true);
        setSize(800,400);
        setVisible(true);
        setLocationRelativeTo(null);

        CANCELButton.addActionListener(e->{
            dispose();
            new Mainpage();
        });
        DONATEButton.addActionListener(e->{
            if(con!=null)
            {
                if(!textField1.getText().isEmpty() &&!NAmeTextField.getText().isEmpty()) {
                    String name=NAmeTextField.getText();
                    String contact=textField1.getText();
                    String blood= String.valueOf(comboBox1.getSelectedItem());
                    String City=String.valueOf(comboBox2.getSelectedItem());
                    LocalDate currentDate = LocalDate.now();

                    String query = "INSERT INTO  donate (date_of_donation, blood_bank_name,bloodgroup,contact) VALUES (?, ?,?,?)";
                    try {
                        PreparedStatement preparedStatement=con.prepareStatement(query);
                        preparedStatement.setDate(1, Date.valueOf(currentDate));
                        preparedStatement.setString(2,City);
                        preparedStatement.setString(3,blood);
                        preparedStatement.setString(4,contact);
                        int result=preparedStatement.executeUpdate();
                        if(result>=1)
                        {
                            JOptionPane.showMessageDialog(null,"Donated Sucessfully","Sucess",JOptionPane.PLAIN_MESSAGE);
                            textField1.setText("");
                            NAmeTextField.setText("");
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Cannot Donate Due to Error at our hand","Sorry",JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Please input all fields","Error",JOptionPane.ERROR_MESSAGE);

                }


            }
        });


    }
}
