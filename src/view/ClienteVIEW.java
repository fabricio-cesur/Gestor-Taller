package view;

import dao.ClienteDAO;
import java.util.ArrayList;
import java.util.Scanner;
import model.Cliente;

public class ClienteVIEW {

    public ArrayList<Cliente> array_clientes = new ArrayList<>();

    Scanner sc = new Scanner(System.in);
    public void registrarCliente() {
        String dni;
        String nombre;
        String apellido;
        String direccion;
        int telefono;
        String cuenta_bancaria;
       //TODO: Añadir validaciones al registrar cliente
        Cliente cliente;
        System.out.print("Ingrese el DNI: ");
        dni = sc.next();
        System.out.print("Ingrese el nombre: ");
        nombre = sc.next();
        System.out.print("Ingrese el apellido: ");
        apellido = sc.next();
        System.out.print("Ingrese la dirección: ");
        direccion = sc.nextLine();
        System.out.print("Ingrese el telefono: ");
        telefono = sc.nextInt();
        System.out.print("Ingrese el número de cuenta bancaria: ");
        cuenta_bancaria = sc.nextLine();
 
        cliente = new Cliente(dni, nombre, apellido, direccion, telefono, cuenta_bancaria);
       // array_clientes.add(cliente);
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.insertarClienteDAO(cliente);
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
    public String[] modificarClienteDAO() {
        
        System.out.print("¿Qué desea modificar? ");
        String columna = sc.next();
        System.out.print("Ingrese el nuevo dato: ");
        String valor = sc.next();
        System.out.print("Ingrese el dni: ");
        String dni = sc.next();

        return new String[]{columna, valor, dni};
    }

    
}