package com.arep.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.bson.Document;

import com.arep.model.Mensaje;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * 
 * @author SebastianPaez
 *
 */
public class DataBase {

	private MongoCollection<org.bson.Document> columnas;

	/**
	 * Realiza la conexion con la base de datos
	 */
	public DataBase() {
		// arep-mongo-db1 localhost
		MongoClientURI uri = new MongoClientURI(
				"mongodb://najoh2907:Prueba123%40@arep-mongo-db1:27017/?serverSelectionTimeoutMS=5000&connectTimeoutMS=10000&authSource=AREP-DOCKER-01&authMechanism=SCRAM-SHA-256&3t.uriVersion=3&3t.connection.name=AREP-DOCKER-01");
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase("AREP-DOCKER-01");
		columnas = database.getCollection("Mensajes");
	}

	/**
	 * Añade un nuevo mensaje a la base de datos
	 * 
	 * @param m Es el mensaje a añadir
	 */
	public void addMensaje(Mensaje m) {
		HashMap<String, Object> map = new HashMap<>();
		String mensaje = m.getMensaje();
		String fecha = m.getFecha();
		map.put("Mensaje", mensaje);
		map.put("Fecha", fecha);
		Document registro = new Document(map);
		columnas.insertOne(registro);
	}
	
	/**
	 * Consulta todos los mensaje de la base de datos
	 * @return
	 */
	public String getMensajes() {		
		Mensaje mensaje;
		int cont = 0;
		ArrayList<Mensaje> mensajes = new ArrayList<>();
		for (Document d : columnas.find()) {
			cont++;
			if (columnas.countDocuments() - cont < 10) {
				mensaje = new Mensaje(d.get("Mensaje").toString(), d.get("Fecha").toString());
				mensajes.add(mensaje);
			}
		}
		ArrayList<Mensaje> invertido = new ArrayList<>();
		for (int i = mensajes.size() - 1; i >= 0; i--) {
			invertido.add(mensajes.get(i));
		}
		Gson gson = new Gson();
		String json = gson.toJson(invertido);
		return json;		
	}

}
