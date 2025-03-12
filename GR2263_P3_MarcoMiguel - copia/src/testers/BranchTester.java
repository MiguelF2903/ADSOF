package testers;

import commit.Commit;
import java.time.LocalDate;

import branches.Branch;

public class BranchTester {
    
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
        LocalDate fechaFija = LocalDate.of(2025, 2, 21);
        
        Branch main = new Branch("main");
        main.commit(new TestCommit("00001", "Alice", "no comment", fechaFija));
        main.commit(new TestCommit("00002", "Alice", "Decorator interface", fechaFija));
        main.commit(new TestCommit("00003", "Alice", "Merging previous commits", fechaFija));
        
        Branch solvingIssue = new Branch("Solving issue #1 (from main)", main);
        
        System.out.println(main);
        System.out.println(solvingIssue);
    }
}
