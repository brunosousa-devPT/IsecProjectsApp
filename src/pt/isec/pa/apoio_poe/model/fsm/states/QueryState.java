package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.AppData;
import pt.isec.pa.apoio_poe.model.fsm.AppContext;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.model.fsm.AppStateAdapter;

import java.io.Serializable;

public class QueryState extends AppStateAdapter {
    public QueryState(AppContext context, AppData data) {
        super(context,data);
    }


    @Override
    public AppState getState() {
        return AppState.QUERY_STATE;
    }

    @Override
    public void begin() {changeState(AppState.BEGIN_STATE);}

}
