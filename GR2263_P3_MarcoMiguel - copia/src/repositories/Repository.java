package repositories;
import branches.*;
import commit.Commit;
import java.util.*;
import strategies.MergeStrategy;
import commit.MergeCommit;

public class Repository {
    private String nombre;
    private Map<String, Branch> ramas;
    private Branch ramaActiva;
    private Set<String> usuarios;
    private MergeStrategy defaultStrategy;

    public Repository(String nombre) {
        this.nombre = nombre;
        ramas = new LinkedHashMap<>();
        usuarios = new HashSet<>();
        Branch main = new Branch("main");
        ramas.put("main", main);
        ramaActiva = main;
        this.defaultStrategy = new MergeStrategy(MergeStrategy.STRATEGY_NULL);
    }

    public void setDefaultStrategy(MergeStrategy strategy) {
        this.defaultStrategy = strategy;
    }
    
    public void addUser(String usuario) {
        usuarios.add(usuario);
    }
    
    public void createBranch(String nombreNueva, String nombreOrigen) {
        Branch origen = ramas.get(nombreOrigen);
        if (origen == null) {
            throw new IllegalArgumentException("La rama origen no existe: " + nombreOrigen);
        }
        Branch nueva = new Branch(nombreNueva, origen);
        ramas.put(nombreNueva, nueva);
    }
    
    public void changeActiveBranch(String nombreRama) {
        Branch rama = ramas.get(nombreRama);
        if (rama == null) {
            throw new IllegalArgumentException("La rama no existe: " + nombreRama);
        }
        ramaActiva = rama;
    }
    
    public void commit(Commit commit) {
        if (!usuarios.contains(commit.getNombreAutor())) {
            System.out.println("El usuario " + commit.getNombreAutor() + " no tiene permiso para realizar commits.");
            return;
        }
        ramaActiva.commit(commit);
    }
    
    public List<String> merge(String sourceBranchName, String targetBranchName) {
        return merge(sourceBranchName, targetBranchName, defaultStrategy);
    }

    public List<String> merge(String sourceBranchName, String targetBranchName, MergeStrategy strategy) {
        Branch sourceBranch = ramas.get(sourceBranchName);
        Branch targetBranch = ramas.get(targetBranchName);
        
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
    
    private List<String> findConflicts(List<Commit> newCommits, Branch targetBranch, 
        Commit commonCommit, MergeStrategy strategy) {
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
                sb.append("- ").append(branch.getNombre()).append("\n");
            }
        }
        sb.append("\n");
        sb.append(ramaActiva.toString());
        return sb.toString();
    }
}
