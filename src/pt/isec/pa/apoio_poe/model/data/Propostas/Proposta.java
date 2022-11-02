package pt.isec.pa.apoio_poe.model.data.Propostas;

import java.io.Serializable;
import java.util.Objects;

public abstract class Proposta implements Serializable {
    private String id,titulo;
    private long numeroAluno;
    private String orientador;
    TipoPropostas tipo;


    public Proposta(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.numeroAluno = 0;
        this.orientador = "";
    }
    public TipoPropostas getTipo() {
        return this.tipo;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public long getNumeroAluno() {
        return numeroAluno;
    }

    public String getOrientador() {
        return orientador;
    }

    public String getEmailDocente() {
        return "";
    }

    public void setEmailDocente(String emailDocente) {

    }

    public void setOrientador(String orientador) {
        this.orientador = orientador;
    }

    public void setNumeroAluno(long numeroAluno) {
        this.numeroAluno = numeroAluno;
    }

    @Override
    public String toString() {
        return "Proposta{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proposta proposta)) return false;
        return Objects.equals(id, proposta.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getRamo() {
        return "";
    }

    public String getIdEntidade() {
        return "";
    }
}