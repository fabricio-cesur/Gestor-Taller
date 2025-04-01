package view;

import dao.PedidoDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import model.Pedido;

public class PedidoVIEW {

    public ArrayList<Pedido> array_pedidos = new ArrayList<>();
    
    Scanner sc = new Scanner(System.in);
    public void registrarPedido() {
        
        String codigo_item;
        int cantidad_solicitada;
        LocalDate fecha_pedido;
        Boolean completado = false;
        Pedido pedido;
        
        System.out.print("Ingrese el codigo del item: ");
        codigo_item = sc.nextLine();
        System.out.print("Ingrese la cantidad: ");
        cantidad_solicitada = sc.nextInt();
        fecha_pedido = LocalDate.now();
        pedido = new Pedido(codigo_item, cantidad_solicitada, fecha_pedido, completado);
       
        PedidoDAO pedidoDAO = new PedidoDAO();
        pedidoDAO.insertar(pedido);
    }
    public void modificarPedido() {
        String opcion;
        int id;
        String columna;
        int valor;

        Pedido pedido_modificar = null;
        do {
            System.out.println("Qué desea modificar?");
            System.out.println("1. Cantidad");
            System.out.println("2. Modificar estado");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.nextLine();
            
            System.out.println("Ingrese el id del pedido a modificar: ");
            System.out.print("--> ");
            id = sc.nextInt();

            PedidoDAO pedidoDAO = new PedidoDAO();
            pedido_modificar = pedidoDAO.buscarMostrar(id);
            
            
            if (pedido_modificar == null) {
                System.out.println("ERR0R: No se encontró el pedido");
            } else {
                switch (opcion) {
                    //TODO: Añadir validaciones
                    case "1", "id" -> {
                        System.out.print("Ingrese el nuevo DNI: ");
                        int id_nuevo = sc.nextInt();
                        valor = id_nuevo;
                        columna = "id";
                        
                        boolean actualizado = pedidoDAO.actualizar(columna, id, valor);
                        if (actualizado) {
                        System.out.println("Cantidad actualizada correctamente.");
                        } else {
                        System.out.println("Error al actualizar la cantidad.");
                        }
                    }
                    case "2", "estado" -> {
                        int dato;

                        boolean estado = false;
                        System.out.print("Ingrese el nuevo estado: ");
                            dato = sc.nextInt();

                        if ( dato == 1) {
                            estado = true;
                        }else if (dato == 0) {
                            estado = false;
                        }
                        
                        
                        boolean actualizado = pedidoDAO.actualizarEstado(id, estado);
                        if (actualizado) {
                        System.out.println("Cantidad actualizada correctamente.");
                        } else {
                        System.out.println("Error al actualizar la cantidad.");
                        }
                    }
                    
                    default -> {
                        System.out.println("ERR0R: No se reconoció esa opción");
                    }
                }
            }

        } while (!opcion.equalsIgnoreCase("0"));

    }
    public void eliminarPedido() {
        int id;
        Pedido pedido = null;
        System.out.print("Ingrese el id del pedido que quiere eliminar: ");
        id = sc.nextInt();
        PedidoDAO pedidoDAO = new PedidoDAO();
        pedido = pedidoDAO.buscarMostrar(id);

        if (pedido == null) {
            System.out.println("ERR0R: No se encontró el pedido con ese id");
        } else {
            System.out.println("Está por eliminar al siguiente pedido: ");
            System.out.println("ID: " + pedido.getIdPedido());
            System.out.println("Código item: " + pedido.getCodigoItem());
            System.out.println("Cantidad " + pedido.getCantidadSolicitada());
            System.out.println("Fecha: " + pedido.getFechaPedido());
            System.out.println("---¿Está seguro?---");
            String opcion;
            boolean seguir = true;
            do { 
                System.out.println("1. SI / 2. NO");
                opcion = sc.next();
                switch (opcion) {
                    case "1", "si", "SI" -> {
                        if (pedidoDAO.buscar(id) == id ){
                            pedidoDAO.eliminar(id);
                            System.out.println("Pedido eliminado");
                            seguir = false;
                            
                        } else {
                            System.out.println("No se encuentra el pedido");
                        }
                    }
                    case "2", "no", "NO" -> {
                        System.out.println("Abortando...");
                        seguir = false;
                    }
                
                    default -> {
                        System.out.println("No se reconoció esa opción.");
                    }
                }
            } while (seguir);
        }
    }
    public void mostrarPedidos() {
        PedidoDAO pedidoDAO = new PedidoDAO();
        array_pedidos = pedidoDAO.obtenerTodos(); // Llenar la lista con los clientes de la BD

        if (array_pedidos == null) {
         array_pedidos = new ArrayList<>(); // Evitar que sea null en caso de error
        }

        if (array_pedidos.isEmpty()) {
            System.out.println("No hay clientes registrados");
        } else {
            System.out.println("Clientes: ");
            for (Pedido pedido : array_pedidos) {
                System.out.println(pedido); // `toString()
            }
        }
    }    
}