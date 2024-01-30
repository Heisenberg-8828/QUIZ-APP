package Templates;

import MYSQLCONNECTIVITY.connector;
import com.mysql.cj.conf.ConnectionUrlParser;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Previous extends JFrame {

    DefaultListModel<String> to_display;
    Previous(DefaultListModel<String> to_display){
        this.to_display=to_display;
        JList<String> now= new JList<String>(to_display);
        this.add(now);

    }

}
