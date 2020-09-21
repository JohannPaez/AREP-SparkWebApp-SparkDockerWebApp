package com.arep.model;

import java.util.Date;

/**
 * Clase mensaje que guardará la información de los registros de la base de datos
 * @author SebastianPaez
 *
 */
public class Mensaje {
	private String mensaje;
	private String fecha;
	
	/**
	 * Crea un nuevo Mensaje con la descripción y la fecha de creación
	 * @param mensaje
	 * @param fecha
	 */
	public Mensaje(String mensaje, String fecha) {
		setMensaje(mensaje);
		setFecha(fecha);
	}

	/**
	 * Retorna el mensaje
	 * @return mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Cambia el contenido del mensaje
	 * @param mensaje Es el nuevo contenido del mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Retorna la fecha
	 * @return fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Cambia el contenido de la fecha
	 * @param fecha Es la nueva fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * Retorna el contenido del mensaje en formato JSON
	 */
	public String toString() {
		return "{\"mensaje\": " + "\"" + mensaje +  "\"" + ", \"fecha\": " + "\"" + fecha  +  "\"" + "}";
	}
}
