package li2.plp.imperativecoroutine.excecao;

import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;

/**
 * Usada para finalizar a execução de rotinas em comando do tipo Retorno
 */
public class RetornoException extends RuntimeException{
	
	private static final long serialVersionUID = 2661392765667199967L;
	
	private AmbienteExecucaoImperativa ambiente;

	//construtor
	public RetornoException(AmbienteExecucaoImperativa ambiente) {
		this.ambiente = ambiente;
	}
	
	//gets e sets
	public AmbienteExecucaoImperativa getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(AmbienteExecucaoImperativa ambiente) {
		this.ambiente = ambiente;
	}

}
