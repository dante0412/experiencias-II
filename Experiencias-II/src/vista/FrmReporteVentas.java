package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.ReporteUsuario;
import entidad.ReporteVentas;
import mantenimiento.GestionVentasDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmReporteVentas extends JInternalFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblReporte;
	private JTable tblVentas;
	private JScrollPane scrollPane;
	
	DefaultTableModel model = new DefaultTableModel();
	GestionVentasDAO gVen = new GestionVentasDAO();
	private JButton btnReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporteVentas frame = new FrmReporteVentas();
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
	public FrmReporteVentas() {
		setClosable(true);
		setTitle("Reprote | Ventas");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblReporte = new JLabel("Listado de Ventas");
		lblReporte.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReporte.setBounds(10, 11, 150, 14);
		contentPane.add(lblReporte);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 520, 196);
		contentPane.add(scrollPane);
		
		tblVentas = new JTable();
		scrollPane.setViewportView(tblVentas);
		model.addColumn("N Boleta");
		model.addColumn("Fecha");
		model.addColumn("Vendedor");
		model.addColumn("Monto");
		tblVentas.setModel(model);
		
		btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(this);
		btnReporte.setBounds(224, 242, 89, 23);
		contentPane.add(btnReporte);
	}
	
	private void mostrarReporte() {
		model.setRowCount(0);
		
		//obtener ek resultado de consulta
		ArrayList<ReporteVentas> listVen = gVen.listarReporteVentas();
		//validar el resultado del proceso
		if(listVen.size() == 0) {
			mensajeError("Lista Vacia");
		}else {
			//bucle
			for (ReporteVentas repVen : listVen) {
				Object fila[] = {
						repVen.getBol(),
						repVen.getFecha(),
						repVen.getVendedor(),
						repVen.getMonto()
				};
				model.addRow(fila);
			}
		}
	}

	private void mensajeError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error !!!", 0);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnReporte) {
			actionPerformedBtnReporte(e);
		}
	}
	protected void actionPerformedBtnReporte(ActionEvent e) {
		mostrarReporte();
	}
}
