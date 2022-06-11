package entidad;

public class ProductoTabla {
	private String cod;
	private String nombre;
	private int stock;
	private double precio;
	private String categ;
	private String proveedor;
	
	
	public ProductoTabla() {

	}

	public ProductoTabla(String cod, String nombre, int stock, double precio, String categ, String proveedor) {
		this.cod = cod;
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;
		this.categ = categ;
		this.proveedor = proveedor;
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

	public String getCateg() {
		return categ;
	}

	public void setCateg(String categ) {
		this.categ = categ;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	
}
