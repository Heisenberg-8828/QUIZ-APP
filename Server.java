import ReturnTypes.recent_quizzes;
import ServerConnections.Loginclient;
import ServerConnections.Previousresultsclient;
import ReturnTypes.result;
import ServerConnections.download_quizzes_client;
import ServerConnections.view_recent_quizzes_client;
import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
public class Server{
    public static void main(String[] args) throws IOException, SQLException {
        int port=9090;

        System.out.println("CONNECTED TO DATABASE");
        ServerSocket listener=new ServerSocket(port);
        System.out.println("SERVER STARTED WAITING FOR CLIENTS");

        while(true){

            Socket client=listener.accept();
            BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
            boolean Flushable=true;
            PrintWriter out=new PrintWriter(new OutputStreamWriter(client.getOutputStream()),Flushable);
            System.out.println("CLIENT CONNECTED");
            String type=in.readLine();
            if(type.equals("1")){
                System.out.println("1");
                String username=in.readLine();
                String password=in.readLine();
                System.out.println(username);
                System.out.println(password);
                Loginclient c =new Loginclient(username,password);
                String response=c.get_result();
                out.println(response);

            }
            if(type.equals("2")){
                System.out.println("2");
                String username=in.readLine();
                Previousresultsclient pc=new Previousresultsclient(username);
                result r= pc.get_result();
                ArrayList<Integer> marks=r.marks;
                ArrayList<Integer> quiz=r.quiz;
                int ct=marks.size();
                out.println(ct);
                for(int i=0;i<ct;i++){
                    out.println(quiz.get(i));
                    out.println(marks.get(i));
                }

            }
            if(type.equals("3")){
                System.out.println("3");
                String username=in.readLine();
                view_recent_quizzes_client c= new view_recent_quizzes_client(username);
                recent_quizzes r= c.get_result();
                ArrayList<String> ans=r.t;
                int length=ans.size();
                out.println(length);
                for(int i=0;i<length;i++){
                    out.println(ans.get(i));
                }
            }
            if(type.equals("4")){
                System.out.println("4");
                String username=in.readLine();
                String topic=in.readLine();
                System.out.println(topic);
                download_quizzes_client c= new download_quizzes_client(username,topic);
                String path=c.get_result();
                System.out.println(path);
                File f=new File("C:\\TEACHER_QUIZZES");
                File arr[]=f.listFiles();
                for(File x:arr){
//                    System.out.println("YES");
                    String n=x.getName();
                    System.out.println(x);
                    if(x.getName().contains(topic)){
                        FileInputStream fs=new FileInputStream(x.getAbsolutePath());

                        byte [] b=new byte[2005];
                        fs.read(b,0,b.length);
                        OutputStream os=client.getOutputStream();
                        os.write(b,0,b.length);
                    }
                }

            }

        }



    }
}