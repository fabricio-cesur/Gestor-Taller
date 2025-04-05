package view;

import dao.PedidoDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import model.Pedido;

public class PedidoVIEW {

    public ArrayList<Pedido> array_pedidos = new ArrayList<>();
    
    Scanner sc = new Scanner(System.in);

    //Menu principal de la clase pedido
    public void menu() {
        String opcion;
        do { 
            System.out.println("Qué desea hacer con los pedidos?");
            System.out.println("1. Registrar Pedido");
            System.out.println("2. Modificar Pedido");
            System.out.println("3. Eliminar Pedido");
            System.out.println("4. Mostrar Pedidos");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next();
            
    
            switch (opcion) {
                case "1", "registrar" -> { registrarPedido(); }
                case "2", "modificar" -> { modificarPedido(); }
                case "3", "eliminar" -> { eliminarPedido(); }
                case "4", "mostrar" -> { mostrarPedidos(); }
                case "0" -> { System.out.println("Volviendo al menu anterior. ");}
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }

        } while (!opcion.equalsIgnoreCase("0"));
    }

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
        //Se crea el objeto para introducirlo en la DB
        pedido = new Pedido(codigo_item, cantidad_solicitada, fecha_pedido, completado);
       
        PedidoDAO pedidoDAO = new PedidoDAO();
        pedidoDAO.insertar(pedido);
    }
    //MEnu para modificar el pedido
    public void modificarPedido() {
        String opcion;
        int id;
        String columna;
        int valor;

        Pedido pedido_modificar;

        //Menu modificar pedido
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
            //Obtiene el objeto pedido segun id
            pedido_modificar = pedidoDAO.buscarMostrar(id);
            
            
            if (pedido_modificar == null) {
                System.out.println("ERR0R: No se encontró el pedido");
            } else {
                switch (opcion) {
                    
                    case "1", "id" -> {
                        System.out.print("Ingrese el nuevo id: ");
                        int cantidad_nuevo = sc.nextInt();
                        valor = cantidad_nuevo;
                        columna = "cantidad";
                        //Si consigue actualizar muestra una cosa y sino la otra
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
                        System.out.print("1. Entregado: ");
                        System.out.print("2. No entregado: ");
                        dato = sc.nextInt();
                        //Cambia el dato introducido por un booleano para cambiar el estado
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
                    case "0" -> { System.out.println("Volviendo al menu anterior. ");}
                    default -> {
                        System.out.println("ERR0R: No se reconoció esa opción");
                    }
                }
            }

        } while (!opcion.equalsIgnoreCase("0"));
    }
    
    //MEtodo para eliminar pedido
    public void eliminarPedido() {
        int id;
        Pedido pedido;
        System.out.print("Ingrese el id del pedido que quiere eliminar: ");
        id = sc.nextInt();
        PedidoDAO pedidoDAO = new PedidoDAO();
        //Obtiene el objeto pedido segun el id
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
                        //Compara el datro introducido con el de la base de datos y se elimina
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

    //MEtodo mostrar todos los pedidos
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
                System.out.println(pedido); // imprime los pedidos segun el `toString()
            }
        }
    }    
}