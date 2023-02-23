import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;

public class Aula03Optional {
    public static void main(String[] args) {

        // 00 - Forma comum de usar
        String numeroStr = "10";
        Integer numeroConvertido = converteStringEmNumero(numeroStr);
        System.out.println("Modo comum: " + numeroConvertido);

        // 01 - Usando com Optional
        converteStringEmNumero_02(numeroStr).ifPresent(n -> System.out.println("Com Optional: " + n));

        Integer numeroOrElse = converteStringEmNumero_02(numeroStr).orElse(-1);
        System.out.println("Com Optional-orElse: " + numeroOrElse);

        Integer numeroOrElseGet = converteStringEmNumero_02(numeroStr).orElseGet(() -> {
            return invocaMetodoQualquer();
        });
        System.out.println("Com Optional-orElseGet: " + numeroOrElseGet);

        Integer numeroOrElseThrow = converteStringEmNumero_02(numeroStr)
        .orElseThrow(() -> new NullPointerException("Valor vazio."));
        System.out.println("Com Optional-orElseThrow: " + numeroOrElseThrow);
        
        // 02 - Usando com Optional para tipos primitivos
        int numeroInt = converteStringEmNumero_03(numeroStr)
        .orElseThrow(() -> new NullPointerException("Valor vazio."));
        System.out.println("Com OptionalInt: " + numeroInt);
        

        // EXEMPLO DE STREAM
        Stream.of(15, 2, 36)
                .findFirst()
                .ifPresent(System.out::println);
    }

    private static Integer invocaMetodoQualquer() {
        return -1000;
    }

    // 00 - Forma comum de usar
    public static Integer converteStringEmNumero(String numeroStr) {

        try {

            return Integer.valueOf(numeroStr);
        } catch (Exception e) {

            System.err.println("Isso não é um número!");
            return null;
        }
    }

    // 01 - Usando com Optional
    public static Optional<Integer> converteStringEmNumero_02(String numeroStr) {

        try {

            Integer numeroConvertidoEmInteger = Integer.valueOf(numeroStr);
            return Optional.of(numeroConvertidoEmInteger);
        } catch (Exception e) {

            return Optional.empty();
        }
    }
    
    // 02 - Usando com Optional para tipos primitivos
    public static OptionalInt converteStringEmNumero_03(String numeroStr) {

        try {

            int numeroConvertidoEmInteger = Integer.parseInt(numeroStr);
            return OptionalInt.of(numeroConvertidoEmInteger);
        } catch (Exception e) {

            return OptionalInt.empty();
        }
    }
}

// OPTIONAL TEM O OBJETIVO DE SER USADA COMO RETORNO DE MÉTODO, não use como argumento -> ex: Optional<String>
// Com a classe Optional, podemos trabalhar com classes, valor null e vlrs primitivos (int, long, double, etc.).
// 00 - se passar uma palavra ao invés de um nº em formato de string, ocorre
// exception e precisamos tratar com try-catch
// Em 'return Optional.of(numeroConvertidoEmInteger);', ao invés de '.of()'
// podemos usar '.ofNullable()', se acaso pudermos receber um valor null.
// Método '.isPresent()' verifica se o valor está preenchido ou não(return true
// or false)
// Método '.get()' pega o vlr dentro do Optional, entretanto, se não houver vlr,
// gera um exception
// Método 'ifPresent()', recebe uma expressão lambda. Logo, o nº é recebido como
// argumento da expressão lambda. Então, no caso, se enviamos 'Optional.empty()'
// para essa expressão, ela não fara nada, como no exemplo.
// Método 'orElse', seguindo o mesmo padrão acima, retorna o esperado ou então
// retorna algo pré-escolhido com base no tipo se acaso a entrada não for a
// esperada para retornar uma saída padrão, ou seja, no exemplo, se receber uma
// string ao invés de um nº em formato de string, vai retornar o que vc escolher
// como retorno padrão, no caso, precisa ser um outro nº.
// Método 'orElseGet' semelhante ao método acima, entretanto recebe uma
// expressão lambda, logo, poderíamos retornar uma operação mais 'pesada'.
// Método 'orElseThrow' recebe uma expressão lambda e, caso esteja vazio o
// Optional, ele lança uma exception
// No EXEMPLO DE STREAM, ele pode receber um optional vazio que não imprimirá nada, caso contrário, imprime o 1º nº.