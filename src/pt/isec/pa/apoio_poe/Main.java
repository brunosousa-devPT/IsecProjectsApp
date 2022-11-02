package pt.isec.pa.apoio_poe;

import javafx.application.Application;
import pt.isec.pa.apoio_poe.model.AppManager;
import pt.isec.pa.apoio_poe.ui.gui.MainJFX;
import pt.isec.pa.apoio_poe.ui.text.AppGUI;

public class Main {
    public static void main(String[] args) {
       // AppManager app = new AppManager();
      //  AppGUI appText = new AppGUI(app);
     //  appText.start();

        Application.launch(MainJFX.class,args);
    }
    /* public static void main(String[] args) {
        Application.launch(MainJFX.class,args);
    }
*/
}
