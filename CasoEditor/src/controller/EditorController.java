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
	private static boolean editorEnabled = false;
	private static final int mementoTimeSave = 2500;
	
	public EditorController(EditorModel model, EditorView view) {
		super();
		this.model = model;
		this.view = view;
	}
	
	
	public EditorController() {
		this.model = new EditorModel();
		this.view = new EditorView();
	}


	public void initializeListeners() {
		this.view.btnNew.addActionListener(this);
		this.view.btnOpen.addActionListener(this);
		this.view.btnSave.addActionListener(this);
		this.view.btnSaveAs.addActionListener(this);
		this.view.btnUndo.addActionListener(this);
		this.view.btnRedo.addActionListener(this);
		this.view.btnCut.addActionListener(this);
		this.view.btnCopy.addActionListener(this);
		this.view.btnPaste.addActionListener(this);
	}
	
	public void start() {
		initializeListeners();
		this.view.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand()) {
			case "Open":
				try {
					model.openFile();
					view.setTextPaneText(model.getFormat().getText(), model.getFormat().getColor());
					view.setEnabled(true);
					view.btnSave.setEnabled(true);
				}
				catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(view, "Error on file opening", "Error", JOptionPane.WARNING_MESSAGE);
				}
				break;
			case "New":
				if (model.getFormat() != null || !view.isEmpty()) {
					int choice = JOptionPane.showConfirmDialog(view, "Any changes will be lost. Continue?",
															   "New Document", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.NO_OPTION)
						return;
				}
				
				
				if(!editorEnabled) {
					view.setEnabled(true);
					view.btnUndo.setEnabled(true);
				}
				else
					restartTextEditor();
				
				view.textPane.setText("");
				startBackgroundThread();
				break;
				
			case "Save":
				try {
					model.saveDocument(view.getStyledDocument(), false);
				}
				catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(view, "Error on file saving", "Error", JOptionPane.WARNING_MESSAGE);
				}
				break;
				
			case "Save as":
				try {
					model.saveDocument(view.getStyledDocument(), true);
				}
				catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(view, "Error on file saving", "Error", JOptionPane.WARNING_MESSAGE);
				}
				break;
				
			case "Undo":
				model.loadPreviousMemento();
				if(model.getOriginator().getMemento() != null) {
					view.setTextPaneText(model.getOriginator().getMemento().getTextState(), model.getOriginator().getMemento().getColorState());
				}
				break;
				
			default:
				System.out.println("oops");
				break;
		}
		
	}
	
	private void restartTextEditor() {
		editorEnabled = false;
		try {
			Thread.sleep(mementoTimeSave+1500);
			Caretaker.getInstance().restartStacks();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void startBackgroundThread() {
		editorEnabled = true;
		Runnable mainRunnable = this;
		Thread mainThread = new Thread(mainRunnable);
		mainThread.start();
	}

	@Override
	public void run() {
		try {
			while (editorEnabled) {
				model.savePreviousMemento(view.getStyledDocument());
				Thread.sleep(mementoTimeSave);
			}
			Thread.currentThread().interrupt();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
			startBackgroundThread();
		}
	}
}
