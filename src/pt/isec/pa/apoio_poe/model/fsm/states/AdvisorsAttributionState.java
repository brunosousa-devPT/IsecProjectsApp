package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.AppData;
import pt.isec.pa.apoio_poe.model.fsm.AppContext;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.model.fsm.AppStateAdapter;

import java.io.Serializable;

public class AdvisorsAttributionState extends AppStateAdapter {
    AppContext context;
    AppData data;
    public AdvisorsAttributionState(AppContext context, AppData data) {
        super(context,data);
        this.context = context;
        this.data = data;
    }
    @Override
    public AppState getState() {
        return AppState.ADVISORS_ATTRIBUTION_STATE;
    }

    @Override
    public void begin() {changeState(AppState.BEGIN_STATE);}

    @Override
    public void manualTeacherAttribution() {changeState(AppState.MANUAL_ATTRIBUTION_TEACHER_STATE);}

    @Override
    public void query() {
        changeState(AppState.QUERY_STATE);
    }

    @Override
    public String automaticTeacherAttribution() {
        return data.automaticTeacherAttribution();
    }
    @Override
    public String getInfoDocenteInProposals() {
        return data.getInfoDocenteInProposals();
    }

    @Override
    public String getStudentsWProposalOrientator() {
        return data.getStudentsWProposalOrientator();
    }
    @Override
    public String getStudentsWProposalNoOrientator() {
        return data.getStudentsWProposalNoOrientator();
    }

    @Override
    public void designateOffer() {
        changeState(AppState.OFFERS_ATTRIBUTION_STATE);
    }

}
