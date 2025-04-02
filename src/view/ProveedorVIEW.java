package view;

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
            
    
            switch (opcion) {
                case "1", "registrar" -> { registrarProveedor();  }
                case "2", "modificar" -> { modificarProveedor(); }
                case "3", "eliminar" -> { eliminarProveedor(); }
                case "4", "mostrar" -> { mostrarProveedores(); }
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }

        } while (!opcion.equalsIgnoreCase("0"));
    }

    public void registrarProveedor() {}
    public void modificarProveedor() {}
    public void eliminarProveedor() {}
    public void mostrarProveedores() {}
}
