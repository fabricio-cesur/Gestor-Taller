import java.util.ArrayList;

public class Proveedor {
    public int id;
    public String nombre;
    public String direccion;
    public int cuenta;
    public ArrayList<Item> items;

    public Proveedor(int id, String nombre, String direccion, int cuenta, ArrayList<Item> items) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cuenta = cuenta;
        this.items = items;
    }

    public int getId() { return this.id; }
    public String getNombre() { return this.nombre; }
    public String getDireccion() { return this.direccion; }
    public int getCuenta() { return this.cuenta; }
    public ArrayList<Item> getItems() { return this.items; }

}
