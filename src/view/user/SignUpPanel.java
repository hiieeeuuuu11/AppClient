package view.user;

import javax.swing.*;
import javax.swing.border.Border;

import controller.ConfigBTL;
import controller.LoginService;
import controller.SignUpService;
import controller.SocketConnect;
import model.User;

import java.awt.*;
import java.awt.event.*;
import java.net.Socket;

public class SignUpPanel extends JPanel {
    private JLabel nameLabel,  passwordLabel, confirmPasswordLabel;
    private JTextField nameField, passwordField, confirmPasswordField;
    private JButton signupButton;
    public static Socket socket1 = SocketConnect.cli(ConfigBTL.getSkPortSignup());
    //private WelcomePanel pcon = new WelcomePanel();
    public SignUpPanel() {
        Border bo = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        setBorder(bo);
        //setPreferredSize(new Dimension(200, 200));
        setLayout(new GridLayout(5, 2));


        nameLabel = new JLabel("Tên đăng nhập:");
        passwordLabel = new JLabel("Mật khẩu:");
        confirmPasswordLabel = new JLabel("Xác nhận mật khẩu:");
        nameField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();
        signupButton = new JButton("Đăng kí thôi nào");

        nameField.setPreferredSize(new Dimension(200, 10));
        passwordField.setPreferredSize(new Dimension(200, 50));
        confirmPasswordField.setPreferredSize(new Dimension(200, 50));
        nameField.setColumns(45);

        add(nameLabel);
        add(nameField);
        add(passwordLabel);
        add(passwordField);
        add(confirmPasswordLabel);
        add(confirmPasswordField);
        add(new JLabel());
        add(signupButton);
    }

    public int check(Socket socket) {
        String username = nameField.getText();
        String password = passwordField.getText();
        SignUpService signup = new SignUpService();
        int u = signup.signup(username, password,socket);
        if(u==1) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public JButton getSignupButton() {
        return signupButton;
    }

    public void setSignupButton(JButton signupButton) {
        this.signupButton = signupButton;
    }
}