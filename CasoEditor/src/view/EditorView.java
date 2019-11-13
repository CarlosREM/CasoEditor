package view;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AbstractDocument.LeafElement;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.EditorModel;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.awt.event.ActionEvent;

public class EditorView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	public JButton btnNew,
					btnOpen,
					btnSave,
					btnSaveAs,
					btnUndo,
					btnRedo,
					btnCut,
					btnCopy,
					btnPaste,
					btnColorBlack,
					btnColorRed,
					btnColorGreen,
					btnColorBlue,
					btnColorOrange;
	
	public JTextPane textPane;
	
	private Color fontColor = Color.BLACK;
	
	/**
	 * Create the frame.
	 */
	public EditorView() {
		setTitle("Memento editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		
		// CONTENT PANE
		
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// > TOOLBAR
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		btnNew = new JButton("");
		btnNew.setToolTipText("New");
		btnNew.setBounds(0, 0, 50, 50);
		btnNew.setActionCommand("New");
		ImageIcon iconNewDocument = new ImageIcon(EditorView.class.getResource("/resources/NewIcon.png"));
		Image imageNewDocument = iconNewDocument.getImage();
		Image resizedImageNewDocument = imageNewDocument.getScaledInstance(btnNew.getWidth()-10, btnNew.getHeight()-10, Image.SCALE_SMOOTH);
		btnNew.setIcon(new ImageIcon(resizedImageNewDocument));
		toolBar.add(btnNew);
		
		btnOpen = new JButton("");
		btnOpen.setToolTipText("Open");
		btnOpen.setBounds(0, 0, 50, 50);
		btnOpen.setActionCommand("Open");
		ImageIcon iconOpenDocument = new ImageIcon(EditorView.class.getResource("/resources/OpenIcon.png"));
		Image imageOpenDocument = iconOpenDocument.getImage();
		Image resizedImageOpenDocument = imageOpenDocument.getScaledInstance(btnOpen.getWidth()-10, btnOpen.getHeight()-10, Image.SCALE_SMOOTH);
		btnOpen.setIcon(new ImageIcon(resizedImageOpenDocument));
		toolBar.add(btnOpen);
		
		btnSave = new JButton("");
		btnSave.setEnabled(false);
		btnSave.setToolTipText("Save");
		btnSave.setBounds(0, 0, 50, 50);
		btnSave.setActionCommand("Save");
		ImageIcon iconSaveDocument = new ImageIcon(EditorView.class.getResource("/resources/SaveIcon.png"));
		Image imageSaveDocument = iconSaveDocument.getImage();
		Image resizedImageSaveDocument = imageSaveDocument.getScaledInstance(btnSave.getWidth()-10, btnSave.getHeight()-10, Image.SCALE_SMOOTH);
		btnSave.setIcon(new ImageIcon(resizedImageSaveDocument));
		toolBar.add(btnSave);
		
		btnSaveAs = new JButton("");
		btnSaveAs.setEnabled(false);
		btnSaveAs.setToolTipText("Save as");
		btnSaveAs.setBounds(0, 0, 50, 50);
		btnSaveAs.setActionCommand("Save as");
		ImageIcon iconSaveAsDocument = new ImageIcon(EditorView.class.getResource("/resources/SaveAsIcon.png"));
		Image imageSaveAsDocument = iconSaveAsDocument.getImage();
		Image resizedImageSaveAsDocument = imageSaveAsDocument.getScaledInstance(btnSaveAs.getWidth()-10, btnSaveAs.getHeight()-10, Image.SCALE_SMOOTH);
		btnSaveAs.setIcon(new ImageIcon(resizedImageSaveAsDocument));
		toolBar.add(btnSaveAs);
		
		toolBar.add(new JToolBar.Separator());
		
		btnUndo = new JButton("");
		btnUndo.setEnabled(false);
		btnUndo.setToolTipText("Undo");
		btnUndo.setBounds(0, 0, 50, 50);
		btnUndo.setActionCommand("Undo");
		ImageIcon iconUndo = new ImageIcon(EditorView.class.getResource("/resources/UndoIcon.png"));
		Image imageUndo = iconUndo.getImage();
		Image resizedImageUndo = imageUndo.getScaledInstance(btnUndo.getWidth()-10, btnUndo.getHeight()-10, Image.SCALE_SMOOTH);
		btnUndo.setIcon(new ImageIcon(resizedImageUndo));
		toolBar.add(btnUndo);
		
		btnRedo = new JButton("");
		btnRedo.setEnabled(false);
		btnRedo.setToolTipText("Redo");
		btnRedo.setBounds(0, 0, 50, 50);
		btnRedo.setActionCommand("Redo");
		ImageIcon iconRedo = new ImageIcon(EditorView.class.getResource("/resources/RedoIcon.png"));
		Image imageRedo = iconRedo.getImage();
		Image resizedImageRedo = imageRedo.getScaledInstance(btnRedo.getWidth()-10, btnRedo.getHeight()-10, Image.SCALE_SMOOTH);
		btnRedo.setIcon(new ImageIcon(resizedImageRedo));
		toolBar.add(btnRedo);
		
		toolBar.add(new JToolBar.Separator());
		
		btnCut = new JButton("");
		btnCut.setEnabled(false);
		btnCut.setToolTipText("Cut");
		btnCut.setBounds(0, 0, 50, 50);
		btnCut.setActionCommand("Cut");
		ImageIcon iconCut = new ImageIcon(EditorView.class.getResource("/resources/CutIcon.png"));
		Image imageCut = iconCut.getImage();
		Image resizedImageCut = imageCut.getScaledInstance(btnCut.getWidth()-10, btnCut.getHeight()-10, Image.SCALE_SMOOTH);
		btnCut.setIcon(new ImageIcon(resizedImageCut));
		toolBar.add(btnCut);
		
		btnCopy = new JButton("");
		btnCopy.setEnabled(false);
		btnCopy.setToolTipText("Copy");
		btnCopy.setBounds(0, 0, 50, 50);
		btnCopy.setActionCommand("Copy");
		ImageIcon iconCopy = new ImageIcon(EditorView.class.getResource("/resources/CopyIcon.png"));
		Image imageCopy = iconCopy.getImage();
		Image resizedImageCopy = imageCopy.getScaledInstance(btnCopy.getWidth()-10, btnCopy.getHeight()-10, Image.SCALE_SMOOTH);
		btnCopy.setIcon(new ImageIcon(resizedImageCopy));
		toolBar.add(btnCopy);

		btnPaste = new JButton("");
		btnPaste.setEnabled(false);
		btnPaste.setToolTipText("Paste");
		btnPaste.setBounds(0, 0, 50, 50);
		btnPaste.setActionCommand("Paste");
		ImageIcon iconPaste = new ImageIcon(EditorView.class.getResource("/resources/PasteIcon.png"));
		Image imagePaste = iconPaste.getImage();
		Image resizedImagePaste = imagePaste.getScaledInstance(btnPaste.getWidth()-10, btnPaste.getHeight()-10, Image.SCALE_SMOOTH);
		btnPaste.setIcon(new ImageIcon(resizedImagePaste));
		toolBar.add(btnPaste);
		
		toolBar.add(new JToolBar.Separator());

		btnColorBlack = new JButton();
		btnColorBlack.setEnabled(false);
		btnColorBlack.setBounds(0, 0, 100, 100);
		btnColorBlack.setBackground(Color.BLACK);
		btnColorBlack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fontColor != Color.BLACK) {
					fontColor = Color.BLACK;
					setTextPaneColor(Color.BLACK);
				}
			}
		});
		toolBar.add(btnColorBlack);
		
		btnColorRed = new JButton();
		btnColorRed.setEnabled(false);
		btnColorRed.setBounds(0, 0, 100, 100);
		btnColorRed.setBackground(Color.RED);
		btnColorRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fontColor != Color.RED) {
					fontColor = Color.RED;
					setTextPaneColor(Color.RED);
				}
			}
		});
		toolBar.add(btnColorRed);
		
		btnColorGreen = new JButton();
		btnColorGreen.setEnabled(false);
		btnColorGreen.setBounds(0, 0, 100, 100);
		btnColorGreen.setBackground(Color.GREEN);
		btnColorGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fontColor != Color.GREEN) {
					fontColor = Color.GREEN;
					setTextPaneColor(Color.GREEN);
				}
			}
		});
		toolBar.add(btnColorGreen);
		
		btnColorBlue = new JButton();
		btnColorBlue.setEnabled(false);
		btnColorBlue.setBounds(0, 0, 100, 100);
		btnColorBlue.setBackground(Color.BLUE);
		btnColorBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fontColor != Color.BLUE) {
					fontColor = Color.BLUE;
					setTextPaneColor(Color.BLUE);
				}
			}
		});
		toolBar.add(btnColorBlue);
		
		btnColorOrange = new JButton();
		btnColorOrange.setEnabled(false);
		btnColorOrange.setBounds(0, 0, 100, 100);
		btnColorOrange.setBackground(Color.ORANGE);
		btnColorOrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fontColor != Color.ORANGE) {
					fontColor = Color.ORANGE;
					setTextPaneColor(Color.ORANGE);
				}
			}
		});
		toolBar.add(btnColorOrange);
		
		// TEXT PANE

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textPane = new JTextPane();
		textPane.setForeground(Color.BLACK);
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPane.setEnabled(false);
		scrollPane.setViewportView(textPane);
	}
	
	private void setTextPaneColor(Color color) {
        // Get previous attributes, just in case
        MutableAttributeSet attrs = textPane.getInputAttributes();

        // Set the font color
        StyleConstants.setForeground(attrs, color);

        // Retrieve the pane's document object
        StyledDocument doc = textPane.getStyledDocument();
        
        doc.setCharacterAttributes(doc.getLength(), 0, attrs, false);
	}
	
	private void textPaneAddText(String newText) {
		try {
			StyledDocument doc = textPane.getStyledDocument();
			doc.insertString(doc.getLength(), newText, textPane.getInputAttributes());
		}
		catch(BadLocationException exc) {
			exc.printStackTrace();
		}
	}
	
	public void setTextPaneText(ArrayList<String> text, ArrayList<String> color) {
		textPane.setText("");
		
		String currentText;
		for (int i = 0; i < text.size(); i++) {
			currentText = text.get(i);
			if(color.size() == text.size())
				setTextPaneColor(EditorModel.getColor(color.get(i)));
			textPaneAddText(currentText);
		}
	}
	
	public StyledDocument getStyledDocument() {
		return textPane.getStyledDocument();
	}
	
	public boolean isEmpty() {
		return textPane.getText().isEmpty();
	}
	
	public void setEnabled(boolean value) {
		btnSave.setEnabled(value);
		btnSaveAs.setEnabled(value);
		btnCut.setEnabled(value);
		btnCopy.setEnabled(value);
		btnPaste.setEnabled(value);
		btnRedo.setEnabled(value);
		
		btnColorBlack.setEnabled(value);
		btnColorRed.setEnabled(value);
		btnColorGreen.setEnabled(value);
		btnColorBlue.setEnabled(value);
		btnColorOrange.setEnabled(value);
		
		textPane.setEnabled(value);
	}
}
