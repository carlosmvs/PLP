package li2.plp.imperativecoroutine.memory;

import java.util.Stack;

import li2.plp.imperative1.declaration.Declaracao;
import li2.plp.imperative1.memory.ContextoCompilacaoImperativa;
import li2.plp.imperative1.memory.ListaValor;

public class ContextoCompilacaoImperativaCorotina extends ContextoCompilacaoImperativa
		implements AmbienteCompilacaoImperativaCorotina {

	private Stack<Declaracao> declaracoes;
	
	public ContextoCompilacaoImperativaCorotina(ListaValor entrada) {
		super(entrada);
		declaracoes = new Stack<Declaracao>();
	}

	@Override
	public Declaracao getDeclaracao() {
		Declaracao dec = null;
		if(!declaracoes.empty()) {
			dec = declaracoes.peek();
		}
		return dec;
	}
	
	@Override
	public void putDeclaracao(Declaracao dec) {
		this.declaracoes.push(dec);
	}
	
	@Override
	public void popDeclaracao() {
		declaracoes.pop();
	}

}
