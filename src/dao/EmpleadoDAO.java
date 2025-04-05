package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Empleado;

public class EmpleadoDAO {
    //Metodo para crear un nuevo empleado e insertarlo en la DB
    public boolean  insertar(Empleado empleado) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            String querry = "INSERT INTO Empleado (dni, nombre, apellidos, direccion, telefono, cuenta_bancaria, salario, cargo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
            try (PreparedStatement stmt = conexion.prepareStatement(querry)) {
    
                stmt.setString(1, empleado.getDni());
                stmt.setString(2, empleado.getNombre());
                stmt.setString(3, empleado.getApellido());
                stmt.setString(4, empleado.getDireccion());
                stmt.setInt(5, empleado.getTelefono());
                stmt.setString(6, empleado.getCuentaBancaria());
                stmt.setInt(7, empleado.getSalario());
                stmt.setString(8, empleado.getCargo());
                
    
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Error al agregar empleado: " + e.getMessage());
            }
        }
        return false;
    }
    //Metodo para actualizar un empleado actualiza cualquier columna segun el dato que recibe
    public boolean actualizar(String columna, String dni, String valor ) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Empleado SET " + columna + " = ? WHERE dni = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, valor); 
                stmt.setString(2, dni); 
    
                int filas_afectadas = stmt.executeUpdate();
                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al actualizar empleado: " + e.getMessage());
            } 
        }
        return false;
    }
    //Metodo para eliminar un empleado segun el DNI
    public boolean  eliminar(String dni) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Empleado WHERE dni = " + dni;
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                int filas_afectadas = stmt.executeUpdate();
                
                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al eliminar cliente: " + e.getMessage());
            }
        }
        return false;
    }
    //Metodo para buscar un empleado en la base de datos
    public String buscar(String dni) {
        Connection conexion = ConexionDB.conectar();
        String dni_busqueda = null;

        if (conexion != null) {
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
    //Metodo para obtener un objeto Empleado por su dni
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
                        rs.getString("apellidos"),
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
    //MEtodo para obtener todos los datos de Empleados de la DB
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
                        rs.getString("apellidos"),
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

