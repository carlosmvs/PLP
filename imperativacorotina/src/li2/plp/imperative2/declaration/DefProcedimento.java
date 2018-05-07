package li2.plp.imperative2.declaration;

import li2.plp.expressions1.util.Tipo;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative2.util.TipoProcedimento;

/**
 * Uma defini�ao de procedimento � uma declara�ao de comando e parametrosFormais
 * uma declara��o de procedimento.
 */
public class DefProcedimento {

	/**
	 * Declara��o dos parametrosFormais
	 */
	private ListaDeclaracaoParametro parametrosFormais;
	
	/**
	 * Indica se possui retorno
	 */
	private boolean retorna;

	/**
	 * Declara��o de Comando
	 */
	private Comando comando;

	/**
	 * Construtor
	 * 
	 * @param parametrosFormais
	 *            Declara��o de ListaDeclaracaoParametro
	 * @param comando
	 *            Declara��o de Comando.
	 */
	public DefProcedimento(ListaDeclaracaoParametro parametrosFormais,
			boolean retorna,
			Comando comando) {
		this.parametrosFormais = parametrosFormais;
		this.comando = comando;
		this.retorna = retorna;
	}

	/**
	 * Obt�m o comando do Procedimento.
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
	 * Obt�m os parametrosFormais do Procedimento.
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
