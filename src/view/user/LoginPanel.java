package view.user;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.accessibility.Accessible;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


import controller.ConfigBTL;
import controller.LoginService;
import controller.SocketConnect;
import model.User;


public class LoginPanel extends JPanel implements ActionListener{
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField, passwordField;
    private JButton loginButton;
    private JButton signupButton;
    public static Socket socket = SocketConnect.cli(ConfigBTL.getSkPortLogin());

    //private WelcomePanel pcon = new WelcomePanel();
    public LoginPanel() {
    	Border bo = BorderFactory.createEmptyBorder(20, 20, 20, 20);
    	setBorder(bo);
    	setLayout(new GridLayout(5, 2));
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Đăng nhập");
        //loginButton.addActionListener(this);
        signupButton = new JButton("Đăng kí nếu chưa có tài khoản");
        //signupButton.addActionListener(this);

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel());
        add(loginButton);
        add(new JLabel());
        add(signupButton);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==loginButton) {
			if(check(socket)!=null) {
				System.out.println("Thanh cong");
			}
			else {
				System.out.println("Sai");
			}
		}


	}

	public User check(Socket socket) {
		String username = usernameField.getText();
        String password = passwordField.getText();
        LoginService login = new LoginService();
        User u = login.logIn(username, password,socket);
        if(u!=null) {
        	System.out.println("dang nhap thanh cong");
        	return u;
        }
        else {
        	return null;
        }
	}

	public JButton getSignupButton() {
		return signupButton;
	}
	public void setSignupButton(JButton signupButton) {
		this.signupButton = signupButton;
	}
	public JButton getLoginButton() {
		return loginButton;
	}
	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}


}