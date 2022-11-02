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

import java.io.Serializable;
import java.util.Set;

public class AppStateAdapter implements IAppState, Serializable {

    AppContext context;
    AppData data;

    protected AppStateAdapter(AppContext context, AppData data) {
        this.context = context;
        this.data = data;
    }

    protected void changeState(AppState newState) {
        context.changeState(newState.createState(context,data));
    }

    @Override
    public AppState getState() {
        return null;
    }

    @Override
    public void config() {

    }

    @Override
    public void query() {

    }

    @Override
    public void managePoE() {

    }

    @Override
    public void manageStudent() {

    }

    @Override
    public void manageTeacher() {

    }

    @Override
    public void designateOffer() {

    }

    @Override
    public void adivisorAttribution() {

    }

    @Override
    public void candidature() {

    }

    @Override
    public void begin() {

    }
    @Override
    public void manualOfferAttribution(){}

    @Override
    public void manualTeacherAttribution() {};

    @Override
    public void tieBreaker() {}



    /*        ALUNOS        */
    @Override
    public String getStudentInfo(long numero) {
        return "";
    }
    @Override
    public boolean addStudent(long numero, String nome, String email, String curso, String ramo, double classificacao, boolean acesso){
        return false;
    }
    @Override
    public boolean delStudent(long numero) {
        return false;
    }
    @Override
    public boolean editAluno(long numero, DadosAluno tipo, String newData) {
        return false;
    }
    @Override
    public Set<Aluno> getAlunos() {
        return data.getAlunos();
    }

    /*     FIM ALUNOS       */



    /*    CANDIDATURAS      */
    @Override
    public String getCandid (long numero) {
        return "";
    }
    @Override
    public Set<Candidatura> getCandidaturas() {
        return data.getCandidaturas();
    }

    @Override
    public boolean addCandidatura(long studentNumber) {
        return false;
    }

    @Override
    public boolean removeCandidatura(long studentNumber) {
        return false;
    }
    @Override
    public boolean addProposalId(long studentNumber, String proposalId) {
        return false;
    }
    @Override
    public boolean removeProposalId(long studentNumber, String proposalId) {
        return false;
    }
    /*  FIM CANDIDATURAS    */
    /*       DOCENTES       */
    @Override
    public Set<Docente> getDocentes() {return data.getDocentes();}
    @Override
    public boolean addDocente(String email, String nome) {
        return false;
    }
    @Override
    public boolean removeDocente(String email) {
        return false;
    }
    @Override
    public boolean editDocente(DadosDocente tipo, String newData, String email) {
        return false;
    }
    @Override
    public String getInfoDocente(String email) {
        return "";
    }

    /*     FIM DOCENTES     */



    /*       PROPOSTAS      */
    @Override
    public String getPropostaStr(String ID) {
        return "";
    }
    @Override
    public Set<Proposta> getPropostasSet() {return data.getPropostasSet();}
    @Override
    public String associateStudentWithProposal(long numero, String proposalID) {

       return "";
    }

    @Override
    public boolean addAutoProposta(String idProposta, String titulo) {
        return false;
    }
    @Override
    public boolean addProjeto(String idProposta, String ramo, String titulo, String email) {
        return false;
    }

    @Override
    public boolean addEstagio(String idProposta, String ramo, String titulo, String idEntidade) {
        return false;
    }
    @Override
    public boolean removeProposta(String idProposta) {
        return false;
    }
    @Override
    public boolean setOrientador(String idProposta, String email) {
        return false;
    }
    @Override
    public String automaticTeacherAttribution() {
        return "";
    }

    @Override
    public String removeNumberProposal(String proposalID) {
        return "";
    }

    @Override
    public String associateDocente(String email, String proposalID) {
        return "";
    }

    @Override
    public String desassociateDocente(String proposalID) { return "";}


    /*     FIM PROPOSTAS    */



    /*       CONSULTA DE DADOS      */
    @Override
    public String getInfoDocenteInProposals() {
        return "";
    }

    @Override
    public String getStudentsWProposalOrientator() {
        return "";
    }

    @Override
    public String getStudentsWProposalNoOrientator() {
        return "";
    }
    @Override
    public String getStudentsAutoproposta() {
        return data.getStudentsAutoproposta();
    }

    public String getStudentsRegistered() { // Obter estudantes com registro feito
        return data.getStudentsRegistered();
    }
    @Override
    public String getStudentsWithNoCandidature() { // Obter estudantes sem candidatura registada
        return data.getStudentsWithNoCandidature();
    }
    @Override
    public String getStudentsWithCandidature() {    // Obter estudantes com candidatura registada
        return data.getStudentsWithCandidature();
    }

    @Override
    public String getAutoProposals() {
        return data.getAutoProposals();
    }
    @Override
    public String getProjetos() {

        return data.getProjetos();
    }
    @Override
    public String getProposalsWithCandidature() {
        return data.getProposalsWithCandidature();
    }
    @Override
    public String getProposalsWithNoCandidature() {
        return data.getProposalsWithNoCandidature();
    }
    @Override
    public String getAP() { // Get autoprpopostas (info estudante)
        return data.getAP();
    }
    @Override
    public String getStudentsWithCandidatureRegistered() {

        return data.getStudentsWithCandidatureRegistered();
    }
    @Override
    public String StudentsWithAttributedProposal() {
        return data.StudentsWithAttributedProposal();
    }

    @Override
    public String getStudentsNotRegistered() {
        return data.getStudentsNotRegistered();

    }
    @Override
    public String getFreeProposals() {

        return data.getFreeProposals();
    }

    public String getAttributedProposal() {
        return data.getAttributedProposal();
    }

    @Override
    public String getAllStudentProposalsNotDesignated() {

        return data.getAllStudentProposalsNotDesignated();
    }

    /*      FIM CONSULTA DE DADOS   */



    /*          FILES               */
    @Override
    public String importFile(ManagementTypes tipo, DataTypes dataType, String filename) {


        FileManagement file = new FileManagement();


        return file.importFile(tipo,dataType,filename, this.data);
    }


    /*        END OF FILES          */

    /* AUTOPROPOSTA */
    @Override
    public String automaticProposalAttribution() {
        return "";
    }
    /*FIM AUTOPROPOSTA*/




}


