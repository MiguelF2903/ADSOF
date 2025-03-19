package changes;

/**
 * Representa un cambio de tipo eliminación en un archivo.
 * <p>
 * Esta clase encapsula la información necesaria para representar un cambio que elimina
 * un rango de líneas de un archivo.
 * </p>
 */
public class RemoveChange extends Change {
    /** Línea inicial del rango de eliminación. */
    private int startLine;
    
    /** Línea final del rango de eliminación. */
    private int endLine;
    
    /** Ruta del archivo del que se eliminarán las líneas. */
    private String filePath;

    /**
     * Construye una nueva instancia de RemoveChange.
     *
     * @param startLine la línea inicial del rango que se eliminará
     * @param endLine la línea final del rango que se eliminará
     * @param filePath la ruta del archivo en el que se realizará la eliminación
     */
    public RemoveChange(int startLine, int endLine, String filePath) {
        super(startLine, filePath, '-');
        this.startLine = startLine;
        this.endLine = endLine;
        this.filePath = filePath;
    }

    /**
     * Retorna el tipo de cambio.
     * <p>
     * Este método utiliza la implementación de la clase base {@link Change}.
     * </p>
     *
     * @return el carácter que representa el tipo de cambio (en este caso, '-')
     */
    @Override
    public char getType(){
        return super.getType();
    }

    /**
     * Retorna la línea inicial del rango de eliminación.
     *
     * @return la línea desde la cual se inicia la eliminación
     */
    public int getStartLine(){
        return startLine;
    }

    /**
     * Retorna la línea final del rango de eliminación.
     *
     * @return la línea hasta la cual se realiza la eliminación
     */
    public int getEndLine(){
        return endLine;
    }

    /**
     * Retorna una representación en forma de cadena del número de cambios realizados.
     * <p>
     * Este método calcula el número de líneas eliminadas y lo retorna precedido por el signo '-'.
     * </p>
     *
     * @return una cadena en el formato "-N", donde N es el número de líneas eliminadas
     */
    @Override
    public String getNumberChanges(){
        return "-" + Integer.toString(this.getEndLine() - this.getStartLine() + 1);
    }
    
    /**
     * Retorna una representación en forma de cadena de este objeto RemoveChange.
     *
     * @return una cadena con los detalles del cambio, incluyendo el tipo, la línea inicial,
     *         la ruta del archivo y la línea final.
     */
    public String toString() {
        return "{\n type=" + super.getType() + ",\n start line=" + startLine
                + ",\n file path='" + filePath 
                + "',\n end line=" + endLine
                + "\n}";
    }
}
