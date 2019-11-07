package model;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;

public class XmlFileFormat extends AFileFormat {

	public XmlFileFormat(File file) {
		super(file);
	}

	@Override
	public void parseFile() throws IOException, ParserConfigurationException, SAXException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		
		NodeList nList = doc.getElementsByTagName("text");
        
        Node node;
		for (int i = 0; i < nList.getLength(); i++) {
           node = nList.item(i);
           if (node.getNodeType() == Node.ELEMENT_NODE) {
        	   Element elem = (Element) node;
        	   addColor(elem.getAttribute("color"));
        	   addText(elem.getTextContent());
           }
        }
	}

	@Override
	public void saveFile() throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		
        // root element
        Element root = doc.createElement("file");
        doc.appendChild(root);

        Element textNode;
        Attr colorAttr;
        for (int i = 0; i < text.size(); i++) {
        	textNode = doc.createElement("text");
        	colorAttr = doc.createAttribute("color");
        	colorAttr.setValue(getColor(i));
            textNode.setAttributeNode(colorAttr);
        	textNode.setTextContent(getText(i));
        	
        	root.appendChild(textNode);
        }

        
        // create the xml file
        // transform the DOM Object to an XML File
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource domSource = new DOMSource(doc);
        StreamResult streamResult = new StreamResult(file);
        
        transformer.transform(domSource, streamResult);

	}

}