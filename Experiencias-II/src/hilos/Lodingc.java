package hilos;

import vista.FrmLoding;

public class Lodingc extends Thread {
	@Override
	public void run() {
		for(int i = 0; i <= 100; i++) {
			FrmLoding.prbCarga.setValue(i);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("Error en el hilo " + e.getMessage());
			}
		}
	}
}
