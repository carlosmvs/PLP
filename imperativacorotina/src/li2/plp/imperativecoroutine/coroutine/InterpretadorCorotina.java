package li2.plp.imperativecoroutine.coroutine;

import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.expression.Valor;
import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li2.plp.imperativecoroutine.declaration.DefCorotina;
import li2.plp.imperativecoroutine.excecao.RetornoException;
import li2.plp.imperativecoroutine.memory.*;

public class InterpretadorCorotina extends Coroutine{
	private DefCorotina corotina;
	private AmbienteExecucaoImperativa ambiente;
	
	//construtor
	public InterpretadorCorotina(DefCorotina corotina, AmbienteExecucaoImperativa ambiente) {
		this.corotina = corotina;
		this.ambiente = new ContextoExecucaoCorotina(ambiente);
	}
	
	//corpo da co-rotina
	protected void body() {
		
		try {
			//executa o comando da co-rotina
			ambiente = corotina.getComando().executar(ambiente);
			
		}catch(RetornoException e) {
			
			ambiente = e.getAmbiente();
			Valor valorRetorno = e.getValorRetorno();
			if(valorRetorno != null) {
				ambiente.map(new Id("return"), valorRetorno);
			}
			
		}catch (Exception e) {
            throw new RuntimeException(e);
		}
		
	}
	
	//gets e sets
	public DefCorotina getCorotina() {
		return corotina;
	}

	public void setCorotina(DefCorotina corotina) {
		this.corotina = corotina;
	}

	public AmbienteExecucaoImperativa getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(AmbienteExecucaoImperativa ambiente) {
		this.ambiente = ambiente;
	}
}
