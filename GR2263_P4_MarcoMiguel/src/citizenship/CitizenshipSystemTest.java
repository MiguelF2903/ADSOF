package citizenship;

import exceptions.AssociationException;
import exceptions.IdFormatException;
import exceptions.ExistingUserException;

public class CitizenshipSystemTest {
    
    public static void main(String[] args) {
        try {
            CitizenshipSystem system = new CitizenshipSystem();
            
            // Register citizens
            Citizen juan = system.registerCitizen("Juan Bravo", "01234567K", "pass");
            Citizen ana = system.registerCitizen("Ana López", "01234567L", "pass");
            Citizen luisa = system.registerCitizen("Luisa Gómez", "01234567G", "pass");
            
            // Register associations
            Association conservemos = system.registerAssociation("conservemos el manzanares", "pass", juan);
            Association amigos = system.registerAssociation("amigos de los pájaros", "pass", juan);
            
            // Register foundation
            Foundation fundacion = system.registerFoundation("Fundación Canal", "A1234567B", "pass");
            
            // Add citizens to associations
            system.addCitizenToAssociation(juan, conservemos);
            system.addCitizenToAssociation(ana, amigos);
            system.addCitizenToAssociation(luisa, amigos);
            
            // Add association to another
            system.addAssociationToAssociation(conservemos, amigos);
            
            // Print all users with the correct Spanish format
            System.out.println(system.getUsers());
            
        } catch (IdFormatException | ExistingUserException | AssociationException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
