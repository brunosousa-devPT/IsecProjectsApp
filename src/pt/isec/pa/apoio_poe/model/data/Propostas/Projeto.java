package pt.isec.pa.apoio_poe.model.data.Propostas;
import pt.isec.pa.apoio_poe.model.data.Propostas.TipoPropostas;

import java.io.Serializable;

import static pt.isec.pa.apoio_poe.model.data.Propostas.TipoPropostas.PROJETO;

public class Projeto extends Proposta implements Serializable {
    private String ramo;
    private String emailDocente;

    Projeto(String id, String ramo, String titulo, String email) {
        super(id, titulo);
        this.emailDocente = email;
        this.ramo = ramo;

    }
    @Override
    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }
    @Override
    public String getEmailDocente() {
        return emailDocente;
    }
    @Override
    public void setEmailDocente(String emailDocente) {
        this.emailDocente = emailDocente;
    }
    @Override
    public TipoPropostas getTipo(){
        return PROJETO;
    }
    @Override
    public String toString() {

        if (getNumeroAluno() == 0) {
            return super.toString() + ", professor='" + emailDocente + '\'' +
                    ", ramo='" + ramo + '\'' +
                    ", Orientador = " + getOrientador() + "}";
        }
        else {
            return super.toString() + ", professor='" + emailDocente + '\'' +
                    ", ramo='" + ramo + '\'' +
                    ", numero_aluno='" + getNumeroAluno() + '\'' +
                    ", Orientador = " + getOrientador() + "}";
        }

    }

}