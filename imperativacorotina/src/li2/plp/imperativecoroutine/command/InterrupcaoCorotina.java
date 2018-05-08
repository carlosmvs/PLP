package li2.plp.imperativecoroutine.command;

import li2.plp.expressions2.expression.Expressao;
import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.expression.Valor;
import li2.plp.expressions2.memory.IdentificadorJaDeclaradoException;
import li2.plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import li2.plp.expressions2.memory.VariavelJaDeclaradaException;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative1.declaration.Declaracao;
import li2.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li2.plp.imperative1.memory.EntradaVaziaException;
import li2.plp.imperative1.memory.ErroTipoEntradaException;
import li2.plp.imperative2.declaration.DeclaracaoProcedimento;
import li2.plp.imperativecoroutine.coroutine.Coroutine;
import li2.plp.imperativecoroutine.declaration.DeclaracaoCorotina;
import li2.plp.imperativecoroutine.memory.AmbienteCompilacaoImperativaCorotina;

public class InterrupcaoCorotina implements Comando{
	
	private Expressao expressao;
	
	public InterrupcaoCorotina(Expressao expressao) {
		this.expressao = expressao;
	}
	
	/**
	 * N�o realiza nenhuma altera��o no ambiente.
	 * 
	 * @param ambiente
	 *            o ambiente de execu��o.
	 * 
	 * @return o ambiente inalterado.
	 * 
	 */
	public AmbienteExecucaoImperativa executar(AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException, IdentificadorNaoDeclaradoException, EntradaVaziaException,
			ErroTipoEntradaException {
		Valor val = null;

		if (expressao != null) {
			val = expressao.avaliar(ambiente);
			
			try {
				ambiente.map(new Id("yield"), val);
			
			}catch(VariavelJaDeclaradaException e) {
				ambiente.changeValor(new Id("yield"), val);
			}
		}

		Coroutine.detach();
		return ambiente;
	}

	/**
	 * Realiza a verificacao de tipos do comando
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o.
	 * @return <code>true</code> se o comando � bem tipado; <code>false</code>
	 *         caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente) {
		Declaracao declaracao = ((AmbienteCompilacaoImperativaCorotina) ambiente).getDeclaracao();
		
		boolean resposta = true;
		
		if(declaracao == null || declaracao instanceof DeclaracaoProcedimento) {
			resposta = false;
		}else { //co-rotina
			DeclaracaoCorotina decCor = (DeclaracaoCorotina) declaracao;
			if(decCor.getDefCorotina().getTipoRetorno() != null) {
				resposta = expressao != null && expressao.checaTipo(ambiente);
				if(resposta) { //verifica se o tipo de retorno � igual ao da express�o
					resposta = decCor.getDefCorotina().getTipoRetorno().eIgual(expressao.getTipo(ambiente));
				}
			}else {
				if(expressao != null) {
					resposta = false;
				}
			}
		}
		
		return resposta;
	}
}
