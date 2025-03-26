public class Item {
    
    public String codigo;
    public String nombre;
    // public Proovedor proovedor;
    // public String posicion; No lo veo Necesario
    public int minimo;
    public boolean restock;

    public Item(String cod, String nom, /*Proovedor pro, String pos,*/ int min, boolean res) {
        this.codigo = cod;
        this.nombre = nom;
        // this.proovedor = pro;
        // this.posicion = pos; No lo veo Necesario
        this.minimo = min;
        this.restock = res;
    }

    public String getCodigo() { return this.codigo; }
    public String getNombre() { return this.nombre; }
    // public Proovedor getProovedor() { return this.proovedor; }
    // public String getPosicion() { return this.posicion; } No lo veo Necesario
    public int getMinimo() { return this.minimo; }
    public boolean getRestock() { return this.restock; }

}
