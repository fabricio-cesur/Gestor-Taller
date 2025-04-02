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
            String query = "INSERT INTO Item ( nombre, id_proveedor, cantidad, minimo, precio) VALUES (" + item.getNombre() + ", " + item.getIdProveedor() +
                 ", " + item.getCantidad() + ", " +  item.getMinimo() + ", " + item.getPrecio() + ");" ; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) { 
               
                stmt.executeUpdate(); // Ejecuta la consulta de inserción 

                return true; 
            } catch (SQLException e) { 
                System.out.println("Error al agregar el Item: " + e.getMessage()); 
            } 
        }
        return false;
    }

    public boolean actualizar(String columna, String codigo, String valor ) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Item SET " + columna + "=" + valor + " WHERE codigo = " + codigo; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                                
                int filas_afectadas = stmt.executeUpdate();

                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al actualizar cliente: " + e.getMessage());
            } 
        }
        return false;
    }

    public boolean  eliminar(String codigo) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Item WHERE codigo = " + codigo;
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                int filas_afectadas = stmt.executeUpdate();
                
                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al eliminar cliente: " + e.getMessage());
            }
        }
        return false;
    }

    public String buscar(String codigo) {
        Connection conexion = ConexionDB.conectar();
        String codigo_busqueda = null;

        if (conexion != null) {
            String query = "SELECT * FROM Cliente WHERE codigo = " + codigo;
            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    codigo_busqueda =  rs.getString("codigo");
                    
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar item por codigo: " + e.getMessage());
            }
            
            return codigo_busqueda; 
        }
        return null; 
    }

    public Item buscarMostrar(String codigo) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            Item item = null;
            String query = "SELECT * FROM Item WHERE codigo = " + codigo;

            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    item = new Item(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("id_proveedor"),
                        rs.getInt("minimo"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio")
                    );
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar cliente por DNI: " + e.getMessage());
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
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("id_proveedor"),
                        rs.getInt("minimo"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio")
                    );
                    items.add(item);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener todos los clientes: " + e.getMessage());
            }
        }
        return items;
    }
}
