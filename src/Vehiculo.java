public class Vehiculo {
    
    public String matricula;
    public String modelo;
    public String marca;
    public int año;
    // public Cliente propietario;

    public Vehiculo(String mat, String mod, String marc, int año /*, Cliente pro*/) {
        this.matricula = mat;
        this.modelo = mod;
        this.marca = marc;
        this.año = año;
        //this.propietario = pro
    }

    public String getMatricula() { return this.matricula; }
    public String getModelo() { return this.modelo; }
    public String getMarca() { return this.marca; }
    public int getAño() { return this.año; }
    // public Cliente getPropietario() { return this.propietario; }

}
