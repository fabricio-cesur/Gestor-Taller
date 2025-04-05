package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Servicio;

public class ServicioDAO {
    //Metodo para insertar un nuevo servicio
    public boolean insertar(Servicio servicio) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) {
            String query = "INSERT INTO Servicio (nombre, descripcion, id_item, precio) VALUES (?, ?, ?, ?)";
    
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, servicio.getNombre());
                stmt.setString(2, servicio.getDescripcion());
                stmt.setInt(3, servicio.getItemCodigo());
                stmt.setDouble(4, servicio.getPrecio());
    
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Error al agregar servicio: " + e.getMessage());
            }
        }
        return false;
    }
    //MEtodo para actualizar un servicio
    public boolean actualizar(String columna, String id, String valor ) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Servicio SET " + columna + " = ? WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, valor);
                stmt.setString(2, id);
    
                int filas_afectadas = stmt.executeUpdate();
                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al actualizar servicio: " + e.getMessage());
            }
        }
        return false;
    }
    //Metodo para eliminar un servicio
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
    //Metodo para buscar un servicio en la DB
    public String buscar(String id) {
        Connection conexion = ConexionDB.conectar();
        String id_busqueda = null;

        if (conexion != null) {
            String query = "SELECT * FROM Servicio WHERE id = " + id;

            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    id_busqueda =  rs.getString("id");
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar servicio por ID: " + e.getMessage());
            }
            return id_busqueda; 
        }
        return null; 
    }
    //Metodo para obtener un objeto servicio 
    public Servicio obtener(String id) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            Servicio servicio = null;
            String query = "SELECT * FROM Servicio WHERE id = " + id;

            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    servicio = new Servicio(
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("id_item"),
                        rs.getInt("precio")
                    );
                    servicio.setId(rs.getInt("id"));
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar servicio por ID: " + e.getMessage());
            }
            return servicio; 
        }
        return null; 
    }
    //Metodo para ver todos los servicios
    public ArrayList<Servicio> obtenerTodos() {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            ArrayList<Servicio> servicios = new ArrayList<>();
            String query = "SELECT * FROM Servicio";

            try (
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Servicio servicio = new Servicio(
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("id_item"),
                        rs.getInt("precio")
                    );
                    servicio.setId(rs.getInt("id"));
                    servicios.add(servicio);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener todos los servicio: " + e.getMessage());
            }
            return servicios;
        }
        return null;
    }
}
