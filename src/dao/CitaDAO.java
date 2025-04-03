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
            String query = "INSERT INTO Cita (fecha, hora, matricula_coche) VALUES (?, ?, ?)" ; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) { 
                stmt.setString(1, cita.getFecha());
                stmt.setString(2, cita.getHora() + ":00");
                stmt.setString(3, cita.getVehiculoMatricula());
                stmt.executeUpdate(); // Ejecuta la consulta de inserción 

                System.out.println("Cita guardada correctamente: " ); 
                return true; 
            } catch (SQLException e) { 
                System.out.println("Error al agregar la cita: " + e.getMessage()); 
            } 
        }
        return false;
    }

    public boolean actualizar(String columna, String matricula_coche, String valor, String fecha) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Cita SET " + columna + " = ? WHERE matricula_coche = ? AND fecha = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, valor);
                stmt.setString(2, matricula_coche);
                stmt.setString(3, fecha); // Asumiendo que 'fecha' es un String en formato compatible con tu base de datos

                int filas_afectadas = stmt.executeUpdate();

                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al actualizar la cita: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean eliminar(String matricula_coche, String fecha) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Cita WHERE matricula_coche = ? AND fecha = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, matricula_coche);
                stmt.setString(2, fecha); // Asumiendo que 'fecha' es un String en formato compatible con tu base de datos

                int filas_afectadas = stmt.executeUpdate();

                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al eliminar la cita: " + e.getMessage());
            }
        }
        return false;
    }

     public String buscar(String matricula) {
        Connection conexion = ConexionDB.conectar();
        String matricula_busqueda = null;

        if (conexion != null) {
            
            String query = "SELECT * FROM Cita WHERE matricula_coche = " + matricula;
            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    matricula_busqueda =  rs.getString("matricula_coche");
                    
                }
            } catch (SQLException e) {
                System.out.println("Error al la cita por matricula: " + e.getMessage());
            }
            
            return matricula_busqueda; 
        }
        return null; 
    }

    public Cita buscarMostrar(String matricula_coche, String fecha) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            Cita cita = null;
            String query = "SELECT * FROM Cita WHERE matricula_coche = ? AND fecha = ?";

            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, matricula_coche);
                stmt.setString(2, fecha);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    cita = new Cita(
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getString("matricula_coche")
                    );
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar cita por matrícula y fecha: " + e.getMessage());
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
                        
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getString("matricula_coche")
                    );
                    citas.add(cita);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener todas las citas: " + e.getMessage());
            }
        }
        return citas;
    }
}
 

