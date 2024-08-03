package tpa.routeoptimizer.lib;
import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import javax.swing.JTextArea;

public class Grafo<T> implements IGrafo<T> {
    private ArrayList<Vertice<T>> vertices;

    public Grafo() {
        this.vertices = new ArrayList<>();
    }

    // Adiciona um vértice ao grafo com o valor fornecido.
    public Vertice<T> adicionarVertice(T valor) {
        Vertice<T> novo = new Vertice<>(valor);
        this.vertices.add(novo);
        return novo;
    }

    public ArrayList<T> obterVertices() {
        ArrayList<T> nomesVertices = new ArrayList<>();
        for (Vertice<T> vertice : vertices) {
            nomesVertices.add(vertice.getValor());
        }
        return nomesVertices;
    }
    // Obtém um vértice do grafo pelo valor.
    public Vertice<T> obterVertice(T valor) {
        for (Vertice<T> v : this.vertices) {
            if (v.getValor().equals(valor)) {
                return v;
            }
        }
        return null;
    }

    // Adiciona uma aresta entre os vértices de origem e destino, com o peso fornecido.
    // Cria os vértices se não existirem.
    public void adicionarAresta(T origem, T destino, float peso) {
        Vertice<T> verticeOrigem = obterVertice(origem);

        if (verticeOrigem == null) {
            verticeOrigem = adicionarVertice(origem);
        }

        Vertice<T> verticeDestino = obterVertice(destino);

        if (verticeDestino == null) {
            verticeDestino = adicionarVertice(destino);
        }

        verticeOrigem.adicionarDestino(new Aresta<>(verticeDestino, peso));
    }

    // Realiza uma busca em largura no grafo a partir do primeiro vértice adicionado.
    public void buscaEmLargura() {
        ArrayList<Vertice<T>> marcados = new ArrayList<>();
        ArrayList<Vertice<T>> fila = new ArrayList<>();

        //Pega o primeiro vértice como ponto de partida e o coloca na fila
        //Poderia escolher qualquer outro
        //Mas note que dependendo do grafo pode ser que vc não caminhe por todos os vertices
        Vertice<T> atual = this.vertices.get(0);
        fila.add(atual);
        System.out.println("Busca em largura a partir do vértice: " + atual.getValor());

        //Enquanto houver vertice na fila ...
        while (!fila.isEmpty()) {
            //Pego o proximo da fila, marco como visitado e o imprimo
            atual = fila.get(0);
            fila.remove(0);
            marcados.add(atual);
            System.out.println(atual.getValor());

            //Depois pego a lista de adjacencia do no e se o no adjacente ainda não tiver sido visitado, o coloco na
            // fila
            // Itera sobre os destinos do vértice atual
            for (Aresta<T> destino : atual.getDestinos()) {
                Vertice<T> proximo = destino.getDestino();

                if (!marcados.contains(proximo) && !fila.contains(proximo)) {
                    fila.add(proximo);
                }
            }
        }
    }

    // Verifica se o grafo possui ciclos.
    public boolean temCiclo() {
        ArrayList<Vertice<T>> visitados = new ArrayList<>();
        ArrayList<Vertice<T>> pilhaRecursao = new ArrayList<>();

        for (Vertice<T> vertice : vertices) {
            if (!visitados.contains(vertice) && temCicloUtil(vertice, visitados, pilhaRecursao)) {
                return true;
            }
        }

        return false;
    }

    // Função auxiliar para verificar ciclos durante a busca em profundidade (DFS).
    private boolean temCicloUtil(Vertice<T> vertice, ArrayList<Vertice<T>> visitados, ArrayList<Vertice<T>> pilhaRecursao) {
        visitados.add(vertice);
        pilhaRecursao.add(vertice);

        for (Aresta<T> aresta : vertice.getDestinos()) {
            Vertice<T> destino = aresta.getDestino();

            if (!visitados.contains(destino)) {
                if (temCicloUtil(destino, visitados, pilhaRecursao)) {
                    return true;
                }
            } else if (pilhaRecursao.contains(destino)) {
                return true;
            }
        }

        pilhaRecursao.remove(vertice);
        return false;
    }

    // Executa uma ordenação topológica do grafo.
    public ArrayList<Vertice<T>> ordenacaoTopologica() {
        ArrayList<Vertice<T>> resultado = new ArrayList<>();
        Stack<Vertice<T>> pilha = new Stack<>();
        ArrayList<Vertice<T>> visitados = new ArrayList<>();

        for (Vertice<T> vertice : vertices) {
            if (!visitados.contains(vertice)) {
                ordenacaoTopologicaUtil(vertice, visitados, pilha);
            }
        }

        // A pilha agora contém a ordenação topológica inversa
        while (!pilha.isEmpty()) {
            resultado.add(pilha.pop());
        }

        return resultado;
    }

    // Função auxiliar para a ordenação topológica durante a busca em profundidade (DFS).
    private void ordenacaoTopologicaUtil(Vertice<T> vertice, ArrayList<Vertice<T>> visitados, Stack<Vertice<T>> pilha) {
        visitados.add(vertice);

        for (Aresta<T> aresta : vertice.getDestinos()) {
            Vertice<T> destino = aresta.getDestino();

            if (!visitados.contains(destino)) {
                ordenacaoTopologicaUtil(destino, visitados, pilha);
            }
        }

        pilha.push(vertice);
    }
    
    public String imprimirGrafo() {
        StringBuilder sb = new StringBuilder();
        
        for (Vertice<T> vertice : vertices) {
            // Adiciona o valor do vértice
            sb.append(vertice.getValor().toString()).append(":\t");
            
            // Adiciona as arestas e pesos
            ArrayList<Aresta<T>> destinos = vertice.getDestinos();
            for (Aresta<T> aresta : destinos) {
                sb.append(aresta.getDestino().getValor().toString())
                  .append(" (peso: ").append(aresta.getPeso()).append(")\t");
            }
            
            // Quebra de linha após cada vértice
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
public String calcularAGM() {
    // Cria uma lista de arestas sem duplicatas
    ArrayList<Aresta<T>> arestas = new ArrayList<>();

    // Coleta todas as arestas de todos os vértices
    for (Vertice<T> vertice : vertices) {
        for (Aresta<T> aresta : vertice.getDestinos()) {
            // Adiciona aresta à lista se ainda não foi adicionada
            if (arestas.stream().noneMatch(a ->
                (a.getDestino().equals(aresta.getDestino()) && a.getPeso() == aresta.getPeso()) ||
                (a.getDestino().equals(vertice) && a.getPeso() == aresta.getPeso())
            )) {
                arestas.add(aresta);
            }
        }
    }

    // Ordena as arestas pelo peso
    Collections.sort(arestas, Comparator.comparingDouble(Aresta::getPeso));

    // Inicializa o conjunto disjunto
    Map<Vertice<T>, Vertice<T>> pai = new HashMap<>();
    Map<Vertice<T>, Integer> rank = new HashMap<>();

    // Inicializa os conjuntos disjuntos para cada vértice
    for (Vertice<T> vertice : vertices) {
        pai.put(vertice, vertice);
        rank.put(vertice, 0);
    }

    // Calcula a árvore geradora mínima e constrói a string
    StringBuilder resultado = new StringBuilder();
    float somaTotal = 0;
    resultado.append("Árvore Geradora Mínima:\n");

    for (Aresta<T> aresta : arestas) {
        Vertice<T> origem = findOrigem(aresta, vertices);
        Vertice<T> destino = aresta.getDestino();

        Vertice<T> u = find(origem, pai);
        Vertice<T> v = find(destino, pai);

        if (!u.equals(v)) {
            resultado.append("Aresta: ")
                     .append(origem.getValor())
                     .append(" - ")
                     .append(destino.getValor())
                     .append(" - Peso: ")
                     .append(aresta.getPeso())
                     .append("\n");
            somaTotal += aresta.getPeso();
            union(u, v, pai, rank);
        }
    }

    resultado.append("Soma total dos pesos: ")
             .append(somaTotal)
             .append("\n");

    return resultado.toString();
}

// Função para determinar o vértice de origem baseado na aresta
private Vertice<T> findOrigem(Aresta<T> aresta, ArrayList<Vertice<T>> vertices) {
    for (Vertice<T> vertice : vertices) {
        for (Aresta<T> a : vertice.getDestinos()) {
            if (a.getDestino().equals(aresta.getDestino()) && a.getPeso() == aresta.getPeso()) {
                return vertice;
            }
        }
    }
    return null; // Retorne nulo ou lance uma exceção, se necessário
}
       // Função para encontrar o representante de um vértice
    private Vertice<T> find(Vertice<T> vertice, Map<Vertice<T>, Vertice<T>> pai) {
        if (!pai.get(vertice).equals(vertice)) {
            pai.put(vertice, find(pai.get(vertice), pai));
        }
        return pai.get(vertice);
    }

    // Função para unir dois conjuntos
    private void union(Vertice<T> u, Vertice<T> v, Map<Vertice<T>, Vertice<T>> pai, Map<Vertice<T>, Integer> rank) {
        Vertice<T> rootU = find(u, pai);
        Vertice<T> rootV = find(v, pai);

        if (rootU.equals(rootV)) return;

        if (rank.get(rootU) > rank.get(rootV)) {
            pai.put(rootV, rootU);
        } else if (rank.get(rootU) < rank.get(rootV)) {
            pai.put(rootU, rootV);
        } else {
            pai.put(rootV, rootU);
            rank.put(rootU, rank.get(rootU) + 1);
        }
    }
    
    public float calcDistance(T origem, T destino) {
        Vertice<T> verticeOrigem = obterVertice(origem);
        Vertice<T> verticeDestino = obterVertice(destino);

        if (verticeOrigem == null || verticeDestino == null) {
            throw new IllegalArgumentException("Uma ou ambas as cidades não existem no grafo.");
        }

        for (Aresta<T> aresta : verticeOrigem.getDestinos()) {
            if (aresta.getDestino().equals(verticeDestino)) {
                return aresta.getPeso();
            }
        }

        throw new IllegalArgumentException("Não existe uma aresta conectando as duas cidades.");
    }
    
    public void calcMinRoute(T origem, T destino, JTextArea resultField) {
        Vertice<T> verticeOrigem = obterVertice(origem);
        Vertice<T> verticeDestino = obterVertice(destino);

        if (verticeOrigem == null || verticeDestino == null) {
            throw new IllegalArgumentException("Uma ou ambas as cidades não existem no grafo.");
        }

        Map<Vertice<T>, Float> distancias = new HashMap<>();
        Map<Vertice<T>, Vertice<T>> antecessores = new HashMap<>();
        PriorityQueue<Vertice<T>> fila = new PriorityQueue<>(Comparator.comparing(distancias::get));

        for (Vertice<T> vertice : vertices) {
            distancias.put(vertice, Float.MAX_VALUE);
            antecessores.put(vertice, null);
        }

        distancias.put(verticeOrigem, 0f);
        fila.add(verticeOrigem);

        while (!fila.isEmpty()) {
            Vertice<T> atual = fila.poll();

            for (Aresta<T> aresta : atual.getDestinos()) {
                Vertice<T> vizinho = aresta.getDestino();
                float novaDistancia = distancias.get(atual) + aresta.getPeso();

                if (novaDistancia < distancias.get(vizinho)) {
                    distancias.put(vizinho, novaDistancia);
                    antecessores.put(vizinho, atual);
                    fila.add(vizinho);
                }
            }
        }

        ArrayList<T> caminho = new ArrayList<>();
        for (Vertice<T> vertice = verticeDestino; vertice != null; vertice = antecessores.get(vertice)) {
            caminho.add(vertice.getValor());
        }
        Collections.reverse(caminho);

        if (caminho.size() == 1 && !caminho.get(0).equals(origem)) {
            throw new IllegalArgumentException("Não há caminho entre as duas cidades.");
        }
              StringBuilder resultado = new StringBuilder();
                resultado.append("Caminho mínimo: ");
                for (T cidade : caminho) {
                    resultado.append(cidade).append(" -> ");
                }
                resultado.setLength(resultado.length() - 4);  // Remove o último " -> "
                resultado.append("\nDistância total: ").append(distancias.get(verticeDestino));

                resultField.setText(resultado.toString());
 
    }
    
        // Verifica se o grafo está vazio.
    public boolean checkIsEmpty() {
        return vertices.isEmpty();
    }
    
    
}