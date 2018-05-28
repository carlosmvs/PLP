package li2.plp.imperativecoroutine.util;

import static li2.plp.expressions1.util.ToStringProvider.listToString;

import java.util.ArrayList;
import java.util.List;

import li2.plp.expressions1.util.Tipo;
import li2.plp.expressions1.util.TipoPrimitivo;

public abstract class TipoRotina implements Tipo{

	protected List<Tipo> tiposParametrosFormais = new ArrayList<Tipo>();
	protected Tipo retorno;

	public TipoRotina(List<Tipo> tiposParametrosFormais2) {
		this(tiposParametrosFormais2, null);
	}
	
	public TipoRotina(List<Tipo> tiposParametrosFormais2, Tipo retorno) {
		this.tiposParametrosFormais.addAll(tiposParametrosFormais2);
		this.retorno = retorno;
	}

	public boolean eBooleano() {
		if(retorno != null) {
			return this.eIgual(TipoPrimitivo.BOOLEANO);
		}
		return false;
	}
	
	public boolean eInteiro() {
		if(retorno != null) {
			return this.eIgual(TipoPrimitivo.INTEIRO);
		}
		return false;
	}

	public boolean eString() {
		if(retorno != null) {
			return this.eIgual(TipoPrimitivo.STRING);
		}
		return false;
	}

	public boolean eValido() {
		boolean retorno = true;
		for (Tipo tipo : tiposParametrosFormais) {
			retorno &= tipo.eValido();
		}

		return retorno;
	}

	public String getNome() {
		if(retorno != null) {
			return this.retorno.getNome();
		}
		return listToString(this.tiposParametrosFormais, "{", "}", ",");
	}

	public Tipo intersecao(Tipo outroTipo) {
		if (outroTipo.eIgual(this))
			return this;
		else
			return null;
	}
	
	public Tipo getRetorno() {
		return retorno;
	}

	public abstract boolean eIgual(Tipo tipo);
	
}
