

import java.sql.Connection;
public class Menu {
    
    
    
    public static void main(String[] args) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            System.out.println("Conexion establecida correctamente.");
        } else{
            System.out.println("No se pudo establecer la conexión");
        }
    }
}