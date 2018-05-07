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
	private boolean retorna;

	/**
	 * Declaracao de Comando
	 */
	private Comando comandoCorotina;
	
	public DefCorotina(ListaDeclaracaoParametro parametrosFormais,
			boolean retorna,
			Comando comandoCorotina) {
		this.parametrosFormais = parametrosFormais;
		this.retorna = retorna;
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
	 * Retorna true se a co-rotina retorna um valor.
	 * 
	 * @return retorna
	 */
	public boolean retorna() {
		return retorna;
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
		return new TipoCorotina(parametrosFormais.getTipos());
	}
}
