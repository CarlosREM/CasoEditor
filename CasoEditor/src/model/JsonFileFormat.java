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
	public String parseFile() throws IOException {
	    InputStream stream = new FileInputStream(file);
	    JsonReader reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));
	    
	    StringBuilder textBuilder = new StringBuilder();
	    reader.beginArray();
	    while (reader.hasNext())
	    	textBuilder.append(readJsonElement(reader));
	    
	    reader.endArray();
	    reader.close();
	    
	    return textBuilder.toString();
	}
	
	public String readJsonElement(JsonReader reader) throws IOException {
		String text = "";
		
	    reader.beginObject();
	    String name;
	    while (reader.hasNext()) {
	    	name = reader.nextName();
	    	if (name.equals("color"))
	    		System.out.println(reader.nextString());
	    	else if (name.equals("spelling"))
	    		text = reader.nextString();
	    }
	    reader.endObject();

		return text;
	}
}
