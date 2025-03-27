package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.*;

public class ClienteDAO {
    public void insertar(Cliente cliente) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            String query = "INSERT INTO clientes (dni, nombre, apellido, direccion, telefono, cuenta_bancaria) VALUES (?, ?, ?, ?, ?, ?)"; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) { 
                stmt.setString(1, cliente.getDni());  
                stmt.setString(2, cliente.getNombre()); 
                stmt.setString(3, cliente.getApellido()); 
                stmt.setString(4, cliente.getDireccion()); 
                stmt.setString(5, cliente.getTelefono()); 
                stmt.setString(6, cliente.getCuenta_bancaria()); 

                stmt.executeUpdate(); // Ejecuta la consulta de inserción 

                System.out.println("Cliente agregado exitosamente."); 
            } catch (SQLException e) { 
                System.out.println("Error al agregar cliente: " + e.getMessage()); 
            }
            
        }
    }

    public void actualizar(Cliente cliente) {

    }

    public void eliminar(int dni) {

    }
    public Cliente buscarPorDNI(int dni) {

    }
    public List<Cliente> obtenerTodos() {

    }
}
