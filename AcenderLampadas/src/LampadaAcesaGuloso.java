import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import grafos.algoritmos.BuscaEmLargura;
import grafos.algoritmos.BuscaEmProfundidade;


/**
 * Resolve o problema através do paradigma de programação Guloso
 * @author Diego Augusto de Faria Barros
 */
public class LampadaAcesaGuloso extends LampadaAcesa {
	
	private ArrayList<Caminho> caminhos = new ArrayList<Caminho>();
	

	/**
	 * Contrutor Padrão
	 */
	public LampadaAcesaGuloso() {	}

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
	public LampadaAcesaGuloso(int[] ruas, String estadoInicial, String lampadaImportante) {
		super(ruas, estadoInicial, lampadaImportante);
		
	}
	
	@Override
    public int minimizar(int[] ruas, String estadoInicial, String lampadaImportante) {
	  
		super.minimizar(ruas, estadoInicial, lampadaImportante);
		AlgoritmoGuloso();
	   
	return numeroOperacoes;
	
   } // Fim do método minimizar
	
	
	
	/**
	 * Algoritmo Guloso para solução do problema
	 */
	private void AlgoritmoGuloso() {
		
		ArrayList solucao = new ArrayList();
		
		ObtemCaminhos();
		// Escolhe caminho Ótimo [EncontraMelhorCaminho();]
		// Acende as Luzes
		
		// Se (Não Acendeu todas as luzes importantes) então
		numeroOperacoes++;
		
		
		int[] path = { 2, 0, 1, 4, 5 };
		
		int[] path2 = { 3, 1, 4, 6 };
		
		System.out.println(TodasLampasImportantesAcesas());
		System.out.println(getEstadoInicial());
		AcenderLampada(path);
		System.out.println(getEstadoInicial());
		
		AcenderLampada(path2);
		System.out.println(getEstadoInicial());
		
		System.out.println(TodasLampasImportantesAcesas());
		
	} // Fim do método AlgoritmoGuloso
	
	
	
	/**
	 * Obtém o caminho que possui o maior número de lâmpadas importantes desligadas
	 * @return Um vetor contendo o caminho
	 */
	private int[] EncontraMelhorCaminho() {
		
		return null;
		
	} // Fim do método EncontraMelhorCaminho
	
	private void ObtemCaminhos() {
		
		
		LinkedList<Integer> ruasCaminho = new LinkedList<Integer>();
		
		int inicio;
		int fim;
		int tamanho;
		
		
		for (int i = 0; i < grafo.getNumeroDeVertices(); i++) {
			
			BuscaEmLargura buscaLargura = new BuscaEmLargura(grafo, i);

			for (int u = 0; u < grafo.getNumeroDeVertices(); u++) {
				
				if (buscaLargura.ExisteCaminhoPara(u)) {
					
					ruasCaminho = new LinkedList<Integer>();
					
					
					for (int v : buscaLargura.CaminhoPara(u))
						ruasCaminho.add(v);

					
					inicio = i;
					fim = u;
					tamanho = buscaLargura.DistanciaPara(u);
					
					int[] caminho = new int[ruasCaminho.size()];
					
					for (int j = 0; j < ruasCaminho.size(); j++)
						caminho[j] = ruasCaminho.get(j);
					
					caminhos.add(new Caminho(inicio, fim, caminho, tamanho));
					

				} else {   
					System.out.println(String.format("%d para %d (-): Não Conectado\n", i, u));
				}
				
			} // Fim for int u  = 0

		} // Fim for int i = 0 
		
		
		System.out.println("\nCaminhos");
		Caminho[] caminhoOrdenado = caminhos.toArray(new Caminho[caminhos.size()]); 

		QuickSort.Ordena(caminhoOrdenado);
		caminhos.clear();
		
		for (int i = 0; i < caminhoOrdenado.length; i++)
			caminhos.add(caminhoOrdenado[i]);
		
		
		for (Caminho caminho : caminhos) {
			System.out.println(caminho);
		}

		
	} // Fim do método ObtemCaminho
	
	
} // Fim da classe LampadaAcesaGuloso
