package view;


import dao.CitaDAO;
import java.util.ArrayList;
import java.util.Scanner;
import model.Cita;


public class CitaVIEW {
    public ArrayList<Cita> array_citas = new ArrayList<>();
    
    Scanner sc = new Scanner(System.in);

    //MEnu principal de la clase cita
    public void menu() {
        String opcion;
        
        do { 
            System.out.println("Qué desea hacer con las citas?");
            System.out.println("1. Crear Cita");
            System.out.println("2. Modificar Cita");
            System.out.println("3. Eliminar Cita");
            System.out.println("4. Mostrar Citas");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.next();
            sc.nextLine();
            //Limpia el buffer de las entradas
    
            switch (opcion) {
                case "1", "registrar" ->  registrarCita(); 
                case "2", "modificar" ->  modificarCita(); 
                case "3", "eliminar" ->  eliminarCita(); 
                case "4", "mostrar" ->  mostrarCitas(); 
                case "0" -> { System.out.println("Volviendo al menu anterior. ");} //Se muestra cuando se introduce 0
                default -> {
                    System.out.println("ERR0R: No se reconoció esa opción");
                }
            }
        //Se ejecuta mientras no se introduzca 0
        } while (!opcion.equalsIgnoreCase("0"));
    }

    public void registrarCita() {
        //Atributos para la insercion de datos
        String fecha;
        String hora;
        String matricula;
        Cita cita;
                
       //TODO: Añadir validaciones al asignar cita
        
        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        fecha = sc.nextLine();
        System.out.print("Ingrese la hora (HH:MM): ");
        hora = sc.nextLine();
        System.out.print("Ingrese la matrícula del vehículo: ");
        matricula = sc.nextLine();
                
        cita = new Cita(fecha, hora, matricula);
        CitaDAO citaDAO = new CitaDAO();
        //Inserta en la base de datos los datos recogidos
        citaDAO.insertar(cita);
    }

    public void modificarCita() {

        String opcion;
        String columna;
        String valor;
        String matricula;

        Cita cita_modificar;
        do {
            System.out.println("Qué desea modificar?");
            System.out.println("1. Fecha");
            System.out.println("2. Hora");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.nextLine();
            
            //Si se introduce el 0 sale del menu y no se ejecuta el resto del codigo
            if(opcion.equalsIgnoreCase("0")) {
                break;
            }

            
            System.out.println("Ingrese la matricula del coche para modificar la cita: ");
            System.out.print("--> ");
            matricula = sc.nextLine();
            System.out.print("Ingrese la fecha antigua: ");
            String fecha = sc.next();
            sc.nextLine(); //Limpia el buffer de entrada de datos

            CitaDAO citaDAO = new CitaDAO();
            //Obtiene el objeto cita
            cita_modificar = citaDAO.buscarMostrar(matricula,fecha);

            //En caso de que el objeto sea nulo es que no se ha encintrado en la BD
            if (cita_modificar == null) {
                System.out.println("ERR0R: No se encontró esa matrícula");
            } else {
                switch (opcion) {
                    
                    case "1", "fecha" -> {
                        
                        System.out.print("Ingrese la nueva fecha: ");
                        String fecha_nueva = sc.next();
                        sc.nextLine();
                        valor = fecha_nueva;
                        columna = "fecha"; //Es el nombre de la columna en la DB
                        //Actualiza la cita
                        boolean actualizado = citaDAO.actualizar(columna, matricula, valor, fecha);
                        
                        //Salidas segun si se ha conseguido modificar o no
                        if (actualizado) {
                        System.out.println("Fecha actualizada correctamente.");
                        } else {
                        System.out.println("Error al actualizar la fecha.");
                        }
                    }
                    case "2", "hora" -> {
                        
                        System.out.print("Ingrese la nueva hora: ");
                        String hora_nueva = sc.next();
                        sc.nextLine();
                        columna = "hora";
                        valor = hora_nueva;
                        boolean actualizado = citaDAO.actualizar(columna, matricula, valor, fecha);
                        if (actualizado) {
                            System.out.println("Hora actualizada correctamente.");
                        } else {
                            System.out.println("Error al actualizar la hora.");
                        }
                    }
                    //En caso de que la entrada sea 0 vuelve al menu anterior
                    case "0" -> { System.out.println("Volviendo al menu anterior. ");}
                    default -> {
                        System.out.println("ERR0R: No se reconoció esa opción");
                    }
                }
            }
        } while (!opcion.equalsIgnoreCase("0"));
    }
    //MEtodo de eliminar una cita
    public void eliminarCita() {
        String matricula;
        Cita cita;
        System.out.print("Ingrese la matricula de la cita que quiere eliminar: ");
        matricula = sc.nextLine();
        System.out.print("Ingrese la fecha de la cita: ");
        String fecha = sc.next();
        sc.nextLine();
        //Limpia el buffer de entrada de datos

        CitaDAO citaDAO = new CitaDAO();
        //Obtiene el objeto cita
        cita = citaDAO.buscarMostrar(matricula, fecha);

        if (cita == null) {
            System.out.println("ERR0R: No se encontró una cita con esa matricula");
        } else {
            System.out.println("Está por eliminar la siguiente cita: ");
            System.out.println("Matricula del coche" + cita.getVehiculoMatricula());
            System.out.println("Fecha y hora: " + cita.getFecha() + " " + cita.getHora());
            System.out.println("---¿Está seguro?---");
            String opcion;
            //Atributo de seguir al siguiente menu o no
            boolean seguir = true;
            do { 
                System.out.println("1. SI / 2. NO");
                opcion = sc.next();
                sc.nextLine();
                switch (opcion) {
                    case "1", "si", "SI" -> {
                        
                        if (citaDAO.buscar(matricula).equals(matricula)) {
                            citaDAO.eliminar(matricula, fecha);
                            System.out.println("Cita eliminada");
                            seguir = false;
                            
                        } else {
                            System.out.println("No se encuentra la cita");
                        }
                    }
                    case "2", "no", "NO" -> {
                        System.out.println("Abortando...");
                        seguir = false;
                    }
                
                    default -> { System.out.println("No se reconoció esa opción."); }
                }
            } while (seguir);
        }
    }
    //Metodo para mostrar todas las citas
    public void mostrarCitas() {
        CitaDAO citaDAO = new CitaDAO();
        array_citas = citaDAO.obtenerTodos(); // Llenar la lista con los clientes de la BD

        if (array_citas == null) {
            array_citas = new ArrayList<>(); // Evitar que sea null en caso de error
        }

        if (array_citas.isEmpty()) {
            System.out.println("No hay citas registradas");
        } else {
            System.out.println("Citas: ");
            for (Cita cita : array_citas) {
                System.out.println(cita); // imprime con el`toString()
            }
        }
    }
}
