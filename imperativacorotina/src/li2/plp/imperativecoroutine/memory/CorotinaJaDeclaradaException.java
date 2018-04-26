package li2.plp.imperativecoroutine.memory;

import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.memory.IdentificadorJaDeclaradoException;

public class CorotinaJaDeclaradaException extends IdentificadorJaDeclaradoException{
	
	private static final long serialVersionUID = -3361730126331055275L;
	
	public CorotinaJaDeclaradaException(Id id) {
		super("Corotina " + id + " já declarada.");
	}
}
