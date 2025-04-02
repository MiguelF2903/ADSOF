package citizenship;

import exceptions.IdFormatException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Citizen extends User {
    private final String nif;
    private Set<Association> associations;
    
    public Citizen(String name, String nif, String password) throws IdFormatException {
        super(name, password);
        if (!validateNIF(nif)) {
            throw new IdFormatException("The NIF " + nif + " does not have the correct format (8 digits + letter)");
        }
        this.nif = nif;
        this.associations = new HashSet<>();
    }
    
    private boolean validateNIF(String nif) {
        return Pattern.matches("\\d{8}[A-Za-z]", nif);
    }
    
    @Override
    public String getId() {
        return nif;
    }
    
    @Override
    public int getUserType() {
        return User.CITIZEN_TYPE;
    }
    
    public boolean joinAssociation(Association association) {
        if (association.registerCitizen(this)) {
            associations.add(association);
            return true;
        }
        return false;
    }
    
    public boolean leaveAssociation(Association association) {
        if (association.unregisterCitizen(this)) {
            associations.remove(association);
            return true;
        }
        return false;
    }
    
    public void leaveAllAssociations() {
        for (Association association : new HashSet<>(associations)) {
            leaveAssociation(association);
        }
    }
    
    public Set<Association> getAssociations() {
        return new HashSet<>(associations);
    }
    
    @Override
    public String toString() {
        return "\n" + this.getName() + " NIF (" + this.getId() + ") <usuario>";
    }
}
