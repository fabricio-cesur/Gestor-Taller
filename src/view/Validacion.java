package view;

import java.util.ArrayList;
import model.*;
import dao.*;

public class Validacion {
    Formateo formatear = new Formateo();
    VehiculoDAO vehiculoDAO = new VehiculoDAO();
    ServicioDAO servicioDAO = new ServicioDAO();
    EncargoDAO encargoDAO = new EncargoDAO();
    
    public boolean validarId(String id, String clase, boolean buscar) {
        if (id == null) {
            System.out.println("El ID no puede estar vacío");
            return false;
        }
        int num_id;
        try {
            num_id = Integer.parseInt(id);
            if (num_id < 0) {
                System.out.println("El ID es menor a 0");
                return false;
            }
        } catch (NumberFormatException e) {
            System.err.println("El ID no es un número entero");
            return false;
        }
        if (buscar) {
            switch (clase) {
                case "encargo" -> {
                    if (encargoDAO.obtenerPorID(num_id) == null) {
                        return false;
                    } else {
                        return true;
                    }
                }
                case "servicio" -> {
                    if (servicioDAO.buscar(id) == null) {
                        System.out.println("No existe un servicio con este ID");
                        return false;
                    }
                    return true;
                }
                default -> { return true; }
            }
        }
        return true;
    }
    public boolean validarIdAuxiliar(String id, int id_principal, String clase) {
        int num_id;
        if (id == null) {
            System.out.println("El ID no puede estar vacío");
            return false;
        }
        try {
            num_id = Integer.parseInt(id);
            if (num_id < 0) {
                System.out.println("El ID es menor a 0");
                return false;
            }
        } catch (NumberFormatException e) {
            System.err.println("El ID no es un número entero");
            return false;
        }
        switch (clase) {
            case "servicio_encargo" -> {
                ArrayList<Servicio> servicios_encargo = encargoDAO.obtenerServicios(id_principal);
                for (Servicio servicio : servicios_encargo) {
                    if (num_id == servicio.getId()) {
                        return true;
                    }
                }
                System.out.println("Ese ID no pertene a un servicio de ese encargo");
                return false;
            }
            default -> { return true; }
        }
    }
    public boolean validarDNI(String dni) {
        if (dni == null) {
            System.out.println("El DNI no puede estar vacío");
            return false;
        } else if (dni.length() != 9) {
            System.out.println("El DNI debe tener 9 carácteres");
            return false;
        } else if (Character.isDigit(dni.charAt(8))) {
            System.out.println("El último carácter debe ser la letra");
            return false;
        } else if (dni.contains("I") || dni.contains("Ñ") || dni.contains("O") || dni.contains("U")) {
            System.out.println("El DNI no puede tener la letra O, U, Ñ o I");
            return false;
        } else {
            for (int i = 0; i < dni.length(); i++) {
                if (!Character.isDigit(dni.charAt(i))) {
                    System.out.println("Los primeros 8 carácteres deben ser números");
                    return false;
                }
            }
            return true;
        }
    }
    public boolean validarMatricula(String matricula, boolean buscar) {
        //Se asegura de estar todo bien escrito, para ello la entrada se debió formatear con anterioridad
        if (matricula == null) {
            System.out.println("La matrícula ingresada está vacía");
            return false;
        }
        if (matricula.length() != 7) {
            System.out.println("La matrícula " + matricula + " debe tener 7 carácteres");
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (Character.isAlphabetic(matricula.charAt(i))) {
                System.out.println("Los primeros 4 carácteres deben ser numéricos");
                return false;
            }
        }
        for (int i = 4; i < 7; i++) {
            if (!Character.isAlphabetic(matricula.charAt(i))) {
                System.out.println("Los últimos 3 carácteres deben ser letras");
                return false;
            }
        }
        //TODO: Obtener matriculas de prueba realistas y acorde al estándar europeo
        /*Evitamos esta validación porque las matrículas de prueba tienen vocales*/
        // char[] letras_excluidas = {'I', 'O', 'U', 'Ñ'};
        // for (char letra : letras_excluidas) {
        //     if (matricula.contains(String.valueOf(letra))) {
        //         System.out.println("El DNI no puede tener la letra O, U, Ñ o I");
        //         return false;
        //     }
        // }
        if (buscar) {
            if (vehiculoDAO.buscar(matricula) == null) {
                System.out.println("No se encontró la matrícula " + matricula + " en la base de datos");
                return false;
            }
        }
        return true;
    }
    public boolean validarTelefono(String matricula) {
        if (matricula.length() != 9) {
            System.out.println("El número de teléfono debe tener 9 dígitos");
            return false;
        } else {
            for (int i = 0; i < matricula.length(); i++) {
                if (Character.isAlphabetic(matricula.charAt(i))) {
                    System.out.println("El número de teléfono sólo debe tener números");
                    return false;
                }
            }
            return true;
        }
    }
    public boolean validarIBAN(String iban) {
        //Formato de cuenta bancaria ESXX XXXX XXXX XXXX XXXX
        //Revisa que tenga o 24 carácteres de los dígitos sin espacio
        //o 29 de los 24 carácters con espacio entre cada 4
        if (iban.length() != 24 || iban.length() != 29) {
            System.out.println("La cuenta bancaria debe tener 24 carácteres");
            return false;
        } else { return true; }
        //TODO: Validar los demás parámetros del formato
    }
}
