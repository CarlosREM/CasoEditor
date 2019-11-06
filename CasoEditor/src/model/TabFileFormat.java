package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TabFileFormat extends AFileFormat {

	public TabFileFormat(File file) {
		super(file);
	}

	@Override
	public void parseFile() throws Exception {
		FileReader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr);
	    StringBuilder textBuilder = new StringBuilder();
	    int c = 0;
	    while((c = br.read()) != -1) {
	    	char character = (char) c;
	        if (character == '\t')
	        	continue;
	        textBuilder.append(character);
	    }
		br.close();
		
		addColor("black");
		addText(textBuilder.toString());
	}

}
