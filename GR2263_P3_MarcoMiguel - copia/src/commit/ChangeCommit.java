package commit;

import java.util.List;
import changes.Change;

public class ChangeCommit extends Commit {

    private List<Change> changes;

    public ChangeCommit(String desc, String autor, List<Change> changes) {
        super(autor, desc);
        this.changes = changes;
    }

    public ChangeCommit(String autor, List<Change> changes) {
        super(autor);
        this.changes = changes;
    }

    public ChangeCommit(List<Change> changes) {
        super();
        this.changes = changes;
    }

    public List<Change> getChanges() {
        return this.changes;
    }

    @Override
    public String getFileName() {
        if (changes == null || changes.isEmpty()) {
            return "";
        }
        return changes.get(0).getFile(); // Devolvemos el archivo del primer cambio
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString() + "\n");
        for (Change c : changes) {
            sb.append(c.getType()).append(" : ").append(c.getFile()).append(" (").append(c.getNumberChanges())
                    .append(")").append("\n");
        }
        return sb.toString();
    }
}