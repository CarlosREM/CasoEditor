package model;

import java.io.File;

public class EditorModel {
	public void openFile() {
		File file = FileOpener.openFileChooser();
		System.out.println("File path: " + file.getPath());
	}
}
