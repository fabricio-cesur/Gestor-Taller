package model;
public class Vehiculo {
    //Atributos
    public String matricula;
    public String modelo;
    public String marca;
    public String ano;
    public String dni_cliente;

    //Constructor
    public Vehiculo(String matricula, String modelo, String marca, String ano, String dni_cliente) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.dni_cliente = dni_cliente;
    }

    //Geter y seter
    public String getMatricula() { return this.matricula; }
    public String getModelo() { return this.modelo; }
    public String getMarca() { return this.marca; }
    public String getAno() { return this.ano; }
    public String getDniCliente() { return this.dni_cliente; }
   
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setAno(String ano) { this.ano = ano; }
    public void setDniCliente(String dni_cliente) { this.dni_cliente = dni_cliente; }

    //MEtodo que se utiliza a imprimir el objeto
    @Override
    public String toString() { 
        return "Matricula: " + this.getMatricula() + ", Marca: " + this.getMarca() + ", Modelo: " + this.getModelo() 
        + ", año: " + this.getAno() + ", DNI propietario " + this.getDniCliente();
    }
}
