package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.AppData;
import pt.isec.pa.apoio_poe.model.fsm.AppContext;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.model.fsm.AppStateAdapter;

public class OffersTiebreakerState extends AppStateAdapter {

    public OffersTiebreakerState(AppContext context, AppData data) {
        super(context, data);
    }
    @Override
    public AppState getState() {
        return AppState.OFFERS_TIEBREAKER_STATE;
    }

    @Override
    public void designateOffer() {
        changeState(AppState.OFFERS_ATTRIBUTION_STATE);
    }
}
