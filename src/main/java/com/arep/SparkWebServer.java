package com.arep;


import static spark.Spark.*;

import javax.xml.ws.http.HTTPException;

import com.arep.services.ServicesHttp;

/**
 * 
 * @author SebastianPaez
 *
 */
public class SparkWebServer {

	
	/**
	 * Ejecuta la aplicación para poder utilizar los servicios
	 * @param args Son los parametros al momento de ejecutar.
	 */
	public static void main(String... args) {
		ServicesHttp servicesHttp = new ServicesHttp();
		port(getPort());
		get("hello", (req, res) -> "Hello Docker!");
		get("/mensajes", (req, res) -> {
			System.out.println("Peticion get /mensajes");
			return servicesHttp.getMensajes();
		});
		post("/addMensaje", (request, response) -> {			
			String body = request.body();
			System.out.println("BODY \n" + body);
			String res = "El mensaje ha sido agregado con exito!";
			try {
				servicesHttp.addMensaje(body);
			} catch (HTTPException e) {
				response.status(400);
				res = "Error, esa petición no es válida!";
			} catch (Exception e) { 
				response.status(400);
				res = "Se esperaba un stringJson, el formato no es válido!";
			}
			return res;
		});
		
	}

	/**
	 * Funcion que retorna el número del puerto por el cual se correrá el servicio.
	 * @return El número de puerto del servicio.
	 */
	private static int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 4567;
	}

}