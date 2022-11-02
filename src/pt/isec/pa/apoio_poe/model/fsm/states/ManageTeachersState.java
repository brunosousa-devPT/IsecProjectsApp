package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.AppData;
import pt.isec.pa.apoio_poe.model.data.Docentes.DadosDocente;
import pt.isec.pa.apoio_poe.model.fsm.AppContext;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.model.fsm.AppStateAdapter;

import java.io.Serializable;

public class ManageTeachersState extends AppStateAdapter  {
    AppData data;
    AppContext context;

    public ManageTeachersState(AppContext context, AppData data) {
        super(context,data);
        this.data = data;
        this.context = context;
    }

    @Override
    public AppState getState() {
        return AppState.MANAGE_TEACHERS_STATE;
    }

    @Override
    public void config() {
        changeState(AppState.CONFIG_STATE);
    }
    @Override
    public boolean addDocente(String email, String nome) {
        return data.addDocente(email,nome);
    }
    @Override
    public void begin() {changeState(AppState.BEGIN_STATE);}
    @Override
    public boolean removeDocente(String email) {
        return data.removeDocente(email);
    }

    @Override
    public boolean editDocente(DadosDocente tipo, String newData, String email) {
        return data.editDocente(tipo, newData, email);
    }
    @Override
    public String getInfoDocente(String email) {
        return data.getInfoDocente(email);
    }
}
