package li2.plp.imperativecoroutine.declaration;

import li2.plp.expressions1.util.Tipo;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative2.declaration.ListaDeclaracaoParametro;
import li2.plp.imperativecoroutine.coroutine.TipoCorotina;

public class DefCorotina {
	
	/**
	 * Declaracao dos parametrosFormais
	 */
	private ListaDeclaracaoParametro parametrosFormais;
	
	/**
	 * Indica se possui retorno
	 */
	private Tipo tipoRetorno;

	/**
	 * Declaracao de Comando
	 */
	private Comando comandoCorotina;
	
	public DefCorotina(ListaDeclaracaoParametro parametrosFormais,
			Tipo tipoRetorno,
			Comando comandoCorotina) {
		this.parametrosFormais = parametrosFormais;
		this.tipoRetorno = tipoRetorno;
		this.comandoCorotina = comandoCorotina;
	}
	
	/**
	 * Obtém os parametrosFormais da Co-rotina.
	 * 
	 * @return a ListaDeclaracaoParametro
	 */
	public ListaDeclaracaoParametro getParametrosFormais() {
		return parametrosFormais;
	}
	
	/**
	 * Retorna o tipo de retorno da co-rotina.
	 * 
	 * @return retorna
	 */
	public Tipo getTipoRetorno() {
		return tipoRetorno;
	}
	
	/**
	 * Obtém o comando da co-rotina.
	 * 
	 * @return o comando
	 */
	public Comando getComandoCorotina() {
		return comandoCorotina;
	}
	
	public Tipo getTipo() {
		return new TipoCorotina(parametrosFormais.getTipos(), this.tipoRetorno);
	}
}
