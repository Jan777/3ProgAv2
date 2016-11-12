package cliente;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window.Type;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;

public class VentanaEstadisticasDelUsuario extends JFrame {

	private JPanel contentPane;
	private JTable tablaDelUsuario;
	private JScrollPane tabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEstadisticasDelUsuario frame = new VentanaEstadisticasDelUsuario();
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
	public VentanaEstadisticasDelUsuario() {
		setTitle("Volver al Futuro - Estadisticas Del Usuario");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 381);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String titulos[]={"Juego","Puntos","Ganado"};
		String juegos[][]={{"Juego 1","1000","SI"},{"Juego 3","500","NO"},{"Juego 2","2000","SI"},{"Juego 1","4000","SI"},{"Juego 1","300","NO"},{"Juego 2","600","NO"},{"Juego 1","1000","SI"},{"Juego 3","10000","SI"},{"Juego 3","100","NO"}};
		tablaDelUsuario= new JTable(juegos,titulos);
		tablaDelUsuario.setEnabled(false);
		tablaDelUsuario.setShowGrid(false);
		
		tablaDelUsuario.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		tablaDelUsuario.setBounds(96, 78, 225, 48);
		
		
		tabla=new JScrollPane(tablaDelUsuario);
		tabla.setBackground(Color.LIGHT_GRAY);
		tabla.setBounds(10, 78, 414, 157);
		
		getContentPane().add(tabla, BorderLayout.CENTER);
		
		JLabel etiquetaPTotal = new JLabel("Puntaje Total:");
		etiquetaPTotal.setForeground(Color.WHITE);
		etiquetaPTotal.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		etiquetaPTotal.setBounds(10, 247, 189, 29);
		contentPane.add(etiquetaPTotal);
		
		JLabel etiquetaPuntajeTotal = new JLabel("19500");
		etiquetaPuntajeTotal.setForeground(Color.YELLOW);
		etiquetaPuntajeTotal.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		etiquetaPuntajeTotal.setBounds(219, 247, 151, 29);
		contentPane.add(etiquetaPuntajeTotal);
		
		JLabel etiquetaG = new JLabel("Juegos Ganados:");
		etiquetaG.setForeground(Color.WHITE);
		etiquetaG.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		etiquetaG.setBounds(10, 281, 199, 29);
		contentPane.add(etiquetaG);
		
		JLabel etiquetaGanados = new JLabel("5");
		etiquetaGanados.setForeground(Color.YELLOW);
		etiquetaGanados.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		etiquetaGanados.setBounds(219, 281, 189, 29);
		contentPane.add(etiquetaGanados);
		
		JLabel etiquetaPR = new JLabel("Posicion en Ranking:");
		etiquetaPR.setForeground(Color.WHITE);
		etiquetaPR.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		etiquetaPR.setBounds(10, 22, 268, 29);
		contentPane.add(etiquetaPR);
		
		JLabel etiquetaRanking = new JLabel("3");
		etiquetaRanking.setForeground(Color.YELLOW);
		etiquetaRanking.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		etiquetaRanking.setBounds(273, 22, 151, 29);
		contentPane.add(etiquetaRanking);
		
		
	}
}
