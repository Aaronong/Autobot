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
		this.add(knowledgeBaseUI(this.settings));
		this.add(new JSeparator());
		this.add(passiveScannerUI(this.settings));
	}

	private JPanel knowledgeBaseUI (AutobotSettings settings) {
		//Set up panel to store all UI elements
		JPanel kb = new JPanel();
		kb.setLayout(new BoxLayout(kb, BoxLayout.Y_AXIS));
		kb.setAlignmentX(LEFT_ALIGNMENT);

		
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
		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.X_AXIS));
		innerPanel.setMaximumSize(new Dimension (500,25));
		innerPanel.setAlignmentX(LEFT_ALIGNMENT);
		innerPanel.add(textbox);
		innerPanel.add(this.uiHelper.componentSpacer());
		innerPanel.add(button);
		kb.add(innerPanel);
		kb.add(this.uiHelper.componentSpacer());
		
		return kb;
	}
	
	private JPanel passiveScannerUI (AutobotSettings settings) {
		//Set up panel to store all UI elements
		JPanel ps = new JPanel();
		ps.setLayout(new BoxLayout(ps, BoxLayout.Y_AXIS));
		ps.setAlignmentX(LEFT_ALIGNMENT);

		
		//Create Title
		ps.add(uiHelper.createBurpTitle("Passive Scanner Options"));
		ps.add(this.uiHelper.componentSpacer());
		
		//Create Description
		ps.add(new JLabel("This setting allows you to select the passive scanners that"
				+ " you wish to use."));
		ps.add(this.uiHelper.componentSpacer());
		
		//Retrieve Passive Scanner List
		ArrayList<ScannerObject> psl = this.settings.getPassiveScanners();
		
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
			ps.add(box);
			ps.add(this.uiHelper.componentSpacer());
		}
		
		return ps;
	}

}
