package view;

import dao.VehiculoDAO;
import java.util.ArrayList;
import java.util.Scanner;
import model.Cliente;
import model.Vehiculo;

public class VehiculoVIEW {
    public ArrayList<Vehiculo> array_vehiculos = new ArrayList<>();
    public ArrayList<Cliente> array_clientes = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    //Menu principal de la clase vehiculo
    public void menu() {
        String opcion;
        
        do { 
            System.out.println("Qué desea hacer con los vehiculos?");
            System.out.println("1. Registrar Vehiculo");
            System.out.println("2. Modificar Vehiculo");
            System.out.println("3. Eliminar Vehiculo");
            System.out.println("4. Mostrar Vehiculos");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.nextLine();
            
            switch (opcion) {
                case "1", "registrar" -> { registrarVehiculo(); }
                case "2", "modificar" -> { modificarVehiculo(); }
                case "3", "eliminar" -> { eliminarVehiculo(); }
                case "4", "mostrar" -> { mostrarVehiculos(); }
                case "0" -> { System.out.println("Volviendo al menu anterior. ");}
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }

        } while (!opcion.equalsIgnoreCase("0"));
    }

    //MEtodo para introducir un nuevo vehiculo
    public void registrarVehiculo() {
        String matricula;
        String modelo;
        String marca;
        String ano;
        String dni_cliente;
        Vehiculo vehiculo;
        
        System.out.print("Ingrese la matrícula: ");
        matricula = sc.nextLine();
        System.out.print("Ingrese el modelo: ");
        modelo = sc.nextLine();
        System.out.print("Ingrese el marca: ");
        marca = sc.nextLine();
        System.out.print("Ingrese el año: ");
        ano = sc.nextLine();
        System.out.print("Ingrese el DNI del propietario: ");
        dni_cliente = sc.nextLine();

        //Crea un nuevo objeto y lo introduce en la DB
        vehiculo = new Vehiculo(matricula, modelo, marca, ano, dni_cliente);
        VehiculoDAO vehiculoDAO = new VehiculoDAO();

        
        if (vehiculoDAO.insertar(vehiculo)) {
            System.out.println("Vehículo introducido correctamente");
        } else {
            System.out.println("El vehículo no se ha introducido correctamente ");
        }
    }

    //Metodo para modificar un vehiculo
    public void modificarVehiculo() {
        String opcion;
        String columna;
        String valor;
        String matricula;

        Vehiculo vehiculo_modificar;
        do {
            System.out.println("Qué desea modificar?");
            System.out.println("1. Propietario");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next();
            sc.nextLine();
            //Limpia el buffer de entrada de datos

            //En caso de que se introduzca 0 sale del menu y no se ejecuta el codigo restante
            if(opcion.equalsIgnoreCase("0")) {
                break;
            }
            System.out.println("Ingrese la matrícula del vehículo a modificar: ");
            System.out.print("--> ");
            matricula = sc.next();
            sc.nextLine();

            VehiculoDAO vehiculoDAO = new VehiculoDAO();
            //Obtiene el objeto vehiculo segun la matricula
            vehiculo_modificar = vehiculoDAO.buscarMostrar(matricula);
            
            if (vehiculo_modificar == null) {
                System.out.println("ERR0R: No se encontró el vehículo");
                break;

            } else {
                switch (opcion) {
                    case "1", "propietario" -> {
                        System.out.print("Ingrese el nuevo dni: ");
                        String propietario_nuevo = sc.next();
                        sc.nextLine();
                        //Limpia el buffer de entrada de datos

                        columna = "dni_cliente";
                        valor = propietario_nuevo;
                        //Actualiza vehiculo y segun si lo consigue o no muestra una cosa u otra
                        boolean actualizado = vehiculoDAO.actualizar(columna, matricula, valor);
                        if (actualizado) {
                        System.out.println("Propietario actualizado correctamente.");
                        } else {
                        System.out.println("Error al actualizar el propietario.");
                        }
                        opcion = "0";
                    }
                    case "0" -> { 
                        System.out.println("Volviendo al menu anterior. ");
                    }
                     default -> {
                        System.out.println("ERR0R: No se reconoció esa opción");
                    }
                }
            }

        } while (!opcion.equalsIgnoreCase("0"));
    }

    //Metodo para eliminar un vehiculo
    public void eliminarVehiculo() {
        String matricula;
        Vehiculo vehiculo;
        System.out.print("Ingrese la matrícula del vehiculo que quiere eliminar: ");
        matricula = sc.next();

        //obtine el objeto vehiculo segun la matricula
        VehiculoDAO vehiculoDAO = new VehiculoDAO();
        vehiculo = vehiculoDAO.buscarMostrar(matricula);

        if (vehiculo == null) {
            System.out.println("ERR0R: No se encontró al vehiculo con esa matrícula");
        } else {
            System.out.println("Está por eliminar al siguiente vehiculo: ");
            System.out.println("Matricula: " + vehiculo.getMatricula());
            System.out.println("Marca: " + vehiculo.getMarca());
            System.out.println("Modelo: " + vehiculo.getModelo());
            System.out.println("Año: " + vehiculo.getAno());
            System.out.println("DNI propietario: " + vehiculo.getDniCliente());
            System.out.println("---¿Está seguro?---");
            String opcion;
            boolean seguir = true;
            do { 
                System.out.println("1. SI / 2. NO");
                opcion = sc.next();
                sc.nextLine();
                switch (opcion) {
                    case "1", "si", "SI" -> {
                        
                        //Compara la matricula introducida con la de la DB y la elimina
                        if (vehiculoDAO.buscar(matricula).equals(matricula)) {
                            vehiculoDAO.eliminar(matricula);
                            System.out.println("Vehiculo eliminado correctamente");
                            seguir = false;
                        } else {
                            System.out.println("No se encuentra el vehículo");
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

    //Metodo para mostrar todos los vehiculos
    public void mostrarVehiculos() {
        VehiculoDAO vehiculoDAO = new VehiculoDAO();
        array_vehiculos = vehiculoDAO.obtenerTodos(); 
        if (array_vehiculos == null) {
            array_vehiculos = new ArrayList<>(); 
        }

        if (array_vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados");
        } else {
            System.out.println("Vehiculos: ");
            for (Vehiculo vehiculo : array_vehiculos) {
                System.out.println(vehiculo); // imprime los vehiculos con la plantilla `toString()
            }
        }
    }
    
}
