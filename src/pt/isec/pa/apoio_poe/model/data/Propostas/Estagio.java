package pt.isec.pa.apoio_poe.model.data.Propostas;

import java.io.Serializable;

import static pt.isec.pa.apoio_poe.model.data.Propostas.TipoPropostas.ESTAGIO;
import static pt.isec.pa.apoio_poe.model.data.Propostas.TipoPropostas.PROJETO;

public class Estagio extends Proposta  implements Serializable {
    private String ramo, idEntidade;

    Estagio(String id, String ramo, String titulo, String idEntidade) {
        super(id, titulo);
        this.ramo = ramo;
        this.idEntidade = idEntidade;

    }

    @Override
    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }
    @Override
    public String getIdEntidade() {
        return idEntidade;
    }

    public void setIdEntidade(String idEntidade) {
        this.idEntidade = idEntidade;
    }

    @Override
    public String toString() {

        if (getNumeroAluno() == 0) {
            return super.toString() + ", entidade='" + idEntidade + '\'' +
                    ", ramo='" + ramo + '\'' +
                    ", Orientador = " + getOrientador() + "}";
        }
        else {
            return super.toString() + ", entidade='" + idEntidade + '\'' +
                    ", ramo='" + ramo + '\'' +
                    ", numero_aluno='" + getNumeroAluno() + '\'' +
                    ", Orientador = " + getOrientador() + "}";
        }

    }
    @Override
    public TipoPropostas getTipo(){
        return ESTAGIO;
    }

}
