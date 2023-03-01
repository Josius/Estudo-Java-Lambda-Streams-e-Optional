import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Aula09InterfacesFuncionais {

    public static void main(String[] args) {

        // Supplier
        Stream.generate(() -> new Random().nextInt())
                .limit(7)

                // Consumer
                .forEach((e) -> System.out.println(e));

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        list.stream()
                // Predicate
                .filter(e -> e % 2 == 0)
                // Function
                .map(e -> e.doubleValue())
                // UnaryOperator
                .reduce((e1, e2) -> e1 + e2)
                .ifPresent(System.out::println);
    }
}
