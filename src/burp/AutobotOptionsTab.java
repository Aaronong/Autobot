package burp;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import burp.AutobotSettings.ScannerObject;

public class AutobotOptionsTab extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6195537307019981776L;
	IBurpExtenderCallbacks callbacks;
	AutobotUIResources uiHelper;
	AutobotSettings settings;
	
	public AutobotOptionsTab (IBurpExtenderCallbacks callbacks, AutobotSettings settings) {
		this.callbacks = callbacks;
		this.uiHelper = new AutobotUIResources();
		this.settings = settings;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(this.uiHelper.componentBorder());
		
		//Knowledge Base UI
		this.add(knowledgeBaseUI(this.settings));
		
		this.add(new JSeparator());
		
		//Passive Scanner UI
		this.add(this.uiHelper.componentSpacer());
		this.add(ScannerUI(this.settings,"Passive Scanner Options", "This setting allows "
				+ "you to select the passive scanners that you wish to use.", 
				this.settings.getPassiveScanners()));
		this.add(new JSeparator());
		
		//Active Scanner UI
		this.add(this.uiHelper.componentSpacer());
		this.add(ScannerUI(this.settings,"Active Scanner Options", "This setting allows "
				+ "you to select the active scanners that you wish to use.", 
				this.settings.getActiveScanners()));
		
		//Add Filler to take up rest of the vertical space 
		//(500 without any scanners, each scanner takes up 25)
		int totalSpace = 500;
		int totalScanners = this.settings.getActiveScanners().size() + this.settings.getPassiveScanners().size();
		int fillerSpace = totalSpace - totalScanners*25;
		if (fillerSpace > 0) {
			this.add(this.uiHelper.pageFiller(fillerSpace));
		}
	}

	private JPanel knowledgeBaseUI (AutobotSettings settings) {
		//Set up panel to store all UI elements
		JPanel kb = this.uiHelper.verticalPanel();

		
		//Create Title
		kb.add(uiHelper.createBurpTitle("Knowledge Base Location"));
		kb.add(this.uiHelper.componentSpacer());
		
		//Create Description
		kb.add(new JLabel("This setting allows you to configure the knowledge base that you wish"
				+ " to use in generating the error messages."));
		kb.add(this.uiHelper.componentSpacer());
		
		//Create Text box to display kb path
		JTextField textbox = new JTextField();
		textbox.setEditable(false);
		textbox.setText(settings.getKBL());
		textbox.setPreferredSize(new Dimension(350,25));
		
		//Create button to choose trigger file picker
		JButton button = new JButton("Select file...");
		
		button.addActionListener(new ActionListener() { 

			@Override
			public void actionPerformed(ActionEvent e) {
				//FileSystemView resource to make file picking more intuitive
				FileSystemView fsv = FileSystemView.getFileSystemView();
				
				//Create File Picker
				final JFileChooser chooser = new JFileChooser(settings.getKBL(), fsv);
				int retval = chooser.showOpenDialog(null);
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (JFileChooser.APPROVE_OPTION == retval) {
					settings.setKBL(chooser.getSelectedFile().getAbsolutePath());
					textbox.setText(settings.getKBL());
//					textbox.setText(chooser.getSelectedFile().getAbsolutePath());	
				}
			} 
		});
		
		//Create Panel to display text box and button horizontally
		JPanel innerPanel = this.uiHelper.horizontalPanel();
		innerPanel.setMaximumSize(new Dimension (500,25));
		innerPanel.add(textbox);
		innerPanel.add(this.uiHelper.componentSpacer());
		innerPanel.add(button);
		kb.add(innerPanel);
		kb.add(this.uiHelper.componentSpacer());
		
		return kb;
	}
	
	private JPanel ScannerUI (AutobotSettings settings, String title,
		String description, ArrayList<ScannerObject> list) {
		//Set up panel to store all UI elements
		JPanel scanUI = this.uiHelper.verticalPanel();

		
		//Create Title
		scanUI.add(uiHelper.createBurpTitle(title));
		scanUI.add(this.uiHelper.componentSpacer());
		
		//Create Description
		scanUI.add(new JLabel(description));
		scanUI.add(this.uiHelper.componentSpacer());
		
		//Retrieve Passive Scanner List
		ArrayList<ScannerObject> psl = list;
		
		for (ScannerObject scanner: psl) {
			JCheckBox box = new JCheckBox(scanner.name);
			box.setSelected(settings.getScannerSetting(scanner.name));
			box.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					settings.setScannerSetting(scanner.name, box.isSelected());
				}
				
			});
			scanUI.add(box);
			scanUI.add(this.uiHelper.componentSpacer());
		}
		
		return scanUI;
	}

}
