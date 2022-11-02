package pt.isec.pa.apoio_poe.ui.gui.states;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import pt.isec.pa.apoio_poe.model.AppManager;
import pt.isec.pa.apoio_poe.model.data.Alunos.Aluno;
import pt.isec.pa.apoio_poe.model.data.Alunos.DadosAluno;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ManageStudentsUI extends BorderPane {
    AppManager appManager;
    Button btnnAddAluno,btnRmAluno, btnGetData, btnEditAluno, btnBack, btnGetStudents;
    VBox vLeft, vRight;
    VBox empty;
    HBox hBox,boxArea;
    Label appLabel, backgrou;
    ImageView isec;
    TextArea areaText;
    private ListView<String> listViewAluno;



    public ManageStudentsUI(AppManager appManager) {

        this.appManager = appManager;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#073140"), CornerRadii.EMPTY, Insets.EMPTY)));
        btnGetData = new Button("Consultar Aluno");
        btnGetData.setPrefWidth(150);
        btnGetData.setMinHeight(30);
        btnGetData.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnGetStudents = new Button("Obter Alunos");
        btnGetStudents.setPrefWidth(150);
        btnGetStudents.setMinHeight(30);
        btnGetStudents.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );


        btnnAddAluno  = new Button("Adicionar Aluno");
        btnnAddAluno.setPrefWidth(150);
        btnnAddAluno.setMinHeight(30);
        btnnAddAluno.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnRmAluno = new Button("Remover Aluno");
        btnRmAluno.setPrefWidth(150);
        btnRmAluno.setMinHeight(30);
        btnRmAluno.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnEditAluno = new Button("Editar Aluno");
        btnEditAluno.setPrefWidth(150);
        btnEditAluno.setMinHeight(30);
        btnEditAluno.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnBack = new Button("Voltar");
        btnBack.setPrefWidth(150);
        btnBack.setMinHeight(30);
        btnBack.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );




        appLabel = new Label("MANAGE STUDENTS");
        appLabel.setPrefWidth(Integer.MAX_VALUE);
        appLabel.setPrefHeight(80);
        appLabel.setFont(new Font("Courier New",18));
        appLabel.setTextFill(Color.WHITE);
        appLabel.setAlignment(Pos.CENTER);
        appLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("#c95151"), new CornerRadii(10), Insets.EMPTY)) );


        //appLabel.setBorder(new Border(new BorderStroke(Paint.valueOf("red"),BorderStrokeStyle.DOTTED,new CornerRadii(10),BorderWidths.DEFAULT)));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(appLabel, btnGetStudents,btnnAddAluno, btnEditAluno, btnGetData, btnRmAluno, btnBack);
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

        btnnAddAluno.setOnAction(ev-> {
            // TODO adicionar aluno
        });
        btnGetData.setOnAction(ev -> {
            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Obter Aluno");
            tid.setHeaderText("Numero: :");
            tid.setContentText("Numero estudante");

            Optional<String> result = tid.showAndWait();
            areaText.clear();
            result.ifPresent(s -> areaText.setText(appManager.getStudentInfo(Long.parseLong(s))));
        });
        btnEditAluno.setOnAction(ev -> {
            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Obter Aluno");
            tid.setHeaderText("Numero: :");
            tid.setContentText("Numero estudante");

            Optional<String> result = tid.showAndWait();
            TextInputDialog tid2 = new TextInputDialog();
            tid2.setTitle("New Data");
            tid2.setHeaderText("New Data: ");
            tid2.setContentText("Insira a data");

            Optional<String> result2 = tid2.showAndWait();
            List<DadosAluno> lst = List.of(DadosAluno.NOME, DadosAluno.EMAIL, DadosAluno.CLASSIFICACAO, DadosAluno.CURSO, DadosAluno.RAMO, DadosAluno.ACESSO,DadosAluno.NUMERO);
            ChoiceDialog<DadosAluno> cd = new ChoiceDialog<>(lst.get(0), lst);
            cd.setTitle("Tipo de Dado");
            cd.setContentText("Options");
            cd.setHeaderText("Select an option");
            cd.showAndWait().ifPresent(response-> {
                result.ifPresent(s -> {
                    result2.ifPresent(b->appManager.editAluno(Long.parseLong(s),response,b));
                    areaText.clear();
                    areaText.setText(appManager.getAlunosString());
                });
            });


        });
        btnBack.setOnAction(ev-> {
            appManager.config();
        });
        btnGetStudents.setOnAction(ev-> {
            areaText.setText(appManager.getAlunosString());

        });
        btnRmAluno.setOnAction(ev -> {
            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Remover Aluno");
            tid.setHeaderText("Numero: :");
            tid.setContentText("Numero estudante");
            Optional<String> result = tid.showAndWait();
            areaText.clear();

            result.ifPresent(s->appManager.delStudent(Long.parseLong(s)));
            areaText.setText(appManager.getAlunosString());

        });

    }

    private void update() {
        if (appManager.getState() != AppState.MANAGE_STUDENTS_STATE) {
            this.setVisible(false);

            return;
        }


        this.setVisible(true);

    }
}
