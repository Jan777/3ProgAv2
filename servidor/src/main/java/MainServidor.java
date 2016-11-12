package servidor;
import java.net.*;

public class MainServidor {	
	private int cantJugadores;
	private int puerto;
	private static boolean play = false;
	public MainServidor(String puerto, String jugadores)
	{this.cantJugadores=Integer.parseInt(jugadores);
		this.puerto=Integer.parseInt(puerto);					}
    public void correr() {
    	
        Socket socket = null;
        int jugadores=0;
        
        ServerLogica sl=new ServerLogica();
        
        Servidor servidor = null;
            

        System.out.println("SERVIDOR DEL JUEGO POR CONSOLA:\n-----------------------------");      
        servidor = new Servidor(this.puerto);  
        
        System.out.println("Nombre del Servidor:\t" + servidor.getNombreHost());
        System.out.println("Puerto de Escucha:\t" + this.puerto);
        System.out.println("\nServidor en escucha...");

         while (jugadores<this.cantJugadores) {
        	 jugadores++;
            socket = servidor.aceptarConexion();
            if (socket != null)
                new HiloServidor(socket,sl,jugadores).start();
        }       
         
        System.out.println("Comienza el juego");
        play=true;
        
        sl.startT();
        
        
    }
    

}
