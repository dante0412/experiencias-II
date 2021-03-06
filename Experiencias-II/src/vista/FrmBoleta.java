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
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entidad.CabeceraBoleta;
import entidad.DetalleBoleta;
import mantenimiento.GestionVentasDAO;

import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrmBoleta extends JInternalFrame implements ActionListener {

	private JPanel contentPane;
	
	public static JTextField txtCodCliente;
	public static JTextField txtNomCompletoCliente;
	private JTextField txtFechaActual;
	private JButton btnNuevo;
	private JButton btnFinalizar;
	private JTextField txtTotal;
	private JTextField txtNumBoleta;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblProducto;
	public static JTextField txtCodProducto;
	public static JTextField txtDesProducto;
	public static JTextField txtPreProducto;
	public static JTextField txtStockProducto;
	private JTextField txtCantidadAComprar;
	private JButton btnAgregar;
	private JLabel lblCantidad;
	private JButton btnConsultarProducto;
	private JTable tblVentas;
	private JScrollPane scrollPane;
	
	DefaultTableModel model = new DefaultTableModel();
	
	//acumular el total de importes
	double impTotal;
	//instanciar
	ArrayList<DetalleBoleta> carro = new ArrayList<DetalleBoleta>();
	
	GestionVentasDAO gVen = new GestionVentasDAO();
	



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmBoleta frame = new FrmBoleta();
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
	public FrmBoleta() {
//		setIconifiable(true);
//		setMaximizable(true);
		setClosable(true);
		setTitle("Boleta");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 460);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(23, 387, 89, 23);
		btnNuevo.addActionListener(this);
		contentPane.add(btnNuevo);
		
		btnFinalizar = new JButton("Finalizar Compra");		
		btnFinalizar.setBounds(137, 387, 144, 23);
		btnFinalizar.addActionListener(this);
		contentPane.add(btnFinalizar);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(494, 374, 86, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(429, 377, 46, 14);
		contentPane.add(lblTotal);
		
		JPanel panel = new JPanel();
		panel.setBounds(319, 25, 261, 105);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFecha_1 = new JLabel("Fecha:");
		lblFecha_1.setBounds(10, 56, 48, 14);
		panel.add(lblFecha_1);
		
		txtFechaActual = new JTextField();
		txtFechaActual.setEditable(false);
		txtFechaActual.setText("A\u00F1o/Mes/D\u00EDa");
		txtFechaActual.setBounds(68, 53, 97, 20);
		panel.add(txtFechaActual);
		txtFechaActual.setColumns(10);
		
		JLabel lblNm = new JLabel("N\u00FAm");
		lblNm.setBounds(10, 14, 33, 14);
		panel.add(lblNm);
		
		txtNumBoleta = new JTextField();
		txtNumBoleta.setEditable(false);
		txtNumBoleta.setText("B0000");
		txtNumBoleta.setColumns(10);
		txtNumBoleta.setBounds(68, 11, 162, 20);
		panel.add(txtNumBoleta);
		
		panel_1 = new JPanel();
		panel_1.setBounds(20, 34, 261, 96);
		panel_1.setBorder(new TitledBorder(null, "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(10, 21, 70, 25);
		panel_1.add(lblCliente);
		
		txtCodCliente = new JTextField();
		txtCodCliente.setEditable(false);
		txtCodCliente.setBounds(67, 23, 97, 20);
		panel_1.add(txtCodCliente);
		txtCodCliente.setColumns(10);
		
		txtNomCompletoCliente = new JTextField();
		txtNomCompletoCliente.setEditable(false);
		txtNomCompletoCliente.setBounds(67, 54, 173, 20);
		panel_1.add(txtNomCompletoCliente);
		txtNomCompletoCliente.setColumns(10);
		
		JButton btnConsultarCliente = new JButton("");
		btnConsultarCliente.setBounds(184, 9, 37, 37);
		panel_1.add(btnConsultarCliente);
		btnConsultarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DlgClientes d = new DlgClientes();
				d.setVisible(true);
			}
		});
		btnConsultarCliente.setBorder(null);
		btnConsultarCliente.setBorderPainted(false);
		btnConsultarCliente.setContentAreaFilled(false);
		btnConsultarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		
		btnConsultarCliente.setIcon(new ImageIcon(FrmBoleta.class.getResource("/img/busca.png")));
		
		panel_2 = new JPanel();
		panel_2.setForeground(Color.WHITE);
		panel_2.setBounds(20, 141, 563, 96);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Producto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel_2);
		
		lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(10, 21, 70, 25);
		panel_2.add(lblProducto);
		
		txtCodProducto = new JTextField();
		txtCodProducto.setEditable(false);
		txtCodProducto.setColumns(10);
		txtCodProducto.setBounds(87, 23, 86, 20);
		panel_2.add(txtCodProducto);
		
		txtCantidadAComprar = new JTextField();
		txtCantidadAComprar.setColumns(10);
		txtCantidadAComprar.setBounds(87, 54, 86, 20);
		panel_2.add(txtCantidadAComprar);
		
		btnAgregar = new JButton("");
		btnAgregar.addActionListener(this);
		btnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAgregar.setRolloverIcon(new ImageIcon(FrmBoleta.class.getResource("/img/cartp.png")));
		btnAgregar.setIcon(new ImageIcon(FrmBoleta.class.getResource("/img/cartp.png")));
		btnAgregar.setContentAreaFilled(false);
		btnAgregar.setBorderPainted(false);
		btnAgregar.setBorder(null);
		btnAgregar.setBounds(183, 48, 37, 37);
		panel_2.add(btnAgregar);
		
		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(10, 57, 70, 14);
		panel_2.add(lblCantidad);
		
		btnConsultarProducto = new JButton("");
		btnConsultarProducto.addActionListener(this);
		btnConsultarProducto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		
		btnConsultarProducto.setIcon(new ImageIcon(FrmBoleta.class.getResource("/img/busca.png")));
		btnConsultarProducto.setContentAreaFilled(false);
		btnConsultarProducto.setBorderPainted(false);
		btnConsultarProducto.setBorder(null);
		btnConsultarProducto.setBounds(183, 9, 37, 37);
		panel_2.add(btnConsultarProducto);
		
		txtDesProducto = new JTextField();
		txtDesProducto.setEditable(false);
		txtDesProducto.setColumns(10);
		txtDesProducto.setBounds(235, 23, 143, 20);
		panel_2.add(txtDesProducto);
		
		txtPreProducto = new JTextField();
		txtPreProducto.setEditable(false);
		txtPreProducto.setColumns(10);
		txtPreProducto.setBounds(387, 23, 70, 20);
		panel_2.add(txtPreProducto);
		
		txtStockProducto = new JTextField();
		txtStockProducto.setEditable(false);
		txtStockProducto.setColumns(10);
		txtStockProducto.setBounds(471, 23, 70, 20);
		panel_2.add(txtStockProducto);
		
		JLabel lblAgregarProducto = new JLabel("Agregar producto ");
		lblAgregarProducto.setBounds(230, 57, 148, 14);
		panel_2.add(lblAgregarProducto);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 248, 556, 115);
		contentPane.add(scrollPane);
		
		tblVentas = new JTable();
		scrollPane.setViewportView(tblVentas);
		model.addColumn("C?digo");
		model.addColumn("Producto");
		model.addColumn("Cantidad");
		model.addColumn("Precio");
		model.addColumn("Importe");
		tblVentas.setModel(model);
		
		//obtener el numero de bolesta
		txtNumBoleta.setText(generarNumeroBoleta());
		txtFechaActual.setText(obtenerFecha());
		
	}
	
	private String generarNumeroBoleta() {
		GestionVentasDAO gVen = new GestionVentasDAO();
		return gVen.numeroBoleta();
	}

	private int obtenerCodVendedor() {
		//Devolver el codigo de usuario q ingreso al sistema
		return Login.usuario.getCodigo();
	}

	private int leerCodCliente() {
		// TODO Auto-generated method stub
		return Integer.parseInt(txtCodCliente.getText());
	}

	private String obtenerNumBoleta() {		
		
		return generarNumeroBoleta();
	}

	private String obtenerFecha() {
		// Obtener la fecha del sistema
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	}

	private int leerCantidad() {
		int cant = -1;
		if(txtCantidadAComprar.getText().trim().length() == 0) {
			mensajeError("Ingresar una cantidad");
			
		}else {
			try {
				cant = Integer.parseInt(txtCantidadAComprar.getText());
				if(cant > leerStock()) {
					mensajeError("Stock insuficiente");
					txtCantidadAComprar.setText("");
					txtCantidadAComprar.requestFocus();
					cant = -1;
				}
			} catch (NumberFormatException e) {
				mensajeError("Ingresar solo valores numericos al stock");
				
			}
		}
		return cant;
	}

	private int leerStock() {
		int stock = -1;
		if(txtStockProducto.getText().trim().length() == 0) {
			mensajeError("Ingresar el stock");
			
		}else {
			try {
				stock = Integer.parseInt(txtStockProducto.getText());
				if(stock <= 0) {
					mensajeError("Ingresar stock mayor a 0");
					stock = -1;
				}
			} catch (NumberFormatException e) {
				mensajeError("Ingresar solo valores numericos al stock");
				
			}
		}
		return stock;
	}

	private double leerPrecio() {
		// TODO Auto-generated method stub
		return Double.parseDouble(txtPreProducto.getText());
	}

	private String leerNomProd() {
		String prod = null;
		if(txtDesProducto.getText().trim().length() == 0) {
			mensajeError("Ingresar un producto");
			prod = null;
		}else {
			prod = txtDesProducto.getText().trim();
		}
		return prod;
	}

	private String leerCodProd() {
		String cod = null;
		if(txtCodProducto.getText().trim().length() == 0) {
			mensajeError("Ingresar un c?digo de producto");
			cod = null;
		}else {
			cod = txtCodProducto.getText().trim();
		}
		return cod;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(e);
		}
		if (e.getSource() == btnConsultarProducto) {
			actionPerformedBtnConsultarProducto(e);
		}
		if (e.getSource() == btnFinalizar) {
			actionPerformedBtnFinalizar(e);
		}
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
	}
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		txtNomCompletoCliente.setText("");
		txtCodCliente.setText("");
		txtCodProducto.setText("");
		txtDesProducto.setText("");
		txtStockProducto.setText("");
		txtCantidadAComprar.setText("");
		txtPreProducto.setText("");
		txtTotal.setText("");
		
		model.setRowCount(0);
		
		txtNumBoleta.setText(generarNumeroBoleta());
		
	}
	protected void actionPerformedBtnFinalizar(ActionEvent e) {
		finalizarCompra();
	}
	private void finalizarCompra() {
		//declarar variables -- obtener los datos para enviarlos a la clase cabBoleta
		String numBol, fechBol;
		int codCliente, codVendedor;
		double totalBol;
		
		//obtener datos del sistema
		numBol = obtenerNumBoleta();
		fechBol = obtenerFecha();
		codCliente = leerCodCliente();
		codVendedor = obtenerCodVendedor();
		totalBol = impTotal;
		
		//instanciar un objeto de la clase cabBoleta
		CabeceraBoleta cBol = new CabeceraBoleta(numBol, fechBol, codCliente, codVendedor, totalBol);
		//llamar al proceso de transaccion
		int ok = gVen.realizarVenta(cBol, carro);
		//validar el resultado del proceso vcenta
		if (ok == 0) {
			mensajeError("Error en la venta");
		}else {
			mensajeExito("Venta exitosa");
		}
		
	}

	private void mensajeExito(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Exito !", 1);
		
	}

	protected void actionPerformedBtnConsultarProducto(ActionEvent e) {
		DlgProducto d = new DlgProducto();
		d.setVisible(true);
	}
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		agregarProducto();
		limpiarcajasVenta();
	}

	private void agregarProducto() {
		int cant, stock;
		String codProd, nomProd;
		double precio, importe;
		
		//obtener datos de gui
		codProd = leerCodProd();
		nomProd = leerNomProd();
		cant = leerCantidad();
		precio = leerPrecio();
		stock = leerStock();
		
		//validar stock
//		if(cant > stock) {
//			mensajeError("Stock insuficiente");
//			return;
//		}
		if(codProd == null || nomProd == null || cant == -1 || precio == 0|| stock == -1) {
			return;
		}else {
			//calcular importe -- procesos
			importe = calcularImporte(precio, cant);
			impTotal += importe;
			//mostrar datos en la tabla
			Object fila [] = {codProd, nomProd, cant, precio, importe};
			model.addRow(fila);
			
//			mostrar el total de importes
			txtTotal.setText("S/. " + impTotal);
			
			//Agregar los productos al carro de compras -- "DetalleBoleta"
			DetalleBoleta d = new DetalleBoleta(null, codProd, cant, precio, importe);
			carro.add(d);
		}
		
		
//		System.out.println("registros: " +carro.size());
	}

	private void mensajeError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error !!!" , 0);
		
	}

	private double calcularImporte(double precio, int cant) {
		return precio * cant;
	}
	
	private void limpiarcajasVenta() {
		txtCodProducto.setText("");
		txtDesProducto.setText("");
		txtPreProducto.setText("");
		txtStockProducto.setText("");
		txtCantidadAComprar.setText("");
	}
}
