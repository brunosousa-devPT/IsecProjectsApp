package pt.isec.pa.apoio_poe.ui.gui.states;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import pt.isec.pa.apoio_poe.model.AppManager;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class CandidatureUI  extends BorderPane {
    AppManager appManager;
    Button btnRemoverCand, btnEditCand, btnVerCand,btnAdicionarCand, btnCandidaturasObter, btnBack,btnNextPhase;
    VBox vLeft, vRight;
    VBox empty;
    HBox hBox, boxArea;
    TextArea areaText;
    Label appLabel, backgrou;
    ImageView isec;

    public CandidatureUI(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#073140"), CornerRadii.EMPTY, Insets.EMPTY)));
        btnAdicionarCand = new Button("Adicionar Candidatura");
        btnAdicionarCand.setPrefWidth(150);
        btnAdicionarCand.setMinHeight(30);
        btnAdicionarCand.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnRemoverCand = new Button("Remover Candidatura");
        btnRemoverCand.setPrefWidth(150);
        btnRemoverCand.setMinHeight(30);
        btnRemoverCand.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnEditCand = new Button("Editar Candidatura");
        btnEditCand.setPrefWidth(150);
        btnEditCand.setMinHeight(30);
        btnEditCand.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnVerCand = new Button("Ver Candidatura");
        btnVerCand.setPrefWidth(150);
        btnVerCand.setMinHeight(30);
        btnVerCand.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );



        btnCandidaturasObter = new Button("Obter Candidaturas");
        btnCandidaturasObter.setPrefWidth(150);
        btnCandidaturasObter.setMinHeight(30);
        btnCandidaturasObter.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnNextPhase = new Button("Próxima Fase");
        btnNextPhase.setPrefWidth(150);
        btnNextPhase.setMinHeight(30);
        btnNextPhase.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnBack = new Button("Voltar");
        btnBack.setPrefWidth(150);
        btnBack.setMinHeight(30);
        btnBack.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        appLabel = new Label("Candidaturas");
        appLabel.setPrefWidth(Integer.MAX_VALUE);
        appLabel.setPrefHeight(80);
        appLabel.setFont(new Font("Courier New",18));
        appLabel.setTextFill(Color.WHITE);
        appLabel.setAlignment(Pos.CENTER);
        appLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("#c95151"), new CornerRadii(10), Insets.EMPTY)) );

        //appLabel.setBorder(new Border(new BorderStroke(Paint.valueOf("red"),BorderStrokeStyle.DOTTED,new CornerRadii(10),BorderWidths.DEFAULT)));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(appLabel, btnCandidaturasObter,btnRemoverCand,btnEditCand,btnAdicionarCand, btnVerCand,btnBack,btnNextPhase);
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
        areaText.setText(appManager.getCandidaturasString());
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
        btnNextPhase.setOnAction(ev-> {
            appManager.designateOffer();
        });
        btnBack.setOnAction(ev-> {
            appManager.config();
        });
        btnCandidaturasObter.setOnAction(ev-> {
            areaText.clear();
            areaText.setText(appManager.getCandidaturasString());
        });

        btnRemoverCand.setOnAction(ev-> {
            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Remover Candidatura");
            tid.setHeaderText("Numero: :");
            tid.setContentText("Numero estudante");
            Optional<String> result = tid.showAndWait();
            areaText.clear();

            result.ifPresent(s->appManager.removeCandidatura(Long.parseLong(s)));
            areaText.setText(appManager.getCandidaturasString());
        });
        btnVerCand.setOnAction(ev-> {
            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Obter Candidatura");
            tid.setHeaderText("Numero: :");
            tid.setContentText("Numero estudante");
            Optional<String> result = tid.showAndWait();
            areaText.clear();

            result.ifPresent(s->areaText.setText(appManager.getCandid(Long.parseLong(s))));
        });

        btnAdicionarCand.setOnAction(ev-> {
            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Adicionar Candidatura");
            tid.setHeaderText("Numero: ");
            tid.setContentText("Numero estudante");
            Optional<String> result = tid.showAndWait();
            areaText.clear();

            result.ifPresent(s->appManager.addCandidatura(Long.parseLong(s)));
            areaText.setText(appManager.getCandidaturasString());
        });
        btnEditCand.setOnAction(ev-> {
            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Editar Candidatura");
            tid.setHeaderText("Numero: ");
            tid.setContentText("Numero estudante");
            Optional<String> result2 = tid.showAndWait();

            result2.ifPresent(number-> {
                do {
                    TextInputDialog tid2 = new TextInputDialog();
                    tid2.setTitle("Adicionar PropostaID");
                    tid2.setHeaderText("ID: ");
                    tid2.setContentText("ID PROPOSTA");

                    Optional<String> result = tid2.showAndWait();
                    areaText.clear();
                    if (result.isEmpty()) break;
                    result.ifPresent(s->appManager.addProposalId(Long.parseLong(number),s));
                    areaText.setText(appManager.getCandidaturasString());
                }while(true);
                    });
            areaText.setText(appManager.getCandidaturasString());
        });
    }

    private void update() {
        if (appManager.getState() != AppState.CANDIDATURE_STATE) {
            this.setVisible(false);

            return;
        }
        this.setVisible(true);

    }
}
