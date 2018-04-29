package li2.plp.imperativecoroutine.memory;

import li2.plp.expressions1.util.Tipo;
import li2.plp.imperative1.memory.AmbienteCompilacaoImperativa;

public interface AmbienteCompilacaoImperativaCorotina extends AmbienteCompilacaoImperativa{
	
	public Tipo getTipoEscopo();
	public void putTipoEscopo(Tipo tipo);
	public void popTipoEscopo();
}
