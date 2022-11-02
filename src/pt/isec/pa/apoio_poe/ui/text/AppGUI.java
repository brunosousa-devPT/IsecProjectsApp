package pt.isec.pa.apoio_poe.ui.text;
import pt.isec.pa.apoio_poe.model.AppManager;
import pt.isec.pa.apoio_poe.model.data.Alunos.DadosAluno;
import pt.isec.pa.apoio_poe.model.data.DataTypes;
import pt.isec.pa.apoio_poe.model.data.Docentes.DadosDocente;
import pt.isec.pa.apoio_poe.model.data.ManagementTypes;
import  pt.isec.pa.apoio_poe.utils.Input;

import java.util.HashSet;
import java.util.Set;

public class AppGUI {
    AppManager app;

    public AppGUI(AppManager manager) {
        this.app = manager;
    }
    private boolean finish = false;
    public void start() {
        while(!finish) {

            switch (app.getState()) {
                case BEGIN_STATE -> begin_menu();
                case CONFIG_STATE -> config_menu();
                case MANAGE_TEACHERS_STATE -> config_teacher_menu();
                case MANAGE_POE_STATE -> config_PoE_menu();
                case MANAGE_STUDENTS_STATE -> config_students_menu();
                case OFFERS_ATTRIBUTION_STATE -> offers_attribution_menu();
                case ADVISORS_ATTRIBUTION_STATE -> advisors_attribution_menu();
                case QUERY_STATE -> consultation_menu();
                case CANDIDATURE_STATE -> candidature_menu();
                case MANUAL_ATTRIBUTION_OFFER_STATE -> manual_atribution_offers_attribution_menu();
                case MANUAL_ATTRIBUTION_TEACHER_STATE -> advisor_manual_atribution_menu();
                case OFFERS_TIEBREAKER_STATE -> tieBreakerMenu();
            }
        }




    }

    public void begin_menu() {
        switch (Input.chooseOption("Menu principal", "Begin", "Quit")) {
            case 1 -> app.config(); // System.out.println(Texts.configuration_menu());
            case 2 -> System.exit(1);
        }
    }
    public void config_menu() {
        switch (Input.chooseOption("Menu principal", "Import File", "Gestão de Alunos",
                "Gestão de Docentes","Gestão de Propostas","Fechar Fase",
                "Avançar Próxima Fase", "Export File", "Sair","Load","Save")) {
            case 1->importFileConfigMenu();
            case 2 -> app.manageStudent();
            case 3 -> app.manageTeacher();
            case 4 -> app.managePoE();
            case 6 -> app.candidature();
            case 7-> exportFileConfigMenu();
            case 8 -> finish = true;
            case 9 -> app.load();
            case 10 -> app.save();
        }
    }
    public void tieBreakerMenu() {
        app.designateOffer();
    }

    public void config_students_menu() {
        switch (Input.chooseOption("Students Management", "Adicionar Aluno", "Consultar dados Aluno",
                "Editar dados Aluno","Eliminar Aluno","Voltar")) {
            case 1 -> addStudentInput();
            case 2 ->getInfoStudentInput();
            case 3 -> editInfoStudentInput();
            case 4 -> removeStudentInput();
            case 5 -> app.config();
        }

    }
    public void importFileConfigMenu() {
        int option=Input.chooseOption("Para qual deseja importar: ","Alunos","Docentes","Propostas","Candidaturas");
        String filename = Input.readString("Insira o nome do ficheiro: ", false);

        switch (option) {
            case 1 -> app.importFile(ManagementTypes.IMPORT, DataTypes.ALUNO, filename);
            case 2 ->  app.importFile(ManagementTypes.IMPORT, DataTypes.DOCENTES, filename);
            case 3 ->  app.importFile(ManagementTypes.IMPORT, DataTypes.PROPOSTAS, filename);
            case 4 ->  app.importFile(ManagementTypes.IMPORT, DataTypes.CANDIDATURAS, filename);

        }



    }
    public void exportFileConfigMenu() {
        int option=Input.chooseOption("Do que deseja exportar: ","Alunos","Docentes","Propostas","Candidaturas");
        String filename = Input.readString("Insira o nome do ficheiro a criar: ", false);

        switch (option) {
            case 1 -> app.importFile(ManagementTypes.EXPORT,DataTypes.ALUNO ,filename);
            case 2 -> app.importFile(ManagementTypes.EXPORT,DataTypes.DOCENTES ,filename);
            case 3 -> app.importFile(ManagementTypes.EXPORT,DataTypes.PROPOSTAS ,filename);
            case 4 -> app.importFile(ManagementTypes.EXPORT,DataTypes.CANDIDATURAS ,filename);
        }


    }

    public void candidature_menu() {
        switch(Input.chooseOption("Candidatura","Gestão de Candidaturas", "Obter Lista de Alunos",
                "Obter lista de propostas","Fechar Fase", "Voltar","Fase seguinte","Sair")) {
            case 1 -> candidature_config_menu();
            case 2->studentsList_candidature_menu();
           case 3 -> proposalsList_candidature_menu();
            case 5 -> app.config();
            case 6 -> app.designateOffer();
            case 7 -> finish = true;

        }
    }

    public void config_PoE_menu() {
        switch (Input.chooseOption("PoE Management", "Adicionar Proposta", "Consultar Dados de uma Proposta",
                "Editar dados Proposta", "Eliminar Proposta","Voltar")) {
            case 1 -> {
                addProposta_config_menu();
            }
            case 2 -> getData_Propostas_config_menu();

            case 4 -> {
                removeProposal_config_menu() ;
            }
            case 5 -> app.config();

        }


    }
    public void removeProposal_config_menu() {
        String propostaId = Input.readString("Insira o Id da proposta: ",true);
        if (app.removeProposta(propostaId)) {
            System.out.println("Proposta removida com sucesso!");
        }
        else {
            System.out.println("Proposta não removida");
        }
    }
    public void getData_Propostas_config_menu() {
        String propostaId = Input.readString("Insira o Id da proposta: ",true);
        System.out.println(app.getPropostaStr(propostaId));
    }
    public void addProposta_config_menu() {
        switch (Input.chooseOption("Seleciona o tipo de Proposta", "Auto Proposta", "Estagio","Projeto")) {
            case 1 ->{
                long numero = Input.readNumber("Insira o numero do aluno: ");
                String titulo = Input.readString("Insira o titulo da Proposta: ", false);
                String codigo = Input.readString("Insira o codigo da Proposta: ", true);
                app.addAutoProposta(codigo, titulo);
             //   app.e(codigo, numero);
            }
            case 2 ->{
                String titulo = Input.readString("Insira o titulo da Proposta: ", false);
                String codigo = Input.readString("Insira o codigo da Proposta: ", true);
                String entidade = Input.readString("Insira a entidade: ", false);
                String ramo = Input.readString("Insira o ramo: ", true);


                if (app.addEstagio(codigo,ramo,titulo,entidade)) {
                    System.out.println("Proposta adicionada com sucesso!");
                }
                else
                    System.out.println("Proposta não adicionada!");

                switch (Input.chooseOption("Deseja Associar Numero?","Sim","Não")) {
                  //  case 1-> app.setNumeroAluno(codigo, Input.readNumber("Insira o numero do aluno: ")); //TODO PROPOSTAS EDIT
                    case 2-> app.managePoE();
                }
            }
            case 3 -> {
                String titulo = Input.readString("Insira o titulo da Proposta: ", false);
                String codigo = Input.readString("Insira o codigo da Proposta: ", true);
                String docente = Input.readString("Insira o email do docente: ", true);
                String ramo = Input.readString("Insira o ramo: ", true);

                if (app.addProjeto(codigo,ramo,docente,titulo)) {
                    System.out.println("Proposta adicionada com sucesso!");
                }
                else
                    System.out.println("Proposta não adicionada!");

                switch (Input.chooseOption("Deseja Associar Numero?","Sim","Não")) {
                   // case 1-> app.setNumeroAluno(codigo, Input.readNumber("Insira o numero do aluno: "));  // TODO FOLLOW UP PROPOSTAS
                    case 2-> app.managePoE();
                }
            }
        }
        app.managePoE();

    }


    public void config_teacher_menu() {
        switch (Input.chooseOption("Teacher Management", "Adicionar Docente", "Consultar Dados Docente",
                "Editar Nome Docente", "Eliminar Docente","Obter Dados Todos Docentes","Sair")) {
            case 1 -> addTeacherInput();
            case 2 -> getTeacherDataInput();
            case 3 -> editTeacherNameInput();
            case 4 -> removerTeacherInput();
            //case 5 -> getAllDocentes_config_menu();
            case 6 -> app.config();

        }
    }
  /*  public void getAllDocentes_config_menu() {
        System.out.println(app.getAllDocentesInfo());
    }*/
    public void candidature_config_menu() {
        switch (Input.chooseOption("Candidaturas", "Adicionar Candidatura", "Editar Candidatura",
                "Ver Candidatura", "Eliminar Candidatura","Obter todas Candidaturas", "Voltar", "Sair")) {
            case 1 -> addCandidaturaInput(); // System.out.println(Texts.configuration_menu());
            case 2 -> editarCandidaturaInput();
            case 3 -> verCandidatura();
            case 4 -> removeCandidaturaInput();
            case 5 -> verTodasCandidaturas(); // mudar para app.closeState()
            case 6 -> app.designateOffer();
            case 7-> finish = true;

        }
    }
    public void studentsList_candidature_menu() {
        switch (Input.chooseOption("Obter listas de alunos: ", "Alunos com autoproposta",
                "Alunos com candidatura registada", "Alunos sem candidatura","Voltar")) {
            case 1 -> System.out.println(app.getStudentsAutoproposta());
            case 2 ->  System.out.println(app.getStudentsWithCandidature());
            case 3 -> System.out.println(app.getStudentsWithNoCandidature());
            case 4 -> app.candidature();
        }
    }
    public void proposalsList_candidature_menu() {
        switch (Input.chooseOption("Obter listas de Propostas: ", "Lista de Autopropostas",
                "Propostas de Docentes", "Propostas com candidatura", "Propostas sem candidatura", "Voltar")) {
            case 1 -> System.out.println(app.getAutoProposals());
            case 2 ->  System.out.println(app.getProjetos() );
            case 3 -> System.out.println(app.getProposalsWithCandidature());
            case 4 -> System.out.println(app.getProposalsWithNoCandidature());
            case 5 -> app.candidature();
        }
    }


    public void offers_attribution_menu() {
        switch (Input.chooseOption("Offers Attribution",
                "Atribuição Automática"
                , "Atribuição Manual", "Obtenção de listas de alunos","Obtenção de listas de propostas", "Fechar Fase",
                "Fase de candidaturas","Fase de atribuição de docentes","Sair")) {

            case 1 -> System.out.println(app.automaticProposalAttribution());
            case 2 ->app.manualOfferAttribution();

            case 3-> student_list_offers_attribution_menu();
            case 4 -> proposal_list_offers_attribution_menu();
            case 6 ->app.candidature();
            case 7 -> app.advisorAttribution();
            case 8->System.exit(1);

        }

    }
    public void manual_atribution_offers_attribution_menu() {
        switch (Input.chooseOption("Atribuição Manual","Atribuir Proposta","Remover Proposta","Voltar","Undo","Redo")) {
            case 1 -> {
                String proposta = Input.readString("Insira ID da proposta: ", true);
                long numero = Input.readNumber("Insira o numero do estudante: ");
                app.associateStudentWithProposal(numero, proposta); // TODO fazer verificacoes
            }
            case 2-> {
                String proposta = Input.readString("Insira ID da proposta: ", true);
                app.removeNumberProposal(proposta);
            }
            case 3 -> app.designateOffer();
            case 4 -> app.undo();
            case 5-> app.redo();
        }
    }
    public void student_list_offers_attribution_menu() {
        switch(Input.chooseOption("Listas de alunos", "Com autoproposta associada", "Candidatura Registada",
                "Proposta Atribuida","Sem qualquer proposta atribuída")) {
            case 1 ->System.out.println(app.getAP()); // TODO CHANGE THIS ONE
            case 2 ->System.out.println(app.getStudentsWithCandidatureRegistered());
            case 3 -> System.out.println(app.StudentsWithAttributedProposal());
            case 4 -> System.out.println(app.getStudentsNotRegistered());
        }
    }
    public void proposal_list_offers_attribution_menu() {
        switch(Input.chooseOption("Listas de Propostas", "Autoproposta alunos", "Proposta docente",
                "Proposta disponiveis","Propostas atribuidas","Voltar")) {
            case 1 -> System.out.println(app.getAP());
            case 2 -> System.out.println(app.getProjetos());
            case 3 -> System.out.println(app.getFreeProposals());
            case 4-> System.out.println(app.getAttributedProposal());
        }

    }
    public void advisors_attribution_menu() {
        switch (Input.chooseOption("Advisors Attribution", "Associação automática", "Associação manual",
                "Obtenção de dados", "Fechar Fase","Voltar Atrás","Consulta","Sair")) {
            case 1-> System.out.println(app.automaticTeacherAttribution());
            case 2 -> app.manualTeacherAttribution();
            case 3->advisors_get_data_menu();
            case 4, 6 -> app.query();
            case 5 ->app.designateOffer();
            case 7->finish = true;

        } // TODO Automatic attribution

    }
    public void advisor_manual_atribution_menu() {
        switch (Input.chooseOption("Advisors Manual Attribution", "Associar com estudante", "Eliminar orientador",
                "Voltar Atrás", " Undo", "Redo")) {
            case 1 -> {
                String proposalId= Input.readString("Insira o ID da proposta: ", true);
                String email = Input.readString("Insira o seu email: ", true);
                app.associateDocente( email,proposalId);
            }
            case 2 ->{
                String proposalId= Input.readString("Insira o ID da proposta: ", true);
                app.desassociateDocente(proposalId);

            }
            case 3 -> app.advisorAttribution();
            case 4-> app.undo();
            case 5-> app.redo();
        }
    }
    public void advisors_get_data_menu() {
        switch(Input.chooseOption("Obtenção de dados: ","Estudantes com proposta atribuida e orientador associado",
                "Estudantes com proposta atribuida e orientador não associado",
                "Número de orientações por docente","Voltar")) {
            case 1 -> System.out.println(app.getStudentsWProposalOrientator());
            case 2-> System.out.println(app.getStudentsWProposalNoOrientator());
            case 3 -> System.out.println(app.getInfoDocenteInProposals());
            case 4 -> app.advisorAttribution();
        }
    }

    public void consultation_menu() {
        switch (Input.chooseOption("Consultation Menu", "Estudantes com propostas atribuidas",
                "Estudantes sem propostas e com candidatura",
                "Conjunto de propostas disponíveis","Conjunto de propostas atribuídas","Número de orientações por docente",
                "Sair")) {
            case 1 -> System.out.println(app.StudentsWithAttributedProposal());
            case 2 ->System.out.println(app.getAllStudentProposalsNotDesignated()); // TODO fazer - os que tem candidaturas
            case 3 -> System.out.println(app.getFreeProposals());
            case 4-> System.out.println(app.getAttributedProposal());
            case 5-> System.out.println(app.getInfoDocenteInProposals());
            case 6 ->finish = true;
        }
    }

    public void addStudentInput() {
        long numero;
        String nome, email,curso, ramo;
        double classificacao;
        boolean acesso;

        nome = Input.readString("Insira o nome do aluno: ",false);
        numero = Input.readNumber("Insira número do aluno: ");
        email = Input.readString("Insira o email do aluno: ", true);
        curso = Input.readString("Insira o curso do aluno: ", true);
        ramo = Input.readString("Insira o ramo do aluno: ", true);
        classificacao  = Input.readDouble("Insira a classificacao do aluno: ");
        acesso = false;

        if (app.addStudent(numero,nome,email, curso, ramo, classificacao, acesso)) {
            System.out.println("Aluno adicionado com sucesso!");
        }
        else {
            System.out.println("Aluno não adicionado com sucesso!");
        }
        app.config();
    }

    public void removeStudentInput() {
        long numero;
        numero = Input.readNumber("Insira o número do aluno: ");
        app.delStudent(numero);


    }
    public void getInfoStudentInput() {
        long numero;
        numero = Input.readNumber("Insira o numero do aluno: ");
        System.out.println(app.getStudentInfo(numero));
    }
    public void editInfoStudentInput() {
        String newEdit;
        long numero = Input.readNumber("Insira o numero do Aluno: ");

        int choice = Input.chooseOption("Qual deseja editar: ", "Nome","Curso","Ramo","Classificacao","Acesso","Numero","Email");
        switch (choice) {
            case 1 -> app.editAluno(numero, DadosAluno.NOME,Input.readString("Insira o novo nome: ", false));
            case 2 -> app.editAluno(numero,DadosAluno.CURSO,Input.readString ("Insira o novo curso: ", true));
            case 3 -> app.editAluno(numero,DadosAluno.RAMO,Input.readString("Insira o novo ramo: ",true));
            case 4 -> app.editAluno(numero,DadosAluno.CLASSIFICACAO,Input.readString("Insira a classificacao: ", true));
            case 5 -> {
                newEdit = Input.readString("Aluno tem acesso?(S/N)", true);
                if (newEdit.equals("S")) app.editAluno(numero,DadosAluno.ACESSO, newEdit);

            }
            case 6-> {
                app.editAluno(numero, DadosAluno.NUMERO,Input.readString("Insira o novo numero: ", false));
            }
            case 7-> {
                app.editAluno(numero, DadosAluno.EMAIL,Input.readString("Insira o novo email: ", false));

            }
        }
        app.manageStudent();
    }
    public void addTeacherInput() {

        String nome, email;

        nome = Input.readString("Insira o nome do docente: ",false);
        email = Input.readString("Insira o email do docente: ", true);

        if (app.addDocente(nome,email)) {
            System.out.println("Docente adicionado com sucesso!");
        }
        else {
            System.out.println("Docente não adicionado com sucesso!");
        }
        app.manageTeacher();
    }
    public void getTeacherDataInput() {
        String email;
        email = Input.readString("Insira o email do Docente: ", true);
        System.out.println(app.getInfoDocente(email));
        app.manageTeacher();
    }
    public void editTeacherNameInput() { // TODO VERIFY
        String email,newEmail, nome;
        email = Input.readString("Insira o email do Docente: ", true);


        switch (Input.chooseOption("Teacher Edit Data", "Mudar Email", "Mudar Nome",
                "Sair")) {
            case 1-> {
                newEmail = Input.readString("Insira o novo email do Docente: ", false);

                app.editDocente(DadosDocente.EMAIL, email, newEmail);
            }
            case 2 -> {
                nome = Input.readString("Insira o novo nome do Docente: ", false);

                app.editDocente(DadosDocente.NOME, email, nome);
            }
            case 3 -> app.manageTeacher();
            default -> System.out.println("\n Opção Inválida");
        }

        app.manageTeacher();
    }

    public void removerTeacherInput() {
        String email;
        email = Input.readString("Insira o email do docente: ", false);
        if (app.removeDocente(email)) {
            System.out.println("Docente eliminado com sucesso!");
        }
        else {
            System.out.println("Docente não eliminado com sucesso!");
        }
        app.manageTeacher();
    }
    public void addCandidaturaInput() {

        Set<String> proposalsIds = new HashSet<>();
        long numero = Input.readNumber("Insira o numero do estudante: ");
        String input = "";

        while(!input.equals("fim")) {
            input = Input.readString("Insira o ID da proposta(fim para acabar): ",true);
            if (!input.equalsIgnoreCase("fim"))  {

                proposalsIds.add(input);
            }
        }
        app.addCandidatura(numero);
        for (String prop: proposalsIds) {
            app.addProposalId(numero, prop);
        }
        //   System.out.println("Candidatura adicionada com sucesso");

        //}
        //else {
        //  System.out.println("Candidatura não adicionada.");
        // }
    }
    public void removeCandidaturaInput() {
        long numero = Input.readNumber("Insira o numero do estudante: ");
        if (app.removeCandidatura(numero)) {
            System.out.println("Removeu a candidatura com sucesso!");
        }
        else {
            System.out.println("Falha na remoção da candidatura!");
        }

    }
    public void addProposalCandidaturaInput() {

        long numero = Input.readNumber("Insira o numero do estudante: ");
        String proposalId= "";



        while(!proposalId.equalsIgnoreCase("fim")) {
            proposalId = Input.readString("Insira o ID da Proposta a adicionar(fim to finish): ",true);
            if (!proposalId.equalsIgnoreCase("fim")) {
                app.addProposalId(numero, proposalId);
            }
        }

    }
    public void removeProposalCandidaturaInput() {
        long numero = Input.readNumber("Insira o numero do estudante: ");
        String proposalId = Input.readString("Insira o ID da proposta: ", true);
        //app.removeProposalid(numero, proposalId);
        app.candidature();
    }
    public void editarCandidaturaInput() {
        switch (Input.chooseOption("Editar Candidatura", "Adicionar Id Proposta", "Eliminar ID Proposta")) {
            case 1 ->addProposalCandidaturaInput();
            case 2 -> removeProposalCandidaturaInput();
        }
    }
    public void verTodasCandidaturas() {
        //System.out.println(app.getCandidaturas());
    }
    public void verCandidatura() {
        long numero = Input.readNumber("Insira o numero do aluno: ");
      //  System.out.println(app.getInfoCandidatura(numero));
    }

}