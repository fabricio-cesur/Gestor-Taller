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
        Encargo encargo;

        //Bucle que no sale hasta que la matricula ingresada sea correcta y exista
        do {
            System.out.print("Ingrese la matricula: ");
            matricula_vehiculo = sc.nextLine();
            matricula_vehiculo = form.matricula(matricula_vehiculo);
        } while (!val.validarMatricula(matricula_vehiculo, true));
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
            //Bucle hasta que ingrese una id de servicio válida
            do {
                System.out.print("--> ");
                id_servicio = sc.nextLine();
                if (id_servicio.equalsIgnoreCase("0")) {
                    break;
                }
                id_servicio = form.id(id_servicio);
            } while (!val.validarId(id_servicio, "servicio", true));
            int int_id = Integer.parseInt(id_servicio);
            if (!id_servicio.equals("0")) {
                //Revisa que el servicio no haya sido añadido ya
                if (servicios_añadidos.contains(int_id)) {
                    System.out.println("Este servicio ya se ha añadido");
                } else {
                    //Suma el precio del servicio al total e inserta el servicio en la tabla auxiliar
                    dao.insertarServicio(encargo.getId(), int_id);
                    num_servicios++;
                    servicios_añadidos.add(int_id);
                }
            } else if (num_servicios == 0) {
                System.out.println("Debe añadir al menos 1 servicio");
            }
        } while (!id_servicio.equals("0") || num_servicios == 0);
        //Cuando tenga todos los servicios en la tabla auxiliar junto con el precio total lo agrega a la base de datos
        dao.actualizarPrecioTotal(encargo.getId());

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
            System.out.println("1. Matrícula");
            System.out.println("2. Servicios");
            System.out.println("3. Fecha Inicio");
            System.out.println("4. Completado");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next().toLowerCase();
            sc.nextLine();
            //Revisando si es 0 antes de preguntar la matrícula
            if (opcion.equals("0")) {
                System.out.println("Volviendo al menu anterior.");
                break;
            }
            //Revisando que se reconozca la opción, de forma que no sea así se vuelve a iterar el do-while
            if (!(opcion.equals("1") || opcion.equals("2") || opcion.equals("3") || opcion.equals("4"))) {
                System.out.println("No se reconoció esa opción");
                continue;
            }
            System.out.println("Ingrese la matrícula del encargo a modificar");
            //Bucle que valida que la matrícula esté bien escrita y exista
            do {
                System.out.print("--> ");
                matricula = form.matricula(sc.nextLine());
            } while (!val.validarMatricula(matricula, true));
            encargo_modificar = dao.obtenerUltimo(matricula);
            if (encargo_modificar == null) {
                System.out.println("ERR0R: No se encontró el encargo");
                return;
            } else {
                int id_encargo = encargo_modificar.getId();
                String alternativa; //variable para no tener que usar opcion
                boolean alternativa_valida;
                switch (opcion) {
                    case "1", "matricula" -> {
                        String matricula_nueva;
                        //Valida que la nueva matrícula esté bien escrita, pero no revisa si existe
                        do {
                            System.out.print("Ingrese la nueva matrícula: ");
                            matricula_nueva = form.matricula(sc.nextLine());
                        } while (!val.validarMatricula(matricula_nueva, true));
                        valor = matricula_nueva;
                        columna = "matricula_coche";
                        if (dao.actualizar(columna, encargo_modificar.getId(), valor)) {
                            System.out.println("Matrícula actualizada correctamente.");
                        } else {
                            System.out.println("Error al actualizar la Matrícula.");
                        }
                    }
                    case "2", "servicios" -> {
                        mostrarServiciosEncargo(id_encargo);
                        System.out.println("Qué quiere hacer?");
                        System.out.println("1. Quitar Servicio");
                        System.out.println("2. Añadir Servicio");
                        do {
                            System.out.print("--> ");
                            alternativa = sc.next().toLowerCase();
                            switch (alternativa) {
                                case "1", "quitar" -> {
                                    System.out.println("Cuál quiere quitar?");
                                    String id_servicio;
                                    do {
                                        System.out.print("--> ");
                                        id_servicio = sc.next();
                                        id_servicio = form.id(id_servicio);
                                    } while (!val.validarIdAuxiliar(id_servicio, id_encargo, "servicio_encargo"));
                                    dao.quitarServicio(id_encargo, Integer.parseInt(id_servicio));
                                    dao.actualizarPrecioTotal(id_encargo);
                                    alternativa_valida = true;
                                }
                                case "2", "añadir" -> {
                                    String id_servicio;
                                    System.out.println("Qué servicio quiere añadir?");
                                    do {
                                        System.out.print("--> ");
                                        id_servicio = sc.next();
                                        id_servicio = form.id(id_servicio);
                                    } while (val.validarId(id_servicio, "servicio", true));
                                    dao.insertarServicio(id_encargo, Integer.parseInt(id_servicio));
                                    dao.actualizarPrecioTotal(id_encargo);
                                    alternativa_valida = true;
                                }
                                default -> {
                                    System.out.println("No se reconoce esa opcion");
                                    alternativa_valida = false;
                                }
                            }
                        } while (!alternativa_valida);
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
                    default -> {}
                }
            }
        } while (!opcion.equalsIgnoreCase("0"));
    }
    public void eliminar() {
        System.out.println("Quiere seleccionar encargo por 1. matrícula o 2. ID?");
        String decision;
        Encargo encargo = null;
        do {
            System.out.print(">>> ");
            decision = sc.nextLine();
            switch (decision) {
                case "1", "matricula" -> {
                    System.out.println("Ingrese la matrícula del encargo a eliminar");
                    String matricula;
                    do {
                        System.out.print("--> ");
                        matricula = form.matricula(sc.nextLine());
                    } while (!val.validarMatricula(matricula, true));
                    encargo = dao.obtenerUltimo(matricula);
                }
                case "2", "id" -> {
                    System.out.println("Ingrese el id del encargo a eliminar");
                    String id;
                    do {
                        System.out.print("--> ");
                        id = form.id(sc.nextLine());
                    } while (!val.validarId(id, "encargo", true));
                    encargo = dao.obtenerPorID(Integer.parseInt(id));
                }
                default -> {
                    System.out.println("No se reconoció esa opción");
                }
            }
        } while (encargo == null);

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
            sc.nextLine();
            switch (opcion) {
                case "1", "si" -> {
                    if (dao.obtenerPorID(encargo.getId()).getId() == id_encargo) {
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
        String matricula;
        Encargo encargo = null;
        do {
            do {
                System.out.print("--> ");
                matricula = form.matricula(sc.nextLine());
            } while (!val.validarMatricula(matricula, true));
            if (dao.obtenerUltimo(matricula) != null) {
                encargo = dao.obtenerUltimo(matricula);
            }
        } while (encargo == null || !val.validarId(Integer.toString(encargo.getId()), "encargo", true));
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
