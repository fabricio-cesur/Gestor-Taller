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

    public boolean validarTelefono(String telefono) {
        if (telefono.length() != 9) {
            System.out.println("El número de teléfono debe tener 9 dígitos");
            return false;
        } else {
            for (int i = 0; i < telefono.length(); i++) {
                if (Character.isAlphabetic(telefono.charAt(i))) {
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
