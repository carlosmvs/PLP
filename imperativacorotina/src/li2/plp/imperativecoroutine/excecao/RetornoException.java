package li2.plp.imperativecoroutine.excecao;

import li2.plp.expressions2.expression.Valor;
import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;

/**
 * Usada para finalizar a execu��o de rotinas em comando do tipo Retorno
 */
public class RetornoException extends RuntimeException{
	
	private static final long serialVersionUID = 2661392765667199967L;
	
	private AmbienteExecucaoImperativa ambiente;
	
	private Valor valorRetorno;

	//construtor
	public RetornoException(AmbienteExecucaoImperativa ambiente, Valor valorRetorno) {
		super();
		this.ambiente = ambiente;
		this.valorRetorno = valorRetorno;
	}
	
	//gets e sets
	public AmbienteExecucaoImperativa getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(AmbienteExecucaoImperativa ambiente) {
		this.ambiente = ambiente;
	}

	public Valor getValorRetorno() {
		return valorRetorno;
	}

	public void setValorRetorno(Valor valorRetorno) {
		this.valorRetorno = valorRetorno;
	}

}
