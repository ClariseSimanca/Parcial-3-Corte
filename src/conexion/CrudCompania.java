
package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CrudCompania {
    
     public void insertarDato(String nombre){
            
        String query = "insert into companias (nombre) values (?)" ;
        try{
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,nombre);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Compañia agregada correctamente");
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al ingresar la compañia" + e.getMessage());
        
        }
    
    }
     
     public void leerDatos(){
        
        String query = "select * from companias";
        
        try{
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                int id = rs.getInt("idCompania");
                String nombre = rs.getString("nombre");
                
                JOptionPane.showMessageDialog(null," id: " + id + " - " + " Nombre: " + nombre);
            
            }
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al ver compañias" + e.getMessage());
        
        }        
    
    }
     
         
    public void actualizarDatos(int idCompania,String nombre){
        String query = "update companias set nombre=? where idCompania=?";
        try{  
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
             
            ps.setString(1,nombre);
            ps.setInt(2, idCompania);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Datos actualizados con éxito");
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al actualizar datos"+ e.getMessage());
        
        }
    }
    
      public void eliminarDatos(int idCompania){
        String query = "delete from companias where idCompania=?";
        try{  
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, idCompania);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Dato eliminado con éxito");
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar datos " + e.getMessage());
        
        }
    }
      
       public void agregarCompañia(int idAeropuerto, int idCompania){
            
        String query = "insert into aeropuertos_companias (idAeropuerto,idCompania) values (?,?)" ;
        try{
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,idAeropuerto);
            ps.setInt(2, idCompania);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Compañia agregada correctamente");
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al ingresar la compañia" + e.getMessage());
        
        }
    
    }
       
      public void buscarCompania(int idAeropuerto, JTable tabla) {
        String query = "select ac.idAeropuerto, c.idCompania, c.nombre " +
                   "from aeropuertos_companias ac " +
                   "join companias c ON ac.idCompania = c.idCompania " +
                   "where ac.idAeropuerto = ?";
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();

        try {
            Connection con = ConexionMysql2.conectar(); 
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idAeropuerto);
            ResultSet rs = ps.executeQuery();

            Object[] companias = new Object[3];
            while (rs.next()) {
                
                companias[0] = rs.getInt("idCompania");   
                companias[1] = rs.getString("nombre");    

                model.addRow(companias); 
             }

                tabla.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ver compañías: " + e.getMessage());
    }
}

}
