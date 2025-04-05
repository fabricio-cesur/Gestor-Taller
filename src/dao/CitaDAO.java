package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cita;

public class CitaDAO {

    //Metodo crear una nueva cita
    public boolean  insertar(Cita cita) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            try {
                //Contar citas existentes para la fecha
                String queryContar = "SELECT COUNT(*) FROM Cita WHERE fecha = ?";
                PreparedStatement stmtContar = conexion.prepareStatement(queryContar);
                stmtContar.setString(1, cita.getFecha());
                ResultSet resultados = stmtContar.executeQuery();

                resultados.next();
                int cantidadCitas = resultados.getInt(1);
                // En caso de que haya una cantidad igual a 10 no se puede pedir cita para ese dia
                if (cantidadCitas == 10) {
                    System.out.println("No se pueden crear más citas para el " + cita.getFecha() + ". Se ha alcanzado el límite.");
                    return false; // No se inserta la cita
                }

                //Insertar la nueva cita 
                String queryInsertar = "INSERT INTO Cita (fecha, hora, matricula_coche) VALUES (?, ?, ?)";
                PreparedStatement stmtInsertar = conexion.prepareStatement(queryInsertar);
                stmtInsertar.setString(1, cita.getFecha());
                stmtInsertar.setString(2, cita.getHora() + ":00");
                stmtInsertar.setString(3, cita.getVehiculoMatricula());
                stmtInsertar.executeUpdate();

                System.out.println("Cita guardada correctamente: ");
                return true;

            } catch (SQLException e) { 
                System.out.println("Error al agregar la cita: " + e.getMessage()); 
            } 
        }
        return false;
    }
    //Metodo para actualizar una cita filtrando por matricula y fecha
    public boolean actualizar(String columna, String matricula_coche, String valor, String fecha) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Cita SET " + columna + " = ? WHERE matricula_coche = ? AND fecha = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, valor);
                stmt.setString(2, matricula_coche);
                stmt.setString(3, fecha); 

                int filas_afectadas = stmt.executeUpdate();

                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al actualizar la cita: " + e.getMessage());
            }
        }
        return false;
    }

    //Metodo para eliminar una cita filtrando por matricula y fecha
    public boolean eliminar(String matricula_coche, String fecha) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Cita WHERE matricula_coche = ? AND fecha = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, matricula_coche);
                stmt.setString(2, fecha); 

                int filas_afectadas = stmt.executeUpdate();
                
                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al eliminar la cita: " + e.getMessage());
            }
        }
        return false;
    }
    //Metodo para buscar una cita por matricula
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
    //Metodo para buscar un objeto Cita filtrando por matriucla y fecha
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
    //Metodo para obtener un arrayList de todas las citas
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
 

