package entidad;

public class ReporteVentas {
	private String bol;
	private String fecha;
	private String vendedor;
	private double monto;
	
	
	public ReporteVentas() {

	}

	public ReporteVentas(String bol, String fecha, String vendedor, double monto) {
		this.bol = bol;
		this.fecha = fecha;
		this.vendedor = vendedor;
		this.monto = monto;
	}

	
	public String getBol() {
		return bol;
	}

	public void setBol(String bol) {
		this.bol = bol;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

}
