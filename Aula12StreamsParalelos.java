import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Aula12StreamsParalelos {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 4, 6);

        // parallel
        // IntStream.range(0, 5).parallel()

        // parallelStream
        // list.parallelStream()
        System.out.println("forEachOrdered");
        // forEach e forEachOrdered
        list.parallelStream()
                .forEachOrdered(System.out::println);

        // findAny
        System.out.println("findAny com parallelStream");
        list.parallelStream()
                .findAny()
                .ifPresent(System.out::println);

        System.out.println("findAny com stream");
        list.stream()
                .findAny()
                .ifPresent(System.out::println);

        // unordered: skip, limit, distinct
        System.out.println("unordered: skip-limit-distinct");
        list.parallelStream()
                .unordered()
                .skip(1)
                .distinct()
                .limit(6)
                .forEach(System.out::println);

        // reduce: acumuladores associativos
        System.out.println("reduce: acumuladores associativos");
        list.parallelStream()
                .reduce((n1, n2) -> n1 + n2)
                .ifPresent(System.out::println);

        // collect: toMap e toConcurrentMap
        System.out.println("collect: toMap, toConcurrentMap");
        Map<Integer, Boolean> collect = list.parallelStream()
                .distinct()
                .collect(
                        Collectors.toConcurrentMap(n -> n, n -> n % 2 == 0));
        System.out.println(collect);

        System.out.println("collect: groupingBy, groupingByConcurrent");
        // collect: groupingBy
        Map<Boolean, List<Integer>> collect2 = list.parallelStream()
                .distinct()
                .collect(
                        Collectors.groupingByConcurrent(n -> n % 2 == 0));
        System.out.println(collect2);
    }
}
