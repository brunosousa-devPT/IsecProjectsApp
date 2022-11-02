package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.AppData;
import pt.isec.pa.apoio_poe.model.fsm.AppContext;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.model.fsm.AppStateAdapter;

import java.io.Serializable;

public class ConfigState extends AppStateAdapter  {
    public ConfigState(AppContext context, AppData data) {
        super(context,data);
    }

    @Override
    public AppState getState() {
        return AppState.CONFIG_STATE;
    }

    @Override
    public void manageStudent() {
        changeState(AppState.MANAGE_STUDENTS_STATE);
    }
    @Override
    public void manageTeacher() {
        changeState(AppState.MANAGE_TEACHERS_STATE);
    }
    @Override
    public void managePoE() {
        changeState(AppState.MANAGE_POE_STATE);
    }
    @Override
    public void candidature() {
        changeState(AppState.CANDIDATURE_STATE);
    }

    @Override
    public void query() {
        changeState(AppState.QUERY_STATE);
    }

    @Override
    public void begin() {changeState(AppState.BEGIN_STATE);}

}
