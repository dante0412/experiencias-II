package entidad;

public class ReporteUsuario {
	private int codigo;
	private String nomApe;
	private String categoria;
	
	
	public ReporteUsuario() {

	}

	public ReporteUsuario(int codigo, String nomApe, String categoria) {
		this.codigo = codigo;
		this.nomApe = nomApe;
		this.categoria = categoria;
	}

	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomApe() {
		return nomApe;
	}

	public void setNomApe(String nomApe) {
		this.nomApe = nomApe;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
