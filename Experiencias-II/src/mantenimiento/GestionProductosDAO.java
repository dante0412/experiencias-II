package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.Producto;
import entidad.ProductoTabla;
import interfaces.ProductoInterfacesDAO;
import utils.MySQLConexion8;

public class GestionProductosDAO implements ProductoInterfacesDAO{

	@Override
	public int registrar(Producto p) {
		int res = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			con = MySQLConexion8.getConexion();
			
			String sql = "{call proc_registrarProducto(?, ?, ?, ?, ?, ?)}";
			
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, p.getCod());
			pstm.setString(2, p.getNombre());
			pstm.setInt(3, p.getStock());
			pstm.setDouble(4, p.getPrecio());
			pstm.setInt(5, p.getCategoria());
			pstm.setInt(6, p.getProv());
		
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
	public int actualizar(Producto p) {
		int res = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {

			con = MySQLConexion8.getConexion();

			String sql = "call proc_actualizarProducto(?, ?, ?, ?, ?, ?)";
			
			pstm = con.prepareStatement(sql);

			pstm.setString(1, p.getCod());
			pstm.setString(2, p.getNombre());
			pstm.setInt(3, p.getStock());
			pstm.setDouble(4, p.getPrecio());
			pstm.setInt(5, p.getCategoria());
			pstm.setInt(6, p.getProv());
			
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
	public int eliminar(String codigo) {
		int res = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
		
			con = MySQLConexion8.getConexion();
			
			String sql = "call proc_eliminarProducto(?)";
	
			pstm = con.prepareStatement(sql);
		
			pstm.setString(1, codigo);
			
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
	public ArrayList<ProductoTabla> listaProductos() {
		ArrayList<ProductoTabla> lista = new ArrayList<ProductoTabla>();
		ProductoTabla proTa;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		
		try {
			con = MySQLConexion8.getConexion();
			
			String sql = "call listarProductoenTabla()";
			
			pstm = con.prepareStatement(sql);
			
			res = pstm.executeQuery();
			
			while(res.next()) {
				proTa = new ProductoTabla();
				
				proTa.setCod(res.getString(1));
				proTa.setNombre(res.getString(2));
				proTa.setStock(res.getInt(3));
				proTa.setPrecio(res.getDouble(4));
				proTa.setCateg(res.getString(5));
				proTa.setProveedor(res.getString(6));
				
				lista.add(proTa);
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