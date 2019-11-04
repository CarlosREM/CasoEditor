package model;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class FileOpener {
	public static File openFileChooser() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select a file to open on editor");
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("TXT files", "txt");
		FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML files", "xml");
		FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV files", "csv");
		FileNameExtensionFilter pdfFilter = new FileNameExtensionFilter("PDF files", "pdf");
		FileNameExtensionFilter jsonFilter = new FileNameExtensionFilter("JSON files", "json");
		
		jfc.addChoosableFileFilter(txtFilter);
		jfc.addChoosableFileFilter(xmlFilter);
		jfc.addChoosableFileFilter(csvFilter);
		jfc.addChoosableFileFilter(pdfFilter);
		jfc.addChoosableFileFilter(jsonFilter);
		
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			return jfc.getSelectedFile();
		}
		return null;
	}
}
