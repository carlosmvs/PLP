package li2.plp.imperativecoroutine.memory;

import java.util.Stack;

import li2.plp.expressions1.util.Tipo;
import li2.plp.imperative1.memory.ContextoCompilacaoImperativa;
import li2.plp.imperative1.memory.ListaValor;

public class ContextoCompilacaoImperativaCorotina extends ContextoCompilacaoImperativa
		implements AmbienteCompilacaoImperativaCorotina {

	private Stack<Tipo> tipos;
	
	public ContextoCompilacaoImperativaCorotina(ListaValor entrada) {
		super(entrada);
		tipos = new Stack<Tipo>();
	}

	@Override
	public Tipo getTipoEscopo() {
		Tipo tipo = null;
		if(!tipos.empty()) {
			tipo = tipos.peek();
		}
		return tipo;
	}
	
	@Override
	public void putTipoEscopo(Tipo tipo) {
		this.tipos.push(tipo);
	}
	
	@Override
	public void popTipoEscopo() {
		tipos.pop();
	}

}
