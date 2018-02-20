package modelo;

import java.util.ArrayList;

public class Libro {
	
	/**
	 * atributos
	 */
	protected int id; 
	protected String titulo;
	protected String autor;
	
	private ArrayList <Prestamo> prestamos; 
	
	
	/**
	 * getters y setters
	 */
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * @return the autor
	 */
	public String getAutor() {
		return autor;
	}
	/**
	 * @param autor the autor to set
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	} 
	
	
	
	
}
