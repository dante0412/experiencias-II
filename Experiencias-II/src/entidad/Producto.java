package entidad;

public class Producto {
	private String cod;
	private String nombre;
	private int stock;
	private double precio;
	private int categoria;
	private int prov;
	
	
	public Producto() {

	}

	public Producto(String cod, String nombre, int stock, double precio, int categoria, int prov) {
		this.cod = cod;
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;
		this.categoria = categoria;
		this.prov = prov;
	}

	
	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public int getProv() {
		return prov;
	}

	public void setProv(int prov) {
		this.prov = prov;
	}

}
