package model;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.AbstractDocument.LeafElement;

public class ClipboardManager {
	
	private static ArrayList<Color> colors = new ArrayList<>();
	private static ArrayList<String> text = new ArrayList<>();
	
	public static ArrayList<Color> getColors() {
		return colors;
	}

	public static ArrayList<String> getText() {
		return text;
	}

	
	public static boolean saveClipboard(StyledDocument doc, int start, int end) {
		ArrayList<Color> tempColors = colors;
		ArrayList<String> tempText = text;
		boolean result = true;
		try {
			colors.clear();
			text.clear();
		
			LeafElement set;
			Color color;
			int colorIndex = 0;
			StringBuilder textBuilder = new StringBuilder();
			for (int i = start; i <= end; i++) {
				set = (LeafElement) doc.getCharacterElement(i).getAttributes();
				color = (Color) set.getAttribute(StyleConstants.Foreground);
				
				if (i == start)
					colors.add(color);
				
				else if (color != colors.get(colorIndex)) {
					text.add(textBuilder.toString());
					textBuilder = new StringBuilder();
					
					colors.add(color);
					colorIndex++;
				}
				
				textBuilder.append(doc.getText(i, 1));
			}
			text.add(textBuilder.toString());
		} 
		catch (Exception e) {
			e.printStackTrace();
			colors = tempColors;
			text = tempText;
			result = false;
		}
		return result;
	}
	
}
