import java.util.Arrays;
import java.util.Stack;


/**
 * Representa um caminho das ruas da cidade
 * @author Diego Augusto de Faria Barros
 */
public class Caminho implements Comparable<Caminho> {

	private int inicio;
	private int fim;
	private int[] caminho;
	int tamanho;
	
	
	/**
	 * Cria uma nova instancia de caminho
	 * @param inicio Interseção (A) de início
	 * @param fim Interseção (B) de início
	 * @param caminho ruas que compõe este caminho 
	 * @param tamanho Quantidade de ruas (arestas) que compõe o caminho
	 */
	public Caminho(int inicio, int fim, int[] caminho, int tamanho) {
		
		this.inicio = inicio;
		this.fim = fim;
		this.tamanho = tamanho;
		
		int[] caminhoInvertido = new int[caminho.length];
		Stack<Integer> pilha = new Stack<Integer>();
		
		
		for (int i = 0; i < caminho.length; i++)
			pilha.push(caminho[i]);
		
		
		int j = 0;
		
		while(pilha.size() > 0) {
			caminhoInvertido[j] = pilha.pop();
			j++;
		}
		
		this.caminho = caminhoInvertido;

	} // Fim do construtor


	/**
	 * @return A interseção (vértice) de início do caminho
	 */
	public int getInicio() { return inicio; }


	/**
	 * @return A interseção (vértice) de fim do caminho
	 */
	public int getFim() { return fim; }


	/**
	 * @return Um vetor de inteiros contendo as ruas que compõe o caminho
	 */
	public int[] getCaminho() { return caminho; }


	/**
	 * @return O tamanho do Caminho
	 */
	public int getTamanho() { return tamanho; }
	
	


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Caminho [Início =" + inicio + ", Fim = " + fim + ", Caminho = "
				+ Arrays.toString(caminho) + ", Tamanho = " + tamanho + "]";
	}


	@Override
	public int compareTo(Caminho caminho) {
		if (this.tamanho < caminho.tamanho)
			return -1;
		else if (this.tamanho > caminho.tamanho)
			return +1;
		else
			return 0;
	}
	
	
	
} // Fim do método Caminho
