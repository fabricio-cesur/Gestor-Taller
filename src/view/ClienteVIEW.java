package view;

import java.util.Scanner;

public class ClienteVIEW {

    Scanner sc = new Scanner(System.in);
    
    public String[] modificarClienteDAO() {
        
        System.out.print("¿Qué desea modificar? ");
        String columna = sc.next();
        System.out.print("Ingrese el nuevo dato: ");
        String valor = sc.next();
        System.out.print("Ingrese el dni: ");
        String dni = sc.next();

        return new String[]{columna, valor, dni};
    }

    
}