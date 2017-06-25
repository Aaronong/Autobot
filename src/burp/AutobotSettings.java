package burp;

import java.util.ArrayList;

public class AutobotSettings {
	IBurpExtenderCallbacks mycallback;
	String knowledgeBaseLocation;
	ArrayList<ScannerObject> activeScanners;
	ArrayList<ScannerObject> passiveScanners;
	
	public AutobotSettings (IBurpExtenderCallbacks callback) {
		this.mycallback = callback;
		this.activeScanners = new ArrayList<ScannerObject>();
		this.passiveScanners = new ArrayList<ScannerObject>();
	}
	
	public String getKBL() {
		return this.mycallback.loadExtensionSetting("AutobotKnowledgeBaseSetting");
	}
	
	public void setKBL(String kbl) {
		this.mycallback.saveExtensionSetting("AutobotKnowledgeBaseSetting", kbl);
	}
	
	public ArrayList<ScannerObject> getActiveScanners() {
		return this.activeScanners;
	}
	
	public ArrayList<ScannerObject> getPassiveScanners() {
		return this.passiveScanners;
	}
	
	public boolean getScannerSetting(String scannerName) {
		String stateStr = this.mycallback.loadExtensionSetting("AutobotScanner"+scannerName);
		return (stateStr.equals("Active")? true:false);
	}
	
	public void setScannerSetting(String scannerName, boolean state) {
		String stateStr = state? "Active":"Inactive";
		this.mycallback.saveExtensionSetting("AutobotScanner"+scannerName, stateStr);
	}

	class ScannerObject {
		String name;
		IScannerCheck scanner;
		 
		ScannerObject (String scannerName, IScannerCheck scannerObject) {
			this.name = scannerName;
			this.scanner = scannerObject;
		}
		
	}
	
	public void addActiveScanner (String scannerName, IScannerCheck scannerObject) {
		this.activeScanners.add(new ScannerObject(scannerName,scannerObject));
	}
	
	public void addPassiveScanner (String scannerName, IScannerCheck scannerObject) {
		this.passiveScanners.add(new ScannerObject(scannerName,scannerObject));
	}
}
