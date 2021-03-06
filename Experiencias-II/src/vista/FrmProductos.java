package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.CategoriaProducto;
import entidad.Distrito;
import entidad.Producto;
import entidad.ProductoTabla;
import entidad.Proveedor;
import entidad.Usuario;
import entidad.UsuarioTabla;
import mantenimiento.GestionCategoriaProdDAO;
import mantenimiento.GestionDistritoDAO;
import mantenimiento.GestionProductosDAO;
import utils.Validaciones;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class FrmProductos extends JInternalFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JLabel lblCodigo;
	private JLabel lblNombre;
	private JLabel lblStock;
	private JLabel lblPrecio;
	private JLabel lblCategoria;
	private JLabel lblProveedor;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JComboBox cboCategoria;
	private JComboBox cboProveedor;
	private JRadioButton rdbtnAgregar;
	private JRadioButton rdbtnActualizar;
	private JRadioButton rdbtnEliminar;
	private JButton btnAceptar;
	private JTable tblProductos;
	private JScrollPane scrollPane;
	DefaultTableModel model = new DefaultTableModel();
	
	GestionProductosDAO gPro = new GestionProductosDAO();
	GestionDistritoDAO gDis = new GestionDistritoDAO();
	GestionCategoriaProdDAO gCatP = new GestionCategoriaProdDAO();
	
	ButtonGroup group = new ButtonGroup();
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmProductos frame = new FrmProductos();
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
	public FrmProductos() {
//		setIconifiable(true);
//		setMaximizable(true);
		setClosable(true);
		setTitle("Mantenimiento | Producto");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(10, 11, 46, 14);
		contentPane.add(lblCodigo);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 36, 88, 14);
		contentPane.add(lblNombre);
		
		lblStock = new JLabel("Stock");
		lblStock.setBounds(10, 61, 46, 14);
		contentPane.add(lblStock);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(10, 86, 46, 14);
		contentPane.add(lblPrecio);
		
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(10, 111, 88, 14);
		contentPane.add(lblCategoria);
		
		lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(10, 136, 88, 14);
		contentPane.add(lblProveedor);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(108, 8, 182, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(108, 33, 182, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtStock = new JTextField();
		txtStock.setBounds(108, 58, 182, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(108, 83, 182, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		cboCategoria = new JComboBox();
		cboCategoria.setBounds(108, 107, 182, 22);
		contentPane.add(cboCategoria);
		
		cboProveedor = new JComboBox();
		cboProveedor.setBounds(108, 132, 182, 22);
		contentPane.add(cboProveedor);
		
		rdbtnAgregar = new JRadioButton("Agregar");
		rdbtnAgregar.addActionListener(this);
		rdbtnAgregar.setBounds(664, 27, 109, 23);
		contentPane.add(rdbtnAgregar);
		
		rdbtnActualizar = new JRadioButton("Actualizar");
		rdbtnActualizar.addActionListener(this);
		rdbtnActualizar.setBounds(664, 52, 109, 23);
		contentPane.add(rdbtnActualizar);
		
		rdbtnEliminar = new JRadioButton("Eliminar");
		rdbtnEliminar.addActionListener(this);
		rdbtnEliminar.setBounds(664, 77, 109, 23);
		contentPane.add(rdbtnEliminar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(664, 132, 89, 23);
		contentPane.add(btnAceptar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 174, 763, 225);
		contentPane.add(scrollPane);
		
		tblProductos = new JTable();
		tblProductos.addMouseListener(this);
		scrollPane.setViewportView(tblProductos);
		model.addColumn("C?digo");
		model.addColumn("Nombre");
		model.addColumn("Stock");
		model.addColumn("Precio");
		model.addColumn("Categor?a");
		model.addColumn("Proveedor");
		tblProductos.setModel(model);
		
		group.add(rdbtnEliminar);
		group.add(rdbtnActualizar);
		group.add(rdbtnAgregar);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrmProductos.class.getResource("/img/productos-lacteos (1).png")));
		lblNewLabel.setBounds(438, 20, 142, 130);
		contentPane.add(lblNewLabel);
		
		cargarComboCategoria();
		cargarComboProveedor();
		
		cargarDataProductos();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rdbtnEliminar) {
			actionPerformedRdbtnEliminar(e);
		}
		if (e.getSource() == rdbtnActualizar) {
			actionPerformedRdbtnActualizar(e);
		}
		if (e.getSource() == rdbtnAgregar) {
			actionPerformedRdbtnAgregar(e);
		}
		if (e.getSource() == btnAceptar) {
			actionPerformedBtnAceptar(e);
		}
	}
	protected void actionPerformedBtnAceptar(ActionEvent e) {
		if(rdbtnAgregar.isSelected()) {
			registrarProd();
			limpiarCajas();
			cargarDataProductos();
		} else if(rdbtnActualizar.isSelected()) {
			actualizarProd();
			limpiarCajas();
			cargarDataProductos();
		} else {
			eliminarProd();
			limpiarCajas();
			cargarDataProductos();
		}
	}
	
	private void limpiarCajas() {
		txtCodigo.setText("");
		txtNombre.setText("");
		txtStock.setText("");
		txtPrecio.setText("");
		cboCategoria.setSelectedIndex(0);;
		cboProveedor.setSelectedIndex(0);
		txtCodigo.requestFocus();
	}
	
	private void cargarComboCategoria() {
		ArrayList<CategoriaProducto> listaCategoria = gCatP.listaCategoriaProducto();
		
		if (listaCategoria.size() == 0) {
			mensajeError("Lista Vacia");
		}else {
			cboCategoria.addItem("Seleccione ...");
			for(CategoriaProducto catPro : listaCategoria) {
				cboCategoria.addItem(catPro.getDesc());
			}
		}
	}
	
	private void cargarComboProveedor() {
		ArrayList<Proveedor> listaProveedor = gCatP.listaProveedor();
		
		if (listaProveedor.size() == 0) {
			mensajeError("Lista Vacia");
		}else {
			cboProveedor.addItem("Seleccione ...");
			for(Proveedor Prov : listaProveedor) {
				cboProveedor.addItem(Prov.getEmpresa());
			}
		}
	}

	private void mensajeError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error !!!", 0);
		
	}
	
	private void mensajeExitoso(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Exito !", 1);
		
	}
	
	private void registrarProd() {
		String cod, nombre;
		int stock, categoria, proveedor;
		double precio;
		
		cod = getCodigo();
		nombre = getNombre();
		stock = getStock();
		precio = getPrecio();
		categoria = getCategoria();
		proveedor = getProveedor();
		
		if(cod == null || nombre == null || stock == -1 || precio == -1 || categoria == 0 || proveedor == 0) {
			return;
		}else {
			Producto p = new Producto();
			
			p.setCod(cod);
			p.setNombre(nombre);
			p.setStock(stock);
			p.setPrecio(precio);
			p.setCategoria(categoria);
			p.setProv(proveedor);
			
			int res = gPro.registrar(p);
			
			if(res == 0) {
				mensajeError("Error en el registro");
			}else {
				mensajeExitoso("Registro Exitoso");
			}
		}
	}
	
	private void actualizarProd() {
		String cod, nombre;
		int stock, categoria, proveedor;
		double precio;
		
		cod = getCodigo();
		nombre = getNombre();
		stock = getStock();
		precio = getPrecio();
		categoria = getCategoria();
		proveedor = getProveedor();
		
		if(cod == null || nombre == null || stock == -1 || precio == -1 || categoria == 0 || proveedor == 0) {
			return;
		}else {
			Producto p = new Producto();
			
			p.setCod(cod);
			p.setNombre(nombre);
			p.setStock(stock);
			p.setPrecio(precio);
			p.setCategoria(categoria);
			p.setProv(proveedor);
			
			int res = gPro.actualizar(p);
			
			if(res == 0) {
				mensajeError("Error en la actualizaci?n");
			}else {
				mensajeExitoso("Acualizaci?n Exitosa");
			}
		}
		
	}
	
	private void eliminarProd() {
		int opcion;
		String cod;
		
		cod = getCodigo();
		
		//validar 
		if(cod == null) {
			return;
		}else {
			//mensaje confirmacion
			opcion = JOptionPane.showConfirmDialog(this, "Seguro de eliminar ?", "Sistema", JOptionPane.YES_NO_OPTION);
			if(opcion == 0){ //click en Yes || si desa eliminar
				//ejecutar el proceso eliminar usuario
				int ok = gPro.eliminar(cod);
				//validar resultado del proceso
				if(ok == 0){
					mensajeError("Codigo no existe");
				}else {
					mensajeExitoso("Usuario eliminado");
				}
			}
		}
	}

	private int getProveedor() {
		int prov = 0;
		prov = cboProveedor.getSelectedIndex();
		if(prov == 0) {
			mensajeError("Seleccione un proveedor");
		}
		return prov;
	}

	private int getCategoria() {
		int categ = 0;
		categ = cboCategoria.getSelectedIndex();
		if(categ == 0) {
			mensajeError("Seleccione una categoria");
		}
		return categ;
	}

	private double getPrecio() {
		double pre = -1;
		if(txtPrecio.getText().trim().length() == 0) {
			mensajeError("Ingrese un precio");
			txtPrecio.requestFocus();
		}else {
			try {
				pre = Double.parseDouble(txtPrecio.getText());
				if(pre <= 0){
					mensajeError("Ingresar un precio mayor a 0");
					txtPrecio.setText("");
					txtPrecio.requestFocus();
					pre = -1;
				}
			} catch (NumberFormatException e) {
				mensajeError("Ingresar solo valores numericos");
			}
		}
		return pre;
	}

	private int getStock() {
		int stock = -1;
		if(txtStock.getText().trim().length() == 0) {
			mensajeError("Ingresas el stock");
			txtStock.requestFocus();
			stock = -1;
		}else {
			try {
				stock = Integer.parseInt(txtStock.getText());
				if(stock <= 0) {
					mensajeError("Ingresar stock mayor a 0");
					txtStock.setText("");
					txtStock.requestFocus();
					stock = -1;
				}
			} catch (NumberFormatException e) {
				mensajeError("Ingresar solo valores numericos al stock");
				
			}
		}
		return stock;
	}

	private String getNombre() {
		String nomb = null;
		if(txtNombre.getText().trim().length() == 0) {
			mensajeError("Ingresar el nombre del producto");
			txtNombre.requestFocus();
		}else if (txtNombre.getText().trim().matches(Validaciones.NOMBREPRO)){
			nomb = txtNombre.getText().trim();
		}else {
			mensajeError("Formato de nombre incorrecto");
			txtNombre.setText("");
			txtNombre.requestFocus();
		}
		return nomb;
	}

	private String getCodigo() {
		String cod = null;
		if(txtCodigo.getText().trim().length() == 0) {
			mensajeError("Ingresar el codigo de producto");
			txtCodigo.requestFocus();
		}else if (txtCodigo.getText().trim().matches(Validaciones.PRODUCTO)){
			cod = txtCodigo.getText().trim();
		}else {
			mensajeError("Formato de Codigo incorrecto, Ingresar P0000");
			txtCodigo.setText("");
			txtCodigo.requestFocus();
		}
		return cod;
	}
	
	private void cargarDataProductos() {
		model.setRowCount(0);
		
		ArrayList<ProductoTabla> data = gPro.listaProductos();
		for(ProductoTabla pt : data) {
			Object fila[] = {
							pt.getCod(),
							pt.getNombre(),
							pt.getStock(),
							pt.getPrecio(),
							pt.getCateg(),
							pt.getProveedor()
			};
			model.addRow(fila);
		}
	}

	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblProductos) {
			mouseClickedTblProductos(e);
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
	protected void mouseClickedTblProductos(MouseEvent e) {
		int posFila = tblProductos.getSelectedRow();
		
		mostrarData(posFila);
	}
	
	private void mostrarData(int posFila) {

		String cod, nomb, stock, precio, categ, prov;

		cod = tblProductos.getValueAt(posFila, 0).toString();
		nomb = tblProductos.getValueAt(posFila, 1).toString();
		stock = tblProductos.getValueAt(posFila, 2).toString();
		precio = tblProductos.getValueAt(posFila, 3).toString();
		categ = tblProductos.getValueAt(posFila, 4).toString();
		prov = tblProductos.getValueAt(posFila, 5).toString();
		

		txtCodigo.setText(cod);
		txtNombre.setText(nomb);
		txtStock.setText(stock);
		txtPrecio.setText(precio);
		cboCategoria.setSelectedItem(categ);
		cboProveedor.setSelectedItem(prov);
		
	}
	protected void actionPerformedRdbtnAgregar(ActionEvent e) {
		txtCodigo.setEditable(true);
		limpiarCajas();
	}
	protected void actionPerformedRdbtnActualizar(ActionEvent e) {
		txtCodigo.setEditable(false);
	}
	protected void actionPerformedRdbtnEliminar(ActionEvent e) {
		txtCodigo.setEditable(false);
	}
}
