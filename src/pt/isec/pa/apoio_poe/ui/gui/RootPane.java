package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.layout.*;
import pt.isec.pa.apoio_poe.model.AppManager;
import pt.isec.pa.apoio_poe.model.data.DataTypes;
import pt.isec.pa.apoio_poe.ui.gui.states.*;

import static pt.isec.pa.apoio_poe.model.data.ManagementTypes.IMPORT;

public class RootPane extends BorderPane {
    AppManager appManager;
    AppMenu menu;

    public RootPane(AppManager appManager) {
        this.appManager = appManager;
        createViews();
        registerHandlers();
        update();

    }

    private void createViews() {
        this.setTop(new AppMenu(appManager));

        StackPane stackPane = new StackPane(
            new BeginUI(appManager),
            new CandidatureUI(appManager),
            new ConfigUI(appManager),
            new ManagePoEUI(appManager),
            new ManageStudentsUI(appManager),
            new ManageTeachersUI(appManager),
            new OffersAttributionUI(appManager),
            new ManualAttributionOfferUI(appManager),
            new AdvisorAttributionUI(appManager),
            new ManualAttributionTeacherUI(appManager),
            new QueryUI(appManager)


        );
        //stackPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("#073140"), CornerRadii.EMPTY, Insets.EMPTY)));


        this.setCenter(stackPane);

    }
    private void registerHandlers() {

    }
    private void update() {

    }

}
