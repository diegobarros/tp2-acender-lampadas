import grafos.Grafo;


/**
 * Classe responsável por solucionar o problema das lampadas acesas
 * <\br>
 * @author Diego Augusto de Faria Barros
 *
 */
public abstract class LampadaAcesa implements ILampadaAcesa {
	
	private int[] ruas;
	
	private String estadoInicial;
	private String lampadaImportante;
	
	protected int numeroOperacoes;
	
	private Grafo grafo;
	private int numeroVertices;

	/**
	 * Construtor Padrão
	 */
	public LampadaAcesa() {		}
	
	
	/**
	 * Cria uma nova instancia de Lampada Acessa<\br>
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
	public LampadaAcesa(int[] ruas, String estadoInicial,
			String lampadaImportante) {
		
		Inicializa(ruas, estadoInicial, lampadaImportante);
		
	} // Fim do construtor


	
	/**
	 * Incializa as variáveis.<\br>
	 *
	 * @param    ruas               As ruas (arestas) da cidade M
	 * @param    estadoInicial      O estado inicial das lâmpadas<\br>
	 * @param    lampadaImportante  As lâmpadas que são importantes<\br>
	 *                   
	 * @return Retorna o número mínimo de operações necessárias para calcular o objetivo.
	**/
	private void Inicializa(int[] ruas, String estadoInicial, String lampadaImportante) {
		
		int numeroRuas = ruas.length;
		
		if ((numeroRuas >= 2) || (numeroRuas <= 50))
			this.ruas = ruas;
		else
			throw new IllegalArgumentException("O número de ruas deve estar entre 2 e 50 inclusive!");
		
		this.estadoInicial = estadoInicial;
		this.lampadaImportante = lampadaImportante;
		this.numeroOperacoes = 0;
		
		InicializaGrafo();
		
	} // Fim do método Inicializa
	
	
	
	/**
	 * @return Obtém o estado inicial das lâmpadas
	 */
	public String getEstadoInicial() { return estadoInicial; }


	/**
	 * @return Obtém as lâmpadas que são importantes
	 */
	public String getLampadaImportante() { return lampadaImportante; }


	/**
	 * @return Obtém o número de operações realizadas pelo algoritmo
	 */
	public int getNumeroOperacoes() { return numeroOperacoes; }


	/**
	 * @return Obtém o grafo que modela a cidade M
	 */
	public Grafo getGrafo() { return grafo; }


	/**
	 * Inicializa o grafo que modela as ruas da cidade M
	 */
	private void InicializaGrafo() {
		
		numeroVertices = ruas.length + 1;
		grafo = new Grafo(numeroVertices);
		
		for (int i = 0; i < numeroVertices - 1; i++) {
			
			int u = ruas[i];
			int v = (i + 1);
			
			grafo.AdicionaAresta(u, v);
			
		} // Fim for int i = 0
		
	} // Fim do método InicializaGrafo
	


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n");
		
		stringBuilder.append("{ ");
		
		for (int rua : ruas)
			stringBuilder.append(rua + ", ");
		
		stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(", "));
		stringBuilder.append("}");
		stringBuilder.append("\n");
		
		stringBuilder.append(estadoInicial);
		stringBuilder.append("\n");
		
		stringBuilder.append(lampadaImportante);
		stringBuilder.append("\n");
		
		return stringBuilder.toString();
		
	} // Fim do método toString()


	/* (non-Javadoc)
	 * @see ILampadaAcesa#minimizar(int, java.lang.String, java.lang.String)
	 */
	@Override
	public int minimizar(int[] ruas, String estadoInicial, String lampadaImportante) {
		Inicializa(ruas, estadoInicial, lampadaImportante);
		return 0;
	}

} // Fim da classe LampadaAcesa
