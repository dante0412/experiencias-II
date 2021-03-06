package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.CategoriaUsuarios;
import interfaces.CategoriaInterfacesDAO;
import utils.MySQLConexion8;

public class GestionCategoriasDAO implements CategoriaInterfacesDAO {

	@Override
	public ArrayList<CategoriaUsuarios> listaCategoriaUsuario() {
		ArrayList<CategoriaUsuarios> lista = new ArrayList<CategoriaUsuarios>();
		CategoriaUsuarios catUs;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		
		try {
		
			con = MySQLConexion8.getConexion();

			String sql = "call proc_listarTipoUsuario()";

			pstm = con.prepareStatement(sql);
			
			res = pstm.executeQuery();
			
			while(res.next()){

				catUs = new CategoriaUsuarios();

				catUs.setCod(res.getInt(1));
				catUs.setCategoria(res.getString(2));
				

				lista.add(catUs);
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
