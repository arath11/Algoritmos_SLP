import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Grafo {
	public static int n;
	public static int[][] matriz;
	public static int[] aristas, etiquetas, etiquetas2,hijo1, hijo2;

	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		matriz = new int[n][n];
		aristas = new int[n];
		etiquetas = new int[n];
		etiquetas2 = new int[n];

		Integer maxArista = 0, 
				maxAristaAcum = 0;
				//maxAristaAnterior = 0, 
				//maxAristaAnteriorAcum = 0;

		//rellenar 
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = sc.nextInt();
				if (matriz[i][j] == 1) {
					aristas[i]++;
				}
			}

			if (aristas[i] > maxAristaAcum) {
				maxArista = i;
				maxAristaAcum = aristas[i];
			} 
		}
		
		etiquetas[maxArista] = 1;
		etiquetas2[maxArista] = 1;
		
		//etiquetas[maxAristaAnterior] = 2;

		//llena los arrays para la permutacion 
		ArrayList<Integer> arre = new ArrayList<Integer>();
		for (int i = 2; i <= n;  i++) {
			arre.add(i);
		}
		
		//padre1
		Permutacion permutacion = new Permutacion(arre);
		
		
		int aux = 0;
		for (int i = 0; i < n; i++) {
			if (etiquetas[i] != 1) {
				etiquetas[i] = Permutacion.genotype.get(aux);
				aux++;
			}
			if (aux == Permutacion.genotype.size()) {
				break;
			}
		}
		
		//padre2
		Permutacion permutacion2 = new Permutacion(arre);
		
		etiquetas2[maxArista] = 1;
		
		int aux2 = 0;
		for (int i = 0; i < n; i++) {
			if (etiquetas2[i] != 1) {
				etiquetas2[i] = Permutacion.genotype.get(aux2);
				aux2++;
			}
			if (aux2 == Permutacion.genotype.size()) {
				break;
			}
		}
		

		//combinar(etiquetas,etiquetas2);
		imprimir(1);
		imprimir(2);
		combinar(etiquetas,etiquetas2);
		//mutar(etiquetas);
		//mutar(etiquetas2);
		//imprimir(1);imprimir(2);
	}
	
	
	public static void combinar(int[] etiquetas, int[] etiquetas2) {
		//todo poner un for de 0 hasta la poblacions
		//esto imprime para ver primero las dos entradas
		//imprimir(1);
		System.out.println();

		//asignamos los hijos al tamaño
		hijo1=new int[etiquetas.length];
		hijo2=new int[etiquetas.length];
 
		
		//definir punto de corte 1
		int corte1=etiquetas.length/2-1;
		System.out.println("Corte1:");
		System.out.println(corte1);
		
		//definir punto de corte 2
		int corte2=etiquetas.length/2;
		System.out.println("Corte2:");
		System.out.println(corte2);

		
		//System.out.println("Primer for:");
		//izquierda
		for(int i = 0; i < corte1; i++) {
		hijo1[i]=etiquetas[i];
		}

		//centro
		for(int i = corte1; i < corte2+1; i++) {
			hijo1[i]=etiquetas2[i];
		}
		
		//System.out.println("Segundo for:");
		//derecha
		for(int i = corte2+1; i < etiquetas.length; i++) {
			hijo1[i]=etiquetas[i];
		}


		//imprimir();
		imprimirHijo(1);

		
		//izqueirda
		//System.out.println("Primer for:");
		for(int i = 0; i < corte1; i++) {
		hijo2[i]=etiquetas2[i];
		}

		//centro
		for(int i = corte1; i < corte2+1; i++) {
			hijo2[i]=etiquetas[i];
		}
		
		//derecha
		//System.out.println("Segundo for:");
		for(int i = corte2+1; i < etiquetas.length; i++) {
			hijo2[i]=etiquetas2[i];
		}
		imprimirHijo(2);	
	}
	
	

	public static int[] mutar(int[] mutante) {

		Random random = new Random();
		for (int i = 0; i < mutante.length/2; i++){
			//System.out.println(1111);
			int datotmp=random.nextInt(mutante.length);
			int segundodato=random.nextInt(mutante.length);
			if( 1!=mutante[datotmp] && 1!=mutante[segundodato]) {
				//System.out.println("entro al if");
				int tmp = mutante[datotmp];
				mutante[datotmp] = mutante[segundodato];
				mutante[segundodato] = tmp;
				System.out.println();
				System.out.println("En el if, ciclo :"+(i+1));
				for (int j = 0; j < mutante.length;j++) {
					System.out.print(mutante[j] + " ");
					}

			}else{
				i--;
				System.out.println();
				System.out.println("Resta un ciclo");
			}

		}
		System.out.println("Final:");
		for (int j = 0; j < mutante.length;j++) {
			System.out.print(mutante[j] + " ");
		}
		return mutante;
		/*
		int datotmp=random.nextInt(mutante.length);
		int segundodato=random.nextInt(mutante.length);

		//if( 1!=mutante[datotmp] && 1!=mutante[segundodato]){
			//System.out.println("entro al if");
			int tmp=mutante[datotmp];
			mutante[datotmp]=mutante[segundodato];
			mutante[segundodato]=tmp;
			return mutante;
		//}*/
		//return mutante;
	}
	
	public static void imprimir(int entrada) {
		if(entrada==1){
			System.out.println();
			System.out.println("Etiquetas");
			for (int i = 0; i < n; i++) {
				System.out.print(etiquetas[i] + " ");
			}
		}else{
			System.out.println();
			System.out.println("Etiquetas2");
			for (int i = 0; i < n; i++) {
				System.out.print(etiquetas2[i] + " ");
			}
		}


	}
	
	
	public static void imprimirHijo(int hijo) {
		if(hijo==1) {
			System.out.println("Hijo1");
			for (int i = 0; i < hijo1.length; i++) {
				System.out.print(hijo1[i] + " ");
			}
			System.out.println();	
		}else if( hijo ==2) {
			System.out.println("Hijo2");
			for (int i = 0; i < hijo2.length; i++) {
				System.out.print(hijo2[i] + " ");
			}	

		}else{
		
		}
	}
	
	
}


//prueba usada
/*
	4
	0 1 1 0
	1 0 1 0
	1 1 0 0
	0 1 0 0*/
