package branches;

import commit.Commit;
import java.util.ArrayList;
import java.util.List;

public class Branch {
    private String nombre;
    private List<Commit> commits;
    
    public Branch(String nombre) {
        this.nombre = nombre;
        this.commits = new ArrayList<>();
    }
    
    public Branch(String nombre, Branch ramaOrigen) {
        this(nombre);
        this.commits.addAll(ramaOrigen.getCommits());
    }
    
    public void commit(Commit commit) {
        commits.add(commit);
    }
    
    public List<Commit> getCommits() {
        return commits;
    }

    public String getNombre() {
        return nombre;
    }
    
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
