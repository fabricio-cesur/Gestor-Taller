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
            String query = "INSERT INTO clientes (dni, nombre, apellido, direccion, telefono, cuenta_bancaria) VALUES (" + cliente.getDni() + ", " + cliente.getNombre() + ", " + cliente.getApellido() + ", " +
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
            String query = "UPDATE Clientes SET " + columna + "=" + valor + " WHERE dni = " + dni; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, valor);
                
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
            String query = "DELETE FROM Clientes WHERE dni = " + dni;
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, dni);

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

    public Cliente buscar(String dni) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            Cliente cliente = null;
            String query = "SELECT * FROM clientes WHERE dni = " + dni;

            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, dni);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    cliente = new Cliente(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
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
            
            String query = "SELECT * FROM clientes";

            try (
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Cliente cliente = new Cliente(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
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

/*modificarCliente. En el main habria que insertar 
public class Main {
    public static void main(String[] args) {
        ClienteVIEW vista = new ClienteVIEW();
        ClienteDAO dao = new ClienteDAO();

        String[] datos = ClienteVIEW.modificarClienteDAO();
        dao.actualizar(datos[0], datos[2], datos[1]); // columna, dni, valor
        System.out.println("Cliente actualizado correctamente");
    }
}
*/