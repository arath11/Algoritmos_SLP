import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.opt4j.core.genotype.PermutationGenotype;
import org.opt4j.operators.crossover.Pair;

public class Crossover {
    public static Individuo hijo1, hijo2;

    
    public static void combinar(Individuo a1, Individuo a2) {
    	Random random = new Random();
    	
    	PermutationGenotype<Integer> p1 = new PermutationGenotype<>(a1.list);
    	PermutationGenotype<Integer> p2 = new PermutationGenotype<>(a2.list);

    	PermutationGenotype<Object> o1 = p1.newInstance();
        PermutationGenotype<Object> o2 = p2.newInstance();

        int size = p1.size();

        Set<Object> elements = new HashSet<Object>();

        int i = 0;
        int j = 0;

        while (o1.size() != size || o2.size() != size) {
                if (j == size || (random.nextBoolean() && i < size)) {
                        Object e = p1.get(i);
                        i++;
                        if (elements.add(e)) {
                                o1.add(e);
                        } else {
                                o2.add(e);
                        }
                } else {
                        Object e = p2.get(j);
                        j++;

                        if (elements.add(e)) {
                                o1.add(e);
                        } else {
                               o2.add(e);
                        }
                }
        }
        Pair<PermutationGenotype<?>> offspring = new Pair<PermutationGenotype<?>>(o1, o2);
        ArrayList<Integer> h1 = (ArrayList<Integer>) offspring.getFirst();
        ArrayList<Integer> h2 = (ArrayList<Integer>) offspring.getSecond();

        hijo1 = new Individuo(Grafo.sumMinIndividuo(h1), h1);
        hijo2 = new Individuo(Grafo.sumMinIndividuo(h2), h2);
    }
}