import java.util.ArrayList;

public class Controller {
    private AppWindow appWindow;
    private ArrayList<AppController> controllers;

    public Controller(AppWindow application) {
        this.controllers = new ArrayList<>();
        this.appWindow = application;
    }

    public void addController(AppController controller) {
        this.controllers.add(controller);
    }

    public void run() {
        appWindow.init();

        for (AppController controller : this.controllers) {
            controller.connect(this.appWindow);
        }
    }
}
