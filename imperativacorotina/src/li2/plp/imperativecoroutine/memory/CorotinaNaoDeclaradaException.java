package li2.plp.imperativecoroutine.memory;

import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.memory.IdentificadorNaoDeclaradoException;

public class CorotinaNaoDeclaradaException extends IdentificadorNaoDeclaradoException {
	
	private static final long serialVersionUID = -5040439326172230378L;

	public CorotinaNaoDeclaradaException(Id id) {
		super("Corotina " + id + " nao declarada.");
	}
}
