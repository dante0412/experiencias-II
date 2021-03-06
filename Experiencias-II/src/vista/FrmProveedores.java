package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.Distrito;
import entidad.EmpresaTabla;
import entidad.Proveedor;
import entidad.Usuario;
import entidad.UsuarioTabla;
import mantenimiento.GestionDistritoDAO;
import mantenimiento.GestionProveedoresDAO;
import utils.Validaciones;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class FrmProveedores extends JInternalFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JLabel lblCodigo;
	private JLabel lblEmpresa;
	private JLabel lblDireccion;
	private JLabel lbldistrito;
	private JLabel lblTelefono;
	private JTextField txtCodigo;
	private JTextField txtEmpresa;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JComboBox cboDistrito;
	private JTable tblEmpresas;
	private JRadioButton rdbtnAgregar;
	private JScrollPane scrollPane;
	private JRadioButton rdbtnActualizar;
	private JRadioButton rdbtnEliminar;
	private JButton btnAceptar;
	
	DefaultTableModel model = new DefaultTableModel();
	ButtonGroup group = new ButtonGroup();
	GestionDistritoDAO gDis = new GestionDistritoDAO();
	GestionProveedoresDAO gProv = new GestionProveedoresDAO();
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmProveedores frame = new FrmProveedores();
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
	public FrmProveedores() {
		setClosable(true);
		setTitle("Mantenimiento | Proveedor");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(10, 11, 73, 14);
		contentPane.add(lblCodigo);
		
		lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setBounds(10, 36, 73, 14);
		contentPane.add(lblEmpresa);
		
		lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setBounds(10, 61, 73, 14);
		contentPane.add(lblDireccion);
		
		lbldistrito = new JLabel("Distrito");
		lbldistrito.setBounds(10, 86, 73, 14);
		contentPane.add(lbldistrito);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 111, 73, 14);
		contentPane.add(lblTelefono);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setText("Autogenerado");
		txtCodigo.setBounds(114, 5, 165, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtEmpresa = new JTextField();
		txtEmpresa.setBounds(114, 30, 191, 20);
		contentPane.add(txtEmpresa);
		txtEmpresa.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(114, 55, 191, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(114, 105, 165, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		cboDistrito = new JComboBox();
		cboDistrito.setBounds(114, 79, 165, 22);
		contentPane.add(cboDistrito);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 147, 763, 252);
		contentPane.add(scrollPane);
		
		tblEmpresas = new JTable();
		tblEmpresas.addMouseListener(this);
		scrollPane.setViewportView(tblEmpresas);
		model.addColumn("C?digo");
		model.addColumn("Empresa");
		model.addColumn("Direcci?n");
		model.addColumn("Distrito");
		model.addColumn("Tel?fono");
		tblEmpresas.setModel(model);
		
		rdbtnAgregar = new JRadioButton("Agregar");
		rdbtnAgregar.addActionListener(this);
		rdbtnAgregar.setSelected(true);
		rdbtnAgregar.setBounds(622, 11, 109, 23);
		contentPane.add(rdbtnAgregar);
		
		rdbtnActualizar = new JRadioButton("Actualizar");
		rdbtnActualizar.setBounds(622, 36, 109, 23);
		contentPane.add(rdbtnActualizar);
		
		rdbtnEliminar = new JRadioButton("Eliminar");
		rdbtnEliminar.setBounds(622, 61, 109, 23);
		contentPane.add(rdbtnEliminar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(622, 107, 89, 23);
		contentPane.add(btnAceptar);
		
		group.add(rdbtnAgregar);
		group.add(rdbtnActualizar);
		group.add(rdbtnEliminar);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrmProveedores.class.getResource("/img/proveedor.png")));
		lblNewLabel.setBounds(413, 5, 173, 128);
		contentPane.add(lblNewLabel);
		
		cargarCombo();
		cargarDataProveedores();
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
	
	private void mensajeError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error !!!", 0);
		
	}
	
	private void mensajeExitoso(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Exito ", 1);
		
	}
	
	private void cargarDataProveedores() {
		model.setRowCount(0);
		
		ArrayList<EmpresaTabla> data = gProv.listaEmpresa();
		for(EmpresaTabla et : data) {
			Object fila[] = {
							et.getCodigo(),
							et.getEmpresa(),
							et.getDireccion(),
							et.getDistrito(),
							et.getTelefono()
			};
			model.addRow(fila);
		}
	}
	
	private void registrarProv() {
		String empresa, direc, telefono;
		int dist;
		
		empresa = getEmpresa();
		direc = getDirec();
		dist = getDist();
		telefono = getTelefono();
		
		if(empresa == null || direc == null || dist == 0 || telefono == null ) {
			return;
		}else {
			Proveedor p = new Proveedor();
			
			p.setEmpresa(empresa);
			p.setDireccion(direc);
			p.setDistrito(dist);
			p.setTelefono(telefono);
			
			int res = gProv.registrar(p);
			
			if(res == 0) {
				mensajeError("Error en el registro");
			}else {
				mensajeExitoso("Registro Exitoso");
			}
		}
	}
	
	private void actualizarProv() {
		String empresa, direc, telefono;
		int cod, dist;
		
		cod = getCod();
		empresa = getEmpresa();
		direc = getDirec();
		dist = getDist();
		telefono = getTelefono();
		
		if(cod == -1 || empresa == null || direc == null || dist == 0 || telefono == null ) {
			return;
		}else {
			Proveedor p = new Proveedor();
			
			p.setEmpresa(empresa);
			p.setDireccion(direc);
			p.setDistrito(dist);
			p.setTelefono(telefono);
			p.setCod(cod);
			
			int res = gProv.actualizar(p);
			
			if(res == 0) {
				mensajeError("Error en el registro");
			}else {
				mensajeExitoso("Registro Exitoso");
			}
		}
	}
	
	private void eliminarProv() {
int cod, opcion;
		
		cod = getCod();
		
		//validar 
		if(cod == -1) {
			return;
		}else {
			//mensaje confirmacion
			opcion = JOptionPane.showConfirmDialog(this, "Seguro de eliminar ?", "Sistema", JOptionPane.YES_NO_OPTION);
			if(opcion == 0){ //click en Yes || si desa eliminar
				//ejecutar el proceso eliminar usuario
				int ok = gProv.eliminar(cod);
				//validar resultado del proceso
				if(ok == 0){
					mensajeError("Codigo no existe");
				}else {
					mensajeExitoso("Usuario eliminado");
				}
			}
		}
	}

	private int getCod() {
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

	private String getTelefono() {
		String tel = null;
		if(txtTelefono.getText().trim().length() == 0) {
			mensajeError("Ingrese un telefono");
			txtTelefono.requestFocus();
		}else if(txtTelefono.getText().trim().matches(Validaciones.CELULAR)) {
			tel = txtTelefono.getText().trim();
		}else {
			mensajeError("Formato de telefono incorrecto, Ingresar 9 n?meros");
			txtTelefono.setText("");
			txtTelefono.requestFocus();
		}
		return tel;
	}

	private int getDist() {
		int distrito = 0;
		distrito = cboDistrito.getSelectedIndex();
		if(distrito == 0){
			mensajeError("Selecione un distrito");
		}
		return distrito;
	}

	private String getDirec() {
		String dir = null;
		if(txtDireccion.getText().trim().length() == 0) {
			mensajeError("Ingrese una direcci?n");
			txtDireccion.requestFocus();
		}else if(txtDireccion.getText().trim().matches(Validaciones.DIRECCION)) {
			dir = txtDireccion.getText().trim();
		}else {
			mensajeError("Formato de direcci?n incorrecto, Ingresar de 3 a 200 letras");
			txtDireccion.setText("");
			txtDireccion.requestFocus();
		}
		return dir;
	}

	private String getEmpresa() {
		String emp = null;
		if(txtEmpresa.getText().trim().length() == 0) {
			mensajeError("Ingrese un nombre de la empresa");
			txtEmpresa.requestFocus();
		}else if(txtEmpresa.getText().trim().matches(Validaciones.EMPRESA)) {
			emp = txtEmpresa.getText().trim();
		}else {
			mensajeError("Formato de nombre incorrecto, Ingresar de 3 a 50 letras");
			txtEmpresa.setText("");
			txtEmpresa.requestFocus();
		}
		return emp;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rdbtnAgregar) {
			actionPerformedRdbtnAgregar(e);
		}
		if (e.getSource() == btnAceptar) {
			actionPerformedBtnAceptar(e);
		}
	}
	protected void actionPerformedBtnAceptar(ActionEvent e) {
		if(rdbtnAgregar.isSelected()) {
			registrarProv();
			limpiarCajas();
			cargarDataProveedores();
		}else if(rdbtnActualizar.isSelected()) {
			actualizarProv();
			limpiarCajas();
			cargarDataProveedores();
		}else {
			eliminarProv();
			limpiarCajas();
			cargarDataProveedores();
		}
	}
	private void limpiarCajas() {
		txtEmpresa.setText("");
		txtDireccion.setText("");
		cboDistrito.setSelectedIndex(0);
		txtTelefono.setText("");
		txtEmpresa.requestFocus();
		
	}
	
	private void mostrarData(int posFila) {

		String cod, empresa, dir, dist, tel;

		cod = tblEmpresas.getValueAt(posFila, 0).toString();
		empresa = tblEmpresas.getValueAt(posFila, 1).toString();
		dir = tblEmpresas.getValueAt(posFila, 2).toString();
		dist = tblEmpresas.getValueAt(posFila, 3).toString();
		tel = tblEmpresas.getValueAt(posFila, 4).toString();
		
		

		txtCodigo.setText(cod);
		txtEmpresa.setText(empresa);
		txtDireccion.setText(dir);
		cboDistrito.setSelectedItem(dist);
		txtTelefono.setText(tel);
		
		
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblEmpresas) {
			mouseClickedTblEmpresas(e);
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
	protected void mouseClickedTblEmpresas(MouseEvent e) {
		int posFila = tblEmpresas.getSelectedRow();
		
		mostrarData(posFila);
	}
	protected void actionPerformedRdbtnAgregar(ActionEvent e) {
		limpiarCajas();
		txtCodigo.setText("AutoGenerado");
	}
}
