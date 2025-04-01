package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Proveedor;

public class ProveedorDAO {
    public boolean insertar(Proveedor proveedor) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            String query = "INSERT INTO Proveedor (nombre, direccion, cuenta_bancaria) VALUES (" + proveedor.getNombre() 
            + ", " + proveedor.getDireccion() + ", " + proveedor.getCuenta() + ");" ; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) { 
               
                stmt.executeUpdate(); // Ejecuta la consulta de inserción 

                return true; 
            } catch (SQLException e) { 
                System.out.println("Error al agregar proveedor: " + e.getMessage()); 
            } 
        }
        return false;
    }
}
