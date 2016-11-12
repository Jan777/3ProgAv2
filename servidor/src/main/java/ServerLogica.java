package servidor;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import datos.Background;
import datos.Bala;
import datos.BalaEnm;

import datos.Datos;
import datos.Enemy;
import datos.Explode;
import datos.Jugador;

public class ServerLogica {
	
	private ArrayList<Jugador>j=new ArrayList<Jugador>();
    private ArrayList<Bala> aBala = new ArrayList<Bala>();//contenedor de balas
	private ArrayList<BalaEnm> aBalaEnm = new ArrayList<BalaEnm>();//contenedor de balasEnm
	private ArrayList<Enemy> aEnemy = new ArrayList<Enemy>();//contenedor de enemigos
	private ArrayList<Explode> aExplode = new ArrayList<Explode>();//contenedor de explosiones
	 Background bg1=new Background(0);
	 Background bg2=new Background(-600);
	private int generarEnemigo=0;
	private int gameTime=0;
	private int totalEnm=0;
	private int cantJugadores=2;
	
	private Datos dt=new Datos();
	
	public ServerLogica()
	{
		for (int i = 0; i < cantJugadores; i++) {
			Jugador jug=new Jugador(i+1);
			j.add(jug);
		}
	    
	}
	
	public void startT(){
		
		 final Toolkit t = Toolkit.getDefaultToolkit ();
	
		 
				Timer timer = new Timer (30, new ActionListener () 
				{ 
				    public void actionPerformed(ActionEvent e) 
				    { 
				    	boolean verif=false;
				    	for (int i = 0; i < cantJugadores; i++) {
				    		if(j.get(i).isActivo())
				    			verif=true;
						}
				    	if(verif)
							gameTime++;

						if(generarEnemigo<=0 && (verif))
						{
							totalEnm++;
							
							Random rand1 = new Random();
							int  n = 0;
							
							if(gameTime>200){
								n=rand1.nextInt(2)+1;
								
								if(gameTime>500)
									n=rand1.nextInt(3);
								
								if(totalEnm%20==0)
									n=3;
								
							}
									
							Enemy enm=null;
							//System.out.println(n);
							if(n==0)
							{
								Random rand = new Random();
								int  nx = rand.nextInt(500) + 50;
								enm= new Enemy(nx,0,0);
							}
							if(n==1)
									enm= new Enemy(800,200,1);
							if(n==2)
									enm= new Enemy(-50,200,2);
							if(n==3)
								enm= new Enemy(600,600,3);
							
							aEnemy.add(enm);
							generarEnemigo=30;
						}else
							generarEnemigo--;
						
						for (int i = 0; i < cantJugadores; i++) {
				    		j.get(i).mover();
						}
				          bg1.mover();
				          bg2.mover();
				          
				          for (int i = 0; i < aExplode.size(); i++) 
							aExplode.get(i).mover();
				          
				          Image img3=t.getImage(j.get(0).getImgS());//igual en todos
				          Rectangle rectangleP [] = new Rectangle[cantJugadores];
				          for (int i = 0; i < cantJugadores; i++) {
				        	  Rectangle rectangleP1 = j.get(i).getBounds(img3);
				        	  rectangleP[i]=rectangleP1;
				          }

				          for (int i = 0; i < rectangleP.length; i++) {
				        	  if(j.get(i).getColdDown()>0)
				        		  j.get(i).setColdDown(j.get(i).getColdDown()-1);
							
					          if(j.get(i).isDisparando() && j.get(i).getColdDown()==0 && j.get(i).isActivo())// si esta disparando crea la bala
					          {
					        	  Bala b=new Bala(j.get(i).getX()+20,j.get(i).getY(),i+1); 
					        	  aBala.add(b);
					        	  
					        	  Bala b2=new Bala(j.get(i).getX()+40,j.get(i).getY(),i+1); 
					        	  aBala.add(b2);
					        	  
					        	  j.get(i).setColdDown(10);
					          }
				          }
				         
				          
				          for (int i = 0; i < aEnemy.size(); i++) {//mueve los Enemigos del array
				        	 
				        	  aEnemy.get(i).mover(j.get(0));//default _rnd?
				        	  	
				        		if(aEnemy.get(i).isShooting())//dispara
				        		{
				        			aEnemy.get(i).setShooting(false);
				        			BalaEnm b=new BalaEnm(aEnemy.get(i).getX()+20,aEnemy.get(i).getY(),j.get(0));//BalaEnm b=new BalaEnm(aEnemy.get(i).getX()+20,aEnemy.get(i).getY(),j1); 
				        			aBalaEnm.add(b);
				        		}
				        	  
								if(!aEnemy.get(i).isActive())//elimina los enemigos no activas para no agrandar demaciado el Array
									aEnemy.remove(i);
								else //se fija si choca contra el jugador
								{
									Image img=t.getImage(aEnemy.get(i).getImgS());
									Rectangle rectangleE = aEnemy.get(i).getBounds(img);
	
									boolean murio=false;
									for (int ji = 0; ji < rectangleP.length; ji++) {
										if(rectangleP[ji].intersects(rectangleE) && j.get(ji).isActivo()){
											if(!murio){
												 aEnemy.get(i).setActive(false);
												 aEnemy.remove(i);
												 murio=true;
												 j.get(ji).setInvTime(200);
												 j.get(ji).setActivo(false);
												 j.get(ji).setVidas( j.get(ji).getVidas()-1);
											}
										 }
									}

								}
								
							  }
				          
				          
				          for (int i = 0; i < aBala.size(); i++) {//mueve las balas del array
							aBala.get(i).mover();
							
							if(!aBala.get(i).isActive())//elimina las balas no activas para no agrandar demaciado el Array
								aBala.remove(i);
							else
							{
//								Rectangle rectangleB = aBala.get(i).getBounds();
								Image img=t.getImage(aBala.get(i).getImgS());
								Rectangle rectangleB = aBala.get(i).getBounds(img);
								int jx = 0;
								boolean done=false;
								while(jx < aEnemy.size() && !done) {//verif si choca con un enm
									Image img2=t.getImage(aEnemy.get(jx).getImgS());
									Rectangle rectangleE = aEnemy.get(jx).getBounds(img2);
			//						Rectangle rectangleE = aEnemy.get(j).getBounds();
									if(rectangleB.intersects(rectangleE)){
										int aux=aBala.get(i).getNumPlayer();
										done=true;
										aBala.get(i).setActive(false);
									   	aBala.remove(i);				 
									   	
									   	aEnemy.get(jx).setVida(aEnemy.get(jx).getVida()-1);
									   	
									   	if(	aEnemy.get(jx).getVida()<=0)
									   	{
									   	 Explode exp=new Explode(aEnemy.get(jx).getX(), aEnemy.get(jx).getY(), 0);
										 aExplode.add(exp);
										 
										 aEnemy.get(jx).setActive(false);
										 aEnemy.remove(j);
										 if(aux==1)
											 j.get(0).setScore(j.get(0).getScore()+50);
										 if(aux==2)
											 j.get(1).setScore(j.get(1).getScore()+50);
											 
											
									   	} 
									 }
									jx++;
								}
								 
							}
						           
						  }
				          
				          for (int i = 0; i < aBalaEnm.size(); i++) {//mueve las balas del array
								aBalaEnm.get(i).mover();
								
								if(!aBalaEnm.get(i).isActive())//elimina las balas no activas para no agrandar demaciado el Array
									aBalaEnm.remove(i);
								else
								{
									Image img4=t.getImage(aBalaEnm.get(i).getImgS());
									Rectangle rectangleBE = aBalaEnm.get(i).getBounds(img4);
									//Rectangle rectangleBE = aBalaEnm.get(i).getBounds();
									boolean murio=false;
									for (int ji = 0; ji < rectangleP.length; ji++) {
										if(rectangleP[ji].intersects(rectangleBE) && j.get(ji).isActivo()){
											if(!murio){
												aBalaEnm.get(i).setActive(false);
												 aBalaEnm.remove(i);
												 murio=true;
												 j.get(ji).setInvTime(200);
												 j.get(ji).setActivo(false);
												 j.get(ji).setVidas( j.get(ji).getVidas()-1);
											}
										 }
									}
									
//								
								}
									
				          }
				          
				          
				          //guardar datos
				          
//				          synchronized (dt) {
				        	
				                dt.setaBala(aBala);						  	   
					   	     	dt.setaBalaEnm(aBalaEnm);
					  	    	dt.setaEnemy(aEnemy);
					  	    	dt.setaExplode(aExplode);

						  	    dt.setBg1(bg1);
						  	    dt.setBg2(bg2);
						  	   
						  	    dt.setJ(j);
						  	    //dt.setJ2(j2);


//						}
						  	    
				  	    	
				   
				    }
				}); 
			   
		
		
		 timer.start();
	};
	
	public Datos getDatos(){
		return dt;
	}

	
	

}
