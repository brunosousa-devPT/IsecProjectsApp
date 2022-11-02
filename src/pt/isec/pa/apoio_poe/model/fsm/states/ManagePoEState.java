package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.AppData;
import pt.isec.pa.apoio_poe.model.fsm.AppContext;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.model.fsm.AppStateAdapter;

import java.io.Serializable;

public class ManagePoEState extends AppStateAdapter  {
    AppData data;
    AppContext context;

    public ManagePoEState(AppContext context, AppData data) {
        super(context,data);
        this.data = data;
        this.context =context;
    }
    @Override
    public boolean removeProposta(String idProposta) {
        return data.removeProposta(idProposta);
    }
    @Override
    public boolean addAutoProposta(String idProposta, String titulo) {
        return data.addAutoProposta(idProposta,titulo);
    }
    @Override
    public boolean addProjeto(String idProposta, String ramo, String titulo, String email) {
        return data.addProjeto(idProposta, ramo, titulo, email);
    }

    @Override
    public boolean addEstagio(String idProposta, String ramo, String titulo, String idEntidade) {
        return data.addEstagio(idProposta, ramo, titulo, idEntidade);
    }
    @Override
    public AppState getState() {
        return AppState.MANAGE_POE_STATE;
    }

    @Override
    public void config() {
        changeState(AppState.CONFIG_STATE);
    }

    @Override
    public void begin() {changeState(AppState.BEGIN_STATE);}
    @Override
    public String getPropostaStr(String ID) {
        return data.getPropostaStr(ID);
    }
}
