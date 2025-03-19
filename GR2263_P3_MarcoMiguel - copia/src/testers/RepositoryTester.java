package testers;

import commit.Commit;
import repositories.Repository;
import java.time.LocalDate;

public class RepositoryTester {
    public static void main(String[] args) {
        Repository repo = new Repository("ADSOF p3");
        
        repo.addUser("Alice");
        
        LocalDate date = LocalDate.now();
        
        repo.commit(new Commit("Alice", "no comment") {
            @Override
            public String getId() {
                return "00001";
            }
            @Override
            public LocalDate getCreationDate() {
                return date;
            }
        });
        repo.commit(new Commit("Alice", "Decorator interface") {
            @Override
            public String getId() {
                return "00002";
            }
            @Override
            public LocalDate getCreationDate() {
                return date;
            }
        });
        repo.commit(new Commit("Alice", "Merging previous commits") {
            @Override
            public String getId() {
                return "00003";
            }
            @Override
            public LocalDate getCreationDate() {
                return date;
            }
        });
        
        repo.createBranch("Solving issue #1", "main");
        System.out.println(repo);
    }
}
