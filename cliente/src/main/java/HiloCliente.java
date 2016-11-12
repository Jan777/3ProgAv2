package cliente;



import java.net.Socket;
import java.io.*;

import datos.Datos;

public class HiloCliente extends Thread{

    private Socket socket;
    private VentanaGame m;
    ObjectInputStream inputStream;
    ObjectOutputStream  outStream;
    private String uname;
    
    public HiloCliente(Socket socket, VentanaGame player, String username) throws IOException {
        super("HiloCliente");
        this.socket = socket;
        uname=username;
        m =player;
        inputStream = new ObjectInputStream(socket.getInputStream());
        outStream = new ObjectOutputStream(socket.getOutputStream());
    }
    
   
    @Override
	public void run() {
      
    	 try {
			int num =(Integer) inputStream.readObject();//obtiene el numero de cliente del serv
			m.getDj().setNumJug(num);
			System.out.println("Client _Start num: "+num);
			outStream.writeObject(uname);
			outStream.flush();
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
    	
        while (true) {
            try {

           	 try {
    				Thread.sleep(24);
    				
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            	
            	outStream.writeObject(m.getDj());
            	outStream.flush();
            	
                Datos o =(Datos) inputStream.readObject();
              //  System.out.println("Client: "+m.getDj().isMoveR());
                m.setD(o);
                
              
               
                outStream.reset();
            } catch (Exception e) {
                e.printStackTrace();

            } 
           
            
        }
		
    }

}
