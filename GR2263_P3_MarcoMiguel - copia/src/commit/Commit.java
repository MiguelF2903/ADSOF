package commit;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Representa un commit abstracto en un sistema de control de versiones.
 * Esta clase define la estructura básica de un commit, que incluye un identificador único,
 * el nombre del autor, la fecha de creación y una descripción del commit.
 * Además, se proporciona un método para determinar si dos commits modifican el mismo archivo.
 */
public abstract class Commit {
    /** Contador estático para generar identificadores únicos. */
    private static int cont = 0;
    
    /** Identificador único del commit. */
    private String id;
    
    /** Nombre del autor del commit. */
    private String authorName;
    
    /** Fecha en la que se creó el commit. */
    private LocalDate creationDate;
    
    /** Descripción del commit. */
    private String description;
    
    /**
     * Crea un nuevo commit con el autor y la descripción especificados.
     * La fecha de creación se establece en la fecha actual.
     *
     * @param author el nombre del autor del commit
     * @param desc la descripción del commit
     */
    public Commit(String author, String desc) {
        this.id = generateId();
        this.authorName = author;
        this.description = desc;
        this.creationDate = LocalDate.now();
    }

    /**
     * Crea un nuevo commit con el autor especificado y una descripción por defecto ("no comment").
     *
     * @param autor el nombre del autor del commit
     */
    public Commit(String author) {
        this(author, "no comment");
    }

    /**
     * Crea un nuevo commit con valores por defecto
     */
    public Commit() {
        this("John Doe", "no comment");
    }

    /**
     * Genera un identificador único para el commit.
     * El identificador se compone de un número secuencial formateado a 5 dígitos,
     * seguido de una subcadena de 15 caracteres extraída de un UUID generado aleatoriamente.
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
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Retorna la fecha de creación del commit.
     *
     * @return la fecha en la que se creó el commit
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Retorna la descripción del commit.
     *
     * @return la descripción del commit
     */
    public String getDescription() {
        return description;
    }

    /**
     * Determina si este commit modifica el mismo archivo que otro commit.
     * Se compara el nombre del archivo obtenido
     * Si el otro commit es nulo, se retorna false.
     *
     * @param other el otro commit a comparar
     * @return true si ambos commits modifican el mismo archivo; de lo contrario, false
     */
    public boolean modifiesSameFile(Commit other) {
        if (other == null) return false;
        return this.getFileName().equals(other.getFileName());
    }
    
    /**
     * Retorna el nombre del archivo afectado por este commit.
     *
     * @return una cadena que representa el nombre del archivo modificado
     */
    public String getFileName() {
        return "";
    }

    /**
     * Retorna una representación en forma de cadena del commit.
     * La representación incluye el identificador, el autor, la fecha de creación y la descripción del commit.
     *
     * @return una cadena que representa el commit
     */
    @Override
    public String toString() {
        return "commit " + this.id + "\n" +
               "Author: " + this.authorName + "\n" +
               "Date: " + this.creationDate + "\n" +
               "Description: " + this.description;
    }
}
