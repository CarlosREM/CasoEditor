package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TxtFileFormat extends AFileFormat {

	
	// Estructura de txt:
	//
	// textotexto <COLOR> textotexto <COLOR>
	
	public TxtFileFormat(File file) {
		super(file);		
	}


	@Override
	public void parseFile() throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		StringBuilder textBuilder = new StringBuilder();
		String token;
		while (scanner.hasNext()) {
			token = scanner.next();
			if (token.startsWith("<")) {
				addColor(token.substring(1, token.length()-1));
				addText(textBuilder.toString());
				textBuilder = new StringBuilder();
			}
			else
				textBuilder.append(token + " ");
		}
		scanner.close();
	}

	@Override
	public void saveFile() throws IOException {
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < text.size(); i++) {
			content.append(getText(i));
			content.append(" <" + getColor(i) + "> ");
		}
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(content.toString());
		writer.close();
	}

}
