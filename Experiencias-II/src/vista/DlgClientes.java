package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.ReporteUsuario;
import mantenimiento.GestionUsuarioDAO;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgClientes extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTable tblClientes;
	private JScrollPane scrollPane;
	private JButton btnAgregar;
	
	DefaultTableModel model = new DefaultTableModel();
	GestionUsuarioDAO gUser = new GestionUsuarioDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgClientes dialog = new DlgClientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgClientes() {
		setTitle("Lista de Clientes");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 205);
		contentPanel.add(scrollPane);
		
		tblClientes = new JTable();
		scrollPane.setViewportView(tblClientes);
		model.addColumn("Código");
		model.addColumn("Nombres Completos");
		tblClientes.setModel(model);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(169, 227, 89, 23);
		contentPanel.add(btnAgregar);
		
		mostrarDatos();
		
	}
	
	private void mensajeError(String msj) {
		JOptionPane.showMessageDialog(this, msj,"Error!!!", 0);
		
	}
	
	private void mostrarDatos() {
		model.setRowCount(0);

		ArrayList<ReporteUsuario> listUser = gUser.listarReporteUsuarioxTipo(4);

		if(listUser.size() == 0) {
			mensajeError("Lista Vacia");
		}else {
			//bucle
			for (ReporteUsuario repUser : listUser) {
				Object fila[] = {
								repUser.getCodigo(),
								repUser.getNomApe(),
				};
				model.addRow(fila);
			}
		}
	}
	
	private void EnviarDatos() {
		String cod, cliente;
		int fila;

		fila = tblClientes.getSelectedRow();

		cod = tblClientes.getValueAt(fila, 0).toString();
		cliente = tblClientes.getValueAt(fila, 1).toString();

		FrmBoleta.txtCodCliente.setText(cod);
		FrmBoleta.txtNomCompletoCliente.setText(cliente);

		this.dispose();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(e);
		}
	}
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		EnviarDatos();
	}
}
