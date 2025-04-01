package view;

public class Validacion {
    
    public boolean validarDNI(String dni) {
        if (dni.length() != 9) {
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
}
