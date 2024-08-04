package tpa.routeoptimizer.lib;

public class Aresta<T> {
    private float peso;
    private Vertice<T> origem;
    private Vertice<T> destino;

    // Construtor que recebe um vértice de origem, um vértice de destino e um peso para inicializar a aresta.
    public Aresta(Vertice<T> origem, Vertice<T> destino, float peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    // Obtém o peso associado à aresta.
    public float getPeso() {
        return peso;
    }

    // Define o peso associado à aresta.
    public void setPeso(float peso) {
        this.peso = peso;
    }

    // Obtém o vértice de origem associado à aresta.
    public Vertice<T> getOrigem() {
        return origem;
    }

    // Define o vértice de origem associado à aresta.
    public void setOrigem(Vertice<T> origem) {
        this.origem = origem;
    }

    // Obtém o vértice de destino associado à aresta.
    public Vertice<T> getDestino() {
        return destino;
    }

    // Define o vértice de destino associado à aresta.
    public void setDestino(Vertice<T> destino) {
        this.destino = destino;
    }

    // Sobrescreve o método toString para fornecer uma representação em string da aresta.
    @Override
    public String toString() {
        return origem.getValor() + " - " + destino.getValor() + "; peso: " + this.peso;
    }
}
