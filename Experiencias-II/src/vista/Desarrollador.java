package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class Desarrollador extends JInternalFrame {

	private JPanel contentPane;
	private JLabel lblLuana;
	private JSeparator separator;
	private JLabel lblDesarrollador;
	private JLabel lblNombre;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Desarrollador frame = new Desarrollador();
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
	public Desarrollador() {
		setTitle("Acerca del Sistema");
		setClosable(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblLuana = new JLabel("Tienda \"Luana Star\"");
		lblLuana.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblLuana.setBounds(98, 11, 228, 30);
		contentPane.add(lblLuana);
		
		separator = new JSeparator();
		separator.setBounds(10, 52, 414, 2);
		contentPane.add(separator);
		
		lblDesarrollador = new JLabel("Autor");
		lblDesarrollador.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDesarrollador.setBounds(177, 77, 49, 24);
		contentPane.add(lblDesarrollador);
		
		lblNombre = new JLabel("Luis Gonzalo Calderon Tocto (i202116262)");
		lblNombre.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNombre.setBounds(89, 113, 275, 19);
		contentPane.add(lblNombre);
		
		lblNewLabel = new JLabel("luisgonzalo152@gmail.com");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel.setBounds(131, 143, 158, 18);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("959370449");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(177, 172, 63, 18);
		contentPane.add(lblNewLabel_1);
	}
}
