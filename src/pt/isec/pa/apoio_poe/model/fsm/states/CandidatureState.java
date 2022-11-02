package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.AppData;
import pt.isec.pa.apoio_poe.model.fsm.AppContext;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.model.fsm.AppStateAdapter;

import java.io.Serializable;

public class CandidatureState extends AppStateAdapter {
    AppContext context;
    AppData data;
    public CandidatureState(AppContext context, AppData data) {
        super(context,data);
        this.data = data;
        this.context = context;
    }


    @Override
    public void designateOffer() {
        changeState(AppState.OFFERS_ATTRIBUTION_STATE);
    }

    @Override
    public AppState getState() {
        return AppState.CANDIDATURE_STATE;
    }

    @Override
    public void config() {
        changeState(AppState.CONFIG_STATE);
    }

    @Override
    public void begin() {changeState(AppState.BEGIN_STATE);}

    @Override
    public boolean removeCandidatura(long studentNumber) {
        return data.removeCandidatura(studentNumber);
    }
    @Override
    public String getCandid (long numero) {
        return data.getCandid(numero);
    }
    @Override
    public boolean addCandidatura(long studentNumber) {
        return data.addCandidatura(studentNumber);
    }


    @Override
    public boolean addProposalId(long studentNumber, String proposalId) {
        return data.addProposalId(studentNumber, proposalId);
    }

    @Override
    public boolean removeProposalId(long studentNumber, String proposalId) {
        return data.removeProposalId(studentNumber, proposalId);
    }
    @Override
    public boolean removeDocente(String email) {
        return data.removeDocente(email);
    }

}
