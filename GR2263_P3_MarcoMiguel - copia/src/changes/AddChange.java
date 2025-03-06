package changes;
public class AddChange extends Change {
    private String content;
    private int startLine;
    private String filePath;

    public AddChange(int startLine, String filePath, String content) {
        super(startLine, filePath, "+");
        this.startLine = startLine;
        this.filePath = filePath;
        this.content = content;
    }
    
    public int getContentLines() {
        if(content == null || content.isEmpty()) {
             return 0;
        }
        int count = 1;
        for (char c : content.toCharArray()) {
             if (c == '\n') count++;
        }
        return count;
    }

    
    
    
    public String toString() {
    	return "{\n type=" + super.getType() + ",\n start line=" + startLine
    			+ ",\n file path='" + filePath
    			+ "',\n content='" + content
    			+ "',\n number of lines=" + getContentLines()
    			+ "\n}";
    }

}
