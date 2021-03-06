package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import entidad.CabeceraBoleta;
import entidad.DetalleBoleta;
import entidad.ReporteUsuario;
import entidad.ReporteVentas;
import interfaces.VentaInterfacesDAO;
import utils.MySQLConexion8;

public class GestionVentasDAO implements VentaInterfacesDAO{

	@Override
	public String numeroBoleta() {
		String codigo = "B0001";
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = MySQLConexion8.getConexion();
			
			String sql = "select substring(max(num_bol),2) from tb_cab_bol"; 
			
			pstm = con.prepareStatement(sql);
			
			res = pstm.executeQuery();
			
			if(res.next()) {
				DecimalFormat df = new DecimalFormat("0000");
				codigo = "B"+ df.format(Integer.parseInt(res.getString(1))+ 1);
			
			}
		} catch (Exception e) {
			System.out.println("Error al generar el numero de boleta" + e.getMessage());
		}finally {
			try {
				if(pstm != null) pstm.close();
				if(res != null) res.close();
				if(con != null) con.close();
			} catch (SQLException e2) {
				System.out.println("<<<< Error al cerrar la base de datos " + e2.getMessage());
			}
		}
		return codigo;
	}

	@Override
	public int realizarVenta(CabeceraBoleta cBol, ArrayList<DetalleBoleta> dBol) {
		int estado = 0;
		Connection con = null;
		PreparedStatement pstm1 = null; //registrar los datos en la tb_cabezeraBoleta
		PreparedStatement pstm2 = null; //registrar los datos en la tb_detalleBoleta
		PreparedStatement pstm3 = null; // actualizar la tabla de productos(stock)
		try {
			//1
			con = MySQLConexion8.getConexion();
			//2.
			con.setAutoCommit(false);
			//Intruccion SQL --> egistrar los datos en la tb_cabezeraBoleta
			String sql1 = "insert into tb_cab_bol values(?, ?, ?, ?, ?)";
			//
			pstm1 = con.prepareStatement(sql1);
			//parametros
			pstm1.setString(1, cBol.getNumBol());
			pstm1.setString(2, cBol.getFechaBol());
			pstm1.setInt(3, cBol.getCodCliente());
			pstm1.setInt(4, cBol.getCodVendedor());
			pstm1.setDouble(5, cBol.getTotalBol());
			//ejecutar
			estado = pstm1.executeUpdate();
			
			//intruccion sql2
			String sql2 = "insert into tb_det_boleta values (?, ?, ?, ?, ?)";
			
			for(DetalleBoleta detBoleta : dBol) {
				pstm2 = con.prepareStatement(sql2);
				//parametros
				pstm2.setString(1, cBol.getNumBol());
				pstm2.setString(2, detBoleta.getCodProd());
				pstm2.setInt(3, detBoleta.getCant());
				pstm2.setDouble(4, detBoleta.getPrecVenta());
				pstm2.setDouble(5, detBoleta.getImporte());
				//ejecutar
				estado = pstm2.executeUpdate();
			}
			
			//actualizar la tabla productos
			String sql3 = "update tb_producto set stock_prod = stock_prod - ? where id_prod = ?";
			
			for(DetalleBoleta detBoleta : dBol) {
				pstm3 = con.prepareStatement(sql3);
				
				pstm3.setInt(1, detBoleta.getCant());
				pstm3.setString(2, detBoleta.getCodProd());
				
				estado = pstm3.executeUpdate();
			}
			
			//confirmar trassaccion
			con.commit();
			
		} catch (Exception e) {
			System.out.println("Error al realizar la venta" +e.getMessage());
			try {
				con.rollback();
			} catch (Exception e2) {
				System.out.println("Eroor al restaurar la Base de datos " + e2.getMessage() );
			}
		}finally {
			try {
				if(pstm1 != null) pstm1.close();
				if(pstm2 != null) pstm2.close();
				if(pstm3 != null) pstm3.close();
				if(con != null) con.close();
			} catch (SQLException e2) {
				System.out.println("<<<< Error al cerrar la base de datos " + e2.getMessage());
			}
		}
		
		
		return estado;
	}

	@Override
	public ArrayList<ReporteVentas> listarReporteVentas() {
		ArrayList<ReporteVentas> lista = new ArrayList<ReporteVentas>();
		ReporteVentas repVen;
		PreparedStatement pstm = null;
		Connection con = null;
		ResultSet res = null;
		try {

			con = MySQLConexion8.getConexion();

			String sql = "call proc_reporteVentas()";
			
			pstm = con.prepareStatement(sql);

			res = pstm.executeQuery();
			
			while(res.next()){

				repVen = new ReporteVentas();

				repVen.setBol(res.getString(1));
				repVen.setFecha(res.getString(2));
				repVen.setVendedor(res.getString(3));
				repVen.setMonto(res.getDouble(4));
				
				lista.add(repVen);
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
