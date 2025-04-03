package view;

import dao.EncargoDAO;
import dao.ServicioDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import model.Encargo;

public class EncargoVIEW {

    public ArrayList<Encargo> array_encargos = new ArrayList<>();
    public EncargoDAO dao = new EncargoDAO();
    
    Scanner sc = new Scanner(System.in);
    public void registrar() {
        String matricula_vehiculo;
        Double precio_total = 0.0;
        Encargo encargo;
        LocalDate fecha_inicio;

        System.out.print("Ingrese la matricula: ");
        matricula_vehiculo = sc.nextLine();
        encargo = new Encargo(matricula_vehiculo);
        dao.insertar(encargo);
        encargo.setId(dao.obtenerUltimo(matricula_vehiculo).getId());
        
        ServicioVIEW serview = new ServicioVIEW();
        ServicioDAO serdao = new ServicioDAO();
        System.out.println("Cuáles servicios quiere añadir?");
        System.out.println("(0 para dejar de añadir)");
        serview.mostrarServicios();
        String id_servicio;
        int num_servicios = 0;
        do {
            //TODO: Validar que no agregue varios servicios del mismo tipo
            //Tal vez añadiendo el id_servicio a un String más largo
            System.out.print("--> ");
            id_servicio = sc.next();
            if (!id_servicio.equals("0")) {
                precio_total += serdao.obtener(id_servicio).getPrecio();
                dao.insertarServicio(String.valueOf(encargo.getId()), id_servicio);
                num_servicios++;
            }
        } while (!id_servicio.equals("0") && num_servicios > 0);

        System.out.println("El encargo empezará ahora?");
        System.out.println("1. SI / 2. NO");
        System.out.print("--> ");
        String opcion = sc.next().toLowerCase();
        switch (opcion) {
            case "1", "si" -> {
                fecha_inicio = LocalDate.now();
            }
            case "2", "no" -> {/*TODO: Añadir mensaje?*/}
            default -> {
                System.out.println("No se reconoció esa opción");
            }
        }

        System.out.println("Encargo registrado.");
    }
    // public void modificar() {
    //     String opcion;
    //     String dni;
    //     String columna;
    //     String valor;

    //     Encargo encargo_modificar = null;
    //     do {
    //         System.out.println("Qué desea modificar?");
    //         System.out.println("1. Dni");
    //         System.out.println("2. Nombre");
    //         System.out.println("3. Apellido");
    //         System.out.println("4. Dirección");
    //         System.out.println("5. Teléfono");
    //         System.out.println("6. Cuenta Bancaria");
    //         System.out.println("0. Atrás");
    //         System.out.print(">>> ");
    //         opcion = sc.nextLine();
    //         //TODO: Validar que el DNI exista
    //         System.out.println("Ingrese el DNI del encargo a modificar: ");
    //         System.out.print("--> ");
    //         dni = sc.nextLine();

    //         encargo_modificar = dao.buscarMostrar(dni);
            
    //         if (encargo_modificar == null) {
    //             System.out.println("ERR0R: No se encontró el encargo");
    //         } else {
    //             switch (opcion) {
    //                 //TODO: Añadir validaciones
    //                 case "1", "dni" -> {
    //                     System.out.print("Ingrese el nuevo DNI: ");
    //                     String dni_nuevo = sc.next();
    //                     valor = dni_nuevo;
    //                     columna = "dni";
                        
    //                     boolean actualizado = dao.actualizar(columna, dni, valor);
    //                     if (actualizado) {
    //                     System.out.println("DNI actualizado correctamente.");
    //                     } else {
    //                     System.out.println("Error al actualizar el DNI.");
    //                     }

    //                 }
    //                 case "2", "nombre" -> {
    //                     System.out.print("Ingrese el nuevo nombre: ");
    //                     String nombre_nuevo = sc.next();
    //                     columna = "nombre";
    //                     valor = nombre_nuevo;
    //                     boolean actualizado = dao.actualizar(columna, dni, valor);
    //                     if (actualizado) {
    //                         System.out.println("Nombre actualizado correctamente.");
    //                     } else {
    //                         System.out.println("Error al actualizar el nombre.");
    //                     }
    //                 }
    //                 case "3", "apellido" -> {
    //                     System.out.print("Ingrese el nuevo apellido: ");
    //                     String apellido_nuevo = sc.next();
    //                     columna = "apellido";
    //                     valor = apellido_nuevo;
    //                     boolean actualizado = dao.actualizar(columna, dni, valor);
    //                     if (actualizado) {
    //                         System.out.println("Apellido actualizado correctamente.");
    //                     } else {
    //                         System.out.println("Error al actualizar el apellido.");
    //                     }
    //                 }
    //                 case "4", "direccion" -> {
    //                     System.out.print("Ingrese la nueva dirección: ");
    //                     String direccion_nueva = sc.nextLine();
    //                     columna = "direccion";
    //                     valor = direccion_nueva;
    //                     boolean actualizado = dao.actualizar(columna, dni, valor);
    //                     if (actualizado) {
    //                         System.out.println("Dirección actualizada correctamente.");
    //                     } else {
    //                         System.out.println("Error al actualizar la dirección.");
    //                     }
    //                 }
    //                 case "5", "telefono" -> {
    //                     System.out.print("Ingrese el nuevo teléfono: ");
    //                     int telefono_nuevo = sc.nextInt();
    //                     columna = "telefono";
    //                     valor = Integer.toString(telefono_nuevo); //paso el valor a String
    //                     boolean actualizado = dao.actualizar(columna, dni, valor);
    //                     if (actualizado) {
    //                         System.out.println("Teléfono actualizado correctamente.");
    //                     } else {
    //                         System.out.println("Error al actualizar el teléfono.");
    //                     }
    //                 }
    //                 case "6", "cuenta bancaria" -> {
    //                     System.out.print("Ingrese la nueva cuenta bancaria: ");
    //                     String cuenta_nueva = sc.nextLine();
    //                     columna = "cuenta_bancaria";
    //                     valor = cuenta_nueva;
    //                     boolean actualizado = dao.actualizar(columna, dni, valor);
    //                     if (actualizado) {
    //                         System.out.println("Cuenta bancaria actualizada correctamente.");
    //                     } else {
    //                         System.out.println("Error al actualizar la cuenta bancaria.");
    //                     }
    //                 }
    //                 default -> {
    //                     System.out.println("ERR0R: No se reconoció esa opción");
    //                 }
    //             }
    //         }

    //     } while (!opcion.equalsIgnoreCase("0"));

    // }
    // public void eliminar() {
    //     String dni;
    //     Encargo encargo = null;
    //     System.out.print("Ingrese el DNI del encargo que quiere eliminar: ");
    //     dni = sc.next();
    //     encargo = dao.buscarMostrar(dni);

    //     if (encargo == null) {
    //         System.out.println("ERR0R: No se encontró al encargo con ese DNI");
    //     } else {
    //         System.out.println("Está por eliminar al siguiente encargo: ");
    //         System.out.println("Nombre: " + encargo.getNombre() + " " + encargo.getApellido());
    //         System.out.println("Dirección: " + encargo.getDireccion());
    //         System.out.println("Teléfono: " + encargo.getTelefono());
    //         System.out.println("Cuenta Bancaria: " + encargo.getCuentaBancaria());
    //         System.out.println("---¿Está seguro?---");
    //         String opcion;
    //         boolean seguir = true;
    //         do { 
    //             System.out.println("1. SI / 2. NO");
    //             opcion = sc.next();
    //             switch (opcion) {
    //                 case "1", "si", "SI" -> {
    //                     if (dao.buscar(dni).equals(dni)) {
    //                         dao.eliminar(dni);
    //                         System.out.println("Encargo eliminado");
    //                         seguir = false;
                            
    //                     } else {
    //                         System.out.println("No se encuentra dni");
    //                     }
    //                 }
    //                 case "2", "no", "NO" -> {
    //                     System.out.println("Abortando...");
    //                     seguir = false;
    //                 }
                
    //                 default -> {
    //                     System.out.println("No se reconoció esa opción.");
    //                 }
    //             }
    //         } while (seguir);
    //     }
    // }
    // public void mostrarEncargos() {
    //     array_encargos = dao.obtenerTodos(); // Llenar la lista con los encargos de la BD

    //     if (array_encargos == null) {
    //      array_encargos = new ArrayList<>(); // Evitar que sea null en caso de error
    //     }

    //     if (array_encargos.isEmpty()) {
    //         System.out.println("No hay encargos registrados");
    //     } else {
    //         System.out.println("Encargos: ");
    //         for (Encargo encargo : array_encargos) {
    //             System.out.println(encargo); // `toString()
    //         }
    //     }
    // }    
}
