package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.*;
import dao.*;

public class VehiculoVIEW {
    public ArrayList<Vehiculo> array_vehiculos = new ArrayList<>();
    public ArrayList<Cliente> array_clientes = new ArrayList<>();

    VehiculoDAO VehiculoDAO = new VehiculoDAO();
    Scanner sc = new Scanner(System.in);

    public void menu() {
        String opcion;
        do { 
            System.out.println("Qué desea hacer con los vehículos?");
            System.out.println("1. Registrar Vehiculo");
            System.out.println("2. Modificar Vehiculo");
            System.out.println("3. Eliminar Vehiculo");
            System.out.println("4. Mostrar Vehiculos");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next();
            opcion = opcion.toLowerCase();
            switch (opcion) {
                case "1", "registrar" -> { /*registrar() */ }
                case "2", "modificar" -> { /*modificar() */ }
                case "3", "eliminar" -> { /*eliminar() */ }
                case "4", "servicios" -> { /*mostrarServicios() */ }
                case "5", "disponibilidad" -> { /*revisarDisponibilidad() */ }
                case "6", "mostrar" -> { /*mostrar() */ }
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }
        } while (!opcion.equalsIgnoreCase("0"));
    }

    public void registrar() {
        //TODO: Añadir validaciones al registrar vehiculo
        System.out.print("Ingrese la matrícula: ");
        String matricula = sc.next();
        System.out.print("Ingrese el modelo: ");
        String modelo = sc.next();
        System.out.print("Ingrese el marca: ");
        String marca = sc.next();
        System.out.print("Ingrese el año: ");
        int año = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el DNI del propietario: ");
        String dni = sc.next();

        VehiculoDAO.insertar(new Vehiculo(matricula, modelo, marca, año, dni));
    }
    public void modificarVehiculo() {
        String opcion;
        String matricula;

        Vehiculo vehiculo_modificar = null;
        do {
            System.out.println("Qué desea modificar?");
            System.out.println("1. Matricula");
            System.out.println("2. Modelo");
            System.out.println("3. Marca");
            System.out.println("4. Año");
            System.out.println("5. Propietario");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next();

            System.out.println("Ingrese la matrícula del vehículo a modificar: ");
            System.out.print("--> ");
            matricula = sc.next();
            for (Vehiculo vehiculo : array_vehiculos) {
                if (matricula.equalsIgnoreCase(vehiculo.getMatricula())) {
                    vehiculo_modificar = vehiculo;
                    break;
                }
            }
            if (vehiculo_modificar == null) {
                System.out.println("ERR0R: No se encontró esa matrícula");
            } else {
                switch (opcion) {
                    case "1", "matricula" -> {
                        System.out.print("Ingrese la nueva matrícula: ");
                        String matricula_nueva = sc.next();
                        vehiculo_modificar.setMatricula(matricula_nueva);
                    }
                    case "2", "modelo" -> {
                        System.out.print("Ingrese el nuevo modelo: ");
                        String modelo_nuevo = sc.next();
                        vehiculo_modificar.setModelo(modelo_nuevo);
                    }
                    case "3", "marca" -> {
                        System.out.print("Ingrese la nueva marca: ");
                        String marca_nueva = sc.next();
                        vehiculo_modificar.setMarca(marca_nueva);
                    }
                    case "4", "año" -> {
                        System.out.print("Ingrese el nuevo año: ");
                        int año_nuevo = sc.nextInt();
                        sc.nextLine();
                        vehiculo_modificar.setAño(año_nuevo);
                    }
                    case "5", "propietario" -> {
                        System.out.print("Ingrese el DNI nuevo propietario: ");
                        String dni_propietario_nuevo = sc.next();
                        Cliente propietario_nuevo = null;
                        for (Cliente cliente : array_clientes) {
                            if (dni_propietario_nuevo.equalsIgnoreCase(cliente.getDni())) {
                                propietario_nuevo = cliente;
                                break;
                            }
                        }
                        if (propietario_nuevo == null) {
                            System.out.println("ERR0R: No se encontró el cliente");
                        } else {
                            vehiculo_modificar.setPropietario(propietario_nuevo);
                        }
                    }
                    default -> {
                        System.out.println("ERR0R: No se reconoció esa opción");
                    }
                }
            }

        } while (!opcion.equalsIgnoreCase("0"));
    }
    public void eliminarVehiculo() {
        String matricula;
        Vehiculo vehiculo = null;
        System.out.print("Ingrese la matrícula del vehiculo que quiere eliminar: ");
        matricula = sc.next();
        for (Vehiculo veh : array_vehiculos) {
            if (matricula.equalsIgnoreCase(veh.getMatricula())) {
                vehiculo = veh;
                break;
            }
        }
        if (vehiculo == null) {
            System.out.println("ERR0R: No se encontró al vehiculo con esa matrícula");
        } else {
            System.out.println("Está por eliminar al siguiente vehiculo: ");
            System.out.println(vehiculo.getMarca() + " " + vehiculo.getModelo());
            System.out.println("Año: " + vehiculo.getAño());
            System.out.println("Propietario: " + vehiculo.getPropietarioNombreApellido());
            System.out.println("---¿Está seguro?---");
            String opcion;
            do { 
                System.out.println("1. SI / 2. NO");
                opcion = sc.next();
                switch (opcion) {
                    case "1", "si", "SI" -> {
                        for (int i = 0; i < array_vehiculos.size(); i++) {
                            if (array_vehiculos.get(i).getMatricula().equals(vehiculo.getMatricula())) {
                                array_vehiculos.remove(i);
                                System.out.println("Cliente eliminado");
                                break;
                            }
                        }
                    }
                    case "2", "no", "NO" -> {
                        System.out.println("Abortando...");
                    }
                
                    default -> {
                        System.out.println("No se reconoció esa opción.");
                    }
                }
            } while (!opcion.equals("2"));
        }
    }
    public void mostrarVehiculos() {
        if (array_vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados");
        } else {
            System.out.println("Vehículos: ");
            for (Vehiculo vehiculo : array_vehiculos) {
                System.out.println(vehiculo.toString());
            }
        }
    }
    
}
