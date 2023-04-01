import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

import java.io.IOException;


/** <p>The class for a Bitmap item</p>
 * <p>Bitmap items are responsible for drawing themselves.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class BitmapItem extends SlideItem {
  private BufferedImage bufferedImage;
  private ImageObserver imageObserver;
  private String imageName;


  	//level indicates the item-level; name indicates the name of the file with the image
	public BitmapItem(int level, String name) {
		super(level);
		imageName = name;
		try {
			bufferedImage = ImageIO.read(new File(imageName));
		}
		catch (IOException e) {
			System.err.println("File" + imageName + " not found") ;
		}
	}

	//Returns the filename of the image
	public String getName() {
		return imageName;
	}

	//Returns the bounding box of the image
	public Rectangle getBoundingBox(ImageData imageData) {
		return new Rectangle((int) (imageData.getStyle().indent * imageData.getScale()), 0,
				(int) (bufferedImage.getWidth(imageData.getImageObserver()) * imageData.getScale()),
				((int) (imageData.getStyle().leading * imageData.getScale())) +
				(int) (bufferedImage.getHeight(imageData.getImageObserver()) * imageData.getScale()));
	}

	public void setImageObserver(ImageObserver imageObserver) {
		this.imageObserver = imageObserver;
	}

	//Draws the image
	@Override
	public void draw(ImageData imageData) {
		int width = imageData.getX() + (int) (imageData.getStyleIndent() * imageData.getScale());
		int height = imageData.getY() + (int) (imageData.getStyleLeading() * imageData.getScale());
		imageData.getGraphics().drawImage(bufferedImage, width, height,
				(int) (bufferedImage.getWidth(imageData.getImageObserver()) * imageData.getScale()),
                (int) (bufferedImage.getHeight(imageData.getImageObserver()) * imageData.getScale()),
				imageData.getImageObserver());
	}

	public String toString() {
		return "BitmapItem[" + getLevel() + "," + imageName + "]";
	}
}
