package li2.plp.imperativecoroutine.declaration;

import li2.plp.expressions1.util.Tipo;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative2.declaration.ListaDeclaracaoParametro;

/**
 * Classe abstrata que agrega todos os atributos e m�todos compartilhados por DefCorotina e DefProcedimento
 */
public abstract class DefRotina {

	/**
	 * Declaracao dos parametrosFormais
	 */
	protected ListaDeclaracaoParametro parametrosFormais;
	
	/**
	 * Indica se possui retorno
	 */
	protected Tipo tipoRetorno;

	/**
	 * Declara��o de comando
	 */
	protected Comando comando;
	
	/**
	 * Indica quantos comandos de retorno existem no corpo da rotina
	 */
	private int qtdRetornos;
	
	/**
	 * Construtor
	 * 
	 * @param parametrosFormais
	 *            Declara��o de ListaDeclaracaoParametro
	 * @param tipoRetorno
	 * 			  Tipo de Retorno
	 * @param comando
	 *            Declara��o de Comando.
	 */
	public DefRotina(ListaDeclaracaoParametro parametrosFormais,
			Tipo tipoRetorno,
			Comando comando) {
		this.parametrosFormais = parametrosFormais;
		this.tipoRetorno = tipoRetorno;
		this.comando = comando;
		this.qtdRetornos = 0;
	}
	
	/**
	 * Obt�m os parametrosFormais.
	 * 
	 * @return a ListaDeclaracaoParametro
	 */
	public ListaDeclaracaoParametro getParametrosFormais() {
		return parametrosFormais;
	}
	
	/**
	 * Retorna o tipo de retorno.
	 * 
	 * @return tipoRetorno
	 */
	public Tipo getTipoRetorno() {
		return tipoRetorno;
	}
	
	/**
	 * Obt�m o comando.
	 * 
	 * @return o comando
	 */
	public Comando getComando() {
		return comando;
	}
	
	/**
	 * Retorna quantidade de comandos de retorno declarados.
	 * 
	 * @return tipoRetorno
	 */
	public int getQtdRetornos() {
		return qtdRetornos;
	}

	public void setQtdRetornos(int qtdRetornos) {
		this.qtdRetornos = qtdRetornos;
	}
	
	public abstract Tipo getTipo();
}
