package MYSQLCONNECTIVITY;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class connector {
    static Connection con;
    public static Connection create_connection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String user="root";
            String password="Poonakhil1!";
            String url="jdbc:mysql://localhost:3306/quizzing";
            con= DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
