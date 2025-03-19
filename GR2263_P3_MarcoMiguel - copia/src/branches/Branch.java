package branches;

import commit.Commit;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una rama en un sistema de control de versiones.
 * <p>
 * Una instancia de esta clase mantiene una lista de commits y permite
 * crear nuevas ramas, ya sea vacías o derivadas de una rama existente.
 * </p>
 */
public class Branch {
    /** Nombre de la rama. */
    private String nombre;
    /** Lista de commits asociados a esta rama. */
    private List<Commit> commits;
    
    /**
     * Crea una nueva rama con el nombre especificado.
     *
     * @param nombre el nombre de la nueva rama
     */
    public Branch(String nombre) {
        this.nombre = nombre;
        this.commits = new ArrayList<>();
    }
    
    /**
     * Crea una nueva rama a partir de una rama existente (rama origen).
     * <p>
     * La nueva rama se inicializa con todos los commits de la rama origen.
     * </p>
     *
     * @param nombre el nombre de la nueva rama
     * @param ramaOrigen la rama de la cual se copiarán los commits
     */
    public Branch(String nombre, Branch ramaOrigen) {
        this(nombre);
        this.commits.addAll(ramaOrigen.getCommits());
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
     * @return el commit con el id especificado, o {@code null} si no se encuentra
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
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Retorna una representación en forma de cadena de la rama.
     * <p>
     * Se muestra el nombre de la rama, el número de commits y un resumen de cada commit.
     * El resumen de cada commit incluye los primeros 5 caracteres del identificador,
     * la descripción y la fecha de creación.
     * </p>
     *
     * @return una cadena que representa el estado de la rama
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Branch: ").append(nombre).append("\n");
        sb.append(commits.size()).append(" commits:\n");
        for (Commit commit : commits) {
            String idResumen = commit.getId().substring(0, 5);
            String desc = commit.getDescripcion();
            String fecha = commit.getFechaCreacion().toString();
            sb.append(idResumen).append(" - ").append(desc).append(" at ").append(fecha).append("\n");
        }
        return sb.toString();
    }
}
