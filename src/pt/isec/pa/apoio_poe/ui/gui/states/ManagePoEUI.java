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
import pt.isec.pa.apoio_poe.model.data.Propostas.TipoPropostas;
import pt.isec.pa.apoio_poe.model.data.Ramos;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

import java.util.List;
import java.util.Optional;

public class ManagePoEUI extends BorderPane {
    AppManager appManager;
    Button btnnAddAluno,btnRmAluno, btnGetData, btnEditAluno, btnBack,btnGetPropostas;
    VBox vLeft, vRight;
    VBox empty;
    HBox hBox, boxArea;
    TextArea areaText;
    Label appLabel, backgrou;
    ImageView isec;

    public ManagePoEUI(AppManager appManager) {
        this.appManager = appManager;
        createViews();
        registerHandlers();

        update();

    }

    private void createViews() {
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("#073140"), CornerRadii.EMPTY, Insets.EMPTY)));
        btnGetData = new Button("Consultar Proposta");
        btnGetData.setPrefWidth(150);
        btnGetData.setMinHeight(30);
        btnGetData.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnGetPropostas = new Button("Obter Proposta");
        btnGetPropostas.setPrefWidth(150);
        btnGetPropostas.setMinHeight(30);
        btnGetPropostas.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );


        btnnAddAluno  = new Button("Adicionar Proposta");
        btnnAddAluno.setPrefWidth(150);
        btnnAddAluno.setMinHeight(30);
        btnnAddAluno.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnRmAluno = new Button("Remover Proposta");
        btnRmAluno.setPrefWidth(150);
        btnRmAluno.setMinHeight(30);
        btnRmAluno.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnEditAluno = new Button("Editar Proposta");
        btnEditAluno.setPrefWidth(150);
        btnEditAluno.setMinHeight(30);
        btnEditAluno.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );

        btnBack = new Button("Voltar");
        btnBack.setPrefWidth(150);
        btnBack.setMinHeight(30);
        btnBack.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), new CornerRadii(5), Insets.EMPTY)) );




        appLabel = new Label("Manage Propostas");
        appLabel.setPrefWidth(Integer.MAX_VALUE);
        appLabel.setPrefHeight(80);
        appLabel.setFont(new Font("Courier New",18));
        appLabel.setTextFill(Color.WHITE);
        appLabel.setAlignment(Pos.CENTER);
        appLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("#c95151"), new CornerRadii(10), Insets.EMPTY)) );


        //appLabel.setBorder(new Border(new BorderStroke(Paint.valueOf("red"),BorderStrokeStyle.DOTTED,new CornerRadii(10),BorderWidths.DEFAULT)));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(appLabel, btnGetPropostas,btnnAddAluno, btnGetData, btnRmAluno, btnBack);
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
        areaText.setEditable(false);

        areaText.setText(appManager.getPropostasString());
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
        btnGetPropostas.setOnAction(ev-> {
            areaText.clear();
            areaText.setText(appManager.getPropostasString());
        });

        btnBack.setOnAction(ev-> {
            appManager.config();
        });
        btnGetData.setOnAction(ev -> {
            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Obter Proposta");
            tid.setHeaderText("ID: :");
            tid.setContentText("ID Proposta");
            Optional<String> result = tid.showAndWait();
            areaText.clear();
            result.ifPresent(s-> areaText.setText(appManager.getPropostaStr(s)));
        });

        btnRmAluno.setOnAction(ev-> {
            TextInputDialog tid = new TextInputDialog();
            tid.setTitle("Remover Proposta");
            tid.setHeaderText("ID: :");
            tid.setContentText("ID Proposta");
            Optional<String> result = tid.showAndWait();
            areaText.clear();

            result.ifPresent(s->appManager.removeProposta(s));
            areaText.setText(appManager.getPropostasString());
        });

        btnnAddAluno.setOnAction(ev-> {  // TODO add proposta
            List<TipoPropostas> lst = List.of(TipoPropostas.PROJETO, TipoPropostas.AUTOPROPOSTA, TipoPropostas.ESTAGIO);
            ChoiceDialog<TipoPropostas> cd = new ChoiceDialog<>(lst.get(0), lst);
            cd.setTitle("Tipo de Porposta");
            cd.setContentText("Options");
            cd.setHeaderText("Select an option");
            cd.showAndWait().ifPresent(response-> {
                switch (response) {
                    case PROJETO -> {
                        //appManager.addProjeto(idproposta, ramo, titulo,email)
                        TextInputDialog tid = new TextInputDialog();  // ID proposta
                        tid.setTitle("Adicionar Proposta");
                        tid.setHeaderText("ID: :");
                        tid.setContentText("ID Proposta");
                        Optional<String> result = tid.showAndWait();

                        TextInputDialog tid2 = new TextInputDialog();  // ID proposta
                        tid.setTitle("Adicionar Proposta");
                        tid.setHeaderText("Titulo: :");
                        tid.setContentText("Titulo");
                        Optional<String> result2 = tid.showAndWait();

                        TextInputDialog tid3 = new TextInputDialog();  // ID proposta
                        tid.setTitle("Adicionar Proposta");
                        tid.setHeaderText("Email");
                        tid.setContentText("Email");
                        Optional<String> result3 = tid.showAndWait();



                        List<Ramos> lst2 = List.of(Ramos.DA, Ramos.RAS, Ramos.SI);
                        ChoiceDialog<Ramos> cd2= new ChoiceDialog<>(lst2.get(0), lst2);
                        cd2.setTitle("Ramo");
                        cd2.setContentText("Options");
                        cd2.setHeaderText("Select an option");
                        cd2.showAndWait().ifPresent(ramo-> {
                            result.ifPresent(id-> {
                                result2.ifPresent(titulo-> {
                                    result3.ifPresent(email-> {
                                        appManager.addProjeto(id, String.valueOf(ramo),titulo,email);
                                    });
                                });
                            });

                        });


                    }
                    case AUTOPROPOSTA -> {
                        //appManager.addAutoProposta(idProposta, titulo)
                        TextInputDialog tid = new TextInputDialog();  // ID proposta
                        tid.setTitle("Adicionar Proposta");
                        tid.setHeaderText("ID: :");
                        tid.setContentText("ID Proposta");
                        Optional<String> result = tid.showAndWait();

                        TextInputDialog tid2 = new TextInputDialog();  // ID proposta
                        tid.setTitle("Adicionar Proposta");
                        tid.setHeaderText("Titulo: :");
                        tid.setContentText("Titulo");
                        Optional<String> result2 = tid.showAndWait();

                        result.ifPresent(id-> {
                            result2.ifPresent(titulo-> {
                                appManager.addAutoProposta(id, titulo);
                            });
                        });


                    }
                    case ESTAGIO -> {
                       // appManager.addEstagio(idProposta,rmao,titulo,idEntidade);
                        TextInputDialog tid = new TextInputDialog();  // ID proposta
                        tid.setTitle("Adicionar Proposta");
                        tid.setHeaderText("ID: :");
                        tid.setContentText("ID Proposta");
                        Optional<String> result = tid.showAndWait();

                        TextInputDialog tid2 = new TextInputDialog();  // ID proposta
                        tid.setTitle("Adicionar Proposta");
                        tid.setHeaderText("Titulo: :");
                        tid.setContentText("Titulo");
                        Optional<String> result2 = tid.showAndWait();

                        TextInputDialog tid3 = new TextInputDialog();  // ID proposta
                        tid.setTitle("Adicionar Proposta");
                        tid.setHeaderText("Id Entidade");
                        tid.setContentText("Id Entidade");
                        Optional<String> result3 = tid.showAndWait();



                        List<Ramos> lst2 = List.of(Ramos.DA, Ramos.RAS, Ramos.SI);
                        ChoiceDialog<Ramos> cd2= new ChoiceDialog<>(lst2.get(0), lst2);
                        cd2.setTitle("Ramo");
                        cd2.setContentText("Options");
                        cd2.setHeaderText("Select an option");
                        cd2.showAndWait().ifPresent(ramo-> {
                            result.ifPresent(id-> {
                                result2.ifPresent(titulo-> {
                                    result3.ifPresent(idEntid-> {
                                        appManager.addEstagio(id, String.valueOf(ramo),titulo,idEntid);
                                    });
                                });
                            });

                        });

                    }
                }

            });



            areaText.clear();
            areaText.setText(appManager.getPropostasString());
        });
    }
    private void update() {
        if (appManager.getState() != AppState.MANAGE_POE_STATE) {
            this.setVisible(false);

            return;
        }
        this.setVisible(true);

    }
}
