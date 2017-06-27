package burp;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class AutobotKnowledgeBase {
	AutobotSettings settings;
	ArrayList<AutobotKnowledgeBaseIssue> records;
	IBurpExtenderCallbacks mycallback;
	
	public AutobotKnowledgeBase (IBurpExtenderCallbacks callback, AutobotSettings settings) {
		this.mycallback = callback;
		this.settings = settings;
		this.records = parseXML();
	}
	
	private ArrayList<AutobotKnowledgeBaseIssue> parseXML() {
		ArrayList<AutobotKnowledgeBaseIssue> records = new ArrayList<AutobotKnowledgeBaseIssue>();
		try {
			//Resource : https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
			//Setting Up XML file parsers
			File fXmlFile = new File(this.settings.getKBL());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			
			//Getting all records
			NodeList recordNodes = doc.getElementsByTagName("record");
			for (int i = 0; i < recordNodes.getLength(); i++) {
				Node record = recordNodes.item(i);
				records.add(new AutobotKnowledgeBaseIssue(record));
				this.mycallback.printOutput(record.toString());
			}
		} catch (Exception e) {
			this.mycallback.printError(e.getMessage());
		}
		return records;
	}
}
