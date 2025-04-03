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
            sc.nextLine();
            
            
            switch (opcion) {
                case "1", "registrar" -> { registrarItem(); }
                case "2", "modificar" -> { modificarItem(); }
                case "3", "eliminar" -> { eliminarItem(); }
                case "4", "mostrar" -> { mostrarItems(); }
                case "0" -> { System.out.println("Volviendo al menu anterior. ");}
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }

        } while (!opcion.equalsIgnoreCase("0"));
    }

    public void registrarItem() {
        
        String nombre;
        int id_proveedor;
        int minimo;
        double precio;
        int cantidad;
        Item item;

        System.out.print("Ingrese el nombre: ");
        nombre = sc.nextLine();
        System.out.print("Ingrese el id del proveedor: ");
        id_proveedor = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese la cantidad mínima que quiere en el almacen: ");
        minimo = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese la cantidad que tiene en el almacen: ");
        cantidad = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el precio del item: ");
        precio = sc.nextDouble();
        sc.nextLine();
         
        item = new Item( nombre, id_proveedor, minimo, cantidad, precio);
       
        ItemDAO itemDAO = new ItemDAO();
        if (itemDAO.insertar(item)) {
            System.out.println("Item registrado correctamente");
        } else {
            System.out.println("No se ha podido registrar el item");
        }
    }
    public void modificarItem() {
        String opcion;
        String columna;
        String valor;
        String nombre;

        Item codigo_modificar;
        do {
            System.out.println("Qué desea modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Id del proveedor");
            System.out.println("3. Cantidad mínima");
            System.out.println("4. Precio del producto");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.nextLine();

            if(opcion.equalsIgnoreCase("0")) {
                break;
            }
            
            System.out.println("Ingrese el nombre del item a modificar: ");
            System.out.print("--> ");
            nombre = sc.nextLine();

            ItemDAO itemDAO = new ItemDAO();
            codigo_modificar = itemDAO.buscarMostrar(nombre);
            
            
            if (codigo_modificar == null) {
                System.out.println("ERR0R: No se encontró el cliente");
            } else {
                switch (opcion) {
                   
                    case "1", "nombre" -> {
                        System.out.print("Ingrese el nuevo nombre: ");
                        String nombre_nuevo = sc.next();
                        sc.nextLine();
                        columna = "nombre";
                        valor = nombre_nuevo;
                        boolean actualizado = itemDAO.actualizar(columna, nombre, valor);
                        if (actualizado) {
                            System.out.println("Nombre actualizado correctamente.");
                        } else {
                            System.out.println("Error al actualizar el nombre.");
                        }
                        opcion = "0";

                    }
                    case "2", "id_proveedor" -> {
                        System.out.print("Ingrese el nuevo id del proveedor: ");
                        String id_proveedor_nuevo = sc.next();
                        sc.nextLine();
                        columna = "id_proveedor";
                        valor = id_proveedor_nuevo;
                        boolean actualizado = itemDAO.actualizar(columna, nombre, valor);
                        if (actualizado) {
                            System.out.println("Id del proveedor actualizado correctamente.");
                        } else {
                            System.out.println("Error al actualizar el apellido id del proveedor.");
                        }
                        opcion = "0";

                    }
                    case "3", "minimo" -> {
                        System.out.print("Ingrese el valor mínimo que desea tener: ");
                        String minimo_nuevo = sc.nextLine();
                        columna = "minimo";
                        valor = minimo_nuevo;
                        boolean actualizado = itemDAO.actualizar(columna, nombre, valor);
                        if (actualizado) {
                            System.out.println("Valor mínimo actualizada correctamente.");
                        } else {
                            System.out.println("Error al actualizar el valor mínimo.");
                        }
                        opcion = "0";

                    }
                    case "4", "precio" -> {
                        System.out.print("Ingrese el nuevo precio: ");
                        String precio_nuevo = sc.nextLine();
                        columna = "precio";
                        valor = precio_nuevo; 
                        boolean actualizado = itemDAO.actualizar(columna, nombre, valor);
                        if (actualizado) {
                            System.out.println("Precio actualizado correctamente.");
                        } else {
                            System.out.println("Error al actualizar el precio.");
                        }
                        opcion = "0";

                    }
                    
                    default -> {
                        System.out.println("ERR0R: No se reconoció esa opción");
                    }
                }
            }

        } while (!opcion.equalsIgnoreCase("0"));

    }
    public void eliminarItem() {
        String nombre;
        Item item;
        System.out.print("Ingrese el nombre del item que quiere eliminar: ");
        nombre = sc.nextLine();
        ItemDAO itemDAO = new ItemDAO();
        item = itemDAO.buscarMostrar(nombre);

        if (item == null) {
            System.out.println("ERR0R: No se encontró al item con ese nombre");
        } else {
            System.out.println("Está por eliminar al siguiente item: ");
            System.out.println("Nombre: " + item.getNombre() );
            System.out.println("Id del proveedor: " + item.getIdProveedor() );
            System.out.println("---¿Está seguro?---");
            String opcion;
            boolean seguir = true;
            do { 
                System.out.println("1. SI / 2. NO");
                opcion = sc.next();
                sc.nextLine();
                switch (opcion) {
                    case "1", "si", "SI" -> {
                        if (itemDAO.buscar(nombre).equals(nombre)) {
                            itemDAO.eliminar(nombre);
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
            System.out.println("No hay items registrados");
        } else {
            System.out.println("Items: ");
            for (Item item : array_items) {
                System.out.println(item); // `toString()
            }
        }
    }    
}