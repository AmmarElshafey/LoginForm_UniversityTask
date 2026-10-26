
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class LoginFormTask extends Frame {

    private final List<User> users = new ArrayList<>();

    public LoginFormTask() {
        super("Login");
        loadUsers();

        setLayout(new GridBagLayout());

        Panel content = new Panel(
                new BorderLayout(10, 15)
        );

        Label title = new Label(
                "Welcome back",
                Label.CENTER
        );
        title.setFont(
                new Font("SansSerif", Font.BOLD, 20)
        );
        content.add(title, BorderLayout.NORTH);

        Panel form = new Panel(
                new GridLayout(3, 2, 8, 8)
        );

        form.add(new Label("Username:", Label.RIGHT));
        TextField username = new TextField(18);
        form.add(username);

        form.add(new Label("Password:", Label.RIGHT));
        TextField password = new TextField(18);
        password.setEchoChar('*');
        form.add(password);

        form.add(new Label("Role:", Label.RIGHT));
        Choice role = new Choice();
        role.add("Student");
        role.add("EX");
        role.add("Professor");
        form.add(role);

        content.add(form, BorderLayout.CENTER);

        Panel buttons = new Panel(
                new FlowLayout(FlowLayout.CENTER, 15, 0)
        );

        Button cancel = new Button("Cancel");
        Button login = new Button("Login");

        buttons.add(cancel);
        buttons.add(login);

        content.add(buttons, BorderLayout.SOUTH);
        add(content);

        // Login button
        login.addActionListener(e -> {
            String enteredUsername =
                    username.getText().trim();

            String enteredPassword =
                    password.getText();

            String selectedRole =
                    role.getSelectedItem();

            User user = findUser(enteredUsername);

            if (user != null
                    && user.getPassword()
                    .equals(enteredPassword)
                    && user.getRole()
                    .equals(selectedRole)) {

                new WelcomePage(user).setVisible(true);
                dispose();

            } else {
                showLoginError();
            }
        });

        // Cancel button
        cancel.addActionListener(e -> dispose());

        // Login window close button
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setSize(420, 340);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void loadUsers() {
        users.add(
                new User("ammar", "122004", "Student")
        );

        users.add(
                new User("walaa", "1234", "Ex")
        );

        users.add(
                new User("Akram", "abcd", "Professor")
        );
    }

    private User findUser(String name) {
        for (User user : users) {
            if (user.getUsername()
                    .equalsIgnoreCase(name)) {
                return user;
            }
        }

        return null;
    }

    private void showLoginError() {
        Dialog dialog = new Dialog(
                this,
                "Login Failed",
                true
        );

        dialog.setLayout(new FlowLayout());

        Label message = new Label(
                "Invalid username, password, or role."
        );

        Button ok = new Button("OK");

        ok.addActionListener(e -> dialog.dispose());

        dialog.add(message);
        dialog.add(ok);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.dispose();
            }
        });

        dialog.setSize(300, 120);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFormTask().setVisible(true);
    }
}
