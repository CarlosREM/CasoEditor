package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.stream.JsonReader;

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
}
