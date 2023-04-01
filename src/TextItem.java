import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/** <p>A text item.</p>
 * <p>A text item has drawing capabilities.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class TextItem extends SlideItem {
	private String text;

	//A textitem of int level with text string
	public TextItem(int level, String string) {
		super(level);
		text = string;
	}

	//Returns the text
	public String getText() {
		return text == null ? "" : text;
	}

	//Returns the AttributedString for the Item
	public AttributedString getAttributedString(ImageData imageData) {
		AttributedString attrStr = new AttributedString(getText());
		attrStr.addAttribute(TextAttribute.FONT, imageData.getStyle().getFont(imageData.getScale()), 0, text.length());
		return attrStr;
	}

	//Returns the bounding box of an Item
	public Rectangle getBoundingBox(ImageData imageData) {
		List<TextLayout> layouts = getLayouts(imageData);
		int xsize = 0, ysize = (int) (imageData.getStyleLeading() * imageData.getScale());
		for (TextLayout layout : layouts) {
			Rectangle2D bounds = layout.getBounds();
			if (bounds.getWidth() > xsize) {
				xsize = (int) bounds.getWidth();
			}
			if (bounds.getHeight() > 0) {
				ysize += bounds.getHeight();
			}
			ysize += layout.getLeading() + layout.getDescent();
		}
		return new Rectangle((int) (imageData.getStyleIndent() * imageData.getScale()), 0, xsize, ysize );
	}

	//Draws the item
	public void draw(ImageData imageData) {
		if (text == null || text.length() == 0) {
			return;
		}
		List<TextLayout> layouts = getLayouts(imageData);
		Point pen = new Point(imageData.getX() + (int)(imageData.getStyleIndent() * imageData.getScale()),
				imageData.getY() + (int)(imageData.getStyleLeading() * imageData.getScale()));
		Graphics2D g2d = (Graphics2D)imageData.getGraphics();
		g2d.setColor(imageData.getStyle().color);
		for (TextLayout layout : layouts) {
			pen.y += layout.getAscent();
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent();
		}
	  }

	private List<TextLayout> getLayouts(ImageData imageData) {
		List<TextLayout> layouts = new ArrayList<TextLayout>();
		AttributedString attrStr = getAttributedString(imageData);
    	Graphics2D g2d = (Graphics2D) imageData.getGraphics();
    	FontRenderContext frc = g2d.getFontRenderContext();
    	LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
    	float wrappingWidth = (AppWindow.WIDTH - imageData.getStyleIndent()) * imageData.getScale();
    	while (measurer.getPosition() < getText().length()) {
    		TextLayout layout = measurer.nextLayout(wrappingWidth);
    		layouts.add(layout);
    	}
    	return layouts;
	}

	public String toString() {
		return "TextItem[" + getLevel()+","+getText()+"]";
	}
}
