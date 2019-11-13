package view;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.AbstractDocument.LeafElement;

import model.EditorModel;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

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
				   btnPaste;
	
	private JToggleButton btnColorBlack,
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
		
		ImageIcon imgIcon;
		Image img, rImg;
		
		btnNew = new JButton("");
		btnNew.setBounds(0, 0, 50, 50);
		btnNew.setToolTipText("New");
		btnNew.setActionCommand("New");
		btnNew.setFocusable(false);
		imgIcon = new ImageIcon(EditorView.class.getResource("/resources/NewIcon.png"));
		img = imgIcon.getImage();
		rImg = img.getScaledInstance(btnNew.getWidth()-10, btnNew.getHeight()-10, Image.SCALE_SMOOTH);
		btnNew.setIcon(new ImageIcon(rImg));
		toolBar.add(btnNew);
		
		btnOpen = new JButton("");
		btnOpen.setBounds(0, 0, 50, 50);
		btnOpen.setToolTipText("Open");
		btnOpen.setActionCommand("Open");
		btnOpen.setFocusable(false);
		imgIcon = new ImageIcon(EditorView.class.getResource("/resources/OpenIcon.png"));
		img = imgIcon.getImage();
		rImg = img.getScaledInstance(btnOpen.getWidth()-10, btnOpen.getHeight()-10, Image.SCALE_SMOOTH);
		btnOpen.setIcon(new ImageIcon(rImg));
		toolBar.add(btnOpen);
		
		btnSave = new JButton("");
		btnSave.setBounds(0, 0, 50, 50);
		btnSave.setToolTipText("Save");
		btnSave.setActionCommand("Save");
		btnSave.setEnabled(false);
		btnSave.setFocusable(false);
		imgIcon = new ImageIcon(EditorView.class.getResource("/resources/SaveIcon.png"));
		img = imgIcon.getImage();
		rImg = img.getScaledInstance(btnSave.getWidth()-10, btnSave.getHeight()-10, Image.SCALE_SMOOTH);
		btnSave.setIcon(new ImageIcon(rImg));
		toolBar.add(btnSave);
		
		btnSaveAs = new JButton("");
		btnSaveAs.setBounds(0, 0, 50, 50);
		btnSaveAs.setToolTipText("Save as");
		btnSaveAs.setActionCommand("Save as");
		btnSaveAs.setEnabled(false);
		btnSaveAs.setFocusable(false);
		imgIcon = new ImageIcon(EditorView.class.getResource("/resources/SaveAsIcon.png"));
		img = imgIcon.getImage();
		rImg = img.getScaledInstance(btnSaveAs.getWidth()-10, btnSaveAs.getHeight()-10, Image.SCALE_SMOOTH);
		btnSaveAs.setIcon(new ImageIcon(rImg));
		toolBar.add(btnSaveAs);
		
		toolBar.add(new JToolBar.Separator());
		
		btnUndo = new JButton("");
		btnUndo.setBounds(0, 0, 50, 50);
		btnUndo.setToolTipText("Undo");
		btnUndo.setActionCommand("Undo");
		btnUndo.setEnabled(false);
		btnUndo.setFocusable(false);
		imgIcon = new ImageIcon(EditorView.class.getResource("/resources/UndoIcon.png"));
		img = imgIcon.getImage();
		rImg = img.getScaledInstance(btnUndo.getWidth()-10, btnUndo.getHeight()-10, Image.SCALE_SMOOTH);
		btnUndo.setIcon(new ImageIcon(rImg));
		toolBar.add(btnUndo);
		
		btnRedo = new JButton("");
		btnRedo.setBounds(0, 0, 50, 50);
		btnRedo.setToolTipText("Redo");
		btnRedo.setActionCommand("Redo");
		btnRedo.setEnabled(false);
		btnRedo.setFocusable(false);
		imgIcon = new ImageIcon(EditorView.class.getResource("/resources/RedoIcon.png"));
		img = imgIcon.getImage();
		rImg = img.getScaledInstance(btnRedo.getWidth()-10, btnRedo.getHeight()-10, Image.SCALE_SMOOTH);
		btnRedo.setIcon(new ImageIcon(rImg));
		toolBar.add(btnRedo);
		
		toolBar.add(new JToolBar.Separator());
		
		btnCut = new JButton("");
		btnCut.setBounds(0, 0, 50, 50);
		btnCut.setToolTipText("Cut");
		btnCut.setActionCommand("Cut");
		btnCut.setEnabled(false);
		btnCut.setFocusable(false);
		imgIcon = new ImageIcon(EditorView.class.getResource("/resources/CutIcon.png"));
		img = imgIcon.getImage();
		rImg = img.getScaledInstance(btnCut.getWidth()-10, btnCut.getHeight()-10, Image.SCALE_SMOOTH);
		btnCut.setIcon(new ImageIcon(rImg));
		toolBar.add(btnCut);
		
		btnCopy = new JButton("");
		btnCopy.setBounds(0, 0, 50, 50);
		btnCopy.setToolTipText("Copy");
		btnCopy.setActionCommand("Copy");
		btnCopy.setEnabled(false);
		btnCopy.setFocusable(false);
		imgIcon = new ImageIcon(EditorView.class.getResource("/resources/CopyIcon.png"));
		img = imgIcon.getImage();
		rImg = img.getScaledInstance(btnCopy.getWidth()-10, btnCopy.getHeight()-10, Image.SCALE_SMOOTH);
		btnCopy.setIcon(new ImageIcon(rImg));
		toolBar.add(btnCopy);

		btnPaste = new JButton("");
		btnPaste.setBounds(0, 0, 50, 50);
		btnPaste.setToolTipText("Paste");
		btnPaste.setActionCommand("Paste");
		btnPaste.setEnabled(false);
		btnPaste.setFocusable(false);
		imgIcon = new ImageIcon(EditorView.class.getResource("/resources/PasteIcon.png"));
		img = imgIcon.getImage();
		rImg = img.getScaledInstance(btnPaste.getWidth()-10, btnPaste.getHeight()-10, Image.SCALE_SMOOTH);
		btnPaste.setIcon(new ImageIcon(rImg));
		toolBar.add(btnPaste);
		
		toolBar.add(new JToolBar.Separator());

		btnColorBlack = new JToggleButton();
		btnColorBlack.setBounds(0, 0, 100, 100);
		btnColorBlack.setEnabled(false);
		btnColorBlack.setFocusable(false);
		btnColorBlack.setSelected(true);
		btnColorBlack.setBackground(Color.BLACK);
		btnColorBlack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fontColor = Color.BLACK;
				setTextPaneColor(Color.BLACK);
				colorButtonSelected();
			}
		});
		toolBar.add(btnColorBlack);
		
		btnColorRed = new JToggleButton();
		btnColorRed.setBounds(0, 0, 100, 100);
		btnColorRed.setEnabled(false);
		btnColorRed.setFocusable(false);
		btnColorRed.setBackground(Color.RED);
		btnColorRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fontColor = Color.RED;
				setTextPaneColor(Color.RED);
				colorButtonSelected();
			}
		});
		toolBar.add(btnColorRed);
		
		btnColorGreen = new JToggleButton();
		btnColorGreen.setBounds(0, 0, 100, 100);
		btnColorGreen.setEnabled(false);
		btnColorGreen.setFocusable(false);
		btnColorGreen.setBackground(Color.GREEN);
		btnColorGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fontColor = Color.GREEN;
				setTextPaneColor(Color.GREEN);
				colorButtonSelected();
			}
		});
		toolBar.add(btnColorGreen);
		
		btnColorBlue = new JToggleButton();
		btnColorBlue.setBounds(0, 0, 100, 100);
		btnColorBlue.setEnabled(false);
		btnColorBlue.setFocusable(false);
		btnColorBlue.setBackground(Color.BLUE);
		btnColorBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fontColor = Color.BLUE;
				setTextPaneColor(Color.BLUE);
				colorButtonSelected();
			}
		});
		toolBar.add(btnColorBlue);
		
		btnColorOrange = new JToggleButton();
		btnColorOrange.setBounds(0, 0, 100, 100);
		btnColorOrange.setEnabled(false);
		btnColorOrange.setFocusable(false);
		btnColorOrange.setBackground(Color.ORANGE);
		btnColorOrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fontColor = Color.ORANGE;
				setTextPaneColor(Color.ORANGE);
				colorButtonSelected();
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
		textPane.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				getCurrentColor();
				colorButtonSelected();
			}
		});
		scrollPane.setViewportView(textPane);
	}
	
	public void setTextPaneColor(Color color) {
        // Get previous attributes, just in case
        MutableAttributeSet attrs = textPane.getInputAttributes();

        // Set the font color
        StyleConstants.setForeground(attrs, color);

        // Retrieve the pane's document object
        StyledDocument doc = textPane.getStyledDocument();
        
        int start = textPane.getSelectionStart(),
        	end = textPane.getSelectionEnd();

		
        doc.setCharacterAttributes(start, end-start, attrs, false);
	}
	
	public void textPaneAddText(String newText, int offset) {
		try {
			StyledDocument doc = textPane.getStyledDocument();
			if (offset < 0)
				offset = doc.getLength();
			doc.insertString(offset, newText, textPane.getInputAttributes());
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
			if(text.size() == color.size())
				setTextPaneColor(EditorModel.getColor(color.get(i)));
			textPaneAddText(currentText, -1);
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
		
		btnColorBlack.setEnabled(value);
		btnColorRed.setEnabled(value);
		btnColorGreen.setEnabled(value);
		btnColorBlue.setEnabled(value);
		btnColorOrange.setEnabled(value);
		
		textPane.setEnabled(value);
	}
	
	public void deleteSelectedText() {
		int start = textPane.getSelectionStart(),
			end = textPane.getSelectionEnd();
		try {
			getStyledDocument().remove(start, end-start);
		}
		catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	private void colorButtonSelected() {
		btnColorBlack.setSelected(fontColor == Color.BLACK);
		btnColorRed.setSelected(fontColor == Color.RED);
		btnColorGreen.setSelected(fontColor == Color.GREEN);
		btnColorBlue.setSelected(fontColor == Color.BLUE);
		btnColorOrange.setSelected(fontColor == Color.ORANGE);
	}
	
	private void getCurrentColor() {
		int pos = textPane.getCaretPosition() - 1;
		LeafElement set = (LeafElement) getStyledDocument().getCharacterElement(pos).getAttributes();
		fontColor = (Color) set.getAttribute(StyleConstants.Foreground);
	}
}
