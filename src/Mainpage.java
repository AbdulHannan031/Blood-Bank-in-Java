import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;

public class Mainpage extends JFrame{
    private JPanel page;
    private JButton donateBloodButton;
    private JButton askForBloodButton;
    private JButton registerAsDonorButton;
    private JButton ourDonorsButton;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JButton cancelButton;
    private JLabel escape;
    Connection con;
    Mainpage()
    {
        try {
            con=new db().connection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        add(page);
        setUndecorated(true);

        setSize(600,400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        donateBloodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Donateblood(con);
            }
        });
        escape.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Mainpage();
            }
        });
        askForBloodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Askforblood(con);
            }
        });
        registerAsDonorButton.addActionListener(e->{
            dispose();
            new Registerasdonor(con);
        });
        ourDonorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new OurDonors(con);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField1.getText().isEmpty() || passwordField1.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Please fill all fields","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(textField1.getText().equals("admin") && passwordField1.getText().equals("admin"))
                {
                    dispose();
                    new dashbord(con);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Invalid credientials","Error",JOptionPane.ERROR_MESSAGE);

                }
            }
        });
    }

    public static void main(String[] args) {
        new Mainpage();
    }
}
