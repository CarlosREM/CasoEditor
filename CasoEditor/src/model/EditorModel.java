package model;

import java.awt.Color;
import java.io.File;
import java.lang.reflect.Field;

import patterns.Caretaker;
import patterns.Originator;

public class EditorModel {
	
	private AFileFormat format = null;
	private Originator originator;	
	
	public AFileFormat getFormat() {
		return format;
	}

	public EditorModel() {
		this.originator = new Originator();
	}

	public void openFile() throws Exception {
		File file = FileManager.openFileChooser();
		if (file != null) {
			format = FileManager.getFileFormat(file);
			format.parseFile();
		}
	}
	
	public static Color getColor(String name) {
		Color color;
		try {
		    Field field = Color.class.getField(name);
		    color = (Color) field.get(null);
		}
		catch (Exception e) {
		    color = null;
		}
		return color;
	}
	
	public void saveMemento(String textSave) {
		Caretaker.getInstance().addPreviousMemento(originator.createMemento(textSave));
	}
	
}
