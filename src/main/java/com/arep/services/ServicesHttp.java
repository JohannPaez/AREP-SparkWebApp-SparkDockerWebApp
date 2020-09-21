package com.arep.services;


import java.util.Date;

import javax.xml.ws.http.HTTPException;

import com.arep.model.Mensaje;
import com.google.gson.Gson;

import spark.Response;



/**
 * Clase encargada de ofrecer los servicios http 
 * @author SebastianPaez
 *
 */
public class ServicesHttp {
	
	private DataBase db;
	
	/**
	 * Crea una nueva instancia de la base de datos
	 */
	public ServicesHttp() {
		db = new DataBase();
	}
	
	/**
	 * Da una lista de los mensajes de la base de datos en formato JSON
	 * @return La lista de animales
	 */
	public String getMensajes() {
		return db.getMensajes();
	}
		
	
	/**
	 * Añade un mensaje dado su cadena en formato JSON
	 * @param jsonString Es la mensaje en formato strinJson a agregar a la base de datos.
	 * @throws HTTPException En caso de queel jsonString no sea válido
	 */
	public void addMensaje(String jsonString) throws HTTPException {
		Gson g = new Gson(); 
		Mensaje mensaje = g.fromJson(jsonString, Mensaje.class);		
		if (mensaje.getMensaje() == null || mensaje.getFecha() == null) throw new HTTPException(400);
		Date fecha = new Date();
		mensaje.setFecha(fecha.toString());
		System.out.println("Conversión del strinJson a objeto: \n" + mensaje.toString());
		db.addMensaje(mensaje);
	}	
	
}
