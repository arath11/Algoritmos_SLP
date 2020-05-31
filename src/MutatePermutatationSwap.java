import java.util.Collections;
import java.util.Random;

import org.opt4j.core.genotype.PermutationGenotype;
import org.opt4j.operators.mutate.MutationRate;

public class MutatePermutatationSwap {

	public static Random random = new Random();
	public static Double mutationRate = 0.1;
	
	public static Individuo mutate(Individuo ind) {
		PermutationGenotype<Integer> genotype = new PermutationGenotype<Integer>(ind.list);
		
		int size = genotype.size();
		
		if (size > 1) {
			for (int i = 0; i < size; i++) {
				if (random.nextDouble() < mutationRate) {
					int j;
					do {
						j = random.nextInt(size);
					} while (j == i);

					Collections.swap(genotype, i, j);
				}
			}
		}
		return new Individuo(Grafo.sumMinIndividuo(genotype),genotype);
	}
}
