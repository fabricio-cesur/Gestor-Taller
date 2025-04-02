package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Vehiculo;

public class VehiculoDAO {

    public boolean insertar(Vehiculo vehiculo) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            String query = "INSERT INTO Vehiculo (matricula, modelo, marca, ano, dni_cliente) VALUES (" + vehiculo.getMatricula() + ", " + vehiculo.getModelo() + ", " 
            + vehiculo.getMarca() + ", " + vehiculo.getAno() +", " +  vehiculo.getDniCliente() +");"; 

            try (PreparedStatement stmt = conexion.prepareStatement(query)) { 
                
                stmt.executeUpdate(); // Ejecuta la consulta de inserción 

                return true;
            } catch (SQLException e) { 
                System.out.println("Error al agregar vehículo: " + e.getMessage()); 
            } 
        }
        return false;
    }

    public boolean actualizar(String columna, String matricula, String valor ) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Vehiculo SET " + columna + "=" + valor + " WHERE matricula = " + matricula; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                                
                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas == 1) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Error al actualizar vehiculo: " + e.getMessage());
            } 
        }
        return false;
    }

    public boolean eliminar(String matricula) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Vehiculo WHERE matricula = " + matricula ;
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
               
                int filasAfectadas = stmt.executeUpdate();
                
                if (filasAfectadas == 1) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar el vehículo: " + e.getMessage());
            }
        }
        return false;
    }

    public String buscar(String matricula) {
        Connection conexion = ConexionDB.conectar();
        String matricula_busqueda = null;
        if (conexion != null) {
            
            String query = "SELECT * FROM Vehiculo WHERE matricula = " + matricula;

            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    matricula_busqueda = rs.getString("matricula");
                        
                    
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar vehiculo por DNI: " + e.getMessage());
            }
            return matricula_busqueda;
        }
        return null; 
    }

    public Vehiculo buscarMostrar(String matricula) {
        Connection conexion = ConexionDB.conectar();
        
        if (conexion != null) {
            Vehiculo vehiculo = null;
            String query = "SELECT * FROM Vehiculo WHERE matricula = " + matricula;

            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    vehiculo = new Vehiculo(
                        rs.getString("matricula"),
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getString("ano"),
                        rs.getString("dni_cliente")
                    );
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar vehiculo por DNI: " + e.getMessage());
            }
            return vehiculo;
        }
        return null; 
    }

    public ArrayList<Vehiculo> obtenerTodos() {
        Connection conexion = ConexionDB.conectar();
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();

        if (conexion != null) {
            String query = "SELECT * FROM Vehiculo";

            try (
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Vehiculo vehiculo = new Vehiculo(
                        rs.getString("matricula"),
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getString("ano"),
                        rs.getString("dni_cliente")
                    );
                    vehiculos.add(vehiculo);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener todos los vehículos: " + e.getMessage());
            }
        }        
        return vehiculos;
    }
}

