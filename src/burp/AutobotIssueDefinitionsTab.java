package burp;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;

public class AutobotIssueDefinitionsTab extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9060799076198515715L;
	IBurpExtenderCallbacks callbacks;
	AutobotUIResources uiHelper;
	AutobotSettings settings;
	
	public AutobotIssueDefinitionsTab (IBurpExtenderCallbacks callbacks) {
		this.callbacks = callbacks;
		this.uiHelper = new AutobotUIResources();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(this.uiHelper.componentBorder());
		this.add(header());
		this.add(issuesSplitPane());
	}
	
	private JPanel header() {
		JPanel head = this.uiHelper.verticalPanel();
		head.add(this.uiHelper.createBurpTitle("Issue Definitions"));
		head.add(new JLabel("This listing contains the definitions of all issues "
				+ "that can be detected by Autobot Scanner."));
		return head;
	}
	
	private JSplitPane issuesSplitPane () {
		//Initializing split pane
		JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		//Creating issues table
		String[] columnNames = {"Name",
                "Typical severity",
                "Type index"};
		Object[][] data = {
			    {"OS Command Injection", "High", "0x000203020"},
			    {"LDAP Injection", "Low", "0x0223234"},
			    {"XML injection", "Medium", "0x0232384"},
			};
		JTable leftSide = new JTable(data, columnNames);
		
		//Creating right pane
		JPanel rightSide = this.uiHelper.verticalPanel();
		rightSide.add(new JLabel("CONTENT"));
		
		//Setting components into split pane
		splitpane.setRightComponent(this.uiHelper.scrollContent(rightSide));
		splitpane.setLeftComponent(this.uiHelper.scrollContent(leftSide));
		
		return splitpane;
	}
}
