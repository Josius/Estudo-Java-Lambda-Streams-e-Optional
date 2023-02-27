import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Aula05Collect {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        Set<String> collect = list.stream()
                .collect(
                        () -> new HashSet<>(),
                        (l, e) -> l.add(e.toString()),
                        (l1, l2) -> l1.addAll(l2));

        System.out.println("list: " + list);
        System.out.println("collect: " + collect);

        // toList
        List<Integer> collect2 = list.stream()
                .filter((n) -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("collect2: " + collect2);

        // toSet
        Set<Integer> collect3 = list.stream()
                .filter((n) -> n % 2 != 0)
                .collect(Collectors.toSet());
        System.out.println("collect3: " + collect3);

        // toCollect
        Deque<Integer> collect4 = list.stream()
                .filter((n) -> n % 2 != 0)
                .collect(Collectors.toCollection(
                        () -> new ArrayDeque<>()));
        System.out.println("collect4: " + collect4);

        // joining - usa internamente um StringBuilder
        String join = list.stream()
                .map(n -> n.toString())
                .collect(Collectors.joining("-"));
        System.out.println(join);

        // averaging
        Double media = list.stream()
                .collect(Collectors.averagingInt(n -> n.intValue()));
        System.out.println("media: " + media);

        // summing
        Integer soma = list.stream()
                .collect(Collectors.summingInt(n -> n.intValue()));
        System.out.println("soma: " + soma);

        // summarizing
        IntSummaryStatistics status = list.stream()
                .collect(Collectors.summarizingInt(n -> n.intValue()));
        System.out.println("status: " + status.getAverage());
        System.out.println("status: " + status.getCount());
        System.out.println("status: " + status.getMax());
        System.out.println("status: " + status.getMin());
        System.out.println("status: " + status.getSum());

        // counting
        Long count = list.stream()
                .filter((n) -> n % 2 == 0)
                .collect(Collectors.counting());
        System.out.println(count);

        // max/min
        list.stream()
                .filter((n) -> n % 2 == 0)
                .collect(Collectors.maxBy(Comparator.naturalOrder()))
                .ifPresent(System.out::println);

        // groupingBy
        Map<Integer, List<Integer>> groupingBy = list.stream()
                .collect(Collectors.groupingBy((n) -> n % 3));
        System.out.println("groupingBy: " + groupingBy);

        // partitioningBy
        Map<Boolean, List<Integer>> partitioningBy = list.stream()
                .collect(Collectors.partitioningBy((n) -> n % 3 == 0));
        System.out.println("partitioningBy: " + partitioningBy);

        // toMap
        Map<Integer, Double> toMap = list.stream()
                .collect(Collectors.toMap(
                        n -> n,
                        n -> Math.pow(n.doubleValue(), 5)));
        System.out.println("toMap: " + toMap);

    }
}
