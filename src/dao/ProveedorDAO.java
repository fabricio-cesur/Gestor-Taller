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

    public boolean actualizar(String columna, String id, String valor ) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Proveedor SET " + columna + "=" + valor + " WHERE id = " + id; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                                
                int filas_afectadas = stmt.executeUpdate();

                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al actualizar proveedor: " + e.getMessage());
            } 
        }
        return false;
    }

    public boolean eliminar(String id) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Proveedor WHERE id = " + id;
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, id);

                int filas_afectadas = stmt.executeUpdate();
                
                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al eliminar proveedor: " + e.getMessage());
            }
        }
        return false;
    }
}
