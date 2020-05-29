import java.util.ArrayList;
import java.util.Random;

import org.opt4j.core.genotype.PermutationGenotype;

public class Permutacion extends PermutationGenotype<Object> {
	
	public static PermutationGenotype<Integer> genotype;
	
	public static void Permutacion(ArrayList<Integer> v) {
		genotype = new PermutationGenotype<Integer>(v);
		genotype.init(new Random());
	}
	
	public static void Imprimir() {
		System.out.println("Permutación");
		
		for(int i = 0; i < genotype.size(); i++) {
			System.out.print(genotype.get(i) + " ");
		}
		System.out.println();
	}
}
