package changes;

public class RemoveChange extends Change {
    private int startLine;
    private int endLine;
    private String filePath;

    public RemoveChange(int startLine, int endLine, String filePath) {
        super(startLine, filePath);
        this.startLine = startLine;
        this.endLine = endLine;
        this.filePath = filePath;
    }
    
    public String toString() {
    	return "{\n type=-,\n start line=" + startLine
    			+ ",\n file path='" + filePath 
    			+ "',\n end line=" + endLine
    			+ "\n}";
    }

}
