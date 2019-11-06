package model;

import java.io.File;
import java.util.ArrayList;

public abstract class AFileFormat{

	protected File file;

	private final ArrayList<String> text = new ArrayList<>();
	private final ArrayList<String> color = new ArrayList<>();
	
	public ArrayList<String> getText() {
		return text;
	}
	protected void addText(String textString) {
		text.add(textString);
	}
	
	public ArrayList<String> getColor() {
		return color;
	}
	protected void addColor(String colorName) {
		color.add(colorName);
	}
	
	public AFileFormat(File file) {
		this.file = file;
	
	}

	public abstract void parseFile() throws Exception;
	
}
