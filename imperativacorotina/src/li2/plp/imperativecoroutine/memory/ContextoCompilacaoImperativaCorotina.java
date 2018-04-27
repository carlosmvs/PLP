package li2.plp.imperativecoroutine.memory;

import java.util.Stack;

import li2.plp.expressions1.util.Tipo;
import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.memory.VariavelJaDeclaradaException;
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
	public void map(Id idArg, Tipo valorId) throws VariavelJaDeclaradaException {
		super.map(idArg, valorId);
		this.tipos.add(valorId);
	}

	@Override
	public Stack<Tipo> getPilhaTipos() {
		return this.tipos;
	}

	public void setTipos(Stack<Tipo> tipos) {
		this.tipos = tipos;
	}

}
