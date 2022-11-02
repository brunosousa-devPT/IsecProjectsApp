package pt.isec.pa.apoio_poe.ui.gui.states;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import pt.isec.pa.apoio_poe.model.AppManager;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

import java.util.Objects;

public class BeginUI extends BorderPane {
    private AppManager appManager;
    private Button btnStart,btnExit;
    VBox vLeft, vRight;
    VBox empty;
    HBox hBox;
    Label appLabel, backgrou;
    ImageView isec;
    public BeginUI(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#073140"),CornerRadii.EMPTY,Insets.EMPTY)));
        btnStart = new Button("Start");
        btnStart.setMinWidth(100);
        btnStart.setMinHeight(70);
        btnStart.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );
        btnStart.setBorder(new Border(new BorderStroke(Paint.valueOf("black"),BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
        btnExit  = new Button("Exit");
        btnExit.setMinWidth(100);
        btnExit.setMinHeight(70);
        btnExit.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );
        btnExit.setBorder(new Border(new BorderStroke(Paint.valueOf("black"),BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));

        appLabel = new Label("APP");
        appLabel.setPrefWidth(Integer.MAX_VALUE);
        appLabel.setPrefHeight(100);
        appLabel.setFont(new Font("Courier New",26));
        appLabel.setTextFill(Color.WHITE);
        appLabel.setAlignment(Pos.CENTER);
        appLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("#c95151"), new CornerRadii(10), Insets.EMPTY)) );
        appLabel.setBorder(new Border(new BorderStroke(Paint.valueOf("black"),BorderStrokeStyle.SOLID,new CornerRadii(10),BorderWidths.DEFAULT)));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(appLabel,btnStart, btnExit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0,50,0,50));
        vBox.setSpacing(5);
        vBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("#235769"), CornerRadii.EMPTY, Insets.EMPTY)) );

        vLeft = new VBox(vBox);
        vLeft.setPrefWidth(300);
        vLeft.setAlignment(Pos.CENTER_LEFT);
        vLeft.setBackground(new Background(new BackgroundFill(Paint.valueOf("#235769"), CornerRadii.EMPTY, Insets.EMPTY)) );
        vLeft.setPrefHeight(this.getHeight());

        isec = new ImageView();
        isec =  ImageManager.getImageView("background.png",60);
        vRight = new VBox();
        vRight.setPrefWidth(400);
        vRight.getChildren().addAll(isec);
        vRight.setAlignment(Pos.TOP_CENTER);



        hBox  = new HBox();
        hBox.getChildren().addAll(vLeft, vRight);
        hBox.setPrefWidth(Integer.MAX_VALUE);

        this.setLeft(hBox);


    }
    private void registerHandlers() {
        appManager.addPropertyChangeListener(evt -> { update(); });

        this.setOnMouseClicked(ev -> {
            btnStart.setOnAction( event -> {
                appManager.config();

            });
        });
        btnStart.setOnAction( event -> {
            appManager.config();

        });
        if (btnStart.isFocused()) {
            btnStart.setBackground(new Background(new BackgroundFill(Paint.valueOf("blue"), new CornerRadii(5), Insets.EMPTY)) );

        }

        btnExit.setOnAction( event -> {
            Platform.exit();
        });
    }
    private void update() {
        if (appManager.getState() != AppState.BEGIN_STATE) {
            this.setVisible(false);

            return;
        }
        this.setVisible(true);

    }

}
