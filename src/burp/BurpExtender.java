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
		
		
		for (int i = 0; i < 8; i++) {
			registerPassiveScanner("PassiveScanner"+i, new AutobotScanner());
		}
		for (int i = 0; i < 8; i++) {
			registerActiveScanner("ActiveScanner"+i, new AutobotScanner());
		}
		
		//Render UI only after doing all the back-end processing
		this.mycallbacks.addSuiteTab(new AutobotMainTab(this.mycallbacks, this.settings));
	}
	
	public void registerPassiveScanner(String name, IScannerCheck scanner) {
		this.settings.addPassiveScanner(name, scanner);
	}
	public void registerActiveScanner(String name, IScannerCheck scanner) {
		this.settings.addActiveScanner(name, scanner);
	}
	
}
