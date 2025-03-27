public class Cliente {
    public String dni;
    public String nombre;
    public String apellido;
    public String direccion;
    public int telefono;
    public int cuenta_bancaria;
    

    public Cliente(String dni, String nombre, String apellido, String direccion, int telefono, int cuenta_bancaria){
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuenta_bancaria = cuenta_bancaria;
    }

    public String getDni() { return this.dni; }
}
