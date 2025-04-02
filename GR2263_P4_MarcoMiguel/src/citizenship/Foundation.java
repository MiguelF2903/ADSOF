package citizenship;

import exceptions.IdFormatException;
import java.util.regex.Pattern;

public class Foundation extends User {
    private final String cif;
    
    public Foundation(String name, String cif, String password) throws IdFormatException {
        super(name, password);
        if (!validateCIF(cif)) {
            throw new IdFormatException("The CIF " + cif + " does not have the correct format (letter + 7 digits + letter)");
        }
        this.cif = cif;
    }
    
    private boolean validateCIF(String cif) {
        return Pattern.matches("[A-Za-z]\\d{7}[A-Za-z]", cif);
    }
    
    @Override
    public String getId() {
        return cif;
    }
    
    @Override
    public String toString() {
        return "\n" + getName() + " CIF (" + this.cif + ") <fundación>";
    }
    
    @Override
    public int getUserType() {
        return User.FOUNDATION_TYPE;
    }
}
