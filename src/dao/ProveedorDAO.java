package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Proveedor;
public class ProveedorDAO {
    public boolean insertar(Proveedor proveedor) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            String query = "INSERT INTO Proveedor (nombre, direccion, cuenta_bancaria) VALUES (?, ?, ?)";

            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, proveedor.getNombre());
                stmt.setString(2, proveedor.getDireccion());
                stmt.setString(3, proveedor.getCuentaBancaria());

                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Error al agregar proveedor: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean actualizar(String columna, String nombre, String valor ) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Proveedor SET " + columna + " = ? WHERE nombre = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, valor);
                stmt.setString(2, nombre);

                int filas_afectadas = stmt.executeUpdate();
                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al actualizar proveedor: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean eliminar(String nombre) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Proveedor WHERE nombre = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, nombre);

                int filas_afectadas = stmt.executeUpdate();
                
                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al eliminar proveedor: " + e.getMessage());
            }
        }
        return false;
    }

    public String buscar(String nombre) {
        Connection conexion = ConexionDB.conectar();
        String nombre_busqueda = null;

        if (conexion != null) {
            String query = "SELECT * FROM Proveedor WHERE nombre = ?";

            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, nombre);
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    nombre_busqueda =  rs.getString("nombre");
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar proveedor por nombre: " + e.getMessage());
            }
            return nombre_busqueda; 
        }
        return null; 
    }

    public Proveedor buscarMostrar(String nombre) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            Proveedor proveedor = null;
            String query = "SELECT * FROM Proveedor WHERE nombre = ?";

            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, nombre);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    proveedor = new Proveedor(
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("cuenta_bancaria")
                    );
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar proveedor por nombre: " + e.getMessage());
            }
            return proveedor; 
        }
        return null;
    }

    public ArrayList<Proveedor> obtenerTodos() {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            ArrayList<Proveedor> proveedores = new ArrayList<>();
            String query = "SELECT * FROM Proveedor";

            try (
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Proveedor proveedor = new Proveedor(
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("cuenta_bancaria")
                    );
                    proveedores.add(proveedor);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener todos los proveedor: " + e.getMessage());
            }
            return proveedores;
        }
        return null;
    }
}
