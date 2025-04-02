package projects;

import exceptions.AssociationException;
import exceptions.ExistingUserException;
import exceptions.IdFormatException;
import citizenship.*;

public class ProjectManagerTest {
    
    public static void main(String[] args) {
        try {
            // Primero, instanciar el sistema y registrar algunos usuarios
            CitizenshipSystem system = new CitizenshipSystem();
            Citizen juan = system.registerCitizen("Juan Bravo", "01234567K", "pass");
            Citizen ana = system.registerCitizen("Ana López", "01234567L", "pass");
            Citizen luisa = system.registerCitizen("Luisa Gómez", "01234567G", "pass");
            
            Association conservemos = system.registerAssociation("conservemos el manzanares", "pass", juan);
            Association amigos = system.registerAssociation("amigos de los pájaros", "pass", juan);
            Foundation fundacion = system.registerFoundation("Fundación Canal", "A1234567B", "pass");
            
            // Relacionar usuarios con asociaciones (según lo implementado en el Apartado 1)
            system.addCitizenToAssociation(juan, conservemos);
            system.addCitizenToAssociation(ana, amigos);
            system.addCitizenToAssociation(luisa, amigos);
            system.addAssociationToAssociation(conservemos, amigos);
            
            // Instanciar el ProjectManager
            ProjectManager projectManager = new ProjectManager();
            
            // Proponer un proyecto de una asociación:
            projectManager.addStandardProject("Limpieza del manzanares", "Proyecto para limpiar el río", conservemos);
            
            // Proponer un proyecto de una fundación (se validan presupuesto y porcentaje)
            projectManager.addFoundationProject("Gastemos menos agua", "Proyecto para el ahorro de agua", fundacion, 1000000.0, 80.0);
            
            // Imprimir la lista de proyectos
            System.out.println(projectManager.getProjects());
            
        } catch (IdFormatException | ExistingUserException | AssociationException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
