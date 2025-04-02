package view;

import dao.ServicioDAO;
import java.util.ArrayList;
import java.util.Scanner;
import model.Servicio;

public class ServicioVIEW {
    public ArrayList<Servicio> array_servicios = new ArrayList<>();
    public Scanner sc = new Scanner(System.in);
    public ServicioDAO dao = new ServicioDAO();

    public void registrar() {
        String nombre;
        String descripcion;
        String cod_item;
        double precio;
        //TODO: Añadir validaciones al registrar servicio
        System.out.print("Ingrese el nombre: ");
        nombre = sc.nextLine();
        System.out.print("Ingrese la descripción: ");
        descripcion = sc.nextLine();
        //TODO: Mostrar los items
        System.out.print("Ingrese el código del item que utilice: ");
        cod_item = sc.nextLine();
        System.out.print("Ingrese el precio: ");
        precio = sc.nextDouble();

        Servicio servicio = new Servicio(nombre, descripcion, cod_item, precio);

        dao.insertar(servicio);
    }
    public void modificar() {
        String opcion;
        String id;
        String columna;
        String valor;
        Servicio servicio_modificar;
        do {
            System.out.println("Qué desea modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Descripción");
            System.out.println("3. Item");
            System.out.println("4. Precio");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.nextLine();
            opcion = opcion.toLowerCase();
            
            System.out.println("Ingrese el id del servicio");
            System.out.print("-->");
            id = sc.next();
            
            servicio_modificar = dao.obtener(id);
            
            if (servicio_modificar == null) {
                System.out.println("ERR0R: No se encontró el servicio");
            } else {
                switch (opcion) {
                    case "1", "nombre" -> {
                        System.out.println("Ingrese el nuevo nombre del servicio: ");
                        String nombre_nuevo = sc.nextLine();
                        columna = "nombre";
                        valor = nombre_nuevo;
    
                        if (dao.actualizar(columna, id, valor)) {
                            System.out.println("Nombre modificado correctamente");
                        } else {
                            System.out.println("Error al modificar el nombre");
                        }
                    }
                    case "2", "descripcion" -> {
                        System.out.println("Ingrese la nueva descripción del servicio: ");
                        String descripcion_nueva = sc.nextLine();
                        columna = "descripcion";
                        valor = descripcion_nueva;
    
                        if (dao.actualizar(columna, id, valor)) {
                            System.out.println("Descripción modificado correctamente");
                        } else {
                            System.out.println("Error al modificar la descripción");
                        }
                    }
                    case "3", "item" -> {
                        System.out.println("Ingrese el código del nuevo item: ");
                        String cod_item_nuevo = sc.next();
                        columna = "item";
                        valor = cod_item_nuevo;
    
                        if (dao.actualizar(columna, id, valor)) {
                            System.out.println("Item modificado correctamente");
                        } else {
                            System.out.println("Error al modificar el item");
                        }
                    }
                    case "4", "precio" -> {
                        System.out.println("Ingrese el nuevo precio del servicio: ");
                        double precio_nuevo = sc.nextDouble();
                        columna = "precio";
                        valor = Double.toString(precio_nuevo);
    
                        if (dao.actualizar(columna, id, valor)) {
                            System.out.println("Nombre modificado correctamente");
                        } else {
                            System.out.println("Error al modificar el nombre");
                        }
                    }
                }
            }
        } while (!opcion.equals("0"));
    }
    public void eliminar() {
        String id;
        Servicio servicio;
        //TODO: Añadir validación de que el id del servicio exista
        System.out.print("Ingrese el id del servicio que quiera borrar: ");
        id = sc.next();
        servicio = dao.obtener(id);

        if (servicio == null) {
            System.out.println("ERR0R: No se encontró el servicio con ese id");
        } else {
            System.out.println("Está por eliminar al siguiente servicio: ");
            System.out.println("Nombre: " + servicio.getNombre());
            System.out.println("Descripción: " + servicio.getDescripcion());
            System.out.println("Item: " + servicio.getItemCodigo()); // servicio.getItem.getNombre();
            System.out.println("Precio: " + servicio.getPrecio());
            System.out.println("---¿Está seguro?---");
            String opcion;
            boolean seguir = true;
            do { 
                System.out.println("1. SI / 2. NO");
                opcion = sc.next();
                switch (opcion) {
                    case "1", "si", "SI" -> {
                        if (dao.buscar(id).equals(id)) {
                            dao.eliminar(id);
                            System.out.println("Servicio eliminado");
                            seguir = false;
                        } else {
                            System.out.println("No se encuentra el id");
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
    public void mostrarServicios() {
        array_servicios = dao.obtenerTodos(); // Llenar la lista con los servicios de la BD

        if (array_servicios == null) {
            array_servicios = new ArrayList<>(); // Evitar que sea null en caso de error
        }

        if (array_servicios.isEmpty()) {
            System.out.println("No hay clientes registrados");
        } else {
            System.out.println("Servicios: ");
            for (Servicio servicio : array_servicios) {
                System.out.println(servicio); // toString()
            }
        }
    }
}
