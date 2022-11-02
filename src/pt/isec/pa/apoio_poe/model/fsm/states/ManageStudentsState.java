package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.Alunos.DadosAluno;
import pt.isec.pa.apoio_poe.model.data.AppData;
import pt.isec.pa.apoio_poe.model.fsm.AppContext;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.model.fsm.AppStateAdapter;

import java.io.Serializable;

public class ManageStudentsState extends AppStateAdapter   {
    AppData data;
    AppContext context;

    public ManageStudentsState(AppContext context, AppData data) {
        super(context,data);
        this.data = data;
        this.context = context;
    }

    @Override
    public boolean addStudent(long numero, String nome, String email, String curso, String ramo, double classificacao, boolean acesso){
        return data.addStudent(numero, nome, email, curso,ramo,classificacao, acesso);
    }

    @Override
    public boolean delStudent(long numero) {
        return data.delStudent(numero);
    }
    @Override
    public void config() {
        changeState(AppState.CONFIG_STATE);
    }
    @Override
    public boolean editAluno(long numero, DadosAluno tipo, String newData) {
        return data.editAluno(numero, tipo, newData);
    }
    @Override
    public AppState getState() {
        return AppState.MANAGE_STUDENTS_STATE;
    }

    @Override
    public void begin() {changeState(AppState.BEGIN_STATE);}

    @Override
    public String getStudentInfo(long numero) {
        return data.getStudentInfo(numero);
    }
}
