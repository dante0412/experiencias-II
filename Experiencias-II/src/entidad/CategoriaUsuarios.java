package entidad;

public class CategoriaUsuarios {
	private int cod;
	private String categoria;
	
	public CategoriaUsuarios() {

	}

	public CategoriaUsuarios(int cod, String categoria) {
		this.cod = cod;
		this.categoria = categoria;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
}
