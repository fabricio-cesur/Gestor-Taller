package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Servicio;

public class ServicioDAO {

    public boolean insertar(Servicio servicio) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            String query = "INSERT INTO Empleado (nombre, descripcion, id_item, precio,) VALUES (" + servicio.getNombre() 
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
            String query = "UPDATE Empleado SET " + columna + "=" + valor + " WHERE id = " + id; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                                
                int filasAfectadas = stmt.executeUpdate();

                return filasAfectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al actualizar empleado: " + e.getMessage());
            } 
        }
        return false;
    }
}
