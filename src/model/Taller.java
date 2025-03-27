package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Taller {
    Scanner sc = new Scanner(System.in);
    
    public ArrayList<Cliente> array_clientes = new ArrayList<>();
    public ArrayList<Vehiculo> array_vehiculos = new ArrayList<>();
    public ArrayList<Cita> array_citas = new ArrayList<>();
    public ArrayList<Encargo> array_encargos = new ArrayList<>();
    public ArrayList<Empleado> array_empleados = new ArrayList<>();
    public ArrayList<Servicio> array_servicios = new ArrayList<>();
    public ArrayList<Item> array_items = new ArrayList<>();
    public ArrayList<Pedido> array_pedido = new ArrayList<>();
    public ArrayList<Proveedor> array_proveedores = new ArrayList<>();

    public void registrarCliente() {
        String dni;
        String nombre;
        String apellido;
        String direccion;
        int telefono;
        String cuenta_bancaria;
       
        Cliente cliente;
        System.out.print("Ingrese el DNI: ");
        dni = sc.next();
        System.out.print("Ingrese el nombre: ");
        nombre = sc.next();
        System.out.print("Ingrese el apelldio: ");
        apellido = sc.next();
        System.out.print("Ingrese la dirección: ");
        direccion = sc.nextLine();
        System.out.print("Ingrese el telefono: ");
        telefono = sc.nextInt();
        System.out.print("Ingrese el número de cuenta bancaria: ");
        cuenta_bancaria = sc.nextLine();
 
        cliente = new Cliente(dni, nombre, apellido, direccion, telefono, cuenta_bancaria);
        array_clientes.add(cliente);
    }
    public void modificarCliente() {}
    public void eliminarCliente() {}
    public void mostrarClientes() {}


    public void registrarVehiculo() {
        String matricula;
        String modelo;
        String marca;
        int año;
        String dni_propietario;
        Cliente propietario = null;

        Vehiculo vehiculo;
        System.out.print("Ingrese la matrícula: ");
        matricula = sc.next();
        System.out.print("Ingrese el modelo: ");
        modelo = sc.nextLine();
        System.out.print("Ingrese la marca: ");
        marca = sc.nextLine();
        System.out.print("Ingrese el año: ");
        año = sc.nextInt();
        System.out.print("Ingrese el dni del propietario: ");
        dni_propietario = sc.next();

        for (Cliente cliente : array_clientes) {
            if (dni_propietario.equals(cliente.getDni())) {
                propietario = cliente;
                break;
            }
        }
        vehiculo = new Vehiculo(matricula, modelo, marca, año, propietario);
        array_vehiculos.add(vehiculo);
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
    public void eliminarVehiculo() {}
    public void mostrarVehiculos() {}


    public void asignarCita() {}
    public void modificarCita() {}
    public void eliminarCita() {}
    public void mostrarCitas() {}


    public void registrarEncargo() {}
    public void modificarEncargo() {}
    public void finalizarEncargo() {}
    public void eliminarEncargo() {}
    public void mostrarEncargos() {}


    public void registrarEmpleado() {}
    public void modificarEmpleado() {}
    public void eliminarEmpleado() {}
    public void mostrarServiciosEmpleado() {}
    public void revisarDisponibilidadEmpleado() {}
    public void mostrarEmpleados() {}


    public void asignarEmpleados() {}
    public void modificarAsignacion() {}
    public void eliminarAsignacion() {}
    public void mostrarAsignaciones() {}


    public void registrarServicio() {}
    public void modificarServicio() {}
    public void eliminarServicio() {}
    public void mostrarServicios() {}


    public void registrarItem() {}
    public void modificarItem() {}
    public void eliminarItem() {}
    public void mostrarItems() {}


    public void registrarPedido() {}
    public void modificarPedido() {}
    public void eliminarPedido() {}
    public void mostrarPedidos() {}


    public void registrarProveedor() {}
    public void modificarProveedor() {}
    public void eliminarProveedor() {}
    public void mostrarProveedores() {}


}
