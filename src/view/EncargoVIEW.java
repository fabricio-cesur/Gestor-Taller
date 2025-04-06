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
    public Formateo form = new Formateo();
    public ServicioVIEW serview = new ServicioVIEW();
    public ServicioDAO serdao = new ServicioDAO();
    public Validacion val = new Validacion();
    
    Scanner sc = new Scanner(System.in);

    public void menu() {
        String opcion;

        do {
            System.out.println("Qué desea hacer con los encargos?");
            System.out.println("1. Registrar Encargo");
            System.out.println("2. Modificar Encargo");
            System.out.println("3. Eliminar Encargo");
            System.out.println("4. Mostrar Encargos");
            System.out.println("5. Mostrar Servicios");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next();
            sc.nextLine();

            switch (opcion) {
                case "1" -> { registrar(); }
                case "2" -> { modificar(); }
                case "3" -> { eliminar(); }
                case "4" -> { mostrarEncargos(); }
                case "5" -> { mostrarServiciosMenu(); }
                case "0" -> { System.out.println("Volviendo al menu anterior. ");}
                default -> {
                    System.out.println("No se reconoció esa opción");
                }
            }
        } while (!opcion.equalsIgnoreCase("0"));
    }

    public void registrar() {
        String matricula_vehiculo;
        Double precio_total = 0.0;
        Encargo encargo;

        //Bucle que no sale hasta que la matricula ingresada sea correcta y exista
        do {
            System.out.print("Ingrese la matricula: ");
            matricula_vehiculo = sc.nextLine();
            matricula_vehiculo = form.matricula(matricula_vehiculo);
        } while (!val.validarMatricula(matricula_vehiculo));
        //Se crea un encargo y se inserta en la base de datos para crear su id
        encargo = new Encargo(matricula_vehiculo);
        if (!dao.insertar(encargo)) {
            System.out.println("ERR0R: La inserción del encargo falló");
            return;
        }
        //Se obtiene el id del encargo que se acaba de hacer
        encargo.setId(dao.obtenerUltimo(matricula_vehiculo).getId());
        
        System.out.println("Cuáles servicios quiere añadir?");
        System.out.println("(0 para dejar de añadir)");
        serview.mostrarServicios();
        String id_servicio;
        int num_servicios = 0;
        ArrayList<Integer> servicios_añadidos = new ArrayList<>();
        //Bucle para que agregue servicios hasta que ponga "0"
        do {
            //Bucle hasta que ingrese uan id de servicio válida
            do {
                System.out.print("--> ");
                id_servicio = sc.nextLine();
                if (id_servicio.equalsIgnoreCase("0")) {
                    break;
                }
                id_servicio = form.id(id_servicio);
            } while (!val.validarId(id_servicio, "servicio"));
            int int_id = Integer.parseInt(id_servicio);
            if (!id_servicio.equals("0")) {
                //Revisa que el servicio no haya sido añadido ya
                if (servicios_añadidos.contains(int_id)) {
                    System.out.println("Este servicio ya se ha añadido");
                } else {
                    //Suma el precio del servicio al total e inserta el servicio en la tabla auxiliar
                    precio_total += serdao.obtener(id_servicio).getPrecio();
                    dao.insertarServicio(encargo.getId(), int_id);
                    num_servicios++;
                    servicios_añadidos.add(int_id);
                }
            } else if (num_servicios == 0) {
                System.out.println("Debe añadir al menos 1 servicio");
            }
        } while (!id_servicio.equals("0") || num_servicios == 0);
        //Cuando tenga todos los servicios en la tabla auxiliar junto con el precio total lo agrega a la base de datos
        dao.actualizar("precio_total", encargo.getId(), Double.toString(precio_total));

        //Pregunta para establecer el día en el que se empieza el encargo
        System.out.println("El encargo empezará ahora?");
        System.out.println("1. SI / 2. NO");
        String opcion;
        boolean opcion_valida;
        //Bucle hasta que el usuario de una opción válida
        do {
            System.out.print("--> ");
            opcion = sc.next().toLowerCase();
            switch (opcion) {
                case "1", "si" -> {
                    LocalDate fecha_inicio = LocalDate.now();
                    dao.actualizar("fecha_inicio", encargo.getId(), form.dateToString(fecha_inicio));
                    opcion_valida = true;
                }
                case "2", "no" -> {
                    System.out.println("Marque cuando cuando el encargo empiece");
                    opcion_valida = true;
                }
                default -> {
                    System.out.println("No se reconoció esa opción");
                    opcion_valida = false;
                }
            }
        } while (!opcion_valida);
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
            opcion = sc.next().toLowerCase();
            sc.nextLine();
            System.out.println("Ingrese la matrícula del encargo a modificar");
            //Bucle que valida que la matrícula esté bien escrita y exista
            do {
                System.out.print("--> ");
                matricula = sc.next();
                sc.nextLine();
                matricula = form.matricula(matricula);
            } while (!val.validarMatricula(matricula));
            encargo_modificar = dao.obtenerUltimo(matricula);
            
            String alternativa;
            if (encargo_modificar == null) {
                System.out.println("ERR0R: No se encontró el encargo");
                return;
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
                        mostrarServiciosEncargo(encargo_modificar.getId());
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
                                valor = form.dateToString(fecha_inicio);
                                if (dao.actualizar(columna, encargo_modificar.getId(), valor)) {
                                    System.out.println("Fecha de inicio actualizada correctamente.");
                                } else {
                                    System.out.println("Error al actualizar la fecha de inicio.");
                                }
                            }
                            case "2", "no" -> {
                                System.out.println("Marque la fecha de inicio del encargo cuando empiece a hacerse");
                            }
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
                                valor = form.dateToString(fecha_finalizado);
                                if (dao.actualizar(columna, encargo_modificar.getId(), valor)) {
                                    System.out.println("Encargo terminado el " + fecha_finalizado);
                                } else {
                                    System.out.println("Error al actualizar la fecha de inicio.");
                                }
                            }
                            case "2", "no" -> {
                                System.out.println("Marque el encargo cómo completado cuándo lo esté");
                            }
                            default -> {
                                System.out.println("No se reconoció esa opción");
                            }
                        }
                    }
                    case "0" -> { System.out.println("Volviendo al menu anterior. ");}
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
    public void mostrarServiciosMenu() {
        System.out.println("Ingrese la matrícula del encargo");
        System.out.print("--> ");
        String matricula = sc.next();
        sc.nextLine();
    
        Encargo encargo = dao.obtenerUltimo(matricula);
        int id_encargo = encargo.getId();

        mostrarServiciosEncargo(id_encargo);
    }
    public void mostrarServiciosEncargo(int id_encargo) {
        ArrayList<Servicio> array_servicios;
        array_servicios = dao.obtenerServicios(id_encargo);

        if (array_servicios == null) {
            array_servicios = new ArrayList<>(); // Evitar que sea null en caso de error
        }

        if (array_servicios.isEmpty()) {
            System.out.println("No hay servicios registrados");
        } else {
            System.out.println("Servicios: ");
            for (Servicio servicio : array_servicios) {
                System.out.println(servicio); // toString()
            }
        }
    }    
}
