package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.AFileFormat;
import model.ClipboardManager;
import model.EditorModel;
import patterns.Caretaker;
import view.EditorView;

public class EditorController implements ActionListener, Runnable{

	private final EditorModel model;
	private final EditorView view;
	private static boolean editorEnabled = false;
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
		int start, end;
		switch(arg0.getActionCommand()) {
			case "Open":
				try {
					model.openFile();
					view.setTextPaneText(model.getFormat().getText(), model.getFormat().getColor());
					view.setEnabled(true);
					view.btnSave.setEnabled(true);
				}
				catch(Exception ex) {
					//ex.printStackTrace();
					JOptionPane.showMessageDialog(view, "Error on file opening", "Error", JOptionPane.ERROR_MESSAGE);
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
				model.setFormat(null);
				startBackgroundThread();
				break;
				
			case "Save":
				try {
					model.saveDocument(view.getStyledDocument(), false);
				}
				catch(Exception ex) {
					//ex.printStackTrace();
					JOptionPane.showMessageDialog(view, "Error on file saving", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
				
			case "Save as":
				try {
					model.saveDocument(view.getStyledDocument(), true);
				}
				catch(Exception ex) {
					//ex.printStackTrace();
					JOptionPane.showMessageDialog(view, "Error on file saving", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
				
			case "Undo":
				if(!view.btnRedo.isEnabled())
					view.btnRedo.setEnabled(true);
				model.loadPreviousMemento(view.getStyledDocument());
				if(model.getOriginator().getMemento() != null) {
					view.setTextPaneText(model.getOriginator().getMemento().getTextState(), model.getOriginator().getMemento().getColorState());
				}
				if(view.textPane.getText().equals(""))
					view.btnUndo.setEnabled(false);
				break;
				
			case "Redo":
				if(!view.btnUndo.isEnabled())
					view.btnUndo.setEnabled(true);
				model.loadNextMemento(view.getStyledDocument());
				if(model.getOriginator().getMemento() != null) {
					view.setTextPaneText(model.getOriginator().getMemento().getTextState(), model.getOriginator().getMemento().getColorState());
				}
				break;
				
			case "Cut": case "Copy":
				start = view.textPane.getSelectionStart();
				end = view.textPane.getSelectionEnd();
				
				if (start != end) {
					boolean result = ClipboardManager.saveClipboard(view.getStyledDocument(), start, end);
					if (!result) {
						JOptionPane.showMessageDialog(view, "Error on Copying text", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						if (arg0.getActionCommand().equals("Cut"))
							view.deleteSelectedText();
						
						if (!view.btnPaste.isEnabled())
							view.btnPaste.setEnabled(true);
					}
				}
				break;
				
			case "Paste":
				start = view.textPane.getSelectionStart();
				end = view.textPane.getSelectionEnd();
				if (start != end)
					view.deleteSelectedText();
				pasteClipboard(start);
				break;
				
			default:
				System.out.println("Este boton no hace nada");
				break;
		}
		
	}

	private void pasteClipboard(int pos) {
		ArrayList<Color> color = ClipboardManager.getColors();
		ArrayList<String> text = ClipboardManager.getText();
		
		for (int i = 0; i < color.size(); i++) {
			view.setTextPaneColor(color.get(i));
			view.textPaneAddText(text.get(i), pos);
			pos += text.get(i).length();
		}
	}
	
	private void restartTextEditor() {
		editorEnabled = false;
		try {
			Thread.sleep(mementoTimeSave+1500);
			Caretaker.getInstance().restartStacks();
		}
		catch (InterruptedException e) {
			//e.printStackTrace();
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
