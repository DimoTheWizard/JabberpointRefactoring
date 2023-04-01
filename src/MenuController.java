
import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * <p>The controller for the menu</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class MenuController extends MenuBar implements AppController {
	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String OPEN = "Open";
	protected static final String PAGE_NR = "Page number?";
	protected static final String PREV = "Prev";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";
	protected static final String TESTFILE = "testPresentation.xml";
	protected static final String SAVEFILE = "savedPresentation.xml";
	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Load Error";
	protected static final String SAVEERR = "Save Error";


	public MenuController() {
	}

	private void initialize(AppWindow application) {
		Presentation presentation = application.getPresentationComponent().getPresentation();
		MenuItem menuItem;

		// File Menu ========================================================
		Menu fileMenu = new Menu(FILE);
		// Open (will open a static presentation)
		fileMenu.add(menuItem = getNewMenuItem(OPEN));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.clear();
				Accessor xmlAccessor = new XMLAccessor();
				try {
					xmlAccessor.loadFile(presentation, TESTFILE);
					presentation.setCurrentSlideNumber(0);
				} catch (IOException exc) {
					JOptionPane.showMessageDialog(application, IOEX + exc, LOADERR, JOptionPane.ERROR_MESSAGE);
				}
				application.repaint();
			}
		});

		// New (will clear the current presentation)
		fileMenu.add(menuItem = getNewMenuItem(NEW));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.clear();
				application.repaint();
			}
		});

		// Save
		fileMenu.add(menuItem = getNewMenuItem(SAVE));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accessor xmlAccessor = new XMLAccessor();
				try {
					xmlAccessor.saveFile(presentation, SAVEFILE);
				} catch (IOException exc) {
					JOptionPane.showMessageDialog(application, IOEX + exc,
							SAVEERR, JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		fileMenu.addSeparator();

		// Exit
		fileMenu.add(menuItem = getNewMenuItem(EXIT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
//                presentation.exit(0);
				System.exit(0);
			}
		});
		add(fileMenu);

		// ========= View Menu ================================================
		Menu viewMenu = new Menu(VIEW);
		// Next
		viewMenu.add(menuItem = getNewMenuItem(NEXT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.nextSlide();
			}
		});

		// Previous presentation.Slide
		viewMenu.add(menuItem = getNewMenuItem(PREV));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.prevSlide();
			}
		});

		// Go to slide
		viewMenu.add(menuItem = getNewMenuItem(GOTO));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String pageNumberStr = JOptionPane.showInputDialog((Object) PAGE_NR);
				int pageNumber = Integer.parseInt(pageNumberStr);
				presentation.setCurrentSlideNumber(pageNumber - 1);
			}
		});
		add(viewMenu);

		// === Help Menu =============================================================
		Menu helpMenu = new Menu(HELP);
		// About
		helpMenu.add(menuItem = getNewMenuItem(ABOUT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AboutBox.show(application);
			}
		});

		//Needed for portability (Motif, etc.).
		setHelpMenu(helpMenu);
	}

	// Creating a menu-item
	public MenuItem getNewMenuItem(String menuName) {
		return new MenuItem(menuName, new MenuShortcut(menuName.charAt(0)));
	}

	@Override
	public void connect(AppWindow applicationWindow) {
		this.initialize(applicationWindow);
		applicationWindow.setMenuBar(this);
	}
}
