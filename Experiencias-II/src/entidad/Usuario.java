package entidad;

public class Usuario {
	private int codigo;
	private String nombre;
	private String apellido;
	private String usuraio;
	private String clave;
	private String fNacim;
	private int categoria;
	private int distrito;
	
	
	public Usuario() {

	}

	public Usuario(int codigo, String nombre, String apellido, String usuraio, String clave, String fNacim, int categoria,
			int distrito) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuraio = usuraio;
		this.clave = clave;
		this.fNacim = fNacim;
		this.categoria = categoria;
		this.distrito = distrito;
	}
	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	public String getUsuraio() {
		return usuraio;
	}

	public void setUsuraio(String usuraio) {
		this.usuraio = usuraio;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getfNacim() {
		return fNacim;
	}

	public void setfNacim(String fNacim) {
		this.fNacim = fNacim;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public int getDistrito() {
		return distrito;
	}

	public void setDistrito(int distrito) {
		this.distrito = distrito;
	}

}
