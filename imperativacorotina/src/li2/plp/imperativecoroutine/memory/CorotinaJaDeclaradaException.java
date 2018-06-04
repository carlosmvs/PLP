package li2.plp.imperativecoroutine.memory;

import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.memory.IdentificadorJaDeclaradoException;

/**
 * Exceção a ser levantada caso a co-rotina referenciada já tenha sido declarada
 *
 */
public class CorotinaJaDeclaradaException extends IdentificadorJaDeclaradoException{
	
	private static final long serialVersionUID = -3361730126331055275L;
	
	//construtor
	public CorotinaJaDeclaradaException(Id id) {
		super("Corotina " + id + " já declarada.");
	}
}
