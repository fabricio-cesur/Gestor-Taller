package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;

public class ClienteDAO {

    public boolean  insertar(Cliente cliente) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            String query = "INSERT INTO Cliente (dni, nombre, apellidos, direccion, telefono, cuenta_bancaria) VALUES (" + cliente.getDni() + ", " + cliente.getNombre() + ", " + cliente.getApellido() + ", " +
            cliente.getDireccion() + ", " + cliente.getTelefono() + ", " + cliente.getCuentaBancaria() + ");" ; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) { 
               
                stmt.executeUpdate(); // Ejecuta la consulta de inserción 

                return true; 
            } catch (SQLException e) { 
                System.out.println("Error al agregar cliente: " + e.getMessage()); 
            } 
        }
        return false;
    }

    public boolean actualizar(String columna, String dni, String valor ) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Cliente SET " + columna + "=" + valor + " WHERE dni = " + dni; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                                
                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas == 1) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Error al actualizar cliente: " + e.getMessage());
            } 
        }
        return false;
    }

    public boolean  eliminar(String dni) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Cliente WHERE dni = " + dni;
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                int filasAfectadas = stmt.executeUpdate();
                
                if (filasAfectadas == 1) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar cliente: " + e.getMessage());
            }
        }
        return false;
    }

    public String buscar(String dni) {
        Connection conexion = ConexionDB.conectar();
        String dni_busqueda = null;

        if (conexion != null) {
            Cliente cliente = null;
            String query = "SELECT * FROM Cliente WHERE dni = " + dni;
            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    dni_busqueda =  rs.getString("dni");
                    
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar cliente por DNI: " + e.getMessage());
            }
            
            return dni_busqueda; 
        }
        return null; 
    }

    public Cliente buscarMostrar(String dni) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            Cliente cliente = null;
            String query = "SELECT * FROM Cliente WHERE dni = " + dni;

            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    cliente = new Cliente(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getInt("telefono"),
                        rs.getString("cuenta_bancaria")
                    );
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar cliente por DNI: " + e.getMessage());
            }
            return cliente; 
        }
        return null; 
    }

    public ArrayList<Cliente> obtenerTodos() {
        Connection conexion = ConexionDB.conectar();
        ArrayList<Cliente> clientes = new ArrayList<>();

        if (conexion != null) {
            
            String query = "SELECT * FROM Cliente";

            try (
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Cliente cliente = new Cliente(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getInt("telefono"),
                        rs.getString("cuenta_bancaria")
                    );
                    clientes.add(cliente);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener todos los clientes: " + e.getMessage());
            }
        }
        return clientes;
    }
}
