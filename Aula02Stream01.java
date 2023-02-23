import java.util.Arrays;
import java.util.List;

public class Aula02Stream01 {
    public static void main(String[] args) {

        // Lista original não é modificada
        List<Integer> lista = Arrays.asList(1, 5, 8, 9, 1, 4, 2, 7, 6, 8, 43, 3, 9, 8, 1, 2, 23, 78, 45, 65, 12, 12, 54,
                78, 23);

        System.out.println(lista);
        System.out.printf(
                "Regras%n skip: pule os dois 1ºs nºs%n limit: máx de nºs:  8%n distinct: não repetir nºs%n filter: somente nºs pares%n");
        lista.stream()
                .skip(2)
                .limit(8)
                .distinct()
                .filter(e -> e % 2 == 0)
                .forEach(System.out::println);

        System.out.printf("Transformação de dados:%n map: eleve ao quadrado todos os nºs da lista%n");
        lista.stream()
                .map(e -> Math.pow(e, 2))
                .forEach(System.out::println);
    }
}
