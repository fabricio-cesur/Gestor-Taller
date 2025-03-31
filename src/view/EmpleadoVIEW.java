package view;

import dao.EmpleadoDAO;
import java.util.ArrayList;
import java.util.Scanner;
import model.Empleado;

public class EmpleadoVIEW {
    public Scanner sc = new Scanner(System.in);
    public EmpleadoDAO EmpleadoDAO = new EmpleadoDAO();
    public ArrayList<Empleado> array_empleados = new ArrayList<>();

    public void menu() {
        String opcion;
        do { 
            System.out.println("Qué desea hacer con los empleados?");
            System.out.println("1. Registrar Empleado");
            System.out.println("2. Modificar Empleado");
            System.out.println("3. Eliminar Empleado");
            System.out.println("4. Mostrar Empleados");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next();
            opcion = opcion.toLowerCase();
            switch (opcion) {
                case "1", "registrar" -> { registrar(); }
                case "2", "modificar" -> { modificar(); }
                case "3", "eliminar" -> { eliminar(); }
                case "4", "mostrar" -> { mostrarEmpleados(); }
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }
        } while (!opcion.equalsIgnoreCase("0"));
    }

    public void registrar() {
        //TODO: Añadir validaciones al registrar empleado
        System.out.print("Ingrese el DNI: ");
        String dni = sc.next();
        System.out.print("Ingrese el nombre: ");
        String nombre = sc.next();
        System.out.print("Ingrese el apellido: ");
        String apellido = sc.next();
        System.out.print("Ingrese la dirección: ");
        String direccion = sc.nextLine();
        System.out.print("Ingrese el teléfono: ");
        int telefono = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese la cuenta bancaria: ");
        String cuenta_bancaria = sc.next();
        System.out.print("Ingrese el salario: ");
        int salario = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el cargo: ");
        String cargo = sc.next();

        EmpleadoDAO.insertar(new Empleado(dni, nombre, apellido, direccion, telefono, cuenta_bancaria, salario, cargo));
    }
    public void modificar() {
        String opcion;
        boolean terminar;
        String columna = null;
        do {
            System.out.println("¿Qué desea modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Dirección");
            System.out.println("4. Teléfono");
            System.out.println("5. Cuenta Bancaria");
            System.out.println("6. Salario");
            System.out.println("7. Cargo");
            System.out.println("0. Atrás");
            do {
                terminar = true;
                System.out.print(">>> ");
                opcion = sc.next();
                switch (opcion) {
                    case "1" -> { columna = "nombre"; }
                    case "2" -> { columna = "apellido"; }
                    case "3" -> { columna = "direccion"; }
                    case "4" -> { columna = "telefono"; }
                    case "5" -> { columna = "cuenta_bancaria"; }
                    case "6" -> { columna = "salario"; }
                    case "7" -> { columna = "cargo"; }
                    default -> {
                        System.out.println("ERR0R: No se reconoció esa opción");
                        terminar = false;
                    }
                }
            } while (!terminar);
            //TODO: Añadir validaciones al modificar empleado
            System.out.print("Ingrese el dni: ");
            String dni = sc.next();
            System.out.print("Ingrese el nuevo valor: ");
            String valor = sc.next();
            EmpleadoDAO.actualizar(columna, dni, valor);
        } while (!opcion.equalsIgnoreCase("0"));
    }
    public void eliminar() {
        String dni;
        String opcion;

        System.out.println("Ingrese el DNI del empleado a eliminar");
        System.out.print("--> ");
        dni = sc.next();
        //TODO: Validar que el dni existe iterando en la base de datos
        System.out.println("Está por eliminar el siguiente Empleado");
        EmpleadoDAO.buscar(dni);
        System.out.println("---¿Está seguro?---");
        System.out.println("1. SI / 2. NO");
        do { 
            System.out.print("-->");
            opcion = sc.next();
            opcion = opcion.toLowerCase();
            switch (opcion) {
                case "1", "si" -> {
                    if (EmpleadoDAO.buscar(dni).equals(dni)) {
                        EmpleadoDAO.eliminar(dni);
                        System.out.println("Se eliminó correctamente");
                    } else {
                        System.out.println("ERR0R: No se encontró el DNI del empleado");
                    }
                }
                case "2", "no" -> {
                    System.out.println("Abortando...");
                }
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            } 
        } while (!opcion.equals("2"));
    }
    public void mostrarEmpleados() {
        EmpleadoDAO EmpleadoDAO = new EmpleadoDAO();
        array_empleados = EmpleadoDAO.obtenerTodos(); // Llenar la lista con los empleados de la BD

        if (array_empleados == null) {
            array_empleados = new ArrayList<>(); // Evitar que sea null en caso de error
        }

        if (array_empleados.isEmpty()) {
            System.out.println("No hay empleados registrados");
        } else {
            System.out.println("Empleados: ");
            for (Empleado empleado : array_empleados) {
                System.out.println(empleado); // toString()
            }
        }
    }
}
