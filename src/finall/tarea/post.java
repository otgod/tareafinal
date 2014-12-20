package finall.tarea;



public class post {
	private String titulo, enlace,puntos,imagen;
	//

	public post() {
	}

	public post(String titulo, String enlace, String puntos, String imagen,
			String genre) {
		this.titulo = titulo;
		this.enlace = enlace;
		
		this.puntos = puntos;
		this.imagen = imagen;
	}

	public String getTitle() {
		return titulo;
	}

	public void setTitle(String name) {
		this.titulo = name;
	}

	public String getThumbnailUrl() {
		return enlace;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.enlace = thumbnailUrl;
	}

	

	public String getpuntos() {
		return puntos;
	}

	public void setpuntos(String string) {
		this.puntos = string;
	}

	

}
