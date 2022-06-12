package interfaces;

import java.util.ArrayList;

import entidad.ReporteUsuario;
import entidad.Usuario;
import entidad.UsuarioTabla;

public interface UsuarioInterfacesDAO {
	//registrar
	public int registrar(Usuario u);
	
	//actualizar
	public int actualizar(Usuario u);
	
	//eliminar
	public int eliminar (int codigo);
	
	//mostrar usuarios en tabla
	public ArrayList<UsuarioTabla> listaUsuarios();
	
	//validar el ingreso al sistema
	public Usuario validarAcceso(String user, String clave);
		
	//reporte x tipo de usuario
	public ArrayList<ReporteUsuario> listarReporteUsuarioxTipo(int tipo);
}
