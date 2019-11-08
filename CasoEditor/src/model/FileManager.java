package model;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


public class FileManager {
	
	private static FileNameExtensionFilter txtFilter = new FileNameExtensionFilter(".txt", "txt"),
										   tabFilter = new FileNameExtensionFilter(".tab", "tab"),
										   csvFilter = new FileNameExtensionFilter(".csv", "csv"),
										   jsonFilter = new FileNameExtensionFilter(".json", "json"),
										   xmlFilter = new FileNameExtensionFilter(".xml", "xml"),
										   pdfFilter = new FileNameExtensionFilter(".pdf", "pdf");
	public static File openFileChooser() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select a file to open on editor");
		jfc.setAcceptAllFileFilterUsed(false);
		
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
	
	public static File saveFileChooser() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select a path to save document");
		jfc.setAcceptAllFileFilterUsed(false);

		jfc.addChoosableFileFilter(txtFilter);
		jfc.addChoosableFileFilter(tabFilter);
		jfc.addChoosableFileFilter(csvFilter);
		jfc.addChoosableFileFilter(jsonFilter);
		jfc.addChoosableFileFilter(xmlFilter);
		jfc.addChoosableFileFilter(pdfFilter);
		
		
	    int returnValue = jfc.showSaveDialog(null);
	    if (returnValue == JFileChooser.APPROVE_OPTION) {
	    	//force file extension
	    	
	    	String extension = jfc.getFileFilter().getDescription();
	    	File file = jfc.getSelectedFile();
	    	String[] tokens = file.getPath().split(".");
	    	if (tokens.length == 0 || tokens[tokens.length-1] != extension)
	    		file = new File(file.getAbsolutePath() + extension);
	    	
	    	return file;
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
