package model;

import java.io.File;

public abstract class AFileFormat{

	protected File file;

	public AFileFormat(File file) {
		this.file = file;
	
	}

	public abstract String parseFile() throws Exception;
	
}
