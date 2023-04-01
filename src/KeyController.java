import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class KeyController extends KeyAdapter implements AppController{
	private Presentation presentation; //Commands are given to the presentation

	public KeyController() {
	}

	public void keyPressed(KeyEvent keyEvent) {
		switch (keyEvent.getKeyCode()) {
			case KeyEvent.VK_PAGE_DOWN, KeyEvent.VK_DOWN, KeyEvent.VK_ENTER, '+' -> presentation.nextSlide();
			case KeyEvent.VK_PAGE_UP, KeyEvent.VK_UP, '-' -> presentation.prevSlide();
			case 'q', 'Q' -> System.exit(0);
			//No buttons pressed do nothing
			default -> {
				break;
			}
		}
	}

	@Override
	public void connect(AppWindow application) {
		this.presentation = application.getPresentationComponent().getPresentation();
		application.addKeyListener(this);
	}
}
