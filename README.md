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

## Aula 06 - Diferentes formas de escrever uma expressão Lambda
### **Forma padrão**
> `.filter(n -> n % 2 == 0)`
### **Quando usar parênteses**
1º - Informar o tipo do parâmetro
> `.filter((int n) -> n % 2 == 0)`

2º - Com dois argumentos
> `.reduce((n1, n2) -> n1 + n2)`

3º - Informar o tipo do parâmetro e com dois argumentos
> `.reduce((int n1, int n2) -> n1 + n2)`

4º - Sem nenhum argumento
> `Runnable runnable = () -> System.out.println("Hello there");`

### **Quando usar chaves**
1º - Se usar chaves na 2ª parte da expressão Lambda precisa usar *return* e *;*
> `.filter((int n) -> {return n % 2 == 0;})`

2º - Com chaves, podemos ter mais de uma linha na expressão Lambda

```
.filter((int n) -> {
                    System.out.println("General Kenobi!");
                    return n % 2 == 0;
                })
```

## Aula 07 - Method Reference
Síntaxe não muito complicada e ajuda a poupar código quando vamos escrever funções Lambda.
> `.forEach(System.out::println);`

O method reference são os **_::_**

### **Métodos estáticos**
Forma Comum:
> `.map((n) -> Método_Estático(n))`

Síntaxe:
> `.map(Nome_da_Classe_Que_Contém_O_Método_Estático::Método_Estático)`

### **Construtores**
Forma Comum:
> `.map((n) -> new BigDecimal(n))`

Síntaxe:
> `.map(BigDecimal::new)`

### **Várias Instâncias**
Para cada n é chamada o doubleValue(), por isso, várias instâncias.

Forma Comum:
> `.map((n) -> n.doubleValue())`

Síntaxe:
> `.map(Integer::doubleValue)`

### **Única Instância**
Forma Comum:
```
BigDecimal dois = new BigDecimal(2);
        list.stream()
                .map(BigDecimal::new)
                .map((b) -> dois.multiply(b))
                .forEach(System.out::println);
```

Síntaxe:
> `.map(dois::multiply)`

## Aula 08 - Debug de Streams e funções Lambda
### **Modo com chaves**
A princípio, se depurarmos uma função Lambda, sendo que esta função esteja no modo padrão:
> `.map((n) -> new StringBuilder().append(n).append("s").append("a"))`

a depuração ficará confusa e não conseguiremos entender o passo a passo que ocorre. Neste caso, o ideal é usar a função em modo com chaves:

```
.map((n) -> {
                    StringBuilder builder = new StringBuilder();
                    builder.append(n);
                    builder.append("s");
                    builder.append("a");
                    return builder;
                })
```
Desta forma será possível verificar o passo a passo da depuração.

### **Usando um método**
Ao invés de criar um bloco de código com chaves e colocar toda a execução dentro da função Lambda, podemos criar um método com o código e simplesmente chamar o método na função:
```
    list.stream()
                .map((n) -> {
                    return converteParaStringBuilder(n);
                })
                .forEach(System.out::println);
```

```

    private static StringBuilder converteParaStringBuilder(Integer n) {
        StringBuilder builder = new StringBuilder();
        builder.append(n);
        builder.append("a");
        builder.append("s");
        return builder;
    }
```
### **Método Peek**
Peek é um método que a interface Stream possui. Ele foi feito com o intuíto de ser utilizado para log e depuração, ao invés de executar uma tarefa mais complexa. Ele pode ser usando entre várias operações.
```
list.stream()
                .peek(n -> System.out.println(n + " antes da alteração"))
                .map((n) -> new StringBuilder().append(n).append("s").append("a"))
                .peek(n -> System.out.println(n + " depois da alteração"))
                .forEach(System.out::println);
```

Então, se tivermos um Stream com várias funções/operações (filter(), map(), etc.) podemos usar o peek entre cada uma dessas operações para saber o que ocorre.

## Aula 09 - Interfaces Funcionais
### **Supplier**
Interface funcional que não recebe nenhum argumento e entrega um valor.
```
@FunctionalInterface
public interface Supplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get();
}
```

O seguinte código usa um generate, o qual usa um Supplier:
```
Stream.generate(() -> new Random().nextInt())
                .limit(7)
                .forEach(System.out::println);
```

### **Consumer**
Interface funcional que recebe um argumento/valor mas não retorna nada, somente usa o argumento/valor. 
```
@FunctionalInterface
public interface Consumer<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t);
    ...
```

O forEach é um exemplo:
> `.forEach((e) -> System.out.println(e));`

### **BiConsumer**
Semelhante a interface acima, mas com a diferença de que recebe dois argumentos e não retorna nada.

### **Predicate**
Interface funcional que recebe um valor e retorna um booleano, ou seja, retorna o resultado de uma comparação que resulte em true ou false.
```
@FunctionalInterface
public interface Predicate<T> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean test(T t);
    ...
```

Por exemplo:
> `.filter(e -> e % 2 == 0)`

### **BiPredicate**
Semelhante a interface acima, mas recebe dois valores e faz um teste com esses dois valores.

### **Function**
Interface funcional que recebe um valor de um tipo e retorna um valor de outro tipo.
```
@FunctionalInterface
public interface Function<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t);
    ...
```

Por exemplo, o map:
> `.map(e -> e.doubleValue())`

### **BiFunction**
Semelhante a interface acima, mas recebe dois valores e retorna um valor.

### **UnaryOperator**
Interface que extende a uma Function, semelhante a ela inclusive, entretanto, ao invés de receber um valor de um tipo e retornar outro valor de outro tipo, UnaryOperator recebe um valor de um tipo e retorna um outro valor do mesmo tipo. Ou seja, a entrada e a saída são do mesmo tipo.

```
@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {

    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @param <T> the type of the input and output of the operator
     * @return a unary operator that always returns its input argument
     */
    static <T> UnaryOperator<T> identity() {
        return t -> t;
    }
}
```

### **BinaryOperator**
Semelhante a interface acima, mas recebe dois argumentos de um tipo e retorna um valor do mesmo tipo que os dois argumentos de entrada. BinaryOperator extende a uma BiFunction.
Por exemplo, o reduce:
> `.reduce((e1, e2) -> e1 + e2)`

## Aula 10 - Criação de Stream
### **Collection**
Qualquer classe que estenda ou implemente Collection terá o método stream e o método parallelStream.

### **Arrays**
Com arrays podemos usar o método stream da classe Arrays para gerar uma stream:
> `Arrays.stream(arrayDeInteiros).forEach(System.out::println);`

### **Stream.of**
Criar um stream de qualquer tipo de objeto.
> `Stream.of("Aenean egestas nunc fringilla rutrum dictum", "In hac habitasse platea dictumst").forEach(System.out::println);`

### **IntStream.range e IntStream.rangeClosed**
Cria um stream de números sequênciais. Ele recebe dois nºs como argumentos. O 1º é o número inicial inclusivo do stream. Já o 2º é o último nº do stream, o qual pode ser exclusivo, se usar o .range, ou inclusivo, se usar o .rangeClosed.

### **Stream.iterate**
Passamos dois argumentos para ele, um é o valor inicial chamado de seed, e o outro é uma transformação que será aplicada nesse valor inicial. 

**NOTA:** Stream.iterate cria um stream infinito, logo, é imprescindível usar .limit().

### **BufferedReader**
BufferedReader é uma forma de ler arquivos em Java. Com o método .lines podemos ler as linhas de um arquivo como um stream.

### **Files**
Cria Stream, mas não com o conteúdo do arquivo, e sim com os arquivos que estão presentes num diretório.

### **Random**
Cria Stream de números aleatórios.
**NOTA:** Random().ints() cria um stream infinito, logo, é imprescindível usar .limit().

### **Pattern**
Usado com expressões regulares (Regex), também pode ser usado para criar Stream.
> `pattern.splitAsStream(string).forEach(System.out::println);`

## Aula 11 - Manipulando Collections com expressões Lambda
Alguns métodos de Collections podem receber expressões Lambda para facilitar seu uso.
## **Exemplos com List**
### **forEach**
Executa a mesma função como se estivesse em um Stream. Usável em qualquer classe que implemente a interface **_Iterable_**.
### **removeIf**
Remove elementos com base em uma expressão Lambda. Usável em qualquer classe que implemente a interface **_Collection_**.
### **replaceAll**
Altera vários elementos, recebendo um elemento e retornando outro elemento, o qual substituirá o 1º elemento. Usável em qualquer classe que implemente a interface **_List_**.

## **Exemplos com Map**
### **compute**
Operação que será executada em algum elemento do map. Se passarmos uma key que não está no mapa, ele acrescentará ela e como value colocará null + a alteração. Pode-se usar o compute dentro do forEach, como no código. Mas isso não é necessário, pois temos o replaceAll, descrito mais abaixo.
### **forEach**
Semelhante ao forEach, mas ao invés de usarmos a interface Consumer, usamos a BiConsumer, pois ele recebe dois elementos, no caso, a Key e o Value do Map.
### **merge**
Recebe 3 argumentos: key, value e  remappingFunction. Em remappingFunction usa-se uma função Lambda com dois argumentos, o 1º argumento é o valor que já está no map com referencia a key e o 2º argumento é referente ao value passado para o merge. Síntaxe:
> `map.merge(key, value, (1º argumento, 2º argumento) -> o que fazer com os dois argumentos);`

Exemplo:
> `map.merge(3, "***", (v1, v2) -> v1 + v2);`

Se passarmos uma key que não está no mapa, ele acrescentará ela e como value colocará o value do merge.
### **replaceAll**
Recebe key e value, indica o que alterar. No exemplo abaixo, não fizemos nada com a key, mas se necessário, poderíamos alterar também:
> `map.replaceAll((k, v) -> v + "%$%$%$%$%");`
