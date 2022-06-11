package hilos;

import vista.Login;

public class Tiempo extends Thread{
	@Override
	public void run() {
		for (int i = 30; i >= 0; i--) {
			Login.lblTiempo.setText(i + "s");
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e) {
				System.out.println("Error en el hilo " + e.getMessage());
			}
		}
		Login.frame.dispose();
	}
	
}
