package view;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class EditorView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	private JMenuItem itemNew,
					  itemOpen,
					  itemSave,
					  itemSaveAs,
					  itemExit,
					  itemUndo,
					  itemRedo,
					  itemCut,
					  itemCopy,
					  itemPaste;
	
	public JButton btnNew,
					btnOpen,
					btnSave,
					btnSaveAs,
					btnUndo,
					btnRedo,
					btnCut,
					btnCopy,
					btnPaste;
	
	/**
	 * Create the frame.
	 */
	public EditorView() {
		setTitle("Memento editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		
		// MENU BAR
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		itemNew = new JMenuItem("New");
		mnFile.add(itemNew);
		
		itemOpen = new JMenuItem("Open");
		mnFile.add(itemOpen);
		
		itemSave = new JMenuItem("Save");
		mnFile.add(itemSave);
		
		itemSaveAs = new JMenuItem("Save As...");
		mnFile.add(itemSaveAs);
		
		JSeparator itemFileSeparator = new JSeparator();
		mnFile.add(itemFileSeparator);
		
		itemExit = new JMenuItem("Exit");
		mnFile.add(itemExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		itemPaste = new JMenuItem("Paste");
		mnEdit.add(itemPaste);
		
		itemRedo = new JMenuItem("Redo");
		mnEdit.add(itemRedo);
		
		JSeparator itemEditSeparator = new JSeparator();
		mnEdit.add(itemEditSeparator);
		
		itemCut = new JMenuItem("Cut");
		mnEdit.add(itemCut);
		
		itemCopy = new JMenuItem("Copy");
		mnEdit.add(itemCopy);
		
		itemPaste = new JMenuItem("Paste");
		mnEdit.add(itemPaste);
		
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
		ImageIcon iconSaveDocument = new ImageIcon(EditorView.class.getResource("/resources/SaveIcon.png"));
		Image imageSaveDocument = iconSaveDocument.getImage();
		Image resizedImageSaveDocument = imageSaveDocument.getScaledInstance(btnSave.getWidth()-10, btnSave.getHeight()-10, Image.SCALE_SMOOTH);
		btnSave.setIcon(new ImageIcon(resizedImageSaveDocument));
		toolBar.add(btnSave);
		
		btnSaveAs = new JButton("");
		btnSaveAs.setEnabled(false);
		btnSaveAs.setToolTipText("Save as");
		btnSaveAs.setBounds(0, 0, 50, 50);
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
		ImageIcon iconUndo = new ImageIcon(EditorView.class.getResource("/resources/UndoIcon.png"));
		Image imageUndo = iconUndo.getImage();
		Image resizedImageUndo = imageUndo.getScaledInstance(btnUndo.getWidth()-10, btnUndo.getHeight()-10, Image.SCALE_SMOOTH);
		btnUndo.setIcon(new ImageIcon(resizedImageUndo));
		toolBar.add(btnUndo);
		
		btnRedo = new JButton("");
		btnRedo.setEnabled(false);
		btnRedo.setToolTipText("Redo");
		btnRedo.setBounds(0, 0, 50, 50);
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
		ImageIcon iconCut = new ImageIcon(EditorView.class.getResource("/resources/CutIcon.png"));
		Image imageCut = iconCut.getImage();
		Image resizedImageCut = imageCut.getScaledInstance(btnCut.getWidth()-10, btnCut.getHeight()-10, Image.SCALE_SMOOTH);
		btnCut.setIcon(new ImageIcon(resizedImageCut));
		toolBar.add(btnCut);
		
		btnCopy = new JButton("");
		btnCopy.setEnabled(false);
		btnCopy.setToolTipText("Copy");
		btnCopy.setBounds(0, 0, 50, 50);
		ImageIcon iconCopy = new ImageIcon(EditorView.class.getResource("/resources/CopyIcon.png"));
		Image imageCopy = iconCopy.getImage();
		Image resizedImageCopy = imageCopy.getScaledInstance(btnCopy.getWidth()-10, btnCopy.getHeight()-10, Image.SCALE_SMOOTH);
		btnCopy.setIcon(new ImageIcon(resizedImageCopy));
		toolBar.add(btnCopy);

		btnPaste = new JButton("");
		btnPaste.setEnabled(false);
		btnPaste.setToolTipText("Paste");
		btnPaste.setBounds(0, 0, 50, 50);
		ImageIcon iconPaste = new ImageIcon(EditorView.class.getResource("/resources/PasteIcon.png"));
		Image imagePaste = iconPaste.getImage();
		Image resizedImagePaste = imagePaste.getScaledInstance(btnPaste.getWidth()-10, btnPaste.getHeight()-10, Image.SCALE_SMOOTH);
		btnPaste.setIcon(new ImageIcon(resizedImagePaste));
		toolBar.add(btnPaste);
		
		// TEXT PANE

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JTextPane textPane = new JTextPane();
		textPane.setEnabled(false);
		scrollPane.setViewportView(textPane);
	}
}
