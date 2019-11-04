package main;

import controller.EditorController;
import model.EditorModel;
import view.EditorView;

public class Main {

	public static void main(String[] args) {
		EditorModel model = new EditorModel();
		EditorView view = new EditorView();
		new EditorController(model, view);
		
		view.setVisible(true);
	}

}
