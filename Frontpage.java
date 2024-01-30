package Templates;
import ReturnTypes.recent_quizzes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.net.Socket;

import static Templates.LoginForm.ip;
import static Templates.LoginForm.port;

public class Frontpage extends JFrame implements ActionListener  {
    String username;
    JButton b1;
    JButton b2;
    public Frontpage(String username){
        b1 = new JButton("See New Quiz"); //set label to button
        b1.addActionListener(this);
        b1.setBounds(200,100,500,50);
        b2= new JButton("VIEW PREVIOUS");
        b2.addActionListener(this);
        b2.setBounds(200,50,500,50);
        this.username=username;

       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setLayout(null);
       this.setSize(500,500);
       this.setVisible(true);
       this.add(b1);
       this.add(b2);



    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            System.out.println("SEE NEW QUIZ");
            view_recent_quizzes r=new view_recent_quizzes(username);
            r.setVisible(true);
        }
        else{
            System.out.println("VIEW PREVIOUS RESULTS");
            try {
                Socket s=new Socket(ip,port);
                BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
                boolean Flushable=true;
                PrintWriter out=new PrintWriter(new OutputStreamWriter(s.getOutputStream()),Flushable);
                out.println("2");
                out.println(username);
                int ct=Integer.parseInt(in.readLine());
                DefaultListModel<String> all = new DefaultListModel<String>();
                for(int i=0;i<ct;i++){
//                    System.out.println("QUIZ ID "+Integer.parseInt(in.readLine())+" "+"Marks "+Integer.parseInt(in.readLine()));
                    String to_add="QUIZ_ID "+in.readLine()+" "+"MARKS "+in.readLine();
                    all.addElement(to_add);
                }
                s.close();
                Previous p=new Previous(all);
                p.setVisible(true);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
