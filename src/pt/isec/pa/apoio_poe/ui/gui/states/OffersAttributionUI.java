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
import pt.isec.pa.apoio_poe.model.data.Alunos.DadosAluno;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;
import pt.isec.pa.apoio_poe.ui.gui.utils.OfferProposalType;
import pt.isec.pa.apoio_poe.ui.gui.utils.OffersAStudentType;

import java.util.List;

public class OffersAttributionUI extends BorderPane {
    AppManager appManager;
    Button btnManualAttribution, btnGetStudents, btnAutomatic, btnGetProposals, btnBack,btnDocAttr;
    VBox vLeft, vRight;
    VBox empty;
    HBox hBox, boxArea;
    TextArea areaText;
    Label appLabel, backgrou;
    ImageView isec;

    public OffersAttributionUI(AppManager appManager) {
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

        btnDocAttr = new Button("Próxima Fase");
        btnDocAttr.setPrefWidth(150);
        btnDocAttr.setMinHeight(30);
        btnDocAttr.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnManualAttribution  = new Button("Atribuição Manual");
        btnManualAttribution.setPrefWidth(150);
        btnManualAttribution.setMinHeight(30);
        btnManualAttribution.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnGetStudents = new Button("Obter Alunos");
        btnGetStudents.setPrefWidth(150);
        btnGetStudents.setMinHeight(30);
        btnGetStudents.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnGetProposals = new Button("Obter Propostas");
        btnGetProposals.setPrefWidth(150);
        btnGetProposals.setMinHeight(30);
        btnGetProposals.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnBack = new Button("Voltar");
        btnBack.setPrefWidth(150);
        btnBack.setMinHeight(30);
        btnBack.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );



        appLabel = new Label("Atribuição Propostas");
        appLabel.setPrefWidth(Integer.MAX_VALUE);
        appLabel.setPrefHeight(80);
        appLabel.setFont(new Font("Courier New",14));
        appLabel.setTextFill(Color.WHITE);
        appLabel.setAlignment(Pos.CENTER);
        appLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("#c95151"), new CornerRadii(10), Insets.EMPTY)) );

        //appLabel.setBorder(new Border(new BorderStroke(Paint.valueOf("red"),BorderStrokeStyle.DOTTED,new CornerRadii(10),BorderWidths.DEFAULT)));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(appLabel, btnManualAttribution, btnGetProposals, btnAutomatic, btnGetStudents, btnBack,btnDocAttr);
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

        btnAutomatic.setOnAction(ev-> {
            appManager.automaticProposalAttribution();
        });
        btnBack.setOnAction(ev -> {
            appManager.candidature();
        });
        btnManualAttribution.setOnAction(ev-> {
            appManager.manualOfferAttribution();
        });
        btnGetStudents.setOnAction(ev -> {
            List<OffersAStudentType> lst = List.of(OffersAStudentType.AUTOPROPOSTA_ASSOCIADA, OffersAStudentType.CANDIDATURA_REGISTADA, OffersAStudentType.SEM_PROPOSTA,OffersAStudentType.PROPOSTA_ATRIBUIDA);
            ChoiceDialog<OffersAStudentType> cd = new ChoiceDialog<>(lst.get(0), lst);
            cd.setTitle("Escolha");
            cd.setContentText("Options");
            cd.setHeaderText("Select an option");
            areaText.clear();
            cd.showAndWait().ifPresent(response-> {
                switch(response) {
                    case AUTOPROPOSTA_ASSOCIADA -> areaText.setText(appManager.getAP());  // TODO CHANGE THIS ONE
                    case SEM_PROPOSTA -> areaText.setText(appManager.getStudentsWithCandidatureRegistered());
                    case PROPOSTA_ATRIBUIDA -> areaText.setText(appManager.StudentsWithAttributedProposal());
                    case CANDIDATURA_REGISTADA -> areaText.setText(appManager.getStudentsNotRegistered());
                }
            });
        });
        btnGetProposals.setOnAction(ev-> {
            List<OfferProposalType> lst = List.of(OfferProposalType.AUTOPROPOSTAS_ASSOCIADAS, OfferProposalType.NAO_ATRIBUIDAS, OfferProposalType.PROPOSTA_ATRIBUIDA,OfferProposalType.CANDIDATURAS_REGISTADA);
            ChoiceDialog<OfferProposalType> cd = new ChoiceDialog<>(lst.get(0), lst);
            cd.setTitle("Escolha");
            cd.setContentText("Options");
            cd.setHeaderText("Select an option");
            areaText.clear();
            cd.showAndWait().ifPresent(response-> {
                switch(response) {
                    case AUTOPROPOSTAS_ASSOCIADAS -> areaText.setText(appManager.getAP());
                    case NAO_ATRIBUIDAS -> areaText.setText(appManager.getProjetos());
                    case PROPOSTA_ATRIBUIDA -> areaText.setText(appManager.getFreeProposals());
                    case CANDIDATURAS_REGISTADA -> areaText.setText(appManager.getAttributedProposal());
                }
            });
        });
        btnDocAttr.setOnAction(ev-> {
            appManager.advisorAttribution();
        });

    }
    private void update() {
        if (appManager.getState() != AppState.OFFERS_ATTRIBUTION_STATE) {
            this.setVisible(false);

            return;
        }
        this.setVisible(true);

    }

}
