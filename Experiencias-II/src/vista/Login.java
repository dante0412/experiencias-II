package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.Usuario;
import hilos.Tiempo;
import mantenimiento.GestionUsuarioDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame implements WindowListener, ActionListener {

	private JPanel contentPane;
	private JLabel lblUsuario;
	private JLabel lblPassword;
	private JLabel lblNewLabel;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JButton btnIngresar;
	private JButton btnCerrar;
	private JLabel lblTexto;
	public static JLabel lblTiempo;
	public static Login frame;
	
	GestionUsuarioDAO gUser = new GestionUsuarioDAO();
	
	public static Usuario usuario = new Usuario();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
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
		addWindowListener(this);
		try {
			//selecionar dise?o a trabajar
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("Luana Star | Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 258);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(125, 91, 70, 13);
		contentPane.add(lblUsuario);
		
		lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setBounds(125, 141, 70, 13);
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
		txtUser.setBounds(205, 88, 140, 19);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setColumns(10);
		txtPass.setBounds(205, 138, 140, 19);
		contentPane.add(txtPass);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(this);
		btnIngresar.setBounds(110, 190, 85, 21);
		contentPane.add(btnIngresar);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(292, 190, 85, 21);
		contentPane.add(btnCerrar);
		
		lblTexto = new JLabel("Esta ventana se cerrara en: ");
		lblTexto.setBounds(70, 49, 159, 13);
		contentPane.add(lblTexto);
		
		lblTiempo = new JLabel("10 s");
		lblTiempo.setBounds(239, 49, 45, 13);
		contentPane.add(lblTiempo);
	}
	
	private void iniciarConteo() {
		Tiempo t = new Tiempo();
		t.start();
	}
	
	public void windowActivated(WindowEvent e) {
	}
	public void windowClosed(WindowEvent e) {
	}
	public void windowClosing(WindowEvent e) {
	}
	public void windowDeactivated(WindowEvent e) {
	}
	public void windowDeiconified(WindowEvent e) {
	}
	public void windowIconified(WindowEvent e) {
	}
	public void windowOpened(WindowEvent e) {
		if (e.getSource() == this) {
			windowOpenedThis(e);
		}
	}
	protected void windowOpenedThis(WindowEvent e) {
		iniciarConteo();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(e);
		}
		if (e.getSource() == btnIngresar) {
			actionPerformedBtnIngresar(e);
		}
	}
	protected void actionPerformedBtnIngresar(ActionEvent e) {
		validarAcceso();
	}
	
	private String getClave() {
		String clave = null;
		clave = String.valueOf(txtPass.getPassword());
		return clave;
	}

	private String getUsuario() {
		String user = null;
		user = txtUser.getText().trim();
		return user;
	}
	
	private void limpiarCajas() {
		txtUser.setText("");
		txtPass.setText("");
		txtUser.requestFocus();
	}
	
	private void mensajeError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error!!!", 0);
		
	}
	
	private void cargarBarraProgreso() {
		FrmLoding l = new FrmLoding();
		l.setVisible(true);
		l.setLocationRelativeTo(this);
		this.dispose();
		
	}
	
	private void validarAcceso() {
		String user,clave;
	
		user = getUsuario();
		clave = getClave();
		
		if(user == null || clave == null) {
			return;
		} else {
		
			usuario = gUser.validarAcceso(user, clave);
		
			if(usuario == null) {
				mensajeError("Usuario y/o password incorrecto");
				limpiarCajas();
			}else {
				cargarBarraProgreso();
			}
		}
		
	}
	protected void actionPerformedBtnCerrar(ActionEvent e) {
		dispose();
	}
}
