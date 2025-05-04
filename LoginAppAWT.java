
    import java.awt.*;
    import java.awt.event.*;
    
    public class LoginAppAWT extends Frame implements ActionListener {
        private TextField usernameField, passwordField;
        private Label statusLabel;
        private int attempts = 3;
    
        public LoginAppAWT() {
            setLayout(new FlowLayout());
    
            add(new Label("Username:"));
            usernameField = new TextField(20);
            add(usernameField);
    
            add(new Label("Password:"));
            passwordField = new TextField(20);
            passwordField.setEchoChar('*');
            add(passwordField);
    
            Button loginButton = new Button("Login");
            loginButton.addActionListener(this);
            add(loginButton);
    
            Button clearButton = new Button("Clear");
            clearButton.addActionListener(e -> {
                usernameField.setText("");
                passwordField.setText("");
                statusLabel.setText("");
            });
            add(clearButton);
    
            statusLabel = new Label("");
            add(statusLabel);
    
            setSize(300, 200);
            setTitle("Login Screen");
            setVisible(true);
    
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    dispose();
                }
            });
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (attempts > 0) {
                    String username = usernameField.getText();
                    String password = passwordField.getText();
    
                    if (!username.equals(password)) {
                        attempts--;
                        throw new LoginException("Username and password must be the same. Attempts left: " + attempts);
                    }
    
                    statusLabel.setText("Login successful!");
                } else {
                    statusLabel.setText("Too many failed attempts!");
                }
            } catch (LoginException ex) {
                statusLabel.setText(ex.getMessage());
            }
        }
    
        class LoginException extends Exception {
            public LoginException(String message) {
                super(message);
            }
        }
    
        public static void main(String[] args) {
            new LoginAppAWT();
        }
    }

