/*
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Encargo;

public class EncargoDAO {

    public boolean  insertar(Encargo encargo) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            String query = "INSERT INTO Encargo ( matricula_coche, precio_total, fecha_inicio, fecha_finalizado ) VALUES (" + encargo.getMatricula() + ", " 
            + encargo.getPrecio_total() + ", " + encargo.fecha_inicio + ", " + encargo.getFechaFinalizado() + ");" ; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) { 
               
                stmt.executeUpdate(); // Ejecuta la consulta de inserción 

                return true; 
            } catch (SQLException e) { 
                System.out.println("Error al agregar cliente: " + e.getMessage()); 
            } 
        }
        return false;
    }

    public boolean actualizar(String columna, String matricula, String valor ) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Encargo SET " + columna + "=" + valor + " WHERE matricula = " + matricula; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                                
                int filas_afectadas = stmt.executeUpdate();

                return filas_afectadas == 1;
                
            } catch (SQLException e) {
                System.out.println("Error al actualizar cliente: " + e.getMessage());
            } 
        }
        return false;
    }

    public boolean  eliminar(String matricula) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Encargo WHERE dni = " + matricula;
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                int filas_afectadas = stmt.executeUpdate();
                
                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al eliminar cliente: " + e.getMessage());
            }
        }
        return false;
    }

    public String buscar(String matricula) {
        Connection conexion = ConexionDB.conectar();
        String matricula_busqueda = null;

        if (conexion != null) {
            Encargo encargo = null;
            String query = "SELECT * FROM Encargo WHERE matricula = " + matricula;
            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    matricula_busqueda =  rs.getString("matricula");
                    
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar cliente por DNI: " + e.getMessage());
            }
            
            return matricula_busqueda; 
        }
        return null; 
    }

    public Encargo buscarMostrar(String matricula) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            Encargo encargo = null;
            String query = "SELECT * FROM Encargo WHERE matricula = " + matricula;

            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    encargo = new Encargo(
                        rs.getString("id"),
                        rs.getString("matricula_vehiculo"),
                        rs.getString("id_servicio"),
                        rs.getDouble("precio_total"),
                        rs.getString("id_asignacion"),
                        rs.getString("fecha_inicio"),
                        rs.getString("fecha_finalizado"),
                        rs.getString("cod_item")
                    );
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar cliente por DNI: " + e.getMessage());
            }
            return encargo; 
        }
        return null; 
    }

    public ArrayList<Encargo> obtenerTodos() {
        Connection conexion = ConexionDB.conectar();
        ArrayList<Encargo> encargos = new ArrayList<>();

        if (conexion != null) {
            
            String query = "SELECT * FROM Encargo";

            try (
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Encargo encargo = new Encargo(
                        rs.getString("id"),
                        rs.getString("matricula_vehiculo"),
                        rs.getString("id_servicio"),
                        rs.getDouble("precio_total"),
                        rs.getString("id_asignacion"),
                        rs.getString("fecha_inicio"),
                        rs.getString("fecha_finalizado"),
                        rs.getString("cod_item")
                    );
                    encargos.add(encargo);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener todos los clientes: " + e.getMessage());
            }
        }
        return encargos;
    }
}
*/