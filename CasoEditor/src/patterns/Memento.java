package patterns;

public class Memento {
	private String textState;

	public Memento(String textState) {
		this.textState = textState;
	}

	public String getTextState() {
		return textState;
	}

	public void setTextState(String textState) {
		this.textState = textState;
	}	
	
}
