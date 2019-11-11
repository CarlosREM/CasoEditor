package model;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFFileFormat extends AFileFormat{

	public PDFFileFormat(File file) {
		super(file);
	}

	@Override
	public void parseFile() throws Exception {
		System.out.println("No se leen PDF");
	}

	@Override
	public void saveFile() throws Exception {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		
		document.open();
	
		for(int i=0; i<text.size(); i++) {
			StringBuilder builder = new StringBuilder();
			Color color = TextColor.valueOf(getColor(i)).getColor();
			Font font = FontFactory.getFont(FontFactory.COURIER,16, new BaseColor(color.getRGB()));
			builder.append(getText(i));
			Chunk chunk = new Chunk(builder.toString(), font);
			document.add(chunk);
		}
		
		document.close();
	}
}
