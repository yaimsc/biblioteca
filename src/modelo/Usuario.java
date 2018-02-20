package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {

	private int id; 
	private String nombre; 
	private String apellido; 
	private int edad;
	private String dni; 
	private Date fecha_nac; 
	
	private ArrayList <Usuario> usuarios; 
	
	
	//GETTERS & SETTERS
	
	
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}
	/**
	 * @param edad the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}
	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}
	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	/**
	 * @return the fecha_nac
	 */
	public Date getFecha_nac() {
		return fecha_nac;
	}
	/**
	 * @param date the fecha_nac to set
	 */
	public void setFecha_nac(Date date) {
		this.fecha_nac = date;
	} 
	
	
	
	
	
}
