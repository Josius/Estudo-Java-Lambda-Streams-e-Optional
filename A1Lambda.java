
public class A1Lambda {

    public static void main(String[] args) {
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Caso comum sem Lambda");
            }
        }).run();

        new Thread(() -> System.out.println("Usando Lambda")).run();
    }    
}