package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.EditorModel;
import view.EditorView;

public class EditorController implements ActionListener{

	private final EditorModel model;
	private final EditorView view;
	
	public EditorController(EditorModel model, EditorView view) {
		super();
		this.model = model;
		this.view = view;
	}
	
	
	
	public EditorController() {
		this.model = new EditorModel();
		this.view = new EditorView();
	}



	public void initiliazeListeners() {
		this.view.btnNew.addActionListener(this);
		this.view.btnOpen.addActionListener(this);
	}
	
	public void start() {
		initiliazeListeners();
		this.view.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand()) {
			case "Open":
				try {
					String text = model.openFile();
					view.textPane.setText(text);
					enableScreen();
				}
				catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(view, "Error on file opening", "Error", JOptionPane.WARNING_MESSAGE);
				}
				break;
			default:
				break;
		}
		
	}
	
	private void enableScreen() {
		view.btnSave.setEnabled(true);
		view.btnSaveAs.setEnabled(true);
		view.btnCut.setEnabled(true);
		view.btnCopy.setEnabled(true);
		view.textPane.setEnabled(true);
	}
}
