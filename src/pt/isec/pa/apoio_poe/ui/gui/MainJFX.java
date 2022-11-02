package pt.isec.pa.apoio_poe.ui.gui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.AppManager;


public class MainJFX extends Application {
    AppManager manager;

    @Override
    public void init() throws Exception {
        super.init();
        manager = new AppManager(); // here or in the constructor
    }

    @Override
    public void start(Stage stage) throws Exception {
        RootPane root = new RootPane(manager);
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.setTitle("@APP ISEC");
        stage.setMinWidth(700);
        stage.setMinHeight(400);
        stage.setResizable(false);
        stage.show();
    }


}
