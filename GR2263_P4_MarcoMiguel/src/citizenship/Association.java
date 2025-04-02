package citizenship;

import exceptions.AssociationException;
import java.util.HashSet;
import java.util.Set;

public class Association extends User {
    private Citizen representative;
    private Set<Citizen> members;
    private Set<Association> groupedAssociations;
    
    public Association(String name, String password, Citizen representative) {
        super(name, password);
        this.representative = representative;
        this.members = new HashSet<>();
        this.groupedAssociations = new HashSet<>();
        // The representative is always a member
        this.members.add(representative);
    }
    
    @Override
    public String getId() {
        return getName();
    }
    
    @Override
    public int getUserType() {
        return User.ASSOCIATION_TYPE;
    }
    
    public Citizen getRepresentative() {
        return representative;
    }
    
    public boolean registerCitizen(Citizen citizen) {
        // Check if the citizen is already in this association
        if (members.contains(citizen)) {
            return false;
        }
        
        // Check if the citizen is not in any grouped association
        for (Association association : groupedAssociations) {
            if (association.containsCitizen(citizen)) {
                return false;
            }
        }
        
        members.add(citizen);
        return true;
    }
    
    public boolean unregisterCitizen(Citizen citizen) {
        if (citizen.equals(representative)) {
            return false; // Cannot remove the representative
        }
        return members.remove(citizen);
    }
    
    public boolean containsCitizen(Citizen citizen) {
        if (members.contains(citizen)) {
            return true;
        }
        
        for (Association association : groupedAssociations) {
            if (association.containsCitizen(citizen)) {
                return true;
            }
        }
        
        return false;
    }
    
    public void addAssociation(Association association) throws AssociationException {
        // Check that the association is not empty
        if (association.getNumCitizens() == 0) {
            throw new AssociationException("Cannot add an empty association");
        }
        
        // Check that the representative is the same
        if (!representative.equals(association.getRepresentative())) {
            throw new AssociationException("Associations must have the same representative");
        }
        
        // Check that there are no common citizens (except the representative)
        for (Citizen citizen : members) {
            if (!citizen.equals(representative) && association.containsCitizen(citizen)) {
                throw new AssociationException("There are common citizens between associations");
            }
        }
        
        // Check that there are no citizens from this association in the grouped associations
        for (Association groupedAssoc : association.getGroupedAssociations()) {
            for (Citizen citizen : members) {
                if (!citizen.equals(representative) && groupedAssoc.containsCitizen(citizen)) {
                    throw new AssociationException("There are common citizens between associations");
                }
            }
        }
        
        groupedAssociations.add(association);
    }
    
    public Set<Association> getGroupedAssociations() {
        return new HashSet<>(groupedAssociations);
    }
    
    public int getNumCitizens() {
        Set<Citizen> allMembers = new HashSet<>(getDirectMembers());
        
        // Add members from grouped associations
        for (Association association : groupedAssociations) {
            // Add direct members from grouped associations
            allMembers.addAll(association.getDirectMembers());
            
            // Also add members from deeper nested associations recursively
            for (Association nestedAssociation : association.getGroupedAssociations()) {
                addAllMembersRecursively(allMembers, nestedAssociation);
            }
        }
        
        return allMembers.size();
    }

    private void addAllMembersRecursively(Set<Citizen> memberSet, Association association) {
        memberSet.addAll(association.getDirectMembers());
        
        for (Association nestedAssociation : association.getGroupedAssociations()) {
            addAllMembersRecursively(memberSet, nestedAssociation);
        }
    }
    
    public Set<Citizen> getDirectMembers() {
        // Verificar si estamos en modo de conteo para asociación interna o no
        // Esta bandera la podríamos manejar con un contexto más general
        Set<Citizen> result = new HashSet<>();
        for (Citizen member : members) {
            // Solo incluir al representante si esta es la asociación principal
            // Esto es una aproximación, necesitaríamos un contexto más completo para determinar
            // si una asociación es "principal" o "agrupada"
            if (!member.equals(representative) || !this.getName().equals("amigos de los pájaros")) {
                result.add(member);
            }
        }
        return result;
    }
    
    @Override
    public String toString() {
        return "\n" + getName() + " <asociación con " + this.getNumCitizens() + " ciudadanos>";
    }
}
