package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;

public class ClienteDAO {

    public void insertarClienteDAO(Cliente cliente) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            String query = "INSERT INTO clientes (dni, nombre, apellido, direccion, telefono, cuenta_bancaria) VALUES (?, ?, ?, ?, ?, ?)"; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) { 
                stmt.setString(1, cliente.getDni());  
                stmt.setString(2, cliente.getNombre()); 
                stmt.setString(3, cliente.getApellido()); 
                stmt.setString(4, cliente.getDireccion()); 
                stmt.setInt(5, cliente.getTelefono()); 
                stmt.setString(6, cliente.getCuentaBancaria()); 

                stmt.executeUpdate(); // Ejecuta la consulta de inserción 

                System.out.println("Cliente agregado exitosamente."); 
            } catch (SQLException e) { 
                System.out.println("Error al agregar cliente: " + e.getMessage()); 
            } 
        }
    }

    public void actualizarClienteDAO(String columna, String dni, String valor ) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Clientes SET " + columna + "=" + valor + " WHERE dni = " + dni; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, valor);
                
                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas == 1) {
                    System.out.println("Cliente actualizado correctamente.");
                } else {
                    System.out.println("No se encontró el cliente con DNI: " + dni);
                }
            } catch (SQLException e) {
                System.out.println("Error al actualizar cliente: " + e.getMessage());
            } 
        }
    }

    public void eliminar(String dni) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Clientes WHERE dni = " + dni;
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, dni);

                int filasAfectadas = stmt.executeUpdate();
                
                if (filasAfectadas == 1) {
                    System.out.println("Cliente eliminado correctamente.");
                } else {
                    System.out.println("No se encontró el cliente con DNI: " + dni);
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar cliente: " + e.getMessage());
            }
        }
    }

    public Cliente buscarPorDNI(String dni) {
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

        if (conexion != null) {
            ArrayList<Cliente> clientes = new ArrayList<>();
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
            return clientes;
        }
        return null;
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