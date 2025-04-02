package view;

import dao.ItemDAO;
import java.util.ArrayList;
import java.util.Scanner;
import model.Item;

public class ItemVIEW {

    public ArrayList<Item> array_items = new ArrayList<>();
    
    Scanner sc = new Scanner(System.in);

    public void menu() {
        String opcion;
        do { 
            System.out.println("Qué desea hacer con los items?");
            System.out.println("1. Registrar Item");
            System.out.println("2. Modificar Item");
            System.out.println("3. Eliminar Item");
            System.out.println("4. Mostrar Items");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next();
            
            
            switch (opcion) {
                case "1", "registrar" -> { registrarItem(); }
                case "2", "modificar" -> { modificarItem(); }
                case "3", "eliminar" -> { eliminarItem(); }
                case "4", "mostrar" -> { mostrarItems(); }
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }

        } while (!opcion.equalsIgnoreCase("0"));
    }

    public void registrarItem() {
        String codigo;
        String nombre;
        String id_proveedor;
        int minimo;
        double precio;
        int cantidad;
        Item item;

      
        System.out.print("Ingrese el código: ");
        codigo = sc.nextLine();
        
        System.out.print("Ingrese el nombre: ");
        nombre = sc.nextLine();
        System.out.print("Ingrese el id del proveedor: ");
        id_proveedor = sc.nextLine();
        System.out.print("Ingrese la cantidad mínima que quiere en el almacen: ");
        minimo = sc.nextInt();
        System.out.print("Ingrese la cantidad que tiene en el almacen: ");
        cantidad = sc.nextInt();
        System.out.print("Ingrese el precio del item: ");
        precio = sc.nextDouble();
         
        item = new Item(codigo, nombre, id_proveedor, minimo, cantidad, precio);
       
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.insertar(item);
    }
    public void modificarItem() {
        String opcion;
        String columna;
        String valor;
        String codigo;

        Item codigo_modificar;
        do {
            System.out.println("Qué desea modificar?");
            System.out.println("1. Código");
            System.out.println("2. Nombre");
            System.out.println("3. Id del proveedor");
            System.out.println("4. Cantidad mínima");
            System.out.println("5. Precio del producto");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.nextLine();
            
            System.out.println("Ingrese el código del item a modificar: ");
            System.out.print("--> ");
            codigo = sc.nextLine();

            ItemDAO itemDAO = new ItemDAO();
            codigo_modificar = itemDAO.buscarMostrar(codigo);
            
            
            if (codigo_modificar == null) {
                System.out.println("ERR0R: No se encontró el cliente");
            } else {
                switch (opcion) {
                   
                    case "1", "nombre" -> {
                        System.out.print("Ingrese el nuevo nombre: ");
                        String nombre_nuevo = sc.next();
                        columna = "nombre";
                        valor = nombre_nuevo;
                        boolean actualizado = itemDAO.actualizar(columna, codigo, valor);
                        if (actualizado) {
                            System.out.println("Nombre actualizado correctamente.");
                        } else {
                            System.out.println("Error al actualizar el nombre.");
                        }
                    }
                    case "2", "id_proveedor" -> {
                        System.out.print("Ingrese el nuevo id del proveedor: ");
                        String id_proveedor_nuevo = sc.next();
                        columna = "id_proveedor";
                        valor = id_proveedor_nuevo;
                        boolean actualizado = itemDAO.actualizar(columna, codigo, valor);
                        if (actualizado) {
                            System.out.println("Id del proveedor actualizado correctamente.");
                        } else {
                            System.out.println("Error al actualizar el apellido id del proveedor.");
                        }
                    }
                    case "3", "minimo" -> {
                        System.out.print("Ingrese el valor mínimo que desea tener: ");
                        String minimo_nuevo = sc.nextLine();
                        columna = "minimo";
                        valor = minimo_nuevo;
                        boolean actualizado = itemDAO.actualizar(columna, codigo, valor);
                        if (actualizado) {
                            System.out.println("Valor mínimo actualizada correctamente.");
                        } else {
                            System.out.println("Error al actualizar el valor mínimo.");
                        }
                    }
                    case "4", "precio" -> {
                        System.out.print("Ingrese el nuevo precio: ");
                        String precio_nuevo = sc.nextLine();
                        columna = "precio";
                        valor = precio_nuevo; 
                        boolean actualizado = itemDAO.actualizar(columna, codigo, valor);
                        if (actualizado) {
                            System.out.println("Teléfono actualizado correctamente.");
                        } else {
                            System.out.println("Error al actualizar el teléfono.");
                        }
                    }
                    
                    default -> {
                        System.out.println("ERR0R: No se reconoció esa opción");
                    }
                }
            }

        } while (!opcion.equalsIgnoreCase("0"));

    }
    public void eliminarItem() {
        String codigo;
        Item item;
        System.out.print("Ingrese el codigo del item que quiere eliminar: ");
        codigo = sc.next();
        ItemDAO itemDAO = new ItemDAO();
        item = itemDAO.buscarMostrar(codigo);

        if (item == null) {
            System.out.println("ERR0R: No se encontró al cliente con ese DNI");
        } else {
            System.out.println("Está por eliminar al siguiente cliente: ");
            System.out.println("Código: " + item.getCodigo() + ", nombre: " + item.getNombre() );
            System.out.println("Id del proveedor: " + item.getIdProveedor() );
            System.out.println("---¿Está seguro?---");
            String opcion;
            boolean seguir = true;
            do { 
                System.out.println("1. SI / 2. NO");
                opcion = sc.next();
                switch (opcion) {
                    case "1", "si", "SI" -> {
                        if (itemDAO.buscar(codigo).equals(codigo)) {
                            itemDAO.eliminar(codigo);
                            System.out.println("Item eliminado");
                            seguir = false;
                            
                        } else {
                            System.out.println("No se encuentra item");
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
    public void mostrarItems() {
        ItemDAO itemDAO = new ItemDAO();
        array_items = itemDAO.obtenerTodos(); // Llenar la lista con los clientes de la BD

        if (array_items == null) {
            array_items = new ArrayList<>(); // Evitar que sea null en caso de error
        }

        if (array_items.isEmpty()) {
            System.out.println("No hay clientes registrados");
        } else {
            System.out.println("Clientes: ");
            for (Item item : array_items) {
                System.out.println(item); // `toString()
            }
        }
    }    
}