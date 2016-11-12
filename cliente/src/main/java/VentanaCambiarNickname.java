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
import javax.swing.JTextField;

import datos.Persona;

import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCambiarNickname extends JDialog {
	private Persona p;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNickname;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public VentanaCambiarNickname(Persona s) {
		p=new Persona(s.getNombre(),s.getPass(),s.getNick(),s.getPregunta(),s.getRespuesta());
		setType(Type.UTILITY);
		setTitle("Volver al Futuro - Cambiar Nickname");
		setBackground(Color.BLACK);
		setBounds(100, 100, 395, 84);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			final JButton botonCambiar = new JButton("CAMBIAR");
			botonCambiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int valor;
					BasedeDatos con = new BasedeDatos();
					con.connect();
					//con.mostrarDatosJugador();
					//System.out.println("antes de cambiar "+ p.getNombre());
					valor=con.cambiarNick(p,textFieldNickname.getText());
					con.close();
					if(valor!=0)
					{JOptionPane.showMessageDialog(null, "Nick cambiado");
					dispose();
					}
					
					else
					JOptionPane.showMessageDialog(null, "ha ocurrido un error");
				}
			});
			botonCambiar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
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
			botonCambiar.setBounds(216, 11, 140, 23);
			contentPanel.add(botonCambiar);
		}
		{
			textFieldNickname = new JTextField();
			textFieldNickname.setBounds(10, 15, 181, 20);
			contentPanel.add(textFieldNickname);
			textFieldNickname.setColumns(10);
		}
	}

}
