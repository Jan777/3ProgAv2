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

public class VentanaEstadisticasGenerales extends JFrame {

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
					VentanaEstadisticasGenerales frame = new VentanaEstadisticasGenerales();
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
	public VentanaEstadisticasGenerales() {
		setTitle("Volver al Futuro - Estadisticas Generales");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 310);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String titulos[]={"Posicion","Usuario","Puntos"};
		String juegos[][]={{"1","Martin","150000"},{"2","Doc","149000"},{"3","MC","19500"},{"4","Piccoro","18000"},{"5","Terminator","17200"},{"6","T-1000","16000"},{"7","Vegeta","14500"},{"8","Goku","13900"},{"9","Morfeo","13700"},{"10","Gohan","12000"}};
		tablaDelUsuario= new JTable(juegos,titulos);
		tablaDelUsuario.setEnabled(false);
		tablaDelUsuario.setShowGrid(false);
		
		tablaDelUsuario.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		tablaDelUsuario.setBounds(96, 78, 225, 48);
		
		
		tabla=new JScrollPane(tablaDelUsuario);
		tabla.setBackground(Color.LIGHT_GRAY);
		tabla.setBounds(10, 54, 414, 178);
		
		getContentPane().add(tabla, BorderLayout.CENTER);
		
		JLabel lblTablaDePosiciones = new JLabel("TABLA DE POSICIONES");
		lblTablaDePosiciones.setForeground(Color.WHITE);
		lblTablaDePosiciones.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		lblTablaDePosiciones.setBounds(84, 11, 254, 22);
		contentPane.add(lblTablaDePosiciones);
		
		
	}
}
