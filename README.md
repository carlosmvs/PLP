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
-------------
A seguintes expressões foram adicionadas a [gramática](https://www.cin.ufpe.br/~in1007/linguagens/Imperativa2/imperativa2.html) da linguagem:
```bnf
     
DeclaracaoCorotina ::= "cor" Id "(" [ ListaDeclaracaoParametro ] ")" [": Tipo"] "{" Comando "}"

ChamadaCorotina ::= "resume" Id "(" [ ListaExpressao ] ")"

InterrupcaoCorotina ::= "yield" [ Expressao ]

Retorno ::= "return" [ Expressao ]
```
E as seguintes alteradas:
```bnf
Comando ::= Atribuicao
| ComandoDeclaracao
| While
| IfThenElse
| IO
| Comando ";" Comando
| Skip
| ChamadaProcedimento
| ChamadaCorotina
| InterrupcaoCorotina
| Retorno

Expressao ::= Valor | ExpUnaria | ExpBinaria | Id | ChamadaProcedimento | ChamadaCorotina

Declaracao ::= DeclaracaoVariavel
| DeclaracaoProcedimento
| DeclaracaoCorotina
| DeclaracaoComposta
```
Referências
-------------
* [IN1007 Paradigmas de Linguagens de Programação](https://www.cin.ufpe.br/~in1007/)
* [Linguagem Imperativa 2](https://www.cin.ufpe.br/~in1007/linguagens/Imperativa2/imperativa2.html)
* Sebesta, R. W. (2015). Concepts of programming languages. Pearson Education.
* Zhang, W., Larsen, P., Brunthaler, S., & Franz, M. (2014, October). Accelerating iterators in optimizing AST interpreters. In ACM SIGPLAN Notices (Vol. 49, No. 10, pp. 727-743). ACM.