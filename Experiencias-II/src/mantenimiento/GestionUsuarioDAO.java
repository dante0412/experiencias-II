package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.ReporteUsuario;
import entidad.Usuario;
import entidad.UsuarioTabla;
import interfaces.UsuarioInterfacesDAO;
import utils.MySQLConexion8;

public class GestionUsuarioDAO implements UsuarioInterfacesDAO{

	@Override
	public int registrar(Usuario u) {
		int res = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			con = MySQLConexion8.getConexion();
			
			String sql = "INSERT INTO tb_usuarios VALUES (null, ?, ?, ?, ?, ?, ?, ?)";
			
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, u.getNombre());
			pstm.setString(2, u.getApellido());
			pstm.setString(3, u.getUsuraio());
			pstm.setString(4, u.getClave());
			pstm.setString(5, u.getfNacim());
			pstm.setInt(6, u.getCategoria());
			pstm.setInt(7, u.getDistrito());
			
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
	public int actualizar(Usuario u) {
		int res = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {

			con = MySQLConexion8.getConexion();

			String sql = "call proc_actualizarUsuarios(?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstm = con.prepareStatement(sql);

			pstm.setInt(1, u.getCodigo());
			pstm.setString(2, u.getNombre());
			pstm.setString(3, u.getApellido());
			pstm.setString(4, u.getUsuraio());
			pstm.setString(5, u.getClave());
			pstm.setString(6, u.getfNacim());
			pstm.setInt(7, u.getCategoria());
			pstm.setInt(8, u.getDistrito());
			

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
			
			String sql = "call proc_eliminarUsuarios(?)";
	
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
	public ArrayList<UsuarioTabla> listaUsuarios() {
		ArrayList<UsuarioTabla> lista = new ArrayList<UsuarioTabla>();
		UsuarioTabla usTa;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		
		try {
			con = MySQLConexion8.getConexion();
			
			String sql = "{call proc_listarUsuarios()}";
			
			pstm = con.prepareStatement(sql);
			
			res = pstm.executeQuery();
			
			while(res.next()) {
				usTa = new UsuarioTabla();
				
				usTa.setCod(res.getInt(1));
				usTa.setNombre(res.getString(2));
				usTa.setApellido(res.getString(3));
				usTa.setUsuario(res.getString(4));
				usTa.setClave(res.getString(5));
				usTa.setFecha(res.getString(6));
				usTa.setCategoria(res.getString(7));
				usTa.setDistrito(res.getString(8));
				
				lista.add(usTa);
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

	@Override
	public Usuario validarAcceso(String user, String clave) {
		Usuario usuario = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			
			con = MySQLConexion8.getConexion();
			
			String sql = "{call proc_validarUsuario(?, ?)}";
			
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, user);
			pstm.setString(2, clave);

			res = pstm.executeQuery();

			if(res.next()) {
				usuario = new Usuario(res.getInt(1),
									  res.getString(2),
									  res.getString(3),
									  res.getString(4), 
									  res.getString(5),
									  res.getString(6),
									  res.getInt(7),
									  res.getInt(8));
			}
			
		} catch (Exception e) {
			System.out.println(">>>>>>>>> Error en la Instruccion SQL - Registrar " + e.getMessage());
		}
		finally {
			try {
				if(pstm != null) pstm.close();
				if(con != null) con.close();
			} catch (SQLException e2) {
				System.out.println("<<<<<< Error al cerrar la base de datos " +e2.getMessage());
			}
		}
		return usuario ;
	}

	@Override
	public ArrayList<ReporteUsuario> listarReporteUsuarioxTipo(int tipo) {
		ArrayList<ReporteUsuario> lista = new ArrayList<ReporteUsuario>();
		ReporteUsuario repUser;
		PreparedStatement pstm = null;
		Connection con = null;
		ResultSet res = null;
		try {

			con = MySQLConexion8.getConexion();

			String sql = "call proc_reporteUsuarios(?);";
			
			pstm = con.prepareStatement(sql);

			pstm.setInt(1, tipo);

			res = pstm.executeQuery();
			
			while(res.next()){

				repUser = new ReporteUsuario();

				repUser.setCodigo(res.getInt(1));
				repUser.setNomApe(res.getString(2));
				repUser.setCategoria(res.getString(3));
				
				lista.add(repUser);
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
