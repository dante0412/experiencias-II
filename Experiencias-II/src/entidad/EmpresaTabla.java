package entidad;

public class EmpresaTabla {
	private int codigo;
	private String empresa;
	private String direccion;
	private String distrito;
	private String telefono;
	
	
	public EmpresaTabla() {

	}

	public EmpresaTabla(int codigo, String empresa, String direccion, String distrito, String telefono) {
		this.codigo = codigo;
		this.empresa = empresa;
		this.direccion = direccion;
		this.distrito = distrito;
		this.telefono = telefono;
	}
	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
