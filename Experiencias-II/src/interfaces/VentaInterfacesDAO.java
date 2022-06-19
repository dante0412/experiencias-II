package interfaces;

import java.util.ArrayList;

import entidad.CabeceraBoleta;
import entidad.DetalleBoleta;
import entidad.ReporteUsuario;
import entidad.ReporteVentas;

public interface VentaInterfacesDAO {
	//metodo para generar el Numero de boleta
	public String numeroBoleta();
	
	//proceso de transaccion
	public int realizarVenta(CabeceraBoleta cBol, ArrayList<DetalleBoleta> dBol);
	
	//reporteVentas
	public ArrayList<ReporteVentas> listarReporteVentas();
}
