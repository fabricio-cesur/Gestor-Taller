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
       //TODO: Añadir validaciones
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
    public void modificarCliente() {
        String opcion;
        String dni;

        Cliente cliente_modificar = null;
        do {
            System.out.println("Qué desea modificar?");
            System.out.println("1. Dni");
            System.out.println("2. Nombre");
            System.out.println("3. Apellido");
            System.out.println("4. Dirección");
            System.out.println("5. Teléfono");
            System.out.println("6. Cuenta Bancaria");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.nextLine();
            //TODO: Validar que el DNI exista
            System.out.println("Ingrese el DNI del cliente a modificar: ");
            System.out.print("--> ");
            dni = sc.next();
            for (Cliente cliente : array_clientes) {
                if (dni.equalsIgnoreCase(cliente.getDni())) {
                    cliente_modificar = cliente;
                    break;
                }
            }
            if (cliente_modificar == null) {
                System.out.println("ERR0R: No se encontró esa matrícula");
            } else {
                switch (opcion) {
                    //TODO: Añadir validaciones
                    case "1", "dni" -> {
                        System.out.print("Ingrese el nuevo DNI: ");
                        String dni_nuevo = sc.next();
                        cliente_modificar.setDni(dni_nuevo);
                    }
                    case "2", "nombre" -> {
                        System.out.print("Ingrese el nuevo nombre: ");
                        String nombre_nuevo = sc.next();
                        cliente_modificar.setNombre(nombre_nuevo);
                    }
                    case "3", "apellido" -> {
                        System.out.print("Ingrese el nuevo apellido: ");
                        String apellido_nuevo = sc.next();
                        cliente_modificar.setApellido(apellido_nuevo);
                    }
                    case "4", "direccion" -> {
                        System.out.print("Ingrese la nueva dirección: ");
                        String direccion_nueva = sc.nextLine();
                        cliente_modificar.setDireccion(direccion_nueva);
                    }
                    case "5", "telefono" -> {
                        System.out.print("Ingrese el nuevo teléfono: ");
                        int telefono_nuevo = sc.nextInt();
                        cliente_modificar.setTelefono(telefono_nuevo);
                    }
                    case "6", "cuenta bancaria" -> {
                        System.out.print("Ingrese la nueva cuenta bancaria: ");
                        String cuenta_nueva = sc.nextLine();
                        cliente_modificar.setCuentaBancaria(cuenta_nueva);
                    }
                    default -> {
                        System.out.println("ERR0R: No se reconoció esa opción");
                    }
                }
            }

        } while (!opcion.equalsIgnoreCase("0"));

    }
    public void eliminarCliente() {
        String dni;
        Cliente cliente = null;
        System.out.print("Ingrese el DNI del cliente que quiere eliminar: ");
        dni = sc.next();
        for (Cliente cli : array_clientes) {
            if (dni.equalsIgnoreCase(cli.getDni())) {
                cliente = cli;
                break;
            }
        }
        if (cliente == null) {
            System.out.println("ERR0R: No se encontró al cliente con ese DNI");
        } else {
            System.out.println("Está por eliminar al siguiente cliente: ");
            System.out.println(cliente.getNombre() + " " + cliente.getApellido());
            System.out.println("Dirección: " + cliente.getDireccion());
            System.out.println("Teléfono: " + cliente.getTelefono());
            System.out.println("Cuenta Bancaria: " + cliente.getCuentaBancaria());
            System.out.println("---¿Está seguro?---");
            String opcion;
            do { 
                System.out.println("1. SI / 2. NO");
                opcion = sc.next();
                switch (opcion) {
                    case "1", "si", "SI" -> {
                        for (int i = 0; i < array_clientes.size(); i++) {
                            if (array_clientes.get(i).getDni().equals(cliente.getDni())) {
                                array_clientes.remove(i);
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
    public void mostrarClientes() {
        if (array_clientes.isEmpty()) {
            System.out.println("No hay clientes registrados");
        } else {
            System.out.println("Clientes: ");
            for (Cliente cliente : array_clientes) {
                System.out.println(cliente.toString());
            }
        }
    }


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
