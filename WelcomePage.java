
import java.awt.*;
import java.awt.event.*;

public class WelcomePage extends Frame {

    public WelcomePage(User user) {
        super("Welcome");

        setLayout(new GridLayout(2, 1, 8, 8));

        Label greeting = new Label(
                "Hello, " + user.getUsername(),
                Label.CENTER
        );

        Label roleLabel = new Label(
                "Role: " + user.getRole(),
                Label.CENTER
        );

        greeting.setFont(
                new Font("SansSerif", Font.BOLD, 18)
        );

        roleLabel.setFont(
                new Font("SansSerif", Font.PLAIN, 15)
        );

        add(greeting);
        add(roleLabel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setSize(320, 150);
        setLocationRelativeTo(null);
    }
}
