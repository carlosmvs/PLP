package li2.plp.imperativecoroutine.memory;

import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.memory.IdentificadorNaoDeclaradoException;

/**
 * Exceção a ser levantada caso a co-rotina referenciada não tenha sido declarada
 *
 */
public class CorotinaNaoDeclaradaException extends IdentificadorNaoDeclaradoException {
	
	private static final long serialVersionUID = -5040439326172230378L;
	
	//construtor
	public CorotinaNaoDeclaradaException(Id id) {
		super("Corotina " + id + " nao declarada.");
	}
}
