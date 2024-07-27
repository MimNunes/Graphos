package tpa.routeoptimizer.app;

import java.util.Comparator;

/**
 *
 * @author victoriocarvalho
 * 
 * Essa é comparadora de alunos por nome que será utilizada para criar as árvores
 * nos programas de teste para redigir os relatórios.
 */

public class ComparadorDisciplinaPorNome implements Comparator<Disciplina> {
 
    @Override
    public int compare(Disciplina o1, Disciplina o2) {
        return o1.getNome().compareTo(o2.getNome());
    }
    
}
