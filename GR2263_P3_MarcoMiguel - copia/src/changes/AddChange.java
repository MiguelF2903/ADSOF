package changes;

/**
 * Representa un cambio de tipo "añadir" en un archivo.
 * Esta clase extiende Change y se utiliza para encapsular la información
 * de un cambio que agrega contenido a un archivo en una posición determinada.
 */
public class AddChange extends Change {
    /** Contenido que se agregará al archivo. */
    private String content;
    
    /** Línea de inicio en la que se realizará la adición. */
    private int startLine;
    
    /** Ruta del archivo donde se aplicará el cambio. */
    private String filePath;

    /**
     * Construye una nueva instancia de AddChange.
     *
     * @param startLine la línea en la que se empezará a añadir el contenido (antes de esa línea)
     * @param filePath la ruta del archivo en el que se aplicará el cambio
     * @param content el contenido que se agregará al archivo
     */
    public AddChange(int startLine, String filePath, String content) {
        super(startLine, filePath, '+');
        this.startLine = startLine;
        this.filePath = filePath;
        this.content = content;
    }

    /**
     * Retorna el número de líneas de contenido a agregar, precedido por el signo '+'.
     *
     * @return una cadena con el formato "+N", donde N es el número de líneas del contenido.
     */
    @Override
    public String getNumberChanges(){
        return "+" + Integer.toString(this.getContentLines());
    }
    
    /**
     * Calcula el número de líneas presentes en el contenido.
     * Si el contenido es nulo o está vacío, retorna 0.
     *
     * @return el número de líneas en el contenido
     */
    public int getContentLines() {
        if(content == null || content.isEmpty()) {
             return 0;
        }
        int count = 1;
        for (char c : content.toCharArray()) {
             if (c == '\n') count++;
        }
        return count;
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
     * Retorna una representación en forma de cadena de este objeto AddChange.
     *
     * @return una cadena con los detalles del cambio, incluyendo tipo, línea de inicio,
     *         ruta del archivo, contenido y número de líneas añadidas.
     */
    public String toString() {
    	return "{\n type=" + super.getType() + ",\n start line=" + startLine
    			+ ",\n file path='" + filePath
    			+ "',\n content='" + content
    			+ "',\n number of lines=" + getContentLines()
    			+ "\n}";
    }

}
