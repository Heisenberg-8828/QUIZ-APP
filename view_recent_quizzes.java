package Templates;

import MYSQLCONNECTIVITY.connector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static Templates.LoginForm.ip;
import static Templates.LoginForm.port;


public class view_recent_quizzes extends JFrame implements ActionListener {
    //some problem with frontend
    String username;
    int ct=0;
    ArrayList<JButton> all_buttons =new ArrayList<JButton>();
    JPanel j=new JPanel(new GridLayout(8,8));
    view_recent_quizzes(String username){
        this.username=username;
        try {
            Socket s=new Socket(ip,port);
            BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
            boolean Flushable=true;
            PrintWriter out=new PrintWriter(new OutputStreamWriter(s.getOutputStream()),Flushable);
            out.println("3");
            out.println(username);
            int length=Integer.parseInt(in.readLine());
            for(int i=0;i<length;i++){
                String title=in.readLine();
                JButton b=new JButton(title);
                b.addActionListener(this);
                all_buttons.add(b);
            }
            s.close();
            int ct=0;
            for (JButton allButton : all_buttons) {
                j.add(allButton);

                ct+=50;
            }
            this.add(j);
            System.out.println(all_buttons.size());

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton allButton : all_buttons) {
            if (e.getSource() == allButton) {
                //establish another connection with server
                try {
                    ct++;
                    Socket s=new Socket(ip,port);
                    boolean Flushable=true;
                    PrintWriter out=new PrintWriter(new OutputStreamWriter(s.getOutputStream()),Flushable);
                    BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
                    out.println("4");
                    out.println(username);
                    out.println(allButton.getText());
                    byte[] b=new byte[2005];
                    InputStream is=s.getInputStream();

                    FileOutputStream fr=new FileOutputStream("C:\\MY_QUIZZES\\download.txt");
                    is.read(b,0,b.length);
                    fr.write(b,0,b.length);
                    s.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    }
}



