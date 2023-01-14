import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

/** <p>The controller for the menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar {
	
	private Frame parent; //The frame, only used as parent for the Dialogs
	private Presentation presentation; //Commands are given to the presentation

	//Menu objects
	private MenuItem menuItem;
	private Menu fileMenu = new Menu(ControllerButton.FILE);
	private Menu viewMenu = new Menu(ControllerButton.VIEW);
	private Menu helpMenu = new Menu(ControllerButton.HELP);

	public MenuController(Frame frame, Presentation pres) {
		parent = frame;
		presentation = pres;

		//File menu
		fileMenuSave();
		fileMenuNew();
		fileMenuOpen();
		fileMenu.addSeparator();
		fileMenuExit();
		add(fileMenu);

		//View menu
		viewMenuNext();
		viewMenuPrevious();
		viewMenuGoTo();
		add(viewMenu);

		//Help menu
		helpMenuAbout();
		setHelpMenu(helpMenu);		//Needed for portability (Motif, etc.).
	}

	//help button in about menu
	private void helpMenuAbout() {
		helpMenu.add(menuItem = mkMenuItem(ControllerButton.ABOUT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AboutBox.show(parent);
			}
		});
	}

	//go to button in view menu
	private void viewMenuGoTo() {
		viewMenu.add(menuItem = mkMenuItem(ControllerButton.GOTO));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String pageNumberStr = JOptionPane.showInputDialog((Object) ControllerButton.PAGENR);
				int pageNumber = Integer.parseInt(pageNumberStr);
				presentation.setSlideNumber(pageNumber - 1);
			}
		});
	}

	//previous button in view menu
	private void viewMenuPrevious() {
		viewMenu.add(menuItem = mkMenuItem(ControllerButton.PREV));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.prevSlide();
			}
		});
	}

	//next button in view menu
	private void viewMenuNext() {
		viewMenu.add(menuItem = mkMenuItem(ControllerButton.NEXT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.nextSlide();
			}
		});
	}

	//exit button in file menu
	private void fileMenuExit() {
		fileMenu.add(menuItem = mkMenuItem(ControllerButton.EXIT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.exit(0);
			}
		});
	}

	//new button in file menu
	private void fileMenuNew() {
		fileMenu.add(menuItem = mkMenuItem(ControllerButton.NEW));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.clear();
				parent.repaint();
			}
		});
	}

	//open button in file menu
	private void fileMenuOpen() {
		fileMenu.add(menuItem = mkMenuItem(ControllerButton.OPEN));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.clear();
				Accessor xmlAccessor = new XMLAccessor();
				try {
					xmlAccessor.loadFile(presentation, ControllerButton.TESTFILE);
					presentation.setSlideNumber(0);
				} catch (IOException exc) {
					JOptionPane.showMessageDialog(parent, ControllerButton.IOEX + exc,
							ControllerButton.LOADERR, JOptionPane.ERROR_MESSAGE);
				}
				parent.repaint();
			}
		} );
	}

	//save button in file menu
	private void fileMenuSave() {
		fileMenu.add(menuItem = mkMenuItem(ControllerButton.SAVE));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accessor xmlAccessor = new XMLAccessor();
				try {
					xmlAccessor.saveFile(presentation, ControllerButton.SAVEFILE);
				} catch (IOException exc) {
					JOptionPane.showMessageDialog(parent, ControllerButton.IOEX + exc,
							ControllerButton.SAVEERR, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	//Creating a menu-item
	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}
