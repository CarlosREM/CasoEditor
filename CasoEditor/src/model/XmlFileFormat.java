package model;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class XmlFileFormat extends AFileFormat {

	public XmlFileFormat(File file) {
		super(file);
	}

	@Override
	public String parseFile() throws IOException, ParserConfigurationException, SAXException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		
		NodeList nList = doc.getElementsByTagName("text");
        
		StringBuilder textBuilder = new StringBuilder();
        Node node;
		for (int i = 0; i < nList.getLength(); i++) {
           node = nList.item(i);
           if (node.getNodeType() == Node.ELEMENT_NODE) {
        	   Element elem = (Element) node;
        	   System.out.println(elem.getAttribute("color"));
        	   textBuilder.append(elem.getTextContent());
           }
        }
		return textBuilder.toString();
	}

}