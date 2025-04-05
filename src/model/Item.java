package model;

public class Item {
    //Atributos
    private String nombre;
    private int id_proveedor;
    private int minimo;
    private double precio;
    private int cantidad;
    private boolean restock;
    private final int REPOSICION = 10;

    //Constructor
    public Item(String nombre, int id_proveedor, int minimo, int cantidad, double precio) {
        
        this.nombre = nombre;
        this.id_proveedor = id_proveedor;
        this.precio = precio;
        this.minimo = minimo;
        this.cantidad = cantidad;
        this.restock = (cantidad <= minimo);
    }

    
    //Geter y seter
    public String getNombre() { return this.nombre; }
    public int getIdProveedor() { return this.id_proveedor; }
    public double getPrecio() { return this.precio; }
    public int getMinimo() { return this.minimo; }
    public int getCantidad() { return this.cantidad; }
    public boolean isRestock() { return this.restock; }

    public void setCantidad(int nuevaCantidad) {
        this.cantidad = nuevaCantidad;
        this.restock = (this.cantidad <= this.minimo);
        //verificarStock();
    }
/*
//Metodos para generear automaticamente un pedido segun el stock
    // Método para verificar si se necesita reabastecimiento
    private void verificarStock() {
        if (this.restock) {
            generarPedido();
        }
    }

    
    private void generarPedido() {
        Pedido pedido = null;
        PedidoDAO pedidoDAO = new PedidoDAO();
       // pedidoDAO.insertar(pedido);
        
    }

    // Simulación de llegada de stock y actualización automática de cantidad
    private void recibirStock() {
        int nuevaCantidad = this.cantidad + this.REPOSICION;
        System.out.println(" Pedido recibido para " + this.nombre + ". Nueva cantidad: " + nuevaCantidad);
        this.setCantidad(nuevaCantidad);
    }
*/

    //MEtodo que se utiliza a imprimir el objeto
    @Override
    public String toString() { 
        return "Nombre: " + this.getNombre() + ", Id del proveedor: " + this.getIdProveedor() 
        + ", precio: " + this.getPrecio() + ", cantidad mínima: " + this.getMinimo()
        + ", cantidad: " + this.getCantidad();
    }
}
