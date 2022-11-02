package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.AppData;
import pt.isec.pa.apoio_poe.model.fsm.AppContext;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.model.fsm.AppStateAdapter;

import java.io.Serializable;

public class OffersAttributionState extends AppStateAdapter  {
    AppData data;
    AppContext context;
    public OffersAttributionState(AppContext context, AppData data) {
        super(context,data);
        this.data = data;
        this.context = context;
    }

    @Override
    public String automaticProposalAttribution() {
        return data.automaticProposalAttribution();
    }
    @Override
    public AppState getState() {
        return AppState.OFFERS_ATTRIBUTION_STATE;
    }

    @Override
    public void begin() {changeState(AppState.BEGIN_STATE);}

    @Override
    public void tieBreaker() {changeState(AppState.OFFERS_TIEBREAKER_STATE);}

    @Override
    public void manualOfferAttribution() {changeState(AppState.MANUAL_ATTRIBUTION_OFFER_STATE);}

    @Override
    public void candidature() {changeState(AppState.CANDIDATURE_STATE);}
    @Override
    public void adivisorAttribution() {changeState(AppState.ADVISORS_ATTRIBUTION_STATE);}
}
