package pt.isec.pa.apoio_poe.model.data.Docentes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class BaseDadosDocentes  implements Serializable {

    Set<Docente> docentes;

    public BaseDadosDocentes() {
        docentes = new HashSet<>();
    }

    private Docente getDocente(String email) {
        if (email == null) return null;
        for (Docente d: docentes) {
            if (d.getEmail().equals(email)) {
                return d;
            }
        }
        return null;
    }

    public boolean addDocente(String email, String nome) {
        if (email == null || nome == null) return false;
        for (Docente d: docentes) {
            if (d.getEmail().equals(email)) {
                return false;
            }
        }
        return docentes.add(new Docente(nome, email));
    }
    public boolean removeDocente(String email) {
        return docentes.remove(getDocente(email));
    }


    public boolean editDocente(DadosDocente tipo, String newData, String email) {

        if (newData == null) return false;

        if (getDocente(email) == null) return false;

        switch (tipo) {
            case EMAIL -> {
                getDocente(email).setEmail(newData);
                return true;

            }
            case NOME -> {
                getDocente(email).setNome(newData);
                return true;
            }
            default -> {
                return false;
            }
        }

    }

    public String getInfoDocente(String email) {
        return getDocente(email).toString();
    }

    public Set<Docente> getDocentes() {
        return docentes;
    }

    public int getNumberOfDocentes() {
        return docentes.size();
    }
}
