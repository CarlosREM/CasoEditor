package model;

import java.awt.Color;

public enum TextColor {
	BLACK (Color.BLACK),
	RED (Color.RED),
	BLUE (Color.BLUE),
	GREEN (Color.GREEN),
	ORANGE (Color.ORANGE);
	
	private final Color color;
	
    TextColor(Color color) {
        this.color = color;
    }
 
    public Color getColor() {
        return color;
    }
    
    public static TextColor fromColor(Color color) {
    	for (TextColor value : values()) {
    		if (value.getColor() == color)
    			return value;
    	}
    	return null;
    }
}
