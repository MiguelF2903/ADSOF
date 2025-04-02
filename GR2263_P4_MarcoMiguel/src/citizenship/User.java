package citizenship;

public abstract class User {
    private String name;
    private String password;
    
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
    
    public abstract String getId();
    
    // Método para obtener el tipo de usuario (para ordenarlos)
    public abstract int getUserType();
    
    // Constantes para tipos de usuario
    public static final int CITIZEN_TYPE = 1;
    public static final int ASSOCIATION_TYPE = 2;
    public static final int FOUNDATION_TYPE = 3;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return getId().equals(user.getId());
    }
    
    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
