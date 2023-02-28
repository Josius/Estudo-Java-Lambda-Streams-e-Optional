import java.util.stream.IntStream;

public class Aula06DiferentesFormasLambda {

    public static void main(String[] args) {

        // Parênteses
        Runnable runnable = () -> System.out.println("Hello there");

        IntStream.range(0, 5)
                .filter((int n) -> n % 2 == 0)
                .reduce((int n1, int n2) -> n1 + n2)
                .ifPresent(System.out::println);

        // Chaves
        IntStream.range(0, 5)
                .filter((int n) -> {
                    System.out.println("General Kenobi!");
                    return n % 2 == 0;
                })
                .forEach(System.out::println);
    }
}
