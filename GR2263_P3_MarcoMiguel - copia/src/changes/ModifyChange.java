/**
 * Miguel Fernández Arco y Marco Vázquez Campos
*/ 
package changes;

/**
 * Representa un cambio de tipo modificación en un archivo.
 * Esta clase encapsula la información necesaria para representar un cambio que modifica
 * el contenido existente en un rango de líneas de un archivo.
 */
public class ModifyChange extends Change {
    /** Contenido del cambio a aplicar. */
    private String change;
    
    /** Línea inicial del rango de modificación. */
    private int startLine;
    
    /** Línea final del rango de modificación. */
    private int endLine;
    
    /** Ruta del archivo que se modificará. */
    private String filePath;

    /**
     * Construye una nueva instancia de ModifyChange.
     *
     * @param startLine la línea inicial donde se inicia la modificación
     * @param endLine la línea final donde termina la modificación
     * @param filePath la ruta del archivo a modificar
     * @param change el contenido que se utilizará para reemplazar el existente
     */
    public ModifyChange(int startLine, int endLine, String filePath, String change) {
        super(startLine, filePath, '/');
        this.startLine = startLine;
        this.endLine = endLine;
        this.filePath = filePath;
        this.change = change;
    }

    /**
     * Retorna el tipo de cambio.
     *
     * @return el carácter que representa el tipo de cambio
     */
    @Override
    public char getType(){
        return super.getType();
    }

    /**
     * Retorna la línea inicial del rango de modificación.
     *
     * @return la línea donde inicia la modificación
     */
    public int getStartLine(){
        return startLine;
    }

    /**
     * Retorna la línea final del rango de modificación.
     *
     * @return la línea donde termina la modificación
     */
    public int getEndLine(){
        return endLine;
    }

    /**
     * Retorna una representación en forma de cadena del número de cambios realizados.
     *
     * @return la cadena "0"
     */
    @Override
    public String getNumberChanges(){
        return "0";
    }

    /**
     * Retorna una representación en forma de cadena de este objeto ModifyChange.
     *
     * @return una cadena con los detalles del cambio, incluyendo tipo, línea de inicio,
     *         ruta del archivo, contenido del cambio, número de líneas afectadas y la línea final.
     */
    @Override
    public String toString() {
        return "{\n type=" + super.getType() + ",\n start line=" + startLine 
            + ",\n file path='" + filePath + "'" 
            + ",\n content='" + change + "'" 
            + ",\n number of lines=" + (endLine - startLine + 1) 
            + ",\n end line=" + endLine
            + "\n}";
    }
}
