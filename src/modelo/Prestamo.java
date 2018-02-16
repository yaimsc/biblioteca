package modelo;

import java.util.Date;

public class Prestamo {
	
	//atributos

	private int id; 
	private int idLibro; 
	private int idUsuario; 
	private Date fechaIni; 
	private Date fechaFin;
	private boolean entregado;
	
	//getters & setters
	
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
	 * @return the id_libro
	 */
	public int getId_libro() {
		return idLibro;
	}
	/**
	 * @param id_libro the id_libro to set
	 */
	public void setId_libro(int id_libro) {
		this.idLibro = id_libro;
	}
	/**
	 * @return the id_usuario
	 */
	public int getId_usuario() {
		return idUsuario;
	}
	/**
	 * @param id_usuario the id_usuario to set
	 */
	public void setId_usuario(int id_usuario) {
		this.idUsuario = id_usuario;
	}
	/**
	 * @return the fechaIni
	 */
	public Date getFechaIni() {
		return fechaIni;
	}
	/**
	 * @param fechaIni the fechaIni to set
	 */
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}
	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the entregado
	 */
	public boolean isEntregado() {
		return entregado;
	}
	/**
	 * @param entregado the entregado to set
	 */
	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	} 
	
	

	
	
	
}
