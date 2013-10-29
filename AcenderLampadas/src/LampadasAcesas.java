import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Classe Principal
 * @author Diego Augusto de Faria Barros
 */
public class LampadasAcesas {
	
	static LampadaAcesaGuloso lampadaAcesaGuloso;
	static LampadaAcesaPD lampadaAcesaPD;
	static int resultadoGuloso = 0;
	static int resultadoPD = 0;
	
	static int[] ruas;
	static String estadoInicial;
	static String lampadaImportante;
	
	static File arquivoTeste;
	
	static Calendar calendario = Calendar.getInstance();
	static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	
	/**
	 * Inicializa os dados de entrada do programa
	 */
	static void Inicializa(String[] parametros) {
		
		arquivoTeste = new File(parametros[0]);
		String casoDeTeste = LerArquivo(arquivoTeste);
		String[] camposCasoDeTeste = casoDeTeste.split("\n");
		
		String arestas = camposCasoDeTeste[0].substring(1, camposCasoDeTeste[0].length() - 1);
		String[] ruasCidade = arestas.split(", ");
		ruas = new int[ruasCidade.length];
		
		
		for (int i = 0; i < ruasCidade.length; i++)
			ruas[i] = (int) Integer.parseInt(ruasCidade[i]);
		
		
		estadoInicial = camposCasoDeTeste[1];
		lampadaImportante = camposCasoDeTeste[2];
		
		
		lampadaAcesaGuloso = new LampadaAcesaGuloso(ruas, estadoInicial, lampadaImportante);
		lampadaAcesaPD = new LampadaAcesaPD(ruas, estadoInicial, lampadaImportante);
		
	} // Fim do método Inicializa
	
	
	/**
	 * @param arquivo Arquivo de entrada
	 * @return Uma string com o conteúdo do arquivo
	 */
	static String LerArquivo(File arquivo) { 
		
		StringBuilder conteudo = new StringBuilder();
		
		try {
			
			BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
			String linha = leitor.readLine();
			
			while (linha != null) {
				
				if(!linha.equals("")) {
					conteudo.append(linha);
					conteudo.append("\n");
				}
				
				linha = leitor.readLine();
				
			} // Fim de while
			
			leitor.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conteudo.toString();
		
	} // Fim do método LerArquivo
	
	
	/**
	 * Grava um novo arquivo no diretório do sistema
	 * @param arquivo Diretório onde o arquivo será gravado
	 * @param conteudo Conteúdo textual do arquivo
	 */
	static void SalvarArquivo(File arquivo, String conteudo) {
		
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo));
			escritor.write(conteudo);
			escritor.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} // Fim do método SalvarArquivo
	
	/**
	 * Grava um novo arquivo no diretório do sistema
	 * @param arquivo Diretório onde o arquivo será gravado
	 * @param conteudo Conteúdo textual do arquivo
	 * @param acrescentarConteudo true para que o conteúdo ser anexado ao arquivo
	 */
	static void SalvarArquivo(File arquivo, String conteudo, boolean acrescentarConteudo) {
		
		try {
			
			BufferedWriter escritor;
			
			if(acrescentarConteudo)
				escritor = new BufferedWriter(new FileWriter(arquivo,true));
			else
				escritor = new BufferedWriter(new FileWriter(arquivo));
			
			escritor.write(conteudo);
			escritor.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} // Fim do método SalvarArquivo
	
	
	
	static void ImprimeCabeçalhoPrograma() {
		
		System.out.println("\n");
		System.out.println("╔══════════════════════════════════════════════════════════════════╗");
		System.out.println("║            	Universidade Federal de Minas Gerais               ║");
		System.out.println("║   	Programa de Pós-Graduação em Ciência da Computação         ║");
		System.out.println("║                Mestrado em Ciência da Computação                 ║");
		System.out.println("║                                                                  ║");
		System.out.println("║         TP2 - Lâmpadas Acesas                                    ║");
		System.out.println("║                                                                  ║");
		System.out.println("║   Disciplina: Projeto e Análise de Algoritmos                    ║");
		System.out.println("║  Professores: Luiz Chaimowicz, Wagner Meira,                     ║");
		System.out.println("║                  Gisele Pappa, Jussara Almeida                   ║");
		System.out.println("║                                                                  ║");
		System.out.println("║         Nome: Diego Augusto de Faria Barros                      ║");
		System.out.println("║                                                                  ║");
		System.out.println("║               Belo Horizonte, 01 de Novembro de 2013             ║");
		System.out.println("╚══════════════════════════════════════════════════════════════════╝");
		System.out.println("\n\n");
		
	} // Fim do método ImprimeCabecalhoPrograma
	
	
	
	/**
	 * Obtém informações da máquina que está executando o programa
	 */
	static void ImprimeInformacoesMaquina() {
		
		String nomeSO = System.getProperty("os.name");
		System.out.println("\nSistema Operacional: " + nomeSO);
		
		String tipoSO = System.getProperty("os.arch");
		System.out.println("Arquitetura: " + tipoSO);
		
		String versaoSO = System.getProperty("os.version");
		System.out.println("Versão do S.O: " + versaoSO);
		
        
        System.out.println("Processadores Disponíveis (Núcleos): " +   
                Runtime.getRuntime().availableProcessors());  
          
		
	} // Fim do método ImprimeInformacoesMaquina
	
	
	
	/**
	 * Calcula o tempo de execução do algoritmo
	 * @param tempoInicio Tempo de Início
	 * @param tempoFim Tempo do fim da execução do código
	 * @return O tempo em milissegundos de execução do algoritmo
	 */
	static long TempoExecucao(long tempoInicio, long tempoFim) {
		return tempoFim - tempoInicio;
	} // Fim do método TempoExecucao
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ImprimeCabeçalhoPrograma();
		Inicializa(args);
		
		System.out.println("\n[" + sdf.format(calendario.getTime()) + "] " + "Executando . . . \n");

		System.out.println("\nArquivo de Teste: " + arquivoTeste.getName());
		System.out.println("Dados de Entrada:");
		System.out.println(lampadaAcesaGuloso.toString());
		
		System.out.println("\nGRAFO: Lista de Adjacências: ");
		System.out.println(lampadaAcesaGuloso.getGrafo().ListaAdjInt());
	
		
		
		long tempoInicioGuloso = System.currentTimeMillis();
		resultadoGuloso = lampadaAcesaGuloso.minimizar(ruas, estadoInicial, lampadaImportante);
		long tempoFimGuloso = System.currentTimeMillis();
		
		System.out.println("\nAlgoritmo Guloso");
		System.out.println("Resultado: " + lampadaAcesaGuloso.getNumeroOperacoes());
		System.out.println("TEMPO: " + TempoExecucao(tempoInicioGuloso, tempoFimGuloso) + " ms");

		long tempoInicioPD = System.currentTimeMillis();
		resultadoGuloso = lampadaAcesaPD.minimizar(ruas, estadoInicial, lampadaImportante);
		long tempoFimPD = System.currentTimeMillis();
		
		System.out.println("\nAlgoritmo de Programação Dinâmica");
		System.out.println("Resultado: " + lampadaAcesaPD.getNumeroOperacoes());
		System.out.println("TEMPO: " + TempoExecucao(tempoInicioPD, tempoFimPD) + " ms");
		
		
		System.out.println();
		ImprimeInformacoesMaquina();

	} // Fim do método Main

} // Fim da classe LampadasAcesas


/*
 ************************************************************************
 *            	Universidade Federal de Minas Gerais	     	        *
 *         Programa de Pós-Graduação em Ciência da Computação           *
 *                Mestrado em Ciência da Computação                     *
 *                                                                      *								
 *    Trabalho: TP2: Acender Lâmpadas						 			*
 *  																	*                                                                      
 * 	Disciplina: Projeto e Análise de Algoritmos                         *
 * Professores: Luiz Chaimowicz, Wagner Meira, 							*
 * 				Gisele Pappa, Jussara Almeida                           *
 *    			  														*
 * 	      Nome: Diego Augusto de Faria Barros                           *
 *   																	*
 *                                                                      *
 *              Belo Horizonte, 01 de Novembro de 2013	  	            *
 ************************************************************************
*/