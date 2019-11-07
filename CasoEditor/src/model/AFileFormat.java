package model;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public abstract class AFileFormat{

	protected File file;

	protected final ArrayList<String> text = new ArrayList<>();
	protected final ArrayList<String> color = new ArrayList<>();
	
	public ArrayList<String> getText() {
		return text;
	}
	public String getText(int index) {
		return text.get(index);
	}
	public void clearText() {
		text.clear();
	}
	public void addText(String textString) {
		text.add(textString);
	}
	
	public ArrayList<String> getColor() {
		return color;
	}
	public String getColor(int index) {
		return color.get(index);
	}
	public void clearColor() {
		color.clear();
	}
	public void addColor(String colorName) {
		color.add(colorName);
	}
	
	public AFileFormat(File file) {
		this.file = file;
	}

	public abstract void parseFile() throws Exception;
	
	public abstract void saveFile() throws Exception;
}
