package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.EmpresaTabla;
import entidad.ProductoTabla;
import entidad.Proveedor;
import interfaces.ProveedorInterfacesDAO;
import utils.MySQLConexion8;

public class GestionProveedoresDAO implements ProveedorInterfacesDAO{

	@Override
	public int registrar(Proveedor p) {
		int res = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			con = MySQLConexion8.getConexion();
			
			String sql = "{call proc_registrarProv(null, ?, ?, ?, ?)}";
			
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, p.getEmpresa());
			pstm.setString(2, p.getDireccion());
			pstm.setInt(3, p.getDistrito());
			pstm.setString(4, p.getTelefono());
		
			res = pstm.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(">>>>>>>>> Error en la Instruccion SQL - Registrar " + e.getMessage());
		}finally {
			try {
				if(pstm != null) pstm.close();
				if(con != null) con.close();
			} catch (SQLException e2) {
				System.out.println("<<<<<< Error al cerrar la base de datos " +e2.getMessage());
			}
		}
		
		return res;
	}

	@Override
	public int actualizar(Proveedor p) {
		int res = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {

			con = MySQLConexion8.getConexion();

			String sql = "call proc_actualizarProv(?, ?, ?, ?, ?)";
			
			pstm = con.prepareStatement(sql);

			pstm.setInt(1, p.getCod());
			pstm.setString(2, p.getEmpresa());
			pstm.setString(3, p.getDireccion());
			pstm.setInt(4, p.getDistrito());
			pstm.setString(5, p.getTelefono());
			
			res = pstm.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(">>>>>>>>> Error en la Instruccion SQL - Actualizar " + e.getMessage());
		}
		finally {
			try {
				if(pstm != null) pstm.close();
				if(con != null) con.close();
			} catch (SQLException e2) {
				System.out.println("<<<<<< Error al cerrar la base de datos " +e2.getMessage());
			}
		}
		return res;
	}

	@Override
	public int eliminar(int codigo) {
		int res = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
		
			con = MySQLConexion8.getConexion();
			
			String sql = "call proc_eliminarProv(?)";
	
			pstm = con.prepareStatement(sql);
		
			pstm.setInt(1, codigo);
			
			res = pstm.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(">>>>>>>>> Error en la Instruccion SQL - Eliminar " + e.getMessage());
		}
		finally {
			try {
				if(pstm != null) pstm.close();
				if(con != null) con.close();
			} catch (SQLException e2) {
				System.out.println("<<<<<< Error al cerrar la base de datos " +e2.getMessage());
			}
		}
		return res;
	}

	@Override
	public ArrayList<EmpresaTabla> listaEmpresa() {
		ArrayList<EmpresaTabla> lista = new ArrayList<EmpresaTabla>();
		EmpresaTabla emTa;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		
		try {
			con = MySQLConexion8.getConexion();
			
			String sql = "call proc_listarprovedoresTabla()";
			
			pstm = con.prepareStatement(sql);
			
			res = pstm.executeQuery();
			
			while(res.next()) {
				emTa = new EmpresaTabla();
				
				emTa.setCodigo(res.getInt(1));
				emTa.setEmpresa(res.getString(2));
				emTa.setDireccion(res.getString(3));
				emTa.setDistrito(res.getString(4));
				emTa.setTelefono(res.getString(5));
				
				lista.add(emTa);
			}
			
		} catch (Exception e) {
			System.out.println(">>>>>>>>> Error en la Instruccion SQL - Consultar la tabla " + e.getMessage());
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
