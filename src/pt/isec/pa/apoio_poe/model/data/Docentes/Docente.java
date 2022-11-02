package pt.isec.pa.apoio_poe.model.data.Docentes;

import java.io.Serializable;

public class Docente implements Serializable {

    private String email, nome;


    public Docente(String email, String nome) {
        this.email = email;
        this.nome =  nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Docente{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }


}
