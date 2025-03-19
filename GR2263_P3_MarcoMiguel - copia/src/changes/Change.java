package changes;

/**
 * Representa un cambio realizado en un archivo.
 * <p>
 * Esta clase abstracta define la estructura básica para describir
 * un cambio, incluyendo la línea donde se aplica, la ruta del archivo
 * y el tipo de cambio (por ejemplo, '+' para adición, '-' para eliminación, etc.).
 * </p>
 */
public abstract class Change {
    /** Ruta del archivo donde se aplica el cambio. */
    private String file;
    
    /** Línea en la que se aplica el cambio. */
    private int line;
    
    /** Tipo de cambio representado por un carácter. */
    private char type;
    
    /**
     * Construye una nueva instancia de Change.
     *
     * @param line la línea en la que se aplica el cambio
     * @param file la ruta del archivo donde se realiza el cambio
     * @param type el tipo de cambio
     */
    public Change(int line, String file, char type) {
        this.line = line;
        this.file = file;
        this.type = type;
    }
    
    /**
     * Retorna la ruta del archivo donde se aplica el cambio.
     *
     * @return la ruta del archivo
     */
    public String getFile() {
        return this.file;
    }
    
    /**
     * Retorna la línea en la que se aplica el cambio.
     *
     * @return el número de línea
     */
    public int getLine(){
        return this.line;
    }
    
    /**
     * Retorna el tipo de cambio.
     *
     * @return el carácter que representa el tipo de cambio
     */
    public char getType(){
        return this.type;
    }
    
    /**
     * Retorna una representación en forma de cadena del número de cambios realizados.
     * <p>
     * Este método se espera que sea sobrescrito en las subclases para proporcionar
     * una descripción específica del cambio. Por defecto, retorna {@code null}.
     * </p>
     *
     * @return una cadena representando el número de cambios o {@code null} si no se implementa.
     */
    public String getNumberChanges(){
        return null;
    }
}
