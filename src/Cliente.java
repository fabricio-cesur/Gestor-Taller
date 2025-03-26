public class Cliente {
    public int id;
    public String nombre;
    public String apellido;
    public String direccion;
    public int telefono;
    public int cuenta_bancaria;
    

    public Cliente(int id, String nombre, String apellido, String direccion, int telefono, int cuenta_bancaria){

        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuenta_bancaria = cuenta_bancaria;
    }
}
