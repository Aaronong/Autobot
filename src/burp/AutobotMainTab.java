package burp;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class AutobotMainTab implements ITab {
	String tabCaption = "Autobot";
	Component tabComponent;
	IBurpExtenderCallbacks mycallbacks;
	AutobotSettings settings;
	
	public AutobotMainTab (IBurpExtenderCallbacks callbacks, AutobotSettings settings) {
		this.mycallbacks = callbacks;
		this.settings = settings;
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
		tabPanes.add("Options", scrollContent(new AutobotOptionsTab(this.mycallbacks, this.settings)));
		tabPanes.addTab("Issue Definition", new AutobotOptionsTab(this.mycallbacks, this.settings));
		tabPanes.addTab("WORKKS", new AutobotOptionsTab(this.mycallbacks, this.settings));
		this.mycallbacks.customizeUiComponent(tabPanes);
		this.tabComponent = tabPanes;
	}

	private JScrollPane scrollContent(Component content) {
		JScrollPane scroll = new JScrollPane (content);
		scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		return scroll;
	}
}
