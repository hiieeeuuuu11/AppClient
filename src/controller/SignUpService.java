package controller;

import model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class SignUpService {
    public int signup(String username, String password, Socket socket) {
        User user = new User();
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

            if (i==1) {
                System.out.println(i);
                return 1;
            }
            else{
                System.out.println(i);
                return 0;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
