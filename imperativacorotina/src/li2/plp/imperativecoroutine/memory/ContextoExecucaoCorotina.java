package li2.plp.imperativecoroutine.memory;

import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.expression.Valor;
import li2.plp.expressions2.memory.*;
import li2.plp.imperative1.memory.*;
import li2.plp.imperative2.declaration.DefProcedimento;
import li2.plp.imperative2.memory.*;
import li2.plp.imperativecoroutine.coroutine.Coroutine;
import li2.plp.imperativecoroutine.declaration.DefCorotina;

public class ContextoExecucaoCorotina implements AmbienteExecucaoImperativaCorotina{

	private AmbienteExecucao contextoGlobal;
	
	private AmbienteExecucao contextoCorotina;
	
	public ContextoExecucaoCorotina(AmbienteExecucao contextoGlobal) {
		this.contextoGlobal = contextoGlobal;
		this.contextoCorotina = new ContextoExecucaoImperativaCorotina(null);
	}
	public ContextoExecucaoCorotina(AmbienteExecucao contextoGlobal, AmbienteExecucao contextoCorotina) {
		this.contextoGlobal = contextoGlobal;
		this.contextoCorotina = contextoCorotina;
	}

	@Override
	public void mapProcedimento(Id idArg, DefProcedimento procedimentoId) throws ProcedimentoJaDeclaradoException {
		((AmbienteExecucaoImperativa2) contextoCorotina).mapProcedimento(idArg, procedimentoId);
	}

	@Override
	public DefProcedimento getProcedimento(Id idArg) throws ProcedimentoNaoDeclaradoException {
		DefProcedimento defProc;
		try {
			defProc = ((AmbienteExecucaoImperativa2) contextoCorotina).getProcedimento(idArg);
		} catch (Exception e) {
			defProc = ((AmbienteExecucaoImperativa2) contextoGlobal).getProcedimento(idArg);
		}

		return defProc;
	}

	@Override
	public void changeValor(Id idArg, Valor valorId) throws VariavelNaoDeclaradaException {
		try {
			((AmbienteExecucaoImperativa2) contextoCorotina).changeValor(idArg, valorId);
		}catch (VariavelNaoDeclaradaException e) {
			((AmbienteExecucaoImperativa2) contextoGlobal).changeValor(idArg, valorId);
		}
	}

	@Override
	public Valor read() throws EntradaVaziaException {
		return ((AmbienteExecucaoImperativa2) contextoGlobal).read();
	}

	@Override
	public void write(Valor v) {
		((AmbienteExecucaoImperativa2) contextoGlobal).write(v);
	}

	@Override
	public ListaValor getSaida() {
		return ((AmbienteExecucaoImperativa2) contextoGlobal).getSaida();
	}

	@Override
	public AmbienteExecucao clone() {
		return new ContextoExecucaoCorotina(this.contextoGlobal.clone(), this.contextoCorotina.clone());
	}

	@Override
	public void incrementa() {
		contextoGlobal.incrementa();
		contextoCorotina.incrementa();
	}

	@Override
	public void restaura() {
		contextoGlobal.restaura();
		contextoCorotina.restaura();
	}

	@Override
	public void map(Id idArg, Valor tipoId) throws VariavelJaDeclaradaException {
		contextoCorotina.map(idArg, tipoId);
	}

	@Override
	public Valor get(Id idArg) throws VariavelNaoDeclaradaException {
		Valor valor;
		
		try {
			valor = contextoCorotina.get(idArg);
		}catch (VariavelNaoDeclaradaException e) {
			valor = contextoGlobal.get(idArg);
		}
		
		return valor;
	}

	@Override
	public void mapDefinicaoCorotina(Id idArg, DefCorotina corotinaId) throws CorotinaJaDeclaradaException {
		((AmbienteExecucaoImperativaCorotina) contextoCorotina).mapDefinicaoCorotina(idArg, corotinaId);
	}

	@Override
	public DefCorotina getDefinicaoCorotina(Id idArg) throws CorotinaNaoDeclaradaException {
		return ((AmbienteExecucaoImperativaCorotina) contextoCorotina).getDefinicaoCorotina(idArg);
	}

	@Override
	public void mapCorotina(Id idArg, Coroutine corotinaId) {
		((AmbienteExecucaoImperativaCorotina)contextoCorotina).mapCorotina(idArg, corotinaId);
	}

	@Override
	public Coroutine getCorotina(Id idArg) {
		return ((AmbienteExecucaoImperativaCorotina)contextoCorotina).getCorotina(idArg);
	}

}
