package li2.plp.imperative2.declaration;

import li2.plp.expressions1.util.Tipo;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative2.util.TipoProcedimento;
import li2.plp.imperativecoroutine.declaration.DefRotina;

/**
 * Uma defini�ao de procedimento � uma declara�ao de comando e parametrosFormais
 * uma declara��o de procedimento.
 */
public class DefProcedimento extends DefRotina{
	
	/**
	 * Construtor
	 * 
	 * @param parametrosFormais
	 *            Declara��o de ListaDeclaracaoParametro
	 * @param tipoRetorno
	 * 			  Tipo de Retorno
	 * @param comando
	 *            Declara��o de Comando.
	 */
	public DefProcedimento(ListaDeclaracaoParametro parametrosFormais, Tipo tipoRetorno, Comando comando) {
		super(parametrosFormais, tipoRetorno, comando);
	}

	public Tipo getTipo() {
		return new TipoProcedimento(parametrosFormais.getTipos(), this.tipoRetorno);
	}
}
