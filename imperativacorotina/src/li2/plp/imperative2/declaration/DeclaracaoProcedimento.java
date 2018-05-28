package li2.plp.imperative2.declaration;

import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.memory.IdentificadorJaDeclaradoException;
import li2.plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import li2.plp.imperative1.declaration.Declaracao;
import li2.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li2.plp.imperative1.memory.EntradaVaziaException;
import li2.plp.imperative2.memory.AmbienteExecucaoImperativa2;
import li2.plp.imperativecoroutine.memory.AmbienteCompilacaoImperativaCorotina;

public class DeclaracaoProcedimento extends Declaracao {

	private Id id;
	private DefProcedimento defProcedimento;

	public DeclaracaoProcedimento(Id id, DefProcedimento defProcedimento) {
		super();
		this.id = id;
		this.defProcedimento = defProcedimento;
	}

	@Override
	public AmbienteExecucaoImperativa elabora(
			AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		((AmbienteExecucaoImperativa2) ambiente).mapProcedimento(getId(),
				getDefProcedimento());
		return ambiente;
	}

	private Id getId() {
		return this.id;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		boolean resposta;

		ambiente.map(id, defProcedimento.getTipo());
		
		AmbienteCompilacaoImperativaCorotina amb = ((AmbienteCompilacaoImperativaCorotina) ambiente);

		ListaDeclaracaoParametro parametrosFormais = getDefProcedimento()
				.getParametrosFormais();
		
		if (parametrosFormais.checaTipo(ambiente)) {
			amb.putDeclaracao(this);
			
			ambiente.incrementa();
			
			ambiente = parametrosFormais.elabora(ambiente);
			
			resposta = getDefProcedimento().getComando().checaTipo(ambiente);
			
			ambiente.restaura();
			
			amb.popDeclaracao();
		} else {
			resposta = false;
		}
		
		return resposta;
	}

	public DefProcedimento getDefProcedimento() {
		return this.defProcedimento;
	}
	
}
