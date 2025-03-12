package repositories;
import branches.*;
import commit.Commit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

public class Repository {
    private String nombre;
    private Map<String, Branch> ramas;
    private Branch ramaActiva;
    private Set<String> usuarios; // Usuarios con permiso para realizar commits

    public Repository(String nombre) {
        this.nombre = nombre;
        // Usamos LinkedHashMap para preservar el orden de inserción de las ramas
        ramas = new LinkedHashMap<>();
        usuarios = new HashSet<>();
        // Se crea automáticamente la rama "main" y se establece como activa
        Branch main = new Branch("main");
        ramas.put("main", main);
        ramaActiva = main;
    }
    
    // Agrega un usuario con permiso para realizar commits.
    public void addUser(String usuario) {
        usuarios.add(usuario);
    }
    
    // Crea una nueva rama a partir de una rama existente.
    public void createBranch(String nombreNueva, String nombreOrigen) {
        Branch origen = ramas.get(nombreOrigen);
        if (origen == null) {
            throw new IllegalArgumentException("La rama origen no existe: " + nombreOrigen);
        }
        Branch nueva = new Branch(nombreNueva, origen);
        ramas.put(nombreNueva, nueva);
    }
    
    // Permite cambiar la rama activa (no se utiliza en este tester, pero se incluye para completar la funcionalidad).
    public void changeActiveBranch(String nombreRama) {
        Branch rama = ramas.get(nombreRama);
        if (rama == null) {
            throw new IllegalArgumentException("La rama no existe: " + nombreRama);
        }
        ramaActiva = rama;
    }
    
    // Realiza un commit en la rama activa, siempre que el usuario tenga permiso.
    public void commit(Commit commit) {
        if (!usuarios.contains(commit.getNombreAutor())) {
            System.out.println("El usuario " + commit.getNombreAutor() + " no tiene permiso para realizar commits.");
            return;
        }
        ramaActiva.commit(commit);
    }
    
    // Imprime el repositorio mostrando la lista de ramas (marcando la activa con un asterisco)
    // y a continuación el detalle de la rama activa.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Repository: " + this.nombre + "\n");
        sb.append("Branches:\n");
        for (String nombreRama : ramas.keySet()) {
            Branch branch = ramas.get(nombreRama);
            if (branch == ramaActiva) {
                sb.append("- ").append(branch.getNombre()).append(" (active) ").append("\n");
            } else {
                sb.append("- ").append(branch.getNombre());
            }
        }
        sb.append("\n");
        sb.append(ramaActiva.toString());
        return sb.toString();
    }
}
