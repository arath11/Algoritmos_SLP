import java.util.ArrayList;
import java.util.Random;

import org.opt4j.core.genotype.PermutationGenotype;

public class Permutacion extends PermutationGenotype<Object> {
	
	public static PermutationGenotype<Integer> genotype;
	
	public Permutacion(ArrayList<Integer> arr) {
		genotype = new PermutationGenotype<Integer>(arr);
		genotype.init(new Random());
		
		System.out.println("Permutación");

		for(int i = 0; i < genotype.size(); i++) {
			System.out.print(genotype.get(i) + " ");
		}
		System.out.println();
	}
}
