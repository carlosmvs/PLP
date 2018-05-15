package li2.plp.expressions2.expression;

import li2.plp.expressions1.util.Tipo;
import li2.plp.expressions1.util.TipoPrimitivo;
import li2.plp.expressions2.memory.AmbienteCompilacao;
import li2.plp.expressions2.memory.AmbienteExecucao;
import li2.plp.expressions2.memory.VariavelJaDeclaradaException;
import li2.plp.expressions2.memory.VariavelNaoDeclaradaException;

/**
 * Um objeto desta classe representa uma Expressao de Valor Absoluto.
 */
public class ExpAbs extends ExpUnaria{
	
	/**
	 * Controi uma Expressao de valor absoluto com expressao
	 * especificada.
	 * 
	 * @param exp Expressao a ser convertida em valor absoluto. Assume-se que sua avaliacao resulta
	 *        em <code>ValorInteiro</code>.
	 */
	public ExpAbs(Expressao exp) {
		super(exp, "abs");
	}
	
	/**
	 * Retorna o valor da Expressao de valor absoluto.
	 * 
	 * @param amb o ambiente de execução.
	 * @return o valor da expressão avaliada.
	 * @exception VariavelNaoDeclaradaException se a variável não está
	 *            declarada no ambiente. 
	 */
	@Override
	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return new ValorInteiro(Math.abs(((ValorInteiro) getExp().avaliar(amb)).valor()));
	}
	
	/**
	 * Retorna os tipos possiveis desta expressao.
	 *
	 * @param amb o ambiente de compilação.
	 * @return os tipos possiveis desta expressao.
	 */
	@Override
	public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return TipoPrimitivo.INTEIRO;
	}

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 * 
	 * @param amb o ambiente de compilação.
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *          <code>false</code> caso contrario.
	 * @exception VariavelNaoDeclaradaException se existir um identificador
	 *          nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException se existir um identificador
	 *          declarado mais de uma vez no mesmo bloco do ambiente.
	 */
	@Override
	protected boolean checaTipoElementoTerminal(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return getExp().getTipo(amb).eInteiro();
	}

	@Override
	public ExpUnaria clone() {
		return new ExpAbs(exp.clone());
	}

	
}
