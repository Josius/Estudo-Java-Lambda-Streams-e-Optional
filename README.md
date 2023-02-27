# Estudos-Java-Lambda-Streams-e-Optional

Fonte: https://www.youtube.com/playlist?list=PLuYctAHjg89ZkhgOQo0zcTtmHY5nuRYud

## Aula 01 
Explicação sobre o conceito de Lambda, SAM - Single Abstract Method e uma pequena introdução a Stream.

## Aula 02 - part 01
Explicação sobre Stream (fluxo de dados), um contexto de como era implementado antes e como é agora com stream, apresentações de alguns métodos/funções intermediárias (skip, limit, distinct, filter, forEach, map) e filtros vem antes das transformações.

## Aula 02 - part 02
Métodos/funções/operações finais (count, min, max, collect), os quais fecham o stream; se precisar usar duas, precisa criar dois (ou mais streams) e armazenar os resultados em variáveis. Operações finais:
* count -> quantidade de elementos no stream
* min -> menor valor no stream
* max -> maior valor no stream
* collect -> processa os elementos do stream, acumula-os e com uma função de combinação mescla-os e retorna o resultado.
	* *.collect(Collectors.toList())* -> armazena tudo numa lista
	* *.collect(Collectors.groupingBy(**parâmetro para agrupar**))* -> agrupa elementos com base em um ***parâmetro para agrupar***
	* *.collect(Collectors.joining())* -> trabalha com string, concatenando, usar um separador, etc

## Aula 03 - Classe Optional
* OPTIONAL TEM O OBJETIVO DE SER USADA COMO RETORNO DE MÉTODO, não o use para receber argumento -> ex: `public static Optional nomeDoMetodo(Optional<String> argumentoRecebido)`
* Com a classe Optional, podemos trabalhar com classes, valor null e vlrs primitivos (int, long, double, etc.).
* 00 - se passar uma palavra ao invés de um nº em formato de string, ocorre exception e precisamos tratar com try-catch
* Em 'return Optional.of(numeroConvertidoEmInteger);', ao invés de '.of()' podemos usar '.ofNullable()', se acaso pudermos receber um valor null.
* Método '.isPresent()' verifica se o valor está preenchido ou não(return true or false)
* Método '.get()' pega o vlr dentro do Optional, entretanto, se não houver vlr, gera um exception
* Método 'ifPresent()', recebe uma expressão lambda. Logo, o nº é recebido como argumento da expressão lambda. Então, no caso, se enviamos 'Optional.empty()' para essa expressão, ela não fara nada, como no exemplo.
* Método 'orElse', seguindo o mesmo padrão acima, retorna o esperado ou então retorna algo pré-escolhido com base no tipo se acaso a entrada não for a esperada para retornar uma saída padrão, ou seja, no exemplo, se receber uma string ao invés de um nº em formato de string, vai retornar o que vc escolher como retorno padrão, no caso, precisa ser um outro nº.
* Método 'orElseGet' semelhante ao método acima, entretanto recebe uma expressão lambda, logo, poderíamos retornar uma operação mais 'pesada'.
* Método 'orElseThrow' recebe uma expressão lambda e, caso esteja vazio o Optional, ele lança uma exception
* No EXEMPLO DE STREAM, ele pode receber um optional vazio que não imprimirá nada, caso contrário, imprime o 1º nº.

## Aula 04 - Reduce em Streams
**Função do Reduce:** ele é uma função associativa, ou seja, agregar todos os elementos e retornar um único elemento com base na função de agregação. Reduce é voltado para trabalhar com valores/objetos imutáveis. Como no caso do exemplo de Concatenação, fazemos a construção da string usando a função .concat(). Logo, esse objeto é imutável, pois se tentarmos usar um StringBuilder neste caso, não conseguiremos, pois o StringBuilder é um objeto mutável. Neste caso, usamos o Collect ao invés do Reduce.
&nbsp;

**Estrutura:**
> `.reduce(valor de identidade, função de acumulação, função de combinação);`

### **Acumulação:** 
> `List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);`
&nbsp;

> `Optional<Integer> reduceSoma = list.stream().reduce((n1, n2) -> n1 + n2);`
* No exemplo acima soma-se os números, n1 = 1º nº da lista e n2 = 2º nº da lista, soma-se, n1 agora é o resultado de n1+n2 e n2 = 3º nº da lista, e assim sucessivamente.
* O mesmo pode pode ser feito para multiplicação. Subtração e divisão não são associativos, logo, não deveriam ser usados em Reduce, pois se usarmos **'.parallel()'**, podemos ter um resultado diferente.
	* **'.parallel()'** - ele divide o Stream em vários grupos para serem processados separadamente por threads diferentes, e por fim são todos agrupados. Por exemplo, digamos que temos o seguinte grupo de nºs: 1, 2, 3, 4, 5, 6. Com .parallel() e soma, digamos que ele separaria assim: (1+2)+(3+4)+(5+6), o qual geraria o mesmo resultado de: 1+2+3+4+5+6. O mesmo não ocorreria com subtração, pois: 1-2-3-4-5-6 possuí resultado diferente de: (1-2)-(3-4)-(5-6).

**Concatenação**
* Com o mesmo sentido, podemos usar Reduce em String, por exemplo, pegamos uma frase, criamos um array dessa String, separando-a por espaço, adicionamos a uma List e, semelhante acima, reduzimos essa List em uma única String com todas as palavras agrupadas, ou seja, sem espaço.

**Valor de Identidade**
> `List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);`
&nbsp;

> `Integer reduceSoma = list.stream().reduce(0, (n1, n2) -> n1 + n2);`
* Para soma, o vlr de identidade é 0, para multiplicação é 1 e para Strings é uma string vazia "". No caso, o vlr de identidade será o n1 e n2 será o 1º elemento da lista, e a função Reduce segue normalmente. Motivos para usar o Valor de Identidade: para termos um valor padrão, por exemplo, se não usarmos o Valor de Identidade, a variável é um Optional, o que pode retornar um Optional vazio, já com o Valor de Identidade, se a lista estiver vazia, ele retornará o próprio Valor de Identidade.

**Reduce Menor Valor**
* Ver o código da aula para entender. Também podemos usar alguns métodos da classe Math para conseguir um resultado, como Math.min(), e se quisermos usar o Valor Identidade, por exemplo de um double, podemos usar uma constante para isso.

### **Função de Combinação**
* É a função que o Reduce chama quando ele divide em várias partes os elementos do Stream. Logo, se estivermos executando um Stream paralelo, o Stream pode ser quebrado em vários grupos, e o Reduce aplicará a função de acumulação nesses grupos, e depois aplicará a função de combinação entre esses grupos. Isso abre a possibilidade de executar algo mais perfomático.
* Seguindo o exemplo **Combinação performática** apresentado no código, ele pega a lista de números, converte para String e concatena os nºs. Primeiro usa o Valor Identidade para String; na Função de Acumulação transforma os inteiros para String concatenando-os em grupos; na Função de Combinação concatenamos os grupos.

## Aula 05 - Collect em Streams
**Estrutura:**
> `.collect(supplier, accumulator, combiner);`
Com estrutura semelhante a Reduce, com diferença em relação ao 1º argumento, o supplier.

### **Supplier**
Função lambda que fornecerá a instância do objeto que queremos usar para acumular o resultado.

### **Acumulator**
Recebe dois argumentos, um é a própria lista gerada pelo supplier, o outro é um dos elementos do stream.

### **Combiner**
Ele pega as duas threads do Acumulator e gera a saída esperada.