package li2.plp.imperativecoroutine.util;

import java.util.List;

import li2.plp.expressions1.util.Tipo;

public class TipoCorotina extends TipoRotina{

	public TipoCorotina(List<Tipo> tiposParametrosFormais2) {
		super(tiposParametrosFormais2);
	}
	
	public TipoCorotina(List<Tipo> tiposParametrosFormais2, Tipo retorno) {
		super(tiposParametrosFormais2, retorno);
	}

	public boolean eIgual(Tipo tipo) {
		if (tipo instanceof TipoCorotina) {
			TipoCorotina tipoProc = (TipoCorotina) tipo;
			return tipoProc.tiposParametrosFormais
					.equals(this.tiposParametrosFormais);
		}

		return tipo.eIgual(this);
	}
	
}
