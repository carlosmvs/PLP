package li2.plp.imperativecoroutine.command;

import li2.plp.expressions2.expression.Expressao;
import li2.plp.expressions2.expression.Valor;
import li2.plp.expressions2.memory.IdentificadorJaDeclaradoException;
import li2.plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative1.declaration.Declaracao;
import li2.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li2.plp.imperative1.memory.EntradaVaziaException;
import li2.plp.imperative1.memory.ErroTipoEntradaException;
import li2.plp.imperative2.declaration.DeclaracaoProcedimento;
import li2.plp.imperativecoroutine.declaration.DeclaracaoCorotina;
import li2.plp.imperativecoroutine.excecao.RetornoException;
import li2.plp.imperativecoroutine.memory.AmbienteCompilacaoImperativaCorotina;

public class Retorno implements Comando{
	
	private Expressao expressao;
	
	public Retorno(Expressao expressao) {
		this.expressao = expressao;
	}
	
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
			ErroTipoEntradaException, RetornoException{
		Valor val = null;
		
		if(expressao != null) {
			val = expressao.avaliar(ambiente);
		}
		
		throw new RetornoException(ambiente, val);
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
		Declaracao declaracao = ((AmbienteCompilacaoImperativaCorotina) ambiente).getDeclaracao();
		
		boolean resposta = true;
		
		if(declaracao == null) { //fora de procedimentos ou co-rotinas
			resposta = false;
		}else{
			if(declaracao instanceof DeclaracaoCorotina) {
				DeclaracaoCorotina decCor = (DeclaracaoCorotina) declaracao;
				
				if(decCor.getDefCorotina().getTipoRetorno() != null) {
					resposta = expressao != null && expressao.checaTipo(ambiente);
				}else {
					if(expressao != null) {
						resposta = false;
					}
				}
				
				decCor.setQtdRetornos(decCor.getQtdRetornos() + 1);
			}else{ //procedimento
				DeclaracaoProcedimento decPro = (DeclaracaoProcedimento) declaracao;
				
				if(decPro.getDefProcedimento().getTipoRetorno() != null) {
					resposta = expressao != null && expressao.checaTipo(ambiente);
				}else {
					if(expressao != null) {
						resposta = false;
					}
				}
				
				decPro.setQtdRetornos(decPro.getQtdRetornos() + 1);
			}
		}
		
		return resposta;
	}
}
