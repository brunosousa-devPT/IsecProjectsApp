package pt.isec.pa.apoio_poe.model.data.Candidaturas;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 *
 * */
public class Candidatura implements Serializable {

    private long numeroAluno;
    private Set<String> proposalsIds;

    public Candidatura(long numeroAluno) {
        this.numeroAluno = numeroAluno;
        this.proposalsIds = new HashSet<>();
    }

    public long getNumeroAluno() {
        return numeroAluno;
    }

    public void setNumeroAluno(long numeroAluno) {
        this.numeroAluno = numeroAluno;
    }

    public Set<String> getProposalsIds() {
        return proposalsIds;
    }

    public void setProposalsIds(Set<String> proposalsIds) {
        this.proposalsIds = proposalsIds;
    }

    /**
     * Adiciona uma String ao Set proposalIDs.
     * @param proposalID: String que representa o id da proposta
     * @return Boolean que diz se a proposta foi adicionada com sucesso
     * @see Candidatura
     * */
    public boolean addProposalID(String proposalID) {
        if (proposalID == null) return false;

        return proposalsIds.add(proposalID);
    }
    /**
     * Remove uma String ao Set proposalIDs.
     * @param proposalID: String que representa o id da proposta
     * @return Boolean que diz se a proposta foi removida com sucesso
     * @see Candidatura
     * */
    public  boolean delProposalID(String proposalID) {
        for (String s: proposalsIds) {
            if (s.equalsIgnoreCase(proposalID)) {
                return proposalsIds.remove(s);
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidatura that = (Candidatura) o;
        return numeroAluno == that.numeroAluno;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroAluno);
    }

    @Override
    public String toString() {
        return "Candidatura{" +
                "numeroAluno=" + numeroAluno +
                ", proposalsIds=" + proposalsIds +
                '}';
    }
}

