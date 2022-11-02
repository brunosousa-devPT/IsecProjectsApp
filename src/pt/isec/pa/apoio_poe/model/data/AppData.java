package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.model.data.Alunos.Aluno;
import pt.isec.pa.apoio_poe.model.data.Alunos.BaseDadosAlunos;
import pt.isec.pa.apoio_poe.model.data.Alunos.DadosAluno;
import pt.isec.pa.apoio_poe.model.data.Candidaturas.BaseDadosCandidatura;
import pt.isec.pa.apoio_poe.model.data.Candidaturas.Candidatura;
import pt.isec.pa.apoio_poe.model.data.Docentes.BaseDadosDocentes;
import pt.isec.pa.apoio_poe.model.data.Docentes.DadosDocente;
import pt.isec.pa.apoio_poe.model.data.Docentes.Docente;
import pt.isec.pa.apoio_poe.model.data.Propostas.BaseDadosPropostas;
import pt.isec.pa.apoio_poe.model.data.Propostas.Proposta;

import java.io.Serializable;

import java.util.Objects;
import java.util.Set;

public class AppData implements Serializable{
    private BaseDadosAlunos alunos;
    private BaseDadosDocentes docentes;
    private BaseDadosPropostas propostas;
    private BaseDadosCandidatura candidaturas;

    public AppData() {
        alunos = new BaseDadosAlunos();
        docentes = new BaseDadosDocentes();
        propostas = new BaseDadosPropostas();
        candidaturas = new BaseDadosCandidatura();
    }

    /*        ALUNOS        */
    public Set<Aluno> getAlunos() {
        return alunos.getAlunos();
    }
    public int getNumberOfStudents() {
        return alunos.getNumberOfStudents();
    }

    public boolean addStudent(long numero, String nome, String email, String curso, String ramo, double classificacao, boolean acesso){
        return alunos.addAluno(numero, nome, email, curso,ramo,classificacao, acesso);
    }

    public boolean delStudent(long numero) {
        return alunos.delAluno(numero);
    }

    public boolean editAluno(long numero, DadosAluno tipo, String data) {
        return alunos.editAluno(numero, tipo, data);
    }
    public String getStudentInfo(long numero) {
        return   alunos.getStudentInfo(numero);
    }
    /*     FIM ALUNOS       */



    /*    CANDIDATURAS      */
    public String getCandid (long numero) {
        return candidaturas.getCandid(numero);
    }
    public int getNumberOfCandidaturas() {
        return candidaturas.getNumberOfCandidaturas();
    }

    public boolean addCandidatura(long studentNumber) {
        return candidaturas.addCandidatura(studentNumber);
    }
    /*  FIM CANDIDATURAS    */

    public boolean removeCandidatura(long studentNumber) {
        return candidaturas.removeCandidatura(studentNumber);
    }
    public boolean addProposalId(long studentNumber, String proposalId) {
        return candidaturas.addProposalId(studentNumber, proposalId);
    }
    public boolean removeProposalId(long studentNumber, String proposalId) {
        return candidaturas.removeProposalId(studentNumber, proposalId);
    }

    public Set<Candidatura> getCandidaturas() {
        return candidaturas.getCandidaturas();
    }

    /*       DOCENTES       */

    public Set<Docente> getDocentes() {return docentes.getDocentes();}

    public int getNumberOfDocentes() {
        return docentes.getNumberOfDocentes();
    }

    public boolean addDocente(String email, String nome) {
        return docentes.addDocente(email,nome);
    }

    public boolean removeDocente(String email) {
        return docentes.removeDocente(email);
    }

    public boolean editDocente(DadosDocente tipo, String newData, String email) {
        return docentes.editDocente(tipo, newData, email);
    }

    public String getInfoDocente(String email) {
        return docentes.getInfoDocente(email);
    }

    /*     FIM DOCENTES     */



    /*       PROPOSTAS      */
    public int getNumberOfPropostas() {
        return propostas.getNumberOfPropostas();
    }

    public String desassociateDocente(String proposalID) { return propostas.desassociateDocente(proposalID);}
    public String associateDocente(String email, String proposalID) {
        return propostas.associateDocente(email, proposalID, docentes);
    }

    public String removeNumberProposal(String proposalID) {

        return propostas.removeNumberProposal(proposalID);
    }
    public String associateStudentWithProposal(long numero, String proposalID) {

        return propostas.associateStudentWithProposal(numero, proposalID,alunos);
    }

    public boolean setOrientador(String idProposta, String email) {
        return propostas.setOrientador(idProposta, email);
    }

    public boolean addAutoProposta(String idProposta, String titulo) {
       return propostas.addAutoProposta(idProposta,titulo);
    }
    public boolean addProjeto(String idProposta, String ramo, String titulo, String email) {
        return propostas.addProjeto(idProposta, ramo, titulo, email);
    }
    public boolean addEstagio(String idProposta, String ramo, String titulo, String idEntidade) {
        return propostas.addEstagio(idProposta, ramo, titulo, idEntidade);
    }

    public boolean removeProposta(String idProposta) {
        return propostas.removeProposta(idProposta);
    }

    public BaseDadosPropostas getPropostas() {
        return propostas;
    }
    public Set<Proposta> getPropostasSet() {return propostas.getPropostas();}
    public String getPropostaStr(String ID) {
        return propostas.getPropostaStr(ID);
    }

    public String automaticTeacherAttribution() {
        return propostas.automaticTeacherAttribution();
    }

    /*     FIM PROPOSTAS    */


    /*   FUNÇÔES PESQUISA   */
    public String getStudentsWProposalOrientator() {
        return propostas.getStudentsWProposalOrientator(alunos);
    }
    public String getStudentsWProposalNoOrientator() {
        return propostas.getStudentsWProposalNoOrientator(alunos);
    }


    public String getInfoDocenteInProposals() {
        return propostas.getInfoDocenteInProposals(docentes);
    }
    public String getStudentsAutoproposta() {  // Obter estudantes candidaturas com autoproposta
        return propostas.getStudentsAutopropostaWithCandidature(candidaturas, alunos);
    }

    public String getStudentsRegistered() { // Obter estudantes com registro feito
        return propostas.getStudentsRegistered(alunos);
    }

    public String getStudentsWithCandidature() {    // Obter estudantes com candidatura registada
        return candidaturas.getStudentsWithCandidature(alunos);
    }

    public String getStudentsWithNoCandidature() {    // Obter estudantes sem candidatura registada
        return candidaturas.getStudentsWithNoCandidature(alunos);
    }

    public String getAutoProposals() {
        return propostas.getAutoProposals();
    }
    public String getProjetos() {

        return propostas.getProjetos();
    }
    public String getProposalsWithCandidature() {
        return propostas.getProposalsWithCandidature(candidaturas);
    }
    public String getProposalsWithNoCandidature() {
        return propostas.getProposalsWithNoCandidature(candidaturas);
    }

    public String getAP() { // Get autoprpopostas (info estudante)
        return propostas.getAP(alunos);
    }

    public String getStudentsWithCandidatureRegistered() {

        return propostas.getStudentsWithCandidatureRegistered(candidaturas, alunos);
    }

    public String StudentsWithAttributedProposal() {
        return propostas.StudentsWithAttributedProposal(alunos);
    }

    public String getStudentsNotRegistered() {
        return propostas.getStudentsNotRegistered(alunos);

    }

    public String getFreeProposals() {

        return propostas.getFreeProposals();
    }

    public String getAttributedProposal() {
        return propostas.getAttributedProposal();
    }

    public String getAllStudentProposalsNotDesignated() {

        return propostas.getAllStudentProposalsNotDesignated(alunos, candidaturas);
    }

    /*   FIM FUNÇOES PESQUISA   */

    /* AUTOPROPOSTA */
    public String automaticProposalAttribution() {
        return propostas.automaticProposalAttribution(candidaturas);
    }









    /*FIM AUTOPROPOSTA*/

    /*EXPORT DATA ALUNOS */

    public String dataToExportAluno(DadosAluno choice, int index) {
        Aluno a = null;
        int j = 0;
        for (Aluno student: alunos.getAlunos()) {
            if (j == index) {
                a = student;
                break;
            }
            j++;
        }
        /*
        1- numero
        2- nome

        * */

        switch (choice) {
            case NUMERO -> {
                //assert a != null;
                return a != null ? Long.toString(a.getNumero()): "-1" ;
            }

            case NOME -> {
                return a != null ? a.getNome() : "-1";
            }

            case EMAIL -> {
                return a != null ? a.getEmail() : "-1";
            }

            case CURSO -> {
                return a != null ? a.getCurso() : "-1";
            }

            case RAMO -> {
                return a != null ? a.getRamo() : "-1";
            }

            case CLASSIFICACAO -> {
                return a != null ? Double.toString(a.getClassificacao()) : "-1";
            }

            case ACESSO -> {
                System.out.println("O acesso do aluno é: " + Boolean.toString(Objects.requireNonNull(a).isAcesso()));
                return Boolean.toString(a.isAcesso());
            }

        }

        return "-1";
    }

    /*END EXPORT DATA ALUNOS*/

    /*EXPORT DATA DOCENTES*/
    public String dataToExportDocente(DadosDocente choice, int index) {
        Docente a = null;
        int j = 0;

        for (Docente d: docentes.getDocentes()) {
            if (j == index) {
                a = d;
                break;
            }
            j++;
        }

        switch (choice) {
            case NOME -> {
                return a != null ? a.getNome() : "-1";
            }

            case EMAIL -> {
                return a != null ? a.getEmail() : "-1";
            }

        }

        return "-1";
    }

    /*END EXPORT DATA DOCENTES*/


    /* EXPORT DATA PROPOSTAS*/
    public String dataToExportProposta(int choice, int index) {
        Proposta a = null;
        int j = 0;
        for (Proposta p: propostas.getPropostas()) {
            if (j == index) {
                a = p;
                break;
            }
            j++;
        }

        if (choice == -2) {
            return switch (Objects.requireNonNull(a).getTipo()) {
                case ESTAGIO -> "2";
                case PROJETO -> "3";
                case AUTOPROPOSTA -> "1";
            };
        }


        else {
            switch (Objects.requireNonNull(a).getTipo()) {
                case ESTAGIO -> {
                    switch (choice) { // TODO email docente and CHange data types
                        case 1 -> {
                            return "T1";
                        }
                        case 2 -> {
                            return a.getId();
                        }
                        case 3 -> {
                            return a.getRamo();
                        }
                        case 4 -> {
                            return a.getTitulo();
                        }
                        case 5 -> {
                            return a.getIdEntidade();
                        }
                        case 6 -> {
                            if (a.getNumeroAluno() != 0)
                                return Long.toString(a.getNumeroAluno());
                            else
                                return "";
                        }
                    }

                    return "-1";
                }

                case PROJETO -> {
                    switch (choice) {
                        case 1 -> {
                            return "T2";
                        }
                        case 2 -> {
                            return a.getId();
                        }
                        case 3 -> {
                            return a.getRamo();
                        }
                        case 4 -> {
                            return a.getTitulo();
                        }
                        case 5 -> {
                            return a.getEmailDocente();
                        }
                        case 6 -> {
                            if (a.getNumeroAluno() != 0)
                                return Long.toString(a.getNumeroAluno());
                            else
                                return "";
                        }
                    }

                    return "-1";
                }

                case AUTOPROPOSTA -> {
                    switch (choice) {
                        case 1 -> {
                            return "T3";
                        }
                        case 2 -> {
                            return a.getId();
                        }
                        case 3 -> {
                            return a.getTitulo();
                        }
                        case 4 -> {
                            return Long.toString(a.getNumeroAluno());
                        }
                    }

                    return "-1";

                }
            }
        }

        return "-1";
    }
    /* END EXPORT DATA PROPOSTAS*/

    /* EXPORT DATA CANDIDATURAS*/  // TODO mudar choice to datatype e modificar set ids
    public String dataToExportCandidatura(int choice, int index) {
        Candidatura a = null;
        int j = 0;
        for (Candidatura c : candidaturas.getCandidaturas()) {
            if (j == index) {
                a = c;
                break;
            }
            j++;
        }

        switch (choice) {
            case 1 -> {
                return a != null ? Long.toString(a.getNumeroAluno()): "-1" ;
            }

            case 2 -> {

                System.out.println("PROPOSTAS: " + Objects.requireNonNull(a).getProposalsIds());
                if (a.getProposalsIds().isEmpty())
                    return ",";
                else
                    return a.getProposalsIds().toString();

            }

        }

        return "-1";
    }

    /* END EXPORT DATA CANDIDATURAS*/

}
