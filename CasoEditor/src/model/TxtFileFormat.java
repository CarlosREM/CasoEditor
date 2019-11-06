package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TxtFileFormat extends AFileFormat {

	public TxtFileFormat(File file) {
		super(file);		
	}

	@Override
	public String parseFile() throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		StringBuilder txtBuilder = new StringBuilder();
		while (scanner.hasNext()) {
			txtBuilder.append(scanner.next() + " ");
		}
		scanner.close();
		
		return txtBuilder.toString();
	}

}
