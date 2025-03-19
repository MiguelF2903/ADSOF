package commit;

import java.time.*;
import java.util.UUID;

public abstract class Commit {
	private static int cont = 0;
	private String id;
	private String nombreAutor;
	private LocalDate fechaCreacion;
	private String descripcion;
	
	public Commit(String autor, String desc){
		this.id = generateId();
		this.nombreAutor = autor;
		this.descripcion = desc;
		this.fechaCreacion = LocalDate.now();
	}

	public Commit(String autor){
		this(autor, "no comment");
	}

	public Commit(){
		this("John Doe", "no comment");
	}

	public String generateId() {
		cont++;
		return String.format("%05d", cont) + UUID.randomUUID().toString().replace("-", "").substring(0, 15);
	}
	
	public String getId() {
        return id;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

	public boolean modifiesSameFile(Commit other) {
		if (other == null) return false;
		return this.getFileName().equals(other.getFileName());
	}
	
	public  String getFileName(){
		return "";
	}

	@Override
	public String toString(){
		return "commit " + this.id + "\n" + "Author: " + this.nombreAutor + "\n" + "Date: " + this.fechaCreacion
		+ "\n" + "Description: " + this.descripcion;
	}

}
