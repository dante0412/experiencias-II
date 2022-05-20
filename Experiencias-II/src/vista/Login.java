package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JLabel lblUsuario;
	private JLabel lblPassword;
	private JLabel lblNewLabel;
	private JTextField txtUser;
	private JTextField txtPass;
	private JButton btnIngresar;
	private JButton btnCerrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Luana Star | Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(125, 73, 70, 13);
		contentPane.add(lblUsuario);
		
		lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setBounds(125, 123, 70, 13);
		contentPane.add(lblPassword);
		
		lblNewLabel = new JLabel("LUANA STAR");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(0, 10, 478, 29);
		contentPane.add(lblNewLabel);
		
		txtUser = new JTextField();
		txtUser.setBounds(205, 70, 140, 19);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBounds(205, 120, 140, 19);
		contentPane.add(txtPass);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(110, 172, 85, 21);
		contentPane.add(btnIngresar);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(292, 172, 85, 21);
		contentPane.add(btnCerrar);
	}
}
