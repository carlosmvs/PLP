package li2.plp.imperativecoroutine.memory;

import li2.plp.imperative1.declaration.Declaracao;
import li2.plp.imperative1.memory.AmbienteCompilacaoImperativa;

public interface AmbienteCompilacaoImperativaCorotina extends AmbienteCompilacaoImperativa{
	
	public Declaracao getDeclaracao();
	public void putDeclaracao(Declaracao declaracao);
	public void popDeclaracao();
}
