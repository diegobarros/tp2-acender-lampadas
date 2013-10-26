
/**
 * Resolve o problema através do paradigma de Programação Dinâmica
 * @author Diego Augusto de Faria Barros
 */
public class LampadaAcesaPD extends LampadaAcesa {

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
	   
	return numeroOperacoes;
	
   } // Fim do método minimizar

} // Fim da classe LampadaAcesaPD
