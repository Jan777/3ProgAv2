package servidor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class VentanaInicio extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaInicio() {
		File archivo=null;
		FileReader fr=null;
		BufferedReader br=null;
		String puerto;
		String jugadores;
		

		setTitle("Main Servidor Volver al Futuro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 392);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				

		
		JLabel lblPuerto = new JLabel("Puerto:");
		lblPuerto.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		lblPuerto.setForeground(Color.WHITE);
		lblPuerto.setBounds(10, 72, 105, 14);
		contentPane.add(lblPuerto);
				
		JLabel lblCantJugadores = new JLabel("Cant. Jugadores:");
		lblCantJugadores.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		lblCantJugadores.setForeground(Color.WHITE);
		lblCantJugadores.setBounds(10, 113, 224, 20);
		contentPane.add(lblCantJugadores);
		
		final JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		

				File archivo = null;
				PrintWriter pw = null;
			
				try{
					archivo = new File ("configsvr.in");
					pw = new PrintWriter (archivo);
					pw.println(textField_1.getText());
					pw.println(textField_2.getText());

							
				}catch (Exception e){
					e.printStackTrace();
				}finally{
					try{
						pw.close();
						btnIniciar.setEnabled(false);
						textField_1.setEnabled(false);
						textField_2.setEnabled(false);
						MainServidor m=new MainServidor(textField_1.getText(),textField_2.getText()); //ejecuto el svr
						m.correr();
					}catch (Exception e){
						e.printStackTrace();
					}
				}
				
			}
		});
		btnIniciar.setBounds(91, 156, 251, 41);
		contentPane.add(btnIniciar);
		
		JButton btnNewButton = new JButton("Ver Jugadores");
		btnNewButton.setBounds(27, 251, 124, 53);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Ver Partidas");
		btnNewButton_1.setBounds(219, 251, 148, 53);
		contentPane.add(btnNewButton_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(140, 72, 240, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(290, 116, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		try
		{
			archivo=new File("configsvr.in");
			fr=new FileReader(archivo);
			br=new BufferedReader(fr);

			
		
			puerto=br.readLine();
			jugadores=br.readLine();
			textField_1.setText(puerto);
			textField_2.setText(jugadores);
			
			JLabel lblBienvenidoAdmin = new JLabel("Bienvenido Admin");
			lblBienvenidoAdmin.setForeground(Color.WHITE);
			lblBienvenidoAdmin.setFont(new Font("OCR A Extended", Font.BOLD, 24));
			lblBienvenidoAdmin.setBounds(56, 22, 286, 14);
			contentPane.add(lblBienvenidoAdmin);
			
			}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	    }
	}
}
