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
 * <br>a execu��o das mesmas com a possibilidade de retorno de valor
 */
public class Retorno implements Comando{
	
	private Expressao expressao;
	
	public Retorno(Expressao expressao) {
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
			ErroTipoEntradaException, RetornoException{
		Valor val = null;
		
		//verifica se possui valor de retorno
		if(expressao != null) {
			//avalia a express�o para obter valor de retorno
			val = expressao.avaliar(ambiente);
		}
		//levanta exce��o retornando o ambiente e o valor de retorno como par�metros do contrutor da exce��o
		throw new RetornoException(ambiente, val);
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
				//verifica se express�o existe e se � v�lida
				resposta = expressao != null && expressao.checaTipo(ambiente);
				
				if(resposta) { //verifica se o tipo de retorno � igual ao da express�o
					resposta = defRot.getTipoRetorno().eIgual(expressao.getTipo(ambiente));
				}
			}else {
				//n�o possue tipo de retorno
				//express�o deve ser nula
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
