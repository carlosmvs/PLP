package li2.plp.imperativecoroutine.command;

import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.memory.IdentificadorJaDeclaradoException;
import li2.plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li2.plp.imperative1.memory.EntradaVaziaException;
import li2.plp.imperative1.memory.ErroTipoEntradaException;
import li2.plp.imperative2.command.ListaExpressao;
import li2.plp.imperativecoroutine.coroutine.Coroutine;
import li2.plp.imperativecoroutine.coroutine.InterpretadorCorotina;
import li2.plp.imperativecoroutine.declaration.DefCorotina;
import li2.plp.imperativecoroutine.memory.AmbienteExecucaoImperativaCorotina;

public class ChamadaCorotina implements Comando {
	
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
		
		Coroutine corotina = amb.getCorotina(nomeCorotina);
		if(corotina != null && !corotina.isTerminated()) {
			Coroutine.call(corotina);
		}else {
			corotina = new InterpretadorCorotina(defCorotina.getComandoCorotina(), amb);
			amb.mapCorotina(nomeCorotina, corotina);
			Coroutine.call(corotina);
		}
		
		return amb;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException, IdentificadorNaoDeclaradoException, EntradaVaziaException {
		// TODO Auto-generated method stub
		return true;
	}

}
