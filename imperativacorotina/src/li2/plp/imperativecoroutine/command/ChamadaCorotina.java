package li2.plp.imperativecoroutine.command;

import li2.plp.expressions1.util.Tipo;
import li2.plp.expressions2.expression.Expressao;
import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.expression.Valor;
import li2.plp.expressions2.memory.AmbienteCompilacao;
import li2.plp.expressions2.memory.AmbienteExecucao;
import li2.plp.expressions2.memory.IdentificadorJaDeclaradoException;
import li2.plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import li2.plp.expressions2.memory.VariavelJaDeclaradaException;
import li2.plp.expressions2.memory.VariavelNaoDeclaradaException;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li2.plp.imperative1.memory.EntradaVaziaException;
import li2.plp.imperative1.memory.ErroTipoEntradaException;
import li2.plp.imperative1.memory.ListaValor;
import li2.plp.imperative2.command.ListaExpressao;
import li2.plp.imperative2.declaration.ListaDeclaracaoParametro;
import li2.plp.imperativecoroutine.coroutine.Coroutine;
import li2.plp.imperativecoroutine.coroutine.InterpretadorCorotina;
import li2.plp.imperativecoroutine.coroutine.TipoCorotina;
import li2.plp.imperativecoroutine.declaration.DefCorotina;
import li2.plp.imperativecoroutine.memory.AmbienteExecucaoImperativaCorotina;

public class ChamadaCorotina implements Comando, Expressao {
	
	private Id nomeCorotina;

	private ListaExpressao parametrosReais;
	
	public ChamadaCorotina(Id nomeCorotina,
			ListaExpressao parametrosReais) {
		this.nomeCorotina = nomeCorotina;
		this.parametrosReais = parametrosReais;
	}

	@Override
	public AmbienteExecucaoImperativa executar(AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException, IdentificadorNaoDeclaradoException, EntradaVaziaException,
			ErroTipoEntradaException {
		AmbienteExecucaoImperativaCorotina amb = (AmbienteExecucaoImperativaCorotina) ambiente;
		
		DefCorotina defCorotina = amb
				.getDefinicaoCorotina(nomeCorotina);
		
		ambiente.incrementa();
		
		ListaDeclaracaoParametro parametrosFormais = defCorotina.getParametrosFormais();
		AmbienteExecucaoImperativa aux = bindParameters(ambiente,
				parametrosFormais);
		
		Coroutine corotina = amb.getCorotina(nomeCorotina);
			
		if(corotina != null && !corotina.isTerminated()) {
			Coroutine.call(corotina);
		}else {
			corotina = new InterpretadorCorotina(defCorotina, aux);
			amb.mapCorotina(nomeCorotina, corotina);
			Coroutine.call(corotina);
		}
		
		aux.restaura();
		
		return aux;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException, IdentificadorNaoDeclaradoException, EntradaVaziaException {
		Tipo tipoCorotina = ambiente.get(this.nomeCorotina);
		
		TipoCorotina tipoParametrosReais = new TipoCorotina(
				parametrosReais.getTipos(ambiente));
		
		return tipoCorotina.eIgual(tipoParametrosReais);
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		AmbienteExecucaoImperativaCorotina ambiente = (AmbienteExecucaoImperativaCorotina) amb;
		
		Valor valorRetorno = null;
		
		DefCorotina defCorotina = ambiente
				.getDefinicaoCorotina(nomeCorotina);
		
		InterpretadorCorotina corotina = (InterpretadorCorotina) ambiente.getCorotina(nomeCorotina);
			
		if(corotina == null || corotina.isTerminated()) {
			corotina = new InterpretadorCorotina(defCorotina, ambiente);
			ambiente.mapCorotina(nomeCorotina, corotina);
			corotina.getAmbiente().incrementa();
		}
		
		ListaDeclaracaoParametro parametrosFormais = defCorotina.getParametrosFormais();
		AmbienteExecucaoImperativa aux = bindParameters(corotina.getAmbiente(),
				parametrosFormais);
		
		Coroutine.call(corotina);
		
		if(defCorotina.getTipoRetorno() != null) {
			try {
				valorRetorno = aux.get(new Id("return"));
			}catch (VariavelNaoDeclaradaException e) {
				valorRetorno = aux.get(new Id("yield"));
			}
		}
		if(corotina.isTerminated()) {
			aux.restaura();
		}
		
		return valorRetorno;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		Tipo tipoCorotina = amb.get(this.nomeCorotina);
		
		TipoCorotina tipoParametrosReais = new TipoCorotina(
				parametrosReais.getTipos((AmbienteCompilacaoImperativa) amb));
		
		return tipoCorotina.eIgual(tipoParametrosReais);
	}

	@Override
	public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		TipoCorotina tipoCorotina = (TipoCorotina) amb.get(this.nomeCorotina);
		return new TipoCorotina(
				parametrosReais.getTipos((AmbienteCompilacaoImperativa) amb), tipoCorotina.getRetorno());
	}

	@Override
	public Expressao reduzir(AmbienteExecucao ambiente) {
		return this;
	}

	@Override
	public Expressao clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * insere no contexto o resultado da associacao entre cada parametro formal
	 * e seu correspondente parametro atual
	 */
	private AmbienteExecucaoImperativa bindParameters(
			AmbienteExecucaoImperativa ambiente,
			ListaDeclaracaoParametro parametrosFormais)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {
		ListaValor listaValor = parametrosReais.avaliar(ambiente);
		while (listaValor.length() > 0) {
			try {
			ambiente.map(parametrosFormais.getHead().getId(), listaValor
					.getHead());
			}catch(VariavelJaDeclaradaException e) {
				ambiente.changeValor(parametrosFormais.getHead().getId(), listaValor
					.getHead());
			}
			parametrosFormais = (ListaDeclaracaoParametro) parametrosFormais
					.getTail();
			listaValor = (ListaValor) listaValor.getTail();
		}
		return ambiente;
	}

}
