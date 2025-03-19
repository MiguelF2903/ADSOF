package testers;

import commit.Commit;
import java.time.LocalDate;
import branches.Branch;

public class BranchTester {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        
        Branch main = new Branch("main");
        main.commit(new Commit("Alice", "no comment") {
            @Override
            public String getId() {
                return "00001";
            }
            @Override
            public LocalDate getFechaCreacion() {
                return date;
            }
        });
        main.commit(new Commit("Alice", "Decorator interface") {
            @Override
            public String getId() {
                return "00002";
            }
            @Override
            public LocalDate getFechaCreacion() {
                return date;
            }
        });
        main.commit(new Commit("Alice", "Merging previous commits") {
            @Override
            public String getId() {
                return "00003";
            }
            @Override
            public LocalDate getFechaCreacion() {
                return date;
            }
        });
        
        Branch solvingIssue = new Branch("Solving issue #1 (from main)", main);
        
        System.out.println(main);
        System.out.println(solvingIssue);
    }
}
