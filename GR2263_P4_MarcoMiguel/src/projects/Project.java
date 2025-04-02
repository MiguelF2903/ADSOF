package projects;

import citizenship.User;
import java.time.LocalDateTime;

public abstract class Project {
    private static int nextCode = 0;
    protected final int code;
    protected String title;
    protected String description;
    protected User proposer;
    protected LocalDateTime proposalDateTime;
    
    public Project(String title, String description, User proposer) {
        this.code = nextCode++;
        this.title = title;
        this.description = description;
        this.proposer = proposer;
        this.proposalDateTime = LocalDateTime.now();
    }
    
    public int getCode() { 
        return code; 
    }
    
    public String getTitle() { 
        return title; 
    }
    
    public String getDescription() { 
        return description; 
    }
    
    public User getProposer() { 
        return proposer; 
    }
    
    public LocalDateTime getProposalDateTime() { 
        return proposalDateTime; 
    }
    
    @Override
    public abstract String toString();
}
