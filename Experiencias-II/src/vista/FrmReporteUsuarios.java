package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.CategoriaUsuarios;
import entidad.ReporteUsuario;
import mantenimiento.GestionCategoriasDAO;
import mantenimiento.GestionUsuarioDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FrmReporteUsuarios extends JInternalFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblListado;
	private JLabel lblCategoria;
	private JComboBox cboCategoria;
	private JTable tblUsuarios;
	private JScrollPane scrollPane;
	private JButton btnReporte;
	DefaultTableModel model = new DefaultTableModel();
	
	GestionCategoriasDAO gCat = new GestionCategoriasDAO();
	GestionUsuarioDAO gUser = new GestionUsuarioDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporteUsuarios frame = new FrmReporteUsuarios();
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
	public FrmReporteUsuarios() {
//		setIconifiable(true);
//		setMaximizable(true);
		setClosable(true);
		setTitle("Reporte | Usuarios");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblListado = new JLabel("Listado de Usuarios");
		lblListado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListado.setBounds(10, 11, 158, 14);
		contentPane.add(lblListado);
		
		lblCategoria = new JLabel("Categor\u00EDa");
		lblCategoria.setBounds(10, 36, 87, 14);
		contentPane.add(lblCategoria);
		
		cboCategoria = new JComboBox();
		cboCategoria.setBounds(90, 32, 193, 22);
		contentPane.add(cboCategoria);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 520, 196);
		contentPane.add(scrollPane);
		
		tblUsuarios = new JTable();
		scrollPane.setViewportView(tblUsuarios);
		model.addColumn("C?digo");
		model.addColumn("Nombres Completos");
		model.addColumn("Categoria");
		tblUsuarios.setModel(model);
		
		btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(this);
		btnReporte.setBounds(373, 32, 107, 23);
		contentPane.add(btnReporte);
		
		comboCategoria();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnReporte) {
			actionPerformedBtnReporte(e);
		}
	}
	protected void actionPerformedBtnReporte(ActionEvent e) {
		mostrarDatos();
	}
	
	private void mostrarDatos() {
		model.setRowCount(0);
		
		int tipo = getCategoria();
		
		//validar
		if(tipo == 0) {
			return;
		}else {
			//obtener ek resultado de consulta
			ArrayList<ReporteUsuario> listUser = gUser.listarReporteUsuarioxTipo(tipo);
			//validar el resultado del proceso
			if(listUser.size() == 0) {
				mensajeError("Lista Vacia");
			}else {
				//bucle
				for (ReporteUsuario repUser : listUser) {
					Object fila[] = {
									repUser.getCodigo(),
									repUser.getNomApe(),
									repUser.getCategoria()
					};
					model.addRow(fila);
				}
			}
		}
	}
	
	private int getCategoria() {
		int categ = 0;
		categ = cboCategoria.getSelectedIndex();
		if(categ == 0) {
			mensajeError("Seleccione una categoria");
		}
		return categ;
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
	
	private void mensajeError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error !!!", 0);
		
	}
}
