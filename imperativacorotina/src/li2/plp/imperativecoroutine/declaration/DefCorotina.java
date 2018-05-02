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
	 * Declaracao de Comando
	 */
	private Comando comandoCorotina;
	
	public DefCorotina(ListaDeclaracaoParametro parametrosFormais,
			Comando comandoCorotina) {
		this.parametrosFormais = parametrosFormais;
		this.comandoCorotina = comandoCorotina;
	}

	public ListaDeclaracaoParametro getParametrosFormais() {
		return parametrosFormais;
	}

	public Comando getComandoCorotina() {
		return comandoCorotina;
	}
	
	public Tipo getTipo() {
		return new TipoCorotina(parametrosFormais.getTipos());
	}
}
