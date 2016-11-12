package servidor;

import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.io.*;

public class Servidor {

	private String data;
    private static ServerSocket servidor;
    
    private Socket cliente;

    public static int cantActualClientes;

    private Collection<Socket> coleccion;    

    private int puerto;

    private String nombreHost;

    public String getNombreHost() {
        return nombreHost;
    }

    public int getPuerto() {
        return puerto;
    }

    public Servidor(int port) {

        // Nombre del Servidor
        try {
            nombreHost = InetAddress.getLocalHost().getHostName().toString();  
            
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
        
    	coleccion = new ArrayList<Socket>();    	
        puerto = port;       
        cantActualClientes = 0;        
      
        try {
            servidor = new ServerSocket(puerto);

        } catch (IOException e) {
            System.out.println("No se puede escuchar desde el puerto elegido, cerrando Servidor...");
            System.exit(1);
        }
    }

    public Collection<Socket> getLista() {
        return coleccion;
    }

    public Socket aceptarConexion() {

       cantActualClientes++;

        try {
            cliente = servidor.accept();
            
         
        } catch (Exception e) {
        	
            System.out.println("\t\tError al aceptar conexiones\n\t\t Servidor Finalizado");
            System.exit(1);
        }
    	
        System.out.println("La Conexion NRO " + cantActualClientes + " fue aceptada correctamente.");
        coleccion.add(cliente);
        return cliente;
    }

    public static  void pararServidor() {
        try {
            servidor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}
