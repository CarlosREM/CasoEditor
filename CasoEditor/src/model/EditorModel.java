package model;

import java.awt.Color;
import java.io.File;

import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.AbstractDocument.LeafElement;

import patterns.Caretaker;
import patterns.Originator;

public class EditorModel {
	
	private AFileFormat format = null;
	private Originator originator;	
	
	public AFileFormat getFormat() {
		return format;
	}
	public void setFormat(AFileFormat format) {
		this.format = format;
	}


	public EditorModel() {
		this.originator = new Originator();
	}
	

	public Originator getOriginator() {
		return originator;
	}

	public void setOriginator(Originator originator) {
		this.originator = originator;
	}

	public void openFile() throws Exception {
		File file = FileManager.openFileChooser();
		if (file != null) {
			format = FileManager.getFileFormat(file);
			format.parseFile();
		}
	}
	
	public static Color getColor(String name) {		
		return TextColor.valueOf(name).getColor();
	}
	
	public void savePreviousMemento(StyledDocument doc) {
		Caretaker.getInstance().savePreviousMemento(originator.createMemento(doc));
	}
	
	public void saveDocument(StyledDocument doc, boolean saveAs) throws Exception {
		if (format == null || saveAs) {
			File file = FileManager.saveFileChooser();
			if (file != null) {
				format = FileManager.getFileFormat(file);
			}
			else
				return;
		}
		format.clearText();
		format.clearColor();
		
		parseStyledDocument(doc);
		
		format.saveFile();
	}
	
	private void parseStyledDocument(StyledDocument doc) {
		try {
			LeafElement set;
			TextColor color;
			int colorIndex = 0;
			StringBuilder textBuilder = new StringBuilder();
			
			for (int i = 0; i < doc.getLength(); i++) {
				set = (LeafElement) doc.getCharacterElement(i).getAttributes();
				color = TextColor.fromColor((Color) set.getAttribute(StyleConstants.Foreground));
				
				if (i == 0)
					format.addColor(color.name());
				
				else if (color.name() != format.getColor(colorIndex)) {
					format.addText(textBuilder.toString());
					textBuilder = new StringBuilder();
					
					format.addColor(color.name());
					colorIndex++;
				}
				
				textBuilder.append(doc.getText(i, 1));				
			}
			format.addText(textBuilder.toString());
		}
		catch(Exception ex) {
			System.out.println("fuck");
			ex.printStackTrace();
		}
	}
	
	public void loadPreviousMemento() {
		originator.setMemento(Caretaker.getInstance().getPreviousMemento());		
	}
}
