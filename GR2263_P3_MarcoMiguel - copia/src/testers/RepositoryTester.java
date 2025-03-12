package testers;

import commit.Commit;
import repositories.Repository;

import java.time.LocalDate;

public class RepositoryTester {

    // Clase concreta para pruebas que extiende Commit.
    static class TestCommit extends Commit {
        private String id;
        private LocalDate fecha;
        
        public TestCommit(String id, String autor, String desc, LocalDate fecha) {
            super(autor, desc);
            this.id = id;
            this.fecha = fecha;
        }
        
        @Override
        public String getId() {
            return id;
        }
        
        @Override
        public LocalDate getFechaCreacion() {
            return fecha;
        }
    }
    
    public static void main(String[] args) {
        Repository repo = new Repository("ADSOF p3");
        
        // Agregar usuario con permiso para realizar commits.
        repo.addUser("Alice");
        
        // Utilizamos la fecha actual.
        LocalDate today = LocalDate.now();
        
        // Realizar tres commits en la rama activa ("main") usando la fecha de hoy.
        repo.commit(new TestCommit("00001", "Alice", "no comment", today));
        repo.commit(new TestCommit("00002", "Alice", "Decorator interface", today));
        repo.commit(new TestCommit("00003", "Alice", "Merging previous commits", today));
        
        // Crear una nueva rama a partir de "main"
        repo.createBranch("Solving issue #1", "main");
        System.out.println(repo);
    }
}
