package pt.isec.pa.apoio_poe.ui.gui.states;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import pt.isec.pa.apoio_poe.model.AppManager;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

public class QueryUI extends BorderPane {
    AppManager appManager;
    ToggleButton btnSWAP, btnASPND, btnGFP, btnGAP, btnGIIN, btnExit;
    VBox vLeft, vRight;
    VBox empty;
    HBox hBox,boxArea;
    TextArea areaText;
    Label appLabel, backgrou;
    ImageView isec;

    public QueryUI(AppManager appManager) {
        this.appManager = appManager;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#235769"), CornerRadii.EMPTY, Insets.EMPTY)));
        btnGFP = new ToggleButton("Propostas Livres");
        btnGFP.setPrefWidth(500);
        btnGFP.setMinHeight(30);
        btnGFP.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnSWAP = new ToggleButton("Estudantes com Propostas");
        btnSWAP.setPrefWidth(500);
        btnSWAP.setMinHeight(30);
        btnSWAP.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnASPND = new ToggleButton("Estudantes sem Propoposta e com candidatura");
        btnASPND.setPrefWidth(500);
        btnASPND.setMinHeight(30);
        btnASPND.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnGAP = new ToggleButton("Propostas Atribúidas ");
        btnGAP.setPrefWidth(500);
        btnGAP.setMinHeight(30);
        btnGAP.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnGIIN = new ToggleButton("Orientações Docentes");
        btnGIIN.setPrefWidth(500);
        btnGIIN.setMinHeight(30);
        btnGIIN.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnExit = new ToggleButton("Fechar Aplicação");
        btnExit.setPrefWidth(500);
        btnExit.setMinHeight(30);
        btnExit.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );



        appLabel = new Label("Consulta Dados");
        appLabel.setPrefWidth(Integer.MAX_VALUE);
        appLabel.setPrefHeight(80);
        appLabel.setFont(new Font("Courier New",18));
        appLabel.setTextFill(Color.WHITE);
        appLabel.setAlignment(Pos.CENTER);
        appLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("#c95151"), new CornerRadii(10), Insets.EMPTY)) );

        //appLabel.setBorder(new Border(new BorderStroke(Paint.valueOf("red"),BorderStrokeStyle.DOTTED,new CornerRadii(10),BorderWidths.DEFAULT)));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(appLabel, btnSWAP, btnGAP, btnGFP, btnASPND, btnGIIN,btnExit);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(25,0,0,10));
        vBox.setSpacing(5);
        vBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("#235769"), CornerRadii.EMPTY, Insets.EMPTY)) );

        vLeft = new VBox(vBox);
        vLeft.setPrefWidth(300);
        vLeft.setAlignment(Pos.TOP_LEFT);
        vLeft.setBackground(new Background(new BackgroundFill(Paint.valueOf("#235769"), CornerRadii.EMPTY, Insets.EMPTY)) );
        vLeft.setPrefHeight(this.getHeight());
        areaText = new TextArea();
        areaText.setMaxWidth(350);
        areaText.setMaxHeight(250);
        areaText.setText(appManager.getAlunosString());
        areaText.setEditable(false);

        boxArea = new HBox(areaText);
        boxArea.setAlignment(Pos.BOTTOM_CENTER);
        boxArea.setPrefWidth(100);
        isec = new ImageView();
        isec =  ImageManager.getImageView("background.png",60);

        vRight = new VBox();
        vRight.setPrefWidth(400);
        vRight.setSpacing(60);
        vRight.getChildren().addAll(isec,boxArea);
        vRight.setAlignment(Pos.TOP_CENTER);



        hBox  = new HBox();
        hBox.getChildren().addAll(vLeft, vRight);
        hBox.setPrefWidth(Integer.MAX_VALUE);

        this.setLeft(hBox);



    }
    private void registerHandlers() {
        appManager.addPropertyChangeListener(evt -> { update(); });
        btnExit.setOnAction(ev -> {
            Platform.exit();
        });
        btnGFP.setOnAction(ev-> {
            areaText.clear();
            areaText.setText(appManager.getFreeProposals());
        });
        btnASPND.setOnAction(ev-> {
            areaText.clear();
            areaText.setText(appManager.getAllStudentProposalsNotDesignated());
        });
        btnSWAP.setOnAction(ev-> {
            areaText.clear();
            areaText.setText(appManager.StudentsWithAttributedProposal());
        });
        btnGAP.setOnAction(ev-> {
            areaText.clear();
            areaText.setText(appManager.getAttributedProposal());
        });
        btnGIIN.setOnAction(ev-> {
            areaText.clear();
            areaText.setText(appManager.getInfoDocenteInProposals());
        });
    }
    private void update() {
        if (appManager.getState() != AppState.QUERY_STATE) {
            this.setVisible(false);

            return;
        }
        this.setVisible(true);

    }
}
