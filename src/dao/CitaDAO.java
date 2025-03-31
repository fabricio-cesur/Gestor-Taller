package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cita;

public class CitaDAO {
   public boolean  insertar(Cita cita) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            String query = "INSERT INTO Cita (fecha, hora, matricula_coche, id_encargo) VALUES (" + cita.getFecha() + ", " + cita.getHora() + ", " +
            cita.getVehiculoMatricula() + ", " + cita.getIdEncargo() + ");" ; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) { 
               
                stmt.executeUpdate(); // Ejecuta la consulta de inserción 

                return true; 
            } catch (SQLException e) { 
                System.out.println("Error al agregar la cita: " + e.getMessage()); 
            } 
        }
        return false;
    }

    public boolean actualizar(String columna, String matricula_coche, String valor ) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Cita SET " + columna + "=" + valor + " WHERE matricula_coche = " + matricula_coche; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                                
                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas == 1) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Error al actualizar la cita: " + e.getMessage());
            } 
        }
        return false;
    }

    public boolean  eliminar(String matricula_coche) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Cita WHERE matricula_coche = " + matricula_coche;
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                int filasAfectadas = stmt.executeUpdate();
                
                if (filasAfectadas == 1) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar la cita: " + e.getMessage());
            }
        }
        return false;
    }
/* 
    public String buscar(String matricula_coche) {
        Connection conexion = ConexionDB.conectar();
        String cita_busqueda = null;

        if (conexion != null) {
            
            String query = "SELECT * FROM Cita WHERE matricula_coche = " + matricula_coche;
            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    cita_busqueda =  rs.getString("dni");
                    
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar cliente por DNI: " + e.getMessage());
            }
            
            return dni_busqueda; 
        }
        return null; 
    }
*/
    public Cita buscarMostrar(String matricula_coche) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            Cita cita = null;
            String query = "SELECT * FROM Cita WHERE matricula_coche = " + matricula_coche;

            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    cita = new Cita(
                        rs.getString("id"),
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getString("matricula_coche"),
                        rs.getString("id_encargo")
                    );
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar cita por la matrícula del coche: " + e.getMessage());
            }
            return cita; 
        }
        return null; 
    }

    public ArrayList<Cita> obtenerTodos() {
        Connection conexion = ConexionDB.conectar();
        ArrayList<Cita> citas = new ArrayList<>();

        if (conexion != null) {
            
            String query = "SELECT * FROM Cita";

            try (
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Cita cita = new Cita(
                        rs.getString("id"),
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getString("matricula_coche"),
                        rs.getString("id_encargo")
                    );
                    citas.add(cita);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener todos los clientes: " + e.getMessage());
            }
        }
        return citas;
    }
}
 

