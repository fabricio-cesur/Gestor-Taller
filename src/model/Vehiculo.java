package model;
public class Vehiculo {
    
    public String matricula;
    public String modelo;
    public String marca;
    public int año;
    public Cliente propietario;

    public Vehiculo(String matricula, String modelo, String marca, int año, Cliente propietario) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.marca = marca;
        this.año = año;
        this.propietario = propietario;
    }

    public String getMatricula() { return this.matricula; }
    public String getModelo() { return this.modelo; }
    public String getMarca() { return this.marca; }
    public int getAño() { return this.año; }
    public Cliente getPropietario() { return this.propietario; }
    public String getPropietarioNombreApellido() { return this.getPropietario().getNombre() + " " + this.getPropietario().getApellido(); }

    public void setMatricula(String matricula) { this.matricula = matricula; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setAño(int año) { this.año = año; }
    public void setPropietario(Cliente propietario) { this.propietario = propietario; }

    @Override
    public String toString() { 
        return this.getMatricula() + ": " 
        + this.getMarca() + " " + this.getModelo() + " " + this.getAño() 
        + " de: " + this.getPropietarioNombreApellido();
    }
}
