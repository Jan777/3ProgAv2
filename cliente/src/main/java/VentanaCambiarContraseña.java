package cliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPasswordField;

import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

import datos.Persona;

public class VentanaCambiarContraseña extends JDialog {
	private Persona p;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldcontraactual;
	private JTextField textFieldcontranueva;
	private JTextField textFiepregunta;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public VentanaCambiarContraseña(Persona s) {
		//System.out.println("en contraseña "+s.getNombre());
		p=new Persona(s.getNombre(),s.getPass(),s.getNick(),s.getPregunta(),s.getRespuesta());
		setType(Type.UTILITY);
		setTitle("Volver al futuro - Cambiar Contrase\u00F1a");
		setBounds(100, 100, 401, 387);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel etiquetaCActual = new JLabel("Contrase\u00F1a Actual");
			etiquetaCActual.setForeground(Color.WHITE);
			etiquetaCActual.setFont(new Font("OCR A Extended", Font.BOLD, 20));
			etiquetaCActual.setBounds(68, 11, 228, 37);
			contentPanel.add(etiquetaCActual);
		}
		
		JLabel etiquetaNContraseña = new JLabel("Nueva Contrase\u00F1a");
		etiquetaNContraseña.setForeground(Color.WHITE);
		etiquetaNContraseña.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		etiquetaNContraseña.setBounds(68, 90, 228, 37);
		contentPanel.add(etiquetaNContraseña);
		
		final JButton botonCambiar = new JButton("CAMBIAR");
		botonCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				int valor;
				BasedeDatos con = new BasedeDatos();
				con.connect();
				//con.mostrarDatosJugador();
				//System.out.println("antes de cambiar "+ p.getNombre());
				valor=con.cambiarContraseña(p,textFieldcontraactual.getText(), textFieldcontranueva.getText(),textFiepregunta.getText());
				con.close();
				if(valor!=0)
				{JOptionPane.showMessageDialog(null, "Contraseña cambiada!!!");
				dispose();
				}
				
				else
				JOptionPane.showMessageDialog(null, "Datos erroneos, verifique");
				
			}
		});
		botonCambiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				botonCambiar.setBackground(Color.RED);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				botonCambiar.setBackground(Color.BLACK);
			}
		});
		botonCambiar.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		botonCambiar.setBackground(Color.BLACK);
		botonCambiar.setForeground(Color.WHITE);
		botonCambiar.setBounds(99, 300, 155, 37);
		contentPanel.add(botonCambiar);
		
		textFieldcontraactual = new JTextField();
		textFieldcontraactual.setBounds(68, 59, 229, 20);
		contentPanel.add(textFieldcontraactual);
		textFieldcontraactual.setColumns(10);
		
		textFieldcontranueva = new JTextField();
		textFieldcontranueva.setBounds(68, 149, 228, 20);
		contentPanel.add(textFieldcontranueva);
		textFieldcontranueva.setColumns(10);
		
		textFiepregunta = new JTextField();
		textFiepregunta.setBounds(68, 241, 228, 20);
		contentPanel.add(textFiepregunta);
		textFiepregunta.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel(p.getPregunta());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 188, 365, 31);
		contentPanel.add(lblNewLabel_1);
		
	}
}
