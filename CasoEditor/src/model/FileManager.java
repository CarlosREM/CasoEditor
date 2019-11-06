package model;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class FileManager {
	
	public static File openFileChooser() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select a file to open on editor");
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("TXT files", "txt");
		FileNameExtensionFilter tabFilter = new FileNameExtensionFilter("TAB files", "tab");
		FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV files", "csv");
		FileNameExtensionFilter jsonFilter = new FileNameExtensionFilter("JSON files", "json");
		FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML files", "xml");
		
		jfc.addChoosableFileFilter(txtFilter);
		jfc.addChoosableFileFilter(tabFilter);
		jfc.addChoosableFileFilter(csvFilter);
		jfc.addChoosableFileFilter(jsonFilter);
		jfc.addChoosableFileFilter(xmlFilter);
		
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			return jfc.getSelectedFile();
		}
		return null;
	}
	
	public static AFileFormat getFileFormat(File file) {
		String path = file.getAbsolutePath();
		int i = path.lastIndexOf('.');
		String extension = path.substring(i+1);
		AFileFormat format;
		switch(extension) {
			case "txt":
				format = new TxtFileFormat(file);
				break;
				
			case "tab":
				format = new TabFileFormat(file);
				break;
				
			case "csv":
				format = new CsvFileFormat(file);
				break;

			case "json":
				format = new JsonFileFormat(file);
				break;
				
			case "xml":
				format = new XmlFileFormat(file);
				break;
				
			default:
				format = null;
		}
		return format;
	}
	
}
