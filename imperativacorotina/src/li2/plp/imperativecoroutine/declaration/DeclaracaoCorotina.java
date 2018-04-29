package li2.plp.imperativecoroutine.declaration;

import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.memory.IdentificadorJaDeclaradoException;
import li2.plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import li2.plp.imperative1.declaration.Declaracao;
import li2.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li2.plp.imperative1.memory.EntradaVaziaException;
import li2.plp.imperativecoroutine.memory.AmbienteCompilacaoImperativaCorotina;
import li2.plp.imperativecoroutine.memory.AmbienteExecucaoImperativaCorotina;
import li2.plp.imperativecoroutine.util.InterpretadorCorotina;

public class DeclaracaoCorotina extends Declaracao {
	
	private Id id;
	private DefCorotina defCorotina;
	
	public DeclaracaoCorotina(Id id, DefCorotina defCorotina) {
		this.id = id;
		this.defCorotina = defCorotina;
	}

	@Override
	public AmbienteExecucaoImperativa elabora(AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException, IdentificadorNaoDeclaradoException, EntradaVaziaException {
		((AmbienteExecucaoImperativaCorotina) ambiente).mapDefinicaoCorotina(getId(),
				getDefCorotina());
		((AmbienteExecucaoImperativaCorotina) ambiente).mapCorotina(getId(),
				new InterpretadorCorotina(getDefCorotina().getComandoCorotina(), ambiente));
		return ambiente;
	}
	
	private Id getId() {
		return this.id;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException, IdentificadorNaoDeclaradoException, EntradaVaziaException {
		boolean resposta;
		
		ambiente.map(id, defCorotina.getTipo());
		AmbienteCompilacaoImperativaCorotina amb = ((AmbienteCompilacaoImperativaCorotina) ambiente);
		amb.putTipoEscopo(defCorotina.getTipo());
		
		resposta = getDefCorotina().getComandoCorotina().checaTipo(ambiente);
		
		amb.popTipoEscopo();
		return resposta;
	}
	
	private DefCorotina getDefCorotina() {
		return this.defCorotina;
	}

}
