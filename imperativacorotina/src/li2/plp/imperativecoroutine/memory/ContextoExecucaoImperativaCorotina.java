package li2.plp.imperativecoroutine.memory;

import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.memory.Contexto;
import li2.plp.expressions2.memory.VariavelJaDeclaradaException;
import li2.plp.expressions2.memory.VariavelNaoDeclaradaException;
import li2.plp.imperative1.memory.ListaValor;
import li2.plp.imperative2.memory.ContextoExecucaoImperativa2;
import li2.plp.imperativecoroutine.declaration.DefCorotina;

public class ContextoExecucaoImperativaCorotina 
	extends ContextoExecucaoImperativa2 
	implements AmbienteExecucaoImperativaCorotina{

	/**
	 * O contexto de co-rotinas 
	 */
	private Contexto<DefCorotina> contextoCorotinas;
	
	public ContextoExecucaoImperativaCorotina(ListaValor entrada) {
		super(entrada);
		contextoCorotinas = new Contexto<DefCorotina>();
	}
	
	@Override
	public void incrementa() {
		super.incrementa();
		this.contextoCorotinas.incrementa();
	}

	@Override
	public void restaura() {
		super.restaura();
		this.contextoCorotinas.restaura();
	}
	
	/**
	 * Mapeia o id no procedimento dado.
	 * 
	 * @exception CorotinaJaDeclaradaException
	 *                se j� existir um mapeamento do identificador nesta tabela.
	 */
	public void mapDefinicaoCorotina(Id idArg, DefCorotina corotinaId)
			throws CorotinaJaDeclaradaException {
		try {
			this.contextoCorotinas.map(idArg, corotinaId);
		} catch (VariavelJaDeclaradaException e) {
			throw new CorotinaJaDeclaradaException(idArg);
		}

	}

	/**
	 * Retorna a co-rotina mapeada ao id dado.
	 * 
	 * @exception CorotinaNaoDeclaradaException
	 *                se n�o existir nenhuma co-rotina mapeada ao id dado
	 *                nesta tabela.
	 */
	public DefCorotina getDefinicaoCorotina(Id idArg)
			throws CorotinaNaoDeclaradaException {
		try {
			return this.contextoCorotinas.get(idArg);
		} catch (VariavelNaoDeclaradaException e) {
			throw new CorotinaNaoDeclaradaException(idArg);
		}

	}

}
