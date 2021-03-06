package burp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

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
	
	public JPanel horizontalPanel() {
		//Create Panel to display text box and button horizontally
		JPanel horiPane = new JPanel();
		horiPane.setLayout(new BoxLayout(horiPane, BoxLayout.X_AXIS));
		horiPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		return horiPane;
	}
	
	public JPanel verticalPanel() {
		JPanel vertPane = new JPanel();
		vertPane.setLayout(new BoxLayout(vertPane, BoxLayout.Y_AXIS));
		vertPane.setAlignmentY(Component.TOP_ALIGNMENT);
		vertPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		vertPane.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		return vertPane;
	}
	
	public JScrollPane scrollContent(Component content) {
		JScrollPane scroll = new JScrollPane (content);
		return scroll;
	}
	
	public Border componentBorder() {
		return BorderFactory.createEmptyBorder(10, 10, 10, 10);
	}
	
	public Filler pageFiller(int preferredHeight) {
		Dimension minSize = new Dimension(5, 5);
		Dimension prefSize = new Dimension(5, preferredHeight);
		Dimension maxSize = new Dimension(5, 1000);
		return new Box.Filler(minSize, prefSize, maxSize);
	}
}
