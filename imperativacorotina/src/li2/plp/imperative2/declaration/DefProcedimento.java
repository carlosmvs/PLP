package li2.plp.imperative2.declaration;

import li2.plp.expressions1.util.Tipo;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative2.util.TipoProcedimento;

/**
 * Uma definiï¿½ao de procedimento ï¿½ uma declaraï¿½ao de comando e parametrosFormais
 * uma declaraï¿½ï¿½o de procedimento.
 */
public class DefProcedimento {

	/**
	 * Declaração dos parametrosFormais
	 */
	private ListaDeclaracaoParametro parametrosFormais;
	
	/**
	 * Indica se possui retorno
	 */
	private boolean retorna;

	/**
	 * Declaração de Comando
	 */
	private Comando comando;

	/**
	 * Construtor
	 * 
	 * @param parametrosFormais
	 *            Declaração de ListaDeclaracaoParametro
	 * @param comando
	 *            Declaração de Comando.
	 */
	public DefProcedimento(ListaDeclaracaoParametro parametrosFormais,
			boolean retorna,
			Comando comando) {
		this.parametrosFormais = parametrosFormais;
		this.comando = comando;
		this.retorna = retorna;
	}

	/**
	 * Obtém o comando do Procedimento.
	 * 
	 * @return o comando
	 */
	public Comando getComando() {
		return comando;
	}
	
	/**
	 * Retorna true se o procedimento retorna um valor.
	 * 
	 * @return retorna
	 */
	public boolean retorna() {
		return retorna;
	}

	/**
	 * Obtém os parametrosFormais do Procedimento.
	 * 
	 * @return a ListaDeclaracaoParametro
	 */
	public ListaDeclaracaoParametro getParametrosFormais() {
		return parametrosFormais;
	}

	public Tipo getTipo() {
		return new TipoProcedimento(parametrosFormais.getTipos());
	}
}
