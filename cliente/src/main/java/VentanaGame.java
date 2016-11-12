package cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import datos.Background;
import datos.Bala;
import datos.BalaEnm;
import datos.Datos;
import datos.DatosJugador;
import datos.Enemy;
import datos.Explode;
import datos.Jugador;


public class VentanaGame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Timer timer;
	private ArrayList<Jugador>j=new ArrayList<Jugador>();
	private ArrayList<Bala> aBala = new ArrayList<Bala>();//contenedor de balas
	private ArrayList<BalaEnm> aBalaEnm = new ArrayList<BalaEnm>();//contenedor de balasEnm
	private ArrayList<Enemy> aEnemy = new ArrayList<Enemy>();//contenedor de enemigos
	private ArrayList<Explode> aExplode = new ArrayList<Explode>();//contenedor de explosiones
	private Background bg1,bg2;
	//private int generarEnemigo;

	private int cantJugadores=2;
	private String ipname;
	
	//private int gameTime=0;
	//private int totalEnm=0;
	
	private Datos d;
	private DatosJugador dj;
	private Cliente cliente;
	private ActionListener menu=null;
	private String username;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaGame frame = new VentanaGame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @param cantJug 
	 * @param ip 
	 * @param username 
	 * @param MenudeJuego 
	 */
	public VentanaGame(int cantJug, String ip, String uname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setSize(900,600); 
		ipname=ip;
		username=uname;
		System.out.println(username);
		//((Window) menu).setVisible(false);
		cantJugadores=cantJug;
		for (int i = 0; i < cantJugadores; i++) {
			Jugador jug=new Jugador(i+1);
			j.add(jug);
		}

		bg1=new Background(0);
		bg2=new Background(-600);
		
		
		setBounds(580,0, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));//espacio sin dibujo n los bordes
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		 setResizable(false);
		  setTitle("1942"); 
	       
	       this.setFocusable(true);
	       this.addKeyListener(new Adapter());
	      // this.setDoubleBuffered(true); // Dibujo en memoria antes que en pantalla
	    
	    d=new Datos();
	    dj=new DatosJugador();
	    mainCliente();
	    
		
		timer = new Timer(24, this); // cada 5ms llama actionPerformed
	    timer.start();
	}
	 
	 
	  private class Adapter extends KeyAdapter{
	       @Override
	       public void keyPressed(KeyEvent e){
	           int key = e.getKeyCode();

	           if(key == KeyEvent.VK_UP)
	        	   dj.setMoveU(true);//j1.setMoveU(true);
	           if(key == KeyEvent.VK_DOWN)
	        	   dj.setMoveD(true);
	           if(key == KeyEvent.VK_RIGHT)
	              dj.setMoveR(true);
	           if(key == KeyEvent.VK_LEFT)
	        	   dj.setMoveL(true);
	           if(key == KeyEvent.VK_SPACE && j.get(0).getColdDown()==0){
	        	  dj.setDisparando(true);

	           }
	           
	       }
	       
	       @Override
	       public void keyReleased(KeyEvent e){
	           int key = e.getKeyCode();

	           if(key == KeyEvent.VK_UP)
	        	   dj.setMoveU(false);
	           if(key == KeyEvent.VK_DOWN)
	        	   dj.setMoveD(false);
	           if(key == KeyEvent.VK_RIGHT)
	        	   dj.setMoveR(false);
	           if(key == KeyEvent.VK_LEFT)
	        	   dj.setMoveL(false);
	           if(key == KeyEvent.VK_SPACE)
		        	  dj.setDisparando(false);
		   
	       }
	
	   }

		public void actionPerformed(ActionEvent e) {// Se ejecuta cada X ms
			
//			System.out.println("time Main");
//			//d.clone(s.getDatos());

			aBala= (ArrayList<Bala>)(d.getaBala()).clone();
			aBalaEnm= (ArrayList<BalaEnm>)(d.getaBalaEnm()).clone();
			aEnemy= (ArrayList<Enemy>)(d.getaEnemy()).clone();
			aExplode=(ArrayList<Explode>)(d.getaExplode()).clone();
			bg1.clone(d.getBg1());
			bg2.clone(d.getBg2());
			
			j=(ArrayList<Jugador>)(d.getJ()).clone();
		
		      repaint(); // "re-pintamos" el panel
	        
		}
		
		 public void paint (Graphics g)
		 { 
		        //super.paint(g);
			 
		        Graphics2D g2d = (Graphics2D)g;
		       
		        Toolkit t = Toolkit.getDefaultToolkit ();
		        bg1.setImg(t.getImage(bg1.getImgS()));
		        bg2.setImg(t.getImage(bg2.getImgS()));
		 
		     
		        g2d.drawImage (bg1.getImg(), 0, bg1.getY(), this);
		        g2d.drawImage (bg2.getImg(), 0, bg2.getY(), this);
		        
		        Font f= new Font("Dialog",Font.BOLD,25);
		        g2d.setFont(f);
		        g2d.setColor(Color.WHITE);
		        
		        for (int i = 0; i < cantJugadores; i++) {
		        	g2d.drawString(""+j.get(i).getScore(), 80+i*300, 80);
			        //g2d.drawString(""+score2, 600, 80);
		        }
		        
		        g2d.setColor(Color.RED);
		        
		        for (int i = 0; i < cantJugadores; i++) {
		        		
		        	if(j.get(i).getInvTime()>0)
		        		if(j.get(i).getVidas()>-1){
		        			g2d.drawString("x"+j.get(i).getVidas(), j.get(i).getX()+40, j.get(i).getY()+50);
		        		}  			
		        		else{
		        			g2d.drawString("Game", j.get(i).getX(), j.get(i).getY()+20);
		        			g2d.drawString("Over", j.get(i).getX(), j.get(i).getY()+50);
		        		}
		        }	  
		        int gmOver=0;
		        for (int i = 0; i < cantJugadores; i++) {
			        if(j.get(i).isGameOver()){
			        	gmOver++;
		        	}     
		        }
		        if(gmOver==cantJugadores)
		        {
		        	
		        	setVisible(false); //you can't see me!
	        		dispose(); //Destroy the JFrame object
		        }
		        
		        Font f2= new Font("Dialog",Font.BOLD,13);
		        g2d.setFont(f2);
		        g2d.setColor(Color.WHITE);
		        
		        for (int i = 0; i < cantJugadores; i++) {
		        	if(!j.get(i).isInv()){
		        		j.get(i).setImg(t.getImage(j.get(i).getImgS()));
			        	g2d.drawImage (j.get(i).getImg(), j.get(i).getX(), j.get(i).getY(), this);
			        	String name;
			        	if(d.getNames().size()>1){
			        		if(dj.getNumJug()==1)
				        		name=d.getNames(i);
				        	else
				        		name=d.getNames(i);
			        	}else
			        		name="jugador";
			        	
			        	
			        	g2d.drawString(name+i,  j.get(i).getX(),  j.get(i).getY()+60);
			        }
				}
		        

		        for (int i = 0; i < aBala.size(); i++) {
		        	aBala.get(i).setImg(t.getImage(aBala.get(i).getImgS()));
					g2d.drawImage (aBala.get(i).getImg(), aBala.get(i).getX(), aBala.get(i).getY(), this);
					
				}
		        for (int i = 0; i < aBalaEnm.size(); i++) {
		        	aBalaEnm.get(i).setImg(t.getImage(aBalaEnm.get(i).getImgS()));
					g2d.drawImage (aBalaEnm.get(i).getImg(), aBalaEnm.get(i).getX(), aBalaEnm.get(i).getY(), this);
					
				}
		       
		        
		        for (int i = 0; i < aEnemy.size(); i++) {
		        	aEnemy.get(i).setImg(t.getImage(aEnemy.get(i).getImgS()));
					g2d.drawImage (aEnemy.get(i).getImg(), aEnemy.get(i).getX(), aEnemy.get(i).getY(), this);
					
		        }
		        
		        for (int i = 0; i < aExplode.size(); i++) {
		        	if(!aExplode.get(i).isActive())
		        		aExplode.remove(i);
		        	else{
		        		aExplode.get(i).setImg(t.getImage(aExplode.get(i).getImgS()));
		        		g2d.drawImage (aExplode.get(i).getImg(), aExplode.get(i).getX(), aExplode.get(i).getY(), this);
		        	}
		        		
					
				}
		        
		        Toolkit.getDefaultToolkit().sync(); // fuerza sincronizacion, basicamente
			    g2d.dispose();
		 }
		 
		 
			public void mainCliente(){

			        String nombrehost = null;
			        int puerto = 0 ;
			        
			    	 //System.out.println("***\t\tBienvenido\t\t***\n");

			        	nombrehost = ipname;//"LOCALHOST";

			            puerto =51;

					cliente = new Cliente(nombrehost,puerto);
					
			        try {
						new HiloCliente(cliente.getSocket(),this,username).start();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				    
		}

			
			
			public Datos getD() {
				return d;
			}

			public void setD(Datos dt) {
				this.d.clone(dt);
				//System.out.println(d);
			}

			public Jugador getJ(int i) {
				return j.get(i);
			}
			public  ArrayList<Jugador> getJ() {
				return j;
			}
			public void setJ(ArrayList<Jugador>ju) {
				this.j =new ArrayList<Jugador>(ju);
			}

			public DatosJugador getDj() {
				return dj;
			}

			public void setDj(DatosJugador dj) {
				this.dj = dj;
			}
	 

}
