package modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Array;

public class LibroModelo extends Conector {
	
	public ArrayList<Libro> selectAll(){
		
		ArrayList<Libro> libros = new ArrayList<Libro>();
		try {
			
			Statement st = super.conexion.createStatement();
			ResultSet rs = st.executeQuery("select * from libros"); //ejecutar la tabla libros de la base de datos biblioteca
			
			while(rs.next()){ //que cree un libro con datos en el array
				Libro nuevo = new Libro(); 
				
				nuevo.setId(rs.getInt("id"));
				nuevo.setTitulo(rs.getString("titulo"));
				nuevo.setAutor(rs.getString("autor"));
				
				libros.add(nuevo); //que lo añada al array
			} 
			
			return libros; 
			
		} catch (SQLException e) {
			System.err.println("Error de SQL");
			e.printStackTrace();
			
		}return libros; //que lo devuelva
	}
	
	public Libro select(int id){
		Libro libro1 = new Libro(); 
		
		try {
			
			PreparedStatement pst = super.conexion.prepareStatement("select * from libros where id = ?");
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery(); //seleccionar lis libros con id
			
			while(rs.next()){ //dar un valor al libro
				libro1.setId(rs.getInt("id"));
				libro1.setTitulo(rs.getString("titulo"));
				libro1.setAutor(rs.getString("autor"));
				
			}return libro1; //devolver el libro
			
		} catch (SQLException e) {
			System.err.println("Error de SQL");
			e.printStackTrace();
		}return null; 
	}
	
	public Libro select(String titulo){
		Libro libro2 = new Libro(); //crear libro
		try {
			
			PreparedStatement pst = super.conexion.prepareStatement("select * from libros where titulo = ?"); 
			pst.setString(1, titulo);
			
			ResultSet rs = pst.executeQuery(); //seleccionar libros desde el titulo
			
			//si hemos reciubido alguna fila
			if (rs.next()){
				libro2.setId(rs.getInt("id"));
				libro2.setTitulo(rs.getString("titulo")); //dar un valor al libro
				libro2.setAutor(rs.getString("autor"));
			}
			
			return libro2; //retornar libro
			
		} catch (SQLException e) {
			System.err.println("Error de SQL");
			e.printStackTrace();
		} return null; 
	}
	
	public void update(int id){
		try {
			
			PreparedStatement pst = super.conexion.prepareStatement("UPDATE libros SET id = ?, titulo = ?,autor= ? WHERE id = ?");
			
			pst.setInt(1, id);
			pst.setString(2, "titulo");
			pst.setString(3, "autor");
			
			pst.executeUpdate(); 
			
		} catch (SQLException e) {
			System.err.println("Error de SQL");
			e.printStackTrace();
		}
		
	}
	
	public void delete(int id){
		
		try {
			PreparedStatement pst = super.conexion.prepareStatement("delete from libros where id = ?");
			
			pst.setInt(1, id);
		} catch (SQLException e) {
			System.err.println("Error de SQL");
			e.printStackTrace();
		} 
	}
	public void insert(Libro libro){
		try {
			
			PreparedStatement pst = super.conexion.prepareStatement("INSERT INTO libros (titulo, autor) VALUES (?,?)");
			
			pst.setString(1, libro.getTitulo());
			pst.setString(2, libro.getAutor());
			
			pst.execute(); 
			
		} catch (SQLException e) {
			System.out.println("fallo de SQL"); 
			e.printStackTrace();
		} 
	}

}
