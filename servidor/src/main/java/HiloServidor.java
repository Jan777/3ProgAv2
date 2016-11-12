package servidor;

import java.net.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.io.*;

import datos.Datos;
import datos.DatosJugador;

public class HiloServidor extends Thread{

    private Socket socket;
    ObjectOutputStream outStream;
	 ObjectInputStream inStream;
	 private Datos d;

	 
private int num;
	 
    public HiloServidor(Socket socket, ServerLogica sl, int jugadores) {

        super("ThreadServer");
        this.socket = socket;
        
        d=new Datos();
        
       // d.clone(sl.getDatos());
        num=jugadores;
        d=sl.getDatos();///pasa la referencia
 
    }

    @Override
    public void run() {

    	
		try {
			outStream = new ObjectOutputStream(socket.getOutputStream());
			inStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}// = new ObjectOutputStream(socketToServer.getOutputStream());
       

        try {
			outStream.writeObject(num);//pasa el num cliente
			outStream.flush();
			String name=(String) inStream.readObject();
			d.setNames(name);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        DatosJugador dj = null;
		try {
			dj = (DatosJugador) inStream.readObject();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        try {
			do {
			    if (true) {//aux != null
			        //System.out.println(aux);
			    	
			           // Socket cliente = i.next();
			            try {
			            	
			     			int cantidadJugadores=2;
							for (int i = 0; i < cantidadJugadores; i++) {
								
								if(dj.getNumJug()==i+1)//testeando datos del primer jugador
								{
									d.getJ(i).setDisparando(dj.isDisparando());
									d.getJ(i).setMoveD(dj.isMoveD());
									d.getJ(i).setMoveR(dj.isMoveR());
									d.getJ(i).setMoveL(dj.isMoveL());
									d.getJ(i).setMoveU(dj.isMoveU());
								
								}
								
							}
			     			
			     			synchronized (socket) {
			     				outStream.flush(); 
				            	outStream.writeObject(d);//java.util.ConcurrentModificationException//writeUnshared(); escribe una nueva instancia del objeto,pero no del todo..
				            	outStream.flush(); 
			     				outStream.reset();
							}
			     				

			            } catch (Exception e) {//IO
			                e.printStackTrace();
			            }
			   
			       
			    }
			    // indico que el flujo de informacion provenga del usuario de
			    // este hilo.
			  
			  //  dataIn = new DataInputStream(socket.getInputStream());
			   // Thread.sleep(1000);
			    
			} while ((dj = (DatosJugador) inStream.readObject() )!= null );
		} catch (ClassNotFoundException e) {
			System.out.println("Class");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO");
			e.printStackTrace();
		}//(aux = inStream.readLine()) != null 
    }

}
