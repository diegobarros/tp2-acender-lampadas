/**
 * 
 */

/**
 * A interface ILampadaAcesa apresenta o método para 
 * resolver o problema das Lâmpadas Acesas</br>
 * @author Diego Augusto de Faria Barros
 *
 */
public interface ILampadaAcesa {
	
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
	public int minimizar(int[] ruas, String estadoInicial, String lampadaImportante);

} // Fim da interface ILampadaAcesa
