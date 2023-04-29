package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import model.User;

public class LoginService {

	public User logIn(String username,String password,Socket socket) {

		User user = null;
		PrintStream pw;
		BufferedReader bf;
		try {
			pw = new PrintStream(socket.getOutputStream());
			bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			int i;
			String u="NOT_FOUND";
			String p;
				pw.println(username);


				pw.println(password);
				i=Integer.parseInt(bf.readLine());
				u = bf.readLine();
				p= bf.readLine();

				if (!u.equals("NOT_FOUND")) {
					user=new User();
					user.setId(i);
					user.setUsername(u);
					user.setPassword(p);
					System.out.println(i+u);
					return user;
				}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	


}
