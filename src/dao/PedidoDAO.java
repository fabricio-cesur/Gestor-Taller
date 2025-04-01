package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Pedido;

public class PedidoDAO {

    public boolean insertar(Pedido pedido) {
        Connection conexion = ConexionDB.conectar(); 
        if (conexion != null) { 
            //Consulta para obtener el precio por unidad
            String seleccionar_precio = "SELECT precio FROM Item WHERE codigo  = " + pedido.getCodigoItem() ;
            int precio_unidad = 0;
            try ( PreparedStatement stmt = conexion.prepareStatement(seleccionar_precio)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    precio_unidad =  rs.getInt("precio");
                    
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener precio por unidad: " + e.getMessage());
            }
            String query = "INSERT INTO Pedido (codigo_item, cantidad, precio, fecha_pedido) VALUES ("
                    + pedido.getCodigoItem() + ", " + pedido.getCantidadSolicitada() + ", " + (precio_unidad * pedido.getCantidadSolicitada()) +
                    ", " + pedido.getFechaPedido() + ")"; 
            
            try (PreparedStatement stmt = conexion.prepareStatement(query)) { 
                
                stmt.executeUpdate(); 

                return true; 
            } catch (SQLException e) { 
                System.out.println("Error al agregar pedido: " + e.getMessage()); 
            } 
        }
        return false;
    }

    public boolean actualizar(String columna, int id, int valor ) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Pedido SET " + columna + "=" + valor + " WHERE id = " + id; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                                
                int filas_afectadas = stmt.executeUpdate();

                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al actualizar cliente: " + e.getMessage());
            } 
        }
        return false;
    }
    public boolean actualizarEstado(int id_pedido, boolean completado) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Pedido SET completado = " + completado + " WHERE id_pedido = " + id_pedido; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                                                
                int filasAfectadas = stmt.executeUpdate();
                return filasAfectadas == 1;
            } catch (SQLException e) {
                System.out.println(" Error al actualizar estado del pedido: " + e.getMessage());
            } 
        }
        return false;
    }
    public boolean  eliminar(int id) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Pedido WHERE id = " + id;
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                int filas_afectadas = stmt.executeUpdate();
                
                return filas_afectadas == 1;
            } catch (SQLException e) {
                System.out.println("Error al eliminar el pedido: " + e.getMessage());
            }
        }
        return false;
    }

    public int buscar(int id ) {
        Connection conexion = ConexionDB.conectar();
        int id_busqueda = 0;

        if (conexion != null) {
            String query = "SELECT * FROM Pedido WHERE id = " + id;
            try ( PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    id_busqueda =  rs.getInt("id");
                    
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar el pedido por id: " + e.getMessage());
            }
            
            return id_busqueda; 
        }
        return id_busqueda; 
    }

    public Pedido buscarMostrar(int id_pedido) {
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            Pedido pedido = null;
            String query = "SELECT * FROM Pedido WHERE id_pedido = " + id_pedido;
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                                
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Date fechaPedidoSQL = rs.getDate("fecha_pedido");
                    LocalDate fechaPedido = fechaPedidoSQL != null ? fechaPedidoSQL.toLocalDate() : null;
                    
                    pedido = new Pedido(
                        rs.getString("codigo_item"),
                        rs.getInt("cantidad"),
                        fechaPedido,
                        rs.getBoolean("completado")
                    );
                    pedido.setCompletado(rs.getBoolean("completado"));
                }
            } catch (SQLException e) {
                System.out.println(" Error al buscar pedido: " + e.getMessage());
            }
            return pedido; 
        }
        return null; 
    }

    public ArrayList<Pedido> obtenerTodos() {
        Connection conexion = ConexionDB.conectar();
        ArrayList<Pedido> pedidos = new ArrayList<>();

        if (conexion != null) {
            String query = "SELECT * FROM Pedido";

            try (PreparedStatement stmt = conexion.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Date fechaPedidoSQL = rs.getDate("fecha_pedido");
                    LocalDate fechaPedido = fechaPedidoSQL != null ? fechaPedidoSQL.toLocalDate() : null;
                    
                    Pedido pedido = new Pedido(
                       rs.getString("codigo_item"),
                        rs.getInt("cantidad"),
                        fechaPedido,
                        rs.getBoolean("completado")
                    );
                    
                    pedidos.add(pedido);
                }
            } catch (SQLException e) {
                System.out.println(" Error al obtener todos los pedidos: " + e.getMessage());
            }
        }
        return pedidos;
    }
}
