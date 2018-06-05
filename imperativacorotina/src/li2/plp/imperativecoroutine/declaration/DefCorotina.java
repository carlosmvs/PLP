package li2.plp.imperativecoroutine.declaration;

import li2.plp.expressions1.util.Tipo;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative2.declaration.ListaDeclaracaoParametro;
import li2.plp.imperativecoroutine.coroutine.Coroutine;
import li2.plp.imperativecoroutine.util.TipoCorotina;

public class DefCorotina extends DefRotina{
	
	/**
	 * Indica quantos comando yields existem no corpo da rotina
	 */
	private int qtdYields;
	
	/**
	 * Representa a instância da implementação em Java de co-rotinas
	 */
	private Coroutine corotina;
	
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
	 * @return qtdYields
	 */
	public int getQtdYields() {
		return qtdYields;
	}

	public void setQtdYields(int qtdYields) {
		this.qtdYields = qtdYields;
	}
	
	/**
	 * Retorna implementação em Java da co-rotina.
	 * 
	 * @return corotina
	 */
	public Coroutine getCorotina() {
		return corotina;
	}
	
	public void setCorotina(Coroutine corotina) {
		this.corotina = corotina;
	}

	public Tipo getTipo() {
		return new TipoCorotina(parametrosFormais.getTipos(), this.tipoRetorno);
	}
}
