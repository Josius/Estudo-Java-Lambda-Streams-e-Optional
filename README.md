# Estudos-Java-Lambda-Streams-e-Optional

Fonte: https://www.youtube.com/playlist?list=PLuYctAHjg89ZkhgOQo0zcTtmHY5nuRYud

Aula 01 - Explicação sobre o conceito de Lambda, SAM - Single Abstract Method e uma pequena introdução a Stream.

Aula 02 - part 01 - Explicação sobre Stream (fluxo de dados), um contexto de como era implementado antes e como é agora com stream, apresentações de alguns métodos/funções intermediárias (skip, limit, distinct, filter, forEach, map) e filtros vem antes das transformações.

Aula 02 - part 02 - Métodos/funções/operações finais (count, min, max, collect), os quais fecham o stream; se precisar usar duas, precisa criar dois (ou mais streams) e armazenar os resultados em variáveis. Operações finais:
* count -> quantidade de elementos no stream
* min -> menor valor no stream
* max -> maior valor no stream
* collect -> processa os elementos do stream, acumula-os e com uma função de combinação mescla-os e retorna o resultado.
	* *.collect(Collectors.toList())* -> armazena tudo numa lista
	* *.collect(Collectors.groupingBy(**parâmetro para agrupar**))* -> agrupa elementos com base em um ***parâmetro para agrupar***
	* *.collect(Collectors.joining())* -> trabalha com string, concatenando, usar um separador, etc

Aula 03 - Classe Optional
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
