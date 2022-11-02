package pt.isec.pa.apoio_poe.ui.gui.states;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import pt.isec.pa.apoio_poe.model.AppManager;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;
import pt.isec.pa.apoio_poe.ui.gui.utils.AdvisorOfferType;
import pt.isec.pa.apoio_poe.ui.gui.utils.OfferProposalType;
import pt.isec.pa.apoio_poe.ui.gui.utils.OffersAStudentType;

import java.util.List;

public class AdvisorAttributionUI extends BorderPane {
    AppManager appManager;
    Button btnManualAttribution, btnGetData, btnAutomatic, btnExportFile, btnBack,btnDocAttr;
    VBox vLeft, vRight;
    VBox empty;
    HBox hBox, boxArea;
    TextArea areaText;
    Label appLabel, backgrou;
    ImageView isec;

    public AdvisorAttributionUI(AppManager appManager) {
        this.appManager = appManager;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#073140"), CornerRadii.EMPTY, Insets.EMPTY)));
        btnAutomatic = new Button("Atribuição automática");
        btnAutomatic.setPrefWidth(150);
        btnAutomatic.setMinHeight(30);
        btnAutomatic.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnDocAttr = new Button("Consulta");
        btnDocAttr.setPrefWidth(150);
        btnDocAttr.setMinHeight(30);
        btnDocAttr.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnManualAttribution  = new Button("Atribuição Manual");
        btnManualAttribution.setPrefWidth(150);
        btnManualAttribution.setMinHeight(30);
        btnManualAttribution.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnGetData = new Button("Obtenção de Dados");
        btnGetData.setPrefWidth(150);
        btnGetData.setMinHeight(30);
        btnGetData.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnExportFile = new Button("Obter Propostas");
        btnExportFile.setPrefWidth(150);
        btnExportFile.setMinHeight(30);
        btnExportFile.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnBack = new Button("Voltar");
        btnBack.setPrefWidth(150);
        btnBack.setMinHeight(30);
        btnBack.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );



        appLabel = new Label("Atribuição Docentes");
        appLabel.setPrefWidth(Integer.MAX_VALUE);
        appLabel.setPrefHeight(80);
        appLabel.setFont(new Font("Courier New",15));
        appLabel.setTextFill(Color.WHITE);
        appLabel.setAlignment(Pos.CENTER);
        appLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("#c95151"), new CornerRadii(10), Insets.EMPTY)) );

        //appLabel.setBorder(new Border(new BorderStroke(Paint.valueOf("red"),BorderStrokeStyle.DOTTED,new CornerRadii(10),BorderWidths.DEFAULT)));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(appLabel, btnManualAttribution, btnAutomatic, btnGetData, btnBack,btnDocAttr);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(25,50,0,50));
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

        btnAutomatic.setOnAction(ev-> {
            appManager.automaticTeacherAttribution();
        });
        btnBack.setOnAction(ev -> {
            appManager.designateOffer();
        });
        btnManualAttribution.setOnAction(ev-> {
            appManager.manualTeacherAttribution();
        });

        btnGetData.setOnAction(ev -> {
            List<AdvisorOfferType> lst = List.of(AdvisorOfferType.ORIENTACOES_DOCENTE, AdvisorOfferType.ESTUDANTES_COM_PROPOSTA_ORIENTADOR_ASSOCIADO, AdvisorOfferType.ESTUDANTES_COM_PROPOSTA_SEM_ORIENTADOR);
            ChoiceDialog<AdvisorOfferType> cd = new ChoiceDialog<>(lst.get(0), lst);
            cd.setTitle("Escolha");
            cd.setContentText("Options");
            cd.setHeaderText("Select an option");
            areaText.clear();
            cd.showAndWait().ifPresent(response-> {
                switch(response) {
                    case ORIENTACOES_DOCENTE -> areaText.setText(appManager.getInfoDocenteInProposals());
                    case ESTUDANTES_COM_PROPOSTA_ORIENTADOR_ASSOCIADO -> areaText.setText(appManager.getStudentsWProposalOrientator());
                    case ESTUDANTES_COM_PROPOSTA_SEM_ORIENTADOR -> areaText.setText(appManager.getStudentsWProposalNoOrientator());

                }
            });
        });


        btnDocAttr.setOnAction(ev-> {
            appManager.query();
        });

    }
    private void update() {
        if (appManager.getState() != AppState.ADVISORS_ATTRIBUTION_STATE) {
            this.setVisible(false);

            return;
        }
        this.setVisible(true);

    }

}
