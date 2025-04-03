package model;

import dao.PedidoDAO;

public class Item {
    
    public String nombre;
    public int id_proveedor;
    public int minimo;
    public double precio;
    public int cantidad;
    public boolean restock;
    private final int REPOSICION = 10;

    public Item(String nombre, int id_proveedor, int minimo, int cantidad, double precio) {
        
        this.nombre = nombre;
        this.id_proveedor = id_proveedor;
        this.precio = precio;
        this.minimo = minimo;
        this.cantidad = cantidad;
        this.restock = (cantidad <= minimo);
    }

    
    
    public String getNombre() { return this.nombre; }
    public int getIdProveedor() { return this.id_proveedor; }
    public double getPrecio() { return this.precio; }
    public int getMinimo() { return this.minimo; }
    public int getCantidad() { return this.cantidad; }
    public boolean isRestock() { return this.restock; }

    public void setCantidad(int nuevaCantidad) {
        this.cantidad = nuevaCantidad;
        this.restock = (this.cantidad <= this.minimo);
        verificarStock();
    }

    // Método para verificar si se necesita reabastecimiento
    private void verificarStock() {
        if (this.restock) {
            generarPedido();
        }
    }

    //REVISAR!!!!!!!!
    private void generarPedido() {
        Pedido pedido = null;
        PedidoDAO pedidoDAO = new PedidoDAO();
       // pedidoDAO.insertarPedido(pedido);
        
    }

    // Simulación de llegada de stock y actualización automática de cantidad
    private void recibirStock() {
        int nuevaCantidad = this.cantidad + this.REPOSICION;
        System.out.println(" Pedido recibido para " + this.nombre + ". Nueva cantidad: " + nuevaCantidad);
        this.setCantidad(nuevaCantidad);
    }


    @Override
    public String toString() { 
        return "Nombre: " + this.getNombre() + ", Id del proveedor: " + this.getIdProveedor() 
        + ", precio: " + this.getPrecio() + ", cantidad mínima: " + this.getMinimo()
        + ", cantidad: " + this.getCantidad();
    }
}
