package cliente;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JSeparator;
import javax.swing.BoxLayout;
import javax.swing.JToggleButton;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import datos.Persona;

import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaComenzar extends JFrame {
	private Persona p;
	private JPanel contentPane;
	private String ipname;
	private String uname;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param ip 
	 * @param username 
	 */
	public VentanaComenzar(Persona s, String ip, String username) {
		ipname=ip;
		uname=username;
		p=new Persona(s.getNombre(),s.getPass(),s.getNick(),s.getPregunta(),s.getRespuesta());
		setType(Type.UTILITY);
		setTitle("Volver al Futuro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 378, 243);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.WHITE);
		menuBar.setBackground(Color.BLACK);
		setJMenuBar(menuBar);
		
		JMenu menuPerfil = new JMenu("Perfil");
		menuPerfil.setFont(new Font("OCR A Extended", Font.BOLD, 14));
		menuPerfil.setForeground(Color.WHITE);
		menuBar.add(menuPerfil);
		
		JMenuItem itemCambiarContrasea = new JMenuItem("Cambiar Contrase\u00F1a");
		itemCambiarContrasea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaCambiarContraseña frame=new VentanaCambiarContraseña(p);
				frame.setVisible(true);
				
			}
		});
		
		menuPerfil.add(itemCambiarContrasea);
		
		JMenuItem itemCambiarNickname = new JMenuItem("Cambiar NickName");
		itemCambiarNickname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaCambiarNickname dialog = new VentanaCambiarNickname(p);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		menuPerfil.add(itemCambiarNickname);
		
		JMenuItem itemCerrarSesion = new JMenuItem("Cerrar Sesion");
		itemCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal frame=new Principal();
				frame.setVisible(true);
				dispose();
			}
		});
		menuPerfil.add(itemCerrarSesion);
		
		JMenu menustadisticas = new JMenu("Estadisticas");
		menustadisticas.setForeground(Color.WHITE);
		menustadisticas.setFont(new Font("OCR A Extended", Font.BOLD, 14));
		menuBar.add(menustadisticas);
		
		JMenuItem itemDelUsuario = new JMenuItem("Del Usuario");
		itemDelUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaEstadisticasDelUsuario frame = new VentanaEstadisticasDelUsuario();
				frame.setVisible(true);
			}
		});
		
		menustadisticas.add(itemDelUsuario);
		
		JMenuItem itemGenerales = new JMenuItem("Generales");
		itemGenerales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaEstadisticasGenerales frame = new VentanaEstadisticasGenerales();
				frame.setVisible(true);
			}
		});
		menustadisticas.add(itemGenerales);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Lleno");
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(249, 33, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Unirse");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaGame game=new VentanaGame(2,ipname,uname);//Pasar aCa el Cliente
				game.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(249, 79, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Unirse");
		btnNewButton_2.setBounds(249, 124, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblActivo = new JLabel("Activo");
		lblActivo.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		lblActivo.setForeground(Color.RED);
		lblActivo.setBounds(122, 25, 117, 33);
		contentPane.add(lblActivo);
		
		JLabel lblEnEspera = new JLabel("En Espera");
		lblEnEspera.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		lblEnEspera.setForeground(Color.GREEN);
		lblEnEspera.setBounds(122, 78, 117, 19);
		contentPane.add(lblEnEspera);
		
		JLabel label = new JLabel("En Espera");
		label.setForeground(Color.GREEN);
		label.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		label.setBounds(122, 123, 117, 19);
		contentPane.add(label);
		
		JLabel lblJuego = new JLabel("JUEGO 1");
		lblJuego.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		lblJuego.setForeground(Color.WHITE);
		lblJuego.setBounds(10, 30, 102, 23);
		contentPane.add(lblJuego);
		
		JLabel lblJuego_2 = new JLabel("JUEGO 2");
		lblJuego_2.setForeground(Color.WHITE);
		lblJuego_2.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		lblJuego_2.setBounds(10, 76, 102, 23);
		contentPane.add(lblJuego_2);
		
		JLabel lblJuego_1 = new JLabel("JUEGO 3");
		lblJuego_1.setForeground(Color.WHITE);
		lblJuego_1.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		lblJuego_1.setBounds(10, 121, 102, 23);
		contentPane.add(lblJuego_1);
		
	}
}
