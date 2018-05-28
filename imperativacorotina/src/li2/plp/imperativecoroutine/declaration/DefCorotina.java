package li2.plp.imperativecoroutine.declaration;

import li2.plp.expressions1.util.Tipo;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative2.declaration.ListaDeclaracaoParametro;
import li2.plp.imperativecoroutine.coroutine.TipoCorotina;

public class DefCorotina extends DefRotina{
	
	/**
	 * Indica quantos comando yields existem no corpo da rotina
	 */
	private int qtdYields;
	
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
	public DefCorotina(ListaDeclaracaoParametro parametrosFormais, Tipo tipoRetorno, Comando comando) {
		super(parametrosFormais, tipoRetorno, comando);
		this.qtdYields = 0;
	}
	
	/**
	 * Retorna quantidade de comandos yield declarados.
	 * 
	 * @return tipoRetorno
	 */
	public int getQtdYields() {
		return qtdYields;
	}

	public void setQtdYields(int qtdYields) {
		this.qtdYields = qtdYields;
	}

	public Tipo getTipo() {
		return new TipoCorotina(parametrosFormais.getTipos(), this.tipoRetorno);
	}
}
