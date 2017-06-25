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
		if (stateStr == null) {
			return false;
		}
		return (stateStr.equals("Active")? true:false);
	}
	
	public void setScannerSetting(String scannerName, boolean state) {
		if (state) {
			this.mycallback.registerScannerCheck(getScannerByName(scannerName));
		} else {
			this.mycallback.removeScannerCheck(getScannerByName(scannerName));
		}
		String stateStr = state? "Active":"Inactive";
		this.mycallback.saveExtensionSetting("AutobotScanner"+scannerName, stateStr);
	}
	
	public IScannerCheck getScannerByName(String scannerName) {
		for (ScannerObject scanner: this.activeScanners) {
			if (scanner.name == scannerName) {
				return scanner.scanner;
			}
		}
		for (ScannerObject scanner: this.passiveScanners) {
			if (scanner.name == scannerName) {
				return scanner.scanner;
			}
		}
		return null;
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
		//This seemingly redundant step registers the scanner to burp
		this.setScannerSetting(scannerName, this.getScannerSetting(scannerName));
	}
	
	public void addPassiveScanner (String scannerName, IScannerCheck scannerObject) {
		this.passiveScanners.add(new ScannerObject(scannerName,scannerObject));
		//This seemingly redundant step registers the scanner to burp
		this.setScannerSetting(scannerName, this.getScannerSetting(scannerName));
	}
}
