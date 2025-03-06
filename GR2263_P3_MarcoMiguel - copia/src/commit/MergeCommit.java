package commit;

import java.util.List;

public class MergeCommit extends Commit{
    
    private List<Commit> commits;

    public MergeCommit(String desc, String autor, List <Commit> commits){
        super(autor, desc);
        this.commits = commits;
    }

    public MergeCommit(String autor, List <Commit> commits){
        super(autor);
        this.commits = commits;
    }

    public MergeCommit(List <Commit> commits){
        super();
        this.commits = commits;
    }

    public List<Commit> getcommits(){
        return this.commits;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString() + "\n");
        sb.append("Merged commits:\n");
        for (Commit c : commits) {
            sb.append(c.getId()).append(" on ").append(c.getFechaCreacion()).append("\n");
        }
        return sb.toString();
    }
}
