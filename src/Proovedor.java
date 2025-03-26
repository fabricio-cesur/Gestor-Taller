import java.util.ArrayList;

public class Proovedor {
    public int id;
    public String nombre;
    public String direccion;
    public int cuenta;
    public ArrayList<Item> items;

    public Proovedor(int id, String nom, String dir, int cue, ArrayList<Item> ite) {
        this.id = id;
        this.nombre = nom;
        this.direccion = dir;
        this.cuenta = cue;
        this.items = ite;
    }

    public int getId() { return this.id; }
    public String getNombre() { return this.nombre; }
    public String getDireccion() { return this.direccion; }
    public int getCuenta() { return this.cuenta; }
    public ArrayList<Item> getItems() { return this.items; }

}
