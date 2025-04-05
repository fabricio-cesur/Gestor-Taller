package view;

import dao.ServicioDAO;
import java.util.ArrayList;
import java.util.Scanner;
import model.Servicio;

public class ServicioVIEW {
    public ArrayList<Servicio> array_servicios = new ArrayList<>();
    public Scanner sc = new Scanner(System.in);
    
    //Menu principal de la clase servicio
    public void menu() {
        String opcion;
        do { 
            System.out.println("Qué desea hacer con los servicios?");
            System.out.println("1. Registrar Servicio");
            System.out.println("2. Modificar Servicio");
            System.out.println("3. Eliminar Servicio");
            System.out.println("4. Mostrar Servicios");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next();
            sc.nextLine();
            //Limpia el buffer de entrada de datos
   
            switch (opcion) {
                case "1", "registrar" -> { registrar(); }
                case "2", "modificar" -> {  modificar();}
                case "3", "eliminar" -> { eliminar(); }
                case "4", "mostrar" -> { mostrarServicios(); }
                case "0" -> { System.out.println("Volviendo al menu anterior. ");}

                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }
        } while (!opcion.equalsIgnoreCase("0"));
    }

    //Metodo para registrar un nuevo servicio
    public void registrar() {
        String nombre;
        String descripcion;
        int cod_item;
        double precio;
        Servicio servicio;
        
        System.out.print("Ingrese el nombre: ");
        nombre = sc.nextLine();
        System.out.print("Ingrese la descripción: ");
        descripcion = sc.nextLine();
        
        System.out.print("Ingrese el código del item que utilice: ");
        cod_item = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el precio: ");
        precio = sc.nextDouble();
        sc.nextLine();

        //Crea el objeto servicio para insertarlo en la DB
        servicio = new Servicio(nombre, descripcion, cod_item, precio);

        ServicioDAO servicioDAO = new ServicioDAO();
        if (servicioDAO.insertar(servicio)) {
            System.out.println("Servicio registrado correctamente");
        } else {
            System.out.println("No se ha podido registrar el servicio");
        }
    }

    //Metodo modificar Servicio
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
            
            //En caso de que se introduzca 0 sale del menu y no sigue con el codigo
            if(opcion.equalsIgnoreCase("0")) {
                break;
            }

            System.out.println("Ingrese el id del servicio");
            System.out.print("-->");
            id = sc.next();
            sc.nextLine();
            
            ServicioDAO servicioDAO = new ServicioDAO();
            //Otiene el objeto servicio segun el id
            servicio_modificar = servicioDAO.obtener(id);
            
            if (servicio_modificar == null) {
                System.out.println("ERR0R: No se encontró el servicio");
            } else {
                switch (opcion) {
                    case "1", "nombre" -> {
                        System.out.println("Ingrese el nuevo nombre del servicio: ");
                        String nombre_nuevo = sc.nextLine();
                        columna = "nombre";
                        valor = nombre_nuevo;
                        //Actualiza el servicio y segun si lo ha conseguido o no muestra una cosa u otra
                        boolean actualizado = servicioDAO.actualizar(columna, id, valor);

                        if (actualizado) {
                            System.out.println("Nombre modificado correctamente");
                        } else {
                            System.out.println("Error al modificar el nombre");
                        }
                        //Para evitar problemas con menus posteriores
                        opcion = "0";
                    }
                    case "2", "descripcion" -> {
                        System.out.println("Ingrese la nueva descripción del servicio: ");
                        String descripcion_nueva = sc.nextLine();
                        columna = "descripcion";
                        valor = descripcion_nueva;
                        boolean actualizado = servicioDAO.actualizar(columna, id, valor);
    
                        if (actualizado) {
                            System.out.println("Descripción modificado correctamente");
                        } else {
                            System.out.println("Error al modificar la descripción");
                        }
                        opcion = "0";
                    }
                    case "3", "item" -> {
                        System.out.println("Ingrese el código del nuevo item: ");
                        String cod_item_nuevo = sc.nextLine();
                        columna = "item";
                        valor = cod_item_nuevo;
                        boolean actualizado = servicioDAO.actualizar(columna, id, valor);

    
                        if (actualizado) {
                            System.out.println("Item modificado correctamente");
                        } else {
                            System.out.println("Error al modificar el item");
                        }
                        opcion = "0";

                    }
                    case "4", "precio" -> {
                        System.out.println("Ingrese el nuevo precio del servicio: ");
                        double precio_nuevo = sc.nextDouble();
                        sc.nextLine();
                        columna = "precio";
                        valor = Double.toString(precio_nuevo);
                        boolean actualizado = servicioDAO.actualizar(columna, id, valor);

    
                        if (actualizado) {
                            System.out.println("Nombre modificado correctamente");
                        } else {
                            System.out.println("Error al modificar el nombre");
                        }
                        opcion = "0";

                    }
                    case "0" -> { System.out.println("Volviendo al menu anterior. ");}
                    default -> {
                        System.out.println("No se reconoció esa opción");
                    }
                }
            }
        } while (!opcion.equals("0"));
    }

    //MEtodo eliminar servicio
    public void eliminar() {
        String id;
        Servicio servicio;
        //TODO: Añadir validación de que el id del servicio exista
        System.out.print("Ingrese el id del servicio que quiera borrar: ");
        id = sc.next();
        ServicioDAO servicioDAO = new ServicioDAO();
        //Obtiene el objeto servicio segun id
        servicio = servicioDAO.obtener(id);

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
                sc.nextLine();
                switch (opcion) {
                    case "1", "si", "SI" -> {
                        //Compara id introducido don el de db y elimina servicio si coincide
                        if (servicioDAO.buscar(id).equals(id)) {
                            servicioDAO.eliminar(id);
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

    //Metodo para mostrar todos los servicios
    public void mostrarServicios() {
        ServicioDAO servicioDAO = new ServicioDAO();
        array_servicios = servicioDAO.obtenerTodos(); // Llenar la lista con los servicios de la BD

        if (array_servicios == null) {
            array_servicios = new ArrayList<>(); // Evitar que sea null en caso de error
        }

        if (array_servicios.isEmpty()) {
            System.out.println("No hay servicios registrados");
        } else {
            System.out.println("Servicios: ");
            for (Servicio servicio : array_servicios) {
                System.out.println(servicio); // imprime los sericios utilizando la plantilla toString()
            }
        }
    }
}
