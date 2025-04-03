package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Item;

public class ItemDAO {

    public boolean  insertar(Item item) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) {
            String query = "INSERT INTO Item (nombre, id_proveedor, cantidad, minimo, precio) VALUES (?, ?, ?, ?, ?)";
    
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, item.getNombre());
                stmt.setInt(2, item.getIdProveedor());
                stmt.setInt(3, item.getCantidad());
                stmt.setInt(4, item.getMinimo());
                stmt.setDouble(5, item.getPrecio());
    
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Error al agregar el Item: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean actualizar(String columna, String nombre, String valor) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Item SET " + columna + " = ? WHERE nombre = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, valor);
                stmt.setString(2, nombre);
    
                int filas_afectadas = stmt.executeUpdate();
                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al actualizar Item: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean  eliminar(String nombre) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Item WHERE nombre = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, nombre);
    
                int filas_afectadas = stmt.executeUpdate();
                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al eliminar item: " + e.getMessage());
            }
        }
        return false;
    }

    public String buscar(String nombre) {
        Connection conexion = ConexionDB.conectar();
        String nombre_busqueda = null;
    
        if (conexion != null) {
            String query = "SELECT nombre FROM Item WHERE nombre = ?"; // Usamos un parámetro '?'
    
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, nombre); // Asignamos el valor del nombre al parámetro
                ResultSet rs = stmt.executeQuery();
    
                if (rs.next()) {
                    nombre_busqueda = rs.getString("nombre");
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar item por nombre: " + e.getMessage());
            }
            return nombre_busqueda;
        }
        return null; 
    }

    public Item buscarMostrar(String nombre) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            Item item = null;
            String query = "SELECT * FROM Item WHERE nombre = ?";

            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, nombre);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    item = new Item(
                        rs.getString("nombre"),
                        rs.getInt("id_proveedor"),
                        rs.getInt("minimo"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio")
                    );
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar item por nombre: " + e.getMessage());
            }
            return item; 
        }
        return null; 
    }

    public ArrayList<Item> obtenerTodos() {
        Connection conexion = ConexionDB.conectar();
        ArrayList<Item> items = new ArrayList<>();

        if (conexion != null) {
            
            String query = "SELECT * FROM Item";

            try (
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Item item = new Item(
                        rs.getString("nombre"),
                        rs.getInt("id_proveedor"),
                        rs.getInt("minimo"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio")
                    );

                    items.add(item);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener todos los items: " + e.getMessage());
            }
        }
        return items;
    }
}
