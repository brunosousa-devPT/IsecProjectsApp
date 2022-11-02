package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Alunos.Aluno;
import pt.isec.pa.apoio_poe.model.data.Alunos.DadosAluno;
import pt.isec.pa.apoio_poe.model.data.AppData;
import pt.isec.pa.apoio_poe.model.data.Candidaturas.Candidatura;
import pt.isec.pa.apoio_poe.model.data.DataTypes;
import pt.isec.pa.apoio_poe.model.data.Docentes.DadosDocente;
import pt.isec.pa.apoio_poe.model.data.Docentes.Docente;
import pt.isec.pa.apoio_poe.model.data.FileManagement;
import pt.isec.pa.apoio_poe.model.data.ManagementTypes;
import pt.isec.pa.apoio_poe.model.data.Propostas.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.states.*;

import java.io.Serializable;
import java.util.Set;


public class AppContext implements Serializable{
    IAppState state;
    AppData data;

    public AppContext() {
        data = new AppData();
        state = new BeginState(this, data);
    }
    /**
     * Modifica o estado atual da aplicação.
     *
     * Esta função não retorna nada.
     * @see IAppState
     * @see AppState
     * */
    public void changeState(IAppState newState) {
        this.state = newState;
    }

    /**
     * Obtem o estado atual da aplicação
     *
     * @return AppState O estado atual da aplicação
     *  @see IAppState
     *  @see AppState
     * */
    public AppState getState() {
        if (this.state == null) {
            return null;
        }
        return state.getState();
    }

    /*      FUNÇOES ESTADO      */
    public void config() {
        state.config();
    }
    public void query() {
        state.query();
    }
    public void managePoE() {
        state.managePoE();
    }
    public void manageStudent() {
        state.manageStudent();
    }
    public void manageTeacher() {
        state.manageTeacher();
    }
    public void designateOffer() {
        state.designateOffer();
    }
    public void adivisorAttribution() {
        state.adivisorAttribution();
    }
    public void candidature() {
        state.candidature();
    }
    public void begin() {state.begin();}
    public void manualOfferAttribution(){state.manualOfferAttribution();}
    public void manualTeacherAttribution() {state.manualTeacherAttribution();};
    public void tieBreaker() {state.tieBreaker();}
    /*       FIM FUNÇOES ESTADO     */



    /*        ALUNOS        */
    public String getStudentInfo(long numero) {
        return state.getStudentInfo(numero);
    }
    public boolean addStudent(long numero, String nome, String email, String curso, String ramo, double classificacao, boolean acesso){
        return state.addStudent(numero, nome, email, curso,ramo,classificacao, acesso);
    }

    public boolean delStudent(long numero) {
        return state.delStudent(numero);
    }

    public boolean editAluno(long numero, DadosAluno tipo, String newData) {
        return state.editAluno(numero, tipo, newData);
    }
    public Set<Aluno> getAlunos() {
        return state.getAlunos();
    }

    /*     FIM ALUNOS       */



    /*    CANDIDATURAS      */
    public String getCandid (long numero) {
        return state.getCandid(numero);
    }
    public Set<Candidatura> getCandidaturas() {
        return state.getCandidaturas();
    }


    public boolean addCandidatura(long studentNumber) {
        return state.addCandidatura(studentNumber);
    }


    public boolean removeCandidatura(long studentNumber) {
        return state.removeCandidatura(studentNumber);
    }
    public boolean addProposalId(long studentNumber, String proposalId) {
        return state.addProposalId(studentNumber, proposalId);
    }
    public boolean removeProposalId(long studentNumber, String proposalId) {
        return state.removeProposalId(studentNumber, proposalId);
    }
    /*  FIM CANDIDATURAS    */
    /*       DOCENTES       */

    public Set<Docente> getDocentes() {return state.getDocentes();}

    public boolean addDocente(String email, String nome) {
        return state.addDocente(email,nome);
    }

    public boolean removeDocente(String email) {
        return state.removeDocente(email);
    }

    public boolean editDocente(DadosDocente tipo, String newData, String email) {
        return state.editDocente(tipo, newData, email);
    }

    public String getInfoDocente(String email) {
        return state.getInfoDocente(email);
    }

    /*     FIM DOCENTES     */



    /*       PROPOSTAS      */
    public String getPropostaStr(String ID) {
        return state.getPropostaStr(ID);
    }
    public Set<Proposta> getPropostasSet() {return state.getPropostasSet();}

    public String associateStudentWithProposal(long numero, String proposalID) {

        return state.associateStudentWithProposal(numero, proposalID);
    }

    public boolean addAutoProposta(String idProposta, String titulo) {
        return state.addAutoProposta(idProposta,titulo);
    }
    public boolean addProjeto(String idProposta, String ramo, String titulo, String email) {
        return state.addProjeto(idProposta, ramo, titulo, email);
    }
    public boolean addEstagio(String idProposta, String ramo, String titulo, String idEntidade) {
        return state.addEstagio(idProposta, ramo, titulo, idEntidade);
    }

    public boolean removeProposta(String idProposta) {
        return state.removeProposta(idProposta);
    }

    public boolean setOrientador(String idProposta, String email) {
        return state.setOrientador(idProposta, email);
    }
    public String automaticTeacherAttribution() {
        return state.automaticTeacherAttribution();
    }
    public String removeNumberProposal(String proposalID) {
        return state.removeNumberProposal(proposalID);
    }
    public String associateDocente(String email, String proposalID) {
        return state.associateDocente(email, proposalID);
    }
    public String desassociateDocente(String proposalID) { return state.desassociateDocente(proposalID);}


    /*     FIM PROPOSTAS    */



    /*       CONSULTA DE DADOS      */
    public String getInfoDocenteInProposals() {
        return state.getInfoDocenteInProposals();
    }
    public String getStudentsWProposalOrientator() {
        return state.getStudentsWProposalOrientator();
    }
    public String getStudentsWProposalNoOrientator() {
        return state.getStudentsWProposalNoOrientator();
    }
    public String getStudentsAutoproposta() {
        return state.getStudentsAutoproposta();
    }

    public String getStudentsRegistered() { // Obter estudantes com registro feito
        return state.getStudentsRegistered();
    }
    public String getStudentsWithNoCandidature() { // Obter estudantes sem candidatura registada
        return state.getStudentsWithNoCandidature();
    }
    public String getStudentsWithCandidature() {    // Obter estudantes com candidatura registada
        return state.getStudentsWithCandidature();
    }


    public String getAutoProposals() {
        return state.getAutoProposals();
    }
    public String getProjetos() {

        return state.getProjetos();
    }

    public String getProposalsWithCandidature() {
        return state.getProposalsWithCandidature();
    }
    public String getProposalsWithNoCandidature() {
        return state.getProposalsWithNoCandidature();
    }
    public String getAP() { // Get autoprpopostas (info estudante)
        return state.getAP();
    }
    public String getStudentsWithCandidatureRegistered() {

        return state.getStudentsWithCandidatureRegistered();
    }

    public String StudentsWithAttributedProposal() {
        return state.StudentsWithAttributedProposal();
    }

    public String getStudentsNotRegistered() {
        return state.getStudentsNotRegistered();

    }
    public String getFreeProposals() {

        return state.getFreeProposals();
    }

    public String getAttributedProposal() {
        return state.getAttributedProposal();
    }

    public String getAllStudentProposalsNotDesignated() {

        return state.getAllStudentProposalsNotDesignated();
    }

    /*      FIM CONSULTA DE DADOS   */



    /*          FILES               */
    public String importFile(ManagementTypes tipo, DataTypes dataType, String filename) {

        return state.importFile(tipo, dataType, filename);

    }


    /*        END OF FILES          */
    /* AUTOPROPOSTA */
    public String automaticProposalAttribution() {
        return state.automaticProposalAttribution();
    }
    /*FIM AUTOPROPOSTA*/

}
