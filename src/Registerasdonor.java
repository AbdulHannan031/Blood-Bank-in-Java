import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registerasdonor  extends JFrame{
    private JPanel panel;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox2;
    private JTextField textField3;
    private JButton registerButton;
    private JButton cancelButton;
    private JComboBox comboBox1;

    Registerasdonor(Connection con)
     {
         add(panel);
         setUndecorated(true);
         setSize(600,400);
         setLocationRelativeTo(null);
         setVisible(true);
         cancelButton.addActionListener(e->{
             dispose();
             new Mainpage();
         });
         registerButton.addActionListener(e->{
             if (con!=null)
             {
                 String name=textField1.getText();
                 String contact=textField2.getText();
                 String blood=String.valueOf(comboBox1.getSelectedItem());
                 String city=String.valueOf(comboBox2.getSelectedItem());
                 String disease=textField3.getText();
                 if(!name.isEmpty()&&!contact.isEmpty()&&!disease.isEmpty())
                 {
                     String query="INSERT INTO donor (donor_name,blood_group,donor_contact_no,donor_address,disease) VALUES(?,?,?,?,?)";
                     try {
                         PreparedStatement preparedStatement=con.prepareStatement(query);
                         preparedStatement.setString(1,name);
                         preparedStatement.setString(2,blood);
                         preparedStatement.setString(3,contact);
                         preparedStatement.setString(4,city);
                         preparedStatement.setString(5,disease);
                         int result=preparedStatement.executeUpdate();
                         if(result>=1)
                         {
                             JOptionPane.showMessageDialog(null,"Registered Sucessfully ");
                              textField1.setText("");
                              textField2.setText("");
                              textField3.setText("");
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
