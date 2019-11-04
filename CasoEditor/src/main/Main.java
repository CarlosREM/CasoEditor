package main;

import controller.EditorController;
import model.EditorModel;
import model.FileOpener;
import view.EditorView;

public class Main {

	public static void main(String[] args) {
		
		EditorModel model = new EditorModel();
		EditorView view = new EditorView();
		EditorController controller = new EditorController(model, view);
		
		controller.start();
		
		//System.out.println(FileOpener.openFileChooser().getPath());

	}

}
