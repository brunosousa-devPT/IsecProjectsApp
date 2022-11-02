package pt.isec.pa.apoio_poe.model.data.Alunos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A classe BaseDadosAlunos  é uma espécie de contentor que guarda vários objetos do tipo Aluno num set.
 *
 * @see Aluno
 * */
public class BaseDadosAlunos  implements Serializable {

    private Set<Aluno> alunos;

    public BaseDadosAlunos() {
        alunos = new HashSet<>();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Aluno a: alunos) {
            sb.append("Aluno ").append(a.getNome()).append(" {\n");
            sb.append(a);
            sb.append(" }\n");
        }
        return sb.toString();
    }
    /**
     * Adiciona um aluno à base de dados
     * O aluno não pode ter um número que já exista na base de dados.
     *
     * @param numero: numero do aluno a adicionar, não pode existir na BD
     * @param nome: nome do aluno a adicionar
     * @param email: email do aluno a adicionar
     * @param curso: curso do aluno a adicionar {"LEI , LEI-PL"}
     * @param ramo: ramo do aluno a adicionar {"DA, RAS, SI"}
     * @param classificacao: classificação do aluno a adicionar
     * @param acesso: boolean que indica que o aluno pode aceder ao estágio ou não
     * @return    boolean que indica se o aluno foi adicionado com sucesso
     * @see       Aluno
     *
     * */
    public boolean addAluno(long numero, String nome, String email, String curso, String ramo, double classificacao, boolean acesso) {
            if (nome == null || email == null || curso == null || ramo == null || numero <0 || classificacao <0) return false;
            return alunos.add(new Aluno(numero, nome, email, curso, ramo, classificacao, acesso));
    }
    /**
     * Remove um aluno da base de dados
     *
     * @param numero: numero do aluno a ser removido
     * @return  boolean que indica se o aluno foi removido com sucesso
     * @see Aluno
     * */
    public boolean delAluno(long numero) {
        return alunos.removeIf(a -> a.getNumero() == numero);
    }
    /**
     * Edita os dados de um aluno da base de dados
     *
     * @param numero: numero do aluno com os dados a serem editados
     * @param tipo: tipo de dados a serem substituidos
     * @param data: data que vai substituir a data atual
     * @return boolean que indica se os dados foram editados com sucesso
     * @see DadosAluno
     * */
    public boolean editAluno(long numero, DadosAluno tipo, String data) {
        if (data == null) return false;
        Aluno aluno = null;
        for (Aluno aux: alunos) {  // for each para encontrar um aluno que tenha o mesmo numero que foi introduzido
            if (aux.getNumero() == numero) {
                aluno = aux;
                break;
            }
        }
        if (aluno == null) return false;
        switch(tipo) {  // Verificar o tipo de dado a editar
            case NOME -> {
                aluno.setNome(data);
                return true;
            }
            case RAMO -> {
                if (!data.equals("DA") && !data.equals("RAS") && !data.equals("SI")) return false;
                aluno.setRamo(data);
                return true;
            }
            case CURSO -> {
                if (!data.equals("LEI") && !data.equals("LEI-PL")) return false;
                aluno.setCurso(data);
                return true;
            }
            case EMAIL -> {
                if (data.indexOf('@') == -1)return false;
                aluno.setEmail(data);
                return true;
            }
            case ACESSO -> {
                aluno.setAcesso(Boolean.parseBoolean(data));
                return true;
            }
            case NUMERO -> {
                try {
                    long d = Long.parseLong(data);
                } catch (NumberFormatException nfe) {
                    return false;
                }
                aluno.setNumero(Long.parseLong(data));
                return true;
            }
            case CLASSIFICACAO -> {
                try {
                    Double d = Double.parseDouble(data);
                } catch (NumberFormatException nfe) {
                    return false;
                }
                aluno.setClassificacao(Double.parseDouble(data));
                return true;
            }
            default -> {
                return false;
            }
        }


    }
    public String getStudentInfo(long numero) {
        for (Aluno a: alunos) {
            if (a.getNumero() == numero) {
                return a.toString();
            }
        }
        return "Aluno Nao existe !\n";
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public int getNumberOfStudents() {
        return alunos.size();
    }
}
