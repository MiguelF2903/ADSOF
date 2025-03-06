package testers;

import commit.ChangeCommit;
import commit.MergeCommit;
import changes.AddChange;
import changes.ModifyChange;
import changes.RemoveChange;
import changes.Change;
import java.util.Arrays;

public class CommitTester {
    public static void main(String[] args) {
        Change c1 = new AddChange(0, "/src/main/NuevaClase.java", "import java.util.*;\nimport java.io.*;");
        Change c2 = new ModifyChange(10, 10, "/src/main/ClaseExistente.java", "// Modificación en la clase existente");
        Change c3 = new RemoveChange(1, 2, "/src/main/ClaseObsoleta.java");

        ChangeCommit commit1 = new ChangeCommit(Arrays.asList(c1, c2, c3));

        Change c4 = new AddChange(0, "/src/pkg1/Decorator.java", "class Decorator {}\n interface Decorator{}\n toString()");
        Change c5 = new AddChange(1, "/src/pkg1/Decorator.java", "// Decorator pattern");
        Change c6 = new ModifyChange(2, 2, "/src/pkg1/Decorator.java", "// Interface modified");

        ChangeCommit commit2 = new ChangeCommit("Decorator interface", "John Doe", Arrays.asList(c4, c5, c6));

        MergeCommit mergeCommit = new MergeCommit("Merging previous commits", "John Doe", Arrays.asList(commit1, commit2));

        System.out.println(commit1);
        System.out.println(commit2);
        System.out.println(mergeCommit);
    }
}

