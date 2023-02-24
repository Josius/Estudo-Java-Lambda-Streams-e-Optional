import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

public class Aula04Reduce {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Accumulator
        Optional<Integer> reduceSoma = list.stream()
                .parallel()
                .reduce((n1, n2) -> n1 + n2);
        System.out.println(reduceSoma.get());

        // NÃO FAÇA
        Optional<Integer> reduceSubtracao = list.stream()
                .parallel()
                .reduce((n1, n2) -> n1 - n2);
        System.out.println(reduceSubtracao.get());

        // Concatenação
        String frase = "Cerveja Tem Que Ser Servida Bem Gelada";
        String[] arrayFrase = frase.split(" ");
        List<String> listFrase = Arrays.asList(arrayFrase);

        Optional<String> reduceConcatenacao = listFrase.stream()
                .reduce((s1, s2) -> s1.concat(s2));
        System.out.println(reduceConcatenacao.get());

        // VALOR DE IDENTIDADE
        List<Integer> listVazia = Arrays.asList();
        Integer reduceSomaVazia = listVazia.stream()
                .reduce(0, (n1, n2) -> n1 + n2);
        System.out.println("Lista Vazia - Valor Identidade de Soma: " + reduceSomaVazia);

        Integer reduceSoma2 = list.stream()
                .reduce(0, (n1, n2) -> n1 + n2);
        System.out.println(reduceSoma2);

        Integer reduceMultiplicacao = list.stream()
                .reduce(1, (n1, n2) -> n1 * n2);
        System.out.println(reduceMultiplicacao);

        String reduceConcatenacao2 = listFrase.stream()
                .reduce("", (s1, s2) -> s1.concat(s2));
        System.out.println(reduceConcatenacao2);

        // Reduce Menor Valor
        OptionalDouble menorValor = DoubleStream.of(2.5, 4.9, 7.3, 8.1, 6.0)
                .reduce((d1, d2) -> Math.min(d1, d2));
        System.out.println(menorValor);

        // Reduce Menor Valor com Valor Identidade
        double menorValorIdentidade = DoubleStream.of(2.5, 4.9, 7.3, 8.1, 6.0)
                .reduce(Double.POSITIVE_INFINITY, (d1, d2) -> Math.min(d1, d2));
        System.out.println(menorValorIdentidade);

        // Combinação
        Integer reduceSomaCombinacao = list.stream()
                .parallel()
                .reduce(0, (n1, n2) -> n1 + n2, (n1, n2) -> n1 + n2);
        System.out.println(reduceSomaCombinacao);

        // Combinação performática
        String reduce = list.stream()
                .parallel()
                .reduce(
                        "",
                        ((n1, n2) -> n1.toString().concat(n2.toString())),
                        (n1, n2) -> n1.concat(n2));
        System.out.println(reduce);
    }
}
