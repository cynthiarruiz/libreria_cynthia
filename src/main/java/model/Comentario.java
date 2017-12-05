package model;

public class Comentario {
	int id;
	String nombre;
	String email;
	String asunto;
	String mensaje;


	
	public  Comentario(int id, String n, String e, String a, String m) {
		this.id = id;
		this.nombre = n;
		this.email = e;
		this.asunto = a;
		this.mensaje = m;
	}


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getAsunto() {
		return asunto;
	}



	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}



	public String getMensaje() {
		return mensaje;
	}



	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}

	