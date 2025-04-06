package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Encargo;
import model.Servicio;
import view.Formateo;

public class EncargoDAO {

    public Formateo format = new Formateo();

    public boolean insertar(Encargo encargo) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            String query = "INSERT INTO Encargo (matricula_coche) VALUES (?);";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) { 
               stmt.setString(1, encargo.getMatricula());
                stmt.executeUpdate(); // Ejecuta la consulta de inserción 

                return true; 
            } catch (SQLException e) { 
                System.out.println("Error al agregar encargo: " + e.getMessage()); 
            } 
        }
        return false;
    }
    public boolean insertarServicio(int id_encargo, int id_servicio) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) {
            String query = "INSERT INTO Servicio_Encargo (id_encargo, id_servicio) VALUES (?, ?);";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) { 
                stmt.setInt(1, id_encargo);
                stmt.setInt(2, id_servicio);
                stmt.executeUpdate(); // Ejecuta la consulta de inserción 

                return true; 
            } catch (SQLException e) { 
                System.out.println("Error al insertar servicio en Servicio_Encargo: " + e.getMessage()); 
            } 
        }
        return false;
    }
    public boolean quitarServicio(int id_encargo, int id_servicio) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            String query = "DELETE FROM Servicio_Encargo WHERE id_encargo = ? AND id_servicio = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) { 
                stmt.setInt(1, id_encargo);
                stmt.setInt(2, id_servicio);
                int filas_afectadas = stmt.executeUpdate(); // Ejecuta la consulta de inserción 

                return filas_afectadas == 1; 
            } catch (SQLException e) { 
                System.out.println("Error al insertar servicio en Servicio_Encargo: " + e.getMessage()); 
            } 
        }
        return false;
    }
    //Este método actualiza cualquier dato
    public boolean actualizar(String columna, int id, String valor ) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Encargo SET " + columna + " = ? WHERE id = ?"; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, valor);
                stmt.setInt(2, id);
                int filas_afectadas = stmt.executeUpdate();

                return filas_afectadas == 1;
                
            } catch (SQLException e) {
                System.out.println("Error al actualizar encargo: " + e.getMessage());
            } 
        }
        return false;
    }
    //Este método actualiza el precio total según los servicios que tenga el encargo
    public boolean actualizarPrecioTotal(int id_encargo) {
        ArrayList<Servicio> servicios_encargo = obtenerServicios(id_encargo);
        Double precio_total = 0.0;
        for (Servicio servicio : servicios_encargo) {
            precio_total += servicio.getPrecio();
        }
        if (actualizar("precio_total", id_encargo, String.valueOf(precio_total))) {
            return true;
        } else {
            return false;
        }
    }
    public boolean eliminar(int id) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Encargo WHERE id = " + id;
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                int filas_afectadas = stmt.executeUpdate();
                
                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al eliminar encargo: " + e.getMessage());
            }
        }
        return false;
    }

    public Encargo obtenerPorID(int id) {
        Connection conexion = ConexionDB.conectar();

        Encargo encargo = null;
        if (conexion != null) {
            String query = "SELECT * FROM Encargo WHERE id = " + id;
            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    if (rs.next()) {
                        encargo = new Encargo(rs.getString("matricula_coche"));
                        if (rs.getString("id") != null) {
                            encargo.setId(rs.getInt("id"));
                        }
                        if (rs.getString("precio_total") != null) {
                            encargo.setPrecioTotal(rs.getDouble("precio_total"));
                        }
                        if (rs.getString("fecha_inicio") != null) {
                            encargo.setFechaInicio(format.stringToDate(rs.getString("fecha_inicio")));
                        }
                        if (rs.getString("fecha_finalizado") != null) {
                            encargo.setFechaFinalizado(format.stringToDate(rs.getString("fecha_finalizado")));
                        }
                        // O será false por default o lo habrán cambiado a completado
                        encargo.setCompletado(rs.getBoolean("completado"));
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar encargo por Matricula: " + e.getMessage());
            }
            
            return encargo; 
        } else {
            System.out.println("ERR0R: No se econtró el id del encargo");
            return null; 
        }
    }

    public Encargo obtener(String matricula) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            Encargo encargo = null;
            String query = "SELECT * FROM Encargo WHERE matricula_coche = '" + matricula + "'";

            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    encargo = new Encargo(rs.getString("matricula_coche"));
                    if (rs.getString("id") != null) {
                        encargo.setId(rs.getInt("id"));
                    }
                    if (rs.getString("precio_total") != null) {
                        encargo.setPrecioTotal(rs.getDouble("precio_total"));
                    }
                    if (rs.getString("fecha_inicio") != null) {
                        encargo.setFechaInicio(format.stringToDate(rs.getString("fecha_inicio")));
                    }
                    if (rs.getString("fecha_finalizado") != null) {
                        encargo.setFechaFinalizado(format.stringToDate(rs.getString("fecha_finalizado")));
                    }
                    // O será false por default o lo habrán cambiado a completado
                    encargo.setCompletado(rs.getBoolean("completado"));
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar encargo por matrícula: " + e.getMessage());
            }
            return encargo; 
        }
        return null; 
    }
    public Encargo obtenerUltimo(String matricula) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            Encargo encargo = null;
            String query = "SELECT * FROM Encargo WHERE matricula_coche = '" + matricula + "' ORDER BY id DESC LIMIT 1";

            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    encargo = new Encargo(rs.getString("matricula_coche"));
                    // no hace falta revisar que sea null porque de ser null
                    // getInt() y getDouble() devuelven 0
                    encargo.setId(rs.getInt("id"));
                    encargo.setPrecioTotal(rs.getDouble("precio_total"));
                    if (rs.getString("fecha_inicio") != null) {
                        encargo.setFechaInicio(format.stringToDate(rs.getString("fecha_inicio")));
                    }
                    if (rs.getString("fecha_finalizado") != null) {
                        encargo.setFechaFinalizado(format.stringToDate(rs.getString("fecha_finalizado")));
                    }
                    // O será false por default o lo habrán cambiado a completado
                    encargo.setCompletado(rs.getBoolean("completado"));
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar encargo por matrícula: " + e.getMessage());
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
                    Encargo encargo = new Encargo(rs.getString("matricula_coche"));
                    if (rs.getString("id") != null) {
                        encargo.setId(rs.getInt("id"));
                    }
                    if (rs.getString("precio_total") != null) {
                        encargo.setPrecioTotal(rs.getDouble("precio_total"));
                    }
                    if (rs.getString("fecha_inicio") != null) {
                        encargo.setFechaInicio(format.stringToDate(rs.getString("fecha_inicio")));
                    }
                    if (rs.getString("fecha_finalizado") != null) {
                        encargo.setFechaFinalizado(format.stringToDate(rs.getString("fecha_finalizado")));
                    }
                    // O será false por default o lo habrán cambiado a completado
                    encargo.setCompletado(rs.getBoolean("completado"));
                    encargos.add(encargo);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener todos los encargos: " + e.getMessage());
            }
        }
        return encargos;
    }

    public ArrayList<Servicio> obtenerServicios(int id_encargo) {
        Connection conexion = ConexionDB.conectar();
        ArrayList<Servicio> servicios = new ArrayList<>();

        if (conexion != null) {
            String query = "SELECT * FROM Servicio "
            +"INNER JOIN Servicio_Encargo ON Servicio.id = Servicio_Encargo.id_servicio" + 
            " WHERE Servicio_Encargo.id_encargo = " + id_encargo;
            try (
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {

                    Servicio servicio = new Servicio(
                        rs.getString("nombre"), rs.getString("descripcion"), 
                        rs.getInt("id_item"), rs.getDouble("precio")
                    );
                    servicio.setId(rs.getInt("id"));
                    servicios.add(servicio);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener los servicios: " + e.getMessage());
            }
        }
        return servicios;
    }
}