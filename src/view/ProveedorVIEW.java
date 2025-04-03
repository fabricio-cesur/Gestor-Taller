package view;

import dao.ProveedorDAO;
import java.util.ArrayList;
import java.util.Scanner;
import model.Proveedor;


public class ProveedorVIEW {
    public ArrayList<Proveedor> array_proveedores = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void menu() {
        String opcion;
        do { 
            System.out.println("Qué desea hacer con los proveedores?");
            System.out.println("1. Registrar Proveedor");
            System.out.println("2. Modificar Proveedor");
            System.out.println("3. Eliminar Proveedor");
            System.out.println("4. Mostrar Proveedores");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next();
            sc.nextLine();
            
    
            switch (opcion) {
                case "1", "registrar" -> { registrarProveedor();  }
                case "2", "modificar" -> { modificarProveedor(); }
                case "3", "eliminar" -> { eliminarProveedor(); }
                case "4", "mostrar" -> { mostrarProveedores(); }
                case "0" -> { System.out.println("Volviendo al menu anterior. ");}
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }

        } while (!opcion.equalsIgnoreCase("0"));
    }

    public void registrarProveedor() {
        String nombre;
        String direccion;
        String cuenta_bancaria;
        Proveedor proveedor;
        
        System.out.print("Ingrese el nombre: ");
        nombre = sc.nextLine();
        System.out.print("Ingrese la dirección: ");
        direccion = sc.nextLine();
        System.out.print("Ingrese el número de cuenta bancaria: ");
        cuenta_bancaria = sc.nextLine();
        
        proveedor = new Proveedor(nombre, direccion, cuenta_bancaria);
       
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        if (proveedorDAO.insertar(proveedor)) {
            System.out.println("Proveedor registrado correctamente");
            System.out.println(" ");
        } else {
            System.out.println("No se ha podido registrar el proveedor");
        }
    }
    public void modificarProveedor() {
        String opcion;
        String columna;
        String valor;
        String nombre;
        
        Proveedor proveedor_modificar;
        do {
            System.out.println("Qué desea modificar?");
            System.out.println("1. Dirección");
            System.out.println("2. Cuenta Bancaria");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.nextLine();
            System.out.println("Ingrese el nombre del proveedor a modificar: ");
            System.out.print("--> ");
            nombre = sc.nextLine();
            
            ProveedorDAO proveedorDAO = new ProveedorDAO();
            proveedor_modificar = proveedorDAO.buscarMostrar(nombre);
            
            
            if (proveedor_modificar == null) {
                System.out.println("ERR0R: No se encontró el proveedor");
                break;
            } else {
                switch (opcion) {
                    //TODO: Añadir validaciones
                    
                    case "1", "direccion" -> {
                        System.out.print("Ingrese la nueva dirección: ");
                        String direccion_nueva = sc.nextLine();
                        columna = "direccion";
                        valor = direccion_nueva;
                        boolean actualizado = proveedorDAO.actualizar(columna, nombre, valor);
                        if (actualizado) {
                            System.out.println("Dirección actualizada correctamente.");
                        } else {
                            System.out.println("Error al actualizar la dirección.");
                        }
                        opcion = "0";
                    }
                   
                    case "2", "cuenta bancaria" -> {
                        System.out.print("Ingrese la nueva cuenta bancaria: ");
                        String cuenta_nueva = sc.nextLine();
                        columna = "cuenta_bancaria";
                        valor = cuenta_nueva;
                        boolean actualizado = proveedorDAO.actualizar(columna, nombre, valor);
                        if (actualizado) {
                            System.out.println("Cuenta bancaria actualizada correctamente.");
                        } else {
                            System.out.println("Error al actualizar la cuenta bancaria.");
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
    public void eliminarProveedor() {
        String nombre;
        Proveedor proveedor;
        System.out.print("Ingrese el nombre del proveedor que quiere eliminar: ");
        nombre = sc.nextLine();
        
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        proveedor = proveedorDAO.buscarMostrar(nombre);

        if (proveedor == null) {
            System.out.println("ERR0R: No se encontró al proveedor con ese nombre");
        } else {
            System.out.println("Está por eliminar al siguiente proveedor: ");
            System.out.println("Nombre: " + proveedor.getNombre() );
            System.out.println("Dirección: " + proveedor.getDireccion());
            System.out.println("Cuenta Bancaria: " + proveedor.getCuentaBancaria());
            System.out.println("---¿Está seguro?---");
            String opcion;
            boolean seguir = true;
            do { 
                System.out.println("1. SI / 2. NO");
                opcion = sc.next();
                sc.nextLine();
                switch (opcion) {
                    case "1", "si", "SI" -> {
                        if (proveedorDAO.buscar(nombre).equals(nombre)) {
                            proveedorDAO.eliminar(nombre);
                            System.out.println("Proveedor eliminado");
                            seguir = false;
                            
                        } else {
                            System.out.println("No se encuentra el proveedor");
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
    public void mostrarProveedores() {
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        array_proveedores = proveedorDAO.obtenerTodos(); // Llenar la lista con los clientes de la BD

        if (array_proveedores == null) {
            array_proveedores = new ArrayList<>(); // Evitar que sea null en caso de error
        }

        if (array_proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados");
        } else {
            System.out.println("Proveedores: ");
            for (Proveedor proveedor : array_proveedores) {
                System.out.println(proveedor); // `toString()
            }
        }
    }    
}

