package li2.plp.imperativecoroutine.memory;

import li2.plp.expressions2.expression.Id;
import li2.plp.imperative2.memory.AmbienteExecucaoImperativa2;
import li2.plp.imperativecoroutine.declaration.DefCorotina;

public interface AmbienteExecucaoImperativaCorotina extends AmbienteExecucaoImperativa2{
	public void mapDefinicaoCorotina(Id idArg, DefCorotina corotinaId)
			throws CorotinaJaDeclaradaException;

	public DefCorotina getDefinicaoCorotina(Id idArg)
			throws CorotinaNaoDeclaradaException;
}
