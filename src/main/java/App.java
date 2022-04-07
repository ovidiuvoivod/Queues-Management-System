import ro.utcluj.controller.MainController;
import ro.utcluj.view.MainView;

public class App {
    public static void main(String[] argv) {
        MainView mainView = new MainView();
        MainController mainController = new MainController(mainView);
    }
}
