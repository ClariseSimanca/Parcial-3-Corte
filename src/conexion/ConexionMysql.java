package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexionMysql {
    
    static Connection cn;
    static String user = "root";
    static String password = "";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/ingresar_java_mysql";
    
    public static  Connection conectar(){
        try{
            Class.forName(driver);
            cn = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Conexión exitosa");
        }catch (SQLException | ClassNotFoundException e) {
            System.out.println("No pudo conectarse"+e.getMessage());
        
        }
        return cn;
        
    }
    
}
