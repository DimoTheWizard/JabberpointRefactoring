import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame {
    public static int WIDTH = 1024;
    public static int HEIHHT = 768;

    private PresentationComponent presentationComponent;
    private Dimension dimension;

    public AppWindow(string title, PresentationComponent presentationComponent){
        super(title);
        this.presentationComponent = presentationComponent;
        this.dimension = new Dimension(WIDTH, HEIGHT);
    }

    //Initializes the user interface
    public void init(){
        getContentPane().add((this.presentationComponent);
        this.setSize(this.dimension);
        this.setVisible(true);
    }

    //GETTERS AND SETTERS
    public PresentationComponent getPresentationComponent() {
        return presentationComponent;
    }

    public Dimension getDimension(){
        return this.dimension;
    }
}
