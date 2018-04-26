package li2.plp.imperativecoroutine.memory;

import java.util.HashMap;
import java.util.Map;

import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.memory.Contexto;
import li2.plp.expressions2.memory.VariavelJaDeclaradaException;
import li2.plp.expressions2.memory.VariavelNaoDeclaradaException;
import li2.plp.imperative1.memory.ListaValor;
import li2.plp.imperative2.memory.ContextoExecucaoImperativa2;
import li2.plp.imperativecoroutine.declaration.DefCorotina;
import li2.plp.imperativecoroutine.util.Coroutine;

public class ContextoExecucaoImperativaCorotina 
	extends ContextoExecucaoImperativa2 
	implements AmbienteExecucaoImperativaCorotina{

	/**
	 * O contexto de co-rotinas 
	 */
	private Contexto<DefCorotina> contextoCorotinas;
	private Map<Id, Coroutine> corotinas;
	
	public ContextoExecucaoImperativaCorotina(ListaValor entrada) {
		super(entrada);
		contextoCorotinas = new Contexto<DefCorotina>();
		corotinas = new HashMap<Id, Coroutine>();
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
	 *                se já existir um mapeamento do identificador nesta tabela.
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
	 *                se não existir nenhuma co-rotina mapeada ao id dado
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

	public void mapCorotina(Id idArg, Coroutine corotinaId) {
		corotinas.put(idArg, corotinaId);
	}
	
	public Coroutine getCorotina(Id idArg) {
		return corotinas.get(idArg);
	}

}
