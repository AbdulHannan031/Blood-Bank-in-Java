import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

public class dashbord  extends JFrame{
    private JPanel panel1;
    private JButton newDonationsButton;
    private JButton newAsksForDonationButton;
    private JButton manageDonorsButton;
    private JButton exitButton;
    dashbord(Connection con)
    {
        setUndecorated(true);
        add(panel1);
        setSize(600,400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override

            public void windowClosed(WindowEvent e) {

                new Mainpage();
            }
        });
        newDonationsButton.addActionListener(e->{

            new newdonations(con);

        });
        newAsksForDonationButton.addActionListener(e -> {
            new asks(con);
        });
        manageDonorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new managedonors(con);
            }
        });
        exitButton.addActionListener(e->{
            dispose();
            new Mainpage();
        });
    }

}
