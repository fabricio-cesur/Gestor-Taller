package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Servicio;

public class ServicioDAO {

    public boolean insertar(Servicio servicio) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            String query = "INSERT INTO Servicio (nombre, descripcion, id_item, precio,) VALUES (" + servicio.getNombre() 
            + ", " + servicio.getDescripcion() + ", " + servicio.getItem() + ", " +servicio.getPrecio() +");" ; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) { 
               
                stmt.executeUpdate(); // Ejecuta la consulta de inserción 

                return true; 
            } catch (SQLException e) { 
                System.out.println("Error al agregar servicio: " + e.getMessage()); 
            } 
        }
        return false;
    }
    
    public boolean actualizar(String columna, String id, String valor ) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Servicio SET " + columna + "=" + valor + " WHERE id = " + id; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                                
                int filas_afectadas = stmt.executeUpdate();

                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al actualizar servicio: " + e.getMessage());
            } 
        }
        return false;
    }

    public boolean eliminar(String id) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Servicio WHERE id = " + id;
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, id);

                int filas_afectadas = stmt.executeUpdate();
                
                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al eliminar servicio: " + e.getMessage());
            }
        }
        return false;
    }
}
