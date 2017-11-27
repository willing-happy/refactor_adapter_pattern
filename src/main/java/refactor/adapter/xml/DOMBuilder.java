package refactor.adapter.xml;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Stack;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DOMBuilder extends AbstractBuilder {
	private Document doc;
	private NodeAdapter rootAdapter;
	private NodeAdapter parentAdapter;

	public DOMBuilder(String rootName) {
		init(rootName);
	}

	public NodeAdapter createElement(String child) {
		Element childNode = doc.createElement(child);
		return new ElementAdapter(childNode);
	}

	public void addValue(String value) {
		getCurrent().addValue(doc.createTextNode(value));
	}

	public Document getDocument() {
		return doc;
	}

	protected void init(String rootName) {
		this.doc = new DocumentImpl();
		setRoot(createElement(rootName));
		doc.appendChild((Node) getRoot().getNode());
		setCurrent(getRoot());
		setParent(getRoot());
		history = new Stack();
		history.push(getCurrent());
	}

	public void startNewBuild(String rootName) {
		init(rootName);
	}

	public String toString() {
		OutputFormat format = new OutputFormat(doc);
		StringWriter stringOut = new StringWriter();
			XMLSerializer serial = new XMLSerializer(stringOut, format);
		try {
			serial.asDOMSerializer();
			serial.serialize(doc.getDocumentElement());
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return ioe.getMessage();
		}
		return stringOut.toString();
	}
}
