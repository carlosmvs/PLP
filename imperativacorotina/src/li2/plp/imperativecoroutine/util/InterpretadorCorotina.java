package li2.plp.imperativecoroutine.util;

import li2.plp.imperative1.command.Comando;
import li2.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li2.plp.imperativecoroutine.memory.*;

public class InterpretadorCorotina extends Coroutine{
	private Comando comando;
	private AmbienteExecucaoImperativa ambiente;
	
	public InterpretadorCorotina(Comando comando, AmbienteExecucaoImperativa ambiente) {
		this.comando = comando;
		this.ambiente = new ContextoExecucaoCorotina(ambiente);
	}
	
	protected void body() {
		
		try {
			ambiente = comando.executar(ambiente);
		}catch (Exception e) {
			System.out.println("Imperativa 2 PLP Parser Version 0.0.1:  Encountered errors during parse.");
            e.printStackTrace();
		}
		
	}
	
	public Comando getComando() {
		return comando;
	}
	public void setComando(Comando comando) {
		this.comando = comando;
	}
	public AmbienteExecucaoImperativa getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(AmbienteExecucaoImperativa ambiente) {
		this.ambiente = ambiente;
	}
}
