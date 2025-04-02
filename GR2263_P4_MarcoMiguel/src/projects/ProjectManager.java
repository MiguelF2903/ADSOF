package projects;

import java.util.ArrayList;
import java.util.List;
import citizenship.User;
import citizenship.Foundation;

public class ProjectManager {
    private List<Project> projects;
    
    public ProjectManager() {
        projects = new ArrayList<>();
    }
    
    // Registra un proyecto estándar (ciudadanos o asociaciones)
    public void addStandardProject(String title, String description, User proposer) {
        projects.add(new StandardProject(title, description, proposer));
    }
    
    // Registra un proyecto de fundación
    public void addFoundationProject(String title, String description, Foundation proposer, double estimatedBudget, double percentage) {
        projects.add(new FoundationProject(title, description, proposer, estimatedBudget, percentage));
    }
    
    public List<Project> getProjects() {
        return new ArrayList<>(projects);
    }
    
    // Recupera un proyecto dado su código identificativo
    public Project getProjectByCode(int code) {
        for (Project p : projects) {
            if (p.getCode() == code) {
                return p;
            }
        }
        return null;
    }
    
    // Recupera proyectos que contengan parte del título (sin distinción de mayúsculas)
    public List<Project> getProjectsByTitle(String title) {
        List<Project> result = new ArrayList<>();
        for (Project p : projects) {
            if (p.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }
}
