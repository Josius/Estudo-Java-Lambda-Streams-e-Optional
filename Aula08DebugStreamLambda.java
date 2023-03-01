import java.util.Arrays;
import java.util.List;

public class Aula08DebugStreamLambda {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(32, 45, 1, 8, 76, 928, 3);

        // chaves
        list.stream()
                .map((n) -> {
                    StringBuilder builder = new StringBuilder();
                    builder.append(n);
                    builder.append("s");
                    builder.append("a");
                    return builder;
                })
                .forEach(System.out::println);

        System.out.println();
        
        // método
        list.stream()
        .map((n) -> {
            return converteParaStringBuilder(n);
        })
        .forEach(System.out::println);
        
        System.out.println();

        // método peek
        list.stream()
                .peek(n -> System.out.println(n + " antes da alteração"))
                .map((n) -> new StringBuilder().append(n).append("s").append("a"))
                .peek(n -> System.out.println(n + " depois da alteração"))
                .forEach(System.out::println);
    }

    private static StringBuilder converteParaStringBuilder(Integer n) {
        StringBuilder builder = new StringBuilder();
        builder.append(n);
        builder.append("a");
        builder.append("s");
        return builder;
    }
}
