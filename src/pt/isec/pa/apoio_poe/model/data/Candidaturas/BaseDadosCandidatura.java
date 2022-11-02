package pt.isec.pa.apoio_poe.model.data.Candidaturas;

import pt.isec.pa.apoio_poe.model.data.Alunos.Aluno;
import pt.isec.pa.apoio_poe.model.data.Alunos.BaseDadosAlunos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class BaseDadosCandidatura  implements Serializable {

    private Set<Candidatura> candidaturas;


    public BaseDadosCandidatura() {
        candidaturas = new HashSet<>();
    }
    public Candidatura getCandidatura(long studentNumber) {
        if (studentNumber <= 0) return null;
        for (Candidatura c: candidaturas) {
            if (c.getNumeroAluno() == studentNumber) {
                return c;
            }
        }
        return null;
    }

    public boolean addCandidatura(long studentNumber) {

        return candidaturas.add(new Candidatura(studentNumber));

    }

    public String getCandid (long numero) {
        return getCandidatura(numero)== null? "":getCandidatura(numero).toString();
    }

    public boolean removeCandidatura(long studentNumber) {
        return candidaturas.remove(getCandidatura(studentNumber));
    }
    public boolean addProposalId(long studentNumber, String proposalId) {
        return getCandidatura(studentNumber).addProposalID(proposalId);
    }
    public boolean removeProposalId(long studentNumber, String proposalId) {
        return getCandidatura(studentNumber).delProposalID(proposalId);
    }

    public Set<Candidatura> getCandidaturas() {
        return candidaturas;
    }

    public String getStudentsWithCandidature(BaseDadosAlunos alunos) {
        StringBuilder sb = new StringBuilder();
        for (Candidatura c: candidaturas) {
            sb.append(alunos.getStudentInfo(c.getNumeroAluno())).append("\n");
        }
        return sb.toString();
    }




    public String getStudentsWithNoCandidature(BaseDadosAlunos alunos) {
        boolean confirma = false;
        StringBuilder sb = new StringBuilder ();
        for (Aluno a: alunos.getAlunos()) {

            for (Candidatura c: candidaturas){
                if (c.getNumeroAluno() == a.getNumero()) {
                    confirma = true;
                    break;
                }
            }
            if (!confirma) {
                sb.append(a).append("\n");

            }
            else confirma = false;
        }
        return sb.toString();
    }
    public int getNumberOfCandidaturas() {
        return candidaturas.size();
    }


}