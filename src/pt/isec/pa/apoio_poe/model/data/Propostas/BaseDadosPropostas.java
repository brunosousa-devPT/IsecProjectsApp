package pt.isec.pa.apoio_poe.model.data.Propostas;

import pt.isec.pa.apoio_poe.model.data.Alunos.Aluno;
import pt.isec.pa.apoio_poe.model.data.Alunos.BaseDadosAlunos;
import pt.isec.pa.apoio_poe.model.data.Candidaturas.BaseDadosCandidatura;
import pt.isec.pa.apoio_poe.model.data.Candidaturas.Candidatura;
import pt.isec.pa.apoio_poe.model.data.Docentes.BaseDadosDocentes;
import pt.isec.pa.apoio_poe.model.data.Docentes.Docente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BaseDadosPropostas  implements Serializable {

    Set<Proposta> propostas;

    public BaseDadosPropostas() {
        propostas = new HashSet<>();

    }
    private Proposta getProposta(String idProposta) {
        if (idProposta == null) return null;
        for(Proposta p: propostas) {
            if (p.getId().equals(idProposta)) {
                return p;
            }
        }
        return null;
    }

    public boolean removeProposta(String idProposta) {
        return propostas.remove(getProposta(idProposta));
    }
    public boolean addAutoProposta(String idProposta, String titulo) {
        return propostas.add(new AutoProposta(idProposta, titulo));
    }
    public boolean addProjeto(String idProposta, String ramo, String titulo, String email) {
        return propostas.add(new Projeto(idProposta, ramo, titulo, email));

    }
    public boolean addEstagio(String idProposta, String ramo, String titulo, String idEntidade) {
        return propostas.add(new Estagio(idProposta, ramo, titulo, idEntidade));
    }

    public boolean setStudentNumber(String id, long numero) {
        if (getProposta(id) == null || numero < 0) return false;
        for (Proposta p: propostas) {
            if (p.getNumeroAluno() == numero) return false;
        }
        getProposta(id).setNumeroAluno(numero);
        return true;
    }
    /* TODO COMENTAR JAVA DOCS */
    public String getStudentsAutopropostaWithCandidature(BaseDadosCandidatura candidaturaBD, BaseDadosAlunos alunos) {
        StringBuilder sb = new StringBuilder();

        for (Proposta p: propostas) {
            if  (p.getTipo() == TipoPropostas.AUTOPROPOSTA) {
                for (Candidatura c: candidaturaBD.getCandidaturas()) {
                    for (String d: c.getProposalsIds()) {
                        if (d.equals(p.getId())) {
                            sb.append(alunos.getStudentInfo(c.getNumeroAluno()));
                        }
                    }
                }
            }
        }
        return sb.toString();
    }
    public String getStudentsRegistered(BaseDadosAlunos alunos) {
        StringBuilder sb= new StringBuilder();

        for (Proposta p: propostas) {
            if (p.getNumeroAluno() != 0) {
               sb.append(p).append(alunos.getStudentInfo(p.getNumeroAluno()));
            }
        }
        return sb.toString();

    }

    public String getAutoProposals() {
        StringBuilder sb = new StringBuilder();

        for (Proposta p : propostas) {
            if (p.getTipo() == TipoPropostas.AUTOPROPOSTA)
                sb.append(p).append("\n");
        }
        return sb.toString();
    }

    public String getProjetos() {
        StringBuilder sb = new StringBuilder();
        for (Proposta p : propostas) {
            if (p.getTipo() == TipoPropostas.PROJETO) {
                sb.append(p).append("\n");
            }
        }
        return sb.toString();
    }

    public String getProposalsWithCandidature(BaseDadosCandidatura candidaturas) {
        boolean controlo = false;
        StringBuilder sb= new StringBuilder();

        for (Proposta p: propostas) {
            for (Candidatura c: candidaturas.getCandidaturas()) {
                for (String proposal: c.getProposalsIds()) {
                    if (proposal.equals(p.getId())) {
                        controlo = true;
                        sb.append(p).append("\n");
                        break;
                    }
                }
                if (controlo) {
                    controlo = false;
                    break;
                }
            }
        }

        return sb.toString();
    }
    public String getProposalsWithNoCandidature(BaseDadosCandidatura candidaturas) {
        boolean controlo = false;
        StringBuilder sb= new StringBuilder();
        for (Proposta p: propostas) {
            controlo = false;
            for (Candidatura c: candidaturas.getCandidaturas()) {
                for (String proposal: c.getProposalsIds()) {
                    if (proposal.equals(p.getId())) {
                        controlo = true;
                        break;
                    }
                }
                if (controlo)  break;

            }
            if (!controlo) {
                sb.append(p).append("\n");
            }
        }
        return sb.toString();
    }

    public String automaticProposalAttribution(BaseDadosCandidatura candidatura) { // TODO fix duplicate
        boolean continua = false;
        StringBuilder sb= new StringBuilder();

        for (Candidatura c: candidatura.getCandidaturas()) {
            for (Proposta p: propostas) {
                if (p.getNumeroAluno() != 0) continue;
                for (String l: c.getProposalsIds()) {
                    if (p.getId().equals(l)) {

                        p.setNumeroAluno(c.getNumeroAluno());
                        sb.append(p).append("\n");
                        continua = true;
                        break;
                    }
                }
                if (continua) {
                    continua = false;
                    break;
                }
            }
        }

        return sb.toString();
    }
    public String getAP(BaseDadosAlunos alunosBD) {
        StringBuilder sb = new StringBuilder();
        for (Proposta p : propostas) {
            if (p.getTipo() == TipoPropostas.AUTOPROPOSTA) {
                sb.append(alunosBD.getStudentInfo(p.getNumeroAluno())).append("\n");
            }
        }
        return sb.toString();
    }

    public String getStudentsWithCandidatureRegistered(BaseDadosCandidatura candidaturaBD, BaseDadosAlunos alunos) {
        boolean acesso = false;
        StringBuilder sb = new StringBuilder();

        for (Proposta p: propostas) {
            acesso  = false;
            for (Candidatura c: candidaturaBD.getCandidaturas()) {
                for (String d: c.getProposalsIds()) {
                    if (d.equals(p.getId()) && c.getNumeroAluno() == p.getNumeroAluno()) {

                        sb.append(alunos.getStudentInfo(c.getNumeroAluno()));
                        acesso = true;
                        break;
                    }
                }
                if (acesso) break;
            }

        }
        return sb.toString();
    }

    public String StudentsWithAttributedProposal(BaseDadosAlunos alunosBD) {

        StringBuilder sb= new StringBuilder();

        for (Proposta p: propostas) {
            if (p.getTipo() == TipoPropostas.AUTOPROPOSTA) {
                sb.append(alunosBD.getStudentInfo(p.getNumeroAluno())).append("\n");
            }
        }
        for (Proposta p: propostas) {
            if (p.getTipo() == TipoPropostas.PROJETO) {
                if (p.getNumeroAluno() != 0) {
                    sb.append(alunosBD.getStudentInfo(p.getNumeroAluno())).append("\n");

                }
            }
        }
        for (Proposta p: propostas) {
            if (p.getTipo() == TipoPropostas.ESTAGIO) {
                if (p.getNumeroAluno() != 0) {
                    sb.append(alunosBD.getStudentInfo(p.getNumeroAluno())).append("\n");
                }
            }
        }

        return sb.toString();
    }
    public String getStudentsNotRegistered(BaseDadosAlunos alunosBD) {
        StringBuilder sb= new StringBuilder();
        boolean acesso = false;
        for (Aluno a: alunosBD.getAlunos()) {
            acesso = false;
            for (Proposta p: propostas) {
                if (p.getNumeroAluno() == a.getNumero()) {
                    acesso = true;
                    break;
                }
            }
            if (!acesso) {
                sb.append(a).append("\n");
            }
        }

        return sb.toString();
    }
    public String getFreeProposals() {
        StringBuilder sb= new StringBuilder();

        for (Proposta p: propostas) {
            if (p.getNumeroAluno() == 0) {
                sb.append(p).append("\n");
            }
        }

        return sb.toString();
    }
    public String getAttributedProposal() {
        StringBuilder sb= new StringBuilder();

        for (Proposta p: propostas) {
            if (p.getNumeroAluno() != 0) {
                sb.append(p).append("\n");
            }
        }

        return sb.toString();
    }

    public String getAllStudentProposalsNotDesignated(BaseDadosAlunos alunoBD, BaseDadosCandidatura candidaturaBD) {
        boolean acesso = false;
        StringBuilder sb = new StringBuilder();

        for (Candidatura c: candidaturaBD.getCandidaturas()) {
            acesso = false;
            for (Proposta p: propostas) {
                if (p.getNumeroAluno() == c.getNumeroAluno()) {
                    acesso = true;
                    break;
                }

            }
            if (!acesso) {
                sb.append(alunoBD.getStudentInfo(c.getNumeroAluno())).append("\n");
            }
        }

        return sb.toString();

    }

    public boolean setOrientador(String idProposta, String email) {
        if (idProposta == null || email == null) return false;

        if (getProposta(idProposta) == null) return false;

        getProposta(idProposta).setOrientador(email);

        return true;
    }

    public String automaticTeacherAttribution() {
        StringBuilder sb= new StringBuilder();
        for (Proposta p: propostas) {
            if (p.getTipo() == TipoPropostas.PROJETO && p.getEmailDocente() != null && Objects.equals(p.getOrientador(), "")) {
                p.setOrientador(p.getEmailDocente());
                sb.append(p).append("\n");
            }
        }
        return sb.toString();

    }

    public String associateStudentWithProposal(long numero, String proposalID, BaseDadosAlunos alunos) {
        if (proposalID == null) return "Insira uma proposta, por favor!";
        boolean acesso = false;
        for (Aluno a: alunos.getAlunos()) {
            if (a.getNumero() == numero) {
                acesso = true;
                break;
            }
        }
        if (!acesso) return "Aluno não está na Base de Dados!";
        for (Proposta p: propostas) {
            if (p.getNumeroAluno() == numero) return "Aluno já associado a outra proposta: \n" + p;
        }
        for (Proposta p2: propostas) {
            if (p2.getId().equals(proposalID)) {
                p2.setNumeroAluno(numero);
                return "Aluno associado com sucesso!\n" + p2;
            }
        }
        return "Algum erro aconteceu!";
    }

    public String removeNumberProposal(String proposalID) {
        if (proposalID == null) return "Insira o ID da proposta !";
        for (Proposta p: propostas) {
            if (p.getId().equals(proposalID)) {
                p.setNumeroAluno(0);
                return "Aluno desassociado com sucesso!";
            }
        }
        return "Insira um ID válido!";
    }
    public String desassociateDocente(String proposalID) {
        if (proposalID == null) return "Insira o ID da proposta !";
        for (Proposta p: propostas) {
            if (p.getId().equals(proposalID)) {
                p.setOrientador("");
                return "Orientador desassociado com sucesso!";
            }
        }
        return "Insira um ID válido!";
    }

    public String associateDocente(String email, String proposalID, BaseDadosDocentes docentes) {
        if (email == null ) return "Insira um email válido!";
        if (proposalID == null) return "Insira um ID válido !";
        boolean acesso = false;
        for (Docente d: docentes.getDocentes()) {
            if (d.getEmail().equals(email)) {
                acesso = true;
                break;
            }
        }
        if (!acesso) {
            return "Email inválido!";
        }
        for (Proposta p: propostas ) {
            if (p.getId().equals(proposalID)) {
                if (!Objects.equals(p.getOrientador(), "")) {
                    return "Proposta já se encontra associada a um orientador!";
                }
                else {
                    p.setOrientador(email);
                    return "Orientador associado com sucesso!";
                }
            }

        }
        return "Algum erro aconteceu!";
    }
    public String getStudentsWProposalOrientator(BaseDadosAlunos alunos) {
        StringBuilder sb= new StringBuilder();

        for (Proposta p: propostas) {
            if (p.getNumeroAluno() != 0 && !Objects.equals(p.getOrientador(), "")) {
                sb.append(alunos.getStudentInfo(p.getNumeroAluno())).append("\n").append(p).append("\n");
            }
        }

        return sb.toString();
    }
    public String getStudentsWProposalNoOrientator(BaseDadosAlunos alunos) {
        StringBuilder sb= new StringBuilder();

        for (Proposta p: propostas) {
            if (p.getNumeroAluno() != 0 && Objects.equals(p.getOrientador(), "")) {
                sb.append(alunos.getStudentInfo(p.getNumeroAluno())).append("\n").append(p).append("\n");
            }
        }

        return sb.toString();
    }
    public String getInfoDocenteInProposals(BaseDadosDocentes docentes) {
        StringBuilder sb= new StringBuilder();
        int aux;
        float media = 0;
        ArrayList <Integer> values = new ArrayList<>();
        for (Docente d: docentes.getDocentes()) {
            aux = 0;

            for (Proposta p: propostas) {
                if (d.getEmail().equals(p.getOrientador())) {
                    aux ++;
                }
            }
            values.add(aux);
            sb.append("Docente: ").append(d.getEmail()).append("| Nome: ").append("Está associado a ").append(aux).append(" propostas como orientador!\n");
        }
        int lessValue = Integer.MAX_VALUE;
        int MaxValue = 0;
        assert false;
        for (Integer i: values) {
            if (i < lessValue) lessValue = i;
            if (i>MaxValue) MaxValue = i;
            media += i;
        }
        media = media / values.size();
        sb.append("\n Média: ").append(media).append("\tMáximo: ").append(MaxValue).append("\t Minimo: ").append(lessValue);


        return sb.toString();
    }

    public int getNumberOfPropostas() {
        return propostas.size();
    }

  public  Set<Proposta> getPropostas() {
        return propostas;
  }

  public String getPropostaStr(String ID) {
        for (Proposta a : propostas) {
            if (a.getId().equals(ID)) {
                return a.toString();
            }
        }
        return " ";
  }

}
