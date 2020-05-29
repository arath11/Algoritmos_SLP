import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Grafo {
	public static int n, cont = 1, contador = 0;
	public static ArrayList<Integer> array1 = new ArrayList<Integer>(),
									 array2 = new ArrayList<Integer>(),
									 v = new ArrayList<Integer>();
	public static ArrayList<Individuo> population = new ArrayList<Individuo>(),
			                           population2 = new ArrayList<Individuo>();
	public static Integer minDefinitivo, minTmp, cantIndividuos, cantPoblaciones;
	public static ArrayList<ArrayList<Integer>> pob2 = new ArrayList<ArrayList<Integer>>();

	public static void poblacion(ArrayList<Integer> arre) {
		for (Integer i = 0; i < cantIndividuos; i++) {
			Permutacion.Permutacion(arre);
			population.add(new Individuo(busquedaLocal(arre, sumMinIndividuo(arre))));
		}
	}

	public static ArrayList<Integer> mutar(ArrayList<Integer> mutante) {

		Random random = new Random();
		ArrayList<Integer> mutanteAuxiliar = (ArrayList<Integer>) mutante.clone();

		for (Integer i = 0; i < mutanteAuxiliar.size() / 2; i++) {
			Integer datotmp = random.nextInt(mutanteAuxiliar.size());
			Integer segundodato = random.nextInt(mutanteAuxiliar.size());
			Integer tmp = mutanteAuxiliar.get(datotmp);
			mutanteAuxiliar.set(datotmp, mutanteAuxiliar.get(segundodato));
			mutanteAuxiliar.set(segundodato, tmp);
		}
		return mutanteAuxiliar;
	}

	public static Integer sumMinIndividuo(ArrayList<Integer> arrayList) {
		// Operaciones
		Integer minAcum = 0;
		
		for (int i = 0; i < array1.size(); i++) {
			if (arrayList.get(array1.get(i)) < arrayList.get(array2.get(i))) {
				minAcum += arrayList.get(array1.get(i));
			} else {
				minAcum += arrayList.get(array2.get(i));
			}
		}

		return (minAcum);
	
	}

	public static Individuo busquedaLocal(ArrayList<Integer> arrayList, Integer minimo) {
		ArrayList<Integer> arrayMejorado = new ArrayList<Integer>();
		arrayMejorado = mutar(arrayList);
		Integer minAux = sumMinIndividuo(arrayMejorado);

		if (minAux < minimo) {
			return new Individuo(minAux, arrayMejorado);
		}
		return new Individuo(minimo, arrayList);
	}

	public static void cruza() {
		ArrayList<Individuo> pob;
		pob = (ArrayList<Individuo>) population.clone();
		
		
		pob2.clear();
		population2 = (ArrayList<Individuo>) population.clone();
		population.clear();
		
		//System.out.println(population2.get(0).minimo);
		
		Collections.sort(pob);
		minTmp = pob.get(0).minimo;
		System.out.println(minTmp + " " + cont++);
		
		for (Integer i = 1; i <= (pob.size()/2); i++) {
			Crossover.combinar(pob.get(0).list, pob.get(i).list);
			pob2.add(Crossover.hijo1);
			pob2.add(Crossover.hijo2);
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		//Lectura
		int a1 = 0, a2 = 0, a3 = 0;
		while (true) {
			a1 = sc.nextInt();
			if(a1 == -1) {
				break;
			}
			a2 = sc.nextInt();
			if(a2 == -1) {
				System.out.println("Ingreso los datos incorrectamente");
				return;
			}
			array1.add(a1);
			array2.add(a2);
	    }
	
		quitarRepetidos(array1,array2);

			
		// Rellenar
		//ArrayList<Integer> arre = new ArrayList<Integer>();
		for (Integer i = 1; i <= n; i++) {
			v.add(i);
		}

		cantPoblaciones = 100;

		// Tiene que ser par
		cantIndividuos = 50;
		
		boolean tronar=true;

		poblacion(v);

		cruza();
		minDefinitivo = minTmp;
		while(tronar) {
			
			for (Integer i = 0; i < cantIndividuos; i++) {
				population.add(new Individuo(busquedaLocal(pob2.get(i), sumMinIndividuo(pob2.get(i)))));
				population.add(population2.get(i));
			}
			
			
			Collections.sort(population);
			
			int k=population.size()/2;
			int f =population.size()/2;
			while(k!=0) {
				population.remove(f);
				k--;
			}
			
			
			if (minTmp < minDefinitivo) {
				minDefinitivo = minTmp;
				contador = 0;
			} else {
				contador++;
				if(contador == 10000){
					System.out.println("El minimo final es: " + minDefinitivo);
					System.out.print("El etiquetado final es: ");
					for(int x = 0; x < population.get(0).list.size();x++) {
						System.out.print(population.get(0).list.get(x) + " ");
					}
					tronar= !tronar;
					break;
				}
			}
			
			cruza();
		}
		
	}
	
	public static void quitarRepetidos(ArrayList<Integer> array1, ArrayList<Integer> array2) {
		for (int i = 0; i < array1.size(); i++) {
			for(int j = 0; j < array2.size();j++) {
				if(array1.get(i) == array2.get(j) && array2.get(i) == array1.get(j)) {
					array1.remove(j);
					array2.remove(j);
				}
			}
		}
	}
}


class Individuo implements Comparable<Individuo> {
	public Integer minimo;
	public ArrayList<Integer> list;

	public Individuo(Individuo ind) {
		this.minimo = ind.minimo;
		this.list = ind.list;
	}

	public Individuo(Integer minimo, ArrayList<Integer> list) {
		this.minimo = minimo;
		this.list = list;
	}

	@Override
	public int compareTo(Individuo ind) {
		if (minimo < ind.minimo) {
			return -1;
		}
		if (minimo > ind.minimo) {
			return 1;
		}
		return 0;
	}

}
