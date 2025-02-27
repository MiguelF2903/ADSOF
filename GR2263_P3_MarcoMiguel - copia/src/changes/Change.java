package changes;

public abstract class Change {
	String file;
	int line;
	
	public Change(int line, String file) {
		this.line = line;
		this.file = file;
	}
	
	

}
