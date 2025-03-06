package changes;

public class ModifyChange extends Change {
    private String change;
    private int startLine;
    private int endLine;
    private String filePath;

    public ModifyChange(int startLine, int endLine, String filePath, String change) {
        super(startLine, filePath, '/');
        this.startLine = startLine;
        this.endLine = endLine;
        this.filePath = filePath;
        this.change = change;
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
        return "0";
    }

    @Override
    public String toString() {
        return "{\n type=" + super.getType() + ",\n start line=" + startLine 
            + ",\n file path='" + filePath + "'" 
            + ",\n content='" + change + "'" 
            + ",\n number of lines=" + (endLine - startLine + 1) 
            + ",\n end line=" + endLine
            + "\n}"
            ;
    }
}
