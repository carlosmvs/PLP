package li2.plp.imperativecoroutine.declaration;

import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.memory.IdentificadorJaDeclaradoException;
import li2.plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import li2.plp.imperative1.declaration.Declaracao;
import li2.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li2.plp.imperative1.memory.EntradaVaziaException;
import li2.plp.imperative2.declaration.ListaDeclaracaoParametro;
import li2.plp.imperativecoroutine.memory.AmbienteCompilacaoImperativaCorotina;
import li2.plp.imperativecoroutine.memory.AmbienteExecucaoImperativaCorotina;

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
		
		ListaDeclaracaoParametro parametrosFormais = getDefCorotina().getParametrosFormais();
		
		if(parametrosFormais.checaTipo(ambiente)) {
			amb.putDeclaracao(this);
			
			ambiente.incrementa();
			
			ambiente = parametrosFormais.elabora(ambiente);
			
			resposta = getDefCorotina().getComando().checaTipo(ambiente);
			
			ambiente.restaura();
			
			amb.popDeclaracao();
		}else {
			resposta = false;
		}
		
		if(this.defCorotina.getTipoRetorno() != null) {
			resposta &= (this.defCorotina.getQtdRetornos() > 0 || this.defCorotina.getQtdYields() > 0);
		}
		
		return resposta;
	}
	
	public DefCorotina getDefCorotina() {
		return this.defCorotina;
	}
	
}
