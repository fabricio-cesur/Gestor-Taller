
import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;

public class Menu {
    public static boolean vaciar = true;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        vaciarConsola();
        Connection conexion = ConexionDB.conectar();

        if (conexion != null) {
            System.out.println("Conexion establecida correctamente.");
        } else{
            System.out.println("No se pudo establecer la conexión");
        }

        String opcion;
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
                case "1", "Clientes" -> { menuClientes(); }
                case "2", "Vehiculos" -> { menuVehiculos(); }
                case "3", "Citas" -> { menuCitas(); }
                case "4", "Encargos" -> { menuEncargos(); }
                case "5", "Empleados" -> { menuEmpleados(); }
                case "6", "Asignaciones" -> { menuAsignaciones(); }
                case "7", "Servicios" -> { menuServicios(); }
                case "8", "Items" -> { menuItems(); }
                case "9", "Pedidos" -> { menuPedidos(); }
                case "10", "Proveedores" -> { menuProveedores(); }
                default -> {}
                    
            }
        } while (!opcion.equalsIgnoreCase("0"));
    }

    public static void menuClientes() {
        String opcion;
        do { 
            System.out.println("Qué desea hacer con los clientes?");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Modificar Cliente");
            System.out.println("3. Eliminar Cliente");
            System.out.println("4. Mostrar Clientes");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next();
    
            switch (opcion) {
                case "1", "registrar" -> { /*taller.registrarCliente() */ }
                case "2", "modificar" -> { /*taller.modificarCliente() */ }
                case "3", "eliminar" -> { /*taller.eliminarCliente() */ }
                case "4", "mostrar" -> { /*taller.mostrarClientes() */ }
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }

            vaciarConsola();
        } while (!opcion.equalsIgnoreCase("0"));
    }

    public static void menuVehiculos() {
        String opcion;
        do { 
            System.out.println("Qué desea hacer con los vehiculos?");
            System.out.println("1. Registrar Vehiculo");
            System.out.println("2. Modificar Vehiculo");
            System.out.println("3. Eliminar Vehiculo");
            System.out.println("4. Mostrar Vehiculos");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next();
    
            switch (opcion) {
                case "1", "registrar" -> { /*taller.registrarVehiculo() */ }
                case "2", "modificar" -> { /*taller.modificarVehiculo() */ }
                case "3", "eliminar" -> { /*taller.eliminarVehiculo() */ }
                case "4", "mostrar" -> { /*taller.mostrarVehiculos() */ }
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }

            vaciarConsola();
        } while (!opcion.equalsIgnoreCase("0"));
    }

    public static void menuCitas() {
        String opcion;
        do { 
            System.out.println("Qué desea hacer con los citas?");
            System.out.println("1. Asignar Cita");
            System.out.println("2. Modificar Cita");
            System.out.println("3. Eliminar Cita");
            System.out.println("4. Mostrar Citas");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next();
    
            switch (opcion) {
                case "1", "registrar" -> { /*taller.asignarCita() */ }
                case "2", "modificar" -> { /*taller.modificarCita() */ }
                case "3", "eliminar" -> { /*taller.eliminarCita() */ }
                case "4", "mostrar" -> { /*taller.mostrarCitas() */ }
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }

            vaciarConsola();
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

            vaciarConsola();
        } while (!opcion.equalsIgnoreCase("0"));
    }

    public static void menuEmpleados() {
        String opcion;
        do { 
            System.out.println("Qué desea hacer con los empleados?");
            System.out.println("1. Registrar Empleado");
            System.out.println("2. Modificar Empleado");
            System.out.println("3. Eliminar Empleado");
            System.out.println("4. Mostrar Servicios");
            System.out.println("5. Mostrar Empleados");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next();
    
            switch (opcion) {
                case "1", "registrar" -> { /*taller.registrarEmpleado() */ }
                case "2", "modificar" -> { /*taller.modificarEmpleado() */ }
                case "3", "eliminar" -> { /*taller.eliminarEmpleado() */ }
                case "4", "servicios" -> { /*taller.mostrarServiciosEmpleado() */ }
                case "5", "disponibilidad" -> { /*taller.revisarDisponibilidadEmpleado() */ }
                case "6", "mostrar" -> { /*taller.mostrarEmpleados() */ }
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }

            vaciarConsola();
        } while (!opcion.equalsIgnoreCase("0"));
    }

    public static void menuAsignaciones() {
        String opcion;
        do { 
            System.out.println("Qué desea hacer con las asignaciones?");
            System.out.println("1. Asignar Empleados");
            System.out.println("2. Modificar Asignacion");
            System.out.println("3. Eliminar Asignacion");
            System.out.println("4. Mostrar Asignaciones");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next();
    
            switch (opcion) {
                case "1", "registrar" -> { /*taller.asignarEmpleados() */ }
                case "2", "modificar" -> { /*taller.modificarAsignacion() */ }
                case "3", "eliminar" -> { /*taller.eliminarAsignacion() */ }
                case "4", "mostrar" -> { /*taller.mostrarAsignaciones() */ }
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }

            vaciarConsola();
        } while (!opcion.equalsIgnoreCase("0"));
    }

    public static void menuServicios() {
        String opcion;
        do { 
            System.out.println("Qué desea hacer con los servicios?");
            System.out.println("1. Registrar Servicio");
            System.out.println("2. Modificar Servicio");
            System.out.println("3. Eliminar Servicio");
            System.out.println("4. Mostrar Servicios");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next();
    
            switch (opcion) {
                case "1", "registrar" -> { /*taller.registrarServicio() */ }
                case "2", "modificar" -> { /*taller.modificarServicio() */ }
                case "3", "eliminar" -> { /*taller.eliminarServicio() */ }
                case "4", "mostrar" -> { /*taller.mostrarServicios() */ }
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }

            vaciarConsola();
        } while (!opcion.equalsIgnoreCase("0"));
    }

    public static void menuItems() {
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
            //TODO: Añadir opciones de Restock
            switch (opcion) {
                case "1", "registrar" -> { /*taller.registrarItem() */ }
                case "2", "modificar" -> { /*taller.modificarItem() */ }
                case "3", "eliminar" -> { /*taller.eliminarItem() */ }
                case "4", "mostrar" -> { /*taller.mostrarItems() */ }
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }

            vaciarConsola();
        } while (!opcion.equalsIgnoreCase("0"));
    }

    public static void menuPedidos() {
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
                case "1", "registrar" -> { /*taller.registrarPedido() */ }
                case "2", "modificar" -> { /*taller.modificarPedido() */ }
                case "3", "eliminar" -> { /*taller.eliminarPedido() */ }
                case "4", "mostrar" -> { /*taller.mostrarPedidos() */ }
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }

            vaciarConsola();
        } while (!opcion.equalsIgnoreCase("0"));
    }

    public static void menuProveedores() {
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
                case "1", "registrar" -> { /*taller.registrarProveedor() */ }
                case "2", "modificar" -> { /*taller.modificarProveedor() */ }
                case "3", "eliminar" -> { /*taller.eliminarProveedor() */ }
                case "4", "mostrar" -> { /*taller.mostrarProveedores() */ }
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }

            vaciarConsola();
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