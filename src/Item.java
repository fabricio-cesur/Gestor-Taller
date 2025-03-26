public class Item {
    
    public String codigo;
    public String nombre;
    public Proveedor proveedor;
    public String posicion; // No lo veo Necesario
    public int minimo;
    public boolean restock;

    public Item(String codigo, String nombre, Proveedor proveedor, String posicion, int minimo, boolean restock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.posicion = posicion;
        this.minimo = minimo;
        this.restock = restock;
    }

    public String getCodigo() { return this.codigo; }
    public String getNombre() { return this.nombre; }
    public Proveedor getProveedor() { return this.proveedor; }
    public String getPosicion() { return this.posicion; }
    public int getMinimo() { return this.minimo; }
    public boolean getRestock() { return this.restock; }

}
