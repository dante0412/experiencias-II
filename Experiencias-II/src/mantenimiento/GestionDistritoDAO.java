package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.Distrito;
import interfaces.DistritoIntefacesDAO;
import utils.MySQLConexion8;

public class GestionDistritoDAO implements DistritoIntefacesDAO{

	@Override
	public ArrayList<Distrito> listaDistrito() {
		ArrayList<Distrito> lista = new ArrayList<Distrito>();
		Distrito dis;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		
		try {
			con = MySQLConexion8.getConexion();
			
			String sql = "SELECT * FROM tb_distritos";
			
			pstm = con.prepareStatement(sql);
			
			
			res = pstm.executeQuery();
			
			while(res.next()) {
				dis = new Distrito();
				
				dis.setCodigo(res.getInt(1));
				dis.setDescripcion(res.getString(2));
				
				lista.add(dis);
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
