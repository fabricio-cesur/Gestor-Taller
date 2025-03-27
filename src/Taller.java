import java.util.ArrayList;
import java.util.Scanner;

public class Taller {
    Scanner sc = new Scanner(System.in);
    
    public ArrayList<Cliente> array_clientes = new ArrayList<>();
    public ArrayList<Vehiculo> array_vehiculos = new ArrayList<>();
    public ArrayList<Cita> array_citas = new ArrayList<>();
    public ArrayList<Encargo> array_encargos = new ArrayList<>();
    public ArrayList<Empleado> array_empleados = new ArrayList<>();
    public ArrayList<Servicio> array_servicios = new ArrayList<>();
    public ArrayList<Item> array_items = new ArrayList<>();
    public ArrayList<Pedido> array_pedido = new ArrayList<>();
    public ArrayList<Proveedor> array_proveedores = new ArrayList<>();

    public void registrarCliente() {}
    public void modificarCliente() {}
    public void eliminarCliente() {}
    public void mostrarClientes() {}


    public void registrarVehiculo() {
        String matricula;
        String modelo;
        String marca;
        int año;
        String dni_propietario;
        Cliente propietario = null;

        Vehiculo vehiculo;
        System.out.print("Ingrese la matrícula: ");
        matricula = sc.next();
        System.out.print("Ingrese el modelo: ");
        modelo = sc.nextLine();
        System.out.print("Ingrese la marca: ");
        marca = sc.nextLine();
        System.out.print("Ingrese el año: ");
        año = sc.nextInt();
        System.out.print("Ingrese el dni del propietario: ");
        dni_propietario = sc.next();

        for (Cliente cliente : array_clientes) {
            if (dni_propietario.equals(cliente.getDni())) {
                propietario = cliente;
                break;
            }
        }
        vehiculo = new Vehiculo(matricula, modelo, marca, año, propietario);
        array_vehiculos.add(vehiculo);
    }
    public void modificarVehiculo() {}
    public void eliminarVehiculo() {}
    public void mostrarVehiculos() {}


    public void asignarCita() {}
    public void modificarCita() {}
    public void eliminarCita() {}
    public void mostrarCitas() {}


    public void registrarEncargo() {}
    public void modificarEncargo() {}
    public void finalizarEncargo() {}
    public void eliminarEncargo() {}
    public void mostrarEncargos() {}


    public void registrarEmpleado() {}
    public void modificarEmpleado() {}
    public void eliminarEmpleado() {}
    public void mostrarServiciosEmpleado() {}
    public void revisarDisponibilidadEmpleado() {}
    public void mostrarEmpleados() {}


    public void asignarEmpleados() {}
    public void modificarAsignacion() {}
    public void eliminarAsignacion() {}
    public void mostrarAsignaciones() {}


    public void registrarServicio() {}
    public void modificarServicio() {}
    public void eliminarServicio() {}
    public void mostrarServicios() {}


    public void registrarItem() {}
    public void modificarItem() {}
    public void eliminarItem() {}
    public void mostrarItems() {}


    public void registrarPedido() {}
    public void modificarPedido() {}
    public void eliminarPedido() {}
    public void mostrarPedidos() {}


    public void registrarProveedor() {}
    public void modificarProveedor() {}
    public void eliminarProveedor() {}
    public void mostrarProveedores() {}


}
