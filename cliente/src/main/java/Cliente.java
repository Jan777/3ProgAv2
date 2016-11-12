package cliente;

import java.io.*;
import java.net.*;
import java.util.Calendar;

public class Cliente {

    private Socket cliente; // cliente es un objeto tipo socket
    private String nombre = null;
    private int puerto;
  //  private BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // lee del teclado
    private PrintStream ps ; // escritura 
    private String data;
      
    

	public String getNombre() {
		return nombre;
	}

	public int getPuerto() {
        return puerto;
    }

    public Cliente(String direccion, int port) {
    	
        try {
            puerto = port;
            cliente = new Socket(direccion, port);
            
            ps = new PrintStream(cliente.getOutputStream());
        } catch (IOException e) {
            System.out.println("\t\tNo se pudo conectar con el servidor\n\t\tPrograma Finalizado");
            System.exit(1);
        }
    }

    public Socket getSocket() {
        return cliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        
    }

    public void cerrarCliente() {
        try {
            cliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String horaDelMensaje() {
        Calendar cal = Calendar.getInstance();
        return +cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
    }

}
