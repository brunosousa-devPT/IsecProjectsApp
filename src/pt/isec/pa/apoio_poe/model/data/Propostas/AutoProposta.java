package pt.isec.pa.apoio_poe.model.data.Propostas;

import java.io.Serializable;

import static pt.isec.pa.apoio_poe.model.data.Propostas.TipoPropostas.AUTOPROPOSTA;
import static pt.isec.pa.apoio_poe.model.data.Propostas.TipoPropostas.PROJETO;

public class AutoProposta extends Proposta implements Serializable {
    AutoProposta(String id, String titulo) {
        super(id, titulo);
    }


    @Override
    public String toString() {
        return super.toString() + ", numero_aluno='" + getNumeroAluno() + '\'' +
                '}';
    }
    @Override
    public TipoPropostas getTipo(){
        return AUTOPROPOSTA;
    }

}
