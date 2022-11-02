package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.AppManager;
import pt.isec.pa.apoio_poe.model.fsm.AppState;

import java.io.File;

public class AppMenu extends MenuBar {
    AppManager manager;

    Menu mnFile;
    MenuItem mnNew, mnOpen, mnSave, mnExit;
    Menu mnEdit;
    MenuItem mnUndo, mnRedo;

    public AppMenu(AppManager manager) {
        this.manager = manager;
        createViews();
        registerHandles();
        update();

    }

    public void createViews() {
        mnFile = new Menu("File");
        mnNew = new MenuItem("_New");
        mnOpen = new MenuItem("_Open");
        mnOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        mnSave = new MenuItem("_Save");
        mnExit = new MenuItem("_Exit");
        mnFile.getItems().addAll(mnNew,mnOpen,mnSave, new SeparatorMenuItem(), mnExit);
        mnEdit = new Menu("Edit");
        mnUndo = new MenuItem("_Undo");
        mnRedo = new MenuItem("_Redo");
        mnEdit.getItems().addAll(mnUndo,mnRedo);
        this.getMenus().addAll(mnFile, mnEdit);
    }
    public void registerHandles(){
        mnRedo.setOnAction(e-> {
            manager.redo();
        });
        mnUndo.setOnAction(e-> {
            manager.undo();
        });
        mnNew.setOnAction(e-> {
            manager.begin();
        });
        mnExit.setOnAction(e-> {
            Platform.exit();
        });
        mnSave.setOnAction(e-> {
            manager.save();
        });
        mnOpen.setOnAction(e->{


            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("File open ...");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Drawing (*.dat)","*.dat"),
                    new FileChooser.ExtensionFilter("All","*.*")
            );
            File hFile = fileChooser.showOpenDialog(this.getScene().getWindow());

            if (hFile != null) {
                manager.load();
            }
            //manager.load();
        });
    }
    public void update() {
       // if (manager.getState() != AppState.MANUAL_ATTRIBUTION_OFFER_STATE && manager.getState() != AppState.MANUAL_ATTRIBUTION_TEACHER_STATE) {
        //    mnUndo.setDisable(true);
         //   mnRedo.setDisable(true);
          //  return;
       // }
        //mnUndo.setDisable(false);
        //mnRedo.setDisable(false);

    }
}
