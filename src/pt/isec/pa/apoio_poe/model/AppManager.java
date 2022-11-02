package pt.isec.pa.apoio_poe.model;

import pt.isec.pa.apoio_poe.model.data.Alunos.Aluno;
import pt.isec.pa.apoio_poe.model.data.Alunos.DadosAluno;
import pt.isec.pa.apoio_poe.model.data.Candidaturas.Candidatura;
import pt.isec.pa.apoio_poe.model.data.DataTypes;
import pt.isec.pa.apoio_poe.model.data.Docentes.DadosDocente;
import pt.isec.pa.apoio_poe.model.data.Docentes.Docente;
import pt.isec.pa.apoio_poe.model.data.ManagementTypes;
import pt.isec.pa.apoio_poe.model.data.Propostas.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.Consumer;

public class AppManager {
    /* UNDO */
    ArrayList<ManualCommands> commands;
    ArrayList<ArrayList<String>> dataStudents;  // numero e proposta associada
    ArrayList<ManualCommands> commandsDocentes;
    ArrayList<ArrayList<String>> dataDocentes;
    /* REDO */
    ArrayList<ManualCommands> commandsRedo;
    ArrayList<ArrayList<String>> dataStudentsRedo;  // numero e proposta associada
    ArrayList<ManualCommands> commandsDocentesRedo;
    ArrayList<ArrayList<String>> dataDocentesRedo;

    private AppContext fsm;
    PropertyChangeSupport pcs;
    static final String FILENAME = "data.dat";

    /*      ESTADOS         */
    public AppManager() {
        commands = new ArrayList<>();
        dataStudents = new ArrayList<>();
        dataDocentes = new ArrayList<>();
        commandsDocentes = new ArrayList<>();
        commandsRedo = new ArrayList<>();
        dataStudentsRedo = new ArrayList<>();
        dataDocentesRedo = new ArrayList<>();
        commandsDocentesRedo = new ArrayList<>();
        fsm = new AppContext();
        pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public AppState getState() {
        return fsm.getState();
    }
    public void config() {
        fsm.config();
        pcs.firePropertyChange(null,null,null);

    }
    public void query() {
        fsm.query();
        pcs.firePropertyChange(null,null,null);

    }
    public void managePoE() {
        fsm.managePoE();
        pcs.firePropertyChange(null,null,null);

    }
    public void manageStudent() {
        fsm.manageStudent();
        pcs.firePropertyChange(null,null,null);

    }
    public void manageTeacher() {
        fsm.manageTeacher();
        pcs.firePropertyChange(null,null,null);

    }
    public void designateOffer() {
        fsm.designateOffer();
        pcs.firePropertyChange(null,null,null);

    }
    public void advisorAttribution() {
        fsm.adivisorAttribution();
        pcs.firePropertyChange(null,null,null);

    }
    public void candidature() {
        fsm.candidature();
        pcs.firePropertyChange(null,null,null);

    }
    public void begin() {
        fsm.begin();
        pcs.firePropertyChange(null,null,null);

    }
    public void manualOfferAttribution(){
        fsm.manualOfferAttribution();
        pcs.firePropertyChange(null,null,null);
    }
    public void manualTeacherAttribution() {
        fsm.manualTeacherAttribution();
        pcs.firePropertyChange(null,null,null);
    }
    public void tieBreaker() {
        fsm.tieBreaker();
        pcs.firePropertyChange(null,null,null);
    }

    /*      FIM ESTADOS     */


    /*        ALUNOS        */
    public String getAlunosString() {
        StringBuilder sb = new StringBuilder();
        for(Aluno a: getAlunos()) {
            sb.append(a).append("\n");
        }

        return sb.toString();
    }
    public Set<Aluno> getAlunos() {
        return fsm.getAlunos();
    }
    public String getStudentInfo(long numero) {
        return fsm.getStudentInfo(numero);
    }
    public boolean addStudent(long numero, String nome, String email, String curso, String ramo, double classificacao, boolean acesso){
        return fsm.addStudent(numero, nome, email, curso,ramo,classificacao, acesso);
    }

    public void delStudent(long numero) {

         fsm.delStudent(numero);
    }

    public boolean editAluno(long numero, DadosAluno tipo, String newData) {
        return fsm.editAluno(numero, tipo, newData);
    }


    /*     FIM ALUNOS       */



    /*    CANDIDATURAS      */
    public Set<Candidatura> getCandidaturas() {
        return fsm.getCandidaturas();
    }
    public String getCandidaturasString() {
        StringBuilder sb = new StringBuilder();
        for(Candidatura a: getCandidaturas()) {
            sb.append(a).append("\n");
        }

        return sb.toString();
    }
    public String getCandid (long numero) {
        return fsm.getCandid(numero);
    }
    public boolean addCandidatura(long studentNumber) {
        return fsm.addCandidatura(studentNumber);
    }
    /*  FIM CANDIDATURAS    */

    public boolean removeCandidatura(long studentNumber) {
        return fsm.removeCandidatura(studentNumber);
    }
    public boolean addProposalId(long studentNumber, String proposalId) {
        return fsm.addProposalId(studentNumber, proposalId);
    }
    public boolean removeProposalId(long studentNumber, String proposalId) {
        return fsm.removeProposalId(studentNumber, proposalId);
    }

    /*       DOCENTES       */
    public Set<Docente> getDocentes() {return fsm.getDocentes();}

    public String getDocentesString() {
        StringBuilder sb = new StringBuilder();
        for(Docente a: getDocentes()) {
            sb.append(a).append("\n");
        }

        return sb.toString();
    }
    public boolean addDocente(String email, String nome) {
        return fsm.addDocente(email,nome);
    }

    public boolean removeDocente(String email) {
        return fsm.removeDocente(email);
    }

    public boolean editDocente(DadosDocente tipo, String newData, String email) {
        return fsm.editDocente(tipo, newData, email);
    }

    public String getInfoDocente(String email) {
        return fsm.getInfoDocente(email);
    }

    /*     FIM DOCENTES     */



    /*       PROPOSTAS      */
    public String getPropostaStr(String ID) {
        return fsm.getPropostaStr(ID);
    }
    public String  getPropostasString() {
        StringBuilder sb = new StringBuilder();
        for(Proposta a: fsm.getPropostasSet()) {
            sb.append(a).append("\n");
        }

        return sb.toString();
    }
    public Set<Proposta> getPropostasSet() {return fsm.getPropostasSet();}

    public String desassociateDocente(String proposalID) {
        dataDocentes.add(new ArrayList<>());
        dataDocentes.get(dataDocentes.size()-1).add(proposalID);
        commandsDocentes.add(ManualCommands.DESASSOCIATE);
        return fsm.desassociateDocente(proposalID);}

    public String associateDocente(String email, String proposalID) {
        dataDocentes.add(new ArrayList<>());
        dataDocentes.get(dataDocentes.size()-1).add(proposalID);
        dataDocentes.get(dataDocentes.size()-1).add(email);
        commandsDocentes.add(ManualCommands.ASSOCIATE);
        return fsm.associateDocente(email, proposalID);
    }
    public String removeNumberProposal(String proposalID) {
        dataStudents.add(new ArrayList<>());
        dataStudents.get(dataStudents.size()-1).add(proposalID);
        commands.add(ManualCommands.DESASSOCIATE);

        return fsm.removeNumberProposal(proposalID);
    }
    public String associateStudentWithProposal(long numero, String proposalID) {

        dataStudents.add(new ArrayList<>());
        dataStudents.get(dataStudents.size()-1).add(proposalID);
        dataStudents.get(dataStudents.size()-1).add(""+numero);

        commands.add(ManualCommands.ASSOCIATE);
        return fsm.associateStudentWithProposal(numero, proposalID);
    }
    public boolean addAutoProposta(String idProposta, String titulo) {
        return fsm.addAutoProposta(idProposta,titulo);
    }
    public boolean addProjeto(String idProposta, String ramo, String titulo, String email) {
        return fsm.addProjeto(idProposta, ramo, titulo, email);
    }
    public boolean addEstagio(String idProposta, String ramo, String titulo, String idEntidade) {
        return fsm.addEstagio(idProposta, ramo, titulo, idEntidade);
    }

    public boolean removeProposta(String idProposta) {
        return fsm.removeProposta(idProposta);
    }

    public boolean setOrientador(String idProposta, String email) {
        return fsm.setOrientador(idProposta, email);
    }
    public String automaticTeacherAttribution() {
        return fsm.automaticTeacherAttribution();
    }
    /*     FIM PROPOSTAS    */


    /*          FILES               */

    public String importFile(ManagementTypes tipo, DataTypes dataType, String filename) {

        return fsm.importFile(tipo,dataType,filename);
    }



    /*        END OF FILES          */

    /*       CONSULTA DE DADOS      */
    public String getInfoDocenteInProposals() {
        return fsm.getInfoDocenteInProposals();
    }
    public String getStudentsWProposalOrientator() {
        return fsm.getStudentsWProposalOrientator();
    }
    public String getStudentsWProposalNoOrientator() {
        return fsm.getStudentsWProposalNoOrientator();
    }

    public String getStudentsAutoproposta() {
        return fsm.getStudentsAutoproposta();
    }

    public String getStudentsRegistered() { // Obter estudantes com registro feito
        return fsm.getStudentsRegistered();
    }

    public String getStudentsWithNoCandidature() { // Obter estudantes sem candidatura registada
        return fsm.getStudentsWithNoCandidature();
    }
    public String getStudentsWithCandidature() {    // Obter estudantes com candidatura registada
        return fsm.getStudentsWithCandidature();
    }


    public String getAutoProposals() {
        return fsm.getAutoProposals();
    }
    public String getProjetos() {

        return fsm.getProjetos();
    }
    public String getProposalsWithCandidature() {
        return fsm.getProposalsWithCandidature();
    }
    public String getProposalsWithNoCandidature() {
        return fsm.getProposalsWithNoCandidature();
    }

    public String getAP() { // Get autoprpopostas (info estudante)
        return fsm.getAP();
    }
    public String getStudentsWithCandidatureRegistered() {

        return fsm.getStudentsWithCandidatureRegistered();
    }

    public String StudentsWithAttributedProposal() {
        return fsm.StudentsWithAttributedProposal();
    }

    public String getStudentsNotRegistered() {
        return fsm.getStudentsNotRegistered();

    }
    public String getFreeProposals() {

        return fsm.getFreeProposals();
    }

    public String getAttributedProposal() {
        return fsm.getAttributedProposal();
    }

    public String getAllStudentProposalsNotDesignated() {

        return fsm.getAllStudentProposalsNotDesignated();
    }

    /*      FIM CONSULTA DE DADOS   */

    public String automaticProposalAttribution() {
        return fsm.automaticProposalAttribution();
    }





    /*FIM AUTOPROPOSTA*/

    /*   SAVE AND LOAD   */

    public boolean save() {
        //serializar -> ObjectOutputStream (WriteObject)
        //des... -> ObjectInputStream
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))){
            oos.writeObject(fsm);
            oos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error savid data" + e);
            return false;
        }
        return true;
    }
    public boolean load()  {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            fsm = (AppContext) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }  // TODO Erros

    public void undo() {
        switch(fsm.getState()) {
            case MANUAL_ATTRIBUTION_OFFER_STATE -> {
                switch(commands.get(commands.size()-1)) {
                    case ASSOCIATE -> {
                        fsm.removeNumberProposal(dataStudents.get(dataStudents.size()-1).get(0));
                        dataStudentsRedo.add(new ArrayList<>());
                        dataStudentsRedo.get(dataStudentsRedo.size()-1).add(dataStudents.get(dataStudents.size()-1).get(0));
                        dataStudentsRedo.get(dataStudentsRedo.size()-1).add(dataStudents.get(dataStudents.size()-1).get(1));
                        commandsRedo.add(ManualCommands.ASSOCIATE);
                        dataStudents.remove(dataStudents.size()-1);
                        commands.remove(commands.size()-1);
                    }
                    case DESASSOCIATE -> {
                        fsm.associateStudentWithProposal(Long.parseLong(dataStudents.get(dataStudents.size()-1).get(1)), dataStudents.get(dataStudents.size()-1).get(0));
                        dataStudentsRedo.add(new ArrayList<>());
                        dataStudentsRedo.get(dataStudentsRedo.size()-1).add(dataStudents.get(dataStudents.size()-1).get(0));
                        dataStudentsRedo.get(dataStudentsRedo.size()-1).add(dataStudents.get(dataStudents.size()-1).get(1));
                        commandsRedo.add(ManualCommands.DESASSOCIATE);
                        dataStudents.remove(dataStudents.size()-1);
                        commands.remove(commands.size()-1);
                    }
                }
            }
            case MANUAL_ATTRIBUTION_TEACHER_STATE -> {
                switch(commandsDocentes.get(commandsDocentes.size()-1)) {
                    case ASSOCIATE -> {

                        fsm.desassociateDocente(dataDocentes.get(dataDocentes.size()-1).get(0));
                        dataDocentesRedo.add(new ArrayList<>());
                        dataDocentesRedo.get(dataDocentesRedo.size()-1).add(dataDocentes.get(dataDocentes.size()-1).get(0));
                        dataDocentesRedo.get(dataDocentesRedo.size()-1).add(dataDocentes.get(dataDocentes.size()-1).get(1));
                        commandsDocentesRedo.add(ManualCommands.ASSOCIATE);
                        dataDocentes.remove(dataDocentes.size()-1);
                        commandsDocentes.remove(commandsDocentes.size()-1);
                    }
                    case DESASSOCIATE -> {
                        fsm.associateDocente(dataDocentes.get(dataDocentes.size()-1).get(1),dataDocentes.get(dataDocentes.size()-1).get(0));
                        dataDocentesRedo.add(new ArrayList<>());
                        dataDocentesRedo.get(dataDocentesRedo.size()-1).add(dataDocentes.get(dataDocentes.size()-1).get(0));
                        dataDocentesRedo.get(dataDocentesRedo.size()-1).add(dataDocentes.get(dataDocentes.size()-1).get(1));
                        commandsDocentesRedo.add(ManualCommands.DESASSOCIATE);
                        dataDocentes.remove(dataDocentes.size()-1);
                        commandsDocentes.remove(commandsDocentes.size()-1);
                    }
                };
            }
        }

    }

    public void redo() {
        switch(fsm.getState()) {
            case MANUAL_ATTRIBUTION_OFFER_STATE -> {
                if (commandsRedo.size() == 0) return;
                switch(commandsRedo.get(commandsRedo.size()-1)) {
                    case ASSOCIATE -> {
                        fsm.associateStudentWithProposal(Long.parseLong(dataStudentsRedo.get(dataStudentsRedo.size()-1).get(1)), dataStudentsRedo.get(dataStudentsRedo.size()-1).get(0));
                        dataStudents.add(new ArrayList<>());
                        dataStudents.get(dataStudents.size()-1).add(dataStudentsRedo.get(dataStudentsRedo.size()-1).get(0));
                        dataStudents.get(dataStudents.size()-1).add(dataStudentsRedo.get(dataStudentsRedo.size()-1).get(1));
                        commands.add(ManualCommands.DESASSOCIATE);
                        dataStudentsRedo.remove(dataStudentsRedo.size()-1);
                        commandsRedo.remove(commandsRedo.size()-1);
                    }
                    case DESASSOCIATE -> {
                        fsm.removeNumberProposal(dataStudentsRedo.get(dataStudentsRedo.size()-1).get(0));
                        dataStudents.add(new ArrayList<>());
                        dataStudents.get(dataStudents.size()-1).add(dataStudentsRedo.get(dataStudentsRedo.size()-1).get(0));
                        dataStudents.get(dataStudents.size()-1).add(dataStudentsRedo.get(dataStudentsRedo.size()-1).get(1));
                        commands.add(ManualCommands.ASSOCIATE);
                        dataStudents.remove(dataStudents.size()-1);
                        commandsRedo.remove(commandsRedo.size()-1);
                    }
                }
            }
            case MANUAL_ATTRIBUTION_TEACHER_STATE -> {
                if (commandsDocentesRedo.size() == 0) return;
                switch(commandsDocentesRedo.get(commandsDocentesRedo.size()-1)) {
                    case ASSOCIATE -> {
                        fsm.associateDocente(dataDocentesRedo.get(dataDocentesRedo.size()-1).get(1),dataDocentesRedo.get(dataDocentesRedo.size()-1).get(0));
                        dataDocentes.add(new ArrayList<>());
                        dataDocentes.get(dataDocentes.size()-1).add(dataDocentesRedo.get(dataDocentesRedo.size()-1).get(0));
                        dataDocentes.get(dataDocentes.size()-1).add(dataDocentesRedo.get(dataDocentesRedo.size()-1).get(1));
                        commandsDocentes.add(ManualCommands.DESASSOCIATE);
                        dataDocentesRedo.remove(dataDocentesRedo.size()-1);
                        commandsDocentesRedo.remove(commandsDocentesRedo.size()-1);
                    }
                    case DESASSOCIATE -> {
                        fsm.desassociateDocente(dataDocentesRedo.get(dataDocentesRedo.size()-1).get(0));
                        dataDocentes.add(new ArrayList<>());
                        dataDocentes.get(dataDocentes.size()-1).add(dataDocentesRedo.get(dataDocentesRedo.size()-1).get(0));
                        dataDocentes.get(dataDocentes.size()-1).add(dataDocentesRedo.get(dataDocentesRedo.size()-1).get(1));
                        commandsDocentes.add(ManualCommands.ASSOCIATE);
                        dataDocentesRedo.remove(dataDocentesRedo.size()-1);
                        commandsDocentesRedo.remove(commandsDocentesRedo.size()-1);
                    }
                }
            }
        }

    }

    /* END SAVE AND LOAD */

}
