package view;

import dao.EncargoDAO;
import dao.ServicioDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import model.Encargo;
import model.Servicio;

public class EncargoVIEW {

    public ArrayList<Encargo> array_encargos = new ArrayList<>();
    public EncargoDAO dao = new EncargoDAO();
    public Formateo formateo = new Formateo();
    public ServicioVIEW serview = new ServicioVIEW();
    public ServicioDAO serdao = new ServicioDAO();
    
    Scanner sc = new Scanner(System.in);
    public void registrar() {
        String matricula_vehiculo;
        Double precio_total = 0.0;
        Encargo encargo;

        System.out.print("Ingrese la matricula: ");
        matricula_vehiculo = sc.nextLine();
        encargo = new Encargo(matricula_vehiculo);
        dao.insertar(encargo);
        encargo.setId(dao.obtenerUltimo(matricula_vehiculo).getId());
        
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
                dao.insertarServicio(encargo.getId(), Integer.parseInt(id_servicio));
                num_servicios++;
            }
        } while (!id_servicio.equals("0") && num_servicios > 0);
        dao.actualizar("precio_total", encargo.getId(), Double.toString(precio_total));

        System.out.println("El encargo empezará ahora?");
        System.out.println("1. SI / 2. NO");
        System.out.print("--> ");
        String opcion = sc.next().toLowerCase();
        switch (opcion) {
            case "1", "si" -> {
                LocalDate fecha_inicio = LocalDate.now();
                dao.actualizar("fecha_inicio", encargo.getId(), formateo.dateToString(fecha_inicio));
            }
            case "2", "no" -> {/*TODO: Añadir mensaje?*/}
            default -> {
                System.out.println("No se reconoció esa opción");
            }
        }

        System.out.println("Encargo registrado.");
    }
    public void modificar() {
        String opcion;
        String matricula;
        String columna;
        String valor;

        Encargo encargo_modificar;
        do {
            System.out.println("Qué desea modificar?");
            System.out.println("1. Matricula");
            System.out.println("2. Servicios");
            System.out.println("3. Fecha Inicio");
            System.out.println("4. Completado");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.nextLine().toLowerCase();
            //TODO: Validar que el Encargo exista
            System.out.println("Ingrese la matrícula del encargo a modificar");
            System.out.print("--> ");
            matricula = sc.nextLine();

            encargo_modificar = dao.obtenerUltimo(matricula);
            
            String alternativa;
            if (encargo_modificar == null) {
                System.out.println("ERR0R: No se encontró el encargo");
            } else {
                switch (opcion) {
                    //TODO: Añadir validaciones
                    case "1", "matricula" -> {
                        System.out.print("Ingrese la nueva matrícula: ");
                        String matricula_nueva = sc.next();
                        valor = matricula_nueva;
                        columna = "matricula";
                        
                        if (dao.actualizar(columna, encargo_modificar.getId(), valor)) {
                        System.out.println("DNI actualizado correctamente.");
                        } else {
                        System.out.println("Error al actualizar el DNI.");
                        }
                    }
                    case "2", "servicios" -> {
                        mostrarServicios();
                        System.out.println("Qué quiere hacer?");
                        System.out.println("1. Quitar Servicio");
                        System.out.println("2. Añadir Servicio");
                        System.out.print("--> ");
                        alternativa = sc.next().toLowerCase();
                        switch (alternativa) {
                            case "1", "quitar" -> {
                                System.out.println("Cuál quiere quitar?");
                                System.out.print("--> ");
                                int id_servicio = sc.nextInt();
                                dao.quitarServicio(encargo_modificar.getId(), id_servicio);
                            }
                            case "2", "añadir" -> {
                                System.out.println("Cuál quiere añadir?");
                                System.out.print("--> ");
                                int id_servicio = sc.nextInt();
                                dao.insertarServicio(encargo_modificar.getId(), id_servicio);
                            }
                            default -> {}
                        }
                    }
                    case "3", "fecha incio", "fecha", "inicio" -> {
                        System.out.println("El encargo empezará ahora?");
                        System.out.println("1. SI / 2. NO");
                        System.out.print("--> ");
                        alternativa = sc.next().toLowerCase();
                        switch (alternativa) {
                            case "1", "si" -> {
                                LocalDate fecha_inicio = LocalDate.now();
                                columna = "fecha_inicio";
                                valor = formateo.dateToString(fecha_inicio);
                                if (dao.actualizar(columna, encargo_modificar.getId(), valor)) {
                                    System.out.println("Fecha de inicio actualizada correctamente.");
                                } else {
                                    System.out.println("Error al actualizar la fecha de inicio.");
                                }
                            }
                            case "2", "no" -> {/*TODO: Añadir mensaje?*/}
                            default -> {
                                System.out.println("No se reconoció esa opción");
                            }
                        }
                    }
                    case "4", "completado" -> {
                        System.out.println("El encargo lo ha terminado?");
                        System.out.println("1. SI / 2. NO");
                        System.out.print("--> ");
                        alternativa = sc.next().toLowerCase();
                        LocalDate fecha_finalizado;
                        switch (alternativa) {
                            case "1", "si" -> {
                                columna = "fecha_finalizado";
                                fecha_finalizado = LocalDate.now();
                                valor = formateo.dateToString(fecha_finalizado);
                                if (dao.actualizar(columna, encargo_modificar.getId(), valor)) {
                                    System.out.println("Encargo terminado el " + fecha_finalizado);
                                } else {
                                    System.out.println("Error al actualizar la fecha de inicio.");
                                }
                            }
                            case "2", "no" -> {/*TODO: Añadir mensaje?*/}
                            default -> {
                                System.out.println("No se reconoció esa opción");
                            }
                        }
                    }
                    default -> {
                        System.out.println("No se reconoció esa opción");
                    }
                }
            }
        } while (!opcion.equalsIgnoreCase("0"));
    }
    public void eliminar() {
        System.out.println("Ingrese la matrícula del encargo a eliminar");
        System.out.print("--> ");
        String matricula = sc.nextLine();

        Encargo encargo = dao.obtenerUltimo(matricula);
        int id_encargo = encargo.getId();
        System.out.println("Está por eliminar al siguiente encargo: ");
        System.out.println("ID: " + encargo.getId() + " Matrícula: " + encargo.getMatricula());
        System.out.println("Precio total: " + encargo.getPrecioTotal());
        System.out.println("Inicio: " + encargo.getFechaInicio() + " Finalizado: " + encargo.getFechaInicio());
        System.out.println("Completado: " + encargo.getCompletado());
        System.out.println("---¿Está seguro?---");
        String opcion;
        boolean seguir = true;
        do { 
            System.out.println("1. SI / 2. NO");
            opcion = sc.next().toLowerCase();
            switch (opcion) {
                case "1", "si" -> {
                    if (dao.obtenerUltimo(matricula).getId() == id_encargo) {
                        dao.eliminar(id_encargo);
                        System.out.println("Encargo eliminado");
                        seguir = false;
                    } else {
                        System.out.println("No se encuentra id_encargo");
                    }
                }
                case "2", "no" -> {
                    System.out.println("Abortando...");
                    seguir = false;
                }
            
                default -> {
                    System.out.println("No se reconoció esa opción.");
                }
            }
        } while (seguir);
    }
    public void mostrarEncargos() {
        array_encargos = dao.obtenerTodos(); // Llenar la lista con los encargos de la BD

        if (array_encargos == null) {
         array_encargos = new ArrayList<>(); // Evitar que sea null en caso de error
        }

        if (array_encargos.isEmpty()) {
            System.out.println("No hay encargos registrados");
        } else {
            System.out.println("Encargos: ");
            for (Encargo encargo : array_encargos) {
                System.out.println(encargo); // `toString()
            }
        }
    }    
    public void mostrarServicios() {
        ArrayList<Servicio> array_servicios = new ArrayList<>();
        array_servicios = serdao.obtenerTodos(); // Llenar la lista con los servicios de la BD

        if (array_servicios == null) {
            array_servicios = new ArrayList<>(); // Evitar que sea null en caso de error
        }

        if (array_servicios.isEmpty()) {
            System.out.println("No hay servicios registrados");
        } else {
            System.out.println("Servicios: ");
            for (Servicio servicio : array_servicios) {
                System.out.println(servicio); // `toString()
            }
        }
    }    
}
