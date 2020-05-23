import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Grafo {
	public static int n;
	public static int[][] matrix;
	public static int[] v;
	public static ArrayList<Individuo> population;
	public static int minDefinitivo, minTmp, cantIndividuos,cantPoblaciones;
	public static ArrayList<ArrayList<Integer>> pob2 = new ArrayList<ArrayList<Integer>>();

	public static void poblacion(int maxArista, ArrayList<Integer> arre) {
		population = new ArrayList<Individuo>();

		int aux = 0;
		for (int i = 0; i < cantIndividuos; i++) {
			aux = 0;
			Permutacion.Permutacion(arre);

			ArrayList<Integer> listaAux = new ArrayList<Integer>();

			for (int j = 0; j < n; j++) {
				if (j == maxArista) {
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
			population.add(new Individuo(busquedaLocal(listaAux, sumMinIndividuo(listaAux))));
		}
	}

	public static ArrayList<Integer> mutar(ArrayList<Integer> mutante) {

		Random random = new Random();
		ArrayList<Integer> mutanteAuxiliar = (ArrayList<Integer>) mutante.clone();

		for (int i = 0; i < mutanteAuxiliar.size() / 2; i++) {
			int datotmp = random.nextInt(mutanteAuxiliar.size());
			int segundodato = random.nextInt(mutanteAuxiliar.size());
			if (1 != mutanteAuxiliar.get(datotmp) && 1 != mutanteAuxiliar.get(segundodato)) {
				int tmp = mutanteAuxiliar.get(datotmp);
				mutanteAuxiliar.set(datotmp, mutanteAuxiliar.get(segundodato));
				mutanteAuxiliar.set(segundodato, tmp);
			} else {
				i--;
			}
		}

		return mutanteAuxiliar;
	}

	public static int sumMinIndividuo(ArrayList<Integer> arrayList) {
		int minAcum = 0, etiqueta1 = 0, etiqueta2 = 0;

		// Operaciones
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 1) {
					etiqueta1 = arrayList.get(i);
					etiqueta2 = arrayList.get(j);
					matrix[j][i] = 0;
					if (etiqueta1 < etiqueta2) {
						minAcum += etiqueta1;
					} else {
						minAcum += etiqueta2;
					}
				}
			}
		}
		return minAcum;
	}

	public static Individuo busquedaLocal(ArrayList<Integer> arrayList, int minimo) {
		ArrayList<Integer> arrayMejorado = new ArrayList<Integer>();
		arrayMejorado = mutar(arrayList);
		int minAux = sumMinIndividuo(arrayMejorado);

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
		for (int i = 0; i < pob.size() - 1; i += 2) {
			Crossover.combinar(pob.get(i).list, pob.get(i + 1).list);
			pob2.add(Crossover.hijo1);
			pob2.add(Crossover.hijo2);
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		matrix = new int[n][n];
		v = new int[n];

		Integer maxArista = 0, maxAristaAcum = 0;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = sc.nextInt();
				if (matrix[i][j] == 1) {
					v[i]++;
				}
			}
			if (v[i] > maxAristaAcum) {
				maxArista = i;
				maxAristaAcum = v[i];
			}
		}

		ArrayList<Integer> arre = new ArrayList<Integer>();
		for (int i = 2; i <= n; i++) {
			arre.add(i);
		}

		cantPoblaciones = 25;

		//Tiene que ser par
		cantIndividuos = 10;

		poblacion(maxArista, arre);

		cruza();
		minDefinitivo = minTmp;
		for (int i = 0; i <= cantPoblaciones; i++) {
			for (int j = 0; j < cantIndividuos; j++) {
				population.add(new Individuo(busquedaLocal(pob2.get(j), sumMinIndividuo(pob2.get(j)))));
			}
			if (minTmp < minDefinitivo) {
				minDefinitivo = minTmp;
			}
			cruza();
		}
		System.out.println("El minimo final es: " + minDefinitivo);
	}
}

class Individuo implements Comparable<Individuo> {
	public int minimo;
	public ArrayList<Integer> list;

	public Individuo(Individuo ind) {
		this.minimo = ind.minimo;
		this.list = ind.list;
	}

	public Individuo(int minimo, ArrayList<Integer> list) {
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