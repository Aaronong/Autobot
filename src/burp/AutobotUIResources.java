package burp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AutobotUIResources {

	public JLabel createBurpTitle (String str) {
		JLabel title = new JLabel (str);
		title.setFont(new Font("default", Font.BOLD, 13));
		title.setForeground(new Color(229, 137, 0));
		return title;
	}
	
	public Component componentSpacer () {
		return Box.createRigidArea(new Dimension(5,10));
	}
	
	public JPanel horizontalPanel () {
		//Create Panel to display text box and button horizontally
		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.X_AXIS));
		innerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		return innerPanel;
	}
}
