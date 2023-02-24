import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    }
}
