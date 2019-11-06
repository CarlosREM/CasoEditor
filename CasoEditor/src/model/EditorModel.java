package model;

import java.io.File;

public class EditorModel {
	
	private AFileFormat format;
	
	public String openFile() throws Exception {
		File file = FileManager.openFileChooser();
		if (file != null) {
			format = FileManager.getFileFormat(file);
			return format.parseFile();
		}
		return null;
	}
}
