package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.Producto;
import mantenimiento.GestionProductosDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgProducto extends JDialog implements KeyListener, ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblProducto;
	private JTextField txtProducto;
	private JTable tblProducto;
	private JScrollPane scrollPane;
	
	DefaultTableModel model = new DefaultTableModel();
	GestionProductosDAO gPro = new GestionProductosDAO();
	private JButton btnAgregar;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgProducto dialog = new DlgProducto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgProducto() {
		setTitle("Lista de Productos");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblProducto = new JLabel("Producto");
		lblProducto.setBounds(10, 31, 77, 17);
		contentPanel.add(lblProducto);
		
		txtProducto = new JTextField();
		txtProducto.addKeyListener(this);
		txtProducto.setBounds(97, 29, 251, 20);
		contentPanel.add(txtProducto);
		txtProducto.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 414, 148);
		contentPanel.add(scrollPane);
		
		tblProducto = new JTable();
		scrollPane.setViewportView(tblProducto);
		model.addColumn("Código");
		model.addColumn("Producto");
		model.addColumn("Stock");
		model.addColumn("Precio");
		tblProducto.setModel(model);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(170, 227, 89, 23);
		contentPanel.add(btnAgregar);
	}
	
	private void listarProductos(String prod) {
		model.setRowCount(0);
		
		ArrayList<Producto> listProd = gPro.buscarProducto(prod);
		
		if(listProd.size() == 0) {
			mensajeError("No existe producto");
			limpiarCaja();
		}else {
			for (Producto p : listProd) {
				Object fila[] = {
								p.getCod(),
								p.getNombre(),
								p.getStock(),
								p.getPrecio()
				};
				model.addRow(fila);
			}
		}
	}
	
	private void mensajeError(String msj) {
		JOptionPane.showMessageDialog(this, msj,"Error!!!", 0);
		
	}
	
	private void limpiarCaja() {
		txtProducto.setText("");
		txtProducto.requestFocus();
	}
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == txtProducto) {
			keyPressedTxtProducto(e);
		}
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
	}
	protected void keyPressedTxtProducto(KeyEvent e) {
		String prod;
		prod = txtProducto.getText().trim();
		listarProductos(prod);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(e);
		}
	}
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		EnviarDatos();
	}
	
	private void EnviarDatos() {
		String cod, prod,stock, precio;
		int fila;
		//paso 1:obtener el valor de la fila seleccionada
		fila = tblProducto.getSelectedRow();
		//paso 2: obtener los datos de la fila seleccionada
		cod = tblProducto.getValueAt(fila, 0).toString();
		prod = tblProducto.getValueAt(fila, 1).toString();
		stock = tblProducto.getValueAt(fila, 2).toString();
		precio = tblProducto.getValueAt(fila, 3).toString();
		
		
		//paso 3 Enviar los datos obtenidos a las cajas de texto
		FrmBoleta.txtCodProducto.setText(cod);
		FrmBoleta.txtDesProducto.setText(prod);
		FrmBoleta.txtPreProducto.setText(precio);
		FrmBoleta.txtStockProducto.setText(stock);
		//cerrar ventana
		this.dispose();
		
	}
}
