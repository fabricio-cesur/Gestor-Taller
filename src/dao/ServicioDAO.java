package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Servicio;

public class ServicioDAO {

    public boolean insertar(Servicio servicio) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            String query = "INSERT INTO Servicio (nombre, descripcion, id_item, precio,) VALUES (" + servicio.getNombre() 
            + ", " + servicio.getDescripcion() + ", " + servicio.getItemCodigo() + ", " +servicio.getPrecio() +");" ; 
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
                        rs.getString("id_item"),
                        rs.getInt("precio")
                    );
                    servicio.setId(rs.getString("id"));
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar servicio por ID: " + e.getMessage());
            }
            return servicio; 
        }
        return null; 
    }

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
                        rs.getString("id_item"),
                        rs.getInt("precio")
                    );
                    servicio.setId(rs.getString("id"));
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
