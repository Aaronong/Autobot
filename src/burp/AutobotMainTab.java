package burp;
import java.awt.Component;

import javax.swing.JTabbedPane;

public class AutobotMainTab implements ITab {
	String tabCaption = "Autobot";
	Component tabComponent;
	IBurpExtenderCallbacks mycallbacks;
	AutobotSettings settings;
	AutobotUIResources uiHelper;
	
	public AutobotMainTab (IBurpExtenderCallbacks callbacks, AutobotSettings settings) {
		this.mycallbacks = callbacks;
		this.settings = settings;
		this.uiHelper = new AutobotUIResources();
	}
	@Override
	public String getTabCaption() {
		// TODO Auto-generated method stub
		return this.tabCaption;
	}

	@Override
	public Component getUiComponent() {
		// TODO Auto-generated method stub
		InitializeComponent();
		return tabComponent;
	}
	
	private void InitializeComponent() {
		
		JTabbedPane tabPanes = new JTabbedPane();
		tabPanes.setAlignmentX(Component.LEFT_ALIGNMENT);
		tabPanes.add("Options", this.uiHelper.scrollContent(new AutobotOptionsTab(this.mycallbacks, this.settings)));
		tabPanes.addTab("Issue Definition", new AutobotIssueDefinitionsTab(this.mycallbacks));
		tabPanes.addTab("WORKKS", new AutobotOptionsTab(this.mycallbacks, this.settings));
		this.mycallbacks.customizeUiComponent(tabPanes);
		this.tabComponent = tabPanes;
	}


}
