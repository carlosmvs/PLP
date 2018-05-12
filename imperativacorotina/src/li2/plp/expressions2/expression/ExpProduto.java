package li2.plp.expressions2.expression;

import li2.plp.expressions1.util.Tipo;
import li2.plp.expressions1.util.TipoPrimitivo;
import li2.plp.expressions2.memory.AmbienteCompilacao;
import li2.plp.expressions2.memory.AmbienteExecucao;
import li2.plp.expressions2.memory.VariavelJaDeclaradaException;
import li2.plp.expressions2.memory.VariavelNaoDeclaradaException;

public class ExpProduto extends ExpBinaria {
	
	public ExpProduto(Expressao esq, Expressao dir) {
		super(esq, dir, "*");
	}

	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return new ValorInteiro(
			((ValorInteiro) getEsq().avaliar(amb)).valor() *
			((ValorInteiro) getDir().avaliar(amb)).valor() );
	}
	
		protected boolean checaTipoElementoTerminal(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return (getEsq().getTipo(ambiente).eInteiro() && getDir().getTipo(ambiente).eInteiro());
	}

	
	public Tipo getTipo(AmbienteCompilacao ambiente) {
		return TipoPrimitivo.INTEIRO;
	}
	
	@Override
	public ExpBinaria clone() {
		return new ExpProduto(esq.clone(), dir.clone());
	}

}
