package view;

import dao.ClienteDAO;
import java.util.ArrayList;
import java.util.Scanner;
import model.Cliente;

public class ClienteVIEW {

    public ArrayList<Cliente> array_clientes = new ArrayList<>();
    // public Validacion val = new Validacion();

    Scanner sc = new Scanner(System.in);
    public void registrarCliente() {
        String dni;
        String nombre;
        String apellido;
        String direccion;
        String phone;
        String cuenta_bancaria;
        Cliente cliente;
       //TODO: Añadir validaciones al registrar cliente
        boolean terminar;
        // do {
        System.out.print("Ingrese el DNI: ");
        dni = sc.nextLine();
        // } while (!val.validarDNI(dni));
        System.out.print("Ingrese el nombre: ");
        nombre = sc.nextLine();
        System.out.print("Ingrese el apellido: ");
        apellido = sc.nextLine();
        System.out.print("Ingrese la dirección: ");
        direccion = sc.nextLine();
        do {
            terminar = true;
            System.out.print("Ingrese el telefono: ");
            phone = sc.nextLine();
            for (int i = 0; i < phone.length(); i++) {
                if (Character.isAlphabetic(phone.charAt(i))) {
                    terminar = false;
                    System.out.println("Un número de teléfono debe tener 9 dígitos");
                    break;
                }
            }
        } while (!terminar);
        do { 
            System.out.print("Ingrese el número de cuenta bancaria: ");
            cuenta_bancaria = sc.nextLine();
            //Formato de cuenta bancaria ESXX XXXX XXXX XXXX XXXX
            //Revisa que tenga 
            if (cuenta_bancaria.length() != 24 || cuenta_bancaria.length() != 29) {

            }
        } while (!terminar);

        int telefono = Integer.parseInt(phone);
 
        cliente = new Cliente(dni, nombre, apellido, direccion, telefono, cuenta_bancaria);
       
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.insertar(cliente);
    }
    public void modificarCliente() {
        String opcion;
        String dni;
        String columna;
        String valor;

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
            dni = sc.nextLine();

            ClienteDAO clienteDAO = new ClienteDAO();
            cliente_modificar = clienteDAO.buscarMostrar(dni);
            
            
            if (cliente_modificar == null) {
                System.out.println("ERR0R: No se encontró el cliente");
            } else {
                switch (opcion) {
                    //TODO: Añadir validaciones
                    case "1", "dni" -> {
                        System.out.print("Ingrese el nuevo DNI: ");
                        String dni_nuevo = sc.next();
                        valor = dni_nuevo;
                        columna = "dni";
                        
                        boolean actualizado = clienteDAO.actualizar(columna, dni, valor);
                        if (actualizado) {
                        System.out.println("DNI actualizado correctamente.");
                        } else {
                        System.out.println("Error al actualizar el DNI.");
                        }

                    }
                    case "2", "nombre" -> {
                        System.out.print("Ingrese el nuevo nombre: ");
                        String nombre_nuevo = sc.next();
                        columna = "nombre";
                        valor = nombre_nuevo;
                        boolean actualizado = clienteDAO.actualizar(columna, dni, valor);
                        if (actualizado) {
                            System.out.println("Nombre actualizado correctamente.");
                        } else {
                            System.out.println("Error al actualizar el nombre.");
                        }
                    }
                    case "3", "apellido" -> {
                        System.out.print("Ingrese el nuevo apellido: ");
                        String apellido_nuevo = sc.next();
                        columna = "apellido";
                        valor = apellido_nuevo;
                        boolean actualizado = clienteDAO.actualizar(columna, dni, valor);
                        if (actualizado) {
                            System.out.println("Apellido actualizado correctamente.");
                        } else {
                            System.out.println("Error al actualizar el apellido.");
                        }
                    }
                    case "4", "direccion" -> {
                        System.out.print("Ingrese la nueva dirección: ");
                        String direccion_nueva = sc.nextLine();
                        columna = "direccion";
                        valor = direccion_nueva;
                        boolean actualizado = clienteDAO.actualizar(columna, dni, valor);
                        if (actualizado) {
                            System.out.println("Dirección actualizada correctamente.");
                        } else {
                            System.out.println("Error al actualizar la dirección.");
                        }
                    }
                    case "5", "telefono" -> {
                        System.out.print("Ingrese el nuevo teléfono: ");
                        int telefono_nuevo = sc.nextInt();
                        columna = "telefono";
                        valor = Integer.toString(telefono_nuevo); //paso el valor a String
                        boolean actualizado = clienteDAO.actualizar(columna, dni, valor);
                        if (actualizado) {
                            System.out.println("Teléfono actualizado correctamente.");
                        } else {
                            System.out.println("Error al actualizar el teléfono.");
                        }
                    }
                    case "6", "cuenta bancaria" -> {
                        System.out.print("Ingrese la nueva cuenta bancaria: ");
                        String cuenta_nueva = sc.nextLine();
                        columna = "cuenta_bancaria";
                        valor = cuenta_nueva;
                        boolean actualizado = clienteDAO.actualizar(columna, dni, valor);
                        if (actualizado) {
                            System.out.println("Cuenta bancaria actualizada correctamente.");
                        } else {
                            System.out.println("Error al actualizar la cuenta bancaria.");
                        }
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
        ClienteDAO clienteDAO = new ClienteDAO();
        cliente = clienteDAO.buscarMostrar(dni);

        if (cliente == null) {
            System.out.println("ERR0R: No se encontró al cliente con ese DNI");
        } else {
            System.out.println("Está por eliminar al siguiente cliente: ");
            System.out.println("Nombre: " + cliente.getNombre() + " " + cliente.getApellido());
            System.out.println("Dirección: " + cliente.getDireccion());
            System.out.println("Teléfono: " + cliente.getTelefono());
            System.out.println("Cuenta Bancaria: " + cliente.getCuentaBancaria());
            System.out.println("---¿Está seguro?---");
            String opcion;
            boolean seguir = true;
            do { 
                System.out.println("1. SI / 2. NO");
                opcion = sc.next();
                switch (opcion) {
                    case "1", "si", "SI" -> {
                        if (clienteDAO.buscar(dni).equals(dni)) {
                            clienteDAO.eliminar(dni);
                            System.out.println("Cliente eliminado");
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
            } while (seguir);
        }
    }
    public void mostrarClientes() {
        ClienteDAO clienteDAO = new ClienteDAO();
        array_clientes = clienteDAO.obtenerTodos(); // Llenar la lista con los clientes de la BD

        if (array_clientes == null) {
         array_clientes = new ArrayList<>(); // Evitar que sea null en caso de error
        }

        if (array_clientes.isEmpty()) {
            System.out.println("No hay clientes registrados");
        } else {
            System.out.println("Clientes: ");
            for (Cliente cliente : array_clientes) {
                System.out.println(cliente); // `toString()
            }
        }
    }    
}