package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseDAO {
    public final String URL = "jdbc:mysql://localhost:3306/InventoryClick";
    public final String USER = "root";
    public final String PASSWORD = "193746abcD!";
    public Connection conn;
    
    public DataBaseDAO() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
    }
    
    public void Conectar()throws Exception{
        conn= DriverManager.getConnection(URL,USER,PASSWORD);
    }
    
    public void Desconectar()throws Exception {
        conn.close();
    }
}
