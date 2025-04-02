package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Encargo;

public class EncargoDAO {

    public boolean insertar(Encargo encargo) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            String query = "INSERT INTO Encargo (matricula_coche, precio_total) VALUES (" + encargo.getMatricula() + ", " + encargo.getPrecioTotal() + ");";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) { 
               
                stmt.executeUpdate(); // Ejecuta la consulta de inserción 

                return true; 
            } catch (SQLException e) { 
                System.out.println("Error al agregar cliente: " + e.getMessage()); 
            } 
        }
        return false;
    }

    public boolean actualizar(String columna, int id, String valor ) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Encargo SET " + columna + "=" + valor + " WHERE id = " + id; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                                
                int filas_afectadas = stmt.executeUpdate();

                return filas_afectadas == 1;
                
            } catch (SQLException e) {
                System.out.println("Error al actualizar cliente: " + e.getMessage());
            } 
        }
        return false;
    }

    public boolean eliminar(int id) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Encargo WHERE id = " + id;
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                int filas_afectadas = stmt.executeUpdate();
                
                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al eliminar cliente: " + e.getMessage());
            }
        }
        return false;
    }

    public String buscarMatricula(String matricula) {
        Connection conexion = ConexionDB.conectar();
        String matricula_busqueda = null;

        if (conexion != null) {
            String query = "SELECT * FROM Encargo WHERE matricula = " + matricula;
            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    matricula_busqueda =  rs.getString("matricula");
                    
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar encargo por Matricula: " + e.getMessage());
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
                        rs.getString("matricula_vehiculo"),
                        rs.getDouble("precio_total")
                    );
                    if (rs.getString("fecha_inicio") != null) {
                        encargo.setFechaInicio(rs.getString("fecha_inicio"));
                    }
                    if (rs.getString("fecha_finalizado") != null) {
                        encargo.setFechaFinalizado(rs.getString("fecha_finalizado"));
                    }
                    // O será false por default o lo habrán cambiado a completado
                    encargo.setCompletado(rs.getBoolean("completado"));
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar encargo por id: " + e.getMessage());
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
                        rs.getString("matricula_vehiculo"),
                        rs.getDouble("precio_total")
                    );
                    if (rs.getString("fecha_inicio") != null) {
                        encargo.setFechaInicio(rs.getString("fecha_inicio"));
                    }
                    if (rs.getString("fecha_finalizado") != null) {
                        encargo.setFechaFinalizado(rs.getString("fecha_finalizado"));
                    }
                    // O será false por default o lo habrán cambiado a completado
                    encargo.setCompletado(rs.getBoolean("completado"));
                    encargos.add(encargo);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener todos los clientes: " + e.getMessage());
            }
        }
        return encargos;
    }
}