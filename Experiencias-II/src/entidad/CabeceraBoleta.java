package entidad;

public class CabeceraBoleta {
	private String numBol;
	private String fechaBol;
	private int codCliente;
	private int codVendedor;
	private double totalBol;
	
	
	public CabeceraBoleta() {

	}

	public CabeceraBoleta(String numBol, String fechaBol, int codCliente, int codVendedor, double totalBol) {
		this.numBol = numBol;
		this.fechaBol = fechaBol;
		this.codCliente = codCliente;
		this.codVendedor = codVendedor;
		this.totalBol = totalBol;
	}
	

	public String getNumBol() {
		return numBol;
	}

	public void setNumBol(String numBol) {
		this.numBol = numBol;
	}

	public String getFechaBol() {
		return fechaBol;
	}

	public void setFechaBol(String fechaBol) {
		this.fechaBol = fechaBol;
	}

	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public int getCodVendedor() {
		return codVendedor;
	}

	public void setCodVendedor(int codVendedor) {
		this.codVendedor = codVendedor;
	}

	public double getTotalBol() {
		return totalBol;
	}

	public void setTotalBol(double totalBol) {
		this.totalBol = totalBol;
	}
	
}
