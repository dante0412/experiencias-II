package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hilos.Lodingc;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class FrmLoding extends JFrame implements ChangeListener {

	private JPanel contentPane;
	private JLabel lblCarga;
	public static JProgressBar prbCarga;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLoding frame = new FrmLoding();
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
	public FrmLoding() {
		try {
			//selecionar dise?o a trabajar
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 319, 98);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCarga = new JLabel("El sistema esta cargando, espere unos segundos");
		lblCarga.setBounds(10, 10, 416, 13);
		contentPane.add(lblCarga);
		
		prbCarga = new JProgressBar();
		prbCarga.setStringPainted(true);
		prbCarga.addChangeListener(this);
		prbCarga.setBounds(10, 33, 285, 20);
		contentPane.add(prbCarga);
		
		iniciarCarga();
	}
	
	private void iniciarCarga() {
		Lodingc lo = new Lodingc();
		lo.start();
	}
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == prbCarga) {
			stateChangedPrbCarga(e);
		}
	}
	protected void stateChangedPrbCarga(ChangeEvent e) {
		if(prbCarga.getValue() == 100) {
			FrmPrincipal vent = new FrmPrincipal();
			vent.setVisible(true);
			vent.setLocationRelativeTo(this);
			vent.setExtendedState(MAXIMIZED_BOTH);
			this.dispose();
		}
	}
}
