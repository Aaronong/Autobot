package burp;

public class BurpExtender implements IBurpExtender{
	public static String MENU_NAME = "Send to Autobot";
	public IBurpExtenderCallbacks mycallbacks;
	public IExtensionHelpers helpers;
	public AutobotSettings settings;
	
	
	@Override
	public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
		// TODO Auto-generated method stub
		this.mycallbacks = callbacks;
		this.helpers = this.mycallbacks.getHelpers();
		this.settings = new AutobotSettings(this.mycallbacks);
		callbacks.setExtensionName("Autobot Scanner");
		
		
		for (int i = 0; i < 6; i++) {
			registerPassiveScanner("Scanner"+i, new AutobotScanner());
		}
		
		//Render UI only after doing all the back-end processing
		this.mycallbacks.addSuiteTab(new AutobotMainTab(this.mycallbacks, this.settings));
	}
	
	public void registerPassiveScanner(String name, IScannerCheck scanner) {
		//this.mycallbacks.registerScannerCheck(scanner);
		this.settings.addPassiveScanner(name, scanner);
	}
	
}
