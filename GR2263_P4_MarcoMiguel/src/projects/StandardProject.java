package projects;

import citizenship.User;

public class StandardProject extends Project {

    public StandardProject(String title, String description, User proposer) {
        super(title, description, proposer);
    }
    
    @Override
    public String toString() {
        // Ejemplo: "0: Limpieza del manzanares. Proponente: conservemos el manzanares <asociación con 3 ciudadanos>"
        return code + ": " + title + ". Proponente: " + proposer.toString();
    }
}
