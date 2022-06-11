package entidad;

public class Proveedor {
	private int cod;
	private String empresa;
	private String direccion;
	private int distrito;
	private String telefono;
	
	
	public Proveedor() {

	}

	public Proveedor(int cod, String empresa, String direccion, int distrito, String telefono) {
		this.cod = cod;
		this.empresa = empresa;
		this.direccion = direccion;
		this.distrito = distrito;
		this.telefono = telefono;
	}

	
	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getDistrito() {
		return distrito;
	}

	public void setDistrito(int distrito) {
		this.distrito = distrito;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	
	
}
