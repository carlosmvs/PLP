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
	private Tipo tipoRetorno;

	/**
	 * Declaração de Comando
	 */
	private Comando comando;

	/**
	 * Construtor
	 * 
	 * @param parametrosFormais
	 *            Declaração de ListaDeclaracaoParametro
	 * @param tipoRetorno
	 * 			  Tipo de Retorno
	 * @param comando
	 *            Declaração de Comando.
	 */
	public DefProcedimento(ListaDeclaracaoParametro parametrosFormais,
			Tipo tipoRetorno,
			Comando comando) {
		this.parametrosFormais = parametrosFormais;
		this.comando = comando;
		this.tipoRetorno = tipoRetorno;
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
	 * Retorna o tipo de retorno do procedimento.
	 * 
	 * @return tipoRetorno
	 */
	public Tipo getTipoRetorno() {
		return tipoRetorno;
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
		return new TipoProcedimento(parametrosFormais.getTipos(), this.tipoRetorno);
	}
}
