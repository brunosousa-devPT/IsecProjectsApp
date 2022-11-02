package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.AppData;
import pt.isec.pa.apoio_poe.model.fsm.AppContext;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.model.fsm.AppStateAdapter;

import java.io.Serializable;

public class BeginState extends AppStateAdapter   {
    public BeginState(AppContext context, AppData data) {
        super(context,data);
    }
    @Override
    public AppState getState() {
        return AppState.BEGIN_STATE;
    }

    @Override
    public void config() {
        changeState(AppState.CONFIG_STATE);
    }

}
