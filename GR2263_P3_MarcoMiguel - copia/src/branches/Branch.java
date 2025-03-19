package branches;

import commit.Commit;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una rama en un sistema de control de versiones.
 * Una instancia de esta clase mantiene una lista de commits y permite
 * crear nuevas ramas, ya sea vacías o derivadas de una rama existente.
 */
public class Branch {
    /** Nombre de la rama. */
    private String name;
    /** Lista de commits asociados a esta rama. */
    private List<Commit> commits;
    /** Origen de la rama.*/
    private Branch origin;
    
    /**
     * Crea una nueva rama con el nombre especificado.
     *
     * @param nombre el nombre de la nueva rama
     */
    public Branch(String name) {
        this.name = name;
        this.commits = new ArrayList<>();
        this.origin = null;
    }
    
    /**
     * Crea una nueva rama a partir de una rama existente (rama origen).
     * La nueva rama se inicializa con todos los commits de la rama origen.
     *
     * @param nombre el nombre de la nueva rama
     * @param ramaOrigen la rama de la cual se copiarán los commits
     */
    public Branch(String name, Branch sourceBranch) {
        this(name);
        this.commits.addAll(sourceBranch.getCommits());
        this.origin = sourceBranch;
    }
    
    /**
     * Agrega un commit a la rama.
     *
     * @param commit el commit a agregar
     */
    public void commit(Commit commit) {
        commits.add(commit);
    }

    /**
     * Retorna el commit que coincide con el identificador proporcionado.
     *
     * @param id el identificador del commit a buscar
     * @return el commit con el id especificado, o null si no se encuentra
     */
    public Commit getCommit(String id) {
        for (Commit commit : commits) {
            if (commit.getId().equals(id)) {
                return commit;
            }
        }
        return null;
    }
    
    /**
     * Retorna la lista de commits asociados a esta rama.
     *
     * @return la lista de commits
     */
    public List<Commit> getCommits() {
        return commits;
    }

    /**
     * Retorna el nombre de la rama.
     *
     * @return el nombre de la rama
     */
    public String getName() {
        return name;
    }
    
    /**
     * Retorna una representación en forma de cadena de la rama.
     *
     * @return una cadena que representa el estado de la rama
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Branch: ").append(name);
        if (origin != null) {
            sb.append(" (from ").append(origin.getName()).append(")");
        }
        sb.append("\n").append(commits.size()).append(" commits:\n");
        for (Commit commit : commits) {
            String idResumen = commit.getId().substring(0, 5);
            String desc = commit.getDescription();
            String fecha = commit.getCreationDate().toString();
            sb.append(idResumen).append(" - ").append(desc).append(" at ").append(fecha).append("\n");
        }
        return sb.toString();
    }
}
