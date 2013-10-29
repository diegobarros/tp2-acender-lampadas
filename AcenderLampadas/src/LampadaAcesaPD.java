import grafos.algoritmos.BuscaEmProfundidade;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Resolve o problema através do paradigma de Programação Dinâmica
 * @author Diego Augusto de Faria Barros
 */
public class LampadaAcesaPD extends LampadaAcesa {
	
	private boolean[] marcado; 		// marcado[u] = existe um caminho s - u
	private int[] arestaPara;		// arestaPara[u] = última aresta no caminho s-u
	private int noFonte;			// Vértice fonte	
	private int contador;			// Número de vértices conectados à fonte

	private int[][] tabelaPD;
	private boolean[][] intersecoesVisitadas; 
	

	/**
	 * Contrutor Padrão
	 */
	public LampadaAcesaPD() {	}

	/**
	 * Liga todas as lâmpadas importantes ao mesmo tempo (Não ligando para o estado das lâmpadas restantes).<\br>
	 *
	 * @param    ruas               As ruas (arestas) da cidade M
	 * @param    estadoInicial      O estado inicial das lâmpadas, onde:
	 *                              <\br>
	 *                              estadoInicial [ i ] = '1' significa que a lâmpada i está inicialmente ligada
	 *                              estadoInicial [ i ] = '0' ' significa que a lâmpada i está inicialmente desligada<\br>
	 * @param    lampadaImportante  As lâmpadas que são importantes, onde:
	 *                              
	 *                              lampadaImportantel [ i ] = '1' significa que a lâmpada i é importante
	 *                              lampadaImportantel [ i ] = '0' ' significa que a lâmpada i não é importante
	 *                              <\br>
	 *                              
	 * @return Retorna o número mínimo de operações necessárias para calcular o objetivo.
	**/
	public LampadaAcesaPD(int[] ruas, String estadoInicial, String lampadaImportante) {
		super(ruas, estadoInicial, lampadaImportante);
		
	}
	
   @Override
   public int minimizar(int[] ruas, String estadoInicial, String lampadaImportante) {
	   super.minimizar(ruas, estadoInicial, lampadaImportante);
	   
	   AlgoritmoProgramacaoDinamica();
	   
	   return numeroOperacoes;
	
   } // Fim do método minimizar
   
   
   
   private void ObtemCaminhos(int u) {

		contador++;
		marcado[u] = true;
		
		for (int v : grafo.AdjacenciasInt(u)) {
			
			if(!marcado[v]) {
				
				arestaPara[v] = u;
				
				if (u != v) {
					tabelaPD[v][u] = 1;
					intersecoesVisitadas[v][u] = false;
				}
					
				
				ObtemCaminhos(v);
			
			} // Fim de if
			
		} // Fim de foreach
	   
   } // Fim do método ObtemCaminhos
   
   
  /**
   * Algoritmo de programação dinâmica para solucionar o problema 
   */
   private void AlgoritmoProgramacaoDinamica() {
	   
	    noFonte = 0;
	   	arestaPara = new int[grafo.getNumeroDeVertices()];
		marcado = new boolean[grafo.getNumeroDeVertices()];
		tabelaPD = new int[grafo.getNumeroDeVertices()][grafo.getNumeroDeVertices()];
		intersecoesVisitadas = new boolean[grafo.getNumeroDeVertices()][grafo.getNumeroDeVertices()];
		
		ObtemCaminhos(noFonte);
		System.out.println(getEstadoInicial());
		
		while (!TodasLampadasImportantesAcesas()) {
			
			int[] caminho = EncontraMelhorCaminho();
			AcenderLampada(caminho);
			numeroOperacoes++;
			
			System.out.println(Arrays.toString(caminho));
			System.out.println(getLampadaImportante());
			System.out.println(getEstadoInicial());
			 

		} // Fim de while
	
		/*
		System.out.println(" 0 1 2 3 4 5 6");
		
		for (int i = 0; i < tabelaPD.length; i++) {
			
			System.out.print( i + " ");
			
			for (int j = 0; j < tabelaPD[i].length; j++) {
				
				if (i > j)
					System.out.print(" " + tabelaPD[i][j]);
			}
			System.out.println();
		}
	 

		for (int i = 6; i < 7; i++) {
			
			BuscaEmProfundidade buscaProfundidade = new BuscaEmProfundidade(grafo, i);
			
			
			for (int u = 0; u < grafo.getNumeroDeVertices(); u++) {
				
				if (buscaProfundidade.ExisteCaminhoPara(u)) {
					
					System.out.print(String.format("%d para %d:  ", i, u));
					
					for (int v : buscaProfundidade.CaminhoPara(u)) {
						if (v == 0)
							System.out.print(" " + v);
						else
							System.out.print("-" + v);
					}
					
					System.out.println();
					
					
				} else {
					
					System.out.println(String.format("%d para %d (-): Não Conectado\n",  i, u));

				}
				
			}

		}*/
		
	
	   
   } // Fim do método AlgoritmoProgramacaoDinamica
   
   
   /**
	 * Obtém o caminho que possui o maior número de lâmpadas importantes desligadas
	 * @return Um vetor contendo o caminho
	 */
	private int[] EncontraMelhorCaminho() {
		
		Caminho solucao = null;
		ArrayList<Integer> caminho = new ArrayList<Integer>();
		boolean caminhoEncontrado = false;

		
		int coluna = tabelaPD[tabelaPD.length - 1].length - 2;
		int linha = tabelaPD[0].length - 1;
		
		int[] noInicio = EscolheNoInicio(linha, coluna);
		
		linha = noInicio[0];
		coluna = noInicio[1];

		
		while (!caminhoEncontrado) {

			for (int j = coluna; j >= 0; j--) {
			
				if (linha > j && tabelaPD[linha][j] == 1) {
					
					
					// SE o caminho passa por uma rua importante e a lâmpada estiver apgada ENTÃO|| getLampadaImportante().charAt(linha - 1) == '1'
					if (getLampadaImportante().charAt(linha - 1) == '1') {
						
						if (!IntersecaoVisitada(linha, j)) {
							
							intersecoesVisitadas[linha][j] = true;
							
							if (!caminho.contains(linha) || !caminho.contains(j)){
								caminho.add(linha);
								caminho.add(j);
							}
							
							System.out.println(linha + " " + j);
							linha = j;
							coluna = linha - 1;
							j = coluna;
							
						} else {
							
							linha--;
							
							if (!caminho.contains(linha) || !caminho.contains(j)){
								caminho.add(linha);
								caminho.add(j);
							}
							
							System.out.println(linha + " " + j);
							linha = j;
							coluna = linha - 1;
							j = coluna;
							
						} // Fim de if/else

						
					} // Fim de if

				} // Fim de if
				
			} // Fim for int j = coluna
			
			linha--;
			
			if (linha < 0) {
				
				linha = tabelaPD[0].length - 1;
				coluna = 0;
				
				for (int i = 0; i < linha; i++) {
					
					if (tabelaPD[i][coluna] == 1) {
	
						if (!IntersecaoVisitada(i, coluna)) {
							
							intersecoesVisitadas[i][coluna] = true;
							
							// SE o caminho passa por uma rua importante e a lâmpada estiver apgada ENTÃO ) && (getEstadoInicial().charAt(linha - 1) == '0')|| getLampadaImportante().charAt(linha - 1) == '1'
							if (getLampadaImportante().charAt(linha - 1) == '1' ) {
								
								if (!caminho.contains(coluna) || !caminho.contains(i)){
									caminho.add(coluna);
									caminho.add(i);
								}
							}
							
						} // Fim de if Interseção Não visitada

					} // Fim if tabela[i][j] == 1
				
				} // Fim for int i = 0
				
				caminhoEncontrado = true;
				
			} // Fim linha < 0 
			
		} // Fim de while caminhoNaoEncontrado
		
		int[] oCaminho = new int[caminho.size()];
		
		for (int i = 0; i < oCaminho.length; i++)
			oCaminho[i] = caminho.get(i);
		
		
		int[] caminhoLimpo = LimpaCaminho(oCaminho);
		
		solucao = new Caminho(caminhoLimpo[0], caminhoLimpo[caminhoLimpo.length - 1],  caminhoLimpo, caminhoLimpo.length);
		
		return solucao.getCaminho();
		
	} // Fim do método EncontraMelhorCaminho
	
	
	/**
	 * Verifica se a interseção já foi visitada
	 * @param linha A linha da matriz
	 * @param coluna A coluna da matriz
	 * @return True se a interseção já foi visitada, caso contrário, retorna false
	 */
	private boolean IntersecaoVisitada(int linha, int coluna) {
		
		return intersecoesVisitadas[linha][coluna];
		
	} // Fim do método IntersecaoVisitada
	
   
	/**
	 * Escolhe o nó de início da árvore para realizar a pesquisa dos caminhos
	 * @param linha Linha da matriz
	 * @param coluna Coluna da matriz
	 * @return Um vetor contendo a aresta de início do caminho
	 */
	private int[] EscolheNoInicio(int linha, int coluna) {
		
		int[] solucao = new int[2];
		
		for (int i = linha; i >= 0; i--) {
			
			for (int j = linha - 1; j > 0; j--) {
				
				if ((getLampadaImportante().charAt(i - 1) == '1') && (getEstadoInicial().charAt(i - 1) == '0')) {
					
					solucao[0] = i;
					solucao[1] = j;
					
					return solucao;
					
				} // Fim de if 

			} // Fim for int j = linha - 1

		} // Fim for int i = linha
		
		return solucao;

	} // Fim do método EscolheNoInicio
	
	
	/**
	 * Dado um caminho, elimina as intrseção
	 * @param caminho
	 * @return
	 */
	private int[] LimpaCaminho(int[] caminho) {
		
		ArrayList<Integer> caminhoLimpo = new ArrayList<Integer>();
		int[] caminhoAtual = caminho;

		
		for (int i = 0; i < caminhoAtual.length; i++)
			if (!caminhoLimpo.contains(caminhoAtual[i]))
				caminhoLimpo.add(caminhoAtual[i]);
			
		int[] solucao = new int[caminhoLimpo.size()];
		
		
		for (int j = 0; j < caminhoLimpo.size(); j++)
			solucao[j] = caminhoLimpo.get(j);

		return solucao;

	} // Fim do método LImpaCaminho

} // Fim da classe LampadaAcesaPD
