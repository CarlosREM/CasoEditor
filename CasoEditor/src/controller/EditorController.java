package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.AFileFormat;
import model.EditorModel;
import patterns.Caretaker;
import view.EditorView;

public class EditorController implements ActionListener, Runnable{

	private final EditorModel model;
	private final EditorView view;
	private static boolean enableEditor = false;
	private static final int mementoTimeSave = 2000;
	
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
					model.openFile();
					view.setTextPaneText(model.getFormat().getText(), model.getFormat().getColor());
					enableScreen();
				}
				catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(view, "Error on file opening", "Error", JOptionPane.WARNING_MESSAGE);
				}
				break;
			case "New":
				//Hay que mostrar mensaje de que se va a perder todo lo que no tenga guardado
				//Luego se limpia el textarea
				
				if(!enableEditor) 
					enableScreen();
				else 
					restartTextEditor();
				startBackgroundThread();
				break;
			default:
				break;
		}
		
	}
	
	private void restartTextEditor() {
		enableEditor = false;
		try {
			Thread.sleep(mementoTimeSave+1500);
			Caretaker.getInstance().restartStacks();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void enableScreen() {
		view.btnSave.setEnabled(true);
		view.btnSaveAs.setEnabled(true);
		view.btnCut.setEnabled(true);
		view.btnCopy.setEnabled(true);
		view.btnUndo.setEnabled(true);
		view.btnRedo.setEnabled(true);
		view.textPane.setEnabled(true);
	}
	
	public void startBackgroundThread() {
		enableEditor = true;
		Runnable mainRunnable = this;
		Thread mainThread = new Thread(mainRunnable);
		mainThread.start();
	}

	@Override
	public void run() {
		try {
			while (enableEditor) {
				model.saveMemento(view.textPane.getText());
				Thread.sleep(mementoTimeSave);
			}
			Thread.currentThread().interrupt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
