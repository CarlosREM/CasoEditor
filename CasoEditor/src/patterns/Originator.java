package patterns;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.AbstractDocument.LeafElement;

import model.TextColor;

public class Originator {
	private Memento state = null;
	
	public Originator() {
		
	}
	
	public Memento getMemento() {
		return state;
	}


	public void setMemento(Memento memento) {
		this.state = memento;
	}
	
	public Memento createMemento(StyledDocument document) {
		ArrayList<String> text = new ArrayList<>();
		ArrayList<String> colors = new ArrayList<>();
		try {
			LeafElement set;
			TextColor color;
			int colorIndex = 0;
			StringBuilder textBuilder = new StringBuilder();
			
			for (int i = 0; i < document.getLength(); i++) {
				set = (LeafElement) document.getCharacterElement(i).getAttributes();
				color = TextColor.fromColor((Color) set.getAttribute(StyleConstants.Foreground));
				
				if (i == 0)
					colors.add(color.name());
				
				else if (color.name() != colors.get(colorIndex)) {
					text.add(textBuilder.toString());
					textBuilder = new StringBuilder();
					colors.add(color.name());
					colorIndex++;
				}
				
				textBuilder.append(document.getText(i, 1));				
			}
			text.add(textBuilder.toString());
		}
		catch(Exception ex) {
			//ex.printStackTrace();
		}
		Memento memento = new Memento(text,colors);
		setMemento(memento);
		return memento;
	}
}
