package pt.isec.pa.apoio_poe.ui.gui.states;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import pt.isec.pa.apoio_poe.model.AppManager;
import pt.isec.pa.apoio_poe.model.data.DataTypes;
import pt.isec.pa.apoio_poe.model.data.Propostas.TipoPropostas;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

import java.util.List;
import java.util.Optional;

import static pt.isec.pa.apoio_poe.model.data.ManagementTypes.EXPORT;
import static pt.isec.pa.apoio_poe.model.data.ManagementTypes.IMPORT;

public class ConfigUI extends BorderPane {
    AppManager appManager;
    Button btnImportFile,btnGestAlunos,btnGestTeachers,btnGestProjects,btnClosePhase,btnExportFile,btnNextPhase;
    VBox vLeft, vRight;
    VBox empty;
    HBox hBox;
    Label appLabel, backgrou;
    ImageView isec;

    public ConfigUI(AppManager appManager) {

        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }
    private void createViews() {
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#073140"), CornerRadii.EMPTY, Insets.EMPTY)));
        btnImportFile = new Button("Importar Ficheiro");
        btnImportFile.setPrefWidth(150);
        btnImportFile.setMinHeight(30);
        btnImportFile.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnGestAlunos  = new Button("Gestão de Alunos");
        btnGestAlunos.setPrefWidth(150);
        btnGestAlunos.setMinHeight(30);
        btnGestAlunos.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnGestTeachers = new Button("Gestão de Docentes");
        btnGestTeachers.setPrefWidth(150);
        btnGestTeachers.setMinHeight(30);
        btnGestTeachers.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnGestProjects = new Button("Gestão Projetos");
        btnGestProjects.setPrefWidth(150);
        btnGestProjects.setMinHeight(30);
        btnGestProjects.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnClosePhase = new Button("Fechar Fase");
        btnClosePhase.setPrefWidth(150);
        btnClosePhase.setMinHeight(30);
        btnClosePhase.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnNextPhase = new Button("Próxima Fase");
        btnNextPhase.setPrefWidth(150);
        btnNextPhase.setMinHeight(30);
        btnNextPhase.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnExportFile = new Button("Export Ficheiro");
        btnExportFile.setPrefWidth(150);
        btnExportFile.setMinHeight(30);
        btnExportFile.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );


        appLabel = new Label("CONFIG");
        appLabel.setPrefWidth(Integer.MAX_VALUE);
        appLabel.setPrefHeight(80);
        appLabel.setFont(new Font("Courier New",26));
        appLabel.setTextFill(Color.WHITE);
        appLabel.setAlignment(Pos.CENTER);
        appLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("#c95151"), new CornerRadii(10), Insets.EMPTY)) );

        //appLabel.setBorder(new Border(new BorderStroke(Paint.valueOf("red"),BorderStrokeStyle.DOTTED,new CornerRadii(10),BorderWidths.DEFAULT)));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(appLabel, btnImportFile, btnGestAlunos,btnGestTeachers, btnGestProjects, btnExportFile,btnNextPhase);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(25,50,0,50));
        vBox.setSpacing(5);
        vBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("#235769"), CornerRadii.EMPTY, Insets.EMPTY)) );

        vLeft = new VBox(vBox);
        vLeft.setPrefWidth(300);
        vLeft.setAlignment(Pos.TOP_LEFT);
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
        btnGestAlunos.setOnAction(event -> {
            appManager.manageStudent();
        });
        btnImportFile.setOnAction(ev -> {

            List<DataTypes> lst = List.of(DataTypes.ALUNO, DataTypes.CANDIDATURAS, DataTypes.DOCENTES,DataTypes.PROPOSTAS);
            ChoiceDialog<DataTypes> cd = new ChoiceDialog<>(lst.get(0), lst);
            cd.setTitle("Tipo de Ficheiro");
            cd.setContentText("Options");
            cd.setHeaderText("Select an option");

            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Importar Ficheiro");
            tid.setHeaderText("Nome Ficheiro: :");
            tid.setContentText("filename");
            Optional<String> result = tid.showAndWait();


            result.ifPresent(s-> {
                cd.showAndWait().ifPresent(response-> {
                    switch(response) {
                        case CANDIDATURAS -> {
                            appManager.importFile(IMPORT, DataTypes.CANDIDATURAS, s);
                        }
                        case ALUNO -> {
                            appManager.importFile(IMPORT, DataTypes.ALUNO, s);
                        }
                        case DOCENTES -> {
                            appManager.importFile(IMPORT, DataTypes.DOCENTES, s);
                        }
                        case PROPOSTAS -> {
                            appManager.importFile(IMPORT, DataTypes.PROPOSTAS, s);
                        }
                    }
                });

            });



        });
        btnGestTeachers.setOnAction( event -> {
            appManager.manageTeacher();
        });

        btnGestProjects.setOnAction( event -> {
            appManager.managePoE();
        });
        btnNextPhase.setOnAction(ev -> {
            appManager.candidature();
        });

        btnExportFile.setOnAction(ev-> {

            List<DataTypes> lst = List.of(DataTypes.ALUNO, DataTypes.CANDIDATURAS, DataTypes.DOCENTES,DataTypes.PROPOSTAS);
            ChoiceDialog<DataTypes> cd = new ChoiceDialog<>(lst.get(0), lst);
            cd.setTitle("Tipo de Ficheiro");
            cd.setContentText("Options");
            cd.setHeaderText("Select an option");

            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Importar Ficheiro");
            tid.setHeaderText("Nome Ficheiro: :");
            tid.setContentText("filename");
            Optional<String> result = tid.showAndWait();


            result.ifPresent(s-> {
                cd.showAndWait().ifPresent(response-> {
                    switch(response) {
                        case CANDIDATURAS -> {
                            appManager.importFile(EXPORT, DataTypes.CANDIDATURAS, s);
                        }
                        case ALUNO -> {
                            appManager.importFile(EXPORT, DataTypes.ALUNO, s);
                        }
                        case DOCENTES -> {
                            appManager.importFile(EXPORT, DataTypes.DOCENTES, s);
                        }
                        case PROPOSTAS -> {
                            appManager.importFile(EXPORT, DataTypes.PROPOSTAS, s);
                        }
                    }
                });

            });
    });
}

    private void update() {
        if (appManager.getState() != AppState.CONFIG_STATE) {
            this.setVisible(false);

            return;
        }
        this.setVisible(true);

    }
}
