import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Askforblood extends JFrame {

    private JTextField textField1;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JComboBox comboBox2;
    private JButton submitButton;
    private JButton cancelButton;
    private JComboBox comboBox3;
    private JPanel panel;

    Askforblood(Connection con){
    add(panel);
    setUndecorated(true);
    setSize(800,400);
    setLocationRelativeTo(null);
    setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Mainpage();
            }
        });
        submitButton.addActionListener(e->{
            if(con!=null)
            {
                String name=textField1.getText();
                String contact=textField2.getText();
                String blood=String.valueOf(comboBox1.getSelectedItem());
                String amount=String.valueOf(comboBox3.getSelectedItem());
                String city=String.valueOf(comboBox2.getSelectedItem());
                if(!textField1.getText().isEmpty()||!textField2.getText().isEmpty())
                {
                    String query = "INSERT INTO  ask (Name,Contact,Bloodtype,Bloodamount,location) VALUES (?, ?,?,?,?)";
                    try {
                        PreparedStatement preparedStatement=con.prepareStatement(query);
                          preparedStatement.setString(1,name);
                          preparedStatement.setString(2,contact);
                          preparedStatement.setString(3,blood);
                          preparedStatement.setString(4,amount);
                          preparedStatement.setString(5,city);
                          int result=preparedStatement.executeUpdate();
                          if(result>=1)
                          {
                              JOptionPane.showMessageDialog(null,"We will contact you after arranging Blood","Sucess",JOptionPane.PLAIN_MESSAGE);
                               textField1.setText("");
                               textField2.setText("");

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
