package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class FrmPrincipal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnSistema;
	private JMenu mnMantenimiento;
	private JMenuItem mntmSalir;
	private JMenu mnReporte;
	private JMenuItem mntmProducto;
	private JMenuItem mntmUsuarios;
	private JDesktopPane escritorio;
	private JMenu mnVentas;
	private JMenuItem mntmGenerarVenta;
	private JMenuItem mntmReporteUsuarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
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
	public FrmPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmPrincipal.class.getResource("/img/stars.png")));
		setTitle("Luana Star");
		try {
			//selecionar dise�o a trabajar
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 965, 562);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/salida.png")));
		mntmSalir.addActionListener(this);
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, InputEvent.ALT_DOWN_MASK));
		mnSistema.add(mntmSalir);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimiento);
		
		mntmUsuarios = new JMenuItem("Usuarios");
		mntmUsuarios.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/usuario (1).png")));
		mntmUsuarios.addActionListener(this);
		mnMantenimiento.add(mntmUsuarios);
		
		mntmProducto = new JMenuItem("Producto");
		mntmProducto.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/paquete.png")));
		mntmProducto.addActionListener(this);
		mnMantenimiento.add(mntmProducto);
		
		mnVentas = new JMenu("Ventas");
		menuBar.add(mnVentas);
		
		mntmGenerarVenta = new JMenuItem("Realizar Venta");
		mnVentas.add(mntmGenerarVenta);
		
		mnReporte = new JMenu("Reporte");
		menuBar.add(mnReporte);
		
		mntmReporteUsuarios = new JMenuItem("Reporte Usuario");
		mntmReporteUsuarios.addActionListener(this);
		mnReporte.add(mntmReporteUsuarios);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		escritorio = new JDesktopPane();
		contentPane.add(escritorio, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmReporteUsuarios) {
			actionPerformedMntmReporteUsuarios(e);
		}
		if (e.getSource() == mntmProducto) {
			actionPerformedMntmProducto(e);
		}
		if (e.getSource() == mntmUsuarios) {
			actionPerformedMntmClientes(e);
		}
		if (e.getSource() == mntmSalir) {
			actionPerformedMntmSalir(e);
		}
	}
	protected void actionPerformedMntmSalir(ActionEvent e) {
		System.exit(0);
	}
	protected void actionPerformedMntmClientes(ActionEvent e) {
		FrmClientes cli = new FrmClientes();
		cli.setVisible(true);
		escritorio.add(cli);
	}
	protected void actionPerformedMntmProducto(ActionEvent e) {
		FrmProductos prod = new FrmProductos();
		prod.setVisible(true);
		escritorio.add(prod);
	}
	protected void actionPerformedMntmReporteUsuarios(ActionEvent e) {
		FrmReporteUsuarios rU = new FrmReporteUsuarios();
		rU.setVisible(true);
		escritorio.add(rU);
	}
}
