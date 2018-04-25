package li2.plp.imperativecoroutine.declaration;

import li2.plp.imperative2.declaration.ListaDeclaracaoParametro;
import li2.plp.imperativecoroutine.command.ComandoCorotina;

public class DefCorotina {
	
	/**
	 * Declaracao dos parametrosFormais
	 */
	private ListaDeclaracaoParametro parametrosFormais;

	/**
	 * Declaracao de Comando
	 */
	private ComandoCorotina comandoCorotina;
	
	public DefCorotina(ListaDeclaracaoParametro parametrosFormais,
			ComandoCorotina comandoCorotina) {
		this.parametrosFormais = parametrosFormais;
		this.comandoCorotina = comandoCorotina;
	}
	
	

}
