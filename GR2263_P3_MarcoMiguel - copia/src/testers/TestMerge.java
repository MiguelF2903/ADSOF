/**
 * Miguel Fernández Arco y Marco Vázquez Campos
*/ 
package testers;

import repositories.Repository;
import commit.ChangeCommit;
import changes.*;
import strategies.MergeStrategy;
import java.util.ArrayList;
import java.util.List;

public class TestMerge {
    public static void main(String[] args) {
        Repository repo = new Repository("ADSOF p3");
        repo.addUser("usuario1");
        
        List<Change> changes1 = new ArrayList<>();
        changes1.add(new AddChange(1, "Main.java", "Add main class"));
        
        List<Change> changes2 = new ArrayList<>();
        changes2.add(new AddChange(1, "Decorator.java", "Add decorator interface"));
        
        List<Change> changes3 = new ArrayList<>();
        changes3.add(new AddChange(1, "Main.java", "Merge changes"));
        
        repo.commit(new ChangeCommit("no comment", "usuario1", changes1));
        repo.commit(new ChangeCommit("Decorator interface", "usuario1", changes2));
        repo.commit(new ChangeCommit("Merging previous commits", "usuario1", changes3));
        
        System.out.println(repo);
        
        repo.createBranch("Solving issue #1", "main");
        repo.changeActiveBranch("Solving issue #1");
        
        List<Change> changes4 = new ArrayList<>();
        changes4.add(new AddChange(1, "Main.java", "Fix issue"));
        repo.commit(new ChangeCommit("Solving the issue", "usuario1", changes4));
        
        System.out.println(repo);
        
        repo.changeActiveBranch("main");
        repo.setDefaultStrategy(new MergeStrategy(MergeStrategy.STRATEGY_SOURCE));
        List<String> conflicts = repo.merge("Solving issue #1", "main");
        
        if (conflicts == null || conflicts.isEmpty()) {
            System.out.println(repo);
        } else {
            System.out.println("Merge failed. Conflicts found: " + conflicts);
        }
    }
}