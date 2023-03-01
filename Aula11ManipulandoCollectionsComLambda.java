import java.util.ArrayList;
import java.util.HashMap;

public class Aula11ManipulandoCollectionsComLambda {
    
    public static void main(String[] args) {
        
        // Exemplos com List
        System.out.println("\nExemplos com List:");
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(7);

        // forEach
        list.forEach(n -> System.out.println("forEach: " + n));
        // removeIf
        list.removeIf(n -> n % 2 == 0);
        list.forEach(n -> System.out.println("removeIf par numbers: " + n));
        // replaceAll
        list.replaceAll(n -> n * 2);
        list.forEach(n -> System.out.println("replaceAll: " + n));

        // Exemplos com Map
        System.out.println("\nExemplos com Map:");
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "Hello");
        map.put(1, "There");
        map.put(2, "!");
        map.put(3, "General");
        map.put(4, "Kenobi");
        map.put(5, "!");

        // forEach
        System.out.println("\nforEach:");
        map.forEach((k, v) -> System.out.println(k + v));
        
        // compute
        System.out.println("\ncompute:");
        map.compute(1, (k, v) -> v + " depois!");
        map.forEach((k, v) -> map.compute(k, (h, i) -> v + " agora!"));
        map.forEach((k, v) -> System.out.println(k + v));

        // merge
        System.out.println("\nmerge:");
        map.merge(3, "***", (v1, v2) -> v1 + v2);
        map.forEach((k, v) -> System.out.println(k + v));
        
        // replaceAll
        System.out.println("\nreplaceAll:");
        map.replaceAll((k, v) -> "%$%$%$%$%" + v);
        map.forEach((k, v) -> System.out.println(k + v));
    }
}
