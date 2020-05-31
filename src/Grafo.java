import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import org.opt4j.operators.mutate.MutatePermutationSwap;

public class Grafo {
	//Atributos
	public static int n, cont = 1, contador = 0;
	public static ArrayList<Integer> array1 = new ArrayList<Integer>(),
									 array2 = new ArrayList<Integer>(),
									 v = new ArrayList<Integer>();
	public static ArrayList<Individuo> population = new ArrayList<Individuo>(),
									   populationNext = new ArrayList<Individuo>(),
									   populationSuperviviente = new ArrayList<Individuo>(),
									   populationHijos = new ArrayList<Individuo>(),
			                           populationTotal = new ArrayList<Individuo>();
	public static Integer minDefinitivo, minTmp, cantIndividuos, cantPoblaciones;
	public static ArrayList<ArrayList<Integer>> pob2 = new ArrayList<ArrayList<Integer>>();
	
	//Generación de población
	public static void poblacion(ArrayList<Integer> v) {
		for (Integer i = 0; i < cantIndividuos; i++) {
			Permutacion.Permutacion(v);
			population.add(new Individuo(sumMinIndividuo(v),v));
		}
		torneoBinario(population);
	}
	
	//Selección de cruza(Torneo Binario)
	public static void torneoBinario(ArrayList<Individuo> individuo) {
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
		Supervivientes();
	}
	
	//Cruza
	public static void cruza(Individuo ganadorTorneo1, Individuo ganadorTorneo2) {
		//Hijos
		Crossover.combinar(ganadorTorneo1, ganadorTorneo2);
		populationHijos.add(Crossover.hijo1);
		populationHijos.add(Crossover.hijo2);
	}
	
	//Supervivientes (Hijos después de la guerra)
	public static void Supervivientes() {
		//Randomizer
		ArrayList<Integer> random = new ArrayList<Integer>();
		for(Integer i = 0; i < populationHijos.size(); i++) {
			random.add(i);
		}
		Collections.shuffle(random);
		//---------------------------------------------------
		//Supervivientes
		for(int i = 0; i < population.size(); i++) {
			populationSuperviviente.add(populationHijos.get(random.get(i)));
		}
		Mutacion();
	}
	
	//Mutacion de los supervivientes
	public static void Mutacion() {
		for(Integer i = 0; i < populationSuperviviente.size(); i++) {
			populationSuperviviente.set(i, MutatePermutatationSwap.mutate(populationSuperviviente.get(i)));
		}
		busquedaLocal();
		//seleccionFinal();
	}
	
	
	//Búsqueda local 
	public static void busquedaLocal() {
		for(Integer k = 0; k < populationSuperviviente.size(); k++) {
			//System.out.println("***************");
			//System.out.println();
			Individuo original = new Individuo();
			Individuo nuevo = new Individuo();
			original = populationSuperviviente.get(k);
			int i2=0;
			int j2=0;
			int nuevo1=0;
			for(Integer i = 0; i < original.list.size(); i++) {
				//System.out.println("______________________");
				for(Integer j = i + 1; j < original.list.size(); j++) {
					//swap(tmp,i,j);
					//System.out.println(populationSuperviviente.get(k).minimo);
					swap(populationSuperviviente.get(k),i,j);
					nuevo1=populationSuperviviente.get(k).minimo;
					//System.out.println(populationSuperviviente.get(k).minimo+" SWAP"+nuevo.minimo);
					//reswap
					//break;
					i2=i;
					j2=j;
					swap(populationSuperviviente.get(k),i,j);
					//System.out.println();
					//System.out.println("___"+nuevo1);
					//System.out.println(populationSuperviviente.get(k).minimo+" PostSawp");
					
					//System.out.println();
		
					if(original.minimo > nuevo1) {
		//				System.out.println(original.minimo+"vs"+nuevo1+"-------"+minTmp);
						swap(populationSuperviviente.get(k),i2,j2);
						//populationSuperviviente.set(k, nuevo);
						//original=nuevo;
						//System.out.println(populationSuperviviente.get(k).minimo+"____");
					} 
			
				}
					//System.out.println(original.minimo+"tmp");
				//if(minTmp!=null) {
			
					//if(original.minimo > nuevo1 && minTmp>original.minimo) {
						
				
			
			}
			//break;
			
		}

		
		seleccionFinal();
	}
	/*
	public static void busquedaLocal() {
		for(Integer k = 0; k < populationSuperviviente.size(); k++) {
			Individuo best = new Individuo();
			Individuo tmp = new Individuo();
			tmp = populationSuperviviente.get(k);
			int i2=0;
			int j2=0;
			
			for(Integer i = 0; i < tmp.list.size(); i++) {
				for(Integer j = i + 1; j < tmp.list.size(); j++) {
					//swap(tmp,i,j);
					swap(populationSuperviviente.get(k),i,j);
					//reswap
					i2=i;
					j2=j;
					swap(populationSuperviviente.get(k),i2,j2);
				}
					System.out.println(best.minimo+"tmp");
				if(tmp.minimo > best.minimo) {
					populationSuperviviente.set(k, tmp);
					best = tmp;
					System.out.println(populationSuperviviente.get(k).minimo+"____");
				} 
			}
		}

		
		seleccionFinal();
	}*/
	
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
	public static void seleccionFinal() {
		//Merge de P y P'
		populationTotal.addAll(population);
		populationTotal.addAll(populationSuperviviente);
		Collections.sort(populationTotal);
		
		//El mejor de toda la población
		minTmp = populationTotal.get(0).minimo;
		System.out.println(minTmp+" Mejor actual");
		//Siguiente generación (45 mejores)
		for(Integer i = 0; i < population.size() - 5; i++) {
			populationNext.add(populationTotal.get(i));
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
			populationNext.add(populationTotal.get(random.get(i)));
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

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
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
	
		quitarRepetidos(array1,array2);

		// Rellenar
		//ArrayList<Integer> arre = new ArrayList<Integer>();
		for (Integer i = 1; i <= n; i++) {
			v.add(i);
		}

		// Tiene que ser par
		cantIndividuos = 50;
				
		boolean flag = true;
		
		poblacion(v);
		
		minDefinitivo = minTmp;
		Integer cont = 0;
		long ReyPonUnContador=0;
		while(flag) {
			ReyPonUnContador++;
			population = (ArrayList<Individuo>) populationNext.clone();
			//Borrar poblaciones temporales
			populationHijos.clear();
			populationSuperviviente.clear();
			populationNext.clear();
			populationTotal.clear();
			
			torneoBinario(population);
			
			if(minTmp < minDefinitivo) {
				minDefinitivo = minTmp;
				cont = 0;
			} else {
				cont++;
				if(cont == 50) {
					break;
				}
			}
			
		}
		System.out.println(ReyPonUnContador);
		System.out.println("Total: " + minDefinitivo);
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

	public Individuo() {
		this.minimo = 0;
		this.list = new ArrayList<Integer>();
	}
	
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
