package view.user;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controller.SocketConnect;
import model.User;

public class WelcomeFrame extends JFrame{
	LoginPanel login = new LoginPanel();
	SignUpPanel signup = new SignUpPanel();
	User user;
	public void run(){
		setLayout(new BorderLayout());
		setSize(400,200);
		this.add(login,BorderLayout.CENTER);

		login.getSignupButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				login.setVisible(false);
				add(signup,BorderLayout.CENTER);
			}
		});
		login.getLoginButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(login.check(login.socket)!=null) {
					dispose();
					ClientGUI g = new ClientGUI();
					g.setVisible(true);
					try {
						g.execute();
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				}else{
					JOptionPane.showMessageDialog(null, "User hoac password sai!");
				}
			}
		});
		signup.getSignupButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(signup.check(signup.socket1)!=1) {
					JOptionPane.showMessageDialog(null, "Tai khoan da ton tai");
				}else{
					JOptionPane.showMessageDialog(null, "ThanhCong");
					//dispose();
					signup.setVisible(false);
					add(login,BorderLayout.CENTER);
					login.setVisible(true);
				}
			}
		});

		this.setTitle("Welcome");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}

	public static void main(String[] args) {
		WelcomeFrame frame = new WelcomeFrame();
		frame.run();
	}

}
