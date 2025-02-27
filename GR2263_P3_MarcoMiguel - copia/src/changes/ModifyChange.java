package changes;

public class ModifyChange extends Change {
    private String change;
    private int startLine;
    private int endLine;
    private String filePath;

    public ModifyChange(int startLine, int endLine, String filePath, String change) {
        super(startLine, filePath);
        this.startLine = startLine;
        this.endLine = endLine;
        this.filePath = filePath;
        this.change = change;
    }

    @Override
    public String toString() {
        return "{\n type=/,\n start line=" + startLine 
            + ",\n file path='" + filePath + "'" 
            + ",\n content='" + change + "'" 
            + ",\n number of lines=" + (endLine - startLine + 1) 
            + ",\n end line=" + endLine
            + "\n}"
            ;
    }
}
