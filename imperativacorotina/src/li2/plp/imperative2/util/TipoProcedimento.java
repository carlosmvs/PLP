package li2.plp.imperative2.util;

import java.util.List;

import li2.plp.expressions1.util.Tipo;
import li2.plp.imperativecoroutine.util.TipoRotina;

public class TipoProcedimento extends TipoRotina {

	public TipoProcedimento(List<Tipo> tiposParametrosFormais2) {
		super(tiposParametrosFormais2);
	}
	
	public TipoProcedimento(List<Tipo> tiposParametrosFormais2, Tipo retorno) {
		super(tiposParametrosFormais2, retorno);
	}

	public boolean eIgual(Tipo tipo) {
		if (tipo instanceof TipoProcedimento) {
			TipoProcedimento tipoProc = (TipoProcedimento) tipo;
			return tipoProc.tiposParametrosFormais
					.equals(this.tiposParametrosFormais);
		}

		return tipo.eIgual(this);
	}
	
}
