package conexion;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class CrudAeropuerto {
    
    public void insertarDato(String nombre, String ciudad, String pais, 
            int privado, int publico, double subvencion){
            
        String query = "insert into aeropuertos (nombre, ciudad, pais, privado, publico, subvencion) "
                + "values (?,?,?,?,?,?)" ;
        try{
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,nombre);
            ps.setString(2,ciudad);
            ps.setString(3, pais);
            ps.setInt(4, privado);
            ps.setInt(5, publico);
            ps.setDouble(6, subvencion);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Aeropuerto agregado correctamente");
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al ingresar el aeropuerto" + e.getMessage());
        
        }
    
    }
    
    public void leerDatos(){
        
        String query = "select * from aeropuertos";
        
        try{
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                int id = rs.getInt("idAeropuerto");
                String nombre = rs.getString("nombre");
                String ciudad = rs.getString("ciudad");
                String pais = rs.getString("pais");
                int privado = rs.getInt("privado");
                int publico = rs.getInt("publico");
                Double subvencion = rs.getDouble("subvencion");
                
                JOptionPane.showMessageDialog(null," id: " + id + " - " + " Nombre: " + nombre);
            
            }
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al ver aeropuertos" + e.getMessage());
        
        }        
    
    }
    
    public void actualizarDatos(int idAeropuerto,String nombre, String ciudad, String pais, 
            int privado, int publico, double subvencion){
        String query = "update aeropuertos set nombre=?,ciudad=?,pais=?,privado=?,publico=?,subvencion=?"
                + "where idAeropuerto=?";
        try{  
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            
      
            ps.setString(1,nombre);
            ps.setString(2,ciudad);
            ps.setString(3, pais);
            ps.setInt(4, privado);
            ps.setInt(5, publico);
            ps.setDouble(6, subvencion);
            ps.setInt(7, idAeropuerto);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Datos actualizados con éxito");
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al actualizar datos"+ e.getMessage());
        
        }
    }
    
       public void eliminarDatos(int idAeropuerto){
        String query = "delete from aeropuertos where idAeropuerto=?";
        try{  
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, idAeropuerto);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Dato eliminado con éxito");
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar datos " + e.getMessage());
        
        }
    }
       
        public void agregarDatoTabla(ArrayList<Integer> idAeropuertos, JTable tabla){
        
        String query = "select * from aeropuertos where idAeropuerto=?";
        DefaultTableModel model;
        Object[] aero = new Object[5];
        try{
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query); 
            
            model = (DefaultTableModel) tabla.getModel();
            
            for(int idAeropuerto : idAeropuertos){
                ps.setInt(1, idAeropuerto);
                ResultSet rs = ps.executeQuery();    
                while(rs.next()){
                
                    aero [0] = rs.getInt("idAeropuerto");
                    aero[1] = rs.getString("nombre");
                    aero [2] = rs.getString("ciudad");
                    aero [3] = rs.getString("pais");
                    aero [4] = rs.getDouble("subvencion");
                    model.addRow(aero);
                }
            }
                tabla.setModel(model);
         
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al ver aeropuertos" + e.getMessage());
        
        }        
    
    }
}
