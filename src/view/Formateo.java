package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Formateo {
    public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    //Formatear y devolver el mismo tipo o nulo
    public String matricula(String matricula) {
        matricula = quitarEspacios(matricula);
        matricula = quitarEspeciales(matricula);
        /*No quitar vocales porque las matrículas 
        de prueba de la base de datos tienen vocales*/
        // matricula = quitarVocales(matricula, "amb");
        matricula = matricula.toUpperCase();
        return matricula;
    }
    public String id(String id) {
        id = quitarEspacios(id);
        id = quitarEspecialesExcepto(id, ".,");
        id = quitarLetras(id, "amb");
        return id;
    }
    public String fecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            System.out.println("Fecha vacía");
            return null;
        }

        fecha = quitarEspacios(fecha);
        fecha = quitarEspecialesExcepto(fecha, "/-.");

        LocalDate fechaLocalDate = null;
        DateTimeFormatter[] formateos_fecha = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy"),
            DateTimeFormatter.ofPattern("MM.dd.yyyy")
        };
        
        for (DateTimeFormatter formateo_fecha : formateos_fecha) {
            try {
                fechaLocalDate = LocalDate.parse(fecha, formateo_fecha);
                break;
            } catch (DateTimeParseException e) {
                //Intenta con el siguiente formato
            }
        }

        if (fechaLocalDate == null) {
            System.out.println("formato de fecha inválido");
            return null;
        }

        DateTimeFormatter formato_mysql = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fechaLocalDate.format(formato_mysql);
    }
    public String hora(String hora) {
        if (hora == null || hora.trim().isEmpty()) {
            System.out.println("Hora vacía");
            return null;
        }

        hora = quitarEspacios(hora);
        hora = quitarEspecialesExcepto(hora, "/-.");

        LocalDate horaLocalDate = null;
        DateTimeFormatter[] formateos_hora = {
            DateTimeFormatter.ofPattern("HH:mm"),
            DateTimeFormatter.ofPattern("HH:mm:ss"),
            DateTimeFormatter.ofPattern("H:m"),
            DateTimeFormatter.ofPattern("HHmm"),
            DateTimeFormatter.ofPattern("HH-mm"),
            DateTimeFormatter.ofPattern("HH mm")
        };
        
        for (DateTimeFormatter formateo_hora : formateos_hora) {
            try {
                horaLocalDate = LocalDate.parse(hora, formateo_hora);
                break;
            } catch (DateTimeParseException e) {
                //Intenta con el siguiente formato
            }
        }

        if (horaLocalDate == null) {
            System.out.println("Formato de hora inválido");
            return null;
        }

        DateTimeFormatter formato_mysql = DateTimeFormatter.ofPattern("HH:mm");
        return horaLocalDate.format(formato_mysql);
    }
    
    //Convertir tipo
    public String dateToString(LocalDate date) { return date.format(dtf); }
    public LocalDate stringToDate(String string) { return LocalDate.parse(string, dtf); }

    //Modificar Strings
    public String quitarEspacios(String cadena) {
        return cadena.trim().replaceAll("\\s+", "");
    }
    public String quitarNumeros(String cadena) {
        return cadena.replaceAll("[0-9]", "");
    }
    public String quitarLetras(String cadena, String opcion) {
        opcion = opcion.toLowerCase();
        switch (opcion) {
            case "mayusculas", "may" -> {
                return cadena.replaceAll("[A-Z]", "");
            }
            case "minusculas", "min" -> {
                return cadena.replaceAll("[a-z]", "");
            }
            case "ambas", "amb" -> {
                return cadena.replaceAll("[a-zA-Z]", "");
            }
            default -> {
                System.out.println("ERR0R: No se reconoció el tipo de letra que quieres quitar al formatear");
                return cadena;
            }
        }
    }
    public String quitarVocales(String cadena, String opcion) {
        opcion = opcion.toLowerCase();
        switch (opcion) {
            case "mayusculas", "may" -> {
                return cadena.replaceAll("[AEIOU]", "");
            }
            case "minusculas", "min" -> {
                return cadena.replaceAll("[aeiou]", "");
            }
            case "ambas", "amb" -> {
                return cadena.replaceAll("(?i)[aeiou]", "");
            }
            default -> {
                System.out.println("ERR0R: No se reconoció el tipo de v que quieres quitar al formatear");
                return cadena;
            }
        }
    }
    public String quitarEspeciales(String cadena) {
        return cadena.replaceAll("[^a-zA-Z0-9]", "");
    }
    public String quitarEspecialesExcepto(String cadena, String excepciones) {
        String especiales = "a-zA-Z0-9"+ Pattern.quote(excepciones).replaceAll("[\\\\Q\\\\E]", ""); 
        return cadena.replaceAll("[^"+especiales+"]", "");
    }
}
