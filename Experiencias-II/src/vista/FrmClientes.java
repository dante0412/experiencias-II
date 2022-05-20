package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import entidad.Distrito;
import entidad.Usuario;
import mantenimiento.GestionDistritoDAO;
import mantenimiento.GestionUsuarioDAO;
import utils.Validaciones;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FrmClientes extends JInternalFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblCodigo;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblEmail;
	private JLabel lblTelefono;
	private JLabel lblDistrito;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JTextField txtCelular;
	private JLabel lblFechaNac;
	private JDateChooser dcFecha;
	private JButton btnRegistrar;
	private JComboBox cboDistrito;
	private JButton btnLimpiar;
	
	GestionDistritoDAO gDis = new GestionDistritoDAO();
	GestionUsuarioDAO gUser = new GestionUsuarioDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmClientes frame = new FrmClientes();
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
	public FrmClientes() {
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("Mantenimiento | Cliente");
		setBounds(100, 100, 569, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(10, 10, 45, 13);
		contentPane.add(lblCodigo);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 33, 45, 13);
		contentPane.add(lblNombre);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 56, 64, 13);
		contentPane.add(lblApellido);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 79, 45, 13);
		contentPane.add(lblEmail);
		
		lblTelefono = new JLabel("Celular");
		lblTelefono.setBounds(10, 102, 45, 13);
		contentPane.add(lblTelefono);
		
		lblDistrito = new JLabel("Distrito");
		lblDistrito.setBounds(10, 125, 45, 13);
		contentPane.add(lblDistrito);
		
		txtCodigo = new JTextField();
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setText("Auto");
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(140, 7, 160, 19);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(140, 30, 160, 19);
		contentPane.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(140, 53, 160, 19);
		contentPane.add(txtApellido);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(140, 76, 160, 19);
		contentPane.add(txtEmail);
		
		txtCelular = new JTextField();
		txtCelular.setColumns(10);
		txtCelular.setBounds(140, 99, 160, 19);
		contentPane.add(txtCelular);
		
		lblFechaNac = new JLabel("Fecha de Nacimiento");
		lblFechaNac.setBounds(10, 148, 122, 13);
		contentPane.add(lblFechaNac);
		
		dcFecha = new JDateChooser();
		dcFecha.setBounds(140, 145, 160, 19);
		contentPane.add(dcFecha);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(426, 6, 108, 21);
		contentPane.add(btnRegistrar);
		
		cboDistrito = new JComboBox();
		cboDistrito.setBounds(140, 121, 160, 21);
		contentPane.add(cboDistrito);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBounds(426, 33, 108, 21);
		contentPane.add(btnLimpiar);
		
		cargarCombo();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLimpiar) {
			actionPerformedBtnLimpiar(e);
		}
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(e);
		}
	}
	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		registrarUser();
	}
	
	protected void actionPerformedBtnLimpiar(ActionEvent e) {
		limpiarCajas();
	}
	
	private void limpiarCajas() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtEmail.setText("");
		txtCelular.setText("");
		cboDistrito.setSelectedIndex(0);
		dcFecha.setDate(null);
		txtNombre.requestFocus();
		
	}
	
	private void registrarUser() {
		String nomb, ape, cel, email, fecha;
		int distrito;
		
		nomb = getNombre();
		ape = getApellido();
		cel = getCelular();
		email = getEmail();
		fecha = getFecha();
		distrito = getDistrito();
		
		if(nomb == null || ape == null || cel == null || email == null || distrito == 0 || fecha == null) {
			return;
		}else {
			Usuario u = new Usuario();
			
			u.setNombre(nomb);
			u.setApellido(ape);
			u.setCelular(cel);
			u.setEmail(email);
			u.setDistrito(distrito);
			u.setFecha(fecha);
			
			int res = gUser.registrar(u);
			
			if(res == 0) {
				mensajeError("Error en el registro");
			}else {
				mensajeExitoso("Registro Exitoso");
			}
		}
	}

	private int getDistrito() {
		int distrito = 0;
		distrito = cboDistrito.getSelectedIndex();
		if(distrito == 0){
			mensajeError("Selecione un distrito");
		}
		return distrito;
	}

	private String getFecha() {
		String fec = null;
		if(dcFecha.getDate() == null){
			mensajeError("Ingresar una fecha de nacimiento");
		}else{
			fec = new SimpleDateFormat("yyyy/MM/dd").format(dcFecha.getDate());
		}
		return fec;
	}

	private String getEmail() {
		String email = null;
		if(txtEmail.getText().trim().length() == 0) {
			mensajeError("Ingrese un correo");
			txtEmail.requestFocus();
		}else if(txtEmail.getText().trim().matches(Validaciones.EMAIL)) {
			email = txtEmail.getText().trim();
		}else {
			mensajeError("Formato de correo incorrecto, Ingresar xxxx@xxx.xx");
			txtEmail.setText("");
			txtEmail.requestFocus();
		}
		return email;
	}

	private String getCelular() {
		String cel = null;
		if(txtCelular.getText().trim().length() == 0) {
			mensajeError("Ingrese un celular");
			txtCelular.requestFocus();
		}else if(txtCelular.getText().trim().matches(Validaciones.CELULAR)) {
			cel = txtCelular.getText().trim();
		}else {
			mensajeError("Formato de celular incorrecto, Ingresar 9 digitos");
			txtCelular.setText("");
			txtCelular.requestFocus();
		}
		return cel;
	}

	private String getApellido() {
		String ape = null;
		if(txtApellido.getText().trim().length() == 0) {
			mensajeError("Ingrese un apellido");
			txtApellido.requestFocus();
		}else if(txtApellido.getText().trim().matches(Validaciones.APELLIDO)) {
			ape = txtApellido.getText().trim();
		}else {
			mensajeError("Formato de apellido incorrecto, Ingresar de 3 a 30 letras");
			txtApellido.setText("");
			txtApellido.requestFocus();
		}
		return ape;
	}

	private String getNombre() {
		String nomb = null;
		if(txtNombre.getText().trim().length() == 0) {
			mensajeError("Ingrese un nombre");
			txtNombre.requestFocus();
		}else if(txtNombre.getText().trim().matches(Validaciones.NOMBRE)) {
			nomb = txtNombre.getText().trim();
		}else {
			mensajeError("Formato de nombre incorrecto, Ingresar de 3 a 30 letras");
			txtNombre.setText("");
			txtNombre.requestFocus();
		}
		return nomb;
	}

	private void mensajeError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error !!!", 0);
		
	}
	
	private void mensajeExitoso(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Exito ", 1);
		
	}
	
	private void cargarCombo() {
		ArrayList<Distrito> listaDistrito = gDis.listaDistrito();
		
		if (listaDistrito.size() == 0) {
			mensajeError("Lista Vacia");
		}else {
			cboDistrito.addItem("Seleccione ...");
			for(Distrito distrito : listaDistrito) {
				cboDistrito.addItem(distrito.getCodigo() + " - " + distrito.getDescripcion());
			}
		}
	}

	
	
	
}
