import java.awt.*;
import java.awt.image.ImageObserver;

public class ImageData {
    private int x;
    private int y;
    private Graphics g;
    private Style style;
    private ImageObserver imageObserver;

    private Rectangle rectangle;

    private float scale;

    public ImageData(Graphics g, Rectangle rectangle, ImageObserver imageObserver){
        this.g = g;
        this.imageObserver = imageObserver;
        this.rectangle = rectangle;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Graphics getGraphics() {
        return g;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public int getStyleIndent(){
        return style.indent;
    }

    public int getStyleLeading(){
        return style.leading;
    }

    public ImageObserver getImageObserver() {
        return imageObserver;
    }

    public void setImageObserver(ImageObserver imageObserver) {
        this.imageObserver = imageObserver;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

}
