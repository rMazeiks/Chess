package ui;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

public class Bar {
	public static MenuBar mb;

	public static void make() {
		mb = new MenuBar();
		mb.add(file());
	}

	
	/**
	 * L!!
	 * @return
	 */
	private static Menu file() {
		Menu m = new Menu("File");
		
		Menu n = new Menu("New");
		n.add(new MenuItem("Chess game"));
		n.add(new MenuItem("Checker game"));
		n.add(new MenuItem("---"));
		n.add(new MenuItem("Other..."));
		n.addSeparator();
		//n.add(new MenuItem(new Create()));
		m.add(n);
		
		m.add(new MenuItem("Close"));
		
		m.add(new MenuItem("Open..."));
		
		return m;
	}
}
