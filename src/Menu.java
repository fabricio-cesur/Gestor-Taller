
import dao.ConexionDB;
import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;
import view.*;

public class Menu {
    public static boolean vaciar = false;
    public static Scanner sc = new Scanner(System.in);

    
    public static void main(String[] args) throws Exception {
        CitaVIEW citaVIEW = new CitaVIEW();
        ClienteVIEW clienteVIEW = new ClienteVIEW();
        VehiculoVIEW vehiculoVIEW = new VehiculoVIEW();
        EmpleadoVIEW empleadoVIEW = new EmpleadoVIEW();
        ItemVIEW itemVIEW = new ItemVIEW();
        PedidoVIEW pedidoVIEW = new PedidoVIEW();
        ProveedorVIEW proveedorVIEW = new ProveedorVIEW();
        ServicioVIEW servicioVIEW = new ServicioVIEW();

        vaciarConsola();
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            System.out.println("Conexion establecida correctamente.");
        } else{
            System.out.println("No se pudo establecer la conexión");
        }

        String opcion;
        System.out.println("Bienvenido al Gestor del Taller!");

        do { 
            System.out.println("Qué quiere manejar?");
            System.out.println("1. Clientes");
            System.out.println("2. Vehiculos");
            System.out.println("3. Citas");
            System.out.println("4. Encargos");
            System.out.println("5. Empleados");
            System.out.println("6. Servicios");
            System.out.println("7. Items");
            System.out.println("8. Pedidos");
            System.out.println("9. Proveedores");
            System.out.println("0. Salir");
            System.out.println("");
            System.out.print(">>> ");
            opcion = sc.nextLine();
            

            switch (opcion) {
                case "1", "Clientes" -> { clienteVIEW.menu(); }
                case "2", "Vehiculos" -> { vehiculoVIEW.menu(); }
                case "3", "Citas" -> { citaVIEW.menu(); }
                case "4", "Encargos" -> { /*menuEncargos();*/ }
                case "5", "Empleados" -> { empleadoVIEW.menu(); }
                case "6", "Servicios" -> { servicioVIEW.menu(); }
                case "7", "Items" -> { itemVIEW.menu(); }
                case "8", "Pedidos" -> { pedidoVIEW.menu(); }
                case "9", "Proveedores" -> { proveedorVIEW.menu(); }
                default -> {}
                    
            }
        } while (!opcion.equalsIgnoreCase("0"));
    }


    public static void menuEncargos() {
        String opcion;
        do { 
            System.out.println("Qué desea hacer con los encargos?");
            System.out.println("1. Registrar Encargo");
            System.out.println("2. Modificar Encargo");
            System.out.println("3. Finalizar Encargo");
            System.out.println("4. Eliminar Encargo");
            System.out.println("5. Mostrar Encargos");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next();
            vaciarConsola();
    
            switch (opcion) {
                case "1", "registrar" -> { /*taller.registrarEncargo() */ }
                case "2", "modificar" -> { /*taller.modificarEncargo() */ }
                case "3", "finalizar" -> { /*taller.finalizarEncargo() */ }
                case "4", "eliminar" -> { /*taller.eliminarEncargo() */ }
                case "5", "mostrar" -> { /*taller.mostrarEncargos() */ }
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
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