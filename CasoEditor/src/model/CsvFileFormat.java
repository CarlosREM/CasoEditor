package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CsvFileFormat extends AFileFormat {

	public CsvFileFormat(File file) {
		super(file);		
	}

	@Override
	public String parseFile() throws FileNotFoundException, IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader(file.getAbsolutePath()));
		String line;
		StringBuilder textBuilder = new StringBuilder();
		while ((line = csvReader.readLine()) != null) {
		    String[] data = line.split(",");
		    for(String token : data)
		    	textBuilder.append(token + " ");
		}
		csvReader.close();
		
		return textBuilder.toString();
	}

}
