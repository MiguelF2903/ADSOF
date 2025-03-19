package commit;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Representa un commit abstracto en un sistema de control de versiones.
 * <p>
 * Esta clase define la estructura básica de un commit, que incluye un identificador único,
 * el nombre del autor, la fecha de creación y una descripción del commit.
 * Además, se proporciona un método para determinar si dos commits modifican el mismo archivo.
 * </p>
 */
public abstract class Commit {
    /** Contador estático para generar identificadores únicos. */
    private static int cont = 0;
    
    /** Identificador único del commit. */
    private String id;
    
    /** Nombre del autor del commit. */
    private String nombreAutor;
    
    /** Fecha en la que se creó el commit. */
    private LocalDate fechaCreacion;
    
    /** Descripción del commit. */
    private String descripcion;
    
    /**
     * Crea un nuevo commit con el autor y la descripción especificados.
     * La fecha de creación se establece en la fecha actual.
     *
     * @param autor el nombre del autor del commit
     * @param desc la descripción del commit
     */
    public Commit(String autor, String desc) {
        this.id = generateId();
        this.nombreAutor = autor;
        this.descripcion = desc;
        this.fechaCreacion = LocalDate.now();
    }

    /**
     * Crea un nuevo commit con el autor especificado y una descripción por defecto ("no comment").
     *
     * @param autor el nombre del autor del commit
     */
    public Commit(String autor) {
        this(autor, "no comment");
    }

    /**
     * Crea un nuevo commit con valores por defecto ("John Doe" como autor y "no comment" como descripción).
     */
    public Commit() {
        this("John Doe", "no comment");
    }

    /**
     * Genera un identificador único para el commit.
     * <p>
     * El identificador se compone de un número secuencial formateado a 5 dígitos,
     * seguido de una subcadena de 15 caracteres extraída de un UUID generado aleatoriamente.
     * </p>
     *
     * @return un String que representa el identificador único del commit
     */
    public String generateId() {
        cont++;
        return String.format("%05d", cont) + UUID.randomUUID().toString().replace("-", "").substring(0, 15);
    }
    
    /**
     * Retorna el identificador del commit.
     *
     * @return el identificador del commit
     */
    public String getId() {
        return id;
    }

    /**
     * Retorna el nombre del autor del commit.
     *
     * @return el nombre del autor
     */
    public String getNombreAutor() {
        return nombreAutor;
    }

    /**
     * Retorna la fecha de creación del commit.
     *
     * @return la fecha en la que se creó el commit
     */
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Retorna la descripción del commit.
     *
     * @return la descripción del commit
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Determina si este commit modifica el mismo archivo que otro commit.
     * <p>
     * Se compara el nombre del archivo obtenido mediante {@link #getFileName()}.
     * Si el otro commit es nulo, se retorna false.
     * </p>
     *
     * @param other el otro commit a comparar
     * @return {@code true} si ambos commits modifican el mismo archivo; de lo contrario, {@code false}
     */
    public boolean modifiesSameFile(Commit other) {
        if (other == null) return false;
        return this.getFileName().equals(other.getFileName());
    }
    
    /**
     * Retorna el nombre del archivo afectado por este commit.
     * <p>
     * La implementación por defecto retorna una cadena vacía.
     * Se recomienda sobrescribir este método en las subclases cuando se requiera especificar
     * el archivo modificado.
     * </p>
     *
     * @return una cadena que representa el nombre del archivo modificado
     */
    public String getFileName() {
        return "";
    }

    /**
     * Retorna una representación en forma de cadena del commit.
     * <p>
     * La representación incluye el identificador, el autor, la fecha de creación y la descripción del commit.
     * </p>
     *
     * @return una cadena que representa el commit
     */
    @Override
    public String toString() {
        return "commit " + this.id + "\n" +
               "Author: " + this.nombreAutor + "\n" +
               "Date: " + this.fechaCreacion + "\n" +
               "Description: " + this.descripcion;
    }
}
