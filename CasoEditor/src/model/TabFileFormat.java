package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TabFileFormat extends AFileFormat {

	public TabFileFormat(File file) {
		super(file);
	}

	@Override
	public void parseFile() throws Exception {
		FileReader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr);
	    StringBuilder textBuilder = new StringBuilder(),
	    			  colorBuilder = new StringBuilder();
	    int c = 0;
	    boolean colorFlag = false;
	    char character;
	    while((c = br.read()) != -1) {
	    	character = (char) c;
	    	switch(character) {
	    		case '\t':
	    			break;
	    	
	    		case '<':
		    		colorFlag = true;
		    		addText(textBuilder.toString());
		    		textBuilder = new StringBuilder();
		    		break;
		    		
	    		case '>':
		    		addColor(colorBuilder.toString());
		    		colorBuilder = new StringBuilder();
		    		colorFlag = false;
		    		break;
	    			
	    		default:
			    	if (colorFlag)
			    		colorBuilder.append(character);
			    	else
			    		textBuilder.append(character);
	    	}
	    }
		br.close();
	}

	@Override
	public void saveFile() throws IOException {
		StringBuilder content = new StringBuilder();
		String textString;
		int counter = 0;
		for (int i = 0; i < text.size(); i++) {
			textString = getText(i);			
			char character;
			for (int j = 0; j < textString.length(); j++) {
				if (counter == 10) {
					content.append('\t');
					counter = 0;
				}
				character = textString.charAt(j);
				if (character == '\n')
					counter = 0;
				else
					counter++;
				content.append(character);
			}
			content.append("<" + getColor(i) + ">");
		}
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(content.toString());
		writer.close();
	}
	
}
