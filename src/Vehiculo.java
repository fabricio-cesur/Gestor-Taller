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

    public void setMatricula(String matricula) { this.matricula = matricula; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setAño(int año) { this.año = año; }
    public void setPropietario(Cliente propietario) { this.propietario = propietario; }

}
