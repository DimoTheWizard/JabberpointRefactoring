import java.awt.*;
import javax.swing.*;


public class PresentationComponent extends JComponent{
    private Font labelFont;
    private Presentation presentation;
    private Style[] styles;

    //COLORS
    private static Color BACKGROUNDCOLOR = Color.WHITE;
    private static Color COLOR = Color.BLACK;

    //FONT
    private static String FONT = "Dialog";
    private static int FONTSTYLE = Font.BOLD;
    private static int FONTSIZE = 12;

    //TEXT COORDINATES
    private static int TEXTXPOS = 1000;
    private static int TEXTYPOS = 20;

    //CONSTRUCTOR
    public PresentationComponent(){
        this.setBackground(BACKGROUNDCOLOR);
        this.presentation = new Presentation();
        this.labelFont = new Font(FONT, FONTSTYLE, FONTSIZE);
        this.styles = new Style[5];
        createDefaultStyles();
    }

    //METHODS
    private void createDefaultStyles() {
        this.styles[0] = new Style(0, Color.RED, 48, 20);
        this.styles[1] = new Style(15, Color.BLUE, 42, 10);
        this.styles[2] = new Style(50, Color.BLACK, 36, 10);
        this.styles[3] = new Style(65, Color.BLACK, 30, 10);
        this.styles[4] = new Style(80, Color.BLACK, 24, 10);
    }

    //Draws the slide based on the graphics
    public void paintComponent(Graphics graphics){
        this.repaint();
        Slide slide = this.presentation.getCurrentSlide();

        graphics.setColor(BACKGROUNDCOLOR);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);

        if(slide == null){
            return;
        }

        graphics.setFont(this.labelFont);
        graphics.setColor(COLOR);
        graphics.drawString("Slide " + (1 + this.presentation.getCurrentSlideNumber()) + " of " + this.presentation.getSlidesSize(), TEXTXPOS, TEXTYPOS);

        Rectangle rectangle = (new Rectangle(0, TEXTYPOS, getWidth(), (getHeight() - TEXTYPOS)));;

        this.drawSlide(slide, new ImageData(graphics, rectangle, this));
    }

    //draws all slides with their appropriate styles
    private void drawSlide(Slide slide, ImageData imageData){
        SlideItem slideItem = new TextItem(0, slide.getTitle());
        Style style = this.getStyle(slideItem.getLevel());

        imageData.setScale(getScale(imageData.getRectangle()));
        imageData.setStyle(style);

        SlideItem textSlideItem = new TextItem(0, slide.getTitle());
        textSlideItem.draw(imageData);
        imageData.setY(imageData.getY() + textSlideItem.getBoundingBox(imageData).height);

        for(SlideItem item : slide.getSlideItems()) {
            if(item instanceof BitmapItem){
                ((BitmapItem) item).setImageObserver(this);
            }
            style = this.getStyle(item.getLevel());
            imageData.setStyle(style);
            item.draw(imageData);
            imageData.setY(imageData.getY() + item.getBoundingBox(imageData).height);
        }
    }

    //GETTERS AND SETTERS
    private float getScale(Rectangle area){
        return Math.min(((float)area.width) / ((float)AppWindow.WIDTH), ((float)area.height) / ((float)AppWindow.HEIGHT));
    }

    public Presentation getPresentation() {
        return this.presentation;
    }

    private Style getStyle(int weight){
        if(this.isValidStyle(weight)){
            return this.styles[weight];
        }
        return this.styles[4];
    }

    private boolean isValidStyle(int level){
        if(level >= 0 && level < this.styles.length){
            return true;
        }
        return false;
    }
}
