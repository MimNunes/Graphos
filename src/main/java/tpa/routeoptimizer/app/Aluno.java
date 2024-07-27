package tpa.routeoptimizer.app;

import java.util.ArrayList;

/**
 *
 * @author victoriocarvalho
 * 
 * Essa é a classe Aluno que será utilizada como tipo do conteúdo das árvores nos 
 * programas de teste para redigir os relatórios.
 */

public class Aluno  {
    private int matricula;
    private String nome;
    ArrayList<Integer> disciplinas;

    public Aluno(int matricula, String nome){
        this.matricula = matricula;
        this.nome = nome;    
        this.disciplinas = new ArrayList();
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public ArrayList getDisciplinas() {
        return disciplinas;
    }
    
    public void setDisciplinas(int codigo) {
        this.disciplinas.add(codigo);
    }
}
