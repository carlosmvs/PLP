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
import li2.plp.imperativecoroutine.declaration.DefRotina;
import li2.plp.imperativecoroutine.excecao.RetornoException;
import li2.plp.imperativecoroutine.memory.AmbienteCompilacaoImperativaCorotina;

/**
 * Corresponde ao comando return
 * <br>Usado tanto em co-rotinas como procedimentos para finalizar
 * <br>a execução das mesmas com a possibilidade de retorno de valor
 */
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
		
		//verifica se possui valor de retorno
		if(expressao != null) {
			//avalia a expressão para obter valor de retorno
			val = expressao.avaliar(ambiente);
		}
		//levanta exceção retornando o ambiente e o valor de retorno como parâmetros do contrutor da exceção
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
			DefRotina defRot;
			
			if(declaracao instanceof DeclaracaoCorotina) {
				defRot = ((DeclaracaoCorotina) declaracao).getDefCorotina();
			}else { //procedimento
				defRot = ((DeclaracaoProcedimento) declaracao).getDefProcedimento();
			}
			
			defRot = ((DeclaracaoCorotina) declaracao).getDefCorotina();
			
			if(defRot.getTipoRetorno() != null) {
				//verifica se expressão existe e se é válida
				resposta = expressao != null && expressao.checaTipo(ambiente);
				
				if(resposta) { //verifica se o tipo de retorno é igual ao da expressão
					resposta = defRot.getTipoRetorno().eIgual(expressao.getTipo(ambiente));
				}
			}else {
				//não possue tipo de retorno
				//expressão deve ser nula
				if(expressao != null) {
					resposta = false;
				}
			}
			
			//incrementa quantidade de yields
			defRot.setQtdRetornos(defRot.getQtdRetornos() + 1);
		}
		
		return resposta;
	}
}
