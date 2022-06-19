package entidad;

public class DetalleBoleta {
	private String numBol;
	private String codProd;
	private int cant;
	private double precVenta;
	private double importe;
	
	
	public DetalleBoleta() {

	}

	public DetalleBoleta(String numBol, String codProd, int cant, double precVenta, double importe) {
		this.numBol = numBol;
		this.codProd = codProd;
		this.cant = cant;
		this.precVenta = precVenta;
		this.importe = importe;
	}

	
	public String getNumBol() {
		return numBol;
	}

	public void setNumBol(String numBol) {
		this.numBol = numBol;
	}

	public String getCodProd() {
		return codProd;
	}

	public void setCodProd(String codProd) {
		this.codProd = codProd;
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}

	public double getPrecVenta() {
		return precVenta;
	}

	public void setPrecVenta(double precVenta) {
		this.precVenta = precVenta;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	
}
