package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.CitaDAO;
import model.Cita;
import model.Encargo;
import model.Vehiculo;

public class CitasVIEW {
    public ArrayList<Cita> array_citas = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public void asignarCita() {
        int id = array_citas.size() + 1;
        String fecha;
        String hora;
        String matricula_coche;
        String id_encargo;
        
       //TODO: Añadir validaciones al asignar cita
        Cita cita;
        System.out.print("Ingrese la fecha: ");
        fecha = sc.nextLine();
        System.out.print("Ingrese la hora: ");
        hora = sc.nextLine();
        System.out.print("Ingrese la matrícula del vehículo: ");
        matricula = sc.nextLine();
        for (Vehiculo vehi : array_vehiculos) {
            if (vehi.getMatricula().equals(matricula)) {
                vehiculo = vehi;
            }
        }
        System.out.print("Ingrese el id del encargo: ");
        id_encargo = sc.nextInt();
        for (Encargo enca : array_encargos) {
            if (enca.getId() == id_encargo) {
                encargo = enca;
            }
        }
        cita = new Cita(id, fecha, hora, vehiculo, encargo);
        array_citas.add(cita);
    }
    public void modificarCita() {
        String opcion;
        int id;

        Cita cita = null;
        do {
            System.out.println("Qué desea modificar?");
            System.out.println("1. Fecha");
            System.out.println("2. Hora");
            System.out.println("3. Vehiculo");
            System.out.println("4. Encargo");
            System.out.println("0. Atrás");
            System.out.print(">>> ");
            opcion = sc.nextLine();
            //TODO: Validar que el DNI exista
            System.out.println("Ingrese el ID de la cita a modificar: ");
            System.out.print("--> ");
            id = sc.nextInt();
            for (Cita ci : array_citas) {
                if (id == ci.getId()) {
                    cita = ci;
                    break;
                }
            }
            if (cita == null) {
                System.out.println("ERR0R: No se encontró esa matrícula");
            } else {
                switch (opcion) {
                    //TODO: Añadir validaciones al modificar la cita
                    case "1", "fecha" -> {
                        System.out.print("Ingrese la nueva fecha: ");
                        String fecha_nueva = sc.next();
                        cita.setFecha(fecha_nueva);
                    }
                    case "2", "hora" -> {
                        System.out.print("Ingrese la nueva hora: ");
                        String hora_nueva = sc.next();
                        cita.setHora(hora_nueva);
                    }
                    case "3", "vehiculo" -> {
                        System.out.print("Ingrese la matrícula del nuevo vehiculo: ");
                        String matricula_nueva = sc.next();
                        Vehiculo vehiculo = null;
                        for (Vehiculo vehi : array_vehiculos) {
                            if (vehi.getMatricula().equals(matricula_nueva)) { vehiculo = vehi; break; }
                        }
                        if (vehiculo == null) {
                            System.out.println("ERR0R: Vehiculo no encontrado");
                        } else {
                            cita.setVehiculo(vehiculo);
                        }
                    }
                    case "4", "encargo" -> {
                        System.out.print("Ingrese el id del nuevo encargo: ");
                        int id_encargo = sc.nextInt();
                        Encargo encargo = null;
                        for (Encargo enc : array_encargos) {
                            if (enc.getId() == id_encargo) { encargo = enc; break; }
                        }
                        if (encargo == null) {
                            System.out.println("ERR0R: Encargo no encontrado");
                        } else {
                            cita.setEncargo(encargo);
                            System.out.println("Encargo cambiado.");
                        }
                    }
                    default -> {
                        System.out.println("ERR0R: No se reconoció esa opción");
                    }
                }
            }
        } while (!opcion.equalsIgnoreCase("0"));
    }
    public void eliminarCita() {
        int id;
        Cita cita = null;
        System.out.print("Ingrese el id de la cita que quiere eliminar: ");
        id = sc.nextInt();
        for (Cita ci : array_citas) {
            if (ci.getId() == id) { cita = ci; break; }
        }
        if (cita == null) {
            System.out.println("ERR0R: No se encontró una cita con ese id");
        } else {
            System.out.println("Está por eliminar la siguiente cita: ");
            System.out.println("Cita Nº" + cita.getId());
            System.out.println("Fecha y hora: " + cita.getFecha() + " " + cita.getHora());
            System.out.println("Vehículo: " + cita.getVehiculo().getMarca() + cita.getVehiculo().getModelo());
            System.out.println("Encargo: " + cita.getEncargo().getServicio());
            System.out.println("---¿Está seguro?---");
            String opcion;
            do { 
                System.out.println("1. SI / 2. NO");
                opcion = sc.next();
                switch (opcion) {
                    case "1", "si", "SI" -> {
                        for (int i = 0; i < array_citas.size(); i++) {
                            if (array_citas.get(i).getId() == cita.getId()) {
                                array_citas.remove(i);
                                System.out.println("Cliente eliminado");
                                break;
                            }
                        }
                    }
                    case "2", "no", "NO" -> {
                        System.out.println("Abortando...");
                    }
                
                    default -> { System.out.println("No se reconoció esa opción."); }
                }
            } while (!opcion.equals("2"));
        }
    }
    public void mostrarCitas() {
        if (array_citas.isEmpty()) {
            System.out.println("No hay citas registradas");
        } else {
            System.out.println("Citas: ");
            for (Cita cita : array_citas) {
                System.out.println(cita.toString());
            }
        }
    }
}
