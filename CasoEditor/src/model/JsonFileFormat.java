package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class JsonFileFormat extends AFileFormat {
	
	public JsonFileFormat(File file) {
		super(file);		
	}
	
	@Override
	public void parseFile() throws IOException {
	    InputStream stream = new FileInputStream(file);
	    JsonReader reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));
	    
	    reader.beginArray();
	    while (reader.hasNext())
	    	readJsonElement(reader);
	    
	    reader.endArray();
	    reader.close();
	    
	}
	
	public void readJsonElement(JsonReader reader) throws IOException {
	    reader.beginObject();
	    String name;
	    while (reader.hasNext()) {
	    	name = reader.nextName();
	    	if (name.equals("color"))
	    		addColor(reader.nextString());
	    	else if (name.equals("spelling"))
	    		addText(reader.nextString());
	    }
	    reader.endObject();
	}

	@Override
	public void saveFile() throws IOException {
		OutputStream stream = new FileOutputStream(file);
		JsonWriter writer = new JsonWriter(new OutputStreamWriter(stream, "UTF-8"));
		writer.setIndent("  ");

		writer.beginArray();
		for (int i = 0; i < text.size(); i++)
			writeJsonElement(writer, i);
		writer.endArray();
		writer.close();
	}
	
	private void writeJsonElement(JsonWriter writer, int index) throws IOException {
		writer.beginObject();
		writer.name("color").value(getColor(index));
		writer.name("spelling").value(getText(index));
		writer.endObject();
	}
}
