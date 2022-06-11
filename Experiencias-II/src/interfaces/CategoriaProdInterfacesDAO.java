package interfaces;

import java.util.ArrayList;

import entidad.CategoriaProducto;
import entidad.Proveedor;

public interface CategoriaProdInterfacesDAO {
	public ArrayList<CategoriaProducto> listaCategoriaProducto();
	
	//listar proveedores combo
	public ArrayList<Proveedor> listaProveedor();
}
