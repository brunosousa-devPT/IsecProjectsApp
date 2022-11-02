package pt.isec.pa.apoio_poe.ui.gui.states;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import pt.isec.pa.apoio_poe.model.AppManager;
import pt.isec.pa.apoio_poe.model.data.Alunos.DadosAluno;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

import java.util.List;
import java.util.Optional;

public class ManualAttributionOfferUI extends BorderPane {
    AppManager appManager;
    Button btnDesassociar,btnRmAluno, btnGetData, voltar, btnAssociarProposta;
    VBox vLeft, vRight;
    VBox empty;
    HBox hBox,boxArea;
    Label appLabel, backgrou;
    ImageView isec;
    long numero;
    TextArea areaText;



    public ManualAttributionOfferUI(AppManager appManager) {

        this.appManager = appManager;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#073140"), CornerRadii.EMPTY, Insets.EMPTY)));


        btnAssociarProposta = new Button("Associar Proposta");
        btnAssociarProposta.setPrefWidth(150);
        btnAssociarProposta.setMinHeight(30);
        btnAssociarProposta.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );


        btnDesassociar = new Button("Desassociar Proposta");
        btnDesassociar.setPrefWidth(150);
        btnDesassociar.setMinHeight(30);
        btnDesassociar.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );


        voltar = new Button("Voltar");
        voltar.setPrefWidth(150);
        voltar.setMinHeight(30);
        voltar.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );


        btnGetData = new Button("Obter Dados");
        btnGetData.setPrefWidth(150);
        btnGetData.setMinHeight(30);
        btnGetData.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );


        appLabel = new Label("Atribuição Manual Proposta");
        appLabel.setPrefWidth(Integer.MAX_VALUE);
        appLabel.setPrefHeight(80);
        appLabel.setPrefWidth(400);
        appLabel.setFont(new Font("Courier New",12));
        appLabel.setTextFill(Color.WHITE);
        appLabel.setAlignment(Pos.CENTER);
        appLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("#c95151"), new CornerRadii(10), Insets.EMPTY)) );


        //appLabel.setBorder(new Border(new BorderStroke(Paint.valueOf("red"),BorderStrokeStyle.DOTTED,new CornerRadii(10),BorderWidths.DEFAULT)));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(appLabel, btnGetData, btnAssociarProposta, btnDesassociar, voltar);
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
        areaText.setText("Alunos:\n " + appManager.getAlunosString()+ "\nPropostas: " + appManager.getPropostasString());
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
        btnDesassociar.setOnAction(ev-> {
            //appManager.removeNumberProposal("Proposal ID")
            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Associar Proposta");
            tid.setHeaderText("ID Proposta: ");
            tid.setContentText("ID Proposta");
            Optional<String> result = tid.showAndWait();
            result.ifPresent(s-> {
                appManager.removeNumberProposal(s);
            });
            areaText.clear();
            areaText.setText("Alunos:\n " + appManager.getAlunosString()+ "\nPropostas: " + appManager.getPropostasString());
        });
        btnAssociarProposta.setOnAction(ev-> {
            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Associar Proposta");
            tid.setHeaderText("ID Proposta: ");
            tid.setContentText("ID Proposta");
            Optional<String> result = tid.showAndWait();
            TextInputDialog tid2 = new TextInputDialog();
            tid2.setTitle("Associar Proposta");
            tid2.setHeaderText("Numero Aluno: ");
            tid2.setContentText("Numero");
            Optional<String> result2 = tid2.showAndWait();
            result.ifPresent(s-> {

                result2.ifPresent(numero-> {
                    appManager.associateStudentWithProposal(Long.parseLong(numero),s);

                    areaText.clear();
                    areaText.setText("Alunos:\n " + appManager.getAlunosString()+ "\nPropostas: " + appManager.getPropostasString());
                });
            });

            //appManager.associateStudentWithProposal(numero, ID);
        });
        btnGetData.setOnAction(ev -> {

            areaText.clear();
            areaText.setText("Alunos:\n " + appManager.getAlunosString()+ "\nPropostas: " + appManager.getPropostasString());
        });
        voltar.setOnAction(ev -> {
            appManager.designateOffer();

        });







    }

    private void update() {
        if (appManager.getState() != AppState.MANUAL_ATTRIBUTION_OFFER_STATE) {
            this.setVisible(false);

            return;
        }


        this.setVisible(true);

    }
}
