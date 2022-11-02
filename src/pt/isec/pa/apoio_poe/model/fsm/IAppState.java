package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Alunos.Aluno;
import pt.isec.pa.apoio_poe.model.data.Alunos.DadosAluno;
import pt.isec.pa.apoio_poe.model.data.Candidaturas.Candidatura;
import pt.isec.pa.apoio_poe.model.data.DataTypes;
import pt.isec.pa.apoio_poe.model.data.Docentes.DadosDocente;
import pt.isec.pa.apoio_poe.model.data.Docentes.Docente;
import pt.isec.pa.apoio_poe.model.data.FileManagement;
import pt.isec.pa.apoio_poe.model.data.ManagementTypes;
import pt.isec.pa.apoio_poe.model.data.Propostas.Proposta;

import java.util.Set;

public interface IAppState {
    AppState getState();



    void config(); // configuration menu (like a start function but to the management menu)
    void query();
    void managePoE();
    void manageStudent();
    void manageTeacher();
    void designateOffer(); // Attribution of an offer
    void adivisorAttribution(); // Menu "Advisors Attributions"
    void candidature();
    void begin();
    void manualOfferAttribution();
    void manualTeacherAttribution();
    void tieBreaker();


    /*        ALUNOS        */
    String getStudentInfo(long numero);
    boolean addStudent(long numero, String nome, String email, String curso, String ramo, double classificacao, boolean acesso);



    boolean delStudent(long numero);

    boolean editAluno(long numero, DadosAluno tipo, String newData);
    Set<Aluno> getAlunos();


    /*     FIM ALUNOS       */



    /*    CANDIDATURAS      */
    String getCandid (long numero);
    Set<Candidatura> getCandidaturas();


    boolean addCandidatura(long studentNumber);


    boolean removeCandidatura(long studentNumber);

    boolean addProposalId(long studentNumber, String proposalId);
    boolean removeProposalId(long studentNumber, String proposalId);
    /*  FIM CANDIDATURAS    */

    /*       DOCENTES       */

    Set<Docente> getDocentes();

    boolean addDocente(String email, String nome);

    boolean removeDocente(String email);
    boolean editDocente(DadosDocente tipo, String newData, String email);

    String getInfoDocente(String email);

    /*     FIM DOCENTES     */



    /*       PROPOSTAS      */
    String getPropostaStr(String ID);
    Set<Proposta> getPropostasSet();

    String associateStudentWithProposal(long numero, String proposalID);

    boolean addAutoProposta(String idProposta, String titulo);

    boolean addProjeto(String idProposta, String ramo, String titulo, String email);

    boolean addEstagio(String idProposta, String ramo, String titulo, String idEntidade);

    boolean removeProposta(String idProposta);

    boolean setOrientador(String idProposta, String email);

    String automaticTeacherAttribution();

    String removeNumberProposal(String proposalID);

    String associateDocente(String email, String proposalID);


    String desassociateDocente(String proposalID);


    /*     FIM PROPOSTAS    */



    /*       CONSULTA DE DADOS      */
    String getInfoDocenteInProposals();

    String getStudentsWProposalOrientator();

    String getStudentsWProposalNoOrientator();

    String getStudentsAutoproposta();

    String getStudentsRegistered();

    String getStudentsWithNoCandidature();
    String getStudentsWithCandidature();


    String getAutoProposals();
    String getProjetos();

    String getProposalsWithCandidature();

    String getProposalsWithNoCandidature();

    String getAP();
    String getStudentsWithCandidatureRegistered();

    String StudentsWithAttributedProposal();

    String getStudentsNotRegistered();

    String getFreeProposals();

    String getAttributedProposal();
    String getAllStudentProposalsNotDesignated();

    /*      FIM CONSULTA DE DADOS   */



    /*          FILES               */

    String importFile(ManagementTypes tipo, DataTypes dataType, String filename);


    /*        END OF FILES          */

    /* AUTOPROPOSTA */
    String automaticProposalAttribution();
    /*FIM AUTOPROPOSTA*/



}
