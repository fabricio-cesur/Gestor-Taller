
import java.io.IOException;
import java.util.Scanner;
import java.sql.Connection;

public class Menu {
    public static boolean vaciar = true;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        Connection conexion = ConexionDB.conectar();
          if (conexion != null) {
             System.out.println("Conexion establecida correctamente.");
          } else{
             System.out.println("No se pudo establecer la conexión");
          }

        String opcion;
        vaciarConsola();
        System.out.println("\nBienvenido al Gestor del Taller!");

        do { 
            System.out.println("Qué quiere manejar?");
            System.out.println("1. Clientes");
            System.out.println("2. Vehiculos");
            System.out.println("3. Citas");
            System.out.println("4. Encargos");
            System.out.println("5. Empleados");
            System.out.println("6. Asignaciones");
            System.out.println("7. Servicios");
            System.out.println("8. Items");
            System.out.println("9. Pedidos");
            System.out.println("10. Proveedores");
            System.out.println("0. Salir");
            System.out.println("");
            System.out.print(">>> ");
            opcion = sc.next();
            vaciarConsola();

            switch (opcion) {
                case "1", "Clientes" -> {}
                case "2", "Vehiculos" -> {}
                case "3", "Citas" -> {}
                case "4", "Encargos" -> {}
                case "5", "Empleados" -> {}
                case "6", "Asignaciones" -> {}
                case "7", "Servicios" -> {}
                case "8", "Items" -> {}
                case "9", "Pedidos" -> {}
                case "10", "Proveedores" -> {}
                default -> {}
                    
            }
        } while (!opcion.equalsIgnoreCase("0"));
    }

  
  
    
    public static void vaciarConsola() {
        String os = System.getProperty("os.name");

        if (vaciar) {
            try {
                if (os.contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                }
            } catch (IOException | InterruptedException e) {
                vaciar = false;
                System.err.println("ERR0R al vaciar la consola: " + e.getMessage());
            }
        }
    }
}