package pt.isec.pa.apoio_poe.model.data.Alunos;

import java.io.Serializable;
import java.util.Objects;

/**
 * Esta classe representa um aluno. Esta contem várias variáveis que dizem respetio às características
 * de um aluno.
 *
 * @see BaseDadosAlunos
 * */
public class Aluno implements Serializable {
    private long numero;
    private String nome, email, curso, ramo;
    private double classificacao;
    private boolean acesso;


    public Aluno(long numero, String nome, String email, String curso, String ramo, double classificacao, boolean acesso) {
        this.numero = numero;
        this.nome = nome;
        this.email = email;
        this.curso = curso;
        this.ramo = ramo;
        this.classificacao = classificacao;
        this.acesso = acesso;
    }


    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public boolean isAcesso() {
        return acesso;
    }

    public void setAcesso(boolean acesso) {
        this.acesso = acesso;
    }
    @Override
    public String toString() {
        return "Aluno{" +
                "numero=" + numero +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", curso='" + curso + '\'' +
                ", ramo='" + ramo + '\'' +
                ", classificacao=" + classificacao +
                ", acesso=" + acesso +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return numero == aluno.numero;
    }

    @Override
    public int hashCode() {

        return Objects.hash(numero);
    }


}