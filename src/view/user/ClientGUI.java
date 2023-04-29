package view.user;

import BoomGame.main.RunGame;
import model.User;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ClientGUI extends JFrame{


    public ClientGUI() {
        initComponents();
    }
    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTextAreaAllChat = new JTextArea();
        jPanel1 = new JPanel();
        jScrollPane2 = new JScrollPane();
        jTextAreaSendMessage = new JTextArea();
        jButtonSend = new JButton();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Client");
        getContentPane().setLayout(new BorderLayout(10, 10));

        jTextAreaAllChat.setColumns(20);
        jTextAreaAllChat.setRows(5);
        jScrollPane1.setViewportView(jTextAreaAllChat);

        getContentPane().add(jScrollPane1, BorderLayout.CENTER);

        jPanel1.setLayout(new BorderLayout(10, 10));

        jTextAreaSendMessage.setColumns(20);
        jTextAreaSendMessage.setRows(5);
        jScrollPane2.setViewportView(jTextAreaSendMessage);

        jPanel1.add(jScrollPane2, BorderLayout.CENTER);

        jButtonSend.setText("Send");

        jPanel1.add(jButtonSend, BorderLayout.LINE_END);

        getContentPane().add(jPanel1, BorderLayout.PAGE_END);

        setBounds(0, 0, 335, 376);
    }

    public void execute() throws IOException {

        Socket client = new Socket("localhost", 15797);
        Thread read = new Thread(new Runnable() {

            public void run() {
                DataInputStream dis = null;
                try {
                    dis = new DataInputStream(client.getInputStream());
                    while(true) {
                        String sms = dis.readUTF();
                        System.out.println(sms);
                        jTextAreaAllChat.append(sms+"\n");
                    }
                } catch (Exception e) {
                    try {
                        dis.close();
                        client.close();
                    } catch (IOException ex) {
                        System.out.println("Ngắt kết nối Server");
                    }
                }
            }
        });
        Thread write = new Thread(new Runnable() {
            public void run() {
                DataOutputStream dos = null;
                Scanner sc = null;
                try {
                    dos = new DataOutputStream(client.getOutputStream());
                    sc = new Scanner(System.in);
                    while(true) {
                        String sms = sc.nextLine();
                        dos.writeUTF(sms);

                    }
                } catch (Exception e) {
                    try {
                        dos.close();
                        client.close();
                    } catch (IOException ex) {
                        System.out.println("Ngắt kết nối Server");
                    }
                }
            }
        });
        read.start();
        jButtonSend.addActionListener(new ActionListener() {
            DataOutputStream dos = null;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dos = new DataOutputStream(client.getOutputStream());
                    String sms = jTextAreaSendMessage.getText();
                    dos.writeUTF(sms);

                    jTextAreaAllChat.append(sms+"\n");
                    if(sms.equals("GAME")) {
                        Thread game = new Thread(new RunGame());
                        game.start();
                    }
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }


            }
        });
    }

    private JButton jButtonSend;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextArea jTextAreaAllChat;
    private JTextArea jTextAreaSendMessage;
    private Socket socket;
    private PrintWriter writer;
    private Scanner scanner;

}
