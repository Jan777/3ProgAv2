package cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.SwingConstants;

import datos.Persona;

import java.awt.Font;

public class Registrar extends JFrame {
	private Persona persona;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldPass;
	private JTextField textFieldNick;
	private JTextField textFieldPregunta;
	private JTextField textFieldRespuesta;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Registrar() {
		setBackground(new Color(0, 0, 0));
		setTitle("REGISTRATE GRATIS!!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNombre.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNombre.setBounds(130, 50, 198, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldPass = new JTextField();
		textFieldPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPass.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPass.setBounds(130, 81, 198, 20);
		contentPane.add(textFieldPass);
		textFieldPass.setColumns(10);
		
		textFieldNick = new JTextField();
		textFieldNick.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNick.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNick.setBounds(130, 112, 198, 20);
		contentPane.add(textFieldNick);
		textFieldNick.setColumns(10);
		
		textFieldPregunta = new JTextField();
		textFieldPregunta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPregunta.setBounds(130, 143, 198, 20);
		contentPane.add(textFieldPregunta);
		textFieldPregunta.setColumns(10);
		
		textFieldRespuesta = new JTextField();
		textFieldRespuesta.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldRespuesta.setBounds(130, 174, 198, 20);
		contentPane.add(textFieldRespuesta);
		textFieldRespuesta.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(new Color(240, 248, 255));
		lblUsuario.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		lblUsuario.setBounds(10, 52, 149, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblPass = new JLabel("Pass");
		lblPass.setForeground(new Color(240, 248, 255));
		lblPass.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		lblPass.setBounds(10, 83, 110, 14);
		contentPane.add(lblPass);
		
		JLabel lblNick = new JLabel("Nick");
		lblNick.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		lblNick.setForeground(new Color(240, 248, 255));
		lblNick.setBounds(10, 114, 89, 14);
		contentPane.add(lblNick);
		
		JLabel lblRespuesta = new JLabel("Respuesta");
		lblRespuesta.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		lblRespuesta.setForeground(new Color(240, 255, 255));
		lblRespuesta.setBounds(10, 174, 120, 14);
		contentPane.add(lblRespuesta);
		
		JLabel lblPregunta = new JLabel("Pregunta");
		lblPregunta.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		lblPregunta.setForeground(new Color(240, 255, 255));
		lblPregunta.setBounds(10, 143, 119, 14);
		contentPane.add(lblPregunta);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(textFieldNombre.getText().length()==0||textFieldPass.getText().length()==0||textFieldNick.getText().length()==0||textFieldPregunta.getText().length()==0||textFieldRespuesta.getText().length()==0)
				{JOptionPane.showMessageDialog(null, "Completa todos los datos!!!!");}
				else {
					int valor;
					BasedeDatos con = new BasedeDatos();
					con.connect();
					valor=con.validauser(textFieldNombre.getText());
					con.close();
					if(valor==0)
						{persona= new Persona(textFieldNombre.getText(),textFieldPass.getText(),textFieldNick.getText(),textFieldPregunta.getText(),textFieldRespuesta.getText());
						con.connect();
						con.saveAlumno(persona);
						con.close();
						dispose();
						JOptionPane.showMessageDialog(null, "Registro con exito");}
					else
						JOptionPane.showMessageDialog(null, "Usuario ya usado!!! elija otro");
					////////////////////////////////////////////////////////////////////
				
				//valor=con.validauser(textFieldNombre.getText());
				//System.out.println(valor);
				//if(valor)
				//	{JOptionPane.showMessageDialog(null, "Usuario ya usado!!! elija otro");
				//	con.close();
				//	}
				//else{
				
				}//}
			}
		});
		btnRegistrar.setBounds(140, 215, 188, 23);
		contentPane.add(btnRegistrar);
	}
}
