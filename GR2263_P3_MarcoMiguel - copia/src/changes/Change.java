package changes;

public abstract class Change {
	private String file;
	private int line;
	private char type;
	
	public Change(int line, String file, char type) {
		this.line = line;
		this.file = file;
		this.type = type;
	}
	
	public String getFile() {
        return this.file;
    }

	public int getLine(){
		return this.line;
	}

	public char getType(){
		return this.type;
	}

	public String getNumberChanges(){
        return null;
    }
	

}
