import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame {
    public static int WIDTH = 1024;
    public static int HEIGHT = 768;

    private PresentationComponent presentationComponent;
    private Dimension dimension;

    //CONSTRUCTOR
    public AppWindow(String title, PresentationComponent presentationComponent){
        super(title);
        this.presentationComponent = presentationComponent;
        this.dimension = new Dimension(WIDTH, HEIGHT);
    }

    //METHODS
    //Initializes the user interface
    public void init(){
        getContentPane().add(this.presentationComponent);
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
