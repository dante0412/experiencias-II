package entidad;

public class CategoriaProducto {
	private int cod;
	private String desc;
	
	
	public CategoriaProducto() {

	}

	public CategoriaProducto(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	
	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
