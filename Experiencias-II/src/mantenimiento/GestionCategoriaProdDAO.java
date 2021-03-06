package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.CategoriaProducto;
import entidad.Proveedor;
import interfaces.CategoriaProdInterfacesDAO;
import utils.MySQLConexion8;

public class GestionCategoriaProdDAO implements CategoriaProdInterfacesDAO {

	@Override
	public ArrayList<CategoriaProducto> listaCategoriaProducto() {
		ArrayList<CategoriaProducto> lista = new ArrayList<CategoriaProducto>();
		CategoriaProducto catPro;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		
		try {
		
			con = MySQLConexion8.getConexion();

			String sql = "call proc_listarCategoria()";

			pstm = con.prepareStatement(sql);
			
			res = pstm.executeQuery();
			
			while(res.next()){

				catPro = new CategoriaProducto();

				catPro.setCod(res.getInt(1));
				catPro.setDesc(res.getString(2));
				

				lista.add(catPro);
			}
			
		} catch (Exception e) {
			System.out.println(">>>>>>>>> Error en la Instruccion SQL - Consultar " + e.getMessage());
		}
		finally {
			try {
				if(pstm != null) pstm.close();
				if(res !=null) res.close();
				if(con != null) con.close();
			} catch (SQLException e2) {
				System.out.println("<<<<<< Error al cerrar la base de datos " +e2.getMessage());
			}
		}
		return lista;
	}

	@Override
	public ArrayList<Proveedor> listaProveedor() {
		ArrayList<Proveedor> lista = new ArrayList<Proveedor>();
		Proveedor prov;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		
		try {
		
			con = MySQLConexion8.getConexion();

			String sql = "call proc_listarProveedores()";

			pstm = con.prepareStatement(sql);
			
			res = pstm.executeQuery();
			
			while(res.next()){

				prov = new Proveedor();

				prov.setCod(res.getInt(1));
				prov.setEmpresa(res.getString(2));
				

				lista.add(prov);
			}
			
		} catch (Exception e) {
			System.out.println(">>>>>>>>> Error en la Instruccion SQL - Consultar " + e.getMessage());
		}
		finally {
			try {
				if(pstm != null) pstm.close();
				if(res !=null) res.close();
				if(con != null) con.close();
			} catch (SQLException e2) {
				System.out.println("<<<<<< Error al cerrar la base de datos " +e2.getMessage());
			}
		}
		return lista;
	}
	
	
	

}
