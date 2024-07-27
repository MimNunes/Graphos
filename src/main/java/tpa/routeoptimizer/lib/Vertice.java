package tpa.routeoptimizer.lib;
import java.util.ArrayList;

public class Vertice<T> {
    private T valor;
    private ArrayList<Aresta<T>> destinos;

    // Construtor que recebe um valor para inicializar o vértice.
    public Vertice(T valor){
        setValor(valor);
        this.destinos = new ArrayList<>();
    }

    // Obtém o valor associado ao vértice.
    public T getValor(){
        return valor;
    }

    // Define o valor associado ao vértice.
    public void setValor(T valor){
        this.valor = valor;
    }

    // Adiciona uma aresta de destino ao vértice.
    public void adicionarDestino(Aresta<T> aresta){
        this.destinos.add(aresta);
    }

    // Obtém a lista de arestas de destino do vértice.
    public ArrayList<Aresta<T>> getDestinos(){
        return destinos;
    }

    // Sobrescreve o método toString para fornecer uma representação em string do vértice.
    @Override
    public String toString() {
        return this.valor.toString();
    }
    
    
}
