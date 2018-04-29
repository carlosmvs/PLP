package li2.plp.imperativecoroutine.command;

import li2.plp.expressions1.util.Tipo;
import li2.plp.expressions2.memory.IdentificadorJaDeclaradoException;
import li2.plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li2.plp.imperative1.memory.EntradaVaziaException;
import li2.plp.imperative1.memory.ErroTipoEntradaException;
import li2.plp.imperative2.util.TipoProcedimento;
import li2.plp.imperativecoroutine.memory.AmbienteCompilacaoImperativaCorotina;
import li2.plp.imperativecoroutine.util.Coroutine;

public class InterrupcaoCorotina implements Comando{
	/**
	 * Não realiza nenhuma alteração no ambiente.
	 * 
	 * @param ambiente
	 *            o ambiente de execução.
	 * 
	 * @return o ambiente inalterado.
	 * 
	 */
	public AmbienteExecucaoImperativa executar(AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException, IdentificadorNaoDeclaradoException, EntradaVaziaException,
			ErroTipoEntradaException{
		Coroutine.detach();
		return ambiente;
	}

	/**
	 * Realiza a verificacao de tipos do comando
	 * 
	 * @param ambiente
	 *            o ambiente de compilação.
	 * @return <code>true</code> se o comando é bem tipado; <code>false</code>
	 *         caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente) {
		Tipo tipo = ((AmbienteCompilacaoImperativaCorotina) ambiente).getTipoEscopo();
		
		boolean resposta = true;
		
		if(tipo == null || tipo instanceof TipoProcedimento) {
			resposta = false;
		}
		
		return resposta;
	}
}
