package Templates;

//import required classes and packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.Exception;
import java.net.Socket;
import java.net.UnknownHostException;

//create CreateLoginForm class to create login form
//class extends JFrame to create a window where our component add
//class implements ActionListener to perform an action on button click
public class LoginForm extends JFrame implements ActionListener
{
     static final String ip="127.0.0.1";
     static final int port=9090;
    //initialize button, panel, label, and text field
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    final JTextField  textField1, textField2;
    String userValue;
    String passValue;
    //calling constructor
    public LoginForm()
    {

        //create label for username
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1

        //create text field to get username from the user
        textField1 = new JTextField(15);    //set length of the text

        //create label for password
        passLabel = new JLabel();
        passLabel.setText("Password");      //set label value for textField2

        //create text field to get password from the user
        textField2 = new JPasswordField(15);    //set length for the password

        //create submit button
        b1 = new JButton("SUBMIT"); //set label to button

        //create panel to put form elements
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(textField2);   //set text field to panel
        newPanel.add(b1);           //set button to panel

        //set border to panel
        add(newPanel, BorderLayout.CENTER);

        //perform action on button click
        b1.addActionListener(this);     //add action listener to button
        setTitle("LOGIN FORM");         //set title to the login form
    }

    //define abstract method actionPerformed() which will be called on button click
    public void actionPerformed (ActionEvent ae)      //pass action listener as a parameter
    {
         userValue = textField1.getText();        //get user entered username from the textField1
         passValue = textField2.getText();
         try{
             Socket s=new Socket(ip,port);
             BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
             boolean Flushable=true;
             PrintWriter out=new PrintWriter(new OutputStreamWriter(s.getOutputStream()),Flushable);
             out.println("1");
             out.println(userValue);
             out.println(passValue);
             String response=in.readLine();
             System.out.println(response);
             if(response.equals("1")){
                 Frontpage f=new Frontpage(userValue);
             }
             else{
                 JOptionPane.showMessageDialog(null,"INVALID","error", JOptionPane.INFORMATION_MESSAGE);
             }
             s.close();
         } catch (IOException e) {
             throw new RuntimeException(e);
         }

    }
}
