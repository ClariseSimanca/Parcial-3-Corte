/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Clarise simanca
 */
public class CrudPasajero {
     int idPasajero = 0;
     
    public void insertarDato(String nombre, String pasaporte, String nacionalidad) {

        String query = "insert into pasajeros (nombre, pasaporte, nacionalidad) "
                + "values (?,?,?)";
        try {
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, pasaporte);
            ps.setString(3, nacionalidad);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar al vuelo" + e.getMessage());

        }

    }

    public int leerDatos() {

        String query = "select * from pasajeros";
        try {
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("idPasajero");
                String nombre = rs.getString("nombre");
                String pasaporte = rs.getString("pasaporte");
                String nacionalidad = rs.getString("nacionalidad");
           

               idPasajero = id;

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ver pasajeros" + e.getMessage());

        }
        return idPasajero;

    }
    
      public void Verdatos() {

        String query = "select * from pasajeros";
        try {
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("idPasajero");
                String nombre = rs.getString("nombre");
                String pasaporte = rs.getString("pasaporte");
                String nacionalidad = rs.getString("nacionalidad");
           

              JOptionPane.showMessageDialog(null," id: "+ id + " nombre: " + nombre +
                      " pasaporte: " + pasaporte);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ver pasajeros" + e.getMessage());

        }

    }

    public void actualizarDatos(int idPasajero, String nombre, String pasaporte, String nacionalidad) {
        String query = "update pasajeros set nombre=?,pasaporte=?,nacionalidad=? where idPasajero=?";
        try {
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, nombre);
            ps.setString(2, pasaporte);
            ps.setString(3, nacionalidad);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos actualizados con éxito");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar datos" + e.getMessage());

        }
    }
    
       public void agregarPasajero(int idVuelo){
            
        String query = "insert into vuelos_pasajeros (idVuelo,idPasajero) values (?,?)" ;
        try{
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,idVuelo);
            ps.setInt(2, idPasajero);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Pasajero agregado correctamente");
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al ingresar el pasajero" + e.getMessage());
        
        }
    
    }
       
        public void agregarPasajeroPanel(int idVuelo, int idPasajero){
            
        String query = "insert into vuelos_pasajeros (idVuelo,idPasajero) values (?,?)" ;
        try{
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,idVuelo);
            ps.setInt(2, idPasajero);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Pasajero agregado correctamente");
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al ingresar el pasajero" + e.getMessage());
        
        }
    
    }

    public void eliminarDatos(int idPasajero) {
        String query = "delete from pasajeros where idPasajero=?";
        try {
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, idPasajero);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dato eliminado con éxito");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar datos " + e.getMessage());

        }
    }

}
