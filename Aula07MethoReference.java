import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Aula07MethoReference {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        // forma comum
        list.stream()
                .forEach((n) -> System.out.println("forma comum: " + n));
        // com method reference
        System.out.println("method reference");
        list.stream()
                .forEach(System.out::println);

        // métodos estáticos
        System.out.println("métodos estáticos - forma comum: ");
        list.stream()
                .map((n) -> multipliquePorDois(n))
                .forEach(System.out::println);

        System.out.println("métodos estáticos - method reference:");
        list.stream()
                .map(Aula07MethoReference::multipliquePorDois)
                .forEach(System.out::println);

        // construtores
        System.out.println("construtores - forma comum: ");
        list.stream()
                .map((n) -> new BigDecimal(n))
                .forEach(System.out::println);

        System.out.println("construtores - method reference:");
        list.stream()
                .map(BigDecimal::new)
                .forEach(System.out::println);

        // várias instâncias
        System.out.println("várias instâncias - forma comum: ");
        list.stream()
                .map((n) -> n.doubleValue())
                .forEach(System.out::println);

        System.out.println("várias instâncias - method reference:");
        list.stream()
                .map(Integer::doubleValue)
                .forEach(System.out::println);

        // única instância
        System.out.println("única instâncias - forma comum: ");
        BigDecimal dois = new BigDecimal(2);
        list.stream()
                .map(BigDecimal::new)
                .map((b) -> dois.multiply(b))
                .forEach(System.out::println);

        System.out.println("única instâncias - method reference:");
        list.stream()
                .map(BigDecimal::new)
                .map(dois::multiply)
                .forEach(System.out::println);
    }

    private static Integer multipliquePorDois(Integer i) {

        return i * 2;
    }
}
