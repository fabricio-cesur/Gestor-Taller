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

    
}
