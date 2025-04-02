package citizenship;

import exceptions.AssociationException;
import exceptions.IdFormatException;
import exceptions.ExistingUserException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CitizenshipSystem {
    private Map<String, User> usersById;
    private Map<String, User> usersByName;
    
    public CitizenshipSystem() {
        usersById = new HashMap<>();
        usersByName = new HashMap<>();
    }
    
    public Citizen registerCitizen(String name, String nif, String password) 
            throws IdFormatException, ExistingUserException {
        if (usersById.containsKey(nif)) {
            throw new ExistingUserException("A user with NIF already exists: " + nif);
        }
        
        Citizen citizen = new Citizen(name, nif, password);
        usersById.put(nif, citizen);
        usersByName.put(name, citizen);
        return citizen;
    }
    
    public Foundation registerFoundation(String name, String cif, String password) 
            throws IdFormatException, ExistingUserException {
        if (usersById.containsKey(cif)) {
            throw new ExistingUserException("A user with CIF already exists: " + cif);
        }
        
        Foundation foundation = new Foundation(name, cif, password);
        usersById.put(cif, foundation);
        usersByName.put(name, foundation);
        return foundation;
    }
    
    public Association registerAssociation(String name, String password, Citizen representative) 
            throws ExistingUserException {
        if (usersByName.containsKey(name)) {
            throw new ExistingUserException("An association with this name already exists: " + name);
        }
        
        Association association = new Association(name, password, representative);
        usersByName.put(name, association);
        return association;
    }
    
    public boolean addCitizenToAssociation(Citizen citizen, Association association) {
        return citizen.joinAssociation(association);
    }
    
    public boolean removeCitizenFromAssociation(Citizen citizen, Association association) {
        return citizen.leaveAssociation(association);
    }
    
    public void removeCitizenFromAllAssociations(Citizen citizen) {
        citizen.leaveAllAssociations();
    }
    
    public void addAssociationToAssociation(Association main, Association secondary) 
            throws AssociationException {
        main.addAssociation(secondary);
    }
    
    public User getUserByName(String name) {
        return usersByName.get(name);
    }
    
    public User getUserById(String id) {
        return usersById.get(id);
    }
    
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        List<User> citizens = new ArrayList<>();
        List<User> associations = new ArrayList<>();
        List<User> foundations = new ArrayList<>();
        
        // Clasificar usuarios por tipo
        for (User user : usersById.values()) {
            if (user.getUserType() == User.CITIZEN_TYPE) {
                citizens.add(user);
            } else if (user.getUserType() == User.FOUNDATION_TYPE) {
                foundations.add(user);
            }
        }
        
        for (User user : usersByName.values()) {
            if (user.getUserType() == User.ASSOCIATION_TYPE && !usersById.containsValue(user)) {
                associations.add(user);
            }
        }
        
        // Construir la lista final en el orden adecuado
        users.addAll(citizens);
        users.addAll(associations);
        users.addAll(foundations);
        
        return users;
    }
}