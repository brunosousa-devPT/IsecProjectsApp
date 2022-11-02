package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.AppData;
import pt.isec.pa.apoio_poe.model.fsm.AppContext;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.model.fsm.AppStateAdapter;

public class ManualAttributionTeacherState extends AppStateAdapter {
    AppData data;
    AppContext context;

    public ManualAttributionTeacherState(AppContext context, AppData data) {
        super(context, data);
        this.context = context;
        this.data = data;
    }
    @Override
    public AppState getState() {
        return AppState.MANUAL_ATTRIBUTION_TEACHER_STATE;
    }

    @Override
    public void adivisorAttribution() {
        changeState(AppState.ADVISORS_ATTRIBUTION_STATE);
    }
    @Override
    public void manualTeacherAttribution() {changeState(AppState.MANUAL_ATTRIBUTION_TEACHER_STATE);}

    @Override
    public String associateDocente(String email, String proposalID) {
        return data.associateDocente(email, proposalID);
    }
    @Override
    public String desassociateDocente(String proposalID) { return data.desassociateDocente(proposalID);}

    @Override
    public boolean setOrientador(String idProposta, String email) {
        return data.setOrientador(idProposta, email);
    }

}
