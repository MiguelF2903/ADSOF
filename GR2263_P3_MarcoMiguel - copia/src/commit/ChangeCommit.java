/**
 * Miguel Fernández Arco y Marco Vázquez Campos
*/ 
package commit;

import java.util.List;
import changes.Change;

/**
 * Representa un commit que agrupa una lista de cambios.
 * La clase ChangeCommit extiende de Commit y permite asociar múltiples
 * instancias de Change a un mismo commit. Esto facilita el manejo de
 * operaciones que involucran más de un cambio en el repositorio.
 */
public class ChangeCommit extends Commit {

    /** Lista de cambios asociados a este commit. */
    private List<Change> changes;

    /**
     * Construye un ChangeCommit con descripción, autor y lista de cambios.
     *
     * @param desc la descripción del commit
     * @param autor el autor del commit
     * @param changes la lista de cambios asociados a este commit
     */
    public ChangeCommit(String desc, String autor, List<Change> changes) {
        super(autor, desc);
        this.changes = changes;
    }

    /**
     * Construye un ChangeCommit con autor y lista de cambios.
     *
     * @param autor el autor del commit
     * @param changes la lista de cambios asociados a este commit
     */
    public ChangeCommit(String autor, List<Change> changes) {
        super(autor);
        this.changes = changes;
    }

    /**
     * Construye un ChangeCommit únicamente con una lista de cambios.
     *
     * @param changes la lista de cambios asociados a este commit
     */
    public ChangeCommit(List<Change> changes) {
        super();
        this.changes = changes;
    }

    /**
     * Retorna la lista de cambios asociados a este commit.
     *
     * @return la lista de Change de este commit
     */
    public List<Change> getChanges() {
        return this.changes;
    }

    /**
     * Retorna el nombre del archivo afectado por el commit.
     *
     * @return el nombre del archivo del primer cambio, o una cadena vacía si no hay cambios
     */
    @Override
    public String getFileName() {
        if (changes == null || changes.isEmpty()) {
            return "";
        }
        return changes.get(0).getFile();
    }

    /**
     * Retorna una representación en forma de cadena de este commit, incluyendo los cambios asociados.
     *
     * @return una cadena que describe el commit y sus cambios
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString() + "\n");
        for (Change c : changes) {
            sb.append(c.getType())
              .append(" : ")
              .append(c.getFile())
              .append(" (")
              .append(c.getNumberChanges())
              .append(")")
              .append("\n");
        }
        return sb.toString();
    }
}
