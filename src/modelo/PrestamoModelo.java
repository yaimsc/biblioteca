package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PrestamoModelo extends Conector {
	
	public void insert(Prestamo prestamo){
		try {
			PreparedStatement pst = super.conexion.prepareStatement("insert into prestamos (id_libro, id_usuario, fecha_ini, fecha_fin, entregado) values (?, ?, ?, ?, ?)");
			
			pst.setInt(1, prestamo.getLibro().getId());
			pst.setInt(2, prestamo.getUsuario().getId());
			pst.setDate(3, new java.sql.Date(prestamo.getFechaIni().getTime()));
			pst.setDate(4, new java.sql.Date (prestamo.getFechaFin().getTime()));
			pst.setBoolean(5, prestamo.isEntregado());
			
			pst.execute(); 
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Prestamo> selectAll(){
	
		Prestamo prestamo; 
		
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		
		LibroModelo libroModelo = new LibroModelo();  //se crea todo lo neecsraio para despues coger todos los datos necesario de prestamo
		UsuarioModelo usuarioModelo = new UsuarioModelo(); 
		
		try {
			Statement st = super.conexion.createStatement();
			
			ResultSet rs = st.executeQuery("select * from prestamos");
			
			while(rs.next()){
				prestamo = new Prestamo(); 
				
				prestamo.setId(rs.getInt("id")); 
				prestamo.setLibro(libroModelo.select(rs.getInt("id_libro"))); //seleccionar el libro y de ahi el id
				prestamo.setUsuario(usuarioModelo.select(rs.getInt("id_usuario"))); //seleccuionar el usuario completo y de ahi el id
				prestamo.setFechaIni(rs.getDate("fecha_ini"));
				prestamo.setFechaFin(rs.getDate("fecha_fin"));
				prestamo.setEntregado(rs.getBoolean("entregado"));
				
				prestamos.add(prestamo); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return prestamos; 
		
	}
	
	public Prestamo selectPorLibroUsuario(Libro libro, Usuario usuario){
		
		Prestamo prestamo = new Prestamo(); 
		
		//cerar modelos de libro y usuario
		LibroModelo libroModelo = new LibroModelo(); 
		UsuarioModelo usuarioModelo = new UsuarioModelo(); 
		
		
		try {
			
			//hacer una consulta y seleccionar el prestamo que estamos buscando
			PreparedStatement pst = super.conexion.prepareStatement("select * from prestamos where id_libro = ? and id_usuario = ? and entregado = ?");	
			pst.setInt(1, libro.getId());
			pst.setInt(2,usuario.getId());
			pst.setBoolean(3, false);
			
			ResultSet rs = pst.executeQuery(); 
			
			if(rs.next()){ //se crea el prestamo y se meten los datos de ese prestamo para que nos salga por pantalla
				prestamo.setId(rs.getInt("id"));
				prestamo.setLibro(libroModelo.select(rs.getInt("id_libro")));
				prestamo.setUsuario(usuarioModelo.select(rs.getInt("id_usuario")));
				prestamo.setFechaIni(rs.getDate("fecha_ini"));
				prestamo.setFechaFin(rs.getDate("fecha_fin"));
				prestamo.setEntregado(rs.getBoolean("entregado")); 
				
				
				return prestamo; 
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null; 
	}
	
	
	//hacer update
	
	public void update(Prestamo prestamo){
		PreparedStatement pst;
		try {
			
			pst = super.conexion.prepareStatement("update prestamos set id_libro =?, id_usuario=?, fecha_ini = ?, fecha_fin = ?, entregado =? where id=?");
			pst.setInt(1, prestamo.getLibro().getId());
			pst.setInt(2, prestamo.getUsuario().getId());
			pst.setDate(3, new java.sql.Date (prestamo.getFechaIni().getTime()));
			pst.setDate(4, new java.sql.Date (prestamo.getFechaFin().getTime()));
			pst.setBoolean(5, prestamo.isEntregado()); 
			pst.setInt(6, prestamo.getId());
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
