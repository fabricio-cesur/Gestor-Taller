package model;
public class Vehiculo {
    
    public String matricula;
    public String modelo;
    public String marca;
    public int año;
    public String dni_cliente;

    public Vehiculo(String matricula, String modelo, String marca, int año, String dni_cliente) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.marca = marca;
        this.año = año;
        this.dni_cliente = dni_cliente;
    }

    public String getMatricula() { return this.matricula; }
    public String getModelo() { return this.modelo; }
    public String getMarca() { return this.marca; }
    public int getAño() { return this.año; }
    public String getDniCliente() { return this.dni_cliente; }
   
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setAño(int año) { this.año = año; }
    public void setDniCliente(String dni_cliente) { this.dni_cliente = dni_cliente; }

    @Override
    public String toString() { 
        return this.getMatricula() + ": " 
        + this.getMarca() + " " + this.getModelo() + " " + this.getAño() 
        + " de: " + this.getDniCliente();
    }
}
