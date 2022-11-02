package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.AppData;
import pt.isec.pa.apoio_poe.model.fsm.AppContext;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.model.fsm.AppStateAdapter;

public class ManualAttributionOfferState extends AppStateAdapter {
    AppData data;
    AppContext context;
    public ManualAttributionOfferState(AppContext context, AppData data) {
        super(context, data);
        this.data =data;
        this.context = context;
    }
    @Override
    public void designateOffer() {
        changeState(AppState.OFFERS_ATTRIBUTION_STATE);
    }
    @Override
    public String removeNumberProposal(String proposalID) {
        return data.removeNumberProposal(proposalID);
    }

    @Override
    public AppState getState() {
        return AppState.MANUAL_ATTRIBUTION_OFFER_STATE;
    }
    @Override
    public String associateStudentWithProposal(long numero, String proposalID) {

        return data.associateStudentWithProposal(numero, proposalID);
    }

}
