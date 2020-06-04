import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import org.opt4j.core.genotype.PermutationGenotype;
import org.opt4j.operators.crossover.Pair;


public class Grafo {
	//Atributos
	public static int n, iteraciones = 5;
	public static ArrayList<Integer> array1 = new ArrayList<Integer>(),
			array2 = new ArrayList<Integer>(),
			v = new ArrayList<Integer>();
	public static PermutationGenotype<Integer> genotype;
	public static ArrayList<Individuo> population = new ArrayList<Individuo>(),
			populationSuperviviente = new ArrayList<Individuo>(),
			populationHijosElegidos = new ArrayList<Individuo>(),
			populationHijos = new ArrayList<Individuo>(),
			populationTotal = new ArrayList<Individuo>();
	public static Integer minDefinitivo, minTmp, cantIndividuos;

	//Generación de población
	public static void poblacion(ArrayList<Integer> v) {
		for (Integer i = 0; i < cantIndividuos; i++) {
			PermutationGenotype<Integer> pm = permutation(v);
			population.add(new Individuo(sumMinIndividuo(pm),pm));
		}
	}

	//Pemutación
	public static PermutationGenotype<Integer> permutation(ArrayList<Integer> v){
		genotype = new PermutationGenotype<Integer>(v);
		genotype.init(new Random());
		return new PermutationGenotype<Integer>(genotype);
	}

	//Selección de cruza(Torneo Binario)
	public static void torneoBinario() {
		//Random
		Random random = new Random();
		//Torneo
		Individuo peleador1 = new Individuo();
		Individuo peleador2 = new Individuo();
		Individuo peleador3 = new Individuo();
		Individuo peleador4 = new Individuo();

		Individuo ganadorTorneo1 = new Individuo();
		Individuo ganadorTorneo2 = new Individuo();

		for(Integer i = 0; i < population.size(); i++) {
			//Primer torneo
			Integer tmp = random.nextInt(population.size());
			peleador1 = population.get(tmp);

			Integer tmp2 = random.nextInt(population.size());
			while(tmp == tmp2) {
				tmp2 = random.nextInt(population.size());
			}
			peleador2 = population.get(tmp2);

			if(peleador1.minimo < peleador2.minimo) {
				ganadorTorneo1 = peleador1;
			} else {
				ganadorTorneo1 = peleador2;
			}

			//Segundo torneo
			Integer tmp3 = random.nextInt(population.size());
			peleador3 = population.get(tmp3);

			Integer tmp4 = random.nextInt(population.size());
			while(tmp3 == tmp4) {
				tmp4 = random.nextInt(population.size());
			}
			peleador4 = population.get(tmp4);


			if(peleador3.minimo < peleador4.minimo) {
				ganadorTorneo2 = peleador3;
			} else {
				ganadorTorneo2 = peleador4;
			}

			//Cruza de campeones
			cruza(ganadorTorneo1, ganadorTorneo2);
		}
	}

	//Cruza
	public static void cruza(Individuo ganadorTorneo1, Individuo ganadorTorneo2) {
		//Hijos
		Pair<PermutationGenotype<?>> pair = Crossover.combinar(ganadorTorneo1, ganadorTorneo2);
		populationHijos.add(new Individuo(sumMinIndividuo((ArrayList<Integer>) pair.getFirst()), (ArrayList<Integer>) pair.getFirst()));
		populationHijos.add(new Individuo(sumMinIndividuo((ArrayList<Integer>) pair.getSecond()), (ArrayList<Integer>) pair.getSecond()));
	}

	//Hijo elegidos
	public static void hijosElegidos() {
		//Randomizer
		ArrayList<Integer> random = new ArrayList<Integer>();
		for(Integer i = 0; i < populationHijos.size(); i++) {
			random.add(i);
		}
		Collections.shuffle(random);
		//---------------------------------------------------
		//Elegidos
		for(int i = 0; i < population.size(); i++) {
			populationHijosElegidos.add(populationHijos.get(random.get(i)));
		}
	}

	//Mutacion de los supervivientes
	public static void Mutacion() {
		for(Integer i = 0; i < populationHijosElegidos.size(); i++) {
			populationHijosElegidos.set(i, MutatePermutatationSwap.mutate(populationHijosElegidos.get(i)));
		}
	}

	//Búsqueda local
	public static void busquedaLocal() {
		for(Integer k = 0; k < populationHijosElegidos.size(); k++) {
			Individuo original = new Individuo();
			original = populationHijosElegidos.get(k);
			Integer i2 = 0;
			Integer j2 = 0;
			Integer nuevo1 = 0;
			for(Integer i = 0; i < original.list.size(); i++) {
				for(Integer j = i + 1; j < original.list.size(); j++) {
					swap(populationHijosElegidos.get(k),i,j);
					nuevo1 = populationHijosElegidos.get(k).minimo;

					i2 = i;
					j2 = j;
					swap(populationHijosElegidos.get(k),i,j);


					if(original.minimo > nuevo1) {
						swap(populationHijosElegidos.get(k),i2,j2);
					}

				}

			}
		}
	}

	//Swap de búsqueda local
	public static Individuo swap(Individuo ind, Integer i, Integer j) {
		Integer tmp1 = 0,
				tmp2 = 0;

		tmp1 = ind.list.get(i);
		tmp2 = ind.list.get(j);

		ind.list.set(i, tmp2);
		ind.list.set(j, tmp1);
		ind.minimo = sumMinIndividuo(ind.list);
		return ind;
	}

	//Merge de popblacion original con la mejorada
	public static void seleccionSupervivientes() {
		//Merge de P y P'
		populationTotal.addAll(population);
		populationTotal.addAll(populationHijosElegidos);
		Collections.sort(populationTotal);

		//El mejor de toda la población
		minTmp = populationTotal.get(0).minimo;
		//Siguiente generación (45 mejores)
		for(Integer i = 0; i < population.size() - 5; i++) {
			populationSuperviviente.add(populationTotal.get(i));
		}

		//Randomizer
		ArrayList<Integer> random = new ArrayList<Integer>();
		for(Integer i = population.size() - 5; i < populationTotal.size(); i++) {
			random.add(i);
		}
		Collections.shuffle(random);
		//----------------------------------------------------------
		//Diversidad en la población
		for(Integer i = 0; i < 5; i++) {
			populationSuperviviente.add(populationTotal.get(random.get(i)));
		}
	}

	//Sacar mínimos
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

	//Método main
	public static void main(String[] args) {
		//Lectura de instancias
		Scanner sc = new Scanner(System.in);
		try {
			n = sc.nextInt();

			//Lectura
			int a1 = 0, a2 = 0;
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
		} catch(Exception e) {
			System.out.print("Entada incorrecta");
			return;
		}

		Long startTime = System.currentTimeMillis();

		quitarRepetidos(array1,array2);

		// Rellenar
		for (Integer i = 1; i <= n; i++) {
			v.add(i);
		}

		// Tiene que ser par
		cantIndividuos = 50;

		boolean flag = true;

		//Primera vez que se genera la poblacion
		poblacion(v);
		torneoBinario();
		hijosElegidos();
		Mutacion();
		busquedaLocal();
		seleccionSupervivientes();

		minDefinitivo = minTmp;
		Integer cont = 0;
		while(flag) {
			//La poblacion original ahora es la P'
			population = (ArrayList<Individuo>) populationSuperviviente.clone();
			//Borrar poblaciones temporales
			populationHijos.clear();
			populationHijosElegidos.clear();
			populationSuperviviente.clear();
			populationTotal.clear();

			//Se repite el ciclo
			torneoBinario();
			hijosElegidos();
			Mutacion();
			busquedaLocal();
			seleccionSupervivientes();

			if(minTmp < minDefinitivo) {
				minDefinitivo = minTmp;
				cont = 0;
			} else {
				cont++;
				if(cont == iteraciones) {
					break;
				}
			}

		}
		System.out.println("Mínimo total: " + minDefinitivo);
		System.out.println("Etiquetado mínimo: " + populationTotal.get(0).list);
		Long endTime = System.currentTimeMillis();
		Long duration = (endTime - startTime);  //Total execution time in milli seconds
		System.out.println("Tiempo de ejecución: " + duration + " milisegundos");
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