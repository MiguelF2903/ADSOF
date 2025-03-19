package repositories;

import branches.*;
import commit.Commit;
import java.util.*;
import strategies.MergeStrategy;
import commit.MergeCommit;

/**
 * Representa un repositorio que gestiona ramas, commits y usuarios.
 * <p>
 * Esta clase permite crear ramas, realizar commits y fusionar ramas utilizando
 * una estrategia de merge por defecto. Los usuarios autorizados pueden hacer commits
 * en el repositorio.
 * </p>
 */
public class Repository {
    /** Nombre del repositorio. */
    private String name;
    /** Mapa de ramas, indexado por el nombre de la rama. */
    private Map<String, Branch> branches;
    /** Rama activa en el repositorio. */
    private Branch activeBranch;
    /** Conjunto de usuarios autorizados a realizar commits. */
    private Set<String> users;
    /** Estrategia de merge por defecto para resolver conflictos al fusionar ramas. */
    private MergeStrategy defaultStrategy;

    /**
     * Crea un nuevo repositorio con el nombre especificado.
     * <p>
     * Se inicializa la rama principal ("main") como la rama activa y se establece
     * la estrategia de merge por defecto.
     * </p>
     *
     * @param name el nombre del repositorio
     */
    public Repository(String name) {
        this.name = name;
        branches = new LinkedHashMap<>();
        users = new HashSet<>();
        Branch main = new Branch("main");
        branches.put("main", main);
        activeBranch = main;
        this.defaultStrategy = new MergeStrategy(MergeStrategy.STRATEGY_NULL);
    }

    /**
     * Establece la estrategia de merge por defecto para el repositorio.
     *
     * @param strategy la estrategia de merge a utilizar
     */
    public void setDefaultStrategy(MergeStrategy strategy) {
        this.defaultStrategy = strategy;
    }
    
    /**
     * Agrega un usuario autorizado al repositorio.
     *
     * @param user el nombre del usuario a agregar
     */
    public void addUser(String user) {
        users.add(user);
    }
    
    /**
     * Crea una nueva rama a partir de una rama existente.
     * <p>
     * Si la rama origin no existe, se imprime un mensaje y no se crea la nueva rama.
     * </p>
     *
     * @param nameNew el nombre de la nueva rama
     * @param nameOrigin el nombre de la rama origin
     */
    public void createBranch(String nameNew, String nameOrigin) {
        Branch origin = branches.get(nameOrigin);
        if (origin == null) {
            System.out.println("La rama origin no existe: " + nameOrigin);
            return;
        }
        Branch newB = new Branch(nameNew, origin);
        branches.put(nameNew, newB);
    }
    
    /**
     * Cambia la rama activa del repositorio.
     * <p>
     * Si la rama especificada no existe, se imprime un mensaje y no se cambia la rama activa.
     * </p>
     *
     * @param nameBranch el nombre de la rama a activar
     */
    public void changeActiveBranch(String nameBranch) {
        Branch branch = branches.get(nameBranch);
        if (branch == null) {
            System.out.println("La rama no existe: " + nameBranch);
            return;
        }
        activeBranch = branch;
    }
    
    /**
     * Realiza un commit en la rama activa del repositorio.
     * <p>
     * Si el autor del commit no está autorizado, se imprime un mensaje y el commit no se realiza.
     * </p>
     *
     * @param commit el commit a agregar a la rama activa
     */
    public void commit(Commit commit) {
        if (!users.contains(commit.getNombreAutor())) {
            System.out.println("El usuario " + commit.getNombreAutor() + " no tiene permiso para realizar commits.");
            return;
        }
        activeBranch.commit(commit);
    }
    
    /**
     * Fusiona dos ramas utilizando la estrategia de merge por defecto.
     *
     * @param sourceBranchName el nombre de la rama fuente que se va a fusionar
     * @param targetBranchName el nombre de la rama destino en la que se fusionará la fuente
     * @return una lista de conflictos encontrados durante la fusión, o {@code null} si no se pudo fusionar
     */
    public List<String> merge(String sourceBranchName, String targetBranchName) {
        return merge(sourceBranchName, targetBranchName, defaultStrategy);
    }

    /**
     * Fusiona dos ramas utilizando la estrategia de merge especificada.
     *
     * @param sourceBranchName el nombre de la rama fuente que se va a fusionar
     * @param targetBranchName el nombre de la rama destino en la que se fusionará la fuente
     * @param strategy la estrategia de merge a utilizar para resolver conflictos
     * @return una lista de conflictos encontrados durante la fusión, o {@code null} si no se pudo fusionar
     */
    public List<String> merge(String sourceBranchName, String targetBranchName, MergeStrategy strategy) {
        Branch sourceBranch = branches.get(sourceBranchName);
        Branch targetBranch = branches.get(targetBranchName);
        
        if (sourceBranch == null || targetBranch == null) {
            return null;
        }
        
        Commit commonCommit = findCommonCommit(sourceBranch, targetBranch);
        if (commonCommit == null) {
            return null;
        }
        
        List<Commit> newCommits = getNewCommits(sourceBranch, commonCommit);
        List<String> conflicts = findConflicts(newCommits, targetBranch, commonCommit, strategy);
        
        if (conflicts.isEmpty()) {
            MergeCommit mergeCommit = new MergeCommit("Merge branches " + targetBranchName + 
                " and " + sourceBranchName, "system", newCommits);
            targetBranch.commit(mergeCommit);
        }
        
        return conflicts;
    }
    
    /**
     * Busca el commit común más reciente entre dos ramas.
     *
     * @param source la rama fuente
     * @param target la rama destino
     * @return el commit común encontrado, o {@code null} si no hay ningún commit común
     */
    private Commit findCommonCommit(Branch source, Branch target) {
        for (Commit sourceCommit : source.getCommits()) {
            for (Commit targetCommit : target.getCommits()) {
                if (sourceCommit.getId().equals(targetCommit.getId())) {
                    return sourceCommit;
                }
            }
        }
        return null;
    }
    
    /**
     * Obtiene la lista de commits nuevos en una rama a partir de un commit común.
     *
     * @param branch la rama de la que se obtendrán los nuevos commits
     * @param commonCommit el commit común que marca el inicio de los nuevos commits
     * @return una lista de commits nuevos posteriores al commit común
     */
    private List<Commit> getNewCommits(Branch branch, Commit commonCommit) {
        List<Commit> result = new ArrayList<>();
        List<Commit> commits = branch.getCommits();
        boolean foundCommon = false;
        
        for (Commit commit : commits) {
            if (foundCommon) {
                result.add(commit);
            }
            if (commit.getId().equals(commonCommit.getId())) {
                foundCommon = true;
            }
        }
        
        return result;
    }
    
    /**
     * Busca conflictos de merge entre los commits nuevos y la rama destino.
     *
     * @param newCommits la lista de nuevos commits de la rama fuente
     * @param targetBranch la rama destino en la que se buscarán conflictos
     * @param commonCommit el commit común entre ambas ramas
     * @param strategy la estrategia de merge a utilizar para resolver conflictos
     * @return una lista de descripciones de conflictos encontrados durante la fusión
     */
    private List<String> findConflicts(List<Commit> newCommits, Branch targetBranch, Commit commonCommit, MergeStrategy strategy) {
        List<String> conflicts = new ArrayList<>();
        List<Commit> targetCommits = targetBranch.getCommits();
        int startIndex = targetCommits.indexOf(commonCommit) + 1;
        
        for (Commit sourceCommit : newCommits) {
            for (int i = startIndex; i < targetCommits.size(); i++) {
                Commit targetCommit = targetCommits.get(i);
                if (sourceCommit.modifiesSameFile(targetCommit) && 
                    !strategy.resolveConflict(sourceCommit, targetCommit)) {
                    conflicts.add("Conflict on '" + sourceCommit.getFileName() + "'");
                }
            }
        }
        
        return conflicts;
    }

    /**
     * Retorna una representación en forma de cadena del repositorio.
     * <p>
     * La representación incluye el nombre del repositorio, las ramas disponibles y la
     * descripción de la rama activa.
     * </p>
     *
     * @return una cadena que representa el estado del repositorio
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Repository: " + this.name + "\n");
        sb.append("Branches:\n");
        for (String nameBranch : branches.keySet()) {
            Branch branch = branches.get(nameBranch);
            if (branch == activeBranch) {
                sb.append("- ").append(branch.getNombre()).append(" (active) ").append("\n");
            } else {
                sb.append("- ").append(branch.getNombre()).append("\n");
            }
        }
        sb.append(activeBranch.toString());
        return sb.toString();
    }
}
