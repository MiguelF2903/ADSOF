package changes;

public class RemoveChange extends Change {
    private int startLine;
    private int endLine;
    private String filePath;

    public RemoveChange(int startLine, int endLine, String filePath) {
        super(startLine, filePath, '-');
        this.startLine = startLine;
        this.endLine = endLine;
        this.filePath = filePath;
    }

    @Override
    public char getType(){
        return super.getType();
    }

    public int getStartLine(){
        return startLine;
    }

    public int getEndLine(){
        return endLine;
    }

    @Override
    public String getNumberChanges(){
        return "-" + Integer.toString(this.getEndLine() - this.getStartLine() + 1);
    }
    
    public String toString() {
    	return "{\n type=" + super.getType() + ",\n start line=" + startLine
    			+ ",\n file path='" + filePath 
    			+ "',\n end line=" + endLine
    			+ "\n}";
    }

}
