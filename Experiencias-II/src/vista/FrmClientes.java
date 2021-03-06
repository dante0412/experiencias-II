package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import entidad.CategoriaUsuarios;
import entidad.Distrito;
import entidad.Usuario;
import entidad.UsuarioTabla;
import mantenimiento.GestionCategoriasDAO;
import mantenimiento.GestionDistritoDAO;
import mantenimiento.GestionUsuarioDAO;
import utils.Validaciones;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class FrmClientes extends JInternalFrame implements ActionListener, MouseListener {

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
	private JTextField txtUser;
	private JPasswordField txtClave;
	private JLabel lblFechaNac;
	private JDateChooser dcFecha;
	private JComboBox cboDistrito;
	ButtonGroup group = new ButtonGroup();
	DefaultTableModel model = new DefaultTableModel();
	
	GestionDistritoDAO gDis = new GestionDistritoDAO();
	GestionUsuarioDAO gUser = new GestionUsuarioDAO();
	GestionCategoriasDAO gCat = new GestionCategoriasDAO();
	private JTable tblClientes;
	private JScrollPane scrollPane;
	private JRadioButton rdbtnAgregar;
	private JRadioButton rdbtnActualizar;
	private JRadioButton rdbtnEliminar;
	private JButton btnAceptar;
	private JLabel lblCategoria;
	private JComboBox cboCategoria;
	private JLabel lblNewLabel;

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
//		setIconifiable(true);
//		setMaximizable(true);
		setClosable(true);
		setTitle("Mantenimiento | Cliente");
		setBounds(100, 100, 799, 449);
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
		
		lblEmail = new JLabel("Usuario");
		lblEmail.setBounds(10, 79, 45, 13);
		contentPane.add(lblEmail);
		
		lblTelefono = new JLabel("Clave");
		lblTelefono.setBounds(10, 102, 45, 13);
		contentPane.add(lblTelefono);
		
		lblDistrito = new JLabel("Distrito");
		lblDistrito.setBounds(10, 170, 45, 13);
		contentPane.add(lblDistrito);
		
		txtCodigo = new JTextField();
		txtCodigo.setText("AutoGenerado");
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(140, 7, 200, 19);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(140, 30, 200, 19);
		contentPane.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(140, 53, 200, 19);
		contentPane.add(txtApellido);
		
		txtUser = new JTextField();
		txtUser.setColumns(10);
		txtUser.setBounds(140, 76, 200, 19);
		contentPane.add(txtUser);
		
		txtClave = new JPasswordField();
		txtClave.setColumns(10);
		txtClave.setBounds(140, 99, 200, 19);
		contentPane.add(txtClave);
		
		lblFechaNac = new JLabel("Fecha de Nacimiento");
		lblFechaNac.setBounds(10, 122, 122, 13);
		contentPane.add(lblFechaNac);
		
		dcFecha = new JDateChooser();
		dcFecha.setBounds(140, 122, 200, 19);
		contentPane.add(dcFecha);
		
		cboDistrito = new JComboBox();
		cboDistrito.setBounds(140, 170, 200, 21);
		contentPane.add(cboDistrito);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 202, 763, 206);
		contentPane.add(scrollPane);
		
		tblClientes = new JTable();
		tblClientes.addMouseListener(this);
		scrollPane.setViewportView(tblClientes);
		model.addColumn("C?digo");
		model.addColumn("Nombres");
		model.addColumn("Apellidos");
		model.addColumn("Usuario");
		model.addColumn("Contrase?a");
		model.addColumn("Fecha");
		model.addColumn("Categoria");
		model.addColumn("Distrito");
		tblClientes.setModel(model);
		
		rdbtnAgregar = new JRadioButton("Agregar");
		rdbtnAgregar.addActionListener(this);
		rdbtnAgregar.setSelected(true);
		rdbtnAgregar.setBounds(664, 39, 109, 23);
		contentPane.add(rdbtnAgregar);
		
		rdbtnActualizar = new JRadioButton("Actualizar");
		rdbtnActualizar.setBounds(664, 67, 109, 23);
		contentPane.add(rdbtnActualizar);
		
		rdbtnEliminar = new JRadioButton("Eliminar");
		rdbtnEliminar.setBounds(664, 95, 109, 23);
		contentPane.add(rdbtnEliminar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(664, 136, 89, 23);
		contentPane.add(btnAceptar);
		
		group.add(rdbtnEliminar);
		group.add(rdbtnActualizar);
		group.add(rdbtnAgregar);
		
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(10, 145, 109, 14);
		contentPane.add(lblCategoria);
		
		cboCategoria = new JComboBox();
		cboCategoria.setBounds(140, 145, 200, 22);
		contentPane.add(cboCategoria);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrmClientes.class.getResource("/img/grupo.png")));
		lblNewLabel.setBounds(444, 30, 200, 149);
		contentPane.add(lblNewLabel);
		
		cargarDataUsuarios();
		cargarCombo();
		comboCategoria();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rdbtnAgregar) {
			actionPerformedRdbtnAgregar(e);
		}
		if (e.getSource() == btnAceptar) {
			actionPerformedBtnAceptar(e);
		}
	}
	
	private void limpiarCajas() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtUser.setText("");
		txtClave.setText("");
		cboDistrito.setSelectedIndex(0);
		cboCategoria.setSelectedIndex(0);
		dcFecha.setDate(null);
		txtNombre.requestFocus();
		
	}
	
	private void registrarUser() {
		String nomb, ape, user, clave, fecha;
		int distrito, categ;
		
		nomb = getNombre();
		ape = getApellido();
		user = getUser();
		clave = getClave();
		fecha = getFecha();
		categ = getCategoria();
		distrito = getDistrito();
		
		if(nomb == null || ape == null || user == null || clave == null || fecha == null || categ == 0 || distrito == 0) {
			return;
		}else {
			Usuario u = new Usuario();
			
			u.setNombre(nomb);
			u.setApellido(ape);
			u.setUsuraio(user);;
			u.setClave(clave);;
			u.setfNacim(fecha);
			u.setCategoria(categ);
			u.setDistrito(distrito);
			
			int res = gUser.registrar(u);
			
			if(res == 0) {
				mensajeError("Error en el registro");
			}else {
				mensajeExitoso("Registro Exitoso");
			}
		}
	}
	
	private void actualizarUser() {
		String nomb, ape, user, clave, fecha;
		int cod, distrito, categ;
		
		cod = getCodigo();
		nomb = getNombre();
		ape = getApellido();
		user = getUser();
		clave = getClave();
		fecha = getFecha();
		categ = getCategoria();
		distrito = getDistrito();
		
		if(cod == -1 || nomb == null || ape == null || user == null || clave == null || fecha == null || categ == 0 || distrito == 0) {
			return;
		}else {
			Usuario u = new Usuario();
			
			u.setNombre(nomb);
			u.setApellido(ape);
			u.setUsuraio(user);;
			u.setClave(clave);;
			u.setfNacim(fecha);
			u.setCategoria(categ);
			u.setDistrito(distrito);
			u.setCodigo(cod);
			
			int res = gUser.actualizar(u);
			
			if(res == 0) {
				mensajeError("Error en la actualizaci?n");
			}else {
				mensajeExitoso("Acualizaci?n Exitosa");
			}
		}
		
	}
	
	private void eliminarUsuarios() {
		int cod, opcion;
		
		cod = getCodigo();
		
		//validar 
		if(cod == -1) {
			return;
		}else {
			//mensaje confirmacion
			opcion = JOptionPane.showConfirmDialog(this, "Seguro de eliminar ?", "Sistema", JOptionPane.YES_NO_OPTION);
			if(opcion == 0){ //click en Yes || si desa eliminar
				//ejecutar el proceso eliminar usuario
				int ok = gUser.eliminar(cod);
				//validar resultado del proceso
				if(ok == 0){
					mensajeError("Codigo no existe");
				}else {
					mensajeExitoso("Usuario eliminado");
				}
			}
		}
	}

	private int getCodigo() {
		int cod = -1;
		if(txtCodigo.getText().trim().length() == 0) {
			mensajeError("Ingresar el codigo");
			txtCodigo.requestFocus();
			cod = -1;
		}else {
			cod = Integer.parseInt(txtCodigo.getText());
		}
		return cod;
	}

	private int getCategoria() {
		int categ = 0;
		categ = cboCategoria.getSelectedIndex();
		if(categ == 0) {
			mensajeError("Seleccione una categoria");
		}
		return categ;
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

	private String getUser() {
		String user = null;
		if(txtUser.getText().trim().length() == 0) {
			mensajeError("Ingrese un usuario");
			txtUser.requestFocus();
		}else if(txtUser.getText().trim().matches(Validaciones.USER)) {
			user = txtUser.getText().trim();
		}else {
			mensajeError("Formato de usuario incorrecto, Ingresar U000");
			txtUser.setText("");
			txtUser.requestFocus();
		}
		return user;
	}

	private String getClave() {
		String pass = null;
		if(String.valueOf(txtClave.getPassword()).trim().length() == 0) {
			mensajeError("Ingrese una contrase?a");
			txtClave.requestFocus();
		}else {
			pass = String.valueOf(txtClave.getPassword());
		}
		
		return pass;
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
				cboDistrito.addItem(distrito.getDescripcion());
			}
		}
	}
	
	private void comboCategoria() {
		ArrayList<CategoriaUsuarios> listaCat = gCat.listaCategoriaUsuario();
		
		if (listaCat.size() == 0) {
			mensajeError("Lista vacia");
		} else {
			cboCategoria.addItem("Seleccione ...");
			for(CategoriaUsuarios cat : listaCat) {
				cboCategoria.addItem(cat.getCategoria());
			}
		}
	}
	
	private void cargarDataUsuarios() {
		model.setRowCount(0);
		
		ArrayList<UsuarioTabla> data = gUser.listaUsuarios();
		for(UsuarioTabla ut : data) {
			Object fila[] = {
							ut.getCod(),
							ut.getNombre(),
							ut.getApellido(),
							ut.getUsuario(),
							ut.getClave(),
							ut.getFecha(),
							ut.getCategoria(),
							ut.getDistrito()
			};
			model.addRow(fila);
		}
	}
	
	private void mostrarData(int posFila) {

		String cod, nomb, ape, user, clave, fecha, categ, dist;

		cod = tblClientes.getValueAt(posFila, 0).toString();
		nomb = tblClientes.getValueAt(posFila, 1).toString();
		ape = tblClientes.getValueAt(posFila, 2).toString();
		user = tblClientes.getValueAt(posFila, 3).toString();
		clave = tblClientes.getValueAt(posFila, 4).toString();
		fecha = tblClientes.getValueAt(posFila, 5).toString();
		categ = tblClientes.getValueAt(posFila, 6).toString();
		dist = tblClientes.getValueAt(posFila, 7).toString();

		txtCodigo.setText(cod);
		txtNombre.setText(nomb);
		txtApellido.setText(ape);
		txtUser.setText(user);
		txtClave.setText(clave);
		try {
			dcFecha.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
		} catch (ParseException e) {

			e.printStackTrace();
		}
		cboCategoria.setSelectedItem(categ);
		cboDistrito.setSelectedItem(dist);
		
	}
	protected void actionPerformedBtnAceptar(ActionEvent e) {
		if(rdbtnAgregar.isSelected()) {
			registrarUser();
			limpiarCajas();
			cargarDataUsuarios();
		} else if (rdbtnActualizar.isSelected()) {
			actualizarUser();
			limpiarCajas();
			cargarDataUsuarios();
		} else {
			eliminarUsuarios();
			limpiarCajas();
			cargarDataUsuarios();
		}
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblClientes) {
			mouseClickedTblClientes(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedTblClientes(MouseEvent e) {
		int posFila = tblClientes.getSelectedRow();
		
		mostrarData(posFila);
	}
	protected void actionPerformedRdbtnAgregar(ActionEvent e) {
		limpiarCajas();
		txtCodigo.setText("AutoGenerado");
	}
}
