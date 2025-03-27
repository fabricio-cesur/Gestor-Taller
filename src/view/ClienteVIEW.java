package view;

import java.util.Scanner;

public class ClienteVIEW {

    Scanner sc = new Scanner(System.in);

    String dni;
    String columna;
    String valor;
    
    public void modificarClienteDAO() {
        

        System.out.print("¿Qué desea modificar? ");
        columna = sc.next();
        System.out.print("Ingrese el nuevo dato: ");
        valor = sc.next();
        System.out.print("Ingrese el dni: ");
        dni = sc.next();
    }

    public String getDni() { return this.dni; }
    public String getColumna() { return this.columna; }
    public String getValor() { return this.valor; }
}