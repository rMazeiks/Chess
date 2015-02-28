package ui;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Create extends AbstractAction {
	static JFrame frame;

	public Create(String text, ImageIcon icon, String desc, Integer mnemonic) {
		super(text, icon);
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
	}

	public static void make() {
		frame = new JFrame("New game...");
		frame.setMinimumSize(new Dimension(300, 200));

		frame.setMenuBar(Bar.mb);

		Panel p = new Panel();
		p.setLayout(new GridLayout(2, 2, 20, 20));

		p.add(new Button("Chess"));
		p.add(new Button("Checkers"));
		p.add(new Button("---"));
		p.add(new Button("Other..."));

		frame.add(p);

		frame.pack();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		frame.setVisible(true);
	}
}
