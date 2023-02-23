import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Aula02Stream02 {
	public static void main(String[] args) {

		List<Integer> lista = Arrays.asList(1, 5, 8, 9, 1, 4, 2, 7, 6, 8, 43, 3, 9, 8, 1, 2, 23, 78, 45, 65, 12, 12, 54,
				78, 23);

		// Operações finais fecham o stream, não é possível usar duas operações finais em um stream. É preciso usar um stream para cada operação final, valendo-se de atribuições para armazenar os resultados.
		long contagem = lista.stream()
				.map(e -> e * 2)
				.count();
		System.out.println("Quantos elementos há no stream: " + contagem);
		
		Optional<Integer> minimo =  lista.stream()
				.filter(e -> e % 2 == 0)
				.min(Comparator.naturalOrder());
		System.out.println("Qual o menor elemento par no stream: " + minimo.get());
		
		Optional<Integer> maximo =  lista.stream()
				.filter(e -> e  % 2 != 0)
				.max(Comparator.naturalOrder());
		System.out.println("Qual o maior elemento ímpar no stream: " + maximo.get());

		List<Integer> listaDeNumerosPares = lista.stream()
				.filter(e -> e  % 2 == 0)
				.distinct()
				.collect(Collectors.toList());
		System.out.println("Qual são os elementos pares no stream: " + listaDeNumerosPares);

		Map<Boolean, List<Integer>> mapaDeNumerosParesEImpares = lista.stream()
				.map(e -> e * 3)
				.collect(Collectors.groupingBy(e -> e % 2 == 0));
		System.out.println("Qual são os elementos pares e ímpares no stream: " + mapaDeNumerosParesEImpares);
		
		Map<Integer, List<Integer>> mapaDoRestoDaDivisaoPorTres = lista.stream()
				.collect(Collectors.groupingBy(e -> e % 3));
		System.out.println("Qual são os elementos agrupados pelo resto da divisão por três: " + mapaDoRestoDaDivisaoPorTres);
		
		String listaConvertidaParaString = lista.stream()
				.map(e -> String.valueOf(e))
				.collect(Collectors.joining());
		System.out.println("Elementos convertidos para String e agrupados: " + listaConvertidaParaString);

		String listaConvertidaParaStringComDelimitador = lista.stream()
				.map(e -> String.valueOf(e))
				.collect(Collectors.joining("-"));
		System.out.println("Elementos convertidos para String e agrupados: " + listaConvertidaParaStringComDelimitador);

		


	}
}
