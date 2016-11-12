package cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import datos.Persona;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;


public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JTextField textFieldIP;
	private JTextField textFieldPass;


	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Bienvenido a Volver al Futuro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldIP = new JTextField();
		textFieldIP.setBounds(61, 40, 177, 20);
		contentPane.add(textFieldIP);
		textFieldIP.setColumns(10);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(61, 80, 177, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		textFieldPass = new JTextField();
		textFieldPass.setBounds(61,120, 177, 20);
		contentPane.add(textFieldPass);
		textFieldPass.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registrar r= new Registrar();
				r.setVisible(true);
			}
		});
		btnRegistrar.setBounds(260, 200, 164, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		btnLogIn.setBackground(new Color(0, 128, 0));
		btnLogIn.setForeground(new Color(0, 0, 0));
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persona p=new Persona();
				int valor;
				BasedeDatos con = new BasedeDatos();
				con.connect();
				//con.mostrarDatosJugador();
				valor=con.autenticarUser(textFieldUsuario.getText(), textFieldPass.getText());
				con.close();
				if(valor!=0)
					{con.connect();
					con.mostrarDatosJugador(p,textFieldUsuario.getText(),textFieldPass.getText());
					con.close();
					
					VentanaComenzar vcomenzar=new VentanaComenzar(p,textFieldIP.getText(),textFieldUsuario.getText());
					vcomenzar.setVisible(true);
					dispose();}
				else
					JOptionPane.showMessageDialog(null, "Datos erroneos, Registrese =D");
			}
		});
		btnLogIn.setBounds(61, 200, 177, 23);
		contentPane.add(btnLogIn);
		
		JLabel lblIP = new JLabel("IP");
		lblIP.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lblIP.setForeground(new Color(240, 248, 255));
		lblIP.setBounds(260, 40, 120, 14);
		contentPane.add(lblIP);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lblUsuario.setForeground(new Color(240, 248, 255));
		lblUsuario.setBounds(260,80, 120, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(245, 255, 250));
		lblPassword.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lblPassword.setBounds(260, 120, 120, 14);
		contentPane.add(lblPassword);
	}
}
