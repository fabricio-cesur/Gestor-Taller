package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Empleado;

public class EmpleadoDAO {

    public boolean  insertar(Empleado empleado) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            String query = "INSERT INTO Empleado (dni, nombre, apellido, direccion, telefono, cuenta_bancaria, salario, cargo) VALUES (" + empleado.getDni() + ", " + empleado.getNombre() + ", " + empleado.getApellido() + ", " +
            empleado.getDireccion() + ", " + empleado.getTelefono() + ", " + empleado.getCuentaBancaria() + empleado.getSalario() + ", " + empleado.getCargo() +");" ; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) { 
               
                stmt.executeUpdate(); // Ejecuta la consulta de inserción 

                return true; 
            } catch (SQLException e) { 
                System.out.println("Error al agregar empleado: " + e.getMessage()); 
            } 
        }
        return false;
    }

    public boolean actualizar(String columna, String dni, String valor ) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Empleado SET " + columna + "=" + valor + " WHERE dni = " + dni; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                                
                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas == 1) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Error al actualizar empleado: " + e.getMessage());
            } 
        }
        return false;
    }

    public boolean eliminar(String dni) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Empleado WHERE dni = " + dni;
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, dni);

                int filasAfectadas = stmt.executeUpdate();
                
                if (filasAfectadas == 1) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar empleado: " + e.getMessage());
            }
        }
        return false;
    }

    public String buscar(String dni) {
        Connection conexion = ConexionDB.conectar();
        String dni_busqueda = null;

        if (conexion != null) {
            Empleado empleado = null;
            String query = "SELECT * FROM Empleado WHERE dni = " + dni;

            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    dni_busqueda =  rs.getString("dni");
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar empleado por DNI: " + e.getMessage());
            }
            return dni_busqueda; 
        }
        return null; 
    }
    public Empleado buscarMostrar(String dni) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            Empleado empleado = null;
            String query = "SELECT * FROM Empleado WHERE dni = " + dni;

            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    empleado = new Empleado(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("direccion"),
                        rs.getInt("telefono"),
                        rs.getString("cuenta_bancaria"),
                        rs.getInt("salario"),
                        rs.getString("cargo")
                    );
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar empleado por DNI: " + e.getMessage());
            }
            return empleado; 
        }
        return null; 
    }

    public ArrayList<Empleado> obtenerTodos() {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            ArrayList<Empleado> empleados = new ArrayList<>();
            String query = "SELECT * FROM Empleado";

            try (
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Empleado empleado = new Empleado(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("direccion"),
                        rs.getInt("telefono"),
                        rs.getString("cuenta_bancaria"),
                        rs.getInt("salario"),
                        rs.getString("cargo")
                    );
                    empleados.add(empleado);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener todos los empleado: " + e.getMessage());
            }
            return empleados;
        }
        return null;
    }
}

