package view;

import dao.EmpleadoDAO;
import java.util.ArrayList;
import java.util.Scanner;
import model.Empleado;




public class EmpleadoVIEW {
    public Scanner sc = new Scanner(System.in);
    public ArrayList<Empleado> array_empleados = new ArrayList<>();
    
    //MEnu principal de la clase empleado
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
            sc.nextLine();
            //Limpia el buffer de entrada de datos

            switch (opcion) {
                case "1", "registrar" -> { registrarEmpleado(); }
                case "2", "modificar" -> { modificarEmpleado(); }
                case "3", "eliminar" -> { eliminarEmpleado(); }
                case "4", "mostrar" -> { mostrarEmpleados(); }
                case "0" -> { System.out.println("Volviendo al menu anterior. ");}
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }

        } while (!opcion.equalsIgnoreCase("0"));
    }
    // Metodo registrar nuevo empleado
    public void registrarEmpleado() {
        String dni;
        String nombre;
        String apellido;
        String direccion;
        String phone;
        String cuenta_bancaria;
        String salary;

        Empleado empleado;
        //TODO: Añadir validaciones al registrar empleado
        System.out.print("Ingrese el DNI: ");
        dni = sc.nextLine();
        System.out.print("Ingrese el nombre: ");
        nombre = sc.nextLine();
        System.out.print("Ingrese el apellidos: ");
        apellido = sc.nextLine();
        System.out.print("Ingrese la dirección: ");
        direccion = sc.nextLine();
        System.out.print("Ingrese el teléfono: ");
        phone = sc.nextLine();
        System.out.print("Ingrese la cuenta bancaria: ");
        cuenta_bancaria = sc.nextLine();
        System.out.print("Ingrese el salario: ");
        salary = sc.nextLine();
        System.out.print("Ingrese el cargo: ");
        String cargo = sc.nextLine();

        //Se pasan los datos de Phone y salary a int pra crear objeto e insertarlo en la DB
        int telefono = Integer.parseInt(phone);
        int salario = Integer.parseInt(salary);

        empleado = new Empleado(dni, nombre, apellido, direccion, telefono, cuenta_bancaria, salario, cargo);
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        if (empleadoDAO.insertar(empleado)) {
            System.out.println("Empleado introducido correctamente");
        }else {
            System.out.println("El empleado no se ha introducido correctamente");

        }
    }
    //MEtodo modificar empleado
    public void modificarEmpleado() {
        String opcion;
        String columna;
        String valor;
        String dni;
        
        Empleado empleado_modificar;

        do {
            System.out.println("¿Qué desea modificar?");
            System.out.println("1. Dirección");
            System.out.println("2. Teléfono");
            System.out.println("3. Cuenta Bancaria");
            System.out.println("4. Salario");
            System.out.println("5. Cargo");
            System.out.println("0. Atrás");
            opcion = sc.next();
            sc.nextLine();
            //Limpia el buffer de entrada de datos

            //En caso de que se introduzca 0 sale del menu y no se ejecuta el codigo restante
            if(opcion.equalsIgnoreCase("0")) {
                break;
            }

            System.out.println("Ingrese el DNI del empleado a modificar: ");
            System.out.print("--> ");
            dni = sc.nextLine();

            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            //Obtiene el objeto empleado segun dni
            empleado_modificar = empleadoDAO.buscarMostrar(dni);
            
            if (empleado_modificar == null) {
                System.out.println("ERR0R: No se encontró el vehículo");
                break;

            } else {
                System.out.print(">>> ");
                switch (opcion) {
                    case "1", "direccion" -> {
                        System.out.print("Ingrese la nueva dirección: ");
                        String direccion_nueva = sc.nextLine();
                        valor = direccion_nueva;
                        columna = "direccion";
                        
                        //Actualiza empleado segun columna y dni y si lo consigue o no muestra cosas diferentes
                        boolean actualizado = empleadoDAO.actualizar(columna, dni, valor);
                        if (actualizado) {
                        System.out.println("Empleado actualizado correctamente.");
                        } else {
                        System.out.println("Error al actualizar el empleado.");
                        }
                        //Asigna a opcion 0 para evitar problemas en menu posteriores
                        opcion = "0";
                    }
                    case "2", "telefono" -> {
                        System.out.print("Ingrese el nuevo número de telefono: ");
                        String telefono_nuevo = sc.nextLine();
                        valor = telefono_nuevo;
                        columna = "telefono";
                        
                        boolean actualizado = empleadoDAO.actualizar(columna, dni, valor);
                        if (actualizado) {
                        System.out.println("Empleado actualizado correctamente.");
                        } else {
                        System.out.println("Error al actualizar el empleado.");
                        }
                        opcion = "0";
                    }
                    case "3", "cuenta bancaria" -> {
                        System.out.print("Ingrese la nueva cuenta bancaria: ");
                        String cuenta_nueva = sc.nextLine();
                        columna = "cuenta_bancaria";
                        valor = cuenta_nueva;

                        boolean actualizado = empleadoDAO.actualizar(columna, dni, valor);
                        if (actualizado) {
                            System.out.println("Cuenta bancaria actualizada correctamente.");
                        } else {
                            System.out.println("Error al actualizar la cuenta bancaria.");
                        }
                        opcion = "0";
                    }
                    case "4", "salario" -> {
                        System.out.print("Ingrese el nuevo salario: ");
                        String salario_nuevo = sc.nextLine();
                        columna = "salario";
                        valor = salario_nuevo;

                        boolean actualizado = empleadoDAO.actualizar(columna, dni, valor);
                        if (actualizado) {
                            System.out.println("Salario actualizado correctamente.");
                        } else {
                            System.out.println("Error al actualizar el salario.");
                        }
                        opcion = "0";
                    }
                    case "5", "cargo" -> {
                        System.out.print("Ingrese el nuevo cargo: ");
                        String cargo_nuevo = sc.nextLine();
                        columna = "cargo";
                        valor = cargo_nuevo;

                        boolean actualizado = empleadoDAO.actualizar(columna, dni, valor);
                        if (actualizado) {
                            System.out.println("Cargo actualizado correctamente.");
                        } else {
                            System.out.println("Error al actualizar el cargo.");
                        }
                        opcion = "0";
                    }
                    case "0" -> { System.out.println("Volviendo al menu anterior. ");}
                    default -> {
                        System.out.println("ERR0R: No se reconoció esa opción");
                    }
                }
            }

        } while (!opcion.equalsIgnoreCase("0"));
    }
    //MEtodo eliminar empleado
    public void eliminarEmpleado() {
        String dni;
        Empleado empleado;
        System.out.println("Ingrese el DNI del empleado a eliminar");
        System.out.print("--> ");
        dni = sc.next();
        sc.nextLine();
        //Limpia el buffer de entrada de datos

        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        //Obtiene el objeto empleado segun dni
        empleado = empleadoDAO.buscarMostrar(dni);

        if (empleado == null) {
            System.out.println("ERR0R: No se encontró al empleado con ese DNI");
        } else {
            System.out.println("Está por eliminar al siguiente empleado: ");
            System.out.println("Nombre: " + empleado.getNombre() + " " + empleado.getApellido());
            System.out.println("Dirección: " + empleado.getDireccion());
            System.out.println("Teléfono: " + empleado.getTelefono());
            System.out.println("Cuenta Bancaria: " + empleado.getCuentaBancaria());
            System.out.println("---¿Está seguro?---");
            String opcion;
            boolean seguir = true;

            do { 
                System.out.println("1. SI / 2. NO");
                opcion = sc.next();
                sc.nextLine();
                switch (opcion) {
                    case "1", "si", "SI" -> {
                        //En caso de que coincida el dni introducido con el de la DB se elimina y si lo consigue o no muestra una cosa diferente
                        if (empleadoDAO.buscar(dni).equals(dni)) {
                            empleadoDAO.eliminar(dni);
                            System.out.println("Empelado eliminado");
                            seguir = false;
                            
                        } else {
                            System.out.println("No se encuentra dni");
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
            } while(seguir);
        }
    }

    //MEtodo para mostrar todos los empleados
    public void mostrarEmpleados() {
       EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        array_empleados = empleadoDAO.obtenerTodos(); // Llenar la lista con los clientes de la BD

        if (array_empleados == null) {
         array_empleados = new ArrayList<>(); // Evitar que sea null en caso de error
        }

        if (array_empleados.isEmpty()) {
            System.out.println("No hay empleados registrados");
        } else {
            System.out.println("Clientes: ");
            for (Empleado empleado : array_empleados) {
                System.out.println(empleado); // `imprime como el toString()
            }
        }

    }
}
