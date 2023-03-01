import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Aula10CriacaoDeStreams {

    public static void main(String[] args) throws IOException {

        // Collection
        System.out.println("Collection:");
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        list.stream()
                .forEach(System.out::println);

        // Arrays
        System.out.println("\nArrays:");
        Integer[] arrayDeInteiros = new Integer[] { 1, 2, 3, 4 };
        Arrays.stream(arrayDeInteiros)
                .forEach(System.out::println);

        // Stream.of
        System.out.println("\nStream.of:");
        Stream
                .of(
                        "Aenean egestas nunc fringilla rutrum dictum", "In hac habitasse platea dictumst",
                        "Sed laoreet magna sem. Duis dictum interdum augue",
                        " eu imperdiet lorem interdum quis. Suspendisse potenti. Fusce efficitur nulla quam",
                        " ut sagittis erat congue ultrices. Quisque viverra dignissim rutrum. Sed rhoncus lectus sit amet augue dignissim",
                        " et ultricies enim efficitur. Nunc sed est id purus elementum mollis ut in magna. Nulla commodo neque at lorem ornare",
                        " in vulputate neque mattis. Vestibulum rhoncus ex ipsum",
                        " et porttitor nulla malesuada sed. Quisque in scelerisque est. Sed ornare ac turpis fringilla lobortis. Sed risus magna",
                        " rhoncus blandit maximus nec",
                        " auctor id mi")
                .forEach(System.out::println);

        // IntStream.range
        System.out.println("\nIntStream.range:");
        IntStream
                .range(0, 5)
                .forEach(System.out::println);

        // Stream.iterate
        System.out.println("\nStream.iterate:");
        Stream
                .iterate(2, n -> n * 2)
                .limit(10)
                .map((n) -> new StringBuilder().append(n).append(" bits"))
                .forEach(System.out::println);

        // BufferedReader - lines
        System.out.println("\nBufferedReader:");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("texto.txt")));

        bufferedReader
                .lines()
                .forEach(System.out::println);
        bufferedReader.close();

        // Files
        System.out.println("\nFiles:");
        Path p = Paths.get("Aula10LoremIpsium");
        Files.list(p).forEach(System.out::println);

        // Random
        System.out.println("\nRandom:");
        new Random()
                .ints()
                .limit(7)
                .forEach(System.out::println);
        
        // Pattern
        System.out.println("\nPattern:");
        String string = "new String() na área gente!";
        Pattern pattern = Pattern.compile(" ");
        pattern.splitAsStream(string).forEach(System.out::println);
    }
}
