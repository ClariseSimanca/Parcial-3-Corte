
package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexionMysql2 {
    
    static String url = "jdbc:mysql://82.197.82.62:3306/u984447967_op2024b";
    static String user = "u984447967_unipaz";
    static String pass = "estudiantesPoo2024B.*";
    
    
    public static Connection conectar(){
        Connection cn = null;
        try{
            cn = DriverManager.getConnection(url,user,pass);
            System.out.println("Conexión exitosa");
                    
        }catch(SQLException e){
            System.out.println("Error al momento de conectarse"+ e.getMessage());
        }
        return cn;
    }
}
