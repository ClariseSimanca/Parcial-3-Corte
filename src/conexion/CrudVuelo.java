package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CrudVuelo {

    public void insertarDato(String identificador, String ciudadOrigen, String ciudadDestino,
            double precio, int numMaxPasajeros) {

        String query = "insert into vuelos (identificador, ciudadOrigen, ciudadDestino, precio, numMaxPasajeros) "
                + "values (?,?,?,?,?)";
        try {
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, identificador);
            ps.setString(2, ciudadOrigen);
            ps.setString(3, ciudadDestino);
            ps.setDouble(4, precio);
            ps.setInt(5, numMaxPasajeros);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Vuelo agregado correctamente");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar el vuelo" + e.getMessage());

        }

    }

    public void leerDatos() {

        String query = "select * from vuelos";

        try {
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("idVuelo");
                String identificador = rs.getString("identificador");
                String ciudadOrigen = rs.getString("ciudadOrigen");
                String ciudadDestino = rs.getString("ciudadDestino");
                double precio = rs.getDouble("precio");
                int numMaxPasajeros = rs.getInt("numMaxPasajeros");

                JOptionPane.showMessageDialog(null, " id: " + id + " - " + " Identificador: " + identificador +
                        " Ciudad Origen: " + ciudadOrigen + " Ciudad Destino: " + ciudadDestino);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ver vuelos" + e.getMessage());

        }

    }

    public void actualizarDatos(int idVuelo, String identificador, String ciudadOrigen, String ciudadDestino,
            double precio, int numMaxPasajeros) {
        String query = "update vuelos set identificador=?,ciudadOrigen=?,ciudadDestino=?,"
                + "precio=?,numMaxPasajeros=? where idVuelo=?";
        try {
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, identificador);
            ps.setString(2, ciudadOrigen);
            ps.setString(3, ciudadDestino);
            ps.setDouble(4, precio);
            ps.setInt(5, numMaxPasajeros);
            ps.setInt(6, idVuelo);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos actualizados con éxito");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar datos" + e.getMessage());

        }
    }

    public void eliminarDatos(int idVuelo) {
        String query = "delete from vuelos where idVuelo=?";
        try {
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, idVuelo);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dato eliminado con éxito");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar datos " + e.getMessage());

        }
    }
     public void agregarVuelo(int idCompania, int idVuelo){
            
        String query = "insert into compania_vuelos (idCompania,idVuelo) values (?,?)" ;
        try{
            Connection con = (Connection) ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,idCompania);
            ps.setInt(2, idVuelo);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Vuelo agregado correctamente");
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al ingresar el vuelo" + e.getMessage());
        
        }
    
    }
    public void buscarVuelo(int idCompania, JTable tabla) {
        String query = "select av.idCompania, v.idVuelo, v.identificador, v.ciudadOrigen, "
                + "v.ciudadDestino, v.Precio, v.numMaxPasajeros "
                + "FROM compania_vuelos av "
                + "JOIN vuelos v ON av.idVuelo = v.idVuelo "
                + "WHERE av.idCompania = ?";

        DefaultTableModel model = (DefaultTableModel) tabla.getModel();

        try {
            Connection con = ConexionMysql2.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idCompania);
            ResultSet rs = ps.executeQuery();

            Object[] vuelos = new Object[6];
            while (rs.next()) {
                vuelos[0] = rs.getInt("idVuelo");
                vuelos[1] = rs.getString("identificador");
                vuelos[2] = rs.getString("ciudadOrigen");
                vuelos[3] = rs.getString("ciudadDestino");
                vuelos[4] = rs.getDouble("Precio");
                vuelos[5] = rs.getInt("numMaxPasajeros");
                model.addRow(vuelos);
            }

            tabla.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ver los vuelos: " + e.getMessage());
        }
    }
      
}
