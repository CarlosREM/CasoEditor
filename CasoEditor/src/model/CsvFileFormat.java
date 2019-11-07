package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CsvFileFormat extends AFileFormat {

	public CsvFileFormat(File file) {
		super(file);		
	}

	@Override
	public void parseFile() throws FileNotFoundException, IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader(file.getAbsolutePath()));
		String line;
		StringBuilder textBuilder = new StringBuilder();
		while ((line = csvReader.readLine()) != null) {
		    String[] data = line.split(",");
		    for(String token : data) {
				if (token.startsWith("<")) {
					addColor(token.substring(1, token.length()-1));
					addText(textBuilder.toString());
					textBuilder = new StringBuilder();
				}
				else
					textBuilder.append(token + " ");
		    }
		}
		csvReader.close();
	}

	@Override
	public void saveFile() throws IOException {
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < text.size(); i++) {
			for (String token : getText(i).split(" "))
				content.append(token + ",");
			content.append("<" + getColor(i) + ">,");
		}
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(content.toString());
		writer.close();
	}

}
