/**
 * Miguel Fernández Arco y Marco Vázquez Campos
*/ 
package commit;

import java.util.List;

/**
 * Representa un commit de fusión que combina varios commits.
 * La clase MergeCommit extiende la clase abstracta Commit y permite fusionar
 * múltiples commits en uno solo. Se almacena una lista de commits que han sido fusionados, lo
 * cual facilita el seguimiento de la historia de fusión en un sistema de control de versiones.
 */
public class MergeCommit extends Commit {

    /** Lista de commits que han sido fusionados en este merge commit. */
    private List<Commit> commits;

    /**
     * Crea un nuevo MergeCommit con una descripción, autor y lista de commits a fusionar.
     *
     * @param desc    la descripción del merge commit
     * @param author   el autor del merge commit
     * @param commits la lista de commits que se van a fusionar
     */
    public MergeCommit(String desc, String author, List<Commit> commits) {
        super(author, desc);
        this.commits = commits;
    }

    /**
     * Crea un nuevo MergeCommit con un autor y una lista de commits a fusionar,
     * utilizando una descripción por defecto.
     *
     * @param author   el autor del merge commit
     * @param commits la lista de commits que se van a fusionar
     */
    public MergeCommit(String author, List<Commit> commits) {
        super(author);
        this.commits = commits;
    }

    /**
     * Crea un nuevo MergeCommit únicamente a partir de una lista de commits a fusionar.
     * Se utilizan valores por defecto para el autor y la descripción.
     *
     * @param commits la lista de commits que se van a fusionar
     */
    public MergeCommit(List<Commit> commits) {
        super();
        this.commits = commits;
    }

    /**
     * Retorna la lista de commits fusionados.
     *
     * @return la lista de Commit fusionados en este merge commit
     */
    public List<Commit> getcommits() {
        return this.commits;
    }

    /**
     * Retorna el nombre del archivo afectado por este merge commit.
     * Se devuelve el nombre del archivo del primer commit fusionado. Si la lista de commits es nula
     * o está vacía, se retorna una cadena vacía.
     *
     * @return el nombre del archivo del primer commit fusionado, o una cadena vacía si no hay commits
     */
    @Override
    public String getFileName() {
        if (commits == null || commits.isEmpty()) {
            return "";
        }
        return commits.get(0).getFileName();
    }

    /**
     * Retorna una representación en forma de cadena del merge commit, incluyendo la información
     * de los commits fusionados.
     * La representación incluye la información básica del commit (como id, autor, fecha y descripción)
     * seguida de una lista de los identificadores y fechas de creación de cada commit fusionado.
     *
     * @return una cadena que representa el merge commit y sus commits fusionados
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString() + "\n");
        sb.append("Merged commits:\n");
        for (Commit c : commits) {
            sb.append(c.getId())
              .append(" on ")
              .append(c.getCreationDate())
              .append("\n");
        }
        return sb.toString();
    }
}
