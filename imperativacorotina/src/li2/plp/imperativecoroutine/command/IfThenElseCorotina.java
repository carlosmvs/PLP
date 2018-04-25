package li2.plp.imperativecoroutine.command;

import li2.plp.expressions2.expression.Expressao;
import li2.plp.expressions2.expression.ValorBooleano;
import li2.plp.expressions2.memory.IdentificadorJaDeclaradoException;
import li2.plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li2.plp.imperative1.memory.EntradaVaziaException;
import li2.plp.imperative1.memory.ErroTipoEntradaException;
import li2.plp.imperativecorotina.excecao.InterrupcaoCorotinaException;

public class IfThenElseCorotina implements ComandoCorotina{
	private Expressao expressao;

	private Comando comandoThen;

	private Comando comandoElse;

	public IfThenElseCorotina(Expressao expressao, Comando comandoThen,
			Comando comandoElse) {
		this.expressao = expressao;
		this.comandoThen = comandoThen;
		this.comandoElse = comandoElse;
	}

	/**
	 * Implementa o comando <code>if then else</code>.
	 * 
	 * @param ambiente
	 *            o ambiente de execu��o.
	 * 
	 * @return o ambiente depois de modificado pela execu��o do comando
	 *         <code>if then else</code>.
	 * @throws ErroTipoEntradaException 
	 * 
	 */
	public AmbienteExecucaoImperativa executar(
			AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException, ErroTipoEntradaException,
			InterrupcaoCorotinaException{
		if (((ValorBooleano) expressao.avaliar(ambiente)).valor())
			return comandoThen.executar(ambiente);
		else
			return comandoElse.executar(ambiente);
	}

	/**
	 * Realiza a verificacao de tipos da express�o e dos comandos do comando
	 * <code>if then else</code>
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o.
	 * @return <code>true</code> se a express�o e os comando s�o bem tipados;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		return expressao.checaTipo(ambiente)
				&& expressao.getTipo(ambiente).eBooleano()
				&& comandoThen.checaTipo(ambiente)
				&& comandoElse.checaTipo(ambiente);
	}
}
