import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;


/** <p>SlideViewerComponent is a graphical component that ca display Slides.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerComponent extends JComponent {
		
	private Slide slide; //The current slide
	private Font labelFont = null; //The font for labels
	private Presentation presentation = null; //The presentation
	private JFrame frame = null;

	public SlideViewerComponent(Presentation pres, JFrame frame) {
		setBackground(SlideViewerDefault.BGCOLOR);
		presentation = pres;
		labelFont = new Font(SlideViewerDefault.FONTNAME, SlideViewerDefault.FONTSTYLE, SlideViewerDefault.FONTHEIGHT);
		this.frame = frame;
	}

	public void update(Presentation presentation, Slide data) {
		if (data == null) {
			repaint();
		} else {
			this.presentation = presentation;
			this.slide = data;
			repaint();
			frame.setTitle(presentation.getTitle());
		}
	}

//Draw the slide
	public void paintComponent(Graphics g) {
		g.setColor(SlideViewerDefault.BGCOLOR);
		g.fillRect(0, 0, getSize().width, getSize().height);
		if (presentation.getSlideNumber() < 0 || slide == null) {
			return;
		}
		g.setFont(labelFont);
		g.setColor(SlideViewerDefault.COLOR);
		g.drawString("Slide " + (1 + presentation.getSlideNumber()) + " of " +
                 presentation.getSize(), SlideViewerDefault.XPOS, SlideViewerDefault.YPOS);
		Rectangle area = new Rectangle(0, SlideViewerDefault.YPOS, getWidth(), (getHeight() - SlideViewerDefault.YPOS));
		slide.draw(new ImageData(g, area, this));
	}
}
