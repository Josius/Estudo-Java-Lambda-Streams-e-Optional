import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class A1LambdaStream {
    public static void main(String[] args) {
        
        List<Integer> lista = Arrays.asList(1,2,3,4);
        
        // Caso Stream sem Lambda
        lista.stream().filter(new Predicate<Integer>() {
            
            @Override
            public boolean test(Integer e){

                return e % 2 == 0;
            }
        })
        .forEach(new Consumer<Integer>() {
            
            public void accept(Integer e){
                System.out.println(e);
            }
        });

        // Caso Stream com Lambda
        lista.stream()
            .filter(e -> e % 2 == 0)
            .forEach(e -> System.out.println(e));
    }
}
