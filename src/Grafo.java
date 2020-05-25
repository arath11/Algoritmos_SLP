import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Grafo {
	public static int n, cont = 1, contador = 0;
	public static int[] v;
	public static ArrayList<Integer> array1 = new ArrayList<Integer>(),
									 array2 = new ArrayList<Integer>(),
									 array3 = new ArrayList<Integer>();
	public static ArrayList<Individuo> population;
	public static Integer minDefinitivo, minTmp, cantIndividuos, cantPoblaciones;
	public static ArrayList<ArrayList<Integer>> pob2 = new ArrayList<ArrayList<Integer>>();

	public static void poblacion(Integer maxArista, ArrayList<Integer> arre) {
		population = new ArrayList<Individuo>();

		Integer aux = 0;
		for (Integer i = 0; i < cantIndividuos; i++) {
			aux = 0;
			Permutacion.Permutacion(arre);
			System.out.print("Esto vale el máx arista: "+ maxArista);

			ArrayList<Integer> listaAux = new ArrayList<Integer>();

			for (Integer j = 0; j < n; j++) {
				if (j == maxArista) {
					System.out.print("Entré:");
					listaAux.add(1);
				}
				if (j != maxArista) {

					listaAux.add(Permutacion.genotype.get(aux));
					aux++;
				}
				if (aux == Permutacion.genotype.size()) {
					break;
				}
			}
			
			for(int k = 0; k < listaAux.size();k++) {
				System.out.print(listaAux.get(k) + " ");
			}
			
			population.add(new Individuo(busquedaLocal(listaAux, sumMinIndividuo(listaAux))));
		}
	}

	public static ArrayList<Integer> mutar(ArrayList<Integer> mutante) {

		Random random = new Random();
		ArrayList<Integer> mutanteAuxiliar = (ArrayList<Integer>) mutante.clone();

		for (Integer i = 0; i < mutanteAuxiliar.size() / 2; i++) {
			Integer datotmp = random.nextInt(mutanteAuxiliar.size());
			Integer segundodato = random.nextInt(mutanteAuxiliar.size());
			if (1 != mutanteAuxiliar.get(datotmp) && 1 != mutanteAuxiliar.get(segundodato)) {
				Integer tmp = mutanteAuxiliar.get(datotmp);
				mutanteAuxiliar.set(datotmp, mutanteAuxiliar.get(segundodato));
				mutanteAuxiliar.set(segundodato, tmp);
			} else {
				i--;
			}
		}

		return mutanteAuxiliar;
	}

	public static Integer sumMinIndividuo(ArrayList<Integer> arrayList) {
		// Operaciones
		Integer minAcum = 0;
		
		for (Integer i = 0; i < array1.size(); i++) {
			for (Integer j = 0; j < array2.size(); j++) {
				if (array3.get(i) != 0) {
					if (array1.get(i) == array2.get(j) && array2.get(i) == array1.get(j)) {
						array3.set(j, 0);
						if (arrayList.get(array1.get(i)) < arrayList.get(array1.get(j))) {
							minAcum += arrayList.get(array1.get(i));
						} else {
							minAcum += arrayList.get(array1.get(j));
						}
					}
				}
			}

		}
		return minAcum;
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
		Collections.sort(pob);
		minTmp = pob.get(0).minimo;
		System.out.println("GANADORA");
		for (int k=0;k<pob.get(0).list.size();k++) {
			System.out.print(pob.get(0).list.get(k)+" ");
		}
		System.out.println();
		System.out.println(minTmp + " " + cont++);
		for (Integer i = 0; i < pob.size() - 1; i += 2) {
			Crossover.combinar(pob.get(i).list, pob.get(i + 1).list);
			pob2.add(Crossover.hijo1);
			pob2.add(Crossover.hijo2);
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		v = new int[n];

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
			a3 = sc.nextInt();
			if(a3 == -1) {
				System.out.println("Ingreso los datos incorrectamente");
				return;
			}
			array1.add(a1);
			array2.add(a2);
			array3.add(a3);
	    }
		
		Integer maxArista = 0, maxAristaAcum = 0, cont = 0;

		for(int i = 0; i < n;i++) {
			while(array1.get(cont) == i) {
				v[i]++;
				cont++;
				if(cont > array1.size() - 1) {
					break;
				}
			}
			
			if (v[i] > maxAristaAcum) {
				maxArista = i;
				maxAristaAcum = v[i];
			}
		}
		
		System.out.println(maxArista);
		
			
		// Rellenar
		ArrayList<Integer> arre = new ArrayList<Integer>();
		for (Integer i = 2; i <= n; i++) {
			arre.add(i);
		}

		cantPoblaciones = 100;

		// Tiene que ser par
		cantIndividuos = 50;
	
		
		
		

		poblacion(maxArista, arre);

		cruza();
		minDefinitivo = minTmp;
		boolean tronar=false;
		int contadorAux=1;
		while(!tronar) {
			for (Integer j = 0; j < cantIndividuos; j++) {
				population.add(new Individuo(busquedaLocal(pob2.get(j), sumMinIndividuo(pob2.get(j)))));
			}
			if (minTmp < minDefinitivo) {
				minDefinitivo = minTmp;
				contador = 0;
			} else {
				contador++;
				if(contador == 100) {
					tronar=true;
					break;
				}
			}
			contadorAux++;
			
			cruza();
		}
		System.out.println("El minimo final es: " + minDefinitivo);
		System.out.print(contadorAux);
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
