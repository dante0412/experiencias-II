package interfaces;


import java.util.ArrayList;

import entidad.EmpresaTabla;
import entidad.Proveedor;

public interface ProveedorInterfacesDAO {
	//registrar
	public int registrar(Proveedor p);
		
	//actualizar
	public int actualizar(Proveedor p);
		
	//eliminar 
	public int eliminar(int codigo);
	
	//listar empresas
	public ArrayList<EmpresaTabla> listaEmpresa();
}
