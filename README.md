Coroutine - PLP Project
===================

Sobre
-------------
Projeto parte da avaliação da disciplina de [Paradigmas de Linguagens de Programação (IN1007)](https://www.cin.ufpe.br/~in1007/) ministrada pelo professor doutor Augusto Sampaio no semestre 2018.1, Centro de Informática, Universidade Federal de Pernambuco (UFPE).

Equipe
-------------
* Carlos Manoel [(cmvs)](mailto:cmvs@cin.ufpe.br)
* Carlos Zimmerle [(cezl)](mailto:cezl@cin.ufpe.br)
* Odilon Lima [(oflj)](mailto:oflj@cin.ufpe.br)

Objetivo
-------------

Implementar suporte a co-rotinas assimétricas na [linguagem imperativa 2](https://www.cin.ufpe.br/~in1007/linguagens/Imperativa2/imperativa2.html). Co-rotinas permitem que a execução de um procedimento seja suspensa, seu estado salvo e a execução seja retomada a partir do estado salvo em momento posterior.

BNF
-------
A seguintes expressões foram adicionadas a [gramática](https://www.cin.ufpe.br/~in1007/linguagens/Imperativa2/imperativa2.html) da linguagem:
<pre>
DeclaracaoCorotina ::= "cor" Id "(" [ ListaDeclaracaoParametro ] ")" [": Tipo"] "{" Comando "}"

ChamadaCorotina ::= "resume" Id "(" [ ListaExpressao ] ")"

InterrupcaoCorotina ::= "yield" [ Expressao ]

Retorno ::= "return" [ Expressao ]
</pre>

E as seguintes alteradas:

<pre>
Comando ::= Atribuicao
| ComandoDeclaracao
| While
| IfThenElse
| IO
| Comando ";" Comando
| Skip
| ChamadaProcedimento
| <a href="imperativacorotina/src/li2/plp/imperativecoroutine/command/ChamadaCorotina.java">ChamadaCorotina</a>
| <a href="imperativacorotina/src/li2/plp/imperativecoroutine/command/InterrupcaoCorotina.java">InterrupcaoCorotina</a>
| <a href="imperativacorotina/src/li2/plp/imperativecoroutine/command/Retorno.java">Retorno</a>
  
Expressao ::= Valor | ExpUnaria | ExpBinaria | Id | ChamadaProcedimento | 
<a href="imperativacorotina/src/li2/plp/imperativecoroutine/command/ChamadaCorotina.java">ChamadaCorotina</a>

Declaracao ::= DeclaracaoVariavel
| <a href="imperativacorotina/src/li2/plp/imperative2/declaration/DeclaracaoProcedimento.java">DeclaracaoProcedimento</a>
| <a href="imperativacorotina/src/li2/plp/imperativecoroutine/declaration/DeclaracaoCorotina.java">DeclaracaoCorotina</a>
| DeclaracaoComposta

DeclaracaoProcedimento ::= "proc" Id "(" [ ListaDeclaracaoParametro ] ")" [": Tipo"] "{" Comando "}"
</pre>

Classes Auxiliares

<pre>
<a href="imperativacorotina/src/li2/plp/imperativecoroutine/util/TipoRotina.java">TipoRotina</a>
<a href="imperativacorotina/src/li2/plp/imperativecoroutine/util/TipoCorotina.java">TipoCorotina</a>

<a href="imperativacorotina/src/li2/plp/imperativecoroutine/declaration/DefRotina.java">DefRotina</a>
<a href="imperativacorotina/src/li2/plp/imperativecoroutine/declaration/DefCorotina.java">DefCorotina</a>

<a href="imperativacorotina/src/li2/plp/imperativecoroutine/coroutine/Coroutine.java">Coroutine</a>
<a href="imperativacorotina/src/li2/plp/imperativecoroutine/coroutine/InterpretadorCorotina.java">InterpretadorCorotina</a>

<a href="imperativacorotina/src/li2/plp/imperativecoroutine/memory/AmbienteCompilacaoImperativaCorotina.java">AmbienteCompilacaoImperativaCorotina</a>
<a href="imperativacorotina/src/li2/plp/imperativecoroutine/memory/AmbienteExecucaoImperativaCorotina.java">AmbienteExecucaoImperativaCorotina</a>
<a href="imperativacorotina/src/li2/plp/imperativecoroutine/memory/ContextoCompilacaoImperativaCorotina.java">ContextoCompilacaoImperativaCorotina</a>
<a href="imperativacorotina/src/li2/plp/imperativecoroutine/memory/ContextoExecucaoCorotina.java">ContextoExecucaoCorotina</a>
<a href="imperativacorotina/src/li2/plp/imperativecoroutine/memory/ContextoExecucaoImperativaCorotina.java">ContextoExecucaoImperativaCorotina</a>
<a href="imperativacorotina/src/li2/plp/imperativecoroutine/memory/CorotinaJaDeclaradaException.java">CorotinaJaDeclaradaException</a>
<a href="imperativacorotina/src/li2/plp/imperativecoroutine/memory/CorotinaNaoDeclaradaException.java">CorotinaNaoDeclaradaException</a>

<a href="imperativacorotina/src/li2/plp/expressions2/expression/ExpAbs.java">ExpAbs</a>
<a href="imperativacorotina/src/li2/plp/expressions2/expression/ExpDivisao.java">ExpDivisao</a>
<a href="imperativacorotina/src/li2/plp/expressions2/expression/ExpDivisao.java">ExpProduto</a>
<a href="imperativacorotina/src/li2/plp/expressions2/expression/ExpModulo.java">ExpModulo</a>
</pre>





Exemplos
-------------

### Generator

```
{
	cor fibonacci() : int{
		{
		var prev = 0,
		var curr = 1,
		var temp = 0;
		while true do
			yield curr;
			temp := prev;
			prev := curr;
			curr := prev + temp
		}
	},
	var rep = 0;
	while not rep == 5 do
		write(resume fibonacci());
		rep := rep + 1
}
```

### Produtor/Consumidor

Exemplo adaptado do livro Java How to Program, 10th Ed. (Chap. 23 - Concurrency)

```
{
	var buffer = -1,
	cor produtor(){
		{
			var contador = 1;
			while not contador == 11 do
			buffer := contador;
			write("Produzido: ");
			write(buffer);
			contador := contador + 1;
			yield
		}
	},
	cor consumidor(){
		{
			var soma = 0,
			var contador = 1;
			while not contador == 11 do
				write("Consumido: ");
				write(buffer);
				soma := soma + buffer;
				contador := contador + 1;
				if contador == 11 then
					write("Total contabilizado: ");
					write(soma)
				else skip;
				yield
		}
	},
	var contador = 0;
	while not contador == 10 do
		resume produtor();
		resume consumidor();
		contador := contador + 1
}
```

Referências
-------------
* [IN1007 Paradigmas de Linguagens de Programação](https://www.cin.ufpe.br/~in1007/)
* [Linguagem Imperativa 2](https://www.cin.ufpe.br/~in1007/linguagens/Imperativa2/imperativa2.html)
* Barclay, A. (2009) A Language of Coroutines. The University of Bath.
* Deitel, P., & Deitel, H. (2015). Java How to program. Prentice Hall Press.
* Dovland, S. (2006) Liberating Coroutines: Combining Sequential and Parallel Execution. Department of Informatics, University of Oslo.
* Helsgaun, K. (2000). Discrete Event Simulation in Java. Department of Computer Science, Roskilde University.
* Moura, A. L.; Rodriguez, N. & Ierusalimschy, R. (2004) Coroutines in Lua. Journal of Universal Computer Science, vol. 10, no. 7, 910-925
* Moura, A. L. D. & Ierusalimschy, R. (2009) Revisiting Coroutines ACM Trans. Program. Lang. Syst., ACM, 2009, 31, 6:1-6:31
* Sebesta, R. W. (2015). Concepts of programming languages. Pearson Education.



