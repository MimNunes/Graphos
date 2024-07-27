package tpa.routeoptimizer.app;

import java.util.ArrayList;

/**
 *
 * @author victoriocarvalho
 * 
 * Essa é a classe Aluno que será utilizada como tipo do conteúdo das árvores nos 
 * programas de teste para redigir os relatórios.
 */

public class Disciplina  {
    private int codigo;
    private String nome;
    private int cargaHoraria;
    ArrayList<Integer> prerequisitos;

    public Disciplina(int codigo, String nome, int cargaHoraria){
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.nome = nome;    
        this.prerequisitos = new ArrayList();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
        
    public ArrayList getPrerequisitos() {
        return prerequisitos;
    }
    
    public void setPrerequisitos(int codigo) {
        this.prerequisitos.add(codigo);
    }
}
