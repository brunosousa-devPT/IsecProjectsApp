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
import pt.isec.pa.apoio_poe.model.data.Docentes.DadosDocente;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

import java.util.List;
import java.util.Optional;

public class ManageTeachersUI extends BorderPane {

    AppManager appManager;
    Button btnAddDocente, btnRmDocente, btnGetData, btnEditDocente, btnBack,btnGetDocentes;
    VBox vLeft, vRight;
    VBox empty;
    HBox hBox;
    Label appLabel, backgrou;
    ImageView isec;
    private TextArea areaText;
    private HBox boxArea;
    public ManageTeachersUI(AppManager appManager) {
        this.appManager = appManager;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#073140"), CornerRadii.EMPTY, Insets.EMPTY)));

        btnGetDocentes = new Button("Obter Docentes");
        btnGetDocentes.setPrefWidth(150);
        btnGetDocentes.setMinHeight(30);
        btnGetDocentes.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnGetData = new Button("Consultar Docente");
        btnGetData.setPrefWidth(150);
        btnGetData.setMinHeight(30);
        btnGetData.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnAddDocente = new Button("Adicionar Docente");
        btnAddDocente.setPrefWidth(150);
        btnAddDocente.setMinHeight(30);
        btnAddDocente.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnRmDocente = new Button("Remover Docente");
        btnRmDocente.setPrefWidth(150);
        btnRmDocente.setMinHeight(30);
        btnRmDocente.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnEditDocente = new Button("Editar Docente");
        btnEditDocente.setPrefWidth(150);
        btnEditDocente.setMinHeight(30);
        btnEditDocente.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnBack = new Button("Voltar");
        btnBack.setPrefWidth(150);
        btnBack.setMinHeight(30);
        btnBack.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );



        appLabel = new Label("Manage Teachers");
        appLabel.setPrefWidth(Integer.MAX_VALUE);
        appLabel.setPrefHeight(80);
        appLabel.setFont(new Font("Courier New",18));
        appLabel.setTextFill(Color.WHITE);
        appLabel.setAlignment(Pos.CENTER);
        appLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("#c95151"), new CornerRadii(10), Insets.EMPTY)) );

        //appLabel.setBorder(new Border(new BorderStroke(Paint.valueOf("red"),BorderStrokeStyle.DOTTED,new CornerRadii(10),BorderWidths.DEFAULT)));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(appLabel, btnGetDocentes, btnAddDocente, btnEditDocente, btnGetData, btnRmDocente, btnBack);
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
        areaText.setText(appManager.getDocentesString());
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

        btnGetDocentes.setOnAction(ev-> {
            areaText.clear();
            areaText.setText(appManager.getDocentesString());
        });
        btnAddDocente.setOnAction(ev->{
            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Adicionar Docente");
            tid.setHeaderText("Email: :");
            tid.setContentText("Email docente");

            Optional<String> result = tid.showAndWait();
            TextInputDialog tid2 = new TextInputDialog();

            tid2.setTitle("Adicionar Docente");
            tid2.setHeaderText("Nome: :");
            tid2.setContentText("Nome docente");

            Optional<String> result2 = tid2.showAndWait();
            areaText.clear();
            result2.ifPresent(b -> {
                result.ifPresent(s -> appManager.addDocente(b,s));
            });
            areaText.clear();
            areaText.setText(appManager.getDocentesString());
        });

        btnBack.setOnAction(ev-> {
            appManager.config();
        });
        btnGetData.setOnAction(ev-> {
            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Obter Docente");
            tid.setHeaderText("Email: :");
            tid.setContentText("Email docente");

            Optional<String> result = tid.showAndWait();
            result.ifPresent(s -> areaText.setText(appManager.getInfoDocente(s)));
        });
        btnRmDocente.setOnAction(ev-> {
            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Remover Docente");
            tid.setHeaderText("Email: :");
            tid.setContentText("Email docente");

            Optional<String> result = tid.showAndWait();
            result.ifPresent(s -> appManager.removeDocente(s));
            areaText.clear();
            areaText.setText(appManager.getDocentesString());
        });
        btnEditDocente.setOnAction(ev -> {
            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Obter Docente");
            tid.setHeaderText("Email: :");
            tid.setContentText("Email docente");
            Optional<String> result = tid.showAndWait();

            TextInputDialog tid2 = new TextInputDialog();
            tid2.setTitle("New Data");
            tid2.setHeaderText("New Data: ");
            tid2.setContentText("Insira a data");
            Optional<String> result2 = tid2.showAndWait();
            List<DadosDocente> lst = List.of(DadosDocente.NOME, DadosDocente.EMAIL);
            ChoiceDialog<DadosDocente> cd = new ChoiceDialog<>(lst.get(0), lst);
            cd.showAndWait().ifPresent(response-> {
                result.ifPresent(s -> {
                    result2.ifPresent(b-> {
                            appManager.editDocente(response,b,s);
                        areaText.clear();
                        areaText.setText(appManager.getDocentesString());
                    });
                });

                    });

        });

    }
    private void update() {
        if (appManager.getState() != AppState.MANAGE_TEACHERS_STATE) {
            this.setVisible(false);

            return;
        }
        this.setVisible(true);

    }

}
