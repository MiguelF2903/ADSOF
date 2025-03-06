package changes;

public abstract class Change {
	private String file;
	private int line;
	private String type;
	
	public Change(int line, String file, String type) {
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

	public String getType(){
		return this.type;
	}
	

}
