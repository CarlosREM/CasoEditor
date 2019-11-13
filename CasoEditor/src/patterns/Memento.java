package patterns;

import java.util.ArrayList;

public class Memento {
	private ArrayList<String> textState;
	private ArrayList<String> colorState;

	public Memento(ArrayList<String> textState, ArrayList<String> colorState) {
		this.textState = textState;
		this.colorState = colorState;
	}

	public ArrayList<String> getTextState() {
		return textState;
	}

	public void setTextState(ArrayList<String> textState) {
		this.textState = textState;
	}

	public ArrayList<String> getColorState() {
		return colorState;
	}

	public void setColorState(ArrayList<String> colorState) {
		this.colorState = colorState;
	}		
}
