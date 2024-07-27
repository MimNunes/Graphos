package tpa.routeoptimizer.lib;
import java.util.ArrayList;

public interface IGrafo<T> {

  /**
   * Adiciona um vértice ao grafo com o valor fornecido.
   *
   * @param valor O valor do vértice a ser adicionado.
   * @return O vértice recém-adicionado.
   */
  Vertice<T> adicionarVertice(T valor);

  /**
   * Adiciona uma aresta ao grafo entre os vértices de origem e destino, com o peso fornecido.
   * Se os vértices de origem ou destino não existirem, eles são criados.
   *
   * @param origem  O valor do vértice de origem.
   * @param destino O valor do vértice de destino.
   * @param peso    O peso da aresta.
   */
  void adicionarAresta(T origem, T destino, float peso);

  /**
   * Realiza uma busca em largura no grafo a partir do primeiro vértice adicionado.
   */
  void buscaEmLargura();

  /**
   * Verifica se o grafo possui ciclos.
   *
   * @return true se o grafo contiver ciclos, false caso contrário.
   */
  boolean temCiclo();

  /**
   * Executa uma ordenação topológica do grafo.
   *
   * @return Uma lista de vértices representando a ordenação topológica do grafo.
   */
  ArrayList<Vertice<T>> ordenacaoTopologica();
}