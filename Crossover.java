import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.opt4j.core.genotype.PermutationGenotype;
import org.opt4j.operators.crossover.Pair;

public class Crossover {
    public static ArrayList<Integer> stick = new ArrayList<Integer>(),
            						 stick2 = new ArrayList<Integer>(),
            						 hijo1,
            						 hijo2;

    
    public static void combinar(ArrayList<Integer> a1, ArrayList<Integer> a2) {
    	Random random = new Random();
    	
    	PermutationGenotype<Integer> p1 = new PermutationGenotype<Integer>(a1);
    	PermutationGenotype<Integer> p2 = new PermutationGenotype<Integer>(a2);

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
        hijo1 = (ArrayList<Integer>) offspring.getFirst();
        hijo2 = (ArrayList<Integer>) offspring.getSecond();
    }

   public static void imprimir(int entrada) {
        if(entrada==1){
            System.out.println();
            System.out.println("Etiquetas");
            for (int i = 0; i < stick.size(); i++) {
                System.out.print(stick.get(i) + " ");
            }
        }else {
            System.out.println();
            System.out.println("Etiquetas2");
            for (int i = 0; i < stick2.size(); i++) {
                System.out.print(stick2.get(i) + " ");
            }
        }
    }
}