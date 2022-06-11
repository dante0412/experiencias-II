package entidad;

public class UsuarioTabla {
	private int cod;
	private String nombre;
	private String apellido;
	private String usuario;
	private String clave;
	private String fecha;
	private String categoria;
	private String distrito;
	
	
	public UsuarioTabla() {

	}

	public UsuarioTabla(int cod, String nombre, String apellido, String usuario, String clave, String fecha,
			String categoria, String distrito) {
		this.cod = cod;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.clave = clave;
		this.fecha = fecha;
		this.categoria = categoria;
		this.distrito = distrito;
	}
	

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	
}
