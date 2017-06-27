package burp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.w3c.dom.Node;

public class AutobotKnowledgeBaseIssue {
	//Make a function to parse the rawXML as a JLabel
	//Make a function to get table representation of rawXML
	Node rawXML;
	Map<String, String> issueMap = new HashMap<String, String>();
	public AutobotKnowledgeBaseIssue (Node rawXML) {
		this.rawXML = rawXML;
	}

	public void setIssueName(String name) {
		// Name of the issue
		this.issueMap.put("name", name);
	}
	public String getIssueName() {
		// Name of the issue
		return this.issueMap.get("name");
	}

	public void setIssueType(String type) {
		// Hexadecimal Numbers
		this.issueMap.put("type", type);
	}
	public String getIssueType() {
		// Hexadecimal Numbers
		return this.issueMap.get("type");
	}

	public void setSeverity(String severity) {
		// Low, Medium, High, Information
		this.issueMap.put("severity", severity);
	}
	public String getSeverity() {
		// Low, Medium, High, Information
		return this.issueMap.get("severity");
	}
	
	public void setIssueBackground(String background) {
		// Issue Description
		this.issueMap.put("background", background);
	}
	public String getIssueBackground() {
		// Issue Description
		return this.issueMap.get("background");
	}

	public void setRemediationBackground(String remediation) {
		// Remediation Description
		this.issueMap.put("remediation", remediation);
	}
	public String getRemediationBackground() {
		// Remediation Description
		return this.issueMap.get("remediation");
	}
	
	public void setExtraInformation(String key, String value) {
		this.issueMap.put(key, value);
	}
	public String getExtraInformation(String key) {
		return this.issueMap.get(key);
	}
	
	public ArrayList<String> getAllExtraInformation() {
		//Setting up required resources
		ArrayList<String> extras = new ArrayList<String>();
		String[] regularKeys = {"name", "type", "severity",
                "background", "remediation"};
		Map<String,String> cloneMap = this.issueMap;
		
		//Removing regular keys from map
		for (String key: regularKeys) {
			if (cloneMap.containsKey(key)) {
			     cloneMap.remove(key);
			 }
		}
		
		//Storing extra info in arraylist (key then value)
	    Iterator<Entry<String, String>> it = cloneMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String,String> pair = (Map.Entry<String,String>)it.next();
	        it.remove(); // avoids a ConcurrentModificationException
	        extras.add(pair.getKey());
	        extras.add(pair.getValue());
	    }
	    
	    return extras;
	}

}
